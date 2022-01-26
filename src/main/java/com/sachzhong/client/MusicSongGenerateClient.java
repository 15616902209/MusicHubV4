package com.sachzhong.client;

import com.sachzhong.service.MusicSongGenerateService;
import org.jfugue.pattern.Pattern;

/**
 * @author SachZhong
 * @date 2022/1/26 11:33
 * @info 音乐生成练习
 */
public class MusicSongGenerateClient {

    public static void main(String[] args) {
        MusicSongGenerateService musicService = new MusicSongGenerateService(78, "I VI IV V", "C", 3);
        Pattern song = musicService.generateYueJu(musicService.generateDongJi());
        musicService.setSong(song);
        musicService.playSong();
        musicService.saveSong();
    }
}
