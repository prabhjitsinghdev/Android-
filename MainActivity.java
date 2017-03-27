package com.pj92singh.basicphrase1;
/* Prabhjit Singh
* pj92singh
* 8 phrases which will be played
* in French. Help learn French
* */
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //sounds
    MediaPlayer hello_s, how_s, eve_s, naME_s, plz_s, welcome_s, living_s, doyou_s, pling_s;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hello_s = MediaPlayer.create(this, R.raw.hello);
        how_s = MediaPlayer.create(this, R.raw.howareyou);
        eve_s = MediaPlayer.create(this, R.raw.goodevening);
        naME_s = MediaPlayer.create(this, R.raw.mynameis);
        plz_s = MediaPlayer.create(this, R.raw.please);
        welcome_s = MediaPlayer.create(this, R.raw.welcome);
        living_s = MediaPlayer.create(this, R.raw.ilivein);
        doyou_s = MediaPlayer.create(this, R.raw.doyouspeakenglish);
        pling_s = MediaPlayer.create(this, R.raw.pling);

        //get all the buttons
        /*
        Button Hello = (Button) findViewById(R.id.button);
        Button how_are = (Button) findViewById(R.id.button2);
        Button evening = (Button) findViewById(R.id.button3);
        Button name = (Button) findViewById(R.id.button4);
        Button please = (Button) findViewById(R.id.button5);
        Button Welcome = (Button) findViewById(R.id.button6);
        Button lvin = (Button) findViewById(R.id.button7);
        Button doyou = (Button) findViewById(R.id.button8); */
        //at start make the pling sound
        pling_s.start();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // startActivity(
        //        new Intent(Settings.ACTION_SETTINGS));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Basic French Phrases application", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void buttonPress(View view){

        if(view == findViewById(R.id.button)){
            //play sound
            hello_s.start();
        }
        if(view == findViewById(R.id.button2)){
            //play sound
            how_s.start();
        }
        if(view == findViewById(R.id.button3)){
            //play sound
            eve_s.start();
        }
        if(view == findViewById(R.id.button4)){
            //play sound
            naME_s.start();
        }
        if(view == findViewById(R.id.button5)){
            //play sound
            plz_s.start();
        }
        if(view == findViewById(R.id.button6)){
            //play sound
            welcome_s.start();
        }
        if(view == findViewById(R.id.button7)){
            //play sound
            living_s.start();
        }
        if(view == findViewById(R.id.button8)){
            //play sound
            doyou_s.start();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
