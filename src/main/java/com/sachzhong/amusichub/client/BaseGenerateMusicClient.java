package com.sachzhong.amusichub.client;

import com.sachzhong.instruments.Instruments;

import java.util.ArrayList;
import java.util.List;

/**
*  作者： SachZhong 钟盛勤
*  项目名：qingshengmusic
*  版本： 1.0
*  创建时间：2020年1月6日 下午8:41:39
*  类名：Client.java
*  类说明：
*/
public class BaseGenerateMusicClient {
	
	public static void main(String[] args) {
		
		//乐器轨道
		List<String> instrumentList = new ArrayList<String>();
		
		//加入乐器
//		instrumentList.add(Instruments.Acoustic_Bass);
//		instrumentList.add(Instruments.Guitar);
		
//		instrumentList.add(Instruments.ELECTRIC_PIANO);
		instrumentList.add(Instruments.Piano);
//		instrumentList.add(Instruments.Guitar);

//		instrumentList.add(Instruments.VIOLIN);
//		instrumentList.add(Instruments.ALTO_SAX);
		

		
		String chordpath ="I VI IV V";
		String jidiao = "C";
		int yinyu=3;
		int speed = 120;
		
		//构建生成音乐的类
		BaseGenerateMusic musicSong =new BaseGenerateMusic(instrumentList,speed);
		
		
				
		//{"前奏","主题","副歌前","副歌","重复前奏","副歌前","副歌","间奏","副歌","结尾"};
		//设置前奏
		musicSong.generateIntro(jidiao,chordpath,yinyu,speed);
		
		jidiao = "C";
		chordpath ="I V VI V";
		//设置主题
		musicSong.generateVerse(jidiao,chordpath,yinyu,speed);
		
		jidiao = "C";
		chordpath ="V VI I V";
		//设置副歌前
		musicSong.generatePreChorus(jidiao,chordpath,yinyu,speed);
		
		jidiao = "C";
		chordpath ="IV VIII III VI II V I";
		//设置副歌
		musicSong.generateChorus(jidiao,chordpath,yinyu,speed);
		
		jidiao = "C";
		chordpath ="I VI IV V";
		//设置间奏
		musicSong.generateBridge(jidiao,chordpath,yinyu,speed);
		
		jidiao = "C";
		chordpath ="V I";
		//设置结尾
		musicSong.generateOutro(jidiao,chordpath,yinyu,speed);
		
		//播放音乐
		musicSong.PlaySong();
		//存储音乐
		musicSong.SaveSong();
		
		
	}
	
	       

}
