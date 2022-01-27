package com.sachzhong.service;

import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： SachZhong 钟盛勤 项目名：MusicHubV2 版本： 1.0 创建时间：2020年1月23日 下午12:01:09
 * 类名：NoteGenerateUtil.java 类说明：音符生成工具
 */
public class NoteGenerateService {

	// 基本音阶
	private final String[] NodeArr = new String[] { "C", "D", "E", "F", "G", "A", "B" };

	// 中国五声音阶
	private final String[] ChinaNodeArr = new String[] { "C", "D", "E", "G", "A" };

	
	// 随机获取音符
	public String getNote() {
		// 随机生成 和弦内的音符
		int index = (int) (Math.random() * NodeArr.length);
		return NodeArr[index];
	}

	
	
	// 根据根音和音域随机获取一个音符
	public String getNode(String chordRoot, int yinyu) {
		List<String> chordEnList = this.getChordList(chordRoot, yinyu);

		// 获取长度
		int length = chordEnList.size();

		// 根据音符数组的长度随机获取下标
		int index = (int) (Math.random() * length);

		return chordEnList.get(index);
	}

	
	
	// 根据和弦随机获取和弦内的音符
	public Note getChordNote(Chord chord) {
		Note[] mynotes = chord.getNotes();
		// 随机生成 和弦内的音符
		int index = (int) (Math.random() * mynotes.length);
		return mynotes[index];
	}

	
	
	// 根据和弦随机获取和弦内的音符
	public String getNote(Chord chord) {
		Note[] mynotes = chord.getNotes();
		// 随机生成 和弦内的音符
		int index = (int) (Math.random() * mynotes.length);
		return mynotes[index].toString();
	}

	// 根据和弦随机获取和弦内的音符
	public String getRodomNote(Chord chord) {
		List<String> list = this.getNotes(chord);
		// 随机生成 和弦内的音符
		int index = (int) (Math.random() * list.size());
		return list.get(index);
	}
	
	//根据和弦获取能用的音符列表
	public List<String> getNotes(Chord chord)
	{
		List<String> NoteList = new ArrayList<String>();
		
		String chordRoot=chord.getRoot().toString();
		
		int yinyu = Integer.parseInt(chordRoot.substring(1, 2));
		
		chordRoot=chordRoot.substring(0, 1);
		
		NoteList=this.getChordLists(chordRoot,yinyu);
		
		Note[] notes=chord.getNotes();
		for (int i = 0; i < notes.length; i++) {
			NoteList.add(notes[i].toString());
		}
		
		return NoteList;
	}
	
	// 根据根音获取能使用的音符列表 输入 C 返回 C D B E G
	public List<String> getChordList(String chordRoot) {
		List<String> chordList = new ArrayList<String>();

		
		
		for (int i = 0; i < NodeArr.length; i++) {

			// 找到与根音匹配的那个，比如 chordRoot=C ,就找到C
			if (chordRoot.equals(NodeArr[i])) {
				// 先把根音加入到列表
				String node = NodeArr[i];
				chordList.add(node);

				int index = i;

				chordList.add(NodeArr[index + 1]);

				chordList.add(NodeArr[(index + 6) % 7]);

				// 循环2次
				for (int j = 0; j < 2; j++) {
					// 然后隔一个加一个
					index = index + 2;
					// System.out.println("index:"+index);
					if (index > 6) {
						// 回到音符数组的起点
						index = index % 7;
					}
					node = NodeArr[index];
					chordList.add(node);
				}
			}
		}
		return chordList;
	}
	
	
	// 根据根音获取能使用的音符列表 输入 C 返回 C D B E G
		public List<String> getChordLists(String chordRoot,int yinyu) {
			List<String> chordList = new ArrayList<String>();

			for (int i = 0; i < NodeArr.length; i++) {

				// 找到与根音匹配的那个，比如 chordRoot=C ,就找到C
				if (chordRoot.equals(NodeArr[i])) {
					// 先把根音加入到列表
					String node = NodeArr[i]+yinyu;
					chordList.add(node);

					int index = i;

					chordList.add(NodeArr[index + 1]+yinyu);

					chordList.add(NodeArr[(index + 6) % 7]+yinyu);

					// 循环2次
					for (int j = 0; j < 2; j++) {
						// 然后隔一个加一个
						index = index + 2;
						// System.out.println("index:"+index);
						if (index > 6) {
							// 回到音符数组的起点
							index = index % 7;
							// 音域也升一位
							yinyu += 1;
						}
						node = NodeArr[index]+yinyu;
						chordList.add(node);
					}
				}
			}
			return chordList;
		}

	
	// 获取音符根音和音域获取能使用的音符列表 输入 C 返回 C E G
	public List<String> getChordList(String chordRoot, int yinyu) {
		List<String> chordEnList=new ArrayList<String>();
	
		for (int i = 0; i < NodeArr.length; i++) {
			
			//找到与根音匹配的那个，比如 chordRoot=C ,就找到C
			if(chordRoot.equals(NodeArr[i]))
			{
				//先把根音加入到列表
				String node=NodeArr[i]+yinyu;
				chordEnList.add(node);
				
				int index=i;
				//循环3次
				for (int j = 0; j < 3; j++) {
					
					//然后隔一个加一个
					index=index+2;
					//System.out.println("index:"+index);
					if(index>6)
					{
						//回到音符数组的起点
						index=index%7;
						//音域也升一位
						yinyu+=1;
					}
					
					node=NodeArr[index]+yinyu;
					chordEnList.add(node);
				}
				

			}
			
		}
		
		return chordEnList;
	}

	
	
	// 随机获取中国的音符
	public String getChinaNote() {
		// 随机生成 和弦内的音符
		int index = (int) (Math.random() * ChinaNodeArr.length);
		return ChinaNodeArr[index];
	}

	
	
	// 随机获取n个中国音阶的八分音符 n<8
	public String[] getChinaNote8(int n) {
		String[] result = new String[8];

		int count = 0;

		for (int i = 0; i < result.length; i++) {

			// 随机生成 和弦内的音符
			int index = (int) (Math.random() * ChinaNodeArr.length);

			int flag = (int) (Math.random() * 2);

			// 如果放到了大于 倒数第n位
			if (i >= (8 - (count + 1))) {
				// 而音符还没放到这么多说明一直在放R 那不管flag为多少 都要放音符
				if (count < n) {
					flag = 1;
				}
			}

			// 如果已经放了n个音符了
			if (count >= n) {
				// 那就只能放R了
				flag = 0;
			}

			if (flag == 0) {
				result[i] = "R";
			} else {
				result[i] = ChinaNodeArr[index];
				count++;
			}

		}

		return result;
	}

	
	
	// 随机获取8个中国音阶的八个音符
	public String[] getChinaNote8() {
		String[] mynotes = new String[] { "C", "D", "E", "G", "A", "R" };
		String[] result = new String[8];
		// 随机生成 和弦内的音符
		int index = (int) (Math.random() * mynotes.length);

		for (int i = 0; i < result.length; i++) {
			result[i] = mynotes[index];
		}

		return result;
	}

	
	
	// 随机获取相差n个音符 或者生成爬音 或者生成降音
	public String[] getChinaNoteByn(String rootNode, int n, int yinyu) {
		int rootIndex = 0;

		// 找到根音的位置
		for (int i = 0; i < ChinaNodeArr.length; i++) {

			if (rootNode.equals(ChinaNodeArr[i])) {
				rootIndex = i;
			}

		}

		// System.out.println("rootIndex:"+rootIndex);

		String[] result = new String[n];

		int flag = (int) (Math.random() * 3);
		int index = 0;

		if (flag == 0) {

			for (int i = 0; i < n; i++) {

				if (i == 0) {
					// 第一个音肯定是根音
					result[i] = rootNode + yinyu;
				} else {
					// 第i个音
					index = (rootIndex + (2 * i)) % 5;
					result[i] = ChinaNodeArr[index] + yinyu;
				}

			}

		} else if (flag == 1) {
			// 爬音
			for (int i = 0; i < n; i++) {

				if (i == 0) {
					// 第一个音肯定是根音
					result[i] = rootNode + yinyu;
				} else {
					// 第i个音
					index = (rootIndex + i) % 5;
					if ((rootIndex + i) >= 5) {
						result[i] = ChinaNodeArr[index] + (yinyu + 1);
					} else {
						result[i] = ChinaNodeArr[index] + yinyu;
					}
				}

			}

		} else if (flag == 2) {
			// 降音

			for (int i = 0; i < n; i++) {

				if (i == 0) {
					// 第一个音肯定是根音
					result[i] = rootNode + yinyu;
				} else {
					// 第i个音
					index = Math.abs((rootIndex + (5 - i)) % 5);
					if ((rootIndex - i) < 0) {
						result[i] = ChinaNodeArr[index] + (yinyu - 1);
					} else {
						result[i] = ChinaNodeArr[index] + yinyu;
					}
				}

			}

		}

		return result;
	}
}
