package com.pj92singh.notficationtest;
/*
* pj92singh
* Prabhjit Singh
* This app is test of Notification Manager
* and Android 8.0 standards of NOTFICATION CHANNELS
* via a notifcation popup at press
* of a button with certain ID 
*
* Once notfication is clicked, then it will
* direct user to Projects website
* */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    //trying Notfications
    public void SendNotificaiton(View view){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "001");
        /*
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("Test notification")
                        .setContentText("Hello from the app!"); */
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://singh13x.myweb.cs.uwindsor.ca/tabbedprojects.html"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle("Notification app");
        mBuilder.setContentText("Visit my project page!");

        NotificationManager mNotfi_Manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotfi_Manager.notify(001, mBuilder.build());
    }
}
