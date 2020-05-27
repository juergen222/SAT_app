package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;


public class GameStartActivity extends AppCompatActivity {

    enum GameColors
    {
        /*ArrayList<String> colors = new ArrayList<String>();     //colour index
        colors.add("failed");
        colors.add("black");
        colors.add("blue");
        colors.add("blue");
        colors.add("red");
        colors.add("red");
        colors.add("yellow");
        colors.add("yellow");
        colors.add("yellow");*/
        FAILED(0),
        BLACK(1),
        BLUE(2),
        RED(3),
        YELLOW(4);


        private int value;


        private GameColors(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
    //MqttAndroidClient client;
    int score = 0;


    int hit_notificationID;
    int timeReceive;
    int maxtime = 0;
    int maxScore = 5000;
    int ScoreReceived = 5000;
    int difficultyReceived = 0;
    int modeReceived = 0;
    /*
            1. Farbe anzeigen (random) randomColor
            2. Warten auf Treffer oder verfehlt userActed
            2,5. User acted
                3. Punkte berechnen
                4. Überprüfen Gewonnen-Verloren
                5. Go to 1.
            */


    int activeColor = 0;
    Player player1 = new Player(0, 1);
    Player player2 = new Player(0, 2);
    Player currentPlayer;
    int maxscore = 5000;
    int winner = 0;
    boolean inProgress = true;

    int mode = 0;
    int random;

    int vergleichswert = 0;
    boolean player1Active = true;
    boolean player2Active = false;
    int addpoints;          //points added to player score




    public void userActed(int colorRing)
    {
        if(!inProgress)
            return;
        if(colorRing == activeColor)
            currentPlayer.addScore(800);
        //TODO Logik für Punkte
        if(currentPlayer.score >= maxscore)
        {
            winner = currentPlayer.number;
            inProgress = false;
            Toast.makeText(this, "Winner"+ winner, Toast.LENGTH_SHORT).show();
            //TODO Result Activity
            fm.beginTransaction().add(R.id.win_lost, winner_declare).commit();
            return;
        }

        if(currentPlayer.number == 1)
            currentPlayer = player2;
        else
            currentPlayer = player1;

        activeColor = randomColor();
        updateText();
        start_command(1);
    }

   /* Runnable gameLoop = new Runnable() {
        @Override
        public void run() {


            //if (player1.score < maxscore && player2.score < maxscore) {
            // while (player1.score < maxscore || player2.score < maxscore) {

            // while (!new_score) {
            //}
            //new_score = false;
            //start_command();
            if (player1.score > maxscore || player2.score > maxscore) {

                vergleichswert = randomColor();



                // random number responsible for the colour generated

                if (score == vergleichswert) { //pointsystem

                    addpoints = 800;
                    hit_notificationID = 1;


                } else if (score == (vergleichswert + -1)) {
                    addpoints = 400;
                    hit_notificationID = 2;
                } else {
                    addpoints = 0;
                    hit_notificationID = 3;


                }
                Log.d("blab", "" + addpoints);


                appearNotification(hit_notificationID, hitNotification);


                if (player1Active) // turnsystem
                {

                    playerchanges(player1, addpoints);
                    player1Active = false;
                    player2Active = true;


                    updateText(scoreText, timeText, playerText, player1);


                } else if (player2Active) {
                    playerchanges(player2, addpoints);
                    player1Active = true;
                    player2Active = false;


                    updateText(scoreText, timeText, playerText, player2);


                }
            }
            handler.postDelayed(this, 300);
        }
    };*/


    TextView scoreText;
    TextView playerText;
    ImageView hitNotification;
    ImageView color;

    Handler handler = new Handler(Looper.getMainLooper());
    FragmentManager fm = getSupportFragmentManager();

    MqttAndroidClient client;

    ConnectionLostFragment fragment = new ConnectionLostFragment();
    WinnerMessageFragment winner_declare = new WinnerMessageFragment();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gamestart_activity);

        color = findViewById(R.id.color);
        scoreText = findViewById(R.id.score);
        playerText = findViewById(R.id.player);



        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this, "tcp://10.0.0.1:1883",
                clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(GameStartActivity.this, "ready to start game", Toast.LENGTH_LONG);
                    try{
                        client.subscribe("masterPhone", 0);
                    }catch(MqttException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    // TODO Dialog Box and back to Main Menu
                     fm.beginTransaction().add(R.id.connection_lost, fragment).commit();
                }
            });
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Toast.makeText(GameStartActivity.this, "ERROR Connecting", Toast.LENGTH_SHORT).show();

                    // TODO Dialog Box and back to Main Menu
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    userActed(Integer.parseInt(new String(message.getPayload())));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                }
            });

        } catch (MqttException e) {
            e.printStackTrace();
            Toast.makeText(this, "ERROR Connecting", Toast.LENGTH_SHORT).show();
            //TODO Dialog Box and back to Main Menu
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


        //Button startGame = findViewById(R.id.startGame);


        // color.setImageResource(R.drawable.white);

       /* Intent intent = getIntent(); // Date Transportation
        maxtime = intent.getIntExtra("Zeit", timeReceive);
        maxscore = intent.getIntExtra("MaxScore", ScoreReceived);
        difficulty = intent.getIntExtra("Difficulty", difficultyReceived);
        mode = intent.getIntExtra("Gamemode", modeReceived);
*/      /*startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        //Runnable myRunnable = new Runnable () {
        //while(player1.score < maxscore || player2.score < maxscore)

        // public void run() {

        startGame();

        //}

        //};
        //
    }

    private void start_command(int mode) {
        String topic = "masterPhone";
        String message = "start1";

        if(mode == 1)
        {
            message = "start1";
        }
        else if(mode == 3)
        {
            message = "start3";
        }

        try {
            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        activeColor = randomColor();
        currentPlayer = player1;
        updateText();
        start_command(1);
    }

    public class Player {  //player class

        int score;
        int number;

        public Player(int score, int number) {
            this.score = score;
            this.number = number;
        }

        public void addScore(int score) {
            this.score += score;

        }
    }

    public int randomColor() {      //generates random number and colour image
        random = new Random().nextInt(8) + 1;

        if (random == 1) {
            color.setImageResource(R.drawable.black);
        }
        if (random > 1 && random < 4) {
            color.setImageResource(R.drawable.blue);
        }

        if (random > 3 && random < 6) {

            color.setImageResource(R.drawable.red);
        }
        if (random > 5 && random < 9) {
            color.setImageResource(R.drawable.yellow);
        }
        return random;
    }

    public void appearNotification(int note, ImageView x) {


        boolean yes = true;


        x = findViewById(R.id.note);
        x.setVisibility(View.VISIBLE);
        if (note == 1) {
            x.setImageResource(R.drawable.hit_message);
        }
        if (note == 2) {
            x.setImageResource(R.drawable.close_note);
        }
        if (note == 3) {
            x.setImageResource(R.drawable.missed_note);
        }


        x.setVisibility(View.INVISIBLE);

    }

    @SuppressLint("SetTextI18n")
    public void updateText() {

        playerText.setText("" + currentPlayer.number);
        scoreText.setText("" + currentPlayer.score);



    }
}
