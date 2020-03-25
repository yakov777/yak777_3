package com.kit181.yakov777;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView winner, winsGamer1, winsGamer2;
    private EditText gamer1EditView, gamer2EditView;
    private GridLayout field;
    private Gamer gamer1, gamer2;
    private Game game;
    private boolean vsBot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        winner = findViewById(R.id.winner);
        winsGamer1 = findViewById(R.id.winsGamer1);
        winsGamer2 = findViewById(R.id.winsGamer2);
        gamer1EditView = findViewById(R.id.gamer1);
        gamer2EditView = findViewById(R.id.gamer2);
        field = findViewById(R.id.filed);

        makeGamePlayerVsPlayer();

        winsGamer1.setTextColor(gamer1.getColor());
        winsGamer2.setTextColor(gamer2.getColor());

        gamer1EditView.setTextColor(gamer1.getColor());
        gamer2EditView.setTextColor(gamer2.getColor());
    }

    private void makeGamePlayerVsPlayer() {
        gamer1 = new Player(gamer1EditView.getText().toString(), "X", Color.RED);
        gamer2 = new Player(gamer2EditView.getText().toString(), "O", Color.BLUE);
        game = new Game(gamer1, gamer2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:

            case R.id.action_about:

            case R.id.action_highscores:
                Toast.makeText(this, "In developing", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_bot:
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    swapPlayerWithBot();
                } else {
                    swapBotWithPlayer();
                }
                gamer2EditView.setText(gamer2.getName());
                restart(null);
                game.swapPlayerWithBot(gamer2);
        }
        return super.onOptionsItemSelected(item);
    }

    private void swapPlayerWithBot() {
        gamer2 = new Bot("Kit181", "O", Color.BLUE);
        vsBot = true;
        gamer2EditView.setFocusable(false);
        winsGamer1.setText("0");
        winsGamer2.setText("0");
    }

    private void swapBotWithPlayer() {
        gamer2 = new Player("Player2", "O", Color.BLUE);
        vsBot = false;
        gamer2EditView.setFocusable(true);
        winsGamer1.setText("0");
        winsGamer2.setText("0");
    }


    private void clearField() {
        for (int i = 0; i < field.getChildCount(); i++) {
            Button button = (Button) field.getChildAt(i);
            button.setText("");
            button.setClickable(true);
        }
    }

    public void restart(View view) {
        clearField();
        game.restart();
        winner.setText("");
        if (vsBot && gamer2.getSide().equals("X")) {
            botPlay();
        }
    }

    public void touch(View view) {
        play((Button)view);

        if (vsBot && winner.getText().toString().equals("")) {
            botPlay();
        }
    }

    private void botPlay() {
        int position = ((Bot)gamer2).getPosition(makeClearPoints());
        Button button = findViewById(position);
        Log.wtf("WTF", button.getTag().toString());
        play(button);
    }

    private void play(Button button) {
        String result = game.touch(Integer.parseInt(button.getTag().toString()));
        button.setText(game.getActiveGamer().getSide());
        button.setTextColor(game.getActiveGamer().getColor());
        button.setClickable(false);
        if (!result.equals("")) {
            winner.setText(result);
            winsGamer1.setText(String.valueOf(gamer1.getWins()));
            winsGamer2.setText(String.valueOf(gamer2.getWins()));
            stopGame();
        }
    }

    private void stopGame() {
        for (int i = 0; i < field.getChildCount(); i++) {
            field.getChildAt(i).setClickable(false);
        }
    }

    private ArrayList<Integer> makeClearPoints() {
        ArrayList<Integer> clearPoints = new ArrayList<>();
        for (int i = 0; i < field.getChildCount(); i++) {
            Button button = (Button) field.getChildAt(i);
            if (button.isClickable()) {
                clearPoints.add(button.getId());
            }
        }
        return clearPoints;
    }
}
