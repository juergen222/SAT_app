package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class gamemode_options1 extends Fragment implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "fragment";


    long time;
    int score;
    boolean colorChooseable;
    String zeit;
    String maxScore;
    int x;
    int y;
    int difficulty;
    private FragmentAListener listener;
    boolean configurated =false;

    public interface FragmentAListener{

        void onInputASent(int inputt, int inputs);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gamemode_options1, container, false);
        ImageButton ok = null;

        ok.findViewById(R.id.ok_button);

        //androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        EditText time = view.findViewById(R.id.time);
        EditText maxscore = view.findViewById(R.id.maxScore);
        zeit = time.getText().toString();
        maxScore = maxscore.getText().toString();
        x = Integer.parseInt(zeit);
        y = Integer.parseInt(maxScore);
        

        ArrayAdapter<CharSequence> difficulties = ArrayAdapter.createFromResource(getActivity() , R.array.difficultys, android.R.layout.simple_spinner_item);
        //ArrayAdapter<String> difficulties = new ArrayAdapter<String>(gamemode_options1.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficultys));
        Spinner difficulty = view.findViewById(R.id.difficulty);
        difficulties.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(difficulties);
        difficulty.setOnItemSelectedListener(this);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onInputASent(x, y);


            }
        });



        configurated = true;




        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener){
            listener =  (FragmentAListener) context;

        }
        else {
            throw new RuntimeException(context.toString() + "must implement FragmentALisener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null; 
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        switch (text)
        {
            case "Easy":
                difficulty = 0;
                return;

            case "Medium":
                difficulty = 1;
                return;

            case "Hard":
                difficulty = 2;
                return;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}