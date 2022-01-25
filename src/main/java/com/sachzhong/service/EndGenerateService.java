package com.sachzhong.service;

import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

/**
 * 作者： SachZhong 钟盛勤 项目名：MusicHubV2 版本： 1.0 创建时间：2020年1月23日 下午12:12:43
 * 类名：MelodyGenerateUtil.java 类说明:随机 休止 停顿 结束音生成工具
 * 
休止 停顿 结束音生成工具
 * 
 */
public class EndGenerateService {

	// 过渡音生成工具
	public Pattern getEnd(Chord chord) {

		Pattern pattern = new Pattern();

		// 随机生成种子
		int randomInt = (int) Math.round(Math.random() * 3);


		if (randomInt == 0) {

			pattern = generateEnd_1(chord);

		} else if (randomInt == 1) {

			pattern = generateEnd_1(chord);

		} else if (randomInt == 2) {

			pattern = generateEnd_1(chord);

		} else if (randomInt == 3) {

			pattern = generateEnd_1(chord);

		}

		return pattern;
	}

	

	// 根据规则生成结束音 1
	private Pattern generateEnd_1(Chord chord) {

		Pattern pattern = new Pattern();

		// 加入传入的是C,5 那返回 C5 E5 G5 B5
		Note[] nodeArr = chord.getNotes();

		
		
		String qupu=" "+nodeArr[0] + "h "+nodeArr[1] + "q "+nodeArr[2] + "q | "; 
		
		qupu+=nodeArr[0] + "h "+nodeArr[2] + "h | ";
		qupu+=nodeArr[0] + "w";

		pattern.add(qupu);

		return pattern;
	}

	
}
