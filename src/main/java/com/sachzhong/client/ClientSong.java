package com.sachzhong.client;

import com.sachzhong.song.GenerateSong;

/**
*  作者： SachZhong 钟盛勤
*  项目名：qingshengmusic
*  版本： 1.0
*  创建时间：2020年1月6日 下午8:41:39
*  类名：Client.java
*  类说明：
*/
public class ClientSong {
	
	public static void main(String[] args) {
		
		
		
		GenerateSong musicSong =new GenerateSong(92);
		
		//{"前奏","主题","副歌前","副歌","重复前奏","副歌前","副歌","间奏","副歌","结尾"};
		
		//设置前奏
		String gechi="你是什么鬼 挑梁小鬼 这是什么鬼 开心鬼";
		musicSong.generateIntro(gechi, 4);
		
		//设置主题
		gechi="酆都开城 百鬼夜行 有一书生 左拿神钩 右持判官笔 披荆斩棘 度化鬼魅";
		musicSong.generateVerse(gechi, 4);
		
		//设置副歌前
		gechi="吊死鬼 淹死鬼 红衣鬼 火焰鬼 开心鬼 天师降临 诸邪退散";
		musicSong.generatePreChorus(gechi, 5);
		
		//设置副歌
		gechi="加如有一天 钟馗爱上了女鬼 他会怎么做 牡丹花下死 做鬼也风流 哈哈哈";
		musicSong.generateChorus(gechi, 5);
		
		//设置间奏
		gechi="你是什么鬼 挑梁小鬼 这是什么鬼 开心鬼";
		musicSong.generateBridge(gechi, 5);
		
		//设置结尾
		gechi="牡丹花下死 做鬼也风流 哈哈哈";
		musicSong.generateOutro(gechi, 4);
		
		musicSong.generateSong();
		musicSong.PlaySong();
		musicSong.ShowGechi();
		
		musicSong.SaveSong();
		
		
	}
	
	       

}
