package com.pj92singh.batteryinfo;
/*
* pj29singh
* Prabhjit Singh
*
* BatteryTest app
* -made to test battery information
* and possibly run to keep the battery temp at optimal levels
* -it will consume battery but to save the phone
* */
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    int temp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    try {
        BatteryManager batteryManager = new BatteryManager(BATTERY_SERVICE);
        int health = BatteryManager.BATTERY_HEALTH_UNKNOWN;
        Log.i("DEBUG", "battery health: " + health);
        int health2 = batteryManager.getIntProperty(BatteryManager.BATTERY_HEALTH_OVERHEAT);
        Log.i("DEBUG", "battery health2: " + health2);
        int currentTemp = BatteryManager.EXTRA_TEMPERATURE);

        String message = "Current temp: " +currentTemp + Character.toString(((char) 176))+ "C";
        Log.i("DEBUG", "current temp: " +message);

    }catch (Exception e){
        Log.i("ERROR", "excepetion error: " +e);
    }


    }
}
