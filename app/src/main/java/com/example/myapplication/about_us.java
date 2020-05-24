package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class about_us extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        TextView introduction;
        TextView tobias;
        TextView juergen;
        introduction = findViewById(R.id.intro);
        tobias = findViewById(R.id.tobias);
        juergen = findViewById(R.id.juergen);
        //introduction.setText("SAT is a project, which aims to improve ones archery experience. The two developsers behind this whole system are Jürgen Ederer and Tobias Zeller. " +
              //  "it works   ");

        //tobias.setText("Tobias main goal was to setup the whole system which detects where the arrows hit the target, so that includes the software which controlls the cameras and the sensor. His main other task was to setup the communcation between the app and the rasperry pi which controlls the hardware ");

        //juergen.setText("Juergen's task was to do the nesessairy chores in the first half of the project, for example ordering the components, construction the wooden target setting up hardware with rasperry, aswell doing the reserach, in the second half his main task was to create and design the app");


    }
}