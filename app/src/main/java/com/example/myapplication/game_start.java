package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class game_start extends AppCompatActivity {

    boolean hit;
    //MqttAndroidClient client;
    int score =4;
    int time;
    volatile boolean new_score = false;
    ImageView color;
    ImageView hit_notification;

    int hit_notificationID;

    int zeit, maxscore, difficulty;
    int gamemode;
    int mode;
    int random;
    int timeReceive;
    int maxtime;
    int maxScore;
    int ScoreReceived;
    int difficultyReceived;
    int modeReceived;
    connection_lost fragment = new connection_lost();
    FragmentManager fm = getSupportFragmentManager();





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





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
                     fm.beginTransaction().add(R.id.connection_lost, fragment).commit();
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
        setContentView(R.layout.game_launch);
        color = findViewById(R.id.color);

        color.setImageResource(R.drawable.white);

        Intent intent = getIntent(); // Date Transportation
        maxtime = intent.getIntExtra("Zeit", timeReceive);
        maxscore = intent.getIntExtra("MaxScore", ScoreReceived);
        difficulty = intent.getIntExtra("Difficulty", difficultyReceived);
        mode = intent.getIntExtra("Gamemode", modeReceived);

        try {
            start_game();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void start_game() throws InterruptedException {

        FragmentManager fm = getSupportFragmentManager();
        TextView scoreText = findViewById(R.id.score);
        TextView TimeText = findViewById(R.id.time);
        TextView PlayerText = findViewById(R.id.player);
        int vergleichswert;
        boolean player1Active = true;
        boolean player2Active = false;
        int addpoints;          //points added to player score




        player player1 = new player(0, 1);
        player player2 = new player(0, 2);

        //start_command();
        // TODO use score for game
        while (!new_score) {
        }
        ArrayList<String> colors = new ArrayList<String>();     //colour index
        colors.add("failed");
        colors.add("black");
        colors.add("blue");
        colors.add("blue");
        colors.add("red");
        colors.add("red");
        colors.add("yellow");
        colors.add("yellow");
        colors.add("yellow");






        if (mode == 0) { // if mode = colorHunt

            while (time < maxtime || player1.score < maxscore || player2.score < maxscore) {

               // while (!new_score) {
               //}
                //new_score = false;
                //start_command();

                vergleichswert = randomImage(); // random number responsible for the colour generated

                if(score == vergleichswert) { //pointsystem

                    addpoints = 800;
                    hit_notificationID = 1;



                }
                else if(score == (vergleichswert +-1 ) )
                {
                    addpoints = 400;
                    hit_notificationID =2;
                }
                else
                {
                    addpoints = 0;
                    hit_notificationID =3;


                }
                appearNotification(hit_notificationID);

                if (player1Active == true) // turnsystem
                {

                    playerchanges(player1, addpoints);
                    player1Active = false;
                    player2Active =true;
                    updateText(scoreText, TimeText, PlayerText, player1);

                }
                else
                {
                    playerchanges(player2, addpoints);
                    player1Active = true;
                    player2Active = false;
                    updateText(scoreText, TimeText, PlayerText, player2);

                }


                //if ready for next score -> start_command();
            }

           if (player1.score > player2.score)
           {
               //fm.beginTransaction().add(R.id.wi, fragment).commit();
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

    public class player {  //player class

        int score;
        int playerNumber;
        boolean hit;

        public player(int scre, int playerNumbr) {
            score = 0;
            score += scre;
            playerNumber = playerNumbr;
        }



    }

    public int randomImage() {      //generates random number and colour image
        random = new Random().nextInt(8) + 1;
        int eQ;

        eQ = random;

        if (random == 1) ;
        color.setImageResource(R.drawable.black);
        if (random > 1 && random < 4) ;
        color.setImageResource(R.drawable.blue);
        if (random > 3 && random < 6) ;
        color.setImageResource(R.drawable.red);
        if (random > 5 && random < 9) ;
        color.setImageResource(R.drawable.yellow);
        return eQ;
    }

    public void playerchanges(player x, int value )
    {
        x.score = x.score + value;


    }

    public void appearNotification(int note) throws InterruptedException {
        hit_notification = findViewById(R.id.note);
        hit_notification.setVisibility(View.VISIBLE);
        if(note == 1)
        {
            hit_notification.setImageResource(R.drawable.hit_message);
        }
        if(note == 2)
        {
            hit_notification.setImageResource(R.drawable.close_note);
        }
        if(note == 3)
        {
            hit_notification.setImageResource(R.drawable.missed_note);
        }

        TimeUnit.SECONDS.sleep(10);
        hit_notification.setVisibility(View.INVISIBLE);

    }

    public void updateText(TextView x, TextView y, TextView z, player p)
    {
        x.setText("" + p.score);
        y.setText(""+time);
        z.setText("" + p.playerNumber);

    }






    /*private void setSubscription(){
        try{
            client.subscribe("masterPhone", 0);
        }catch(MqttException e){
            e.printStackTrace();
        }

    }*/



}