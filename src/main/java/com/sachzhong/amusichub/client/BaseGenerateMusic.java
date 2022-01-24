package com.sachzhong.amusichub.client;

import com.sachzhong.amusichub.*;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.util.ArrayList;
import java.util.List;


/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHub
*  版本： 1.0
*  创建时间：2020年1月16日 上午10:24:47
*  类名：GenerateSong.java
*  类说明：生成歌曲
*/
public class BaseGenerateMusic {
	
		private int speed=120;


		//歌曲总音轨
		private Pattern song=new Pattern();
		
		//音轨列表
		private List<Pattern> patternList=new ArrayList<Pattern>();
		
		//乐器列表
		private List<String> instrumentList =new ArrayList<String>();
		
		//音乐轨道列表的长度
		//private int patternLength=0;
		
		//乐器轨道列表的长度
		private int instrumentLength=0;
		
		//构造方法，必须传入乐器轨道列表，以好确定创建多少的轨道  整体速度
		public BaseGenerateMusic(List<String> instrumentList,int speed) {
			
			//赋值乐器列表
			this.instrumentList = instrumentList;
			
			//根据传入乐器轨道列表 的大小 来 创建 主轨道
			this.instrumentLength = instrumentList.size();
			
			for (int i = 0; i < instrumentLength; i++) {
				

				patternList.add(new Pattern());
			}
			
		}
		
		
		//前奏
		private List<Pattern> Intro=new ArrayList<Pattern>();
		
		//生成前奏  根据和弦走向的根音生成n节yinyu的音符
		public void generateIntro(String jidiao,String chordpath,int yinyu,int speed)
		{
			MusicIntroImpl  musicIntroImpl =new MusicIntroImpl();
			this.Intro = musicIntroImpl.generateMusic(jidiao, chordpath, yinyu, speed,instrumentLength);
		}
		
		
		
		//主题
		private List<Pattern> Verse=new ArrayList<Pattern>();
	
		//生成主题
		public void generateVerse(String jidiao,String chordpath,int yinyu,int speed)
		{
			MusicVerseImpl  musicVerseImpl =new MusicVerseImpl();
			this.Verse = musicVerseImpl.generateMusic(jidiao, chordpath, yinyu, speed,instrumentLength);
		}
		
		//副歌前
		private List<Pattern> PreChorus=new ArrayList<Pattern>();
		//生成副歌前
		public void generatePreChorus(String jidiao,String chordpath,int yinyu,int speed)
		{
			MusicPreChorusImpl  musicPreChorusImpl  =new MusicPreChorusImpl();
			this.PreChorus = musicPreChorusImpl.generateMusic(jidiao, chordpath, yinyu, speed,instrumentLength);
		}
		
		//副歌
		private List<Pattern> Chorus=new ArrayList<Pattern>();

		//生成副歌
		public void generateChorus(String jidiao,String chordpath,int yinyu,int speed)
		{
			MusicChorusImpl musicChorusImpl =new MusicChorusImpl();
			this.Chorus = musicChorusImpl.generateMusic(jidiao, chordpath, yinyu, speed,instrumentLength);
		}
		
		//间奏
		private List<Pattern> Bridge=new ArrayList<Pattern>();

		//生成间奏
		public void generateBridge(String jidiao,String chordpath,int yinyu,int speed)
		{
			MusicBridgeImpl musicBridgeImpl =new MusicBridgeImpl();
			this.Bridge = musicBridgeImpl.generateMusic(jidiao, chordpath, yinyu, speed,instrumentLength);
		}	
		
		//结尾
		private List<Pattern> Outro=new ArrayList<Pattern>();
		
		//生成结尾
		public void generateOutro(String jidiao,String chordpath,int yinyu,int speed)
		{
			MusicOutroImpl musicOutroImpl =new MusicOutroImpl();
			this.Outro = musicOutroImpl.generateMusic(jidiao, chordpath, yinyu, speed,instrumentLength);
		}
		
		//生成音乐
		private void  generateSong()
		{
			//先清空主音轨
			song.clear();
			
			
			
			//歌曲的形式   Intro -> Verse ->  Pre-Chorus ->  Chorus ->  Re-Intro -> 
			//pre-Chorus ->  Chorus ->  Bridge ->  Chorus -> Outro
			
			//{"前奏","主题","副歌前","副歌","重复前奏","副歌前","副歌","间奏","副歌","结尾"};
			
			//插入各个部分的音轨
			this.InsertMusic(Intro);
			this.InsertMusic(Verse);
			this.InsertMusic(PreChorus);
			this.InsertMusic(Chorus);
			this.InsertMusic(Intro);
			this.InsertMusic(PreChorus);
			this.InsertMusic(Chorus);
			this.InsertMusic(Bridge);
			this.InsertMusic(Chorus);
			this.InsertMusic(Outro);
			
			
			
			//音乐轨道列表的长度
			//int patternLength = patternList.size();
			//乐器轨道列表的长度
			//instrumentLength =instrumentList.size();
			
			Pattern pattern=new Pattern();
			
			String instrument;
			

			
			//遍历整个轨道列表
			for (int i = 0; i < instrumentLength; i++) {
				
				//获取音轨
				pattern=patternList.get(i);
				
				//获取乐器
				instrument=this.instrumentList.get(i);
				
				//设置音轨
				pattern.setVoice(i).setInstrument(instrument).setTempo(speed);
				
				System.out.println(pattern.toString());
				
				//增加到主音轨
				song.add(pattern);
			}
			

		}
		
		//播放音乐
		public void PlaySong()
		{
			generateSong();
			
			Player player1=new Player();
			MusicPlayThread musicThread=new MusicPlayThread(player1,song);
			musicThread.start();
		}
		
		//保存音乐
		public void SaveSong()
		{
			String Title = "基础生成音乐";
			//保存
			MusicSaveThread musicUtil=new MusicSaveThread(song, Title);
			musicUtil.start();
		}
		
		//插入音乐
		private void InsertMusic(List<Pattern> generateList)
		{
			
			//遍历生成的整个轨道列表
			for (int i = 0; i < generateList.size(); i++) {
				
				//获取总轨道
				Pattern  pattern=this.patternList.get(i);
				//把传进来的轨道加入到总轨道
				pattern.add(generateList.get(i));
				//System.out.println(pattern);
			}

		}

		public List<String> getInstrumentList() {
			return instrumentList;
		}

		public void setInstrumentList(List<String> instrumentList) {
			this.instrumentList = instrumentList;
		}
		
		

}
