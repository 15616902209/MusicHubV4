package com.sachzhong.client;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年6月1日 下午4:29:26
*  类名：Base.java
*  类说明：
*/
public class Base {
	//两只老虎测试
	public static void main(String[] args) {
		//播放者
		Player player = new Player();
		//第一句：两只老虎
		Pattern pattern1 = new Pattern("C5q D5q E5q C5q |");
		//第二句：跑得快
		Pattern pattern2 = new Pattern("E5q F5q G5h |");
		//第三句：一只没有眼睛
		Pattern pattern3 = new Pattern("G5i A5i G5i F5i E5q C5q |");
		//第四句：真奇怪
		Pattern pattern4 = new Pattern("C5q G4q C5h |");
		//乐谱
		Pattern song = new Pattern();
		//把每个乐句加到乐谱中去，每句加两遍
		song.add(pattern1, 2);
		song.add(pattern2, 2); 
		song.add(pattern3, 2); 
		song.add(pattern4, 2); 
		//设置乐谱在第一轨 乐器小提琴 第一层 速度120
		song.setVoice(1).setInstrument(40).setLayer(1).setTempo(120);
		//播放者 播放音乐
		player.play(song);

	}
}
