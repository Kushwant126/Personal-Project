package com.example.personalproject.mainPackage.gpsutills;

//import static com.winit.alseer.salesman.utilities.StringUtils.getString;

import static com.example.personalproject.mainPackage.utilities.StringUtils.getString;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.example.personalproject.R;
import com.example.personalproject.mainPackage.gpsutills.common.GPSErrorCode;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;/*
import com.google.android.gms.maps.model.LatLng;
import com.winit.alseer.salesman.dataobject.CustomerDao;
import com.winit.alseer.salesman.utilities.StringUtils;
import com.winit.arlafoodpresales.R;
import com.winit.gpsutills.lib.common.GPSConstants;
import com.winit.gpsutills.lib.common.GPSErrorCode;
import com.winit.gpsutills.lib.common.GPSLogutils;
import com.winit.gpsutills.lib.common.GPSPreference;*/
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GPSUtills {
    private String TAG = "GPSTrack";
    private Context context;

//    private GPSTrackerService gpsTrackerService;
    private GPSCallback gpsCallback;
    private static GPSUtills gpsUtills;

    private List<Address> address;
    private LatLng currentLatLng;
    private GPSTrackerService gpsTrackerService;

    public synchronized static GPSUtills getInstance(Context context) {
        if (gpsUtills == null) {
            gpsUtills = new GPSUtills(context);
        }
        return gpsUtills;
    }

    private GPSUtills(Context context) {
        this.context = context;
        gpsTrackerService = new GPSTrackerService(context);
        this.context.startService(new Intent(this.context, GPSTrackerService.class));//sri
    }

    public void setListner(GPSCallback gpsCallback) {
        this.gpsCallback = gpsCallback;
    }
    //method to set enable/disble logs
    public void setLogEnable(boolean isLogEnable) {
        GPSLogutils.setLogEnable(isLogEnable);
    }

    //method to set packagename which will useful to create logs in application dir.
    public void setPackegeName(String packageName) {
        GPSLogutils.setPackgeName(packageName);
    }

    /**
     * method     :isDeviceConfiguredProperly()
     * parameters :null
     */
    public void isDeviceConfiguredProperly() {
        boolean isGpsFeatureAvailbleOnDevice = isGpsFeatureAvailableOnDevice();
        if (isGpsFeatureAvailbleOnDevice) {
            GPSLogutils.createLogDataForLib("isDeviceConfiguredProperly", "Gps Feature Available On Device", "EC_GPS_HARDWARE_SETUP_AVAILABLE_ONDEVICE");
            boolean checkGooglePlayServices = checkGooglePlayServices();
            if (checkGooglePlayServices) {
                gpsCallback.gotGpsValidationResponse(checkGooglePlayServices, GPSErrorCode.EC_DEVICE_CONFIGURED_PROPERLY);
                GPSLogutils.createLogDataForLib("isDeviceConfiguredProperly", "Updated GooglePlay Services Available", "EC_DEVICE_CONFIGURED_PROPERLY");
            } else {
                gpsCallback.gotGpsValidationResponse(checkGooglePlayServices, GPSErrorCode.EC_GOOGLEPLAY_SERVICES_UPDATE_REQUIRED);
                GPSLogutils.createLogDataForLib("isDeviceConfiguredProperly", "Updated GooglePlay Services Not Available", "EC_GOOGLEPLAY_SERVICES_UPDATE_REQUIRED");
            }
        } else {
            gpsCallback.gotGpsValidationResponse(isGpsFeatureAvailbleOnDevice, GPSErrorCode.EC_GPS_HARDWARE_SETUP_NOTAVAILABLE_ONDEVICE);
            GPSLogutils.createLogDataForLib("isDeviceConfiguredProperly", "Gps Feature Not Available On Device", "EC_GPS_HARDWARE_SETUP_NOTAVAILABLE_ONDEVICE");
        }
    }


    /**
     * method     :isGpsProviderEnabled()
     * parameters :null
     */
    public void isGpsProviderEnabled() {
        boolean isGpsProviderEnabled, isNetworkProviderEnabled;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        isGpsProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkProviderEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (isGpsProviderEnabled) {
            gpsCallback.gotGpsValidationResponse(isGpsProviderEnabled, GPSErrorCode.EC_GPS_PROVIDER_ENABLED);
            GPSLogutils.createLogDataForLib("isGpsProviderEnabled", "Gps Provider Enabled", "EC_GPS_PROVIDER_ENABLED");
        } else if (isNetworkProviderEnabled) {
            gpsCallback.gotGpsValidationResponse(isNetworkProviderEnabled, GPSErrorCode.EC_GPS_PROVIDER_ENABLED);
            GPSLogutils.createLogDataForLib("isNetworkProviderEnabled", "Network Provider Enabled", "EC_NETWORK_PROVIDER_ENABLED");
        } else {
            gpsCallback.gotGpsValidationResponse(isGpsProviderEnabled, GPSErrorCode.EC_GPS_PROVIDER_NOT_ENABLED);
            GPSLogutils.createLogDataForLib("isGpsProviderEnabled", "Gps Provider Not Enabled", "EC_GPS_PROVIDER_NOT_ENABLED");
        }
    }
    public void getCurrentLatLng() {
        currentLatLng = gpsTrackerService.getLatLng();
        if (currentLatLng.latitude == 0.0 && currentLatLng.longitude == 0.0) {
            gpsCallback.gotGpsValidationResponse(currentLatLng, GPSErrorCode.EC_UNABLE_TO_FIND_LOCATION);
            GPSLogutils.createLogDataForLib("getCurrentLatLng", getString(R.string.lattitude_col) + currentLatLng.latitude + ", " + currentLatLng.longitude, "EC_UNABLE_TO_FIND_LOCATION");
        } else {
            gpsCallback.gotGpsValidationResponse(currentLatLng, GPSErrorCode.EC_LOCATION_FOUND);
            GPSLogutils.createLogDataForLib("getCurrentLatLng", getString(R.string.lattitude_col)  + currentLatLng.latitude + ", " + currentLatLng.longitude, "EC_LOCATION_FOUND");
        }
    }

    public LatLng getLatLngOnly() {
        if (gpsTrackerService != null) currentLatLng = gpsTrackerService.getLatLng();
        return currentLatLng;
    }





    //method to check Gps Hardware setUp availble or not in Device.
    private boolean isGpsFeatureAvailableOnDevice() {
        PackageManager packageManager = context.getPackageManager();
        boolean hasGps = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
        return hasGps;
    }

    //method to check Supported GooglePlayServices avail in Device or not.
    private boolean checkGooglePlayServices() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (status != ConnectionResult.SUCCESS) {
            GPSLogutils.debug(TAG, GooglePlayServicesUtil.getErrorString(status));
            return false;
        } else {
            // google play services is updated.
            GPSLogutils.debug(TAG, GooglePlayServicesUtil.getErrorString(status));
            return true;
        }
    }

    //method to create Logs into app dir.
    public void createdLogDataForApp(String action, String userId, String siteId, String response) {
        GPSLogutils.createLogDataForApp(action, userId, siteId, response);
    }

    public void connectGoogleApiClient() {
        gpsTrackerService.connectGoogleApiClient();
    }
    public void disConnectGoogleApiClient() {
        gpsTrackerService.disConnectGoogleApiClient();
    }
    public void stopLocationUpdates() {
        gpsTrackerService.stopLocationUpdates();
    }
    public GoogleApiClient getGoogleApiClient() {
        return gpsTrackerService.getGoogleApiClient();
    }

    public void startLocationUpdates() {
        if (getGoogleApiClient().isConnected()) {
            gpsTrackerService.startLocationUpdates();
        }
    }
}
