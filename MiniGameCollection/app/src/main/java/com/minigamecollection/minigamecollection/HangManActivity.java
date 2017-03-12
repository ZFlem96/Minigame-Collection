package com.minigamecollection.minigamecollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class HangManActivity extends AppCompatActivity {
    private String[] examples = {"word", "hi", "example", "temple"};
    private ArrayList<Character> guessedLetters = new ArrayList<>();
    private char[] reveal;
    private EditText subLetter, subWord;
    private TextView chosenWord, triedLetters, results, wordPrompt, letterPrompt;
    private String winningWord, hiddenWord = "";
    private int strikes = 0, correctGuesses = 0;
    private String slFirstPart, ltFirstPart;
    private boolean gameComplete = false, playerWins = false;
    private Button newGame, exitGame, lettSubBttn, wordSubBttn;
    private ImageView hangmanInit, hangmanHead, hangmanTorso,hangmanLArm, hangmanRArm, hangmanLLeg, completehangman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_man);
        hangmanInit = (ImageView) findViewById(R.id.thehangmaninit);
        hangmanTorso = (ImageView) findViewById(R.id.thehangmanwtorso);
        hangmanHead = (ImageView) findViewById(R.id.thehangmanwhead);
        hangmanLArm = (ImageView) findViewById(R.id.thehangmanwleftarm);
        hangmanRArm = (ImageView) findViewById(R.id.thehangmanwrightarm);
        hangmanLLeg = (ImageView) findViewById(R.id.thehangmanwleftleg);
        completehangman = (ImageView) findViewById(R.id.thecompletehangman);
        chosenWord = (TextView) findViewById(R.id.word);
        triedLetters = (TextView) findViewById(R.id.lettersTried);
        subLetter = (EditText) findViewById(R.id.letterTyped);
        subWord = (EditText) findViewById(R.id.wordTyped);
        lettSubBttn = (Button) findViewById(R.id.letterSubmit);
        wordSubBttn = (Button) findViewById(R.id.wordSubmit);
        wordPrompt = (TextView)findViewById(R.id.typeWord);
        letterPrompt = (TextView)findViewById(R.id.typeLetter);
        Random rand = new Random();
        int r = rand.nextInt(examples.length);
        winningWord = examples[r];
        String wwtmp = winningWord.replaceAll("\\w", "-");
        reveal = wwtmp.toCharArray();
        hiddenWord = wwtmp;
        slFirstPart = chosenWord.getText().toString();
        chosenWord.setText(slFirstPart +" "+hiddenWord);
        ltFirstPart = triedLetters.getText().toString();
        results = (TextView) findViewById(R.id.results);
        newGame = (Button) findViewById(R.id.newgame);
        exitGame = (Button) findViewById(R.id.exit);
        results.setVisibility(View.INVISIBLE);
        newGame.setVisibility(View.INVISIBLE);
        exitGame.setVisibility(View.INVISIBLE);
        hangmanLLeg.setVisibility(View.INVISIBLE);
        hangmanLArm.setVisibility(View.INVISIBLE);
        hangmanRArm.setVisibility(View.INVISIBLE);
        hangmanHead.setVisibility(View.INVISIBLE);
        hangmanTorso.setVisibility(View.INVISIBLE);
        completehangman.setVisibility(View.INVISIBLE);
    }

    public void submitLetter(View v){
        if(!gameComplete){
            char submittedLetter = subLetter.getText().toString().charAt(0);
            boolean letterGuessed = false;
            for(int x = 0;x<guessedLetters.size();x++){
                if(guessedLetters.get(x)==submittedLetter){
                    letterGuessed = true;
                    Toast.makeText(HangManActivity.this, "You've already guessed that letter.", Toast.LENGTH_SHORT).show();
                }
            }
            if(!letterGuessed){
                guessedLetters.add(submittedLetter);
                triedLetters.setText(ltFirstPart+" "+submittedLetter+",");
                ltFirstPart = triedLetters.getText().toString();
                if(strikes<6){
                    boolean isValid = false;
                    for(int x=0; x<winningWord.length();x++){
                        if(submittedLetter-32 == winningWord.toCharArray()[x]){
                            submittedLetter-=32;
                            isValid |= submittedLetter == winningWord.toCharArray()[x];
                            reveal[x] = submittedLetter;
                            correctGuesses++;
                        }else if(submittedLetter+32 == winningWord.toCharArray()[x]){
                            submittedLetter+=32;
                            isValid |= submittedLetter == winningWord.toCharArray()[x];
                            reveal[x] = submittedLetter;
                            correctGuesses++;
                        }else if(submittedLetter == winningWord.toCharArray()[x]){
                            isValid |= submittedLetter == winningWord.toCharArray()[x];
                            reveal[x] = submittedLetter;
                            correctGuesses++;
                        }
                    }
                    if(!isValid){
                        Toast.makeText(HangManActivity.this, "Nope.", Toast.LENGTH_SHORT).show();
                        strikes++;
                        displayHangman();
                        if(strikes==6||strikes>6){
                            gameComplete = true;
                            playerWins = false;
                            Toast.makeText(HangManActivity.this, "You Loose.", Toast.LENGTH_SHORT).show();
                            result(playerWins);
                        }
                    }else if(isValid){
                        hiddenWord = "";
                        for(int x =0;x<reveal.length;x++){
                            hiddenWord+=reveal[x];
                        }
                        chosenWord.setText(slFirstPart +" "+hiddenWord);
                        if(correctGuesses==winningWord.length()){
                            gameComplete = true;
                            playerWins = true;
                            Toast.makeText(HangManActivity.this, "You Win.", Toast.LENGTH_SHORT).show();
                            result(playerWins);
                        }else {
                            Toast.makeText(HangManActivity.this, "Correct.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else{
                    gameComplete = true;
                    playerWins = false;
                    Toast.makeText(HangManActivity.this, "You Loose.", Toast.LENGTH_SHORT).show();
                    result(playerWins);
                }
            }
        }

    }

    public void submitWord(View v){
        if(!gameComplete){
            String submittedWord = subWord.getText().toString();
            if(submittedWord.equalsIgnoreCase(winningWord)){
                gameComplete = true;
                playerWins = true;
                result(playerWins);
            }else {
                gameComplete = true;
                playerWins = false;
                Toast.makeText(HangManActivity.this, "Nope. You Loose.", Toast.LENGTH_SHORT).show();
                hangmanInit.setVisibility(View.INVISIBLE);
                result(playerWins);
            }
        }
    }
    public void result(boolean playerWins){
        results.setVisibility(View.VISIBLE);
        newGame.setVisibility(View.VISIBLE);
        exitGame.setVisibility(View.VISIBLE);
        chosenWord.setVisibility(View.INVISIBLE);
        triedLetters.setVisibility(View.INVISIBLE);
        subLetter.setVisibility(View.INVISIBLE);
        subWord.setVisibility(View.INVISIBLE);
        lettSubBttn.setVisibility(View.INVISIBLE);
        wordSubBttn.setVisibility(View.INVISIBLE);
        letterPrompt.setVisibility(View.INVISIBLE);
        wordPrompt.setVisibility(View.INVISIBLE);
        hangmanInit.setVisibility(View.INVISIBLE);
        hangmanHead.setVisibility(View.INVISIBLE);
        hangmanTorso.setVisibility(View.INVISIBLE);
        hangmanLArm.setVisibility(View.INVISIBLE);
        hangmanRArm.setVisibility(View.INVISIBLE);
        hangmanLLeg.setVisibility(View.INVISIBLE);
        completehangman.setVisibility(View.INVISIBLE);
        String message="";
        if(playerWins){
            message = "Congrats! You Won";
        }else if(!playerWins){
            message = "Sorry You Lose";
        }
        results.setText(message);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HangManActivity.this, HangManActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        exitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void displayHangman(){
        switch (strikes){
            case 0:
                hangmanInit.setVisibility(View.VISIBLE);
                hangmanHead.setVisibility(View.INVISIBLE);
                hangmanTorso.setVisibility(View.INVISIBLE);
                hangmanLArm.setVisibility(View.INVISIBLE);
                hangmanRArm.setVisibility(View.INVISIBLE);
                hangmanLLeg.setVisibility(View.INVISIBLE);
                completehangman.setVisibility(View.INVISIBLE);
                break;
            case 1:
                hangmanInit.setVisibility(View.INVISIBLE);
                hangmanHead.setVisibility(View.VISIBLE);
                hangmanTorso.setVisibility(View.INVISIBLE);
                hangmanLArm.setVisibility(View.INVISIBLE);
                hangmanRArm.setVisibility(View.INVISIBLE);
                hangmanLLeg.setVisibility(View.INVISIBLE);
                completehangman.setVisibility(View.INVISIBLE);
                break;
            case 2:
                hangmanInit.setVisibility(View.INVISIBLE);
                hangmanHead.setVisibility(View.INVISIBLE);
                hangmanTorso.setVisibility(View.VISIBLE);
                hangmanLArm.setVisibility(View.INVISIBLE);
                hangmanRArm.setVisibility(View.INVISIBLE);
                hangmanLLeg.setVisibility(View.INVISIBLE);
                completehangman.setVisibility(View.INVISIBLE);
                break;
            case 3:
                hangmanInit.setVisibility(View.INVISIBLE);
                hangmanHead.setVisibility(View.INVISIBLE);
                hangmanTorso.setVisibility(View.INVISIBLE);
                hangmanLArm.setVisibility(View.VISIBLE);
                hangmanRArm.setVisibility(View.INVISIBLE);
                hangmanLLeg.setVisibility(View.INVISIBLE);
                completehangman.setVisibility(View.INVISIBLE);
                break;
            case 4:
                hangmanInit.setVisibility(View.INVISIBLE);
                hangmanHead.setVisibility(View.INVISIBLE);
                hangmanTorso.setVisibility(View.INVISIBLE);
                hangmanLArm.setVisibility(View.INVISIBLE);
                hangmanRArm.setVisibility(View.VISIBLE);
                hangmanLLeg.setVisibility(View.INVISIBLE);
                completehangman.setVisibility(View.INVISIBLE);
                break;
            case 5:
                hangmanInit.setVisibility(View.INVISIBLE);
                hangmanHead.setVisibility(View.INVISIBLE);
                hangmanTorso.setVisibility(View.INVISIBLE);
                hangmanLArm.setVisibility(View.INVISIBLE);
                hangmanRArm.setVisibility(View.INVISIBLE);
                hangmanLLeg.setVisibility(View.VISIBLE);
                completehangman.setVisibility(View.INVISIBLE);
                break;
            case 6:
                hangmanInit.setVisibility(View.INVISIBLE);
                hangmanHead.setVisibility(View.INVISIBLE);
                hangmanTorso.setVisibility(View.INVISIBLE);
                hangmanLArm.setVisibility(View.INVISIBLE);
                hangmanRArm.setVisibility(View.INVISIBLE);
                hangmanLLeg.setVisibility(View.INVISIBLE);
                completehangman.setVisibility(View.VISIBLE);
                break;
        }
    }

}
