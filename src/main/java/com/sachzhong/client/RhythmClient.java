package com.sachzhong.client;

import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.service.RhythmGenerateService;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * @author SachZhong
 * @date 2022/1/24 17:03
 * @info 节奏生成
 */
public class RhythmClient {

	public static void main(String[] args) {
		
		Pattern song=new Pattern();
		
		RhythmGenerateService rhythmGenerateService =new RhythmGenerateService();
		Pattern rhythmPattern= rhythmGenerateService.getRhythm();
		song.add(rhythmPattern);

		System.out.println("节奏:"+song);

		Player player1=new Player();
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		playThread.start();
		MusicSaveThread saveThread=new MusicSaveThread(song, "节奏");
		saveThread.start();
		
	}
	
}
