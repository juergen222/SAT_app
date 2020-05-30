package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class Connection extends AppCompatActivity implements GamemodeOptionsFragment.FragmentAListener {



    private ViewPager mViewpager;
    public String name1 = ("Connection");
    MainActivity guide = new MainActivity();
    GamemodeOptionsFragment einstellungenz;

    static int x;
    static int y;
    static int zx;
    int maxtime;
    int maxscore;
    int difficulty;
    int mode;
    int timeReceive;
    int ScoreReceived;
    int difficultyReceived;
    int modeReceived;
    boolean configurated;
    boolean configuratedReceived = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewpager = (ViewPager) findViewById(R.id.options);
        ImageButton gameoptionsb;
        ImageButton gamestart;
        gameoptionsb = findViewById(R.id.spieloptionen);
        gamestart = findViewById(R.id.launchgame);

        gameoptionsb.setOnClickListener(v -> openOptions());
        onInputASent(x, y ,zx);
        Intent intent = getIntent();
       // maxtime = intent.getIntExtra("Zeit", timeReceive);
        maxscore = intent.getIntExtra("MaxScore", ScoreReceived);
        difficulty = intent.getIntExtra("Difficulty", difficultyReceived);
        mode = intent.getIntExtra("Gamemode", modeReceived);
        configurated = intent.getBooleanExtra("configurated", configuratedReceived);
        //Toast.makeText(Connection.this, "Zeit" + maxscore, Toast.LENGTH_SHORT).show();

        if(configurated) {

            gamestart.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {

                    Intent intent1 = new Intent(getBaseContext(), GameStartActivity.class);
                    // intent1.putExtra("Zeit", maxtime);
                    intent1.putExtra("MaxScore", maxscore);
                    intent1.putExtra("Difficulty", difficulty);
                    intent1.putExtra("Gamemode", mode);


                    startActivity(intent1);

                }
            });
        }
        else
        {
            Toast.makeText(Connection.this, "You need to configurate", Toast.LENGTH_SHORT).show();}





    }








    public void setViewPager(int fragmentNumber){ mViewpager.setCurrentItem(fragmentNumber);
    }

    private void setupViewPager(ViewPager viewpager){
        options adapter = new options(getSupportFragmentManager());
        //adapter.addFragment(new GameOptionsActivity(), "GameOptionsActivity");
        //adapter.addFragment(new GamemodeOptionsFragment(), "Color-hunt options");
       /* adapter.addFragment(new options_fragment(), "configuration");
        adapter.addFragment(new options_fragment(), "configuration");*/
        viewpager.setAdapter(adapter);

    }
    /*public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.config:
                setViewPager(0);
                return true;

            case R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }*/

    public void openOptions(){
        Intent intent1 = new Intent(this, GameOptionsActivity.class);
        startActivity(intent1);

    }
    public void onInputASent(int inputt, int inputs, int z) {

        x = inputt;
        y = inputs;
        zx = z;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.config:
                setViewPager(0);
                return true;

            case R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}
