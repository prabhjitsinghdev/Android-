package com.pj92singh.weatherapp;
/*
* pj2singh
* Prabhjit Singh
*
* This app uses JSON data extracted from API's from openweather.org
* then gives the user the updated weather information for the city typed
*
* side stuff
* The app pushes the keyboard away when the button is pressed
* The app needs internet to work
* App is locked in portrait mode
* */

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText city_Name, output_Weather;

    //weatherMethod
    public void findWeather(View view) {
        //using the city name we need to find the weather
        //get it to the logs to see it works
        Log.i("CityName", city_Name.getText().toString());


        if (city_Name.getText().toString() != "") {
            //now we have to get the data
            //by calling our download task
            try {

                //hide keyboard
                InputMethodManager Mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                Mgr.hideSoftInputFromWindow(city_Name.getWindowToken(), 0);

                try {
                    String encodedCityName = URLEncoder.encode(city_Name.getText().toString(), "UTF-8");
                }
                catch (UnsupportedEncodingException e){
                    Toast.makeText(getApplicationContext(), "Invalid City name", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                DonwloadTask task = new DonwloadTask();
                task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + city_Name.getText().toString() + "&APPID=695ddcf3e06c4a679bca9e042d184142");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        else{
            String message2 = "Please enter a city";
            output_Weather.setText(message2);
        }
    }

    //download task
    public class DonwloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection URLConnection = null;

            try {
                url = new URL(urls[0]);

                URLConnection = (HttpURLConnection) url.openConnection();
                InputStream in = URLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data != -1){
                    char current_char = (char) data;

                    result += current_char;

                    data = reader.read();
                }

                return result;

            } catch (MalformedURLException e) {
                Toast.makeText(getApplicationContext(), "Could not connect to servers", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Could not connect to servers", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            return null;

        }
        //outside the do an background
        //will pass the result
        //aka the result
        //also lets us update the UI
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                //message string
                String message = "";

                //dealing with the JSON data
                //create a JSON object from a string
                // in this cased we called it jsonObject
                // if its a bad string so put in try/catch
                JSONObject jsonObject = new JSONObject(result);

                String weatherInfo = jsonObject.getString("weather");

                Log.i("Weather content", weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);

                for (int i = 0; i < arr.length(); i++) {

                    JSONObject jsonPart = arr.getJSONObject(i);

                    String main = "";
                    String Desc = "";

                    main = jsonPart.getString("main");
                    Desc = jsonPart.getString("description");


                    Log.i("main", jsonPart.getString("main"));
                    Log.i("description", jsonPart.getString("description"));

                    //but first test if either is empty
                    if (main != "" && Desc != "")
                    {
                        //send a message
                        message += main + ":: " + Desc + "\r\n";

                    }


                }
                //once we are done check if its all not empty
                if(message != ""){
                    //update the result and output it in EditText
                    output_Weather.setText(message);
                }
                else{
                    message = "Could not find city's weather";
                    output_Weather.setText(message);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city_Name = (EditText)findViewById(R.id.cityInput);
        output_Weather = (EditText)findViewById(R.id.editTextOutput);
    }
}
