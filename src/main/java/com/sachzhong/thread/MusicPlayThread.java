package com.sachzhong.thread;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHub
*  版本： 1.0
*  类名：MusicPlayThread.java
*  类说明：音乐播放线程类
*/
public class MusicPlayThread extends Thread {

	private Pattern pattern;
	private Player player;
	
	public MusicPlayThread(Player myplayer,Pattern song)
	{
		player=myplayer;
		pattern=song;
	}
	
	@Override
	public void run()
	{
		//播放
		player.play(pattern);
	}
	
}
