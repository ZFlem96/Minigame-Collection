package com.minigamecollection.minigamecollection.statemanagers.games;

import com.minigamecollection.minigamecollection.dbobjects.HangManSave;

import java.io.Serializable;

/**
 * Created by M on 3/13/2017.
 */

public class HangManData extends GameData implements Serializable {

    int strikes;
    int correctGuesses;
    private String winningWord;
    private String hiddenWord;
    private String triedLettersText;
    private final HangManSave save;

    public HangManData(HangManSave save) {
        this.save = save;
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

    public HangManSave getSave() {
        return save;
    }

    public void save() {
        save.setName(this.getName());
        save.setStrikes(this.strikes);
        save.setCorrectGuesses(this.correctGuesses);
        save.setWinningWord(this.winningWord);
        save.setHiddenWord(this.hiddenWord);
        save.setTriedLettersText(this.triedLettersText);
        save.save();
    }

    @Override
    public void load() {
        this.setStrikes(save.getStrikes());
        this.setCorrectGuesses(save.getCorrectGuesses());
        this.setWinningWord(save.getWinningWord());
        this.setHiddenWord(save.getHiddenWord());
        this.setTriedLettersText(save.getTriedLettersText());
    }
}
