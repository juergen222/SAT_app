package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
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


    public gamemode_options1(){


    }

    long time;
    int score;
    boolean colorChooseable;
    String zeit = "0";
    String maxScore = "0";
    int x;
    int y;
    static int z;
    int difficulty;
    private FragmentAListener listener;
    boolean configurated =false;
    private ImageButton ok;
    int gamemode = 1; // 1 is colourhunt

    public interface FragmentAListener{

        void onInputASent(int inputt, int inputs, int z);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gamemode_options1, container, false);


        ok = view.findViewById(R.id.ok_button);

        //androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        /*EditText time = view.findViewById(R.id.time);
        EditText maxscore = view.findViewById(R.id.maxScore);
        zeit = time.getText().toString();
        maxScore = maxscore.getText().toString();
        if (zeit.length()== 0 || maxScore.length() == 0)


        x = Integer.parseInt(zeit);
        y = Integer.parseInt(maxScore);*/
        EditText time = view.findViewById(R.id.time);
        EditText maxscore = view.findViewById(R.id.maxScore);

        ArrayAdapter<CharSequence> difficulties = ArrayAdapter.createFromResource(getActivity() , R.array.difficultys, android.R.layout.simple_spinner_item);
        //ArrayAdapter<String> difficulties = new ArrayAdapter<String>(gamemode_options1.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficultys));
        Spinner difficulty = view.findViewById(R.id.difficulty);
        difficulties.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(difficulties);
        difficulty.setOnItemSelectedListener(this);



            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                /*if (zeit.length()== 0 || maxScore.length() == 0)
                    return;*/
                    zeit = time.getText().toString();
                    maxScore = maxscore.getText().toString();



                    x = Integer.parseInt(zeit);
                    y = Integer.parseInt(maxScore);
                    listener.onInputASent(x, y, z);




                    Intent intent = new Intent(getActivity(), Connection.class);
                    startActivity(intent);

                    Intent intent1 = new Intent(getActivity().getBaseContext(), game_start.class);
                    intent1.putExtra("Zeit",x);
                    intent1.putExtra("MaxScore", y);
                    intent1.putExtra("Difficulty", z);


                    getActivity().startActivity(intent1);



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
                z = 1;
                return;

            case "Medium":
                z = 2;
                return;

            case "Hard":
                z= 3;
                return;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}