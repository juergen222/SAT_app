package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class Connection extends AppCompatActivity {


    private ViewPager mViewpager;
    public String name1 = ("Connection");
    MainActivity guide = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewpager = (ViewPager) findViewById(R.id.options);
        ImageButton gameoptions;
        ImageButton gamestart;
        gameoptions = findViewById(R.id.spieloptionen);
        gamestart = findViewById(R.id.launchgame);



        gameoptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupViewPager(mViewpager);
                setViewPager(0);
            }

        });
    }





    public void setViewPager(int fragmentNumber){
        mViewpager.setCurrentItem(fragmentNumber);
    }

    private void setupViewPager(ViewPager viewpager){
        options adapter = new options(getSupportFragmentManager());
        adapter.addFragment(new gameoptions(), "gameoptions");
        adapter.addFragment(new gamemode_options1(), "Color-hunt options");
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
}
