package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class gameoptions extends Fragment {
    private static final String TAG = "fragment";


    private ImageButton colorHunt;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gameoptions, container, false);

        //androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

     colorHunt = view.findViewById(R.id.colorHunt);
     colorHunt.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             ((Connection)getActivity()).setViewPager(1);

         }
     });

     return view;
    }
}