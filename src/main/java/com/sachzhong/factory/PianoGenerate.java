package com.sachzhong.factory;

import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年1月23日 下午12:00:34
*  类名：PianoGenerate.java
*  类说明：钢琴作曲生成
*/
public interface PianoGenerate extends BaseGenerate{
	
	//传入一个和弦返回一小节柱式音符 4/4拍  共8个音符
	public Pattern[] getzhushi_1(Chord chord);
	
	//传入一个和弦返回一小节分解音符 4/4拍  共8个音符
	public Pattern[] getFenJie_1(Chord chord);
	
	//传入一个和弦返回一小节分解音符 4/4拍  共8个音符
	public Pattern[] getFenJie_2(Chord chord);
}
