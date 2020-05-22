package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class gameoptions extends AppCompatActivity implements gamemode_options1.FragmentAListener{
    //private static final String TAG = "fragment";


    private ImageButton colorHunt;
    private Fragment fragmentA;
    int mode;




    @Nullable
    @Override
    //public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.gameoptions, container, false);


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameoptions);
        fragmentA = new Fragment();

        gamemode_options1 change = new gamemode_options1();
        FragmentManager fm = getSupportFragmentManager();
    //oandroidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        /*getSupportFragmentManager().beginTransaction()
                .replace(R.id.ChOpt, fragmentA)
                .commit();*/
     colorHunt = findViewById(R.id.colorHunt);

     colorHunt.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             mode = 0;
             /*getSupportFragmentManager().beginTransaction()
                     .replace(R.id.ChOpt, fragmentA)
                     .commit();*/
            //((Connection)getActivity()).setViewPager(1);
             fm.beginTransaction().add(R.id.gameO, change).commit();

             Intent intent = new Intent(gameoptions.this, game_start.class);
             intent.putExtra("gamemode", mode );


         }
     });


    }


    @Override
    public void onInputASent(int inputt, int inputs, int z) {

    }
}