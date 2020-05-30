package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends AppCompatActivity{

    private options optionsAdapter;
    private ViewPager mViewpager;
    public String name1 = ("SAT");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton connect = (ImageButton) findViewById(R.id.verbinden);
        ImageButton start = (ImageButton) findViewById(R.id.starten);
        ImageView target = (ImageView) findViewById(R.id.title);
        mViewpager = (ViewPager) findViewById(R.id.options);


        optionsAdapter = new options(getSupportFragmentManager());

        int imageResource = getResources().getIdentifier("@drawable/title1", null, this.getPackageName());
        target.setImageResource(imageResource);
        start.setOnClickListener(v -> openStart());

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*etupViewPager(mViewpager);
                setViewPager(0);*/

                openConnectionLostMessage();
            }


             });





        /*ImageButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            EinfachesFragment fragment = new EinfachesFragment();
            ft.add(R.id.cl, fragment);
            ft.addToBackStack(null);public static class EinfachesFragment extends Fragment{
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
            {
                return inflater.inflate(R.layout.blank_fragment,
                      container false)

            }
        }
            ft.commit();
        }*/
    }
        
    private void setupViewPager(ViewPager viewpager){
        options adapter = new options(getSupportFragmentManager());
        adapter.addFragment(new fragment(), "connect");
       /* adapter.addFragment(new options_fragment(), "configuration");
        adapter.addFragment(new options_fragment(), "configuration");*/
        viewpager.setAdapter(adapter);

    }
    public void setViewPager(int fragmentNumber){
        mViewpager.setCurrentItem(fragmentNumber);
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
            case R.id.about_us:
            Intent intent1 = new Intent (this, about_us.class);
            startActivity(intent1);
            return true;
            case R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public void openStart(){
        Intent intent = new Intent(this, Connection.class);
        startActivity(intent);

    }
    public void openConnectionLostMessage()
    {
        ConnectionLostDialogue dialogue = new ConnectionLostDialogue();
        dialogue.show(getSupportFragmentManager(),"coonection lost dialogue");

    }

    }

