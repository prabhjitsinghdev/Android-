package com.pj92singh.audioslider;
/* Prabhjit Singh
  pj92singh
  Audio slider
  -Control the volume of the audio clip
  -Play/Pause the audio clip
  -Also rotating a image related, to the
  audio clip
  */
import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    MediaPlayer mymusic;
    //Audio manager needed to track
    //and align the track with the seekbar
    AudioManager audioMan;
    ImageView mrpoopy;

    //play on button listener
    public void play_on_press(View view){
        // mymusic = MediaPlayer.create(this, R.raw.poopy);
        //plays it on start of app
        mymusic.start();
        //rotate image
        mrpoopy =(ImageView)findViewById(R.id.image_mrp);
        if(mrpoopy.getAlpha()== 0) {
            mrpoopy.animate().alpha(1f).rotation(720f).setDuration(1500);
        }
        if(mrpoopy.getAlpha()== 1){
            mrpoopy.animate().rotation(720f).setDuration(400).alpha(0.5f);
        }
        if(mrpoopy.getAlpha()== 0.5){
            mrpoopy.animate().rotation(180f).setDuration(1500).alpha(0f);
        }

    }
    public void pause_on_press(View view){
        //mymusic = MediaPlayer.create(this, R.raw.poopy);
        //pause on press of button
        mymusic.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mymusic = MediaPlayer.create(this, R.raw.poopy);
       //int audio_len =  mymusic.getDuration();
        mrpoopy =(ImageView)findViewById(R.id.image_mrp);
        //part 1 seek bar
        //when the user moves the seek bar
        //the result is showed in the logs
        SeekBar volControl = (SeekBar) findViewById(R.id.seekBar);
        //--contine part1 of seekbar
        //where we add the setONSeekBarChangeListener()
        //----------------

        //making 2nd bar
        final SeekBar Sbar2 = (SeekBar) findViewById(R.id.seekBar2);


        //part2 of seek bar
        //-------------------
        //use the audioMan
        //use methods getSystemService
        //and Context.Audio_service
        //and cast it to the AudioManager type
        audioMan =  (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //now lets the get the max and current vol
        //using getStreamMaxVolume
        //and AudioManager.STREAM_MUSIC
        //STREAM_MUSIC for media, you can get others like alarm etc
        int maxVol = audioMan.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //now get the current volume
        int currentVol = audioMan.getStreamVolume(AudioManager.STREAM_MUSIC);


        //GOTO PART 3 SEEKBAR
        //-------------------

        //----CONT' PART 1 SEEKBAR ----
        //we wil 'catch' the event
        //using setOnSeekBarChangeListener()
        volControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            //implement our code here for this listener
            //using onProgressChanged <- built in func
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //prgoress intger aka i
                //tells us how far the bar was changed
                //boolean if we changed the code
                String st_i = Integer.toString(i);
                //print it to the logs first
                Log.i("Seekbar value", st_i);

                //set the seek bar
                //the 0 is the flag variable
                audioMan.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);

            }
            //if we want to know the beginging of how much user moved it
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //PART 3 SEEKBAR
        //using volControl from part1
        //set max
        volControl.setMax(maxVol);
        volControl.setProgress(currentVol);


        //part 4
        //for Sbar2
        //set the length of the audio file
        Sbar2.setMax(mymusic.getDuration());
        //now to link the durationg with the sbar2
        //using a timer
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Sbar2.setProgress(mymusic.getCurrentPosition());
            }
        }, 0, 75); //meaing run the code from the beginging
        //therefore its set at 0
        //and run every second aka 1000 milliseconds
        //since its a short audio file
        //change it to match
        Sbar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar Sbar2, int i2, boolean b) {
                //prgoress intger aka i
                //tells us how far the bar was changed
                //boolean if we changed the code
                String st_i2 = Integer.toString(i2);
                //print it to the logs first
               // Log.i("Sbar2 value", st_i2);

                //move the audio to the postion moved
                mymusic.seekTo(i2);

                //set the seek bar
                //the 0 is the flag variable
                audioMan.setStreamVolume(AudioManager.STREAM_MUSIC, i2, 0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar Sbar2) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar Sbar2) {

            }

        });

    }
}
