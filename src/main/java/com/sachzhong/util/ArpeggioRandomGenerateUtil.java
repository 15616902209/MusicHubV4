package com.sachzhong.util;

import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

/**
 * 作者： SachZhong 钟盛勤 项目名：MusicHubV2 版本： 1.0 创建时间：2020年1月23日 下午12:12:43
 * 类名：MelodyGenerateUtil.java 类说明:随机 琶音生成工具
 * 
 * 琶音生成工具
 * 
 */
public class ArpeggioRandomGenerateUtil {

	// 获取 琶音
	public Pattern getArpeggio(Chord chord) {

		Pattern pattern = new Pattern();

		// 随机生成种子
		int randomInt = (int) Math.round(Math.random() * 3);

		// 规则数组
		int[] rule = null;

		if (randomInt == 0) {

			// 采用 0，1，2，1， 0，2，0，2
			rule = new int[] { 0, 2, 0, 2, 0, 2, 0, 2 };
			pattern = generateArpeggio(chord, rule);

		} else if (randomInt == 1) {

			// 采用 0，1，2，1， 0，2，0，2
			rule = new int[] { 0, 1, 2, 0, 0, 1, 2, 0 };
			pattern = generateArpeggio(chord, rule);

		} else if (randomInt == 2) {

			// 采用 0，1，2，1， 0，2，0，2
			rule = new int[] { 0, 2, 1, 2, 1, 2, 1, 2 };
			pattern = generateArpeggioByRule(chord, rule);

		} else if (randomInt == 3) {

			// 采用 0，2，1，2， 1，2，1，2 理查德式
			rule = new int[] { 0, 2, 1, 2, 1, 2, 1, 2 };
			pattern = generateArpeggioByRule(chord, rule);

		}

		return pattern;
	}

	// 获取 琶音
	public Pattern getArpeggio(Chord chord, int yinyu) {

		Pattern pattern = new Pattern();

		// 随机生成种子
		int randomInt = (int) Math.round(Math.random() * 3);

		// 规则数组
		int[] rule = null;

		if (randomInt == 0) {

			// 采用 0，1，2，1， 0，2，0，2
			rule = new int[] { 0, 2, 0, 2, 0, 2, 0, 2 };

		} else if (randomInt == 1) {

			// 采用 0，1，2，1， 0，2，0，2
			rule = new int[] { 0, 1, 2, 0, 0, 1, 2, 0 };

		} else if (randomInt == 2) {

			// 采用 0，1，2，1， 0，2，0，2
			rule = new int[] { 0, 2, 1, 2, 1, 2, 1, 2 };
		} else if (randomInt == 3) {

			// 采用 0，2，1，2， 1，2，1，2 理查德式
			rule = new int[] { 0, 2, 1, 2, 1, 2, 1, 2 };
		}
		pattern = generateArpeggio(chord, rule, yinyu);
		
		
		return pattern;
	}

	// 根据规则生成琶音
	private Pattern generateArpeggioByRule(Chord chord, int[] rule) {

		Pattern pattern = new Pattern();

		// 加入传入的是C,5 那返回 C5 E5 G5 B5
		Note[] nodeArr = chord.getNotes();

		// 开始生成理查德式音符列表

		// 先将第2个音符提高八度，
		String node = nodeArr[1].toString();
		// System.out.println("oldnode:"+node+"String.valueOf((yinyu+1):"+String.valueOf(yinyu+1));
		// 如果先匹配到音域+1，的就+2
		// node=node.replaceAll(String.valueOf((yinyu+1)), String.valueOf((yinyu+2)));
		// 后面再匹配当前音域
		// node=node.replaceAll(String.valueOf(yinyu),String.valueOf((yinyu+1)));

		nodeArr[1] = new Note(node);
		/// System.out.println("newnode:"+node);

		// 八个八分音符
		for (int i = 0; i < 8; i++) {

			String mynode = nodeArr[rule[i]] + "i";
			pattern.add(mynode);
		}

		return pattern;
	}

	// 根据规则生成琶音
	private Pattern generateArpeggio(Chord chord, int[] rule) {

		Pattern pattern = new Pattern();

		// 加入传入的是C,5 那返回 C5 E5 G5 B5
		Note[] nodeArr = chord.getNotes();

		// 开始生成理查德式音符列表

		// 八个八分音符
		for (int i = 0; i < 8; i++) {

			String mynode = nodeArr[rule[i]] + "i";
			pattern.add(mynode);
		}

		return pattern;
	}

	// 根据规则生成琶音
	private Pattern generateArpeggio(Chord chord, int[] rule, int yinyu) {

		Pattern pattern = new Pattern();

		// 加入传入的是C,5 那返回 C5 E5 G5 B5
		Note[] nodeArr = chord.getNotes();

		// 音符
		String note = "C";

		// 开始生成理查德式音符列表

		// 八个八分音符
		for (int i = 0; i < 8; i++) {
			String chordRoot = nodeArr[rule[i]].toString();
			chordRoot=chordRoot.substring(0, 1);

			note = chordRoot + yinyu + "i";
			pattern.add(note);
		}
		
		return pattern;
	}

}
