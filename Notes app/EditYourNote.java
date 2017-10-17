package com.pj92singh.notes;
/*
* pj92singh
* the notes activtiy
* * pj92singh
* Prabhjit Singh
*
* Notes App
* allows user to save notes
* delete notes
* create notes**/
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class EditYourNote extends AppCompatActivity implements TextWatcher {

    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_your_note);

        EditText editText = (EditText)findViewById(R.id.editTextAc2);
        //now get the contents and put
        //it here
        Intent i = getIntent();
        noteId = i.getIntExtra("noteId", 0);

        if (noteId != -1){
          editText.setText(MainActivity.Notes.get(noteId));
        }

        //so after its opened
        //we want to update it
        //after the user writes to the note
        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //update the arraylist item
        MainActivity.Notes.set(noteId, String.valueOf(s));
        MainActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.pj92singh.notes", Context.MODE_PRIVATE);
        if (MainActivity.set == null){

            MainActivity.set = new HashSet<String>();

        }else {
            MainActivity.set.clear();
        }
        //save everything
        MainActivity.set.addAll(MainActivity.Notes);
        //to FIX THE BUG
        sharedPreferences.edit().remove("Notes").apply();
        //THEN update it
        sharedPreferences.edit().putStringSet("Notes", MainActivity.set).apply();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
