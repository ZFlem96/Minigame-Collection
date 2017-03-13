package com.minigamecollection.minigamecollection.statemanagers;

import com.minigamecollection.minigamecollection.HangManActivity;
import com.minigamecollection.minigamecollection.statemanagers.games.GameData;
import com.minigamecollection.minigamecollection.statemanagers.games.HangManData;

import java.io.Serializable;

/**
 * Created by M on 3/13/2017.
 */

public class SaveGame implements Serializable {

    private final String saveName;
    private final SaveType type;
    private final GameData gameData;

    public SaveGame(String saveName, SaveType type, GameData gameData) {
        this.saveName = saveName;
        this.type = type;
        this.gameData = gameData;
        this.gameData.setName(saveName);
    }

    public String getSaveName() {
        return saveName;
    }

    public SaveType getType() {
        return type;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void save(HangManActivity hangMan) {
        HangManData hangManData = (HangManData) this.gameData;
        hangManData.setStrikes(hangMan.strikes);
        hangManData.setCorrectGuesses(hangMan.correctGuesses);
        hangManData.setWinningWord(hangMan.winningWord);
        hangManData.setHiddenWord(hangMan.hiddenWord);
        hangManData.setTriedLettersText(hangMan.triedLetters.getText().toString());
        hangManData.save();
    }

    public void load(HangManActivity hangMan) {
        this.gameData.load();
        HangManData hangManData = (HangManData) this.gameData;
        hangMan.strikes = hangManData.getStrikes();
        hangMan.correctGuesses = hangManData.getCorrectGuesses();
        hangMan.winningWord = hangManData.getWinningWord();
        hangMan.hiddenWord = hangManData.getHiddenWord();
        hangMan.triedLetters.setText(hangManData.getTriedLettersText());
    }

    public void delete(HangManActivity hangMan) {
        HangManData hangManData = (HangManData) this.gameData;
        hangManData.getSave().delete();
    }

    @Override
    public String toString() {
        return saveName + " | " + type.toString().toLowerCase() + " | ";
    }
}
