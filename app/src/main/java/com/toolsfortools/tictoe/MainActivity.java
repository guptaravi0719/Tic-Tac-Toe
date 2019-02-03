package com.toolsfortools.tictoe;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AdView adView1;
    public String Message;
    private AdView adView2;

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-3945166141600873~1243266851");


        adView1 = (AdView) findViewById(R.id.adView);
        adView2 = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();

        adView1.loadAd(adRequest);
        adView2.loadAd(adRequest);
        //  AdRequest.Builder.addTestDevice("37D130234AD01900E08A26ED126FE128");
        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

        @Override
        public void onClick (View v){
            if (!((Button) v).getText().toString().equals("")) {
                return;
            }

            if (player1Turn) {
                ((Button) v).setText("X");
            } else {
                ((Button) v).setText("O");
            }

            roundCount++;

            if (checkForWin()) {
                if (player1Turn) {
                    player1Wins();
                } else {
                    player2Wins();
                }
            } else if (roundCount == 9) {
                draw();
            } else {
                player1Turn = !player1Turn;
            }

        }

        private boolean checkForWin () {
            String[][] field = new String[3][3];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    field[i][j] = buttons[i][j].getText().toString();
                }
            }

            for (int i = 0; i < 3; i++) {
                if (field[i][0].equals(field[i][1])
                        && field[i][0].equals(field[i][2])
                        && !field[i][0].equals("")) {
                    return true;
                }
            }

            for (int i = 0; i < 3; i++) {
                if (field[0][i].equals(field[1][i])
                        && field[0][i].equals(field[2][i])
                        && !field[0][i].equals("")) {
                    return true;
                }
            }

            if (field[0][0].equals(field[1][1])
                    && field[0][0].equals(field[2][2])
                    && !field[0][0].equals("")) {
                return true;
            }

            if (field[0][2].equals(field[1][1])
                    && field[0][2].equals(field[2][0])
                    && !field[0][2].equals("")) {
                return true;
            }

            return false;
        }

        private void player1Wins () {
            player1Points++;
            //  Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
            String message = "Player 2 Wins!";
            OpenDialogue();
            updatePointsText();
            resetBoard();
        }

        private void player2Wins () {
            player2Points++;
            Message = "Player 2 Wins!";
            OpenDialogue2();
            ;
            //  Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
            updatePointsText();
            resetBoard();
        }

        private void draw () {
            Message = "Its a Draw ! Try Again";
            OpenDialoguedraw();
            ;
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            resetBoard();
        }

        private void updatePointsText () {
            textViewPlayer1.setText("Player 1: " + player1Points);
            textViewPlayer2.setText("Player 2: " + player2Points);
        }

        private void resetBoard () {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("");
                }
            }

            roundCount = 0;
            player1Turn = true;
        }

        private void resetGame () {
            player1Points = 0;
            player2Points = 0;
            updatePointsText();
            resetBoard();
        }

        @Override
        protected void onSaveInstanceState (Bundle outState){
            super.onSaveInstanceState(outState);

            outState.putInt("roundCount", roundCount);
            outState.putInt("player1Points", player1Points);
            outState.putInt("player2Points", player2Points);
            outState.putBoolean("player1Turn", player1Turn);
        }

        @Override
        protected void onRestoreInstanceState (Bundle savedInstanceState){
            super.onRestoreInstanceState(savedInstanceState);

            roundCount = savedInstanceState.getInt("roundCount");
            player1Points = savedInstanceState.getInt("player1Points");
            player2Points = savedInstanceState.getInt("player2Points");
            player1Turn = savedInstanceState.getBoolean("player1Turn");
        }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);

                return true;
            }

            return super.onOptionsItemSelected(item);
        }
        public void OpenDialogue () {
            Dialogue dialogue = new Dialogue();
            dialogue.show(getSupportFragmentManager(), "Result");


        }
        public void OpenDialogue2 () {
            Dialogue2 dialogue2 = new Dialogue2();
            dialogue2.show(getSupportFragmentManager(), "Result");


        }
        public void OpenDialoguedraw () {
            Dialoguedraw dialoguedraw = new Dialoguedraw();
            dialoguedraw.show(getSupportFragmentManager(), "Result");


        }

    }
