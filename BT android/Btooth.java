/**
*pj92insgh
*Prabhjit Singh
*
* testing android bluetooth class

*android manifest will use the following permissions
<manifest ... >
  <uses-permission android:name="android.permission.BLUETOOTH" />
  <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
  ...
</manifest>

**/


/*
NOTES
-first use the adapter to get it
BluetoothAdapter.getDefaultAdapter()
val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

-next using that we will use the listner/profile
-using the BluetoothProfile.ServiceListener and BlueetoothProfile

*/

package com.example.bttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Set;

/*pj92singh
* Prabhjit Singh
* Blueetooth test */
public class MainActivity extends AppCompatActivity {
    private static int GET_BLUETOOTH_ON = 100;
    private boolean per_Granted = false;
    public  static int REQUEST_ENABLE_BT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        * first get the permission for bluetooth
        * */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case GET_BLUETOOTH_ON;
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    per_Granted = true;
                }else {
                    per_Granted = false;
                    Toast.makeText(getApplicationContext(), "App needs bluetooth premission!!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void getBt(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Toast.makeText(getApplicationContext(), "permission denied", Toast.LENGTH_LONG);

        }
    }
    /*
    * after Bluetooth permission is granted we can connect the 2 devices
    * then proceed to stream data/media
    * */
    public void PairDevices(){
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!bluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

                if (bluetoothAdapter == null)
                {
                    // Device doesn't support Bluetooth
                    Log.i("DEBUG", "bluetooth not connected/device not supported");
                    Toast.makeText(getApplicationContext(), "device not supported", Toast.LENGTH_LONG);

                } else{
                    //bluetooth is enabled on device
                    //find paried devices
                    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

                    if (pairedDevices.size() > 0)
                    {
                        // There are paired devices. Get the name and address of each paired device.
                        for (BluetoothDevice device : pairedDevices)
                        {
                            String deviceName = device.getName();
                            String deviceHardwareAddress = device.getAddress(); // MAC address
                            Log.i("DEBUG BT", "BT list: " + deviceName + " hardwareAddress: " + deviceHardwareAddress);
                        }
                    }else if (pairedDevices.size() < 0){
                        //no devices, need to discover
                        //scan for devices
                    }
                }
        }

    }
}
