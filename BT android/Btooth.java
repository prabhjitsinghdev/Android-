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

public class Btooth(){

    public setupBtooh(){
        //if the adapter is null then not supported?
        //
        var bluetoothHeadset: BluetoothHeadset? = null

        // Get the default adapter
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

        private val profileListener = object : BluetoothProfile.ServiceListener {

            override fun onServiceConnected(profile: Int, proxy: BluetoothProfile) {
                if (profile == BluetoothProfile.HEADSET) {
                    bluetoothHeadset = proxy as BluetoothHeadset
                }
            }

            override fun onServiceDisconnected(profile: Int) {
                if (profile == BluetoothProfile.HEADSET) {
                    bluetoothHeadset = null
                }
            }
        }

        // Establish connection to the proxy.
        bluetoothAdapter?.getProfileProxy(context, profileListener, BluetoothProfile.HEADSET)

        // ... call functions on bluetoothHeadset

        // Close proxy connection after use.
        bluetoothAdapter?.closeProfileProxy(BluetoothProfile.HEADSET, bluetoothHeadset)

      }

}
