package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

import java.util.ArrayList;
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


    //int hit_notificationID;


    int ScoreReceived = 5000;

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
    Player secondaryPlayer;
    int maxscore = 5000;
    int winner = 0;
    int looser;
    boolean inProgress = true;
    int round = 1;
    boolean gameStarted = false;

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
        hitNotification.setVisibility(View.VISIBLE);

        shotMissed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 activeColor = 0;
            }
        });

        if(colors.get(colorRing).equals(colors.get(activeColor))) {
            currentPlayer.addScore(800);
            //hit_notificationID = 1;
            //Toast.makeText(GameStartActivity.this, "Color hit!", Toast.LENGTH_SHORT).show();
            hitNotification.setImageResource(R.drawable.hit_message);
        }
        else{
            //Toast.makeText(GameStartActivity.this, "Color missed!", Toast.LENGTH_SHORT).show();
            hitNotification.setImageResource(R.drawable.missed_note);
            //hit_notificationID = 3;
        }
        //appearNotification(hit_notificationID, hitNotification);

        //TODO Logik für Punkte
        if(currentPlayer.score >= maxscore)
        {
            winner = currentPlayer.number;
            looser = secondaryPlayer.number;

            inProgress = false;
            updateText();
            Toast.makeText(this, "Winner"+ winner, Toast.LENGTH_SHORT).show();
            //TODO Result Activity
            //fm.beginTransaction().add(R.id.win_lost, winner_declare).commit();
            Intent intent  = new Intent(GameStartActivity.this , ScoreboardActivity.class );
            intent.putExtra("Winner", winner);
            intent.putExtra("score", currentPlayer.score);
            intent.putExtra("scoreTwo", secondaryPlayer.score);
            intent.putExtra("looser", looser);
            startActivity(intent);
            return;
        }

        updateText();

        if(currentPlayer.number == 1) {
            currentPlayer = player2;
            secondaryPlayer = player1;
        }
        else
        {
            currentPlayer = player1;
            secondaryPlayer = player2;
            round++;
        }
    }

    TextView scoreText;
    TextView playerText;
    TextView roundText;
    ImageView hitNotification;
    ImageView color;
    ImageButton shotMissed;
    Button start_button;


    Handler handler = new Handler(Looper.getMainLooper());
    FragmentManager fm = getSupportFragmentManager();

    MqttAndroidClient client;

    ConnectionLostFragment fragment = new ConnectionLostFragment();
    WinnerMessageFragment winner_declare = new WinnerMessageFragment();

    ArrayList<String> colors = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gamestart_activity);

        color = findViewById(R.id.color);
        scoreText = findViewById(R.id.score);
        playerText = findViewById(R.id.player);
        roundText = findViewById(R.id.round);
        shotMissed = findViewById(R.id.shot_missed);
        start_button = findViewById(R.id.start_button);
        hitNotification = findViewById(R.id.note);

        colors.add("failed");
        colors.add("black");
        colors.add("blue");
        colors.add("blue");
        colors.add("red");
        colors.add("red");
        colors.add("yellow");
        colors.add("yellow");
        colors.add("yellow");


        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this, "tcp://10.0.0.1:1883",
                clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    //Toast.makeText(GameStartActivity.this, "connected", Toast.LENGTH_SHORT).show();
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
                    openConnectionLostMessage();
                     //fm.beginTransaction().add(R.id.connection_lost, fragment).commit();

                }
            });
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Toast.makeText(GameStartActivity.this, "ERROR Connecting", Toast.LENGTH_SHORT).show();

                    // TODO Dialog Box and back to Main Menu
                    openConnectionLostMessage2();
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    userActed(Integer.parseInt(new String(message.getPayload())));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                }
            });
            Toast.makeText(GameStartActivity.this, "Ready to start game", Toast.LENGTH_LONG).show();

        } catch (MqttException e) {
            e.printStackTrace();
            Toast.makeText(this, "ERROR Connecting", Toast.LENGTH_SHORT).show();
            //TODO Dialog Box and back to Main Menu
            openConnectionLostMessage();

        }


        //Button startGame = findViewById(R.id.startGame);


        // color.setImageResource(R.drawable.white);

       Intent intent = getIntent(); // Date Transportation

        maxscore = intent.getIntExtra("MaxScore", ScoreReceived);

        mode = intent.getIntExtra("Gamemode", modeReceived);

     /*startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        //Runnable myRunnable = new Runnable () {
        //while(player1.score < maxscore || player2.score < maxscore)

        // public void run() {

        //startGame();

        //}

        //};
        //
    }

    private void start_command(int mode) {
        String topic = "start_command";
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

    @SuppressLint("SetTextI18n")
    public void startGame(View v) {
        if (!gameStarted){
            gameStarted = true;
            activeColor = randomColor();
            currentPlayer = player1;
            secondaryPlayer = player2;
            updateText();
            start_button.setText("Next Round");
            start_command(1);
        }
        else {
            activeColor = randomColor();
            hitNotification.setVisibility(View.INVISIBLE);
            updateText();
            start_command(1);
        }
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

    /*public void appearNotification(int note, ImageView x) {


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

    }*/

    @SuppressLint("SetTextI18n")
    public void updateText() {

        playerText.setText("" + currentPlayer.number);
        scoreText.setText("" + currentPlayer.score);
        roundText.setText(""+ round);




    }

    public void openWinMessage()
    {


    }
    public void openConnectionLostMessage()
    {
        ConnectionLostDialogue dialogue = new ConnectionLostDialogue();
        dialogue.show(getSupportFragmentManager(),"coonection lost dialogue");

    }

    public void openConnectionLostMessage2()
    {
        ConnectionLostDialogue2 dialogue = new ConnectionLostDialogue2();
        dialogue.show(getSupportFragmentManager(),"coonection lost dialogue 2 ");

    }


}
