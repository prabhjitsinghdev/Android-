package com.pj92singh.webviewdemo;
/*
* pj92singh
* Prabhjit Singh
*
* WebView app
* Loads up my projects page in mobile mode
* * Works in portrait only
* Requires internet
* */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView myWebView = (WebView)findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("http://singh13x.myweb.cs.uwindsor.ca/tabbedprojects.html");

    }
}
