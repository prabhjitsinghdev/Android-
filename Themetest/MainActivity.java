package com.pj92singh.themetest;
/* pj92singh
 * Prabhjit Singh
 * This app is meant as a test for Android Style/Theme editor
 *
 * Also settings Activity 
 *  */
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import static java.lang.Boolean.getBoolean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set the App theme
        //default light theme
        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("AppTheme", false)){
            setTheme(R.style.AppTheme);
        }
        //else use the dark theme if it's set
        else if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("AppTheme.dark", true)){
            setTheme(R.style.AppTheme_dark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //link and apply the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.layout1_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //when items from menu are clicked

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.changeThemeButton:
                Toast.makeText(this, "Theme Editor", Toast.LENGTH_SHORT).show();
                //launch settings
                Intent i_Settings = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i_Settings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
