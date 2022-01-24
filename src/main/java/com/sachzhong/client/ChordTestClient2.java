package com.sachzhong.client;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.util.ChordUtil;
import com.sachzhong.util.MusicPaiUtil;
import com.sachzhong.util.NoteGenerateUtil;
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
*  类说明：
*/
public class ChordTestClient2 {
	
	public static void main(String[] args) {
		
		int speed = 92;
		int yinyu = 4;
		
		//和声
		ChordUtil chordUtil =new ChordUtil();
		ChordProgression cp=chordUtil.getChordProgression("VI IV I V", "Amin", yinyu);

		Chord[] chords=cp.getChords();

		Pattern song=new Pattern();
		NoteGenerateUtil noteGenerateUtil =new NoteGenerateUtil();

		//旋律音轨
		Pattern melodyPattern=new Pattern();
		//和弦音轨
		Pattern chordPattern=new Pattern();

		MusicPaiUtil musicPai = new MusicPaiUtil();
		for (int k =0;k<10;k++){
			for (int i = 0; i < chords.length; i++) {

				Chord  chord=chords[i];
				List<String> jiezou=musicPai.getPai();
				for (int j = 0; j < jiezou.size(); j++) {
					String	node= noteGenerateUtil.getNote(chord);
					node+=jiezou.get(j);
					melodyPattern.add(node);
				}
				chordPattern.add(chord+"w|");
				melodyPattern.add("|");

			}
		}


		melodyPattern.setVoice(0).setInstrument(Instruments.Guitar).setTempo(speed);
		chordPattern.setVoice(1).setInstrument(Instruments.Piano).setTempo(speed);

		System.out.println(melodyPattern);
		System.out.println(chordPattern);

		song.add(melodyPattern);
		song.add(chordPattern);


		Player player1=new Player();
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		playThread.start();

		MusicSaveThread saveThread=new MusicSaveThread(song, "ChordTest2");
		saveThread.start();

	}

}
