package com.minigamecollection.minigamecollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static Button game1,game2,game3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game1 = (Button) findViewById(R.id.gname1);
        game2 = (Button) findViewById(R.id.gname2);
        game3 = (Button) findViewById(R.id.gname3);
    }
    public static Button getGame1(){
        return game1;
    }
    public static Button getGame2(){
        return game2;
    }
    public static Button getGame3(){
        return game3;
    }
    public void displayScoreBoard(View v){
        Intent intent = new Intent(this, Scoreboard.class);
        startActivity(intent);
    }

    public void addNewGame(View v){
        Intent intent = new Intent(this, NewGameActivity.class);
        startActivity(intent);
    }

    public void startGame(View v){
        Intent intent;
        if (v instanceof Button) {
            Button button = (Button) v;
            switch (button.getId()) {
                case R.id.gname1:
                        if(game1.getText().toString().contains("Word Scramble")){
                            intent = new Intent(this, WordScrambleActivity.class);
                            startActivity(intent);
                        } else if(game1.getText().toString().contains("Word Search")){
                            intent = new Intent(this, WordSearchActivity.class);
                            startActivity(intent);
                        }else if(game1.getText().toString().contains("Hang Man")){
                            intent = new Intent(this, HangManActivity.class);
                            startActivity(intent);
                        }
                    break;
                case R.id.gname2:
                        if(game2.getText().toString().contains("Word Scramble")){
                            intent = new Intent(this, WordScrambleActivity.class);
                            startActivity(intent);
                        } else if(game2.getText().toString().contains("Word Search")){
                            intent = new Intent(this, WordSearchActivity.class);
                            startActivity(intent);
                        }else if(game2.getText().toString().contains("Hang Man")){
                            intent = new Intent(this, HangManActivity.class);
                            startActivity(intent);
                        }
                    break;
                case R.id.gname3:
                    if(game3.getText().toString().contains("Word Scramble")){
                        intent = new Intent(this, WordScrambleActivity.class);
                        startActivity(intent);
                    } else if(game3.getText().toString().contains("Word Search")){
                        intent = new Intent(this, WordSearchActivity.class);
                        startActivity(intent);
                    }else if(game3.getText().toString().contains("Hang Man")){
                        intent = new Intent(this, HangManActivity.class);
                        startActivity(intent);
                    }
                    break;
            }
        }
//        startActivity(intent);
    }
}
