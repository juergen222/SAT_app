package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class GameOptionsActivity extends AppCompatActivity implements GamemodeOptionsFragment.FragmentAListener{
    //private static final String TAG = "fragment";


    private ImageButton colorHunt;
    private Fragment fragmentA;
    int mode;




    @Nullable
    @Override
    //public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.GameOptionsActivity, container, false);


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameoptions);
        fragmentA = new Fragment();

        GamemodeOptionsFragment change = new GamemodeOptionsFragment();
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

            //((Connection)getActivity()).setViewPager(1);
             fm.beginTransaction().add(R.id.gameO, change).commit();



         }
     });


    }


    @Override
    public void onInputASent(int inputt, int inputs, int z) {

    }
}