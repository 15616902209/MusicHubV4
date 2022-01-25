package com.sachzhong.song;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.service.MusicGenerateService;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHub
*  版本： 1.0
*  创建时间：2020年1月13日 下午12:02:58
*  类名：MusicSong.java
*  类说明：
*/
public class GenerateChorusSong {
	
	
	
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
	
	//构建和弦走向
	//436251
	private String[] chordpath=new String[]{"F","G","E","A","D","G","C"};
	
	//音域
	private int yinyu;

	//如果是120 表示每分钟120拍 ，一拍0.5秒，4/4拍就是  4分音符做一拍， 一小节四拍，就是每分钟30小节
	private int speed=120;
	
	//歌曲时间 以秒为单位  一般歌曲3分钟  就是 180秒
	private int songTime=180;
	
	//歌曲的形式   Intro -> Verse ->  Pre-Chorus ->  Chorus ->  Re-Intro -> 
	//pre-Chorus ->  Chorus ->  Bridge ->  Chorus -> Outro
	
	private final String[] songStyle=new  String[] 
			{"前奏","主题","副歌前","副歌","重复前奏","副歌前","副歌","间奏","副歌","结尾"};
	
	//音乐生成工具
	private MusicGenerateService musicGenerate=new MusicGenerateService();
	
	
	public GenerateChorusSong()
	{
				//总共有10个部分 按3分钟算 每个部分就是 18秒;
				//int time=songTime/songStyle.length;
				//18秒 如果按 4/4拍，120 来算 就能生成  36 拍   9小节
				//float paimiao=speed/60;
				//int pai=(int)paimiao*time;
				//int jie=(int)pai/4;
				//System.out.println("paimiao:"+paimiao+"pai:"+pai+"jie:"+jie);

	}
	
	public GenerateChorusSong(int speed) {
		super();
		this.speed = speed;
	}

	public GenerateChorusSong(int speed, int songTime) {
		super();
		this.speed = speed;
		this.songTime = songTime;
	}
	
	//前奏
	private Pattern[] Intro=new Pattern[4];
	
	//生成前奏  根据和弦走向的根音生成n节yinyu的音符
	public void generateIntro(String[] chordPath,int yinyu,int n)
	{
		//Intro=musicGenerate.generateXiaoJie(chordRoot, yinyu,3);
		Intro=musicGenerate.generateMusic(chordPath, yinyu,n);
	}
	
	//主题
	private Pattern[] Verse=new Pattern[4];
				
	//生成主题
	public void generateVerse(String[] chordPath,int yinyu,int n)
	{
		Verse=musicGenerate.generateMusic(chordPath, yinyu,n);		
	}
	
	//副歌前
	private Pattern[] PreChorus=new Pattern[4];
		
	//生成副歌前
	public void generatePreChorus(String[] chordPath,int yinyu,int n)
	{
		PreChorus=musicGenerate.generateMusic(chordPath, yinyu,n);			
	}
	
	//副歌
	private Pattern[] Chorus=new Pattern[4];
			
	//生成副歌
	public void generateChorus(String[] chordPath,int yinyu,int n)
	{
		Chorus=musicGenerate.generateMusic(chordPath, yinyu,n);					
	}
	
	//间奏
	private Pattern[] Bridge=new Pattern[4];
				
	//生成间奏
	public void generateBridge(String[] chordPath,int yinyu,int n)
	{
		Bridge=musicGenerate.generateMusic(chordPath, yinyu,n);							
	}	
	
	//结尾
	private Pattern[] Outro=new Pattern[4];
					
	//生成结尾
	public void generateOutro(String[] chordPath,int yinyu,int n)
	{
		Outro=musicGenerate.generateMusic(chordPath, yinyu,n);							
	}
	
	
	//生成音乐
	public void  generateSong()
	{
		//先清空主音轨
		song.clear();
		
		//设置乐器
		//旋律
		melodyPattern.setVoice(0).setInstrument("Piano").setTempo(speed);
		//贝斯
		bassPattern.setVoice(1).setInstrument(Instruments.Acoustic_Bass).setTempo(speed);
		//吉他
		guitarPattern.setVoice(2).setInstrument(Instruments.ELECTRIC_JAZZ_GUITAR).setTempo(speed);	
		//鼓点
		rhythmPattern.setTempo(speed);
		
		
		//歌曲的形式   Intro -> Verse ->  Pre-Chorus ->  Chorus ->  Re-Intro -> 
		//pre-Chorus ->  Chorus ->  Bridge ->  Chorus -> Outro
		
		//{"前奏","主题","副歌前","副歌","重复前奏","副歌前","副歌","间奏","副歌","结尾"};
		
		//插入各个部分
		this.InsertMusic(this.Intro);
		this.InsertMusic(this.Verse);
		this.InsertMusic(this.PreChorus);
		this.InsertMusic(this.Chorus);
		this.InsertMusic(this.Intro);
		this.InsertMusic(this.PreChorus);
		this.InsertMusic(this.Chorus);
		this.InsertMusic(this.Bridge);
		this.InsertMusic(this.Chorus);
		this.InsertMusic(this.Outro);
		
		
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
		String  saveurl="D://automusicsong.mid";
		//保存
		MusicSaveThread musicSaveThread=new MusicSaveThread(song, saveurl);
		musicSaveThread.start();
	}
	
	//插入音乐
	public void InsertMusic(Pattern[] patterns)
	{
		melodyPattern.add(patterns[0]);
		bassPattern.add(patterns[1]);
		guitarPattern.add(patterns[2]);
		rhythmPattern.add(patterns[3]);
	}
	
	
	/*
		下面是各种属性的  get  set
	*/
	
	public int getSpeed() {
		return speed;
	}

	public Pattern getSong() {
		return song;
	}

	public void setSong(Pattern song) {
		this.song = song;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSongTime() {
		return songTime;
	}

	public void setSongTime(int songTime) {
		this.songTime = songTime;
	}


	public String[] getSongStyle() {
		return songStyle;
	}

	public String[] getChordpath() {
		return chordpath;
	}

	public void setChordpath(String[] chordpath) {
		this.chordpath = chordpath;
	}

	public int getYinyu() {
		return yinyu;
	}

	public void setYinyu(int yinyu) {
		this.yinyu = yinyu;
	}


	
	
	
}
