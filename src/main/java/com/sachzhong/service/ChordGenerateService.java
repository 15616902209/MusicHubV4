package com.sachzhong.service;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

/**
 * @author SachZhong
 * @date 2022/1/2 18:19
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

	/**
	 * 构建和弦柱式
	 * @param pattern 乐符存储对象
	 * @param chord 和弦对象
	 * @param type 类型
	 */
	public void buildZhuShi(Pattern pattern, Chord chord,int type){
		if (type == 0){
			pattern.add(chord +"w|");
		}else if (type == 1){
			pattern.add(chord +"h "+chord +"h|");
		}else if (type == 2){
			pattern.add(chord +"h "+chord +"q "+chord +"q|");
		}else if (type == 3){
			pattern.add(chord +"q "+chord +"q "+chord +"q "+chord +"q|");
		}
	}

	/**
	 * 构建和弦分解
	 * @param pattern 乐符存储对象
	 * @param chord 和弦对象
	 * @param type 类型
	 */
	public void buildFenJie(Pattern pattern, Chord chord,int type){
		Note[] notes = chord.getNotes();
		if (type == 0){
			pattern.add(notes[2]+"q " +notes[1]+"q " +notes[0]+"q " +notes[1]+"q|");
		}else if (type == 1){
			pattern.add(notes[2]+"q " +notes[2]+"i "+notes[0]+"s "+notes[1]+"s "
					+notes[2]+"q " +notes[0]+"i "+notes[0]+"i|");
		}else if (type == 2){
			pattern.add(notes[0]+"q " +notes[2]+"i "+notes[0]+"s "+notes[1]+"s "
					+notes[0]+"q " +notes[2]+"i "+notes[0]+"s "+notes[1]+"s|");
		}else if (type == 3){
			pattern.add(notes[2]+"q " +notes[1]+"i "+notes[1]+"i "
					+notes[2]+"q " +notes[0]+"i "+notes[0]+"i|");
		}
	}

}
