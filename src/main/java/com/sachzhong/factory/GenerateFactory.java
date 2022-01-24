package com.sachzhong.factory;

import com.sachzhong.factoryimpl.PianoGenerateImpl;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年1月23日 上午11:58:51
*  类名：GenerateFactory.java
*  类说明：
*/
public class GenerateFactory {
	
	public PianoGenerate getPianoGenerate()
	{
		return new PianoGenerateImpl();
	}

}
