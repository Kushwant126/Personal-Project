package com.example.personalproject.mainPackage.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Description Class : Checking Network Connections
 *
 * @author Neeraj
 */
public class NetworkUtility {
    /**
     * Method to check Network Connections
     *
     * @param context
     * @return boolean value
     */
    public static boolean isNetworkConnectionAvailable(Context context) {
        boolean isNetworkConnectionAvailable = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null)
            isNetworkConnectionAvailable = activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
		
		/*if(isNetworkConnectionAvailable)
		{
			if(!AppConstants.IS_KR_NETWORK_REACHABLE)
			{
				isNetworkConnectionAvailable = false;
				ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);    //Not Required for FarmFresh
				for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) 
				{
					if(service.service.getClassName().equalsIgnoreCase("com.aruba.via.vpn.ViaVpnService"))
					{
						isNetworkConnectionAvailable = true;
						break;
					}
				}
			}
		}*/

        return isNetworkConnectionAvailable;
    }

    public static boolean isWifiConnected(Context context) {
        boolean isNetworkConnectionAvailable = false;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {
            isNetworkConnectionAvailable = true;
        }
        return isNetworkConnectionAvailable;
    }


}
