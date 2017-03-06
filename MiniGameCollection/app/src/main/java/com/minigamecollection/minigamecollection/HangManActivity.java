package com.minigamecollection.minigamecollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class HangManActivity extends AppCompatActivity {
    private String[] examples = {"word", "hi", "example", "temple"};
    private EditText subLetter, subWord;
    private TextView letterPrompt, wordPrompt, chosenWord, triedLetters;
    private String winningWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_man);
        letterPrompt = (TextView) findViewById(R.id.typeLetter);
        wordPrompt = (TextView) findViewById(R.id.typeWord);
        chosenWord = (TextView) findViewById(R.id.word);
        triedLetters = (TextView) findViewById(R.id.lettersTried);
        subLetter = (EditText) findViewById(R.id.letterTyped);
        subWord = (EditText) findViewById(R.id.wordTyped);
        Random rand = new Random();
        int r = rand.nextInt(examples.length);
        winningWord = examples[r];
    }

    public void submitLetter(View v){
        String submittedLetter = subLetter.getText().toString();

    }

    public void submitWord(View v){

    }
}
