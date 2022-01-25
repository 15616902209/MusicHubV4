package com.sachzhong.client;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.service.ChordGenerateService;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.service.MelodyGenerateService;
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
*  类说明：贝多芬音乐
*/
public class BeiduofenClient {
	

	public static void main(String[] args) {
		
		int speed = 92;
		int yinyu = 4;
		
		//和声
		//String Chord_String = "I VI IV V ";
		//String Chord_String = "VI IV V I";
		String Chord_String = "VI IV I V";
		ChordGenerateService chordUtil = new ChordGenerateService();
		ChordProgression cp=chordUtil.getChordProgression(Chord_String, "C", yinyu);
		 
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
		

		//音符生成工具
		NoteGenerateService noteGenerateService =new NoteGenerateService();
		//旋律生成工具
		MelodyGenerateService melodyGenerateService = new MelodyGenerateService();
		//音乐节奏生成工具
		MusicPaiService musicPaiService =new MusicPaiService();
		
		
		
		//生成前奏 吉他独奏
		for (int i = 0; i < 2; i++) {
			
			for (int k = 0; k < chords.length; k++) {
				

				String chordRoot = chords[k].getRoot().toString();
				chordRoot=chordRoot.substring(0, 1);

			

				//为了增加音乐的多样性，采用随机数
				int randomInt=(int) Math.round(Math.random()*3);
				

				if(randomInt==0)
				{

					bass.add(chordRoot+"2h");
					bass.add(chordRoot+"2h");
					drum.add("C2h");
					drum.add("C2h");
					
					List<String> mylist2 =  musicPaiService.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2= noteGenerateService.getNode(chordRoot, yinyu);
						note2+=mylist2.get(j);
						guitar.add(note2);

					}


				}
				else if(randomInt==1)
				{

					bass.add(chordRoot+"2w");
					drum.add("C2w");
					
					guitar.add(melodyGenerateService.generateMelodyAll8(chordRoot, yinyu+1));


				}
				else if(randomInt==2)
				{

					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2h");
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2h");
					
					List<String> mylist2 =  musicPaiService.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2= noteGenerateService.getNode(chordRoot, yinyu+1);
						note2+=mylist2.get(j);
						guitar.add(note2);

					}


				}
				else if(randomInt==3)
				{

					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2q");
					
					guitar.add(melodyGenerateService.getMelodyBylichade(chordRoot, yinyu));


				}
	
				piano.add("Rw");
				musicbox.add("Rw");
				violin.add("Rw");
				
				piano.add(" | ");
				bass.add(" | ");
				drum.add(" | ");
				violin.add(" | ");
				guitar.add(" | ");
				musicbox.add(" | ");
				
			}
			
		}	
		
		
		
		//生成2小节音乐  主题
		for (int i = 0; i < 2; i++) {
			
			for (int k = 0; k < chords.length; k++) {
				

				String chordRoot = chords[k].getRoot().toString();
				chordRoot=chordRoot.substring(0, 1);

			

				//为了增加音乐的多样性，采用随机数
				int randomInt=(int) Math.round(Math.random()*3);
				

				if(randomInt==0)
				{
					piano.add(chords[k]+"h");
					piano.add(chords[k]+"h");
					bass.add(chordRoot+"2h");
					bass.add(chordRoot+"2h");
					drum.add("C2h");
					drum.add("C2h");
					
					List<String> mylist2 =  musicPaiService.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2= noteGenerateService.getNode(chordRoot, yinyu-1);
						note2+=mylist2.get(j);
						violin.add(note2);

					}


				}
				else if(randomInt==1)
				{
					piano.add(chords[k]+"w");
					bass.add(chordRoot+"2w");
					drum.add("C2w");
					
					List<String> mylist2 =  musicPaiService.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2= noteGenerateService.getNode(chordRoot, yinyu+1);
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
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2h");
					
					List<String> mylist2 =  musicPaiService.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2= noteGenerateService.getNode(chordRoot, yinyu);
						note2+=mylist2.get(j);
						violin.add(note2);

					}


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
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2q");
					
					violin.add(melodyGenerateService.getMelodyBylichade(chordRoot, yinyu));


				}
	
				guitar.add("Rw");
				musicbox.add("Rw");
				
				piano.add(" | ");
				bass.add(" | ");
				drum.add(" | ");
				violin.add(" | ");
				guitar.add(" | ");
				musicbox.add(" | ");
				
			}
			
		}		
		
		//换和弦
		Chord_String = "IV V III VI II V I";
		cp=chordUtil.getChordProgression(Chord_String, "Amin", yinyu);
		chords=cp.getChords();
		
		
		//生成8小节音乐  高潮
		for (int i = 0; i < 4; i++) {
			
			for (int k = 0; k < chords.length; k++) {
				

				String chordRoot = chords[k].getRoot().toString();
				chordRoot=chordRoot.substring(0, 1);
				
				//List<String> mylist =  musicPaiUtil.getPai();
				List<String> mylist =  musicPaiService.getPai44();
				for (int j = 0; j < mylist.size(); j++) {

						//复调
						String note2= noteGenerateService.getNode(chordRoot, yinyu+1);
						note2+=mylist.get(j);
						musicbox.add(note2);
						
						
				}

				musicbox.add(" | ");

				//为了增加音乐的多样性，采用随机数
				int randomInt=(int) Math.round(Math.random()*3);
				

				if(randomInt==0)
				{
					piano.add(chords[k]+"h");
					piano.add(chords[k]+"h");
					bass.add(chordRoot+"2h");
					bass.add(chordRoot+"2h");
					drum.add("C3h");
					drum.add("C3h");
					
					//violin.add(melodyGenerateUtil.getMelodyBylichade(chordRoot, yinyu-1));
					violin.add(melodyGenerateService.generateMelody(chordRoot, yinyu));
					guitar.add(melodyGenerateService.generateMelody(chordRoot, yinyu));
				}
				else if(randomInt==1)
				{
					piano.add(chords[k]+"w");
					bass.add(chordRoot+"2w");
					drum.add("C3w");
					
					List<String> mylist2 =  musicPaiService.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2= noteGenerateService.getNode(chordRoot, yinyu+1);
						note2+=mylist2.get(j);
						violin.add(note2);

					}
					guitar.add(melodyGenerateService.generateMelodyAll8(chordRoot, yinyu));

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
					
					violin.add(melodyGenerateService.getMelodyBylichade(chords[k], yinyu+1));
					guitar.add(melodyGenerateService.getMelodyBylichade(chords[k], yinyu+1));

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
					
					violin.add(melodyGenerateService.getMelodyBylichade(chords[k], yinyu));
					guitar.add(melodyGenerateService.getMelodyBylichade(chords[k], yinyu));

				}
	
				guitar.add(" | ");
				piano.add(" | ");
				bass.add(" | ");
				drum.add(" | ");
				violin.add(" | ");
				
			}
			
		}
		
		//换和弦
		Chord_String = "I VI IV V";
		cp=chordUtil.getChordProgression(Chord_String, "C", yinyu);
		chords=cp.getChords();		

		//生成1小节音乐  主题
		for (int i = 0; i < 1; i++) {
			
			for (int k = 0; k < chords.length; k++) {
				

				String chordRoot = chords[k].getRoot().toString();
				chordRoot=chordRoot.substring(0, 1);

			

				//为了增加音乐的多样性，采用随机数
				int randomInt=(int) Math.round(Math.random()*3);
				

				if(randomInt==0)
				{
					piano.add(chords[k]+"h");
					piano.add(chords[k]+"h");
					bass.add(chordRoot+"2h");
					bass.add(chordRoot+"2h");
					drum.add("C2h");
					drum.add("C2h");
					
					List<String> mylist2 =  musicPaiService.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2= noteGenerateService.getNode(chordRoot, yinyu+1);
						note2+=mylist2.get(j);
						violin.add(note2);

					}


				}
				else if(randomInt==1)
				{
					piano.add(chords[k]+"w");
					bass.add(chordRoot+"2w");
					drum.add("C2w");
					
					violin.add(melodyGenerateService.getMelodyBylichade(chords[k], yinyu));


				}
				else if(randomInt==2)
				{
					piano.add(chords[k]+"q");
					piano.add(chords[k]+"q");
					piano.add(chords[k]+"h");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2h");
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2h");
					
					violin.add(melodyGenerateService.getMelodyBylichade(chordRoot, yinyu+1));


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
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2q");
					
					violin.add(melodyGenerateService.getMelodyBylichade(chordRoot, yinyu));


				}
	
				guitar.add("Rw");
				musicbox.add("Rw");
				
				piano.add(" | ");
				bass.add(" | ");
				drum.add(" | ");
				violin.add(" | ");
				guitar.add(" | ");
				musicbox.add(" | ");
				
			}
			
		}						
		
		//生成2小节结尾
		for (int i = 0; i < 2; i++) {
			
			for (int k = 0; k < chords.length; k++) {
				

				String chordRoot = chords[k].getRoot().toString();
				chordRoot=chordRoot.substring(0, 1);

			

				//为了增加音乐的多样性，采用随机数
				int randomInt=(int) Math.round(Math.random()*3);
				

				if(randomInt==0)
				{

					bass.add(chordRoot+"2h");
					bass.add(chordRoot+"2h");
					drum.add("C2h");
					drum.add("C2h");
					
					List<String> mylist2 =  musicPaiService.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2= noteGenerateService.getNode(chordRoot, yinyu-1);
						note2+=mylist2.get(j);
						guitar.add(note2);

					}


				}
				else if(randomInt==1)
				{

					bass.add(chordRoot+"2w");
					drum.add("C2w");
					
					guitar.add(melodyGenerateService.getMelodyBylichade(chords[k], yinyu));


				}
				else if(randomInt==2)
				{

					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2h");
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2h");
					
					List<String> mylist2 =  musicPaiService.getPai();
					for (int j = 0; j < mylist2.size(); j++) {

						//复调
						String note2= noteGenerateService.getNode(chordRoot, yinyu);
						note2+=mylist2.get(j);
						guitar.add(note2);

					}


				}
				else if(randomInt==3)
				{

					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					bass.add(chordRoot+"2q");
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2q");
					drum.add("C2q");
					
					guitar.add(melodyGenerateService.getMelodyBylichade(chordRoot, yinyu));


				}
	
				piano.add("Rw");
				musicbox.add("Rw");
				violin.add("Rw");
				
				piano.add(" | ");
				bass.add(" | ");
				drum.add(" | ");
				violin.add(" | ");
				guitar.add(" | ");
				musicbox.add(" | ");
				
			}
			
		}	
		
		piano.setVoice(0).setInstrument(Instruments.ELECTRIC_PIANO).setTempo(speed);
		guitar.setVoice(1).setInstrument(Instruments.Guitar).setTempo(speed);
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
		song.add(bass);
		song.add(violin);
		song.add(musicbox);
		song.add(drum);
		
		Player player1=new Player();
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		playThread.start();
		MusicSaveThread saveThread=new MusicSaveThread(song, "beiduofen");
		saveThread.start();
		
	}

}
