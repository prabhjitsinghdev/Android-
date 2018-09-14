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
            //extra temp is string so convert it
            int currentTemp = Integer.parseInt(BatteryManager.EXTRA_TEMPERATURE);

            String message = "Current temp: " +currentTemp + Character.toString(((char) 176))+ "C";
            Log.i("DEBUG", "current temp: " +message);

            //check if its charging as well
            boolean charging10 = BatteryManager.isCharging();
            Log.i("DEBUG", "Charging value: " +charging10);
            //check if plugged to AC
            int batteryAC = BatteryManager.BATTERY_PLUGGED_AC;
            Log.i("DEBUG", "Battery AC: " +batteryAC);
            //check if USB
            int batteryUSB = BatteryManager.BATTERY_PLUGGED_USB;
            Log.i("DEBUG", "Battery USB: " +batteryUSB);
            //the battery percentage
            String batteryLevel = BatteryManager.EXTRA_LEVEL;
            Log.i("DEBUG", "batteryLevel: " +batteryLevel);

        }catch (Exception e){
            Log.i("ERROR", "excepetion error: " +e);
        }


        //if the temp drops
        if(currentTemp <= 18) && (charging10 == true || charging10 == false){
            //run the processor
            //check also for  30 < battery < 100
            if((batteryLevel > 30) || (batteryLevel < 100){
                // Toast.makeText(getApplicationContext)

            }
        }


    }
}
