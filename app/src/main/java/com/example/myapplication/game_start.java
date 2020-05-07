package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class game_start extends Fragment {
    private static final String TAG = "fragment";


    boolean hit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_launch, container, false);




    return view;
    }


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



}