package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WinnerMessageFragment extends Fragment {
    private static final String TAG = "fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.winner_message, container, false);

        ImageButton return_menu = view.findViewById(R.id.return_menu);

        TextView player = view.findViewById(R.id.player);
        GameStartActivity startValue = new GameStartActivity();


        return_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), MainActivity.class);
                startActivity(intent1);
                player.setText(""+startValue.winner);

            }
        });

        return view;
    }
}