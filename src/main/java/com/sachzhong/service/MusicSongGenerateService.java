package com.sachzhong.service;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;

import java.util.List;

/**
 * @author SachZhong
 * @date 2022/1/25 17:39
 * @info 音乐生成服务
 */
public class MusicSongGenerateService {
    /**
     * 速度
     */
    private int speed = 120;
    /**
     * 速度
     */
    private String jidiao = "C";
    /**
     * 音域
     */
    private int yinyu = 4;
    /**
     * 和弦走向
     */
    private ChordProgression cp;
    /**
     * 和弦数组
     */
    private Chord[] chords;
    /**
     * 动机
     */
    private Pattern dongji = new Pattern();
    /**
     * 整体音乐旋律存储对象
     */
    private Pattern song = new Pattern();

    /**
     * 生成动机
     *
     * @return 动机
     */
    public Pattern generateDongJi() {
        Pattern pattern = new Pattern();
        MusicPaiService musicPaiService = new MusicPaiService();
        List<String> mylist = musicPaiService.getPai44By4816();
        NoteGenerateService noteGenerateService = new NoteGenerateService();
        for (int j = 0; j < mylist.size(); j++) {
            String note = noteGenerateService.getChinaNote();
            note += mylist.get(j);
            pattern.add(note);
        }
        this.dongji = pattern;
        System.out.println("生成的动机:" + this.dongji);
        return dongji;
    }

    /**
     * 根据动机生成4小节乐句
     *
     * @return 乐句
     */
    public Pattern generateYueJu(Pattern dongJi) {
        Pattern pattern = new Pattern();
        //第一小节就是动机
        pattern.add(dongJi);
        //第二小节，随机更改一个音符
        pattern.add(" | " + updateYinFu(dongJi));
        //第三小节就是动机
        pattern.add(" | " + dongJi);
        //第四小节，随机更改2个音符
        pattern.add(" | " + updateYinFu(updateYinFu(dongJi)));
        System.out.println("生成的乐句:" + pattern);
        return pattern;
    }

    /**
     * 随机更改一个音符
     *
     * @param yueJu 一个乐句
     * @return 更改后的乐句
     */
    public Pattern updateYinFu(Pattern yueJu) {
        Pattern pattern = new Pattern();
        NoteGenerateService noteGenerateService = new NoteGenerateService();
        String[] dongjiArr = yueJu.toString().split(" ");
        int randomInt = (int) Math.round(Math.random() * dongjiArr.length - 1);
        if (randomInt < 0) {
            randomInt = 0;
        }
        String note = dongjiArr[randomInt];
        String pai = note.substring(note.length() - 1);
        note = noteGenerateService.getChinaNote() + pai;
        dongjiArr[randomInt] = note;
        return pattern.add(changeArrToString(dongjiArr));
    }

    /**
     * 播放音乐
     */
    public void playSong() {
        Player player1 = new Player();
        MusicPlayThread musicThread = new MusicPlayThread(player1, song);
        musicThread.start();
    }

    /**
     * 保存音乐
     */
    public void saveSong() {
        MusicSaveThread musicUtil = new MusicSaveThread(song, "作曲练习");
        musicUtil.start();
    }

    /**
     * 音乐生成设置变量
     *
     * @param speed            速度
     * @param chordProgression 和弦走向
     * @param jidiao           基调
     * @param yinyu            音域
     */
    public MusicSongGenerateService(int speed, String chordProgression, String jidiao, int yinyu) {
        this.speed = speed;
        this.yinyu = yinyu;
        this.jidiao = jidiao;
        this.cp = new ChordProgression(chordProgression).setKey(jidiao + yinyu);
        this.chords = cp.getChords();
        //设置乐器和速度
        this.song.setVoice(1).setInstrument(Instruments.Piano).setTempo(speed);
    }

    /**
     * 将字符数组转为符合音乐生成的字符串对象
     *
     * @param arr 字符数组
     * @return 字符串对象
     */
    public String changeArrToString(String[] arr) {
        StringBuilder result = new StringBuilder();
        for (String s : arr) {
            result.append(s).append(" ");
        }
        return result.toString();
    }

    /**
     * ---------------------------------设置函数---------------------------------------
     */

    public Pattern getDongji() {
        return dongji;
    }

    public void setDongji(Pattern dongji) {
        this.dongji = dongji;
    }

    public Pattern getSong() {
        return song;
    }

    public void setSong(Pattern song) {
        this.song = song;
    }

    public String getJidiao() {
        return jidiao;
    }

    public void setJidiao(String jidiao) {
        this.jidiao = jidiao;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getYinyu() {
        return yinyu;
    }

    public void setYinyu(int yinyu) {
        this.yinyu = yinyu;
    }

    public ChordProgression getCp() {
        return cp;
    }

    public void setCp(ChordProgression cp) {
        this.cp = cp;
    }

    public Chord[] getChords() {
        return chords;
    }

    public void setChords(Chord[] chords) {
        this.chords = chords;
    }
}
