package com.minigamecollection.minigamecollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewGameActivity extends AppCompatActivity {
Button tmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
    }

    public void submitSearch(View v) {

    }

    public void submitToDatabase(View v) {

    }

    public void addGame(View v) {
        if (v instanceof Button) {
            Button button = (Button) v;
            switch (button.getId()) {
                case R.id.wordScramble:
                    setGNameButton("Word Scramble");
                    break;
                case R.id.wordSearch:
                    setGNameButton("Word Search");
                    break;
                case R.id.hangMan:
                    setGNameButton("Hang Man");
                    break;
            }
        }
    }

    private void setGNameButton(String gameName) {
        tmp = MainActivity.getGame1();
        if (tmp.getText().toString().equalsIgnoreCase("Game Name 1 | Game Type | % Done")) {
            tmp.setText(gameName + " | % Done");
        } else if(!tmp.getText().toString().equalsIgnoreCase("Game Name 1 | Game Type | % Done")){
            tmp = MainActivity.getGame2();
            if (tmp.getText().toString().equalsIgnoreCase("Game Name 2 | Game Type | % Done")) {
                tmp.setText(gameName + " | % Done");
            } else if(!tmp.getText().toString().equalsIgnoreCase("Game Name 2 | Game Type | % Done")){
                tmp = MainActivity.getGame3();
                if (tmp.getText().toString().equalsIgnoreCase("Game Name 3 | Game Type | % Done")) {
                    tmp.setText(gameName + " | % Done");
                }
            }
        }
    }
}