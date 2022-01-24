package com.sachzhong.client;


import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.util.MelodyGenerateUtil;
import com.sachzhong.util.NoteGenerateUtil;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;


/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHub
*  版本： 1.0
*  创建时间：2020年1月12日 下午9:04:55
*  类名：Client2.java
*  类说明：
*/
public class Clientlichade {
	
public static void main(String[] args) {
		
		Pattern song=new Pattern();
		
		
		//旋律
		Pattern song1=new Pattern();
		//贝斯
		Pattern song2=new Pattern();
		//吉他
		Pattern song3=new Pattern();
		//鼓点
		Pattern song4=new Pattern();
		//鼓点节奏
		Rhythm rhythm =new Rhythm();
		String[] rhythmArr=new String[] {"O","`","S","X"};
		String rhythmStr="";
		
		//构建和弦走向
		//436251
		//String[] chordpath=new String[]{"F","G","E","A","D","G","C"};
		//平行10度
		//String[] chordpath=new String[]{"B","A","G","F","E","D","C"};
		//6415
		String[] chordpath=new String[]{"A","F","G","C"};
		
		NoteGenerateUtil noteGenerateUtil=new NoteGenerateUtil();
		MelodyGenerateUtil melodyGenerateUtil=new MelodyGenerateUtil();
		int yinyu=5;
		
		//循环10次和弦走向
		for (int k = 0; k < 10; k++) {
			
			//每一个和弦生成一小节
			for (int i = 0; i < chordpath.length; i++) {
			
				//增加一个和弦音 一般用于bass 低音
				song2.add(chordpath[i]+(yinyu-2)+"w");
	
				String node="C";

				//根据和弦根音随机获取理查德式音符
				Pattern pattern=melodyGenerateUtil.getMelodyBylichade(chordpath[i], (yinyu-1));
				song1.add(pattern);
				
				
					for (int j = 0; j < 8; j++) {
						
						//吉他音轨随机获取音符
						//根据和弦根音随机获取音符
						node=noteGenerateUtil.getNode(chordpath[i], yinyu);
						node+="i";
						song3.add(node);
						
						//随机生成 鼓点
						int index = (int)(Math.random()*rhythmArr.length);
						//随机加入鼓点
						rhythmStr+=rhythmArr[index];
						
					}
					
					
					

				song1.add("|");
				song2.add("|");
				song3.add("|");
				rhythmStr+=".";
			}
			
		}
		
		//速度	
		int speed=120;
		
		song1.setVoice(0).setInstrument("Piano").setTempo(speed);
		song2.setVoice(1).setInstrument("ACOUSTIC_BASS").setTempo(speed);
		song3.setVoice(2).setInstrument(Instruments.Guitar).setTempo(speed);
		
		//加入鼓点
		rhythm.addLayer(rhythmStr);
		song4.add(rhythm.getPattern()).setTempo(speed);

		System.out.println(song1.toString());
		System.out.println(song2.toString());
		System.out.println(song3.toString());
		System.out.println(song4.toString());
		song.add(song1);
		song.add(song2);
		song.add(song3);
		song.add(song4);
		
		Player player1=new Player();
		MusicPlayThread musicThread=new MusicPlayThread(player1,song);
		musicThread.start();
		
		
		
		//保存地址
		String saveurl="D://lichadesongMusic.mid";
		//保存
		MusicSaveThread musicUtil=new MusicSaveThread(song, saveurl);
		musicUtil.start();
	}

}
