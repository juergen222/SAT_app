package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreboardActivity extends AppCompatActivity {

    TextView firstPlayer =findViewById(R.id.first_player);
    TextView secondPlayer =findViewById(R.id.second_player);
    TextView thirdPlayer =findViewById(R.id.third_player);
    TextView firstScore =findViewById(R.id.first_score);
    TextView secondScore =findViewById(R.id.second_score);
    TextView thirdScore =findViewById(R.id.third_score);
    ImageButton reset = findViewById(R.id.reset_button);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.scoreboard_layout);
        int winnerReceived = 0;
        int winner;
        int looser;
        int looserReceived = 0;
        int scoreOne;
        int scoreOneReceived = 0;
        int scoreTwo;
        int scoreTwoReceived = 0;


        Intent intent = getIntent();
       winner = intent.getIntExtra("Winner", winnerReceived );
        scoreOne = intent.getIntExtra("score", scoreOneReceived );
        scoreTwo = intent.getIntExtra("scoreTwo", scoreTwoReceived );
        looser =intent.getIntExtra("looser", looserReceived );

        firstPlayer.setText(""+ winner);
        firstScore.setText(""+scoreOne);
        secondPlayer.setText(""+ looser);
        secondScore.setText(""+scoreTwo);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent1);

            }
        });


    }
}
