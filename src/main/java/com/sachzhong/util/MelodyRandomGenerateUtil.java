package com.sachzhong.util;

import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： SachZhong 钟盛勤 项目名：MusicHubV2 版本： 1.0 创建时间：2020年1月23日 下午12:12:43
 * 类名：MelodyGenerateUtil.java 类说明:随机旋律生成工具
 */
public class MelodyRandomGenerateUtil {


	// 音乐拍节奏生成工具类
	private MusicPaiUtil musicPai = new MusicPaiUtil();

	// 获取旋律
	public Pattern getMelody(Chord chord) {
		
		Pattern pattern = new Pattern();

		// 随机生成种子
		int randomInt = (int) Math.round(Math.random() * 3);
		
		// 获取4/4拍中的音符节奏
		List<String> jiezou=new ArrayList<String>();

		if (randomInt == 0) {
			
			// 获取节奏
			jiezou = musicPai.getAllPai();
			
		} else if (randomInt == 1) {
			
			// 获取节奏
			jiezou = musicPai.getPaiAll8List();
		
		} else if (randomInt == 2) {

			// 获取节奏
			jiezou = musicPai.getPai44();
			
		} else if (randomInt == 3) {

			// 获取节奏
			jiezou = musicPai.getAllPai();
			
		}

		pattern =generateMelodyByRandom(chord,jiezou);
		return pattern;
	}
	
	
	// 获取旋律
		public Pattern getMelody(Chord chord,int yinyu) {
			
			Pattern pattern = new Pattern();

			// 随机生成种子
			int randomInt = (int) Math.round(Math.random() * 3);
			
			// 获取4/4拍中的音符节奏
			List<String> jiezou=new ArrayList<String>();



			if (randomInt == 0) {
				
				// 获取节奏
				jiezou = musicPai.getAllPai();

			} else if (randomInt == 1) {
				
				// 获取节奏
				jiezou = musicPai.getPaiAll8List();

			} else if (randomInt == 2) {

				// 获取节奏
				jiezou = musicPai.getPai44();
				
			} else if (randomInt == 3) {

				// 获取节奏
				jiezou = musicPai.getAllPai();
				
			}

			pattern =generateMelodyByRandom2(chord,jiezou,yinyu);
			
			return pattern;
		}
	

	// 随机生成旋律
	private Pattern generateMelodyByRandom(Chord chord,List<String> jiezou) {
		Pattern pattern = new Pattern();

		// 加入传入的是C,5 那返回 C5 E5 G5 B5
		Note[] nodeArr = chord.getNotes();

		//音符下标
		int noteIndex=0;
		
		//音符
		String note="C";

		// 根据生成的节奏随机获取音符组成旋律
		for (int j = 0; j < jiezou.size(); j++) {
			
			//从和弦中随机取出音符
			noteIndex= (int) Math.round(Math.random() * (nodeArr.length-1));
			//System.err.println(noteIndex);
			
			note=nodeArr[noteIndex].toString()+jiezou.get(j);

			pattern.add(note);
		}
		return pattern;
	}

	
	// 随机生成旋律2
	private Pattern generateMelodyByRandom2(Chord chord,List<String> jiezou,int yinyu) {
		Pattern pattern = new Pattern();

		// 加入传入的是C,5 那返回 C5 E5 G5 B5
		Note[] nodeArr = chord.getNotes();

		//音符下标
		int noteIndex=0;
		
		//音符
		String note="C";

		// 根据生成的节奏随机获取音符组成旋律
		for (int j = 0; j < jiezou.size(); j++) {
			
			//从和弦中随机取出音符
			noteIndex= (int) Math.round(Math.random() * (nodeArr.length-1));
			//System.err.println(noteIndex);
		
			
			String chordRoot = nodeArr[noteIndex].toString();
			chordRoot=chordRoot.substring(0, 1);
		
			note=chordRoot+yinyu+jiezou.get(j);

			pattern.add(note);
		}
		return pattern;
	}
	
}
