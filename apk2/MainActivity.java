package com.pj92singh.numshape;
/* Prabhjit Singh
* pj92singh 
* numbershape android app*/
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import 	android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void testnumber(View view) {
        EditText usersNum = (EditText) findViewById(R.id.numInput);
        String check_message = "";
        if (usersNum.getText().toString().isEmpty()) {
            check_message = "Please enter a number";
            Toast.makeText(getApplicationContext(), check_message, Toast.LENGTH_LONG).show();
        }
        else {
            Log.i("Users number", usersNum.getText().toString());

            Number myNumb = new Number();
            myNumb.num = Integer.parseInt(usersNum.getText().toString());
            //string to print out
            String message = "";

            if (myNumb.isSquare()) {
                if (myNumb.isTriangular()) {
                    message = myNumb.num + " Is both triangular and square!";
                } else
                    message = myNumb.num + " Is ONLY square, and NOT triangular";
            } else if (myNumb.isTriangular())
                message = myNumb.num + " Is ONLY triangular, and NOT square";
            else
                message = myNumb.num + " Is NOT square and NOT Triangular";


            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            System.out.println(myNumb.isSquare());
        }
    }
    class Number{
        int num;

        public boolean isTriangular(){
            int x=1, triang_num =1;

            while(triang_num < num){
                    x++;
                    triang_num += x;
            }
            if(triang_num == num)
                return true;
            else
                return false;
        }
        public boolean isSquare(){
            double sqr_root= Math.sqrt(num);
            if(sqr_root == Math.floor(sqr_root))
                return true;
            else
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
