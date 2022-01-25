package com.sachzhong.service;

import java.util.ArrayList;
import java.util.List;

/**
*  作者： SachZhong 钟盛勤
*  项目名：MusicHub
*  版本： 1.0
*  创建时间：2020年1月12日 下午9:33:39
*  类名：Du.java
*  类说明：
*/

/*
4.名词理论 

4.1 音符 
note 音符 
rest 休止符 
chord 和弦 

jfugue里用MusicString来表示曲谱。 
CDEFGAB分别代表：哆来咪发索拉西 
R代表 ：休止符 
sharp 升音符  用#表示 
flat 降音符  用b表示 

4.2 音阶 
octave 八度音阶 

4.3 音长 
duration 音长 
whole	w 整体
half	h 半音
quarter	q 四分音符
eighth	i 八分音符
sixteenth	s 16分音符
thirty-second	t 3-2
sixty-fourth	x 6-4
one-twenty-eighth	o 1-12-8

默认为 Q(quartar)， 表示四分之一符，
还有 W(whole)，全音符，
H(half)，半音符。
其他如：1/8("i")、1/16("s")、1/32("t")、1/64("x")和1/128音值("n")。
您能够通过在音值字符后面加上一个点(".")来表示一个点音值。

4.5 乐器 
instrument 乐器 
默认: Piano 

*/
public class MusicPaiService {
	
	//获取4/4拍能组成的一拍，，以16分音符和八分音符为主
		public List<String> getPai44By16()
		{
			//组成拍的列表
			List<String> pai=new ArrayList<String>();
			
			//一个4/4拍，最多组成 全部由八分音符 就是一拍八个音符,最低4个音符 全是四分音符
			
			//假设一拍的总量为160
			int paiCount=160;
			
			while(paiCount>0)
			{
				//等于10的时候只能上八分音符
				if(paiCount==10)
				{
						//生成八分音符
						pai.add("s");
						//减去8分之一
						paiCount-=10;
				}
				else
				{
					//随机生成 16分音符和8分音符
					int index = (int)(Math.random()*2);
					//System.out.println("index:"+index);
					if(index==0)
					{
						if(paiCount>0)
						{
							//如果随机数为0，生成八分音符
							pai.add("i");
							//减去8分之一
							paiCount-=20;
						}
						
					}
					else
					{
						if(paiCount>0)
						{
							//如果随机数为1，生成4分音符
							pai.add("s");
							//减去8分之一
							paiCount-=10;
						}
					}
				}
				
				
			}
			
//			for (int i = 0; i < pai.size(); i++) {
//				System.out.println("pai:"+pai.get(i));
//			}
			
			
			return pai;
		}
	
	
	//获取4/4拍能组成的一拍，，以四分音符和八分音符为主
	public List<String> getPai44()
	{
		//组成拍的列表
		List<String> pai=new ArrayList<String>();
		
		//一个4/4拍，最多组成 全部由八分音符 就是一拍八个音符,最低4个音符 全是四分音符
		
		//假设一拍的总量为160
		int paiCount=160;
		
		while(paiCount>0)
		{
			//等于20的时候只能上八分音符
			if(paiCount==20)
			{
					//生成八分音符
					pai.add("i");
					//减去8分之一
					paiCount-=20;
			}
			else
			{
				//随机生成 4分音符和8分音符
				int index = (int)(Math.random()*2);
				//System.out.println("index:"+index);
				if(index==0)
				{
					if(paiCount>0)
					{
						//如果随机数为0，生成八分音符
						pai.add("i");
						//减去8分之一
						paiCount-=20;
					}
					
				}
				else
				{
					if(paiCount>0)
					{
						//如果随机数为1，生成4分音符
						pai.add("q");
						//减去8分之一
						paiCount-=40;
					}
				}
			}
			
			
		}
		
//		for (int i = 0; i < pai.size(); i++) {
//			System.out.println("pai:"+pai.get(i));
//		}
		
		
		return pai;
	}
	
	
		//根据输入的长度获取这么多音符 length>0 length<=8
		public String[] getPai44(int length)
		{
			//组成拍的列表
			String[] pai=new String[length];
			
			
			//一个4/4拍，最多组成 全部由八分音符 就是一拍八个音符,最低4个音符 全是四分音符
				if(length<0||length>8)
				{
					//直接跳出循环
					return pai;
					
				}
				else
				{
					if(length==1)
					{
						//如果只返回一个 那只能是w
						pai[0]="w";
					}
					else if(length==2)
					{
						//如果只返回2个 那只能是 h,h
						pai[0]="h";
						pai[1]="h";
					}
					else if(length==3)
					{
						//如果只返回3个 那只能是 h q q, q h q, q q h。
					    //第一次随机取出里面的一个值
						int index = (int)(Math.random()*3);
						//先把h放到这个位置
						pai[index]="h";
						//那剩下的位置肯定是
						int one=(index+1)%3;
						pai[one]="q";
						int two=(index+2)%3;
						pai[two]="q";
					}
					else if(length==4)
					{
						//如果只返回4个 那只能是 q q q q
						
						for (int i = 0; i < pai.length; i++) {
							pai[i]="q";
						}
						
					}
					else if(length==5)
					{
						//如果只返回5个 那只能是 q q q i i
						//第一次随机取出里面的一个值
						int oldindex = (int)(Math.random()*5);
						//先把i放到这个位置
						pai[oldindex]="i";
						int newindex=oldindex;
						
						while(newindex==oldindex)
						{
							newindex = (int)(Math.random()*5);
						}
						//先把i放到新位置
						pai[newindex]="i";
						//如果i不是老位置，又不是新位置，那就放q
						//如果index不是老位置，又不是新位置，那就放i
						for (int index = 0; index < 5; index++) {
							
							if(index!=oldindex&&index!=newindex)
							{
								pai[index]="q";
							}
							
						}
	
					}
					else if(length==6)
					{
						//如果只返回6个 那只能是 q q i i i i
						//第一次随机取出里面的一个值
						int oldindex = (int)(Math.random()*6);
						//先把q放到这个位置
						pai[oldindex]="q";
						
						int newindex=oldindex;
						
						while(newindex==oldindex)
						{
							newindex = (int)(Math.random()*6);
						}
						//先把q放到新位置
						pai[newindex]="q";
						//如果index不是老位置，又不是新位置，那就放i
						for (int index = 0; index < 6; index++) {
							
							if(index!=oldindex&&index!=newindex)
							{
								pai[index]="i";
							}
							
						}
						
					}
					else if(length==7)
					{
						//如果只返回7个 那只能是 q i i i i i i
						//第一次随机取出里面的一个值
						int oldindex = (int)(Math.random()*7);
						//先把q放到这个位置
						pai[oldindex]="q";
						//如果index不是老位置，那就放i
						for (int index = 0; index < 7; index++) {
							
							if(index!=oldindex)
							{
								pai[index]="i";
							}
							
						}
						
					}
					else if(length==8)
					{
						//如果只返回8个 那只能是 i i i i i i i i
						//第一次随机取出里面的一个值
						//如果index不是老位置，那就放i
						for (int index = 0; index < 8; index++) {
								pai[index]="i";
						}
					}
					
				}


			
//			for (int i = 0; i < pai.length; i++) {
//				System.out.println("pai:"+pai[i]);
//			}
			
			return pai;
		}
	

	
	    //获取4/4拍能组成的一拍，，以四分音符和八分音符为主
		public List<String> getPai()
		{
			//组成拍的列表
			List<String> pai=new ArrayList<String>();
			
			//一个4/4拍，最多组成 全部由八分音符 就是一拍八个音符,最低4个音符 全是四分音符
			
			//假设一拍的总量为160
			int paiCount=160;
			
			while(paiCount>0)
			{		
				//等于20的时候只能上八分音符
				if(paiCount==20)
				{
						//生成八分音符
						pai.add("i");
						//减去8分之一
						paiCount-=20;
				}
				else
				{
					//随机生成 4分音符和8分音符和半音
					int index = (int)(Math.random()*3);
					//System.out.println("index:"+index);
					if(index==0)
					{
						if(paiCount>0)
						{
							//如果随机数为0，生成八分音符
							pai.add("i");
							//减去8分之一
							paiCount-=20;
						}
						
					}
					else if(index==1)
					{
						//只有大于等于40的时候才能减
						if(paiCount>=40)
						{
							//如果随机数为1，生成4分音符
							pai.add("q");
							//减去8分之一
							paiCount-=40;
						}
					}
					else if(index==2)
					{
						//只有大于等于80的时候才能减
						if(paiCount>=80)
						{
							//如果随机数为1，生成4分音符
							pai.add("h");
							//减去一半
							paiCount-=80;
						}
					}
				}
				
				
			}
			
//			for (int i = 0; i < pai.size(); i++) {
//				System.out.println("pai:"+pai.get(i));
//			}
			
			
			return pai;
		}
		
		
		
		
		 //获取4/4拍能组成的一拍，，以八分音符为主
		public List<String> getPaiAll8List()
		{
			//组成拍的列表
			List<String> pai=new ArrayList<String>();
			
			//一个4/4拍，最多组成 全部由八分音符 就是一拍八个音符,最低4个音符 全是四分音符
			
			for (int i = 0; i < 8; i++) {
				pai.add("i");
			}

//			for (int i = 0; i < pai.size(); i++) {
//				System.out.println("pai:"+pai.get(i));
//			}

			return pai;
		}
		
		
		 //获取4/4拍能组成的一拍，，以八分音符为主
		public String[] getPaiAll8()
		{
			//组成拍的列表
			String[] pai=new String[8];
			
			//一个4/4拍，最多组成 全部由八分音符 就是一拍八个音符,最低4个音符 全是四分音符
			
			for (int i = 0; i < 8; i++) {
				pai[i]="i";
			}

//			for (int i = 0; i < pai.size(); i++) {
//				System.out.println("pai:"+pai.get(i));
//			}

			return pai;
		}
		
		public List<String> getAllPai()
		{
			//组成拍的列表
			List<String> pai=new ArrayList<String>();
			
			//一个4/4拍，最多组成 全部由八分音符 就是一拍八个音符,最低4个音符 全是四分音符
			
			//假设一拍的总量为160
			int paiCount=160;
			
			while(paiCount>0)
			{		
				//等于20的时候只能上八分音符
				if(paiCount==20)
				{
						//生成八分音符
						pai.add("i");
						//减去8分之一
						paiCount-=20;
				}
				else
				{
					//随机生成 4分音符和8分音符和半音
					int index = (int)(Math.random()*4);
					//System.out.println("index:"+index);
					if(index==0)
					{
						if(paiCount>0)
						{
							//如果随机数为0，生成八分音符
							pai.add("i");
							//减去8分之一
							paiCount-=20;
						}
						
					}
					else if(index==1)
					{
						//只有大于等于40的时候才能减
						if(paiCount>=40)
						{
							//如果随机数为1，生成4分音符
							pai.add("q");
							//减去8分之一
							paiCount-=40;
						}
					}
					else if(index==2)
					{
						//只有大于等于80的时候才能减
						if(paiCount>=80)
						{
							//如果随机数为1，生成4分音符
							pai.add("h");
							//减去一半
							paiCount-=80;
						}
					}
					else if(index==3)
					{
						//只有大于等于160的时候才能减
						if(paiCount>=160)
						{
							//如果随机数为1，生成4分音符
							pai.add("w");
							//减去一半
							paiCount-=160;
						}
					}
				}
				
				
			}
			
//			for (int i = 0; i < pai.size(); i++) {
//				System.out.println("pai:"+pai.get(i));
//			}
			
			
			return pai;
		}
	
}
