package com.sachzhong.amusichub;

import org.jfugue.pattern.Pattern;

import java.util.List;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年5月3日 下午6:45:24
*  类名：GenerateMusic.java
*  类说明：音乐生成接口
*/
public interface AGenerateMusic {
	
	//生成音乐接口
	//输入基调 和弦走向 起始音域 和速度  返回结果合集
	public List<Pattern> generateMusic(String jidiao,String chordpath,int yinyu,int speed);
	
	//输入基调 和弦走向 起始音域 和速度 和轨道数  返回结果合集
	public List<Pattern> generateMusic(String jidiao,String chordpath,int yinyu,int speed,int size);

}
