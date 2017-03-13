package com.minigamecollection.minigamecollection.dbobjects;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by M on 3/13/2017.
 */

public class HangManSave extends SugarRecord implements Serializable {

    String name;
    int strikes;
    int correctGuesses;
    String winningWord;
    String hiddenWord;
    String triedLettersText;

    public HangManSave(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrikes() {
        return strikes;
    }

    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public void setCorrectGuesses(int correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    public String getWinningWord() {
        return winningWord;
    }

    public void setWinningWord(String winningWord) {
        this.winningWord = winningWord;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public void setHiddenWord(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public String getTriedLettersText() {
        return triedLettersText;
    }

    public void setTriedLettersText(String triedLettersText) {
        this.triedLettersText = triedLettersText;
    }
}
