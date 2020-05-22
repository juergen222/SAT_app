package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;


public class game_start extends AppCompatActivity {

    boolean hit;
    //MqttAndroidClient client;
    int score;
    volatile boolean new_score = false;
    ImageView color;
    gamemode_options1 einstellungen;
    int zeit, maxscore, difficulty;
    int gamemode;
    int mode;
    int random;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zeit = einstellungen.x;
        maxscore = einstellungen.y;
        difficulty = einstellungen.z;
        gamemode = einstellungen.gamemode;
        color = findViewById(R.id.color);
        color.setImageResource(R.color.white);




        /*String clientId = MqttClient.generateClientId();
        MqttAndroidClient client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883",
                        clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(game_start.this, "ready to start game", Toast.LENGTH_LONG);
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    // TODO Fragment for connection failure
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            // TODO Fragment for connection failure
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                new_score = true;
                score = Integer.parseInt(new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        start_game();

    }

    public void start_game() {
        int time = 0;
        int maxScore = 0;
        int vergleichswert;



        player player1 = new player(0, 1);
        player player2 = new player(0, 2);

        //start_command();
        // TODO use score for game
        while (!new_score) {
        }
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("failed");
        colors.add("black");
        colors.add("blue");
        colors.add("blue");
        colors.add("red");
        colors.add("red");
        colors.add("yellow");
        colors.add("yellow");
        colors.add("yellow");


        //start_command();
        while (!new_score) {
        }
        new_score = false;


        if (mode == 0) {

            while (time < maxtime || player1.score < maxscore || player2.score < maxscore) {

                vergleichswert = randomImage();

                if (score == vergleichswert) {

                    //hit black
                }


                //if ready for next score -> start_command();
            }


        }
    }

    /*private void start_command(int mode) {
        String topic = "masterPhone";
        String message;

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
    }*/

    public class player {

        int score;
        int playerNumber;
        boolean hit;

        public player(int scre, int playerNumbr) {
            score = 0;
            score += scre;
            playerNumber = playerNumbr;
        }

        public void changescore(int score) {
            if (hit == true) {
            }

        }

    }

    public int randomImage() {
        random = new Random().nextInt(8) + 1;
        int eQ;

        eQ = random;

        if (random == 1) ;
        color.setImageResource(R.color.black);
        if (random > 1 && random < 4) ;
        color.setImageResource(R.color.blue);
        if (random > 3 && random < 6) ;
        color.setImageResource(R.color.red);
        if (random > 5 && random < 9) ;
        color.setImageResource(R.color.yellow);
        return eQ;
    }



    /*private void setSubscription(){
        try{
            client.subscribe("masterPhone", 0);
        }catch(MqttException e){
            e.printStackTrace();
        }

    }*/



}