package com.sachzhong.art;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.service.ChordGenerateService;
import com.sachzhong.service.MusicPaiService;
import com.sachzhong.service.NoteGenerateService;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;

import java.util.List;




public class MusicArt {
    public static void main(String[] args) {

        int speed = 92;
        int yinyu = 5;

        //和声
        ChordGenerateService chordUtil = new ChordGenerateService();
        ChordProgression cp=chordUtil.getChordProgression("VI IV I V", "C", yinyu);

        Chord[] chords=cp.getChords();

        Pattern song=new Pattern();
        NoteGenerateService noteGenerateService =new NoteGenerateService();

        //旋律音轨
        Pattern melodyPattern=new Pattern();
        //和弦音轨
        Pattern chordPattern=new Pattern();

        MusicPaiService musicPai = new MusicPaiService();
        for (int k =0;k<10;k++){
            for (int i = 0; i < chords.length; i++) {

                Chord  chord=chords[i];
                List<String> jiezou=musicPai.getPai();
                for (int j = 0; j < jiezou.size(); j++) {
                    String	node= noteGenerateService.getNote(chord);
                    node+=jiezou.get(j);
                    melodyPattern.add(node);
                }
                chordPattern.add(chord+"w|");
                melodyPattern.add("|");

            }
        }


        melodyPattern.setVoice(0).setInstrument(Instruments.Guitar).setTempo(speed);
        chordPattern.setVoice(1).setInstrument(Instruments.Piano).setTempo(speed);

        System.out.println(melodyPattern);
        System.out.println(chordPattern);

        song.add(melodyPattern);
        song.add(chordPattern);

        Player player1=new Player();
        MusicPlayThread playThread=new MusicPlayThread(player1, song);
        playThread.start();

        MusicSaveThread saveThread=new MusicSaveThread(song, "ChordArt");
        saveThread.start();


    }
}
