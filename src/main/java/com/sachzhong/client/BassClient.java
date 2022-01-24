package com.sachzhong.client;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.util.BassGenerateUtil;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年2月10日 下午3:07:53
*  类名：BassClient.java
*  类说明：
*/
public class BassClient {
	
	public static void main(String[] args) {
		
		String[] chordPath=new String[]{"C","A","F","G"};
		Pattern song=new Pattern();
		
		BassGenerateUtil bassGenerateUtil=new BassGenerateUtil();
		
		Pattern bassPattern=bassGenerateUtil.getBass(chordPath, 3);
		 
		song.add(bassPattern);
		song.setVoice(1).setInstrument(Instruments.Acoustic_Bass).setTempo(120);
		System.out.println("贝斯:"+song);
		
		Player player1=new Player();
		MusicPlayThread playThread=new MusicPlayThread(player1, song);
		playThread.start();

		MusicSaveThread saveThread=new MusicSaveThread(song, "贝斯");
		saveThread.start();
	}

}
