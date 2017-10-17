package com.pj92singh.notes;
/*
* pj92singh
* Prabhjit Singh
*
* Notes App
* allows user to save notes
* delete notes
* create notes*/
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> Notes = new ArrayList<>();
    static  ArrayAdapter arrayAdapter;
    static Set<String> set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewJ = (ListView) findViewById(R.id.listView);

        //PART 3 use Sharedpref
        //to save the notes
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.pj92singh.notes", Context.MODE_PRIVATE);
        //lets test to see if we already have
        //saved notes
        //using sets
        set = sharedPreferences.getStringSet("Notes", null);

        if (set != null){
            Notes.clear();
            Notes.addAll(set);
            //this will not copy it twice
            //allowing us to convert our set
            //to an array which will
            //work with our arrayadapter
        }else {
            //if empty
            //add example note
            Notes.add("example note");
            //since its null we need to intialize it
            set = new HashSet<String>();
            //now save this as a set
            set.addAll(Notes);
            sharedPreferences.edit().putStringSet("Notes", set).apply();
        }



        //array adapter
         arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Notes);
        listViewJ.setAdapter(arrayAdapter);

        //when note tapped
        //open new activity
        listViewJ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), EditYourNote.class);
                i.putExtra("noteId", position);
                startActivity(i);
            }
        });

        //long press listerner
        listViewJ.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //after long clicked
                //alert the user
                //can't use this for applcation contexnt
                //bc "this" is reffering to OnItemLongClickListenere
                //( new AlertDialog.Builder(getApplicationContext())  )
                //we want the applicationContext of OUR MAINACITIVTY
                // so use it instead
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure about the delete?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    //remove the note they LONG clicked on
                                    Notes.remove(position);
                                     shared_pref();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                //change the return false
                //to return true
                //to avoid both long and normal click
                return true;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                //let the user know the
                //button is tapped successfully
                Toast.makeText(this, "Starting new note", Toast.LENGTH_SHORT).show();
                //add new note
                Notes.add("");
                SharedPreferences sharedPreferences = this.getSharedPreferences("com.pj92singh.notes", Context.MODE_PRIVATE);
                //check everyting else
                //if set is empty or not
                if (set == null){

                    set = new HashSet<String>();

                }else {
                    set.clear();
                }
                //save everything
                set.addAll(Notes);
                //TO FIX BUG
                sharedPreferences.edit().remove("Notes").apply();
                //REMOVE THEN ADD
                sharedPreferences.edit().putStringSet("Notes", set).apply();

                arrayAdapter.notifyDataSetChanged();
                //then launch intent
                Intent i = new Intent(getApplicationContext(), EditYourNote.class);
                i.putExtra("noteId", Notes.size()-1);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);

        }

    }
    public void shared_pref(){
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.pj92singh.notes", Context.MODE_PRIVATE);
        //check everyting else
        //if set is empty or not
        if (set == null){

            set = new HashSet<String>();

        }else {
            set.clear();
        }
        //save everything
        set.addAll(Notes);
        //TO FIX BUG
        sharedPreferences.edit().remove("Notes").apply();
        //REMOVE THEN ADD
        sharedPreferences.edit().putStringSet("Notes", set).apply();

        //update the array adapter
        arrayAdapter.notifyDataSetChanged();
    }
}
