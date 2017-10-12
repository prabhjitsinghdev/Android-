package com.pj92singh.together;
/* 
pj92singh
Prabhjit Singh
Together app 

This app combines aspects of most previous apps
-asks user for permisson to obtain location access
-using Android 6.0 & higher pop-up premisson code
-sound for settings 
-allowing user to re-assign access to location 
services if they deny it in the first place

 */
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final int MY_FINELOC = 101;
    private static final int MY_COARSELOC = 102;
    private boolean per_Granted = false;

    LocationManager locationManager;
    String provider;

    Button settings_b;

    //MEDIA
    MediaPlayer token_pop;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_FINELOC:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    per_Granted = true;
                }else {
                    per_Granted = false;
                    Toast.makeText(getApplicationContext(), "App needs location premission!!", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //media
        token_pop = MediaPlayer.create(this, R.raw.tokenbeep);

        //getbutton
        settings_b = (Button) findViewById(R.id.buttonS);
        //get premission
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_FINELOC);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);


        //get location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(provider, 400, 1, this);



    }
    public void ChangeSettings(View view){
        //if denied settings
        //or settings changed
        //ask premission again
        token_pop.start();
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_FINELOC);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {


    }

    @Override
    public void onProviderDisabled(String provider) {
        locationManager.removeUpdates(this);

    }
}
