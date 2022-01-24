package com.sachzhong.util;

import org.jfugue.pattern.Pattern;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年2月10日 下午2:52:31
*  类名：BassGenerateUtil.java
*  类说明：
*/
public class BassGenerateUtil {
	
	//贝斯音轨
	private  Pattern bassPattern;
	
	
	public Pattern getBass(String[] chordRoot,int yinyu)
	{
		bassPattern=new Pattern();
		
		for (int i = 0; i < chordRoot.length; i++) {
			
			//增加一个和弦音 一般用于bass 低音
			bassPattern.add(chordRoot[i]+yinyu+"w");
		}

		return bassPattern;
	}

}
