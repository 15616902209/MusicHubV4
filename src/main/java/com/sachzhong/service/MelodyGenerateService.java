package com.sachzhong.service;

import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

import java.util.ArrayList;
import java.util.List;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHubV2
*  版本： 1.0
*  创建时间：2020年1月23日 下午12:12:43
*  类名：MelodyGenerateUtil.java
*  类说明：旋律生成工具
*/
public class MelodyGenerateService {
	
	//音符生成工具类
	private NoteGenerateService noteGenerateService =new NoteGenerateService();
	
	
	//音乐拍节奏生成工具类
	private MusicPaiService musicPai=new MusicPaiService();
	

	//根据根音和音域随机获取理查德式旋律
	public Pattern  getMelodyBylichade(String chordRoot,int yinyu)
	{
		//旋律存储类
		Pattern pattern=new Pattern();
		//加入传入的是C,5 那返回 C5 E5 G5 B5 
		List<String> nodeList=new ArrayList<String>();
	
		nodeList= noteGenerateService.getChordList(chordRoot, yinyu);
		
		//开始生成理查德式音符列表
		
		//先将第2个音符提高八度，
		String node=nodeList.get(1);
		//System.out.println("oldnode:"+node+"String.valueOf((yinyu+1):"+String.valueOf(yinyu+1));
		//如果先匹配到音域+1，的就+2
		node=node.replaceAll(String.valueOf((yinyu+1)), String.valueOf((yinyu+2)));
		//后面再匹配当前音域
		node=node.replaceAll(String.valueOf(yinyu),String.valueOf((yinyu+1)));
		
		nodeList.set(1, node);
		///System.out.println("newnode:"+node);

		//然后采用 0，2，1，2，   1，2，1，2 
		int[] relu=new int[] {0,2,1,2,1,2,1,2};
		
		//八个八分音符
		for (int i = 0; i < relu.length; i++) {
			
			String mynode=nodeList.get(relu[i])+"i";
			pattern.add(mynode);
		}
				
		return pattern;
	} 

	//根据和弦和音域随机获取理查德式旋律
	public Pattern  getMelodyBylichade(Chord chord,int yinyu)
	{

		Pattern pattern=new Pattern();
		
		//加入传入的是C,5 那返回 C5 E5 G5 B5 
		Note[] nodeArr=chord.getNotes();
		
		//开始生成理查德式音符列表
		
		//先将第2个音符提高八度，
		String node=nodeArr[1].toString();
		//System.out.println("oldnode:"+node+"String.valueOf((yinyu+1):"+String.valueOf(yinyu+1));
		//如果先匹配到音域+1，的就+2
		//node=node.replaceAll(String.valueOf((yinyu+1)), String.valueOf((yinyu+2)));
		//后面再匹配当前音域
		//node=node.replaceAll(String.valueOf(yinyu),String.valueOf((yinyu+1)));
		
		nodeArr[1]=new Note(node);
		///System.out.println("newnode:"+node);

		//然后采用 0，2，1，2，   1，2，1，2 
		int[] relu=new int[] {0,2,1,2,1,2,1,2};
		
		//八个八分音符
		for (int i = 0; i < 8; i++) {
			
			String mynode=nodeArr[relu[i]]+"i";
			pattern.add(mynode);
		}
				
		return pattern;
	} 
	
	
		//根据和弦和音域随机获取旋律
		public Pattern  getMelody(Chord chord,int yinyu)
		{

			Pattern pattern=new Pattern();
			
			//加入传入的是C,5 那返回 C5 E5 G5 B5 
			Note[] nodeArr=chord.getNotes();
			
			//开始生成理查德式音符列表
			
			//先将第2个音符提高八度，
			String node=nodeArr[1].toString();
			//System.out.println("oldnode:"+node+"String.valueOf((yinyu+1):"+String.valueOf(yinyu+1));
			//如果先匹配到音域+1，的就+2
			//node=node.replaceAll(String.valueOf((yinyu+1)), String.valueOf((yinyu+2)));
			//后面再匹配当前音域
			//node=node.replaceAll(String.valueOf(yinyu),String.valueOf((yinyu+1)));
			
			nodeArr[1]=new Note(node);
			///System.out.println("newnode:"+node);

			//然后采用 0，1，2，0， 0，2，0，2 
			int[] relu=new int[] {0,2,1,2,1,2,1,2};
			
			//八个八分音符
			for (int i = 0; i < 8; i++) {
				
				String mynode=nodeArr[relu[i]]+"i";
				pattern.add(mynode);
			}
					
			return pattern;
		} 
	
	

	//随机生成1小节的4/4拍旋律
	public Pattern generateMelody(String chordRoot,int yinyu)
	{
			Pattern pattern=new Pattern();
			
			String node="";
			
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
				
				pattern.add(node);
			}
		return pattern;
	}

	//随机生成1小节的全部为8分音符的旋律
	public Pattern generateMelodyAll8(String chordRoot,int yinyu)
	{
		Pattern pattern=new Pattern(); 	
		String node="";
		
			//获取4/4拍中的音符节奏
			List<String> jiezou=musicPai.getPaiAll8List();
			
			
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
				
				pattern.add(node);
			}
		return pattern;
	}

	//随机生成N小节的旋律
	public Pattern generateMelody(String chordRoot,int yinyu,int n)
	{
		Pattern pattern=new Pattern(); 	
		String node="";
		//生成n小节
		for (int i = 0; i < n; i++) {
			
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
				
				pattern.add(node);
			}
			
			pattern.add("|");
		}
		
		return pattern;
	}
}
