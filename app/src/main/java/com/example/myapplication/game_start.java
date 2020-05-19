package com.example.myapplication;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;


public class game_start extends AppCompatActivity {

    boolean hit;
    MqttAndroidClient client;
    int score;
    volatile boolean new_score = false;

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
        //start_command();
        // TODO use score for game
        while(!new_score){}
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


        if(colors.get(score).equals("black"))
        {
            //hit black
        }


        //if ready for next score -> start_command();



    }

    /*private void start_command() {
        String topic = "masterPhone";
        String message = "start";
        try {
            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }*/

    public class player
    {

        int score;
        int playerNumber;
        boolean hit;
        player(int scre, int playerNumbr)
        {
            score = 0;
            score += scre;
            playerNumber = playerNumbr;
        }

        public void changescore(int score)
        {
            if(hit == true )
            {}

        }

    }

    /*private void setSubscription(){
        try{
            client.subscribe("masterPhone", 0);
        }catch(MqttException e){
            e.printStackTrace();
        }

    }*/




}