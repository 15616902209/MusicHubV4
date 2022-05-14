package com.sachzhong.client;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.service.ChordGenerateService;
import com.sachzhong.service.MusicPaiService;
import com.sachzhong.service.NoteGenerateService;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;

import java.util.List;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年4月26日 上午11:06:29
*  类名：ChordTestClient.java
*  类说明：进行曲音乐
*/
public class MarchClient {
	
	public static void main(String[] args) {
		
		int speed = 120;
		int yinyu = 4;
		
		//和声
		ChordGenerateService chordUtil = new ChordGenerateService();
		ChordProgression cp=chordUtil.getChordProgression("VI I V II ", "G", yinyu);
		 
		Chord[] chords=cp.getChords();
		
		Pattern song=new Pattern();
		Pattern piano=new Pattern();
		Pattern guitar=new Pattern();
		Pattern violin=new Pattern();
		Pattern bass=new Pattern();
		Pattern musicbox=new Pattern();
		Pattern drum=new Pattern();
		

		
		NoteGenerateService noteGenerateService =new NoteGenerateService();
		
		MusicPaiService musicPaiService =new MusicPaiService();
		
		for (int i = 0; i < 10; i++) {
			
			for (int k = 0; k < chords.length; k++) {
				

				String chordRoot = chords[k].getRoot().toString();
				chordRoot=chordRoot.substring(0, 1);
				
				//List<String> mylist =  musicPaiUtil.getPai();
				List<String> mylist =  musicPaiService.getPai44();
				for (int j = 0; j < mylist.size(); j++) {

						String note= noteGenerateService.getNode(chordRoot, yinyu);
						note+=mylist.get(j);
						guitar.add(note);	
				}
				guitar.add(" | ");
				

				
				
				List<String> mylist2 =  musicPaiService.getPai();
				for (int j = 0; j < mylist2.size(); j++) {

					//复调
					String note2= noteGenerateService.getNode(chordRoot, yinyu);
					note2+=mylist2.get(j);
					violin.add(note2);

				}
				violin.add(" | ");
			
				
				//节奏
				piano.add(chords[k]+"q");
				piano.add(chords[k]+"q");
				piano.add(chords[k]+"q");
				piano.add(chords[k]+"q");
				bass.add(chordRoot+"2q");
				bass.add(chordRoot+"2q");
				bass.add(chordRoot+"2q");
				bass.add(chordRoot+"2q");
				drum.add("C2q");
				drum.add("C1q");
				drum.add("C2q");
				drum.add("C1q");
				
				musicbox.add("G5q");
				musicbox.add("C5q");
				musicbox.add("G5q");
				musicbox.add("C5q");
	
				piano.add(" | ");
				bass.add(" | ");
				drum.add(" | ");
				musicbox.add(" | ");
			}
			
		}
		

		
		piano.setVoice(0).setInstrument(Instruments.Piano).setTempo(speed);
		guitar.setVoice(1).setInstrument(Instruments.Guitar).setTempo(speed);
		bass.setVoice(2).setInstrument(Instruments.Acoustic_Bass).setTempo(speed);
		violin.setVoice(3).setInstrument(Instruments.GUITAR_HARMONICS).setTempo(speed);
		musicbox.setVoice(4).setInstrument(Instruments.MUSIC_BOX).setTempo(speed);
		drum.setVoice(5).setInstrument(Instruments.Taiko_Drum).setTempo(speed);

		
		System.out.println(piano);
		System.out.println(guitar);
		System.out.println(bass);
		System.out.println(violin);
		System.out.println(musicbox);
		System.out.println(drum);
		
		song.add(piano);
		song.add(guitar);
		song.add(bass);
		song.add(violin);
		song.add(musicbox);
		song.add(drum);
		
		Player player1=new Player();
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		playThread.start();
		MusicSaveThread saveThread=new MusicSaveThread(song, "March");
		saveThread.start();
		
	}

}
