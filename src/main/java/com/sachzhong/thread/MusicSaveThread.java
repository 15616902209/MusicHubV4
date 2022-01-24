package com.sachzhong.thread;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.PatternProducer;

import java.io.File;
import java.io.IOException;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年1月23日 下午12:02:37
*  类名：MusicSaveThread.java
*  类说明：音乐保存线程类
*/
public class MusicSaveThread extends Thread{

	private PatternProducer MusicPattern;
	private String FilePath = "";
	private static final String BASE_PATH = "D://mid/";
	
	public MusicSaveThread(PatternProducer musicPattern,String fileName)
	{
		this.MusicPattern=musicPattern;
		this.FilePath=BASE_PATH+fileName+"_"+System.currentTimeMillis()+".mid";
	}
	
	@Override
	public void run()
	{
		 //保存，midi
		try {
			System.out.println("正在保存mid文件中》》》》文件地址："+FilePath);
			MidiFileManager.savePatternToMidi(MusicPattern, new File(FilePath));
			System.out.println("保存结束！");
		} catch (IOException e) {
			System.out.println("保存mid失败");
			e.printStackTrace();
		}
	}

}
