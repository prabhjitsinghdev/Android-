package com.pj92singh.hikerscompanion;
/* pj92singh
*  Prabhjit Singh
*  Hiker's Companion app
*  -shows the use of location manger
*  -and provids the user with all the
*   related data for hiking 
*   i.e. Location/Longitutde/Latitidue 
*   Altitude/Address etc. 
*   */
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    String provider;
    TextView Longitude;
    TextView latitude;
    TextView Bearing;
    TextView Accuracy;
    TextView Speed;
    TextView Address;
    TextView Altitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find all the vars
        Longitude = (TextView) findViewById(R.id.textViewLng);
        latitude = (TextView) findViewById(R.id.textViewLat);
        Bearing = (TextView) findViewById(R.id.textViewBearing);
        Accuracy = (TextView) findViewById(R.id.textViewAccuracy);
        Speed = (TextView) findViewById(R.id.textViewSpeed);
        Address = (TextView) findViewById(R.id.textViewAddress);
        Altitude = (TextView) findViewById(R.id.textViewAltitude);


        //set up the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        provider = locationManager.getBestProvider(new Criteria(), false);


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
        //this will update the location
        //at the beginning of the app launch
        onLocationChanged(location);
    }

    @Override
    protected void onPause() {
        super.onPause();

        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

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
        //update every 400milliseconds, 1 meter and in this
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            Double alt = location.getAltitude();
            Float bearing_m = location.getBearing();
            Float speed_m = location.getSpeed();
            Float acc = location.getAccuracy();

        //to find the nearest address
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> listAddressesss = geocoder.getFromLocation(lat, lng, 1);

            if (listAddressesss != null && listAddressesss.size() > 0){
                Log.i("PlaceInfo", listAddressesss.get(0).toString());

                //now get the address lines into the TextView
                //its bascially an array


                //using a for loop go through it
                //save it to a holder

                //holder
                String address_holder ="";
                //this will be apended each time
                //therefore get the full address

                //<= bc we want the country as well
                for(int j = 0; j <= listAddressesss.get(0).getMaxAddressLineIndex(); ++j){
                       address_holder +=  listAddressesss.get(0).getAddressLine(j) +"\n";
                }

                Address.setText("Address:\n" + address_holder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Longitude.setText("Longitude " + lng);
        latitude.setText("Latitude " + lat);
        Altitude.setText("Altitude " + alt +"m");
        Bearing.setText("Bearing " + bearing_m);
        Speed.setText("Speed " + speed_m +"m/s");
        Accuracy.setText("Accuracy "+acc +"m");





        Log.i("Latitutde", String.valueOf(lat));
        Log.i("longatude", String.valueOf(lng));
        Log.i("Altitud", String.valueOf(alt));
        Log.i("bearing", String.valueOf(bearing_m));
        Log.i("speed", String.valueOf(speed_m));
        Log.i("acc", String.valueOf(acc));





    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
