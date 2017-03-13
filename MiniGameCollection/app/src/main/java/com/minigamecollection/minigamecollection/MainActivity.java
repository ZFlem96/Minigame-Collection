package com.minigamecollection.minigamecollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.minigamecollection.minigamecollection.dbobjects.HangManSave;
import com.minigamecollection.minigamecollection.statemanagers.SaveGame;
import com.minigamecollection.minigamecollection.statemanagers.SaveType;
import com.minigamecollection.minigamecollection.statemanagers.games.HangManData;
import com.orm.SugarDb;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private static Button game1,game2,game3;

    ListView listViewLoadGames;
    ArrayAdapter<SaveGame> listViewLoadGamesAdapter;
    ArrayList<SaveGame> saveGamesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        game1 = (Button) findViewById(R.id.gname1);
//        game2 = (Button) findViewById(R.id.gname2);
//        game3 = (Button) findViewById(R.id.gname3);

        final MainActivity self = this;

        //HangManSave.deleteAll(HangManSave.class);

        listViewLoadGames = (ListView) findViewById(R.id.listViewLoadGames);

        listViewLoadGamesAdapter = new ArrayAdapter<SaveGame>(this, android.R.layout.simple_list_item_1, saveGamesList);
        listViewLoadGames.setAdapter(listViewLoadGamesAdapter);

        Iterator<HangManSave> hangManSaveGames = HangManSave.findAll(HangManSave.class);
        while (hangManSaveGames.hasNext()) {
            HangManSave dbsave = hangManSaveGames.next();
            SaveGame saveGame = new SaveGame(dbsave.getName(), SaveType.HANGMAN, new HangManData(dbsave));
            saveGamesList.add(saveGame);
        }

        listViewLoadGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (saveGamesList.get(i).getType()) {
                    case HANGMAN:
                        Intent intent = new Intent(self, HangManActivity.class);
                        intent.putExtra("save", saveGamesList.get(i));
                        intent.putExtra("loading", true);
                        startActivity(intent);
                        break;
                }
            }
        });

        listViewLoadGamesAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        saveGamesList.clear();
        Iterator<HangManSave> hangManSaveGames = HangManSave.findAll(HangManSave.class);
        while (hangManSaveGames.hasNext()) {
            HangManSave dbsave = hangManSaveGames.next();
            SaveGame saveGame = new SaveGame(dbsave.getName(), SaveType.HANGMAN, new HangManData(dbsave));
            saveGamesList.add(saveGame);
        }
        listViewLoadGamesAdapter.notifyDataSetChanged();
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
//            switch (button.getId()) {
//                case R.id.gname1:
//                        if(game1.getText().toString().contains("Word Scramble")){
//                            intent = new Intent(this, WordScrambleActivity.class);
//                            startActivity(intent);
//                        } else if(game1.getText().toString().contains("Word Search")){
//                            intent = new Intent(this, WordSearchActivity.class);
//                            startActivity(intent);
//                        }else if(game1.getText().toString().contains("Hang Man")){
//                            intent = new Intent(this, HangManActivity.class);
//                            startActivity(intent);
//                        }
//                    break;
//                case R.id.gname2:
//                        if(game2.getText().toString().contains("Word Scramble")){
//                            intent = new Intent(this, WordScrambleActivity.class);
//                            startActivity(intent);
//                        } else if(game2.getText().toString().contains("Word Search")){
//                            intent = new Intent(this, WordSearchActivity.class);
//                            startActivity(intent);
//                        }else if(game2.getText().toString().contains("Hang Man")){
//                            intent = new Intent(this, HangManActivity.class);
//                            startActivity(intent);
//                        }
//                    break;
//                case R.id.gname3:
//                    if(game3.getText().toString().contains("Word Scramble")){
//                        intent = new Intent(this, WordScrambleActivity.class);
//                        startActivity(intent);
//                    } else if(game3.getText().toString().contains("Word Search")){
//                        intent = new Intent(this, WordSearchActivity.class);
//                        startActivity(intent);
//                    }else if(game3.getText().toString().contains("Hang Man")){
//                        intent = new Intent(this, HangManActivity.class);
//                        startActivity(intent);
//                    }
//                    break;
//            }
        }
//        startActivity(intent);
    }
}
