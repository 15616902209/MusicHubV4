package com.sachzhong.service;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;

/**
 * @author SachZhong
 * @date 2022/1/25 18:19
 * @info 和弦生成工具
 */
public class ChordGenerateService {

	/**
     * 根据和弦走向 获取 和弦走向类
	 * @param chordProgression
     * @param jidiao
     * @param yinyu
     * @return
     */
	public ChordProgression getChordProgression(String chordProgression,String jidiao,int yinyu)
	{
		 return new ChordProgression(chordProgression).setKey(jidiao+yinyu);
	}

	/**
	 * 根据和弦走向 获取 和弦类数组
	 * @param chordProgression
	 * @param jidiao
	 * @param yinyu
	 * @return
	 */
	public Chord[]  getChordArr(String chordProgression,String jidiao,int yinyu)
	{
		ChordProgression cp=new ChordProgression(chordProgression).setKey(jidiao+yinyu); 
		//和弦走向生成的和弦数组
		return cp.getChords();
	}

}
