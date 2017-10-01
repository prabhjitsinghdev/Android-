package com.pj92singh.multipleactivties;
/*  pj92singh
*   Prabhjit Singh
*   Test of making different acitivites
*   and linking them and sharing variables
*
*   This is activtiy 2
*
*   The clicked name will pop-up
*   here and shows that the name 
*   from the peoples' array is being
*   shared between activties
* */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class secondactivity extends AppCompatActivity {


    public void changeActivityHome(View view){
        //description of operation
        //intendedt to chagne activity
        Intent home = new Intent(getApplicationContext(), MainActivity.class);



        //to actulaly do it
        startActivity(home);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);


        TextView textView2 = (TextView)findViewById(R.id.textView2);

        //recieve the variable
        Intent i = getIntent();
        //logging it to test it works
        //Log.i("String Data", i.getStringExtra("Hello"));
        textView2.setText("Hello " + i.getStringExtra("name"));
    }
}
