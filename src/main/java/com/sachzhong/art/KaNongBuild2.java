package com.sachzhong.art;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.service.ChordGenerateService;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

import java.util.ArrayList;
import java.util.List;


public class KaNongBuild2 {

    public static void main(String[] args) {

        Pattern song =new Pattern();


        Pattern chordPattern =new Pattern();
        chordPattern.setVoice(0).setInstrument(Instruments.Piano).setTempo(120);
        Pattern melodyPattern =new Pattern();
        melodyPattern.setVoice(1).setInstrument(Instruments.Guitar).setTempo(120);

        List<Chord> chordList =new ArrayList<>();

        Chord chordParam =new Chord(new Note("C"),Chord.MAJOR_INTERVALS);
        chordList.add(chordParam);
        chordParam =new Chord(new Note("G"),Chord.MAJOR_INTERVALS);
        chordList.add(chordParam);
        chordParam =new Chord(new Note("A"),Chord.MINOR_INTERVALS);
        chordList.add(chordParam);
        chordParam =new Chord(new Note("E"),Chord.MINOR_INTERVALS);
        chordList.add(chordParam);
        chordParam =new Chord(new Note("F"),Chord.MAJOR_INTERVALS);
        chordList.add(chordParam);
        chordParam =new Chord(new Note("C"),Chord.MAJOR_INTERVALS);
        chordList.add(chordParam);
        chordParam =new Chord(new Note("D"),Chord.MINOR_INTERVALS);
        chordList.add(chordParam);
        chordParam =new Chord(new Note("G"),Chord.MAJOR_INTERVALS);
        chordList.add(chordParam);

        for (int j = 0; j < 8; j++) {
            for (Chord chord :chordList){
                buildZhiTi(chordPattern,melodyPattern,chord,j);
            }
        }

        song.add(chordPattern);
        System.out.println("chordPattern:"+chordPattern);
        song.add(melodyPattern);
        System.out.println("melodyPattern:"+melodyPattern);

        Player player1=new Player();
        MusicPlayThread playThread=new MusicPlayThread(player1, song);
        playThread.start();

        MusicSaveThread saveThread=new MusicSaveThread(song, "KaNongBuild");
        saveThread.start();
    }


    public static void buildZhiTi(Pattern chordPattern, Pattern melodyPattern,
                                  Chord chord,int seq){
        ChordGenerateService chordGen =new ChordGenerateService();

        if (seq==0){
            chordGen.buildFenJie(chordPattern,chord,0);
            chordGen.buildFenJie(melodyPattern,chord,0);
        }
        if (seq==1){
            chordGen.buildFenJie(chordPattern,chord,0);
            chordGen.buildFenJie(melodyPattern,chord,1);
        }
        if (seq==2){
            chordGen.buildFenJie(chordPattern,chord,1);
            chordGen.buildFenJie(melodyPattern,chord,2);
        }
        if (seq==3){
            chordGen.buildZhuShi(chordPattern,chord,0);
            chordGen.buildFenJie(melodyPattern,chord,2);
        }
        if (seq==4){
            chordGen.buildZhuShi(chordPattern,chord,1);
            chordGen.buildFenJie(melodyPattern,chord,3);
        }
        if (seq==5){
            chordGen.buildZhuShi(chordPattern,chord,2);
            chordGen.buildFenJie(melodyPattern,chord,3);
        }
        if (seq==6){
            chordGen.buildZhuShi(chordPattern,chord,3);
            chordGen.buildFenJie(melodyPattern,chord,3);
        }
        if (seq==7){
            chordGen.buildZhuShi(chordPattern,chord,3);
            chordGen.buildZhuShi(melodyPattern,chord,1);
        }
        if (seq==8){
            chordGen.buildZhuShi(chordPattern,chord,3);
            chordGen.buildZhuShi(melodyPattern,chord,3);
        }
    }


}
