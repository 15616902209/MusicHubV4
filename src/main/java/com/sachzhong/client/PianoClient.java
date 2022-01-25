package com.sachzhong.client;

import com.sachzhong.factory.GenerateFactory;
import com.sachzhong.factory.PianoGenerate;
import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.service.ChordGenerateService;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年1月26日 下午6:33:20
*  类名：PianoClient.java
*  类说明：
*/
public class PianoClient {
	
	public static void main(String[] args) {
		
		 GenerateFactory generateFactory=new GenerateFactory();
		 PianoGenerate pianoGenerate=generateFactory.getPianoGenerate();

		int speed=92;

		 ChordGenerateService chordUtil = new ChordGenerateService();
		
		 ChordProgression cp=chordUtil.getChordProgression("VI IV V I", "C", 3);
		 
		 Chord[] chords=cp.getChords();
		 
		 Pattern song=new Pattern();
		 Pattern[] pattern=new Pattern[2];
		 Pattern pianoLeftPattern=new Pattern();
		 Pattern pianoRightPattern=new Pattern();
		 //和弦音轨
		 Pattern chordPattern=new Pattern();
		 
		 for (int i = 0; i < chords.length; i++) {
			 
			 pattern=pianoGenerate.getFenJie_1(chords[i]);
			 pianoLeftPattern.add(pattern[0]);
			 pianoRightPattern.add(pattern[1]);
			 chordPattern.add("Rw|");
		 }

		for (int i = 0; i < chords.length; i++) {

			pattern=pianoGenerate.getFenJie_1(chords[i]);
			pianoLeftPattern.add(pattern[0]);
			pianoRightPattern.add(pattern[1]);
			chordPattern.add(chords[i]+"w|");
		}

		for (int i = 0; i < chords.length; i++) {

			pattern=pianoGenerate.getFenJie_2(chords[i]);
			pianoLeftPattern.add(pattern[0]);
			pianoRightPattern.add(pattern[1]);
			chordPattern.add(chords[i]+"h Rq  "+chords[i]+"q|");
		}

		for (int k=0;k<4;k++){
			for (int i = 0; i < chords.length; i++) {

				pattern=pianoGenerate.getzhushi_1(chords[i]);
				pianoLeftPattern.add(pattern[0]);
				pianoRightPattern.add(pattern[1]);
			}
		}

		 
		pianoLeftPattern.setVoice(0).setInstrument(Instruments.Piano).setTempo(speed);
		pianoRightPattern.setVoice(1).setInstrument(Instruments.Piano).setTempo(speed);
		chordPattern.setVoice(2).setInstrument(Instruments.Piano).setTempo(speed);
		 
		System.out.println(pianoLeftPattern.toString());
		System.out.println(pianoRightPattern.toString());
		System.out.println(chordPattern.toString());
		
		song.add(pianoLeftPattern);
		song.add(pianoRightPattern);
		song.add(chordPattern);
	
		
		
		Player player1=new Player();
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		playThread.start();

		MusicSaveThread saveThread=new MusicSaveThread(song, "piano");
		saveThread.start();
		 
	}

}
