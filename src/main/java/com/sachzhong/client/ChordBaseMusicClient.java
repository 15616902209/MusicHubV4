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
import org.jfugue.theory.Note;

import java.util.List;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年1月23日 下午12:11:05
*  类名：BaseMusicClient.java
*  类说明：基础音乐客户端类
*/
public class ChordBaseMusicClient {
	
	public static void main(String[] args) {
		
		int speed = 96;
		int yinyu = 3;
		int n=2;
		
		ChordGenerateService chordUtil = new ChordGenerateService();
		ChordProgression cp=chordUtil.getChordProgression("I VI IV V", "C", yinyu);
		 
		Chord[] chords=cp.getChords();
		
		Pattern song=new Pattern();
		Pattern guitar=new Pattern();
		
		NoteGenerateService noteGenerateService =new NoteGenerateService();
		
		MusicPaiService musicPaiService =new MusicPaiService();
		

		for (int j = 0; j < n; j++) {
			for (int k = 0; k < chords.length; k++) {
				
				List<String> mylist =  musicPaiService.getPai44By4816();
				for (int i = 0; i < mylist.size(); i++) {
					Note[] notes = chords[k].getNotes();
					int index =  (int) Math.round(Math.random() * (notes.length-1));
					String rootSong ="("+ notes[0] + "+" + notes[index]+")";
				    	rootSong+=mylist.get(i);
						guitar.add(rootSong);

				}
				guitar.add(" | ");
			}
		}

		for (int j = 0; j < n; j++) {
			List<String> mylist =  musicPaiService.getPai44By4816();
			for (int k = 0; k < chords.length; k++) {
				NoteGenerateService notes = new NoteGenerateService();
				for (int i = 0; i < mylist.size(); i++) {

					String rootSong ="("+ chords[k].getRoot().toString() + "+" + notes.getRodomNote(chords[k])+")";
					rootSong+=mylist.get(i);
					guitar.add(rootSong);

				}
				guitar.add(" | ");
			}
		}
		
		
		guitar.setVoice(0).setInstrument(Instruments.Piano).setTempo(speed);
		
		song.add(guitar);
		System.out.println(song);
		
		Player player1=new Player();
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		playThread.start();

		MusicSaveThread saveThread=new MusicSaveThread(song, "ChordBaseMusic");
		saveThread.start();
		
		
	}

}
