package com.pj92singh.hackernews;
/* pj92singh
* Prabhjit Singh
* 
* Hacker News App 
* using their api for JSON data, 
* extract the top articles, info/URL
* allow the user to read the articles in the app
* using WebView.
*(load 15 articles)
* 
* requires internet access

*/
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


   //create map to save ID's
   //and save title
    Map<Integer, String>articleURLs = new HashMap<Integer, String>();
    Map<Integer, String>articleTitles = new HashMap<Integer, String>();
    ArrayList<Integer>articleIDs = new ArrayList<Integer>();

    /* 3rd task dealing with DESING */
    ArrayList<String> titles_list  = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    ArrayList<String>urls_list = new ArrayList<String>();
    // content array list
    ArrayList<String>content = new ArrayList<String>();


    /* SECOND TASK
    * CREATING database to store all the data
    * and then using it for the app*/
    SQLiteDatabase articlesDB;
    //SQLiteDatabase articlesDB = this.openOrCreateDatabase("Articles", MODE_PRIVATE, null);


    /* FIRST TASK
    * create download task in the backgorund
    * to get all the data. (using asyn-task)*/
    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            //URL to hold the url
            URL url;
            //http intially null
            HttpsURLConnection urlConnection = null;

            try{

                //so we must first create
                //the url
                url = new URL(urls[0]);
                urlConnection = (HttpsURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1){
                    char current = (char)data;
                    result += current;
                    data = reader.read();
                }

            /*MOVED THE SQL DELETE AND OBTAINING JSON DATA HERE
              * so this way it happens in the background
               * and we can load the UI up, update it and
               * carry on using the app!*/
                //so take the numbers
                JSONArray jsonArray = new JSONArray(result);

                //TASK 2 continued..
                //before getting all the data
                // lets clear all the data in the SQL database
                articlesDB.execSQL("DELETE FROM articles");


                // for (int i=0; i < jsonArray.length(); i++){
                for (int i=0; i < 15; i++){
                    //it worked
                    //Log.i("Article ID jSON array:", jsonArray.getString(i));

                    String articleID = jsonArray.getString(i);

                   // DownloadTask getArticle_task = new DownloadTask();
                    /* Alter this line of code
                    *  String articleinfo = getArticle_task.execute("https://hacker-news.firebaseio.com/v0/item/"+  articleID +".json?print=pretty").get();
                    * so that it doesn't call the asyntask
                    */
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/"+  articleID +".json?print=pretty");
                    //open connection
                    urlConnection  = (HttpsURLConnection)url.openConnection();
                    //using the same input stream
                    //"in"
                    in = urlConnection.getInputStream();
                    reader = new InputStreamReader(in);
                    data = reader.read();
                    String article_info = "";

                    while (data != -1){
                        char current  = (char)data;
                        article_info += current;
                        data = reader.read();
                    }
                    //so since we have the id
                    //we want to get the title
                    //and url which can be used
                    // to load up in a webview
                    //using JSON Object
                    JSONObject jsonObject = new JSONObject(article_info);
                    String article_title = jsonObject.getString("title");
                    String article_url = jsonObject.getString("url");

                    //get some of the content
                    //store it offline
                   /* url = new URL(article_url);
                    //open connection
                    urlConnection  = (HttpsURLConnection)url.openConnection();
                    //using the same input stream
                    //"in"
                    in = urlConnection.getInputStream();
                    reader = new InputStreamReader(in);
                    data = reader.read();
                    String article_content = "";

                    while (data != -1){
                        char current  = (char)data;
                        article_info += current;
                        data = reader.read();
                    }
                    */

                /*
                Log.i("article title", article_title);
                Log.i("article url", article_url);
                */
                    //add the ID to artilce ID array list
                    articleIDs.add(Integer.valueOf(articleID));
                    //add the title
                    articleTitles.put(Integer.valueOf(articleID), article_title);
                    //add the url
                    articleURLs.put(Integer.valueOf(articleID), article_url);

                    //but time to save this info
                    //into an SQL query then load from there
                    //BUT WE GET AN ERROR WITH SOME URL'S
                    // OR SOME TITLES
                    // in the regualr command
                /* articlesDB.execSQL("INSERT INTO articles (articleID, url, title) VALUES ("+articleID +",' "+article_url +"',' "+article_title +"')");
                */
                    // SO TO FIX AROUND THAT
                    //using prepard statements
                    String sql_command = "INSERT INTO articles (articleID, url, title, content) VALUES (?, ?, ?, ?)";
                    //convert it to a sql statement
                    SQLiteStatement statement = articlesDB.compileStatement(sql_command);
                    //now pu the variables into the sql_commnand string
                    //like so
                    statement.bindString(1, articleID);
                    statement.bindString(2, article_url);
                    statement.bindString(3, article_title);
                   // statement.bindString(4, article_content);

                    statement.execute();



                }
            /*MOVED CODE */
            }catch (Exception e){
                 e.printStackTrace();
            }

            return  result;
        }
        //using this onPostExecute
        //udpate the UI using
        //update listview method
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //update the UI
            updatedListView();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //design TASK 3
        ListView titleslistView = (ListView)findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles_list);
        titleslistView.setAdapter(arrayAdapter);
        //the table will be empty in the begining
        //update it down below when we get all the data

        //TASK 4
        //now on click load the page
        titleslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               //now we know it works
                //change this log code
                // Log.i("Article URL", urls_list.get(position));
                //to now load the 2nd activity
                //ALSO PASS THE CONTENT

                Intent i = new Intent(getApplicationContext(), ArticleActivity.class);
                i.putExtra("articleURL", urls_list.get(position));
               // i.putExtra("content", content.get(position));
                startActivity(i);
            }
        });



        articlesDB = this.openOrCreateDatabase("Articles", MODE_PRIVATE, null);

        articlesDB.execSQL("CREATE TABLE IF NOT EXISTS articles (id INTEGER PRIMARY KEY, articleID INTEGER, url VARCHAR, title VARCHAR, content VARCHAR)");


        //update the list view
        updatedListView();

        DownloadTask task = new DownloadTask();
        //moved to the main thread


        try {
          //  change this line
          // String result = task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty").get();
          //to this
            //we are executing it but not getting the result
            //we just want to update now
            //aka no UI thread lock
            task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");



            //Log.i("The result", result);
            //Log the results to see if our
            //download task is working

            //IT WORKED
            //now we get the content
            //of some of those numbers

            /*
            Log.i("ArticleID", articleIDs.toString());
            Log.i("ArticleTitles", articleTitles.toString());
            Log.i("ArticleURLs", articleURLs.toString());
            */
            //see if it saved to the databse
            // by making a quick query
            //ordering the biggest articlesID first
            //using updatelistview method
            //BUT THE CHANGE IS IT WILL BE DONE IN THE ASYN THREAD

        }catch (Exception e){
            e.printStackTrace();
        }

        //this url lists all the latest posts from
        //hacker news
    }

    private void updatedListView() {
        Log.i("Updating list", "Done update");
        try {
            //see if it saved to the databse
            // by making a quick query
            //ordering the biggest articlesID first
            Cursor c = articlesDB.rawQuery("SELECT * FROM articles ORDER BY articleID DESC ", null);
            int articleID_index = c.getColumnIndex("articleID");
            int url_index = c.getColumnIndex("url");
            int title_index = c.getColumnIndex("title");
            //add the context index here
            // to share it with the next activity
            int content_index = c.getColumnIndex("content");



            c.moveToFirst();
            //clear the list when the app starts
            titles_list.clear();
            urls_list.clear();
            //call content array list
            //content.add(c.getString(content_index));

            while (c != null) {
                //contiuned...
                //adding the titels to the list view
                titles_list.add(c.getString(title_index));
                urls_list.add(c.getString(url_index));

                /* it worked!!
                Log.i("articleresults ID", Integer.toString(c.getInt(articleID_index)));
                Log.i("articleresults URL", c.getString(url_index));
                Log.i("articleresults TITLE", c.getString(title_index));

                */
                c.moveToNext();

            }

            //update the array adapter
            arrayAdapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Articles Update finished", Toast.LENGTH_SHORT).show();
    }

    /*
    public void reader_content(int articleID) throws MalformedURLException {


        String url;
        URLConnection urlConnection;
        InputStreamReader in;

        try {
            url = new URL("https://hacker-news.firebaseio.com/v0/item/" + articleID + ".json?print=pretty");
            //open connection
            urlConnection = (HttpsURLConnection) url.openConnection();
            //using the same input stream
            //"in"
            in = urlConnection.getInputStream();
            reader = new InputStreamReader(in);
            data = reader.read();
            String article_info = "";

            while (data != -1) {
                char current = (char) data;
                article_info += current;
                data = reader.read();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    */
}
