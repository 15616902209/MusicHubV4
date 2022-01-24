package com.sachzhong.util;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年1月26日 下午5:26:29
*  类名：ChordUtil.java
*  类说明：和弦工具类
*/
public class ChordUtil {
	
	//根据和弦走向 获取 和弦走向类
	public ChordProgression getChordProgression(String chordProgression,String jidiao,int yinyu)
	{
		 ChordProgression cp=new ChordProgression(chordProgression).setKey(jidiao+yinyu); 
		 
		 return cp;
	}
	
	//根据和弦走向 获取 和弦类数组
	public Chord[]  getChordArr(String chordProgression,String jidiao,int yinyu)
	{
		ChordProgression cp=new ChordProgression(chordProgression).setKey(jidiao+yinyu); 
		//和弦走向生成的和弦数组
		Chord[] chords=cp.getChords();
		return chords;
	}

}
