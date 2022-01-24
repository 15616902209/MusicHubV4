package com.sachzhong.util;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年2月10日 下午2:51:56
*  类名：RhythmGenerateUtil.java
*  类说明：鼓点节奏生成类
*/
public class RhythmGenerateUtil {
	
	//鼓点音轨
	private  Pattern rhythmPattern;
	//鼓点节奏
	private  Rhythm rhythm=new Rhythm();
	//鼓点
	private String[] rhythmArr=new String[] {"O","`","S","X"};
	//存放鼓点的字符串
	private String rhythmStr="";
	
	public Pattern getRhythm()
	{
		rhythmPattern=new Pattern();
		
		for (int i = 0; i < 32; i++) {
			
			//随机生成 鼓点
			int index = (int)(Math.random()*rhythmArr.length);
			//随机加入鼓点
			rhythmStr+=rhythmArr[index];
			//rhythmStr+=" . ";
		}
		
		//加入鼓点
		rhythm.addLayer(rhythmStr);
		rhythmPattern.add(rhythm.getPattern());
		
		return rhythmPattern;
	}

	public Pattern getRhythm44()
	{
		rhythmPattern=new Pattern();

		for (int i = 0; i < 32; i++) {

			//随机生成 鼓点
			int index = (int)(Math.random()*rhythmArr.length);
			//随机加入鼓点
			rhythmStr+=rhythmArr[index];

			if (i%4==0){
				rhythmStr+=" . ";
			}
			//rhythmStr+=" . ";
		}

		//加入鼓点
		rhythm.addLayer(rhythmStr);
		rhythmPattern.add(rhythm.getPattern());

		return rhythmPattern;
	}
	

}
