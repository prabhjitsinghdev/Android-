package com.pj92singh.hackernews;
/* pj92singh
* Prabhjit Singh
* 
* Hacker News App Activity 2 
* this activity loads the articles
* using the Hackernew api to obtain JSON data, 
* extract the top articles, info/URL
* allow the user to read the articles in the app
* using WebView.
*
* requires internet access

*/

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        WebView myWebView = (WebView)findViewById(R.id.webView);

        //set javascript enabled
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());


        //load the url
        Intent i = getIntent();
        String url =  i.getStringExtra("articleURL");
       // String content = i.getStringExtra("content");

       myWebView.loadUrl(url);
       // myWebView.loadData(content, "text/html", "UTF-8");

    }
}
