package com.sachzhong.song;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHub
*  版本： 1.0
*  创建时间：2020年1月16日 上午10:24:47
*  类名：GenerateSong.java
*  类说明：生成歌曲
*/
public class GenerateSong {
	
		private int speed=120;

		public GenerateSong(int speed)
		{
			this.speed=speed;
		}
	
		// 总歌词
		private String  MyGeChi = "----------------歌词: ";
		
		//歌曲总音轨
		private Pattern song=new Pattern();
		
				//旋律音轨
		private Pattern melodyPattern=new Pattern();
				//贝斯音轨
		private	Pattern bassPattern=new Pattern();
				//吉他音轨
		private	Pattern guitarPattern=new Pattern();
				//鼓点音轨
		private	Pattern rhythmPattern=new Pattern();
		
		
		//前奏
		private Pattern[] Intro=new Pattern[4];
		private String IntroGechi="";
		
		//生成前奏  根据和弦走向的根音生成n节yinyu的音符
		public void generateIntro(String  gechi,int yinyu)
		{
			GenerateIntro generate=new GenerateIntro(gechi, yinyu,speed);
			//生成前奏
			generate.generateIntro();
			//获取前奏
			Intro=generate.getIntro();
			IntroGechi=gechi;
		}
		
		//主题
		private Pattern[] Verse=new Pattern[4];
		private String VerseGechi="";		
		//生成主题
		public void generateVerse(String gechi,int yinyu)
		{
			GenerateVerse generate=new GenerateVerse(gechi, yinyu,speed);
			//生成主题
			generate.generateVerse();
			//获取主题
			Verse=generate.getVerse();
			VerseGechi=gechi;
		}
		
		//副歌前
		private Pattern[] PreChorus=new Pattern[4];
		private String PreChorusGechi="";		
			
		//生成副歌前
		public void generatePreChorus(String gechi,int yinyu)
		{
			GeneratePreChorus generate=new GeneratePreChorus(gechi, yinyu,speed);
			//生成副歌前
			generate.generatePreChorus();
			//获取副歌前
			PreChorus=generate.getPreChorus();
			PreChorusGechi=gechi;
		}
		
		//副歌
		private Pattern[] Chorus=new Pattern[4];
		private String ChorusGechi="";
		//生成副歌
		public void generateChorus(String gechi,int yinyu)
		{
			GenerateChorus generate=new GenerateChorus(gechi, yinyu,speed);
			//生成副歌
			generate.generateChorus();
			//获取副歌
			Chorus=generate.getChorus();
			ChorusGechi=gechi;
		}
		
		//间奏
		private Pattern[] Bridge=new Pattern[4];
		private String BridgeGechi="";
		//生成间奏
		public void generateBridge(String  gechi,int yinyu)
		{
			GenerateBridge generate=new GenerateBridge(gechi, yinyu,speed);
			//生成间奏
			generate.generateBridge();
			//获取间奏
			Bridge=generate.getBridge();
			BridgeGechi=gechi;
		}	
		
		//结尾
		private Pattern[] Outro=new Pattern[4];
		private String OutroGechi="";			
		//生成结尾
		public void generateOutro(String  gechi,int yinyu)
		{
			GenerateOutro generate=new GenerateOutro(gechi, yinyu,speed);
			//生成结尾
			generate.generateOutro();
			//获取结尾
			Outro=generate.getOutro();
			OutroGechi=gechi;
		}
		
		//生成音乐
		public void  generateSong()
		{
			//先清空主音轨
			song.clear();
			
			
			//设置乐器
			//旋律
			melodyPattern.setVoice(0).setInstrument(Instruments.Piano).setTempo(speed);
			//贝斯
			bassPattern.setVoice(1).setInstrument(Instruments.Acoustic_Bass).setTempo(speed);
			//吉他
			guitarPattern.setVoice(2).setInstrument(Instruments.Guitar).setTempo(speed);	
			//鼓点
			rhythmPattern.setTempo(speed);
			
			
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
			//拼接各个部分的歌词
			this.addGechi(IntroGechi);
			this.addGechi(VerseGechi);
			this.addGechi(PreChorusGechi);
			this.addGechi(ChorusGechi);
			this.addGechi(IntroGechi);
			this.addGechi(PreChorusGechi);
			this.addGechi(ChorusGechi);
			this.addGechi(BridgeGechi);
			this.addGechi(ChorusGechi);
			this.addGechi(OutroGechi);
			
		
			
			System.out.println("旋律:"+melodyPattern.toString());
			System.out.println("吉他:"+guitarPattern.toString());
			System.out.println("贝斯:"+bassPattern.toString());
			System.out.println("鼓点:"+rhythmPattern.toString());
			
			//加入各个乐器轨
			song.add(melodyPattern);
			song.add(guitarPattern);
			song.add(bassPattern);
			song.add(rhythmPattern);

			//System.out.println(song.toString());
		}
		
		//播放音乐
		public void PlaySong()
		{
			Player player1=new Player();
			MusicPlayThread musicThread=new MusicPlayThread(player1,song);
			musicThread.start();
		}
		
		//保存音乐
		public void SaveSong()
		{
			//保存地址
			String saveurl="D://歌词音乐.mid";
			//保存
			MusicSaveThread musicUtil=new MusicSaveThread(song, saveurl);
			musicUtil.start();
		}
		
		//插入音乐
		public void InsertMusic(Pattern[] patterns)
		{
			melodyPattern.add(patterns[0]);
			bassPattern.add(patterns[1]);
			guitarPattern.add(patterns[2]);
			rhythmPattern.add(patterns[3]);
		}
		
		
		// 拼接歌词
		public void addGechi(String gechi) {
			
			MyGeChi+=gechi+"|";
		}
		
		// 展示歌词
		public void ShowGechi() {
			System.out.println(MyGeChi);
		}

}
