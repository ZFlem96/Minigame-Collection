package com.minigamecollection.minigamecollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.minigamecollection.minigamecollection.dbobjects.HangManSave;
import com.minigamecollection.minigamecollection.statemanagers.SaveGame;
import com.minigamecollection.minigamecollection.statemanagers.SaveType;
import com.minigamecollection.minigamecollection.statemanagers.games.HangManData;

public class NewGameActivity extends AppCompatActivity {

    Button tmp;
    Button buttonCreateGame;
    RadioButton radioButtonWordScramble;
    RadioButton radioButtonHangMan;
    RadioButton radioButtonWordSearch;
    EditText editTextSaveGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        buttonCreateGame = (Button)findViewById(R.id.buttonCreateGame);
        radioButtonWordScramble = (RadioButton)findViewById(R.id.radioButtonWordScramble);
        radioButtonHangMan = (RadioButton)findViewById(R.id.radioButtonHangMan);
        radioButtonWordSearch = (RadioButton)findViewById(R.id.radioButtonWordSearch);
        editTextSaveGame = (EditText)findViewById(R.id.editTextSaveGame);
    }

    public void onClickCreateGame(View v) {
        if (radioButtonHangMan.isChecked()) {
            Intent intent = new Intent(this, HangManActivity.class);
            intent.putExtra("save", new SaveGame(editTextSaveGame.getText().toString(), SaveType.HANGMAN, new HangManData(new HangManSave())));
            intent.putExtra("loading", false);
            startActivity(intent);
        } else if (radioButtonWordScramble.isChecked()) {

        } else if (radioButtonWordSearch.isChecked()) {

        }
    }

    public void submitSearch(View v) {

    }

    public void submitToDatabase(View v) {

    }

    public void addGame(View v) {
        if (v instanceof Button) {
            Button button = (Button) v;
//            switch (button.getId()) {
//                case R.id.wordScramble:
//                    setGNameButton("Word Scramble");
//                    break;
//                case R.id.wordSearch:
//                    setGNameButton("Word Search");
//                    break;
//                case R.id.hangMan:
//                    setGNameButton("Hang Man");
//                    break;
//            }
        }
    }

    private void setGNameButton(String gameName) {
        tmp = MainActivity.getGame1();
        if (tmp.getText().toString().equalsIgnoreCase("Game Name 1 | Game Type | % Done")) {
            tmp.setText(gameName + " | % Done");
        } else if (!tmp.getText().toString().equalsIgnoreCase("Game Name 1 | Game Type | % Done")) {
            tmp = MainActivity.getGame2();
            if (tmp.getText().toString().equalsIgnoreCase("Game Name 2 | Game Type | % Done")) {
                tmp.setText(gameName + " | % Done");
            } else if (!tmp.getText().toString().equalsIgnoreCase("Game Name 2 | Game Type | % Done")) {
                tmp = MainActivity.getGame3();
                if (tmp.getText().toString().equalsIgnoreCase("Game Name 3 | Game Type | % Done")) {
                    tmp.setText(gameName + " | % Done");
                }
            }
        }
    }
}