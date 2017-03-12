package com.minigamecollection.minigamecollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WordScrambleActivity extends AppCompatActivity {
    TextView timer, result, scoreText, strikesText, scrambledWord;
    EditText answer;
    Button newGameBttn, exitGameBttn,submitBttn, timechangerBttn, pauseBttn, goBttn, thirtyBttn, sixtyBttn;
    private String[] examples = {"respectful", "powerful", "example", "temple", "battlefield", "assault", "barrage", "zephyr","record", "supercalifragilisticexpialidocious"};
    private String actualWord = "", scorePart = "";
    private Random rand = new Random();
    private int secs = 30, strikeCount = 0;
    boolean timerPause = false;
    private boolean timesUp = false, correctGuess = false;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_scramble);
        timer = (TextView) findViewById(R.id.timerId);
        result = (TextView) findViewById(R.id.scrambleResultsId);
        scoreText = (TextView) findViewById(R.id.scoreId);
        strikesText = (TextView) findViewById(R.id.strikeID);
        scrambledWord = (TextView) findViewById(R.id.theWord);
        answer = (EditText) findViewById(R.id.editText);
        newGameBttn = (Button) findViewById(R.id.scrNewGameId);
        exitGameBttn = (Button) findViewById(R.id.scrExitId);
        submitBttn = (Button) findViewById(R.id.subId);
        timechangerBttn = (Button) findViewById(R.id.timeChangerId);
        pauseBttn = (Button) findViewById(R.id.pauseId);
        goBttn = (Button) findViewById(R.id.goId);
        thirtyBttn = (Button) findViewById(R.id.thirty);
        sixtyBttn = (Button) findViewById(R.id.sixty);
        result.setVisibility(View.INVISIBLE);
        newGameBttn.setVisibility(View.INVISIBLE);
        exitGameBttn.setVisibility(View.INVISIBLE);
        thirtyBttn.setVisibility(View.INVISIBLE);
        sixtyBttn.setVisibility(View.INVISIBLE);
        scrambleWord();
        scorePart = scoreText.getText().toString();

    }
    public void scrambleWord(){
        scrambledWord.setText("");
        int r = rand.nextInt(examples.length);
        actualWord = examples[r];
        ArrayList<Character> letterContainer1 = new ArrayList<Character>(), letterContainer2 = new ArrayList<Character>();
        String text = scrambledWord.getText().toString();
        for(int x =0;x<actualWord.length();x++){
            letterContainer1.add(actualWord.charAt(x));
        }
        while(letterContainer1.size()>0){
            r = rand.nextInt(letterContainer1.size());
            letterContainer2.add(letterContainer1.get(r));
            letterContainer1.remove(r);
        }
        for(int x = 0; x<letterContainer2.size();x++){
            text+=letterContainer2.get(x);
        }
        scrambledWord.setText(text);
    }

    public void submit(View v){
       if(!timerPause && !timesUp ) {
           String submittedWord = answer.getText().toString();
           answer.setText("");
           if (submittedWord.equalsIgnoreCase(actualWord)) {
               score += 10;
               scoreText.setText(scorePart + score);
               correctGuess = true;
           } else if (!submittedWord.equalsIgnoreCase(actualWord)) {
               String strike = strikesText.getText().toString();
               strikesText.setText(strike + "X");
               strikeCount++;
           }
           if(strikeCount==3||correctGuess){
               strikesText.setText("Strikes: ");
               scrambleWord();
           }
       }
    }
    public void go(View v){
    timerPause = false;
    }
    public void pause(View v){
        timerPause = true;
    }
    public void showTimeOptions(View v){
        thirtyBttn.setVisibility(View.VISIBLE);
        sixtyBttn.setVisibility(View.VISIBLE);
    }
    public void setTo30(View v){
        secs = 30;
        thirtyBttn.setVisibility(View.INVISIBLE);
        sixtyBttn.setVisibility(View.INVISIBLE);
        timer.setText(""+secs);
        scoreText.setText("Score: ");
        strikesText.setText("Strikes: ");
    }
    public void setTo60(View v){
        secs = 60;
        thirtyBttn.setVisibility(View.INVISIBLE);
        sixtyBttn.setVisibility(View.INVISIBLE);
        timer.setText(""+secs);
        scoreText.setText("Score: ");
        strikesText.setText("Strikes: ");
    }
    public void seeResults(){
        timer.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);
        strikesText.setVisibility(View.INVISIBLE);
        scrambledWord.setVisibility(View.INVISIBLE);
        answer.setVisibility(View.INVISIBLE);
        submitBttn.setVisibility(View.INVISIBLE);
        timechangerBttn.setVisibility(View.INVISIBLE);
        thirtyBttn.setVisibility(View.INVISIBLE);
        sixtyBttn.setVisibility(View.INVISIBLE);
        pauseBttn.setVisibility(View.INVISIBLE);
        goBttn.setVisibility(View.INVISIBLE);
        result.setText("Score: "+score);
        result.setVisibility(View.VISIBLE);
        newGameBttn.setVisibility(View.VISIBLE);
        exitGameBttn.setVisibility(View.VISIBLE);

    }
    public void newGame(View v){
        Intent intent = new Intent(WordScrambleActivity.this, WordScrambleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void exit(View v){
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!timerPause && !timesUp ) {
            Timer T = new Timer();
            T.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(!timerPause && !timesUp && secs>=0 ) {
                                timer.setText("" + secs);
                                secs--;
                                if(secs<0){
                                    timesUp = true;
                                }
                            }
                            else if(timesUp){
                                timesUp = false;
                                seeResults();
                            }
                        }
                    });
                }
            }, 1000, 1000);
        }
        else if(timesUp){
            seeResults();
        }
    }
}
