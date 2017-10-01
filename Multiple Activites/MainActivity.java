package com.pj92singh.multipleactivties;
/*	pj92singh
*	Prabhjit Singh
*	Test of making different acitivites
*	and linking them and sharing variables
*
*	This is main activtiy 1 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    public void changeActivity(View view){
        //description of operation
        //intendedt to chagne activity

        //also to pass variables in activtites
        //do it in the intent

        Intent i = new Intent(getApplicationContext(), secondactivity.class);

        i.putExtra("Hello", "PJ");
        //to actulaly do it
        startActivity(i);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the list view
        ListView listView = (ListView)findViewById(R.id.listView);
        //fill in the listview using array
        final ArrayList<String> people = new ArrayList<String>();

        people.add("Doober");
        people.add("Coo Guy");
        people.add("Goku");
        people.add("Tim");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, people);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //do the transtion here
                Intent i = new Intent(getApplicationContext(), secondactivity.class);
                i.putExtra("name", people.get(position));
                startActivity(i);
            }
        });
    }
}
