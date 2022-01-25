package com.sachzhong.factoryimpl;

import com.sachzhong.factory.PianoGenerate;
import com.sachzhong.service.NoteGenerateService;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;

import java.util.List;

/**
 * 作者： SachZhong 钟盛勤 项目名：MusicHubV2 版本： 1.0 创建时间：2020年1月26日 下午6:22:51
 * 类名：PianoGenerateImpl.java 类说明：钢琴生成的实现类
 */
public class PianoGenerateImpl implements PianoGenerate {

	// 左手钢琴音轨 和弦 低音
	private Pattern pianoLeftPattern;

	// 右手钢琴音轨 伴奏
	private Pattern pianoRightPattern;

	// 生成柱式和弦的肢体1
	@Override
	public Pattern[] getzhushi_1(Chord chord) {

		Pattern[] pattern = new Pattern[2];
		Pattern pianoLeftPattern = new Pattern();
		Pattern pianoRightPattern = new Pattern();

		NoteGenerateService noteGenerateService = new NoteGenerateService();
		List<String> NoteList = noteGenerateService.getNotes(chord);

		int index = (int) (Math.random() * 2 + 6);

		int index1 = (int) (Math.random() * 5);
		int index2 = (index1 + 1) % 5;

//		for (int i = 0; i < NoteList.size(); i++) {
//			System.out.println("NoteList:"+NoteList.get(i));
//		}

		String leftsong = " ";
		String rightsong = " ";

		String rootSong = NoteList.get(5) + "+" + NoteList.get(index);
		String meSong = NoteList.get(index1) + "+" + NoteList.get(index2);

		leftsong += "(" + rootSong + ")q RQ " + "(" + rootSong + ")q RQ | ";
		rightsong += "(" + meSong + ")q " + "(" + meSong + ")q " + "(" + meSong + ")q " + "(" + meSong + ")q |";

		pianoLeftPattern.add(leftsong);
		pianoRightPattern.add(rightsong);

		pattern[0] = pianoLeftPattern;
		pattern[1] = pianoRightPattern;
		return pattern;
	}

	// 生成分解和弦的肢体1
	@Override
	public Pattern[] getFenJie_1(Chord chord) {
		Pattern[] pattern = new Pattern[2];
		Pattern pianoLeftPattern = new Pattern();
		Pattern pianoRightPattern = new Pattern();

		NoteGenerateService noteGenerateService = new NoteGenerateService();

		List<String> NoteList = noteGenerateService.getNotes(chord);
		
		int index1 = (int) (Math.random() * 5);
		int index2 = (index1 + 1) % 5;

//		for (int i = 0; i < NoteList.size(); i++) {
//			System.out.println("NoteList:"+NoteList.get(i));
//		}

		String leftsong = " ";
		String rightsong = " ";

		leftsong += NoteList.get(5) + "i " + NoteList.get(7) + "i Ri Ri Ri " + NoteList.get(7) + "i " + "Ri "
				+ NoteList.get(7) + "i |";
		rightsong += "Ri Ri " + NoteList.get(index1) + "i " + NoteList.get(index2) + "i " + "Ri Ri "
				+ NoteList.get(index1) + "i Ri |";

		// leftsong+=NoteList.get(5)+"i "+NoteList.get(6)+"i "+NoteList.get(7)+"i
		// "+NoteList.get(index1)+"i Ri Ri Ri Ri |";
		// rightsong+="Ri Ri Ri Ri Ri "+NoteList.get(6)+"i "+NoteList.get(7)+"i
		// "+NoteList.get(6)+"i |";

		pianoLeftPattern.add(leftsong);
		pianoRightPattern.add(rightsong);

		pattern[0] = pianoLeftPattern;
		pattern[1] = pianoRightPattern;
		return pattern;
	}

	// 生成分解和弦的肢体2
	@Override
	public Pattern[] getFenJie_2(Chord chord) {
		Pattern[] pattern = new Pattern[2];
		Pattern pianoLeftPattern = new Pattern();
		Pattern pianoRightPattern = new Pattern();

		NoteGenerateService noteGenerateService = new NoteGenerateService();
		List<String> NoteList = noteGenerateService.getNotes(chord);

//		for (int i = 0; i < NoteList.size(); i++) {
//			System.out.println("NoteList:"+NoteList.get(i));
//		}

		String leftsong = " ";
		String rightsong = " ";

		leftsong +=NoteList.get(5)+"i "+NoteList.get(6)+"q "+" Rq "+NoteList.get(6)+"i "+" Ri "+NoteList.get(6)+"i |";
		rightsong +="Ri Rq "+NoteList.get(4)+"i "+NoteList.get(3)+"i Ri "+NoteList.get(0)+"i "+"Ri |" ;


		pianoLeftPattern.add(leftsong);
		pianoRightPattern.add(rightsong);

		pattern[0] = pianoLeftPattern;
		pattern[1] = pianoRightPattern;
		return pattern;
	}
	
	
	
	
	
	
	
	

	public Pattern getPianoLeftPattern() {
		return pianoLeftPattern;
	}

	public void setPianoLeftPattern(Pattern pianoLeftPattern) {
		this.pianoLeftPattern = pianoLeftPattern;
	}

	public Pattern getPianoRightPattern() {
		return pianoRightPattern;
	}

	public void setPianoRightPattern(Pattern pianoRightPattern) {
		this.pianoRightPattern = pianoRightPattern;
	}

}
