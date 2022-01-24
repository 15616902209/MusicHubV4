package com.sachzhong.client;

import com.sachzhong.song.GenerateChorusSong;

/**
*  作者： SachZhong 钟盛勤
*  项目名：qingshengmusic
*  版本： 1.0
*  创建时间：2020年1月6日 下午8:41:39
*  类名：Client.java
*  类说明：
*/
public class ClientAutoSong {
	
	public static void main(String[] args) {
		
		
		
		GenerateChorusSong musicSong =new GenerateChorusSong(80);
		
		//{"前奏","主题","副歌前","副歌","重复前奏","副歌前","副歌","间奏","副歌","结尾"};
		
		//设置前奏的和弦走向为1645，每一个和弦生成一小节
		String[] chordPath=new String[] {"A","D","E","G"};
		musicSong.generateIntro(chordPath, 4, 1);
		
		//设置主题的和弦走向为3515，每一个和弦生成一小节
		chordPath=new String[] {"A","G","E","G"};
		musicSong.generateVerse(chordPath, 4, 1);
		
		//设置副歌前的和弦走向为1564，每一个和弦生成一小节
		chordPath=new String[] {"C","E","D","G"};
		musicSong.generatePreChorus(chordPath, 5, 1);
		
		//设置副歌的和弦走向为4536251，每一个和弦生成2小节
		chordPath=new String[] {"C","E","D","G"};
		musicSong.generateChorus(chordPath, 5, 2);
		
		//设置间奏的和弦走向为3,5,1,每一个和弦生成一小节
		chordPath=new String[] {"A","D","F","G"};
		musicSong.generateBridge(chordPath, 4, 1);
		
		//设置结尾的和弦走向为1,5,1，每一个和弦生成一小节
		chordPath=new String[] {"A","G","A"};
		musicSong.generateOutro(chordPath, 4, 1);
		
		musicSong.generateSong();
		musicSong.PlaySong();
		
		musicSong.SaveSong();
		
	}
	
	       

}
