package com.pj92singh.jsondemo;
/* 
pj92singh
Prabhjit Singh

Log test of api's and JSON data 
This app doesn't have an interface just deals with 
disecting JSON data and processing it through 
then finally sending it to the logs

*/
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //download task
    public class DonwloadTask extends AsyncTask<String, Void, String>{

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

                e.printStackTrace();
            } catch (IOException e) {
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

                    Log.i("main", jsonPart.getString("main"));
                    Log.i("description", jsonPart.getString("description"));

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DonwloadTask task = new DonwloadTask();
        task.execute("http://api.openweathermap.org/data/2.5/weather?q=London&APPID=695ddcf3e06c4a679bca9e042d184142");
    }
}
