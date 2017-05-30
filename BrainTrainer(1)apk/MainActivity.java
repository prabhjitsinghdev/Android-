package com.pj92singh.braintrainer1;
/* pj92singh
  * Prabhjit Singh
  * BRAIN TRAINER(1) app
  *
  * lets user play a game of adding 2 randomly generated numbers
  * the correct answer is also randomly "put" in one of the buttons
  *
* */

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button start_Button;
    //image
    ImageView brain;

    RelativeLayout layout2;

    //answers arraylist
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int location_of_correct;

    int score = 0;
    //aslo keep track of questions
    int numberQuestions = 0;
    TextView resultText;
    TextView pointsText;
    TextView sumText;
    TextView timerText;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainB;

    //playagain
    public void playAgain(View view){
        score = 0;
        numberQuestions = 0;

        //reset the timer textView
        timerText.setText("30s");
        pointsText.setText("0/0");
        resultText.setText("");
        //hide this button
        playAgainB.setVisibility(View.INVISIBLE);
        generateQuestions();
        //update the timer
        //timer
        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                //update the timer on the app
                timerText.setText( String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainB.setVisibility(View.VISIBLE);
                timerText.setText("0s");
                //lock all the buttons and display
                //the final score
                resultText.setText("Your score:" +Integer.toString(score)+ "/" + Integer.toString(numberQuestions));
            }
        }.start();

    }


   //generate questions
    public void generateQuestions(){
        //random
        Random rand = new Random();
        //creating random numbers form 0 - 50
        int a = rand.nextInt(51);
        int b = rand.nextInt(51);

        //update sum text view
        sumText.setText(Integer.toString(a)+ "+" +Integer.toString(b));


        //generate the 4 answers
        //one which is correct
        location_of_correct = rand.nextInt(4);

        //remove previous answers
        answers.clear();


        int incorrectAns;

        //loop thourgh the values for the buttons
        for(int j=0; j < 4; ++j){

            if(j == location_of_correct){
                //set the answers
                answers.add(a + b);
            }
            else{
                /* just incase if we create a random integer
                * that happens to be the right answer we must
                * prevent that
                * */
                incorrectAns = rand.nextInt(101);

                while( incorrectAns == a + b){

                    incorrectAns = rand.nextInt(101);
                }
                //if not create incorrect answer
                answers.add(incorrectAns);
            }
        }

        //update the buttons
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


    /* Works by looking at the tags to see
    * which buttons have been tapped
    * to check the answer
    * */

    public void chooseAnswer(View view){
        //test the tags
       // Log.i("Tag", (String) view.getTag());
        //yes it works

        //now compare the tags to the location of answer
        if(view.getTag().toString().equals(Integer.toString(location_of_correct))){
            //Log.i("Correct", "Check");
            //yes it works

            //now add 1 the their score
            //and get another question
            ++score;
            //update the label also
            resultText.setText("Correct :)");
        }
        else{
            resultText.setText("Wrong!");
        }
        ++numberQuestions;
        pointsText.setText(Integer.toString(score)+  "/" + Integer.toString(numberQuestions));
        generateQuestions();
    }

    public void Start(View view){
        //hide that intial button
        start_Button.setVisibility(View.INVISIBLE);
        brain.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.VISIBLE);
        //now
        //start the game
        playAgain(findViewById(R.id.PlayAgainbutton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get start button
        start_Button = (Button)findViewById(R.id.buttonStart);
        brain = (ImageView)findViewById(R.id.imageBrain);
        //rest of the buttons
        button0 = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgainB = (Button)findViewById(R.id.PlayAgainbutton);

        //textviews
        sumText = (TextView)findViewById(R.id.sumTextView);
        resultText = (TextView)findViewById(R.id.resultTextView);
        pointsText = (TextView)findViewById(R.id.pointsTextView);
        timerText = (TextView)findViewById(R.id.timerTextView);


        //layouts
        layout2 = (RelativeLayout)findViewById(R.id.layout2);
        //layout1 = (RelativeLayout)findViewById(R.id.layout1);




        //random
        /*
        Random rand = new Random();
        //creating random numbers form 0 - 50
        int a = rand.nextInt(51);
        int b = rand.nextInt(51);

        //update sum text view
        sumText.setText(Integer.toString(a)+ "+" +Integer.toString(b));


        //generate the 4 answers
        //one which is correct
        location_of_correct = rand.nextInt(4);

        int incorrectAns;

        //loop thourgh the values for the buttons
        for(int j=0; j < 4; ++j){

            if(j == location_of_correct){
                //set the answers
                answers.add(a + b);
            }
            else{
                /* just incase if we create a random integer
                * that happens to be the right answer we must
                * prevent that
                * */
                /*
                incorrectAns = rand.nextInt(101);

                while( incorrectAns == a + b){

                    incorrectAns = rand.nextInt(101);
                }
                //if not create incorrect answer
                answers.add(incorrectAns);
                */
            }
        }

        /*update the buttons
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }
}
*/