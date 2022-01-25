package com.sachzhong.amusichub.client;


import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.service.ArpeggioGenerateService;
import com.sachzhong.service.ChordGenerateService;
import com.sachzhong.service.MelodyRandomGenerateService;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;


/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年4月26日 上午11:06:29
*  类说明：音乐demo1
*/
public class PianoDemo2 {
	

	public static void main(String[] args) {
		
		int speed = 66;
		int yinyu = 4;
		
		//和声
		String Chord_String = "I VI IV V ";
		//String Chord_String = "VI IV V I";
		ChordGenerateService chordUtil = new ChordGenerateService();
		ChordProgression cp=chordUtil.getChordProgression(Chord_String, "G", yinyu);
		 
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
		
		//旋律生成工具
		MelodyRandomGenerateService melodyGenerateUtil = new MelodyRandomGenerateService();
		
		//琶音生成工具
		ArpeggioGenerateService arpeggioGenerateService = new ArpeggioGenerateService();

		
		//生成前奏 吉他独奏
		for (int i = 0; i < 2; i++) {
			
			for (int k = 0; k < chords.length; k++) {
				

				String chordRoot = chords[k].getRoot().toString();
				chordRoot=chordRoot.substring(0, 1);

				//为了增加音乐的多样性随机和弦bass节奏
				int randomInt=(int) Math.round(Math.random()*3);
				

				if(randomInt==0)
				{

					bass.add(chordRoot+"2w");
					drum.add("C2w");
				}
				else if(randomInt==1)
				{

					bass.add(chordRoot+"2w");
					drum.add("C2w");
				}
				else if(randomInt==2)
				{

					bass.add(chordRoot+"2w");
					drum.add("C2w");
				}
				else if(randomInt==3)
				{

					bass.add(chordRoot+"2w");
					drum.add("C2w");
					

				}
	
				piano.add(chords[k]);
				guitar.add(melodyGenerateUtil.getMelody(chords[k],yinyu+1));
				musicbox.add(arpeggioGenerateService.getArpeggio(chords[k]));
				violin.add(chordRoot+"2w");
				

				piano.add(" | ");
				bass.add(" | ");
				drum.add(" | ");
				violin.add(" | ");
				guitar.add(" | ");
				musicbox.add(" | ");
				
			}
			
		}	
		
		
		
	
		piano.setVoice(0).setInstrument(Instruments.Piano).setTempo(speed);
		guitar.setVoice(1).setInstrument(Instruments.Piano).setTempo(speed);
		bass.setVoice(2).setInstrument(Instruments.Acoustic_Bass).setTempo(speed);
		violin.setVoice(3).setInstrument(Instruments.VIOLIN).setTempo(speed);
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

		MusicSaveThread saveThread=new MusicSaveThread(song, "beiDuoFen2");
		saveThread.start();
		
	}

}
