package com.sachzhong.factory;

import org.jfugue.pattern.Pattern;

/**
 * @author SachZhong
 * @date 2022/1/25 18:18
 * @info 贝斯生成工具
 */
public class BassGenerate {

	/**
	 * 贝斯音轨
	 */
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
