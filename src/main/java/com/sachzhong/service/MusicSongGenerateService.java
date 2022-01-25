package com.sachzhong.service;

import org.jfugue.pattern.Pattern;
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
    private Pattern dongji;

    /**
     * 根据歌词生成音符
     *
     * @return
     */
    Pattern generateDongJi() {
        Pattern pattern = new Pattern();
        MusicPaiService musicPaiService = new MusicPaiService();
        List<String> mylist = musicPaiService.getPai44();
        NoteGenerateService noteGenerateService = new NoteGenerateService();
        for (int j = 0; j < mylist.size(); j++) {
            String note = noteGenerateService.getNote();
            note += mylist.get(j);
            pattern.add(note);
        }
        pattern.add(" | ");
        this.dongji = pattern;
        System.out.println("生成的动机:" + this.dongji);
        return dongji;
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
    }

    /**
     * 设置函数
     */
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
