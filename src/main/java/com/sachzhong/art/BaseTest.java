package com.sachzhong.art;

import com.sachzhong.service.ChordGenerateService;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

public class BaseTest {

    public static void main(String[] args) {
        //和声
        ChordGenerateService chordUtil = new ChordGenerateService();
        ChordProgression cp=chordUtil.getChordProgression("I VI IV V", "C", 4);
        Chord[] chords=cp.getChords();
        for (Chord chord:chords){
            System.out.println("chord:"+chord.toString());
            Note[] notes = chord.getNotes();
            for (Note note:notes){
                System.out.println("note:"+note.toString());
            }
            System.out.println("\n");
        }
    }
}
