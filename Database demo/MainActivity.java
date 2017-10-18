package com.pj92singh.databasedemo;
/*
* pj92singh
* Prabhjit Singh
*
* database demo apk is a test of SQLite databse creation
* and extraction of data from it into the logs
* */
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //STAGE 1 CREATE DATABASE
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            //put a table in the database
            //it will have name and age
            //as the paramaters
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

            //now insert some data
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('PJ', 25)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('LBJ', 33)");


            //get the data out of the database
            //using a cursor
            //to loop thorugh the results
            //then preform an action
            //null is where you can have a signal to
            //cancel when u have really big queires
            Cursor c = myDatabase.rawQuery("SELECT  * FROM users", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();
            while(c != null){
                Log.i("The name", c.getString(nameIndex));
                Log.i("The age", Integer.toString(c.getInt(ageIndex)));

                c.moveToNext();

            }

        }catch (Exception e){

            e.printStackTrace();
        }

        /*
        try {
            //2nd database
            SQLiteDatabase historic_events = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);

            historic_events.execSQL("CREATE TABLE IF NOT EXISTS historic_event (day VARCHAR, year VARCHAR)");

            historic_events.execSQL("INSERT INTO historic_event (day, year) VALUES ('Canada Independace day', 'July 1, 1867')");
            historic_events.execSQL("INSERT INTO historic_event (day, year) VALUES ('India Independance day', 'August 15, 1947')");
            historic_events.execSQL("INSERT INTO historic_event (day, year) VALUES ('USA Independance day', 'July 4, 1776')");

            Cursor cursor = historic_events.rawQuery("SELECT * FROM historic_event", null);

            int dayIndex = cursor.getColumnIndex("day");
            int yearIndex = cursor.getColumnIndex("year");

            cursor.moveToFirst();
            while (cursor != null) {
                Log.i("Historic day", cursor.getString(dayIndex));
                Log.i("Historic date", cursor.getString(yearIndex));

                cursor.moveToNext();
            }

        }catch (Exception e2){

            e2.printStackTrace();
        }

        */

    }
}
