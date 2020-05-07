package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment extends Fragment {
    private static final String TAG = "fragment";

    private ImageButton test;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verbinden, container, false);

        test = (ImageButton) view.findViewById(R.id.test);
        test.setOnClickListener(v -> Toast.makeText(getActivity(), "Teesssting .. Roger Roger", Toast.LENGTH_SHORT ).show());



        return view;
    }
}