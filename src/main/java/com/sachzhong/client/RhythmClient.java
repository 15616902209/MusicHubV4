package com.sachzhong.client;

import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.util.RhythmGenerateUtil;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年2月10日 下午2:56:08
*  类名：RhythmClient.java
*  类说明：
*/
public class RhythmClient {

	public static void main(String[] args) {
		
		Pattern song=new Pattern();
		
		RhythmGenerateUtil rhythmGenerateUtil=new RhythmGenerateUtil();
		
		Pattern rhythmPattern=rhythmGenerateUtil.getRhythm();
		song.add(rhythmPattern);

		rhythmPattern=rhythmGenerateUtil.getRhythm44();
		song.add(rhythmPattern);
		
		System.out.println("节奏:"+song);
		
		Player player1=new Player();
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		playThread.start();
		MusicSaveThread saveThread=new MusicSaveThread(song, "节奏");
		saveThread.start();
		
	}
	
}
