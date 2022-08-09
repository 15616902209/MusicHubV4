package com.sachzhong.service;

import com.sachzhong.thread.MusicPlayThread;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;

/**
 * @author SachZhong
 * @date 2022/1/24 16:55
 * @info 节奏生成
 */
public class RhythmGenerateService {
    /**
     * 鼓点节奏
     */
    private Rhythm rhythm = new Rhythm();
    /**
     * //存放鼓点的字符串
     */
    private String rhythmStr = "";
    /**
     * 鼓点样式
     * this.put('.', "Ri"); 静音
     * this.put('O', "[BASS_DRUM]i"); 低音鼓 咚（重）
     * this.put('o', "Rs [BASS_DRUM]s");  低音鼓 咚
     * this.put('S', "[ACOUSTIC_SNARE]i"); 嗒（重）
     * this.put('s', "Rs [ACOUSTIC_SNARE]s"); 嗒
     * this.put('^', "[PEDAL_HI_HAT]i"); 脚踏高帽 嚓（重）
     * this.put('`', "[PEDAL_HI_HAT]s Rs"); 嚓
     * this.put('*', "[CRASH_CYMBAL_1]i"); 钹 仓（重）
     * this.put('+', "[CRASH_CYMBAL_1]s Rs"); 钹 仓
     * this.put('X', "[HAND_CLAP]i"); 鼓掌  啪（重）
     * this.put('x', "Rs [HAND_CLAP]s"); 鼓掌 啪
     * this.put(' ', "Ri"); 静音
     */
    private String[] rhythmArr = new String[]{"O", "o", "S", "s", "^", "`", "*", "+", "X", "x"};

    private String[] rhythmArr2 = new String[]{"O", "S", "^", "*", "X"};

    /**
     * 聆听鼓点
     */
    public  void listen(){
        Pattern  rhythmPattern = new Pattern();
        Rhythm rhythm = new Rhythm();

        for (int i = 0; i < rhythmArr.length; i++) {
            rhythmStr += rhythmArr[i];
        }
        //加入鼓点
        rhythm.addLayer(rhythmStr);
        rhythmPattern.add(rhythm.getPattern());
        Player player1=new Player();
        System.out.println("节奏:"+rhythmPattern);
        MusicPlayThread playThread=new MusicPlayThread(player1, rhythmPattern);
        playThread.start();
    }

    /**
     * 随机获取节奏
     * @return
     */
    public Pattern getRhythm() {
        Pattern rhythmPattern = new Pattern();
        rhythmStr = "";
        for (int i = 0; i < 32; i++) {

            //随机生成 鼓点
            int index = (int) (Math.random() * rhythmArr.length);
            //随机加入鼓点
            rhythmStr += rhythmArr[index];
            //rhythmStr+=" . ";
        }

        //加入鼓点
        rhythm.addLayer(rhythmStr);
        rhythmPattern.add(rhythm.getPattern());

        return rhythmPattern;
    }

    /**
     * 获取44拍的节奏 动次搭次
     * @return
     */
    public Pattern getRhythm44() {
        Pattern rhythmPattern = new Pattern();
        rhythmStr = "";
        for (int i = 0; i < 32; i++) {

            //随机生成 鼓点
            int index = (int) (Math.random() * rhythmArr2.length);
            //随机加入鼓点
            rhythmStr += rhythmArr2[index];
        }

        //加入鼓点
        rhythm.addLayer(rhythmStr);
        rhythmPattern.add(rhythm.getPattern());

        return rhythmPattern;
    }


}
