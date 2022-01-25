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
*  类说明：
*/
public class ChordTestClient {
	
	public static void main(String[] args) {
		
		int speed = 92;
		int yinyu = 4;
		
		//和声
		ChordGenerateService chordUtil = new ChordGenerateService();
		ChordProgression cp=chordUtil.getChordProgression("IV V III VI II V I", "C", yinyu);
		 
		Chord[] chords=cp.getChords();
		
		Pattern song=new Pattern();
		Pattern piano=new Pattern();
		Pattern guitar=new Pattern();
		Pattern violin=new Pattern();
		Pattern bass=new Pattern();
		Pattern musicbox=new Pattern();
		
		NoteGenerateService noteGenerateService =new NoteGenerateService();
		
		MusicPaiService musicPaiService =new MusicPaiService();
		
		for (int i = 0; i < chords.length; i++) {
			
			for (int k = 0; k < chords.length; k++) {
				

				String chordRoot = chords[k].getRoot().toString();
				chordRoot=chordRoot.substring(0, 1);
				
				//List<String> mylist =  musicPaiUtil.getPai();
				List<String> mylist =  musicPaiService.getPai44();
				for (int j = 0; j < mylist.size(); j++) {

						String note= noteGenerateService.getNode(chordRoot, yinyu);
						note+=mylist.get(j);
						guitar.add(note);
						
						//复调
						String note2= noteGenerateService.getNode(chordRoot, yinyu+1);
						note2+=mylist.get(j);
						musicbox.add(note2);
						
						
				}
				guitar.add(" | ");
				musicbox.add(" | ");
				
				List<String> mylist2 =  musicPaiService.getPai();
				for (int j = 0; j < mylist2.size(); j++) {

					//复调
					String note2= noteGenerateService.getNode(chordRoot, yinyu-1);
					note2+=mylist2.get(j);
					violin.add(note2);

				}
				violin.add(" | ");
			
				
				//节奏
				int randomInt=(int) Math.round(Math.random()*2);

				
				if(randomInt==0)
				{
					piano.add(chords[k]+"w");
					
					bass.add(chordRoot+"2w");
				}
				else if(randomInt==1)
				{
					piano.add(chords[k]+"h");
					piano.add(chords[k]+"h");
					bass.add(chordRoot+"2h");
					bass.add(chordRoot+"2h");
				}
				else if(randomInt==2)
				{

					piano.add(chords[k]+"h");
					piano.add("Rq");
					piano.add(chords[k]+"q");
					bass.add(chordRoot+"2h");
					bass.add("Rq");
					bass.add(chordRoot+"2q");
				}
	
				piano.add(" | ");
				bass.add(" | ");
			}
			
		}
		

		piano.setVoice(0).setInstrument(Instruments.Piano).setTempo(speed);
		guitar.setVoice(1).setInstrument(Instruments.Guitar).setTempo(speed);
		bass.setVoice(2).setInstrument(Instruments.Acoustic_Bass).setTempo(speed);
		violin.setVoice(3).setInstrument(Instruments.VIOLIN).setTempo(speed);
		musicbox.setVoice(4).setInstrument(Instruments.MUSIC_BOX).setTempo(speed);
		
		System.out.println(piano);
		System.out.println(guitar);
		System.out.println(bass);
		System.out.println(violin);
		System.out.println(musicbox);
		
		song.add(piano);
		song.add(guitar);
		song.add(bass);
		song.add(violin);
		song.add(musicbox);
		
		Player player1=new Player();
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		playThread.start();

		MusicSaveThread saveThread=new MusicSaveThread(song, "Chord");
		saveThread.start();
		
	}

}
