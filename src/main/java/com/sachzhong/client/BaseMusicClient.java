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
*  创建时间：2020年1月23日 下午12:11:05
*  类名：BaseMusicClient.java
*  类说明：基础音乐客户端类
*/
public class BaseMusicClient {
	
	public static void main(String[] args) {
		
		int speed = 92;
		int yinyu = 4;
		int n=10;
		
		ChordUtil chordUtil =new ChordUtil();
		ChordProgression cp=chordUtil.getChordProgression("IV V III VI II V I", "A", yinyu);
		 
		Chord[] chords=cp.getChords();
		
		Pattern song=new Pattern();
		Pattern guitar=new Pattern();
		
		NoteGenerateUtil noteGenerateUtil=new NoteGenerateUtil();
		
		MusicPaiUtil musicPaiUtil =new MusicPaiUtil();
		

		for (int j = 0; j < n; j++) {
			
			for (int k = 0; k < chords.length; k++) {
				
				//List<String> mylist =  musicPaiUtil.getPai();
				List<String> mylist =  musicPaiUtil.getPai44By16();
				for (int i = 0; i < mylist.size(); i++) {
						String chordRoot = chords[k].getRoot().toString();
						chordRoot=chordRoot.substring(0, 1);
						String note=noteGenerateUtil.getNode(chordRoot, yinyu);
						note+=mylist.get(i);
						guitar.add(note);

				}
				guitar.add(" | ");
			}
		}
		
		
		guitar.setVoice(0).setInstrument(Instruments.Guitar).setTempo(speed);
		
		song.add(guitar);
		System.out.println(song);
		
		Player player1=new Player();
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		playThread.start();

		MusicSaveThread saveThread=new MusicSaveThread(song, "guitar");
		saveThread.start();
		
		
	}

}
