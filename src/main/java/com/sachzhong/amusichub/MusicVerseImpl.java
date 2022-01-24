package com.sachzhong.amusichub;

import com.sachzhong.util.ArpeggioRandomGenerateUtil;
import com.sachzhong.util.ChordUtil;
import com.sachzhong.util.MelodyRandomGenerateUtil;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： SachZhong 钟盛勤 项目名：MusicHub 版本： 1.0 创建时间：2020年1月15日 下午6:10:53
 * 类名：GenerateVerse.java 类说明：生成主题
 */
public class MusicVerseImpl implements AGenerateMusic {

	@Override
	public List<Pattern> generateMusic(String jidiao, String chordpath, int yinyu, int speed) {
		// 钢琴和弦
		Pattern pianoChord = new Pattern();
		// 钢琴旋律
		Pattern pianoMelody = new Pattern();

		List<Pattern> patternList = new ArrayList<Pattern>();

		// String Chord_String = "VI IV V I";
		ChordUtil chordUtil = new ChordUtil();
		ChordProgression cp = chordUtil.getChordProgression(chordpath, jidiao, yinyu);
		Chord[] chords = cp.getChords();

		// 旋律生成工具
		MelodyRandomGenerateUtil melodyGenerateUtil = new MelodyRandomGenerateUtil();

		// 琶音生成工具
		ArpeggioRandomGenerateUtil arpeggioGenerateUtil = new ArpeggioRandomGenerateUtil();

		// 生成主题

		for (int k = 0; k < chords.length; k++) {

			// 随机生成种子
			int randomInt = (int) Math.round(Math.random() * 3);

			pianoChord.add(chords[k] + "h ");
			pianoChord.add(chords[k] + "h ");

			// 随机生成种子
			randomInt = (int) Math.round(Math.random() * 3);

			if (randomInt == 0) {
				pianoMelody.add(melodyGenerateUtil.getMelody(chords[k]));

			} else if (randomInt == 1) {
				pianoMelody.add(melodyGenerateUtil.getMelody(chords[k], yinyu + 2));

			} else if (randomInt == 2) {
				pianoMelody.add(arpeggioGenerateUtil.getArpeggio(chords[k], yinyu + 3));

			} else if (randomInt == 3) {
				pianoMelody.add(arpeggioGenerateUtil.getArpeggio(chords[k], yinyu + 1));

			}

			pianoChord.add(" | ");
			pianoMelody.add(" | ");

		}

		pianoChord.add(pianoChord);
		pianoMelody.add(pianoMelody);

		patternList.add(pianoChord);
		patternList.add(pianoMelody);

		return patternList;
	}

	// 根据传入的轨道数量生成相应的音轨列表
	@Override
	public List<Pattern> generateMusic(String jidiao, String chordpath, int yinyu, int speed, int size) {
		List<Pattern> patternList = new ArrayList<Pattern>();

		if (size == 1) {
			patternList = generateMusicBy_1(jidiao, chordpath, yinyu, speed);
		} else if (size == 2) {
			patternList = generateMusicBy_2(jidiao, chordpath, yinyu, speed);
		} else if (size == 3) {
			patternList = generateMusicBy_3(jidiao, chordpath, yinyu, speed);
		}

		return patternList;
	}

	// 如果轨道为 1 轨的话 生成乐器独奏
	private List<Pattern> generateMusicBy_1(String jidiao, String chordpath, int yinyu, int speed) {

		// 旋律
		Pattern Melody = new Pattern();

		List<Pattern> patternList = new ArrayList<Pattern>();

		// String Chord_String = "VI IV V I";
		ChordUtil chordUtil = new ChordUtil();
		ChordProgression cp = chordUtil.getChordProgression(chordpath, jidiao, yinyu);
		Chord[] chords = cp.getChords();

		// 旋律生成工具
		MelodyRandomGenerateUtil melodyGenerateUtil = new MelodyRandomGenerateUtil();

		// 琶音生成工具
		// ArpeggioRandomGenerateUtil arpeggioGenerateUtil = new
		// ArpeggioRandomGenerateUtil();

		for (int k = 0; k < chords.length; k++) {

			// 随机生成种子
			int randomInt = (int) Math.round(Math.random() * 3);

			// 随机生成种子
			randomInt = (int) Math.round(Math.random() * 3);

			if (randomInt == 0) {
				Melody.add(melodyGenerateUtil.getMelody(chords[k]));

			} else if (randomInt == 1) {
				Melody.add(melodyGenerateUtil.getMelody(chords[k], yinyu + 2));

			} else if (randomInt == 2) {
				Melody.add(melodyGenerateUtil.getMelody(chords[k], yinyu + 3));

			} else if (randomInt == 3) {
				Melody.add(melodyGenerateUtil.getMelody(chords[k], yinyu + 1));

			}

			Melody.add(" | ");

		}

		// 重复一遍
		Melody.add(Melody);

		patternList.add(Melody);

		return patternList;

	}

	// 如果轨道为 2 轨的话 生成乐器独奏 加 和弦
	private List<Pattern> generateMusicBy_2(String jidiao, String chordpath, int yinyu, int speed) {
		// 钢琴和弦
		Pattern pianoChord = new Pattern();
		// 钢琴旋律
		Pattern pianoMelody = new Pattern();

		List<Pattern> patternList = new ArrayList<Pattern>();

		// String Chord_String = "VI IV V I";
		ChordUtil chordUtil = new ChordUtil();
		ChordProgression cp = chordUtil.getChordProgression(chordpath, jidiao, yinyu);
		Chord[] chords = cp.getChords();

		// 旋律生成工具
		MelodyRandomGenerateUtil melodyGenerateUtil = new MelodyRandomGenerateUtil();

		// 琶音生成工具
		ArpeggioRandomGenerateUtil arpeggioGenerateUtil = new ArpeggioRandomGenerateUtil();

		// 生成主题

		for (int k = 0; k < chords.length; k++) {

			// 随机生成种子
			int randomInt = (int) Math.round(Math.random() * 3);

			pianoChord.add(chords[k] + "h ");
			pianoChord.add(chords[k] + "h ");

			// 随机生成种子
			randomInt = (int) Math.round(Math.random() * 3);

			if (randomInt == 0) {
				pianoMelody.add(melodyGenerateUtil.getMelody(chords[k]));

			} else if (randomInt == 1) {
				pianoMelody.add(melodyGenerateUtil.getMelody(chords[k], yinyu + 2));

			} else if (randomInt == 2) {
				pianoMelody.add(arpeggioGenerateUtil.getArpeggio(chords[k], yinyu + 3));

			} else if (randomInt == 3) {
				pianoMelody.add(arpeggioGenerateUtil.getArpeggio(chords[k], yinyu + 1));

			}

			pianoChord.add(" | ");
			pianoMelody.add(" | ");

		}

		pianoChord.add(pianoChord);
		pianoMelody.add(pianoMelody);

		patternList.add(pianoChord);
		patternList.add(pianoMelody);

		return patternList;
	}

	// 如果轨道为 3轨的话 生成乐器独奏 加 吉他
	private List<Pattern> generateMusicBy_3(String jidiao, String chordpath, int yinyu, int speed) {
		// 钢琴和弦
		Pattern pianoChord = new Pattern();
		// 钢琴旋律
		Pattern pianoMelody = new Pattern();

		// 吉他旋律
		Pattern guitarMelody = new Pattern();

		List<Pattern> patternList = new ArrayList<Pattern>();

		// String Chord_String = "VI IV V I";
		ChordUtil chordUtil = new ChordUtil();
		ChordProgression cp = chordUtil.getChordProgression(chordpath, jidiao, yinyu);
		Chord[] chords = cp.getChords();

		// 旋律生成工具
		MelodyRandomGenerateUtil melodyGenerateUtil = new MelodyRandomGenerateUtil();

		// 琶音生成工具
		ArpeggioRandomGenerateUtil arpeggioGenerateUtil = new ArpeggioRandomGenerateUtil();

		for (int k = 0; k < chords.length; k++) {

			// 随机生成种子
			int randomInt = (int) Math.round(Math.random() * 3);

			pianoChord.add(chords[k] + "h ");
			pianoChord.add(chords[k] + "h ");

			// 随机生成种子
			randomInt = (int) Math.round(Math.random() * 3);

			if (randomInt == 0) {
				pianoMelody.add("Rw");
				guitarMelody.add(arpeggioGenerateUtil.getArpeggio(chords[k], yinyu + 2));
				
			} else if (randomInt == 1) {
				pianoMelody.add(melodyGenerateUtil.getMelody(chords[k], yinyu + 2));
				guitarMelody.add(arpeggioGenerateUtil.getArpeggio(chords[k], yinyu + 1));

			} else if (randomInt == 2) {
				pianoMelody.add(arpeggioGenerateUtil.getArpeggio(chords[k], yinyu + 1));
				guitarMelody.add(arpeggioGenerateUtil.getArpeggio(chords[k], yinyu + 3));

			} else if (randomInt == 3) {
				guitarMelody.add("Rw");
				pianoMelody.add(melodyGenerateUtil.getMelody(chords[k], yinyu + 1));

			}

			pianoChord.add(" | ");
			pianoMelody.add(" | ");
			guitarMelody.add(" | ");

		}

		pianoChord.add(pianoChord);
		pianoMelody.add(pianoMelody);
		guitarMelody.add(guitarMelody);

		patternList.add(pianoChord);
		patternList.add(pianoMelody);
		patternList.add(guitarMelody);

		return patternList;
	}

}
