package com.pj92singh.eggtimer;
/* pj92singh
*  Prabhjit Singh
*  eggtimer app
*
*/
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //get the variables
    //seekbar
    SeekBar bar_timer;
    TextView num_timer;
    Button start_button;
    boolean active_counter = false;
    CountDownTimer countDownTimer;

    public void updateTimer(int secondsLeft){
        //when progress is changed
        //casted to get the number rounded down
        int minutes = (int) secondsLeft / 60;
        int seconds =  secondsLeft - (minutes * 60);
        //creating a second string to show the extra 0
        String second_string = Integer.toString(seconds);
        if (seconds <= 9){
            //adding a 0 if its anyting less than 10 seconds
            //including if its 0
            second_string = "0" +second_string;
        }

        //update the timer on the app
        num_timer = (TextView)findViewById(R.id.editTimeText);
         num_timer.setText(Integer.toString(minutes) +":" +second_string);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toast message at the beginning
        Toast.makeText(getApplicationContext(), "Use bar to set timer", Toast.LENGTH_LONG).show();

        bar_timer = (SeekBar)findViewById(R.id.seekBar);
        //timer text
         final TextView num_timer = (TextView)findViewById(R.id.editTimeText);
        //START button
        start_button = (Button)findViewById(R.id.button2);
        //STOP button
       // stop_button = (Button)findViewById(R.id.buttonStop);

        //set the max for the seekbar
        //lets say 10 mins
        bar_timer.setMax(600);
        //set the current progress to 30secs
        bar_timer.setProgress(30);


        //now update the label with seekbar listener
        bar_timer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    //start button listener
    public void StartButtonListen(View view){
        Log.i("START", "Pressed");
        if(active_counter == false) {
            active_counter = true;
            //disable seekbar
            //while counter is active
            //also change GO button to STOP
            start_button.setText("Stop!");
            bar_timer.setEnabled(false);
           countDownTimer = new CountDownTimer(bar_timer.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    //on each tick update the value of the timer
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    //when the timer hits 0
                    //update the timer textView
                    num_timer.setText("0:00");
                    //show it in the logs
                    //just to check =
                    // Log.i("Timer done", "Finished");
                    //also play sound
                    MediaPlayer airhorn = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    airhorn.start();
                    Toast.makeText(getApplicationContext(), "Timer finished!", Toast.LENGTH_SHORT).show();

                    //now reset it
                    num_timer.setText("0:30");
                    //set the seekbar to work and progress of 30 secs
                    bar_timer.setProgress(30);
                    //set to editable
                    bar_timer.setEnabled(true);
                    countDownTimer.cancel();
                    start_button.setText("Go");
                    active_counter = false;


                }
            }.start();
        }//end of IF
        else{
            //reset the timer
            num_timer.setText("0:30");
            //set the seekbar to work and progress of 30 secs
            bar_timer.setProgress(30);
            //set to editable
            bar_timer.setEnabled(true);
            countDownTimer.cancel();
            start_button.setText("Go");
            active_counter = false;
        }
    }

}
