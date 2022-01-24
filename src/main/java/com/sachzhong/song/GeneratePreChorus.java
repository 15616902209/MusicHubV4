package com.sachzhong.song;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import com.sachzhong.util.MusicGenerateUtil;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.ChordProgression;


/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHub
*  版本： 1.0
*  创建时间：2020年1月15日 下午6:11:22
*  类名：GeneratePreChorus.java
*  类说明：生成副歌前
*/
public class GeneratePreChorus {
	//歌曲大致流程{"前奏","主题","副歌前","副歌","重复前奏","副歌前","副歌","间奏","副歌","结尾"};
	
			//和弦走向
			private ChordProgression cp;
	
			//副歌前歌词
			private String gechi="";
			
			//副歌前音域
			private int yinyu=5;
			
			private int speed=120;
			
			//集合轨道 旋律 吉他 贝斯 节奏
			private Pattern[] PreChorus=new Pattern[4];
			
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
		
			public GeneratePreChorus(String gechi,int yinyu,int speed)
			{
				this.gechi=gechi;
				this.yinyu=yinyu;
				this.speed=speed;
			}
		
		
			
			//生成副歌前
			public void generatePreChorus()
			{
				MusicGenerateUtil musicGenerate = new MusicGenerateUtil();
				this.PreChorus=musicGenerate.getSongChina(this.gechi,this.yinyu);
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
				
				
				System.out.println("《《《《《《前奏》》》》》》》");
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
				String saveurl="D://preChorusMusic.mid";
				//保存
				MusicSaveThread musicUtil=new MusicSaveThread(song, saveurl);
				musicUtil.start();
			}



			
			//下面是各种get set
			public String getGechi() {
				return gechi;
			}



			public void setGechi(String gechi) {
				this.gechi = gechi;
			}



			public int getYinyu() {
				return yinyu;
			}



			public void setYinyu(int yinyu) {
				this.yinyu = yinyu;
			}



			public int getSpeed() {
				return speed;
			}



			public void setSpeed(int speed) {
				this.speed = speed;
			}



			public Pattern[] getPreChorus() {
				return PreChorus;
			}



			public void setPreChorus(Pattern[] PreChorus) {
				this.PreChorus = PreChorus;
			}



			public ChordProgression getCp() {
				return cp;
			}



			public void setCp(ChordProgression cp) {
				this.cp = cp;
			}
			
			
}
