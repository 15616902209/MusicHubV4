package com.sachzhong.amusichub.client;


import com.sachzhong.instruments.Instruments;
import com.sachzhong.service.MusicPaiService;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.service.ArpeggioGenerateService;
import com.sachzhong.service.ChordGenerateService;
import com.sachzhong.service.MelodyRandomGenerateService;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

import java.util.List;


/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHub
*  版本： 1.0
*  类说明：PianoDemo
*/
public class PianoDemo {
	

	public static void main(String[] args) {
		
		//速度
		int speed = 67;
		//音域
		int yinyu = 3;
		//和声走向  1-6-4-5
		String Chord_String = "VI IV V";
		//初始化和弦生成工具
		ChordGenerateService chordUtil = new ChordGenerateService();
		//根据和弦走向和基调生成和弦
		ChordProgression cp=chordUtil.getChordProgression(Chord_String, "Amin", yinyu);
		 //获得和弦数组
		Chord[] chords=cp.getChords();
		
		//初始化歌曲合集轨道
		Pattern song=new Pattern();
		//初始化钢琴和弦轨道
		Pattern pianoChord=new Pattern();
		//初始化钢琴旋律轨道
		Pattern pianoMelody=new Pattern();

		
		//旋律生成工具
		MelodyRandomGenerateService melodyGenerateUtil = new MelodyRandomGenerateService();
		
		//琶音生成工具
		ArpeggioGenerateService arpeggioGenerateService = new ArpeggioGenerateService();

		MusicPaiService musicPaiService =new MusicPaiService();

		//循环10遍
		for (int i = 0; i < 20; i++) {
			
			//遍历和弦走向
			for (int k = 0; k < chords.length; k++) {
				

				// 随机种子
				int randomInt = (int) Math.round(Math.random() * 3);

				//随机生成旋律
//				if (randomInt == 0) {
//					pianoChord.add(chords[k]+"w ");
//				} else if (randomInt == 1) {
//					pianoChord.add(chords[k]+"h "+chords[k]+"h ");
//
//				} else if (randomInt == 2) {
//					pianoChord.add(chords[k]+"q "+chords[k]+"q "+chords[k]+"q "+chords[k]+"q ");
//
//				} else if (randomInt == 3) {
//					pianoChord.add(chords[k]+"q Rq "+chords[k]+"q Rq ");
//
//				}

				List<String> mylist =  musicPaiService.getPai44();
				for (int j = 0; j < mylist.size(); j++) {
					Note[] notes = chords[k].getNotes();
					int index =  (int) Math.round(Math.random() * (notes.length-1));
					String rootSong ="("+ notes[0] + "+" + notes[index]+")";
					rootSong+=mylist.get(j);
					pianoChord.add(rootSong);
				}


				
				// 随机种子
				randomInt = (int) Math.round(Math.random() * 3);
				//随机生成旋律
				if (randomInt == 0) {
					pianoMelody.add(melodyGenerateUtil.getMelody(chords[k]));

				} else if (randomInt == 1) {
					pianoMelody.add(melodyGenerateUtil.getMelody(chords[k], yinyu+2));

				} else if (randomInt == 2) {
					pianoMelody.add(arpeggioGenerateService.getArpeggio(chords[k]));

				} else if (randomInt == 3) {
					pianoMelody.add(arpeggioGenerateService.getArpeggio(chords[k]));

				}
				
				pianoChord.add(" | ");
				pianoMelody.add(" | ");

			}
			
		}	
		
		
		//设置和弦钢琴轨道
		pianoChord.setVoice(0).setInstrument(Instruments.ELECTRIC_PIANO).setTempo(speed);
		//设置旋律钢琴轨道
		pianoMelody.setVoice(1).setInstrument(Instruments.Piano).setTempo(speed);
	
		//打印信息
		System.out.println(pianoChord);
		System.out.println(pianoMelody);

		//把旋律添加到总轨道中去
		song.add(pianoChord);
		song.add(pianoMelody);

		//初始化播放者
		Player player1=new Player();
		//开启播放线程
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		//开始播放
		playThread.start();

		//开启音乐存储线程
		MusicSaveThread saveThread=new MusicSaveThread(song, "pianodemo1");
		//保存音乐
		saveThread.start();
		
	}

}
