package com.sachzhong.service;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;

import java.util.List;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年1月23日 下午12:19:32
*  类名：MusicGenerateUtil.java
*  类说明：音乐生成工具类
*/
public class MusicGenerateService {
	
	//总轨
	private  Pattern[] patterns=new Pattern[4];
    //旋律音轨
	private  Pattern melodyPattern=new Pattern();
	//贝斯音轨
	private  Pattern bassPattern=new Pattern();
	//吉他音轨
	private  Pattern guitarPattern=new Pattern();
	//鼓点音轨
	private  Pattern rhythmPattern=new Pattern();
	//鼓点节奏
	private  Rhythm rhythm=new Rhythm();
	//鼓点
	private String[] rhythmArr=new String[] {"O","`","S","X"};
	//存放鼓点的字符串
	private String rhythmStr="";

	//音符变量
	private String node="C";
	//音符生成工具
	private NoteGenerateService noteGenerateService =new NoteGenerateService();
	//旋律生成工具
	private MelodyGenerateService melodyGenerateService =new MelodyGenerateService();
	//节奏拍生成工具
	private MusicPaiService musicPai = new MusicPaiService();
	
	//根据根音随机生成N小节的音乐
	public Pattern[] generateMusic(String chordRoot,int yinyu,int n)
	{
		//生成8小节
		for (int i = 0; i < n; i++) {
			
			//增加一个和弦音 一般用于bass 低音
			bassPattern.add(chordRoot+(yinyu-2)+"w");
			
			//获取4/4拍中的音符节奏
			List<String> jiezou=musicPai.getPai44();
			
			
			//根据pai生成的个数来生成音符
			for (int j = 0; j < jiezou.size(); j++) {
				
				if(j==0)
				{
					//先把根音加入到列表
					node=chordRoot+yinyu+jiezou.get(j);
				}
				else
				{
					//根据和弦根音随机获取音符
					node= noteGenerateService.getNode(chordRoot, yinyu);
					node+=jiezou.get(j);
				}
				
				melodyPattern.add(node);
				
				//吉他音轨随机获取音符
				//根据和弦根音随机获取音符
				node= noteGenerateService.getNode(chordRoot, yinyu-1);
				node+=jiezou.get(j);
				guitarPattern.add(node);
				
				//随机生成 鼓点
				int index = (int)(Math.random()*rhythmArr.length);
				//随机加入鼓点
				rhythmStr+=rhythmArr[index];
			}
			
			melodyPattern.add("|");
			bassPattern.add("|");
			guitarPattern.add("|");
			rhythmStr+=".";
		}
		
		//加入鼓点
		rhythm.addLayer(rhythmStr);
		rhythmPattern.add(rhythm.getPattern());
		
		patterns[0]=melodyPattern;
		patterns[1]=bassPattern;
		patterns[2]=guitarPattern;
		patterns[3]=rhythmPattern;
		
		return patterns;
	}

	//根据和弦走向生成N小节的音符
	public Pattern[] generateMusic(String[] chordPath,int yinyu,int n)
	{		
		initClear();
		String chordRoot="C";
		for (int k = 0; k < chordPath.length; k++) {
			
			//和弦走向的根音
			chordRoot=chordPath[k];
			
			//生成n小节
			for (int i = 0; i < n; i++) {
				
				//增加一个和弦音 一般用于bass 低音
				bassPattern.add(chordRoot+(yinyu-2)+"w");
				
				//获取4/4拍中的音符节奏
				List<String> jiezou=musicPai.getPai44();
				
				
				//根据pai生成的个数来生成音符
				for (int j = 0; j < jiezou.size(); j++) {
					
					if(j==0)
					{
						//先把根音加入到列表
						node=chordRoot+yinyu+jiezou.get(j);
					}
					else
					{
						//根据和弦根音随机获取音符
						node= noteGenerateService.getNode(chordRoot, yinyu-1);
						node+=jiezou.get(j);
					}
					
					melodyPattern.add(node);
					
					//吉他音轨随机获取音符
					//根据和弦根音随机获取音符
					node= noteGenerateService.getNode(chordRoot, yinyu);
					node+=jiezou.get(j);
					guitarPattern.add(node);
					
					//随机生成 鼓点
					int index = (int)(Math.random()*rhythmArr.length);
					//随机加入鼓点
					rhythmStr+=rhythmArr[index];
				}
				
				melodyPattern.add("|");
				bassPattern.add("|");
				guitarPattern.add("|");
				rhythmStr+=".";
			}

		}

		//加入鼓点
		rhythm.addLayer(rhythmStr);
		rhythmPattern.add(rhythm.getPattern());
		
		patterns[0]=melodyPattern;
		patterns[1]=bassPattern;
		patterns[2]=guitarPattern;
		patterns[3]=rhythmPattern;
		
		return patterns;
	}
	
	//根据和弦走向生成N小节的音符
	public  Pattern[] generateChordProgression(String chordProgression,String jidiao,int yinyu,int n)
	{
		 //和弦走向
		// ChordProgression cp= new ChordProgression("IV V III VI II V I");
		 ChordProgression cp=new ChordProgression(chordProgression).setKey(jidiao+yinyu); 
		 //根据基调生成和弦数组
			Chord[] chords=cp.getChords();
		
			Pattern[] patterns=new Pattern[4];
		
		   //旋律音轨
		    Pattern melodyPattern=new Pattern();
				//贝斯音轨
			Pattern bassPattern=new Pattern();
				//吉他音轨
			Pattern guitarPattern=new Pattern();
				//鼓点音轨
			Pattern rhythmPattern=new Pattern();
				//鼓点节奏
			Rhythm rhythm =new Rhythm();
			String[] rhythmArr=new String[] {"O","`","S","X"};
			String rhythmStr="";
		
		String node="C";
		String chordRoot="C";
		//生成n小节
		for (int i = 0; i < n; i++) {
			
			for (int k = 0; k < chords.length; k++) {
				
				//和弦
				Chord chord=chords[k];
				//和弦走向的根音
				chordRoot=chord.getRoot().toString();
					
					
				
					//增加一个和弦音 一般用于bass 低音 //把bass的音域减2
					String bassnote=chordRoot;
					bassnote=bassnote.replaceAll(String.valueOf(yinyu),String.valueOf(yinyu-1));
					bassnote=bassnote.replaceAll(String.valueOf(yinyu+1),String.valueOf(yinyu-1));
					bassnote+="q ";
					bassnote+="Rq "+bassnote+"Rq ";
					bassPattern.add(bassnote);
					
					//获取4/4拍中的音符节奏
					//List<String> jiezou=musicPai.getAllPai();
					List<String> jiezou=musicPai.getPai44();
					
					//根据pai生成的个数来生成音符
					for (int j = 0; j < jiezou.size(); j++) {
						
						if(j==0)
						{
							//先把根音加入到列表
							node=chordRoot+jiezou.get(j);
						}
						else
						{
							//根据和弦根音随机获取音符
							node= noteGenerateService.getChordNote(chord).toString();
							node+=jiezou.get(j);
						}
						
						melodyPattern.add(node);
						
						//吉他音轨随机获取音符
						//根据和弦根音随机获取音符
						//node=this.getChordNote(chord).toString();
						//把吉他的音域加1
						//node=node.replaceAll(String.valueOf(yinyu),String.valueOf(yinyu+1));
						//增加音程
						//node+=jiezou.get(j);
						//guitarPattern.add(node);
						
						
						
						//随机生成 鼓点
						int index = (int)(Math.random()*rhythmArr.length);
						//随机加入鼓点
						rhythmStr+=rhythmArr[index];
					}
					
					
					guitarPattern.add(melodyGenerateService.getMelodyBylichade(chord, yinyu)+"|");
					
					melodyPattern.add("|");
					bassPattern.add("|");
					rhythmStr+=".";
				}

			
		}

		//加入鼓点
		rhythm.addLayer(rhythmStr);
		rhythmPattern.add(rhythm.getPattern());
		
		patterns[0]=melodyPattern;
		patterns[1]=bassPattern;
		patterns[2]=guitarPattern;
		patterns[3]=rhythmPattern;
		
		return patterns;
	}

	// 根据歌词和音域生成的中国风音乐
	public Pattern[] getSongChina(String mygechi, int yinyu) {
		    String[] gechi=mygechi.split(" ");
			//初始化数据
			Pattern[] patterns=new Pattern[4];		
			Pattern melodyPattern=new Pattern();
			Pattern bassPattern=new Pattern();
			Pattern guitarPattern=new Pattern();
			Pattern rhythmPattern=new Pattern();
			
			Rhythm rhythm =new Rhythm();
			String rhythmStr="";
			// 遍历歌词列表 取出每一句
			String ju = "";
			String node = "";
			for (int i = 0; i < gechi.length; i++) {

				ju = gechi[i];
				int length = ju.length();
				String[] pai = musicPai.getPai44(length);
				//System.out.println(pai.length);
				
				for (int j = 0; j < pai.length; j++) {

					node = noteGenerateService.getChinaNote();
					//System.out.println("node1:"+node);
					if (j == 0) {
						String firstnode = new String(node);
						String midnote = "";

						// 贝斯音轨
						// 获取第一个音符当低音
						midnote = firstnode + (yinyu - 2) + "w";
						
						bassPattern.add(midnote);
						
						// 吉他音轨随机获取音符
						// 根据和弦根音随机获取音符
						// node=musicGenerate.getNode(mynode, yinyu-1);
						// node+=pai[j];

						// midnote=musicGenerate.generateXiaoJie(firstnode,yinyu-1).toString();
						midnote = melodyGenerateService.generateMelodyAll8(firstnode, yinyu - 1).toString();
						// midnote=musicGenerate.getNodeListBylichade(firstnode,yinyu-1).toString();
						guitarPattern.add(midnote);
						
						//System.out.println("node2:"+node);
					}
				
					//增加音程
					node += yinyu + pai[j];
					//增加力度
					//int lidu = (int)(Math.random()*20+80);
					//node += lidu;
					// System.out.println("node3:"+node);
					// 增加旋律
					melodyPattern.add(node);

					// 随机生成 鼓点
					int index = (int) (Math.random() * rhythmArr.length);
					// 随机加入鼓点
					rhythmStr += rhythmArr[index];

				}
				melodyPattern.add("|");
				bassPattern.add("|");
				guitarPattern.add("|");
				rhythmStr += ".";
				
			}
			
			// 加入鼓点
			rhythm.addLayer(rhythmStr);
			rhythmPattern.add(rhythm.getPattern());
			patterns[0] = melodyPattern;
			patterns[1] = bassPattern;
			patterns[2] = guitarPattern;
			patterns[3] = rhythmPattern;
		
			return patterns;
		}

	// 根据歌词和弦根音和音域生成的中国风音乐
	public Pattern[] getSongChina(String chordRoot,String[] gechi,int yinyu) {
				
				//初始化数据
				Pattern[] patterns=new Pattern[4];		
				Pattern melodyPattern=new Pattern();
				Pattern bassPattern=new Pattern();
				Pattern guitarPattern=new Pattern();
				Pattern rhythmPattern=new Pattern();
				
				Rhythm rhythm =new Rhythm();
				String rhythmStr="";
				// 遍历歌词列表 取出每一句
				String ju = "";
				String node = "";
				for (int i = 0; i < gechi.length; i++) {

					ju = gechi[i];
					int length = ju.length();
					String[] pai = musicPai.getPaiAll8();
					//System.out.println(pai.length);
					
					String[]  mynodes= noteGenerateService.getChinaNote8(length);
					
					for (int j = 0; j < pai.length; j++) {

						node =mynodes[j];
						//System.out.println("node1:"+node);
						if (j == 0) {
							String firstnode = new String(node);
							String midnote = "";

							// 贝斯音轨
							// 获取第一个音符当低音
							midnote = firstnode + (yinyu - 2) + "w";
							
							bassPattern.add(midnote);
							
							// 吉他音轨随机获取音符
							// 根据和弦根音随机获取音符
							// node=musicGenerate.getNode(mynode, yinyu-1);
							// node+=pai[j];

							// midnote=musicGenerate.generateXiaoJie(firstnode,yinyu-1).toString();
							//midnote = this.generateXiaoJieAll8(firstnode, yinyu - 1).toString();
							// midnote=musicGenerate.getNodeListBylichade(firstnode,yinyu-1).toString();
							//guitarPattern.add(midnote);
							
							//System.out.println("node2:"+node);
						}
					
						//增加音程
						node += yinyu + pai[j];
						//增加力度
						//int lidu = (int)(Math.random()*20+80);
						//node += lidu;
						// System.out.println("node3:"+node);
						// 增加旋律
						melodyPattern.add(node);

						// 随机生成 鼓点
						int index = (int) (Math.random() * rhythmArr.length);
						// 随机加入鼓点
						rhythmStr += rhythmArr[index];

					}
					melodyPattern.add("|");
					bassPattern.add("|");
					guitarPattern.add("|");
					rhythmStr += ".";
					
				}
				
				// 加入鼓点
				rhythm.addLayer(rhythmStr);
				rhythmPattern.add(rhythm.getPattern());
				patterns[0] = melodyPattern;
				patterns[1] = bassPattern;
				patterns[2] = guitarPattern;
				patterns[3] = rhythmPattern;
			
				return patterns;
	}
	
	//初始化各个属性
	public void initClear()
	{
		//总轨
		this.patterns=new Pattern[4];
	    //旋律音轨
		this.melodyPattern=new Pattern();
		//贝斯音轨
		this.bassPattern=new Pattern();
		//吉他音轨
		this.guitarPattern=new Pattern();
		//鼓点音轨
		this.rhythmPattern=new Pattern();
		//鼓点节奏
		this.rhythm=new Rhythm();
		//存放鼓点的字符串
		this.rhythmStr="";
		//音符变量
		this.node="C";
	}
}

