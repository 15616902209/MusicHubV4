package com.sachzhong.client;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.util.ChordUtil;
import com.sachzhong.util.MelodyGenerateUtil;
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
*  类说明：爵士音乐
*/
public class SongClient2 {
	
	public static void main(String[] args) {
		
		int speed = 66;
		int yinyu = 4;
		
		//和声
		ChordUtil chordUtil =new ChordUtil();
		//ChordProgression cp=chordUtil.getChordProgression("I IV V I", "Amin", yinyu);
		ChordProgression cp=chordUtil.getChordProgression("I IV V I", "Amin", yinyu);
		 
		Chord[] chords=cp.getChords();
		
		//歌曲合集轨道
		Pattern song=new Pattern();
		//钢琴和弦
		Pattern piano=new Pattern();
		//钢琴旋律
		Pattern violin=new Pattern();
		//吉他旋律
		Pattern guitar=new Pattern();
		//贝斯
		Pattern bass=new Pattern();
		//音乐盒
		Pattern musicbox=new Pattern();
		//鼓
		Pattern drum=new Pattern();
		

		
		NoteGenerateUtil noteGenerateUtil=new NoteGenerateUtil();
		
		MelodyGenerateUtil melodyGenerateUtil = new MelodyGenerateUtil();
		
		MusicPaiUtil musicPaiUtil =new MusicPaiUtil();
		
		for (int i = 0; i < 8; i++) {
			
			for (int k = 0; k < chords.length; k++) {
				

				String chordRoot = chords[k].getRoot().toString();
				chordRoot=chordRoot.substring(0, 1);
				
				//List<String> mylist =  musicPaiUtil.getPai();
				List<String> mylist =  musicPaiUtil.getPai44();
				for (int j = 0; j < mylist.size(); j++) {

						String note=noteGenerateUtil.getNote(chords[k]);
						note+=mylist.get(j);
						guitar.add(note);
						
						//复调
						String note2=noteGenerateUtil.getNode(chordRoot, yinyu+1);
						note2+=mylist.get(j);
						musicbox.add(note2);
						
						
				}
				guitar.add(" | ");
				musicbox.add(" | ");

				//为了增加音乐的多样性，采用随机数
				int randomInt=(int) Math.round(Math.random()*5);
				

				if(randomInt==0)
				{
					piano.add(chords[k]+"h");
					piano.add(chords[k]+"h");
					bass.add(chordRoot+"2h");
					bass.add(chordRoot+"2h");
					drum.add("C3h");
					drum.add("C3h");
					
					List<String> mylist2 =  musicPaiUtil.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2=noteGenerateUtil.getNode(chordRoot, yinyu-1);
						note2+=mylist2.get(j);
						violin.add(note2);

					}


				}
				else if(randomInt==1)
				{
					piano.add(chords[k]+"w");
					bass.add(chordRoot+"2w");
					drum.add("C3w");
					
					List<String> mylist2 =  musicPaiUtil.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2=noteGenerateUtil.getNode(chordRoot, yinyu+1);
						note2+=mylist2.get(j);
						violin.add(note2);

					}


				}
				else if(randomInt==2)
				{
					piano.add(chords[k]+"q");
					piano.add(chords[k]+"q");
					piano.add(chords[k]+"h");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2h");
					drum.add("C3q");
					drum.add("C3q");
					drum.add("C3h");
					
					violin.add(melodyGenerateUtil.getMelodyBylichade(chordRoot, yinyu+1));


				}
				else if(randomInt==3)
				{
					piano.add(chords[k]+"q");
					piano.add(chords[k]+"q");
					piano.add(chords[k]+"q");
					piano.add(chords[k]+"q");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					drum.add("C3q");
					drum.add("C3q");
					drum.add("C3q");
					drum.add("C3q");
					
					violin.add(melodyGenerateUtil.getMelodyBylichade(chordRoot, yinyu));


				}
				else if(randomInt==4)
				{
					piano.add(chords[k]+"h");
					piano.add(chords[k]+"h");
					bass.add(chordRoot+"2h");
					bass.add(chordRoot+"2h");
					drum.add("C3h");
					drum.add("C3h");

					List<String> mylist2 =  musicPaiUtil.getPaiAll8List();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2=noteGenerateUtil.getNote(chords[k]);
						note2+=mylist2.get(j);
						violin.add(note2);

					}


				}
				else if(randomInt==5)
				{
					piano.add(chords[k]+"w");
					bass.add(chordRoot+"2w");
					drum.add("C3w");

					List<String> mylist2 =  musicPaiUtil.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2=noteGenerateUtil.getNote(chords[k]);
						note2+=mylist2.get(j);
						violin.add(note2);

					}


				}
				piano.add(" | ");
				bass.add(" | ");
				drum.add(" | ");
				violin.add(" | ");
				
			}
			
		}
		

		
		piano.setVoice(0).setInstrument(Instruments.ELECTRIC_PIANO).setTempo(speed);
		guitar.setVoice(1).setInstrument(Instruments.ELECTRIC_JAZZ_GUITAR).setTempo(speed);
		bass.setVoice(2).setInstrument(Instruments.Acoustic_Bass).setTempo(speed);
		violin.setVoice(3).setInstrument(Instruments.Piano).setTempo(speed);
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
		//song.add(bass);
		//song.add(violin);
		song.add(musicbox);
		//song.add(drum);

		Player player1=new Player();
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		playThread.start();

		MusicSaveThread saveThread=new MusicSaveThread(song, "SongClient");
		saveThread.start();


	}

}
