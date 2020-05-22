package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class Connection extends AppCompatActivity implements gamemode_options1.FragmentAListener {



    private ViewPager mViewpager;
    public String name1 = ("Connection");
    MainActivity guide = new MainActivity();
    gamemode_options1 einstellungenz;

    static int x;
    static int y;
    static int zx;
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


        gamestart.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Toast.makeText(Connection.this, "Zeit" + x, Toast.LENGTH_SHORT).show();
               System.out.printf("Time %d Score %d Difficulty %d", x, y, zx);
               Log.d("LOG", "hello" + x);

            }
        });






    }








    public void setViewPager(int fragmentNumber){ mViewpager.setCurrentItem(fragmentNumber);
    }

    private void setupViewPager(ViewPager viewpager){
        options adapter = new options(getSupportFragmentManager());
        //adapter.addFragment(new gameoptions(), "gameoptions");
        //adapter.addFragment(new gamemode_options1(), "Color-hunt options");
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
        Intent intent1 = new Intent(this, gameoptions.class);
        startActivity(intent1);

    }
    public void onInputASent(int inputt, int inputs, int z) {

        x = inputt;
        y = inputs;
        zx = z;
        Toast.makeText(Connection.this, "Zeit" + x, Toast.LENGTH_SHORT).show();

    }


}
