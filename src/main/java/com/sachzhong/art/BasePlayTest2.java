package com.sachzhong.art;

import com.sachzhong.instruments.Instruments;
import com.sachzhong.thread.MusicPlayThread;
import com.sachzhong.thread.MusicSaveThread;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

import java.util.ArrayList;
import java.util.List;


public class BasePlayTest2 {

    public static void main(String[] args) {

        Pattern song =new Pattern();


        Pattern chordPattern =new Pattern();
        chordPattern.setVoice(0).setInstrument(Instruments.Piano).setTempo(120);
        Pattern melodyPattern =new Pattern();
        melodyPattern.setVoice(1).setInstrument(Instruments.Piano).setTempo(120);

        List<Chord> chordList =new ArrayList<>();

        Chord chordParam =new Chord(new Note("A"),Chord.MINOR_SEVENTH_SIXTH_INTERVALS);
        chordList.add(chordParam);
        chordParam =new Chord(new Note("B"),Chord.MINOR_SEVENTH_SIXTH_INTERVALS);
        chordList.add(chordParam);
        chordParam =new Chord(new Note("E"),Chord.MAJOR_SEVENTH_SIXTH_INTERVALS);
        chordList.add(chordParam);
        chordParam =new Chord(new Note("A"),Chord.MAJOR_SEVENTH_SIXTH_INTERVALS);
        chordList.add(chordParam);

        for (int j = 0; j < 8; j++) {

            if (j%3==0){
                for (Chord chord :chordList){
                    buildZhiTi3(chordPattern,melodyPattern,chord);
                }
            }else if (j%4==0){
                for (Chord chord :chordList){
                    buildZhiTi4(chordPattern,melodyPattern,chord);
                }
            }else if (j%2==0){
                for (Chord chord :chordList){
                    buildZhiTi2(chordPattern,melodyPattern,chord);
                }
            }else {
                for (Chord chord :chordList){
                    buildZhiTi(chordPattern,melodyPattern,chord);
                }
            }
        }

        song.add(chordPattern);
        System.out.println("chordPattern:"+chordPattern);
        song.add(melodyPattern);
        System.out.println("melodyPattern:"+melodyPattern);

        Player player1=new Player();
        MusicPlayThread playThread=new MusicPlayThread(player1, song);
        playThread.start();

        MusicSaveThread saveThread=new MusicSaveThread(song, "小调KaNongBuild");
        saveThread.start();
    }


    public static void buildZhiTi(Pattern chordPattern, Pattern melodyPattern, Chord chord){
        chordPattern.add(chord +"w|");
        Note[] notes = chord.getNotes();
        //System.out.println("Chord:"+chord+"  Notes:"+notes[0]+" "+notes[1]+" "+notes[2]);
        melodyPattern.add(notes[2]+"5q " +notes[1]+"5q " +notes[0]+"5q " +notes[1]+"5q|");
    }

    public static void buildZhiTi2(Pattern chordPattern, Pattern melodyPattern, Chord chord){
        chordPattern.add(chord +"w|");
        Note[] notes = chord.getNotes();
        //System.out.println("Chord:"+chord+"  Notes:"+notes[0]+" "+notes[1]+" "+notes[2]);
        melodyPattern.add(notes[2]+"5q " +notes[1]+"5i "+notes[1]+"5i "
                +notes[2]+"5q " +notes[0]+"5i "+notes[0]+"5i|");
    }

    public static void buildZhiTi3(Pattern chordPattern, Pattern melodyPattern, Chord chord){
        chordPattern.add(chord +"w|");
        Note[] notes = chord.getNotes();
        //System.out.println("Chord:"+chord+"  Notes:"+notes[0]+" "+notes[1]+" "+notes[2]);
        melodyPattern.add(notes[2]+"5q " +notes[2]+"5i "+notes[0]+"5s "+notes[1]+"5s "
                +notes[2]+"5q " +notes[0]+"5i "+notes[0]+"5i|");
    }

    public static void buildZhiTi4(Pattern chordPattern, Pattern melodyPattern, Chord chord){
        chordPattern.add(chord +"h "+chord +"h|");
        Note[] notes = chord.getNotes();
        //System.out.println("Chord:"+chord+"  Notes:"+notes[0]+" "+notes[1]+" "+notes[2]);
        melodyPattern.add(notes[0]+"5q " +notes[2]+"5i "+notes[0]+"5s "+notes[1]+"5s "
                +notes[0]+"5q " +notes[2]+"5i "+notes[0]+"5s "+notes[1]+"5s|");
    }
}
