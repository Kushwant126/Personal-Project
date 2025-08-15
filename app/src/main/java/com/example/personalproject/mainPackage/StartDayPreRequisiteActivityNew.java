package com.example.personalproject.mainPackage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.multidex.BuildConfig;

import com.example.personalproject.R;
import com.example.personalproject.mainPackage.common.AppConstants;
import com.example.personalproject.mainPackage.common.Preference;
import com.example.personalproject.mainPackage.utilities.BitmapUtilsLatLang;
import com.example.personalproject.mainPackage.utilities.FileUtils;
import com.example.personalproject.mainPackage.utilities.LocationUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class StartDayPreRequisiteActivityNew extends BaseActivity {
    String camera_imagepath = "";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    Bitmap bitmapProcessed;
    ArrayList<String> vecDamageImageDOS = new ArrayList<>();
    Handler mHandler = new Handler();
    long mStartRX = 0, mStartTX = 0;
    private int batteryLevel = 0;
    LinearLayout llStartDay, llattendance_DropDown;
    TextView down_load, up_load, battery_percentage, tvattendance_type, tv_timeDetails, tv_locationDetails, startday_proceed;
    ImageView internetspeed_tick, internetspeed_cross, syn_tick, syn_cross, datacheck_tick, datacheck_cross, battery_tick, battery_cross, selfie_img, ivRetake_selfie;
    RelativeLayout startday_PreCheck, startday_TakeSelfie, selfie_taken;
    CardView startday_PreCheck_attendance, startday_capture_selfie;
    ArrayList<String> attendance_types = new ArrayList<>();
    boolean isdatacheckWorking = false, isSyncFailed = false, isChecked = true, isCheckedAbsent = false;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri photoURI;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    @Override
    public void initialize() {
        llStartDay = (LinearLayout) inflater.inflate(R.layout.new_startday_prerequisite_layout, llBody);
        setTypeFaceRobotoOriginalSemibold(llStartDay);

        initilizeControl();
        startday_PreCheck.setVisibility(View.VISIBLE);
        startday_TakeSelfie.setVisibility(View.GONE);

//        gpsUtills.setListner(this);
//        gpsUtills.isGpsProviderEnabled();
//        gpsUtills.getCurrentLatLng();

        getBattery_percentage();
        checkSignals();
        if (isNetworkConnectionAvailable(StartDayPreRequisiteActivityNew.this)) {
            internetSpeedCheck();
            internetspeed_tick.setVisibility(View.VISIBLE);
            internetspeed_cross.setVisibility(View.GONE);
//            if (!checkActivityDoneForTheDay(AppConstants.Status_INTERNET_SPEED)) {
//                prepareStatusDo(AppConstants.Status_INTERNET_SPEED);
//                Log.d("StartDay", "Status_INTERNET_SPEED: " + checkActivityDoneForTheDay(AppConstants.Status_INTERNET_SPEED));
//            }
        } else {
            internetspeed_tick.setVisibility(View.GONE);
            internetspeed_cross.setVisibility(View.VISIBLE);
        }

        if (isSyncFailed) {
            syn_tick.setVisibility(View.GONE);
            syn_cross.setVisibility(View.VISIBLE);
        } else {
            syn_tick.setVisibility(View.VISIBLE);
            syn_cross.setVisibility(View.GONE);
//            if (!checkActivityDoneForTheDay(AppConstants.Status_SYNC)) {
//                prepareStatusDo(AppConstants.Status_SYNC);
//                Log.d("StartDay", "Status_SYNC: " + checkActivityDoneForTheDay(AppConstants.Status_SYNC));
//            }
        }
        if (isdatacheckWorking) {
            datacheck_tick.setVisibility(View.VISIBLE);
            datacheck_cross.setVisibility(View.GONE);
//            if (!checkActivityDoneForTheDay(AppConstants.Status_DATA_CHECK)) {
//                prepareStatusDo(AppConstants.Status_DATA_CHECK);
//                Log.d("StartDay", "Status_DATA_CHECK: " + checkActivityDoneForTheDay(AppConstants.Status_DATA_CHECK));
//            }
        } else {
            datacheck_tick.setVisibility(View.GONE);
            datacheck_cross.setVisibility(View.VISIBLE);
        }
        if (batteryLevel >= 40) {
            battery_tick.setVisibility(View.VISIBLE);
            battery_cross.setVisibility(View.GONE);
//            if (!checkActivityDoneForTheDay(AppConstants.Status_BATTERY_CHECK)) {
//                prepareStatusDo(AppConstants.Status_BATTERY_CHECK);
//                Log.d("StartDay", "Status_BATTERY_CHECK: " + checkActivityDoneForTheDay(AppConstants.Status_BATTERY_CHECK));
//            }
        } else {
            battery_tick.setVisibility(View.GONE);
            battery_cross.setVisibility(View.VISIBLE);
        }
        getLocationNew();

    }

    private void initilizeControl() {
        tv_timeDetails = llStartDay.findViewById(R.id.tv_timeDetails);
        tv_locationDetails = llStartDay.findViewById(R.id.tv_locationDetails);
        ivRetake_selfie = llStartDay.findViewById(R.id.ivRetake_selfie);
        tvattendance_type = llStartDay.findViewById(R.id.tvattendance_type);
        llattendance_DropDown = llStartDay.findViewById(R.id.llattendance_DropDown);
        startday_proceed = llStartDay.findViewById(R.id.startday_proceed);
        startday_proceed.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
        selfie_img = llStartDay.findViewById(R.id.selfie_img);
        startday_capture_selfie = llStartDay.findViewById(R.id.startday_capture_selfie);
        selfie_taken = llStartDay.findViewById(R.id.selfie_taken);
        startday_PreCheck_attendance = llStartDay.findViewById(R.id.startday_PreCheck_attendance);
        startday_PreCheck = llStartDay.findViewById(R.id.startday_PreCheck);
        startday_TakeSelfie = llStartDay.findViewById(R.id.startday_TakeSelfie);

        battery_tick = llStartDay.findViewById(R.id.battery_tick);
        battery_cross = llStartDay.findViewById(R.id.battery_cross);
        battery_percentage = llStartDay.findViewById(R.id.battery_percentage);
        internetspeed_tick = llStartDay.findViewById(R.id.internetspeed_tick);
        internetspeed_cross = llStartDay.findViewById(R.id.internetspeed_cross);
        datacheck_tick = llStartDay.findViewById(R.id.datacheck_tick);
        datacheck_cross = llStartDay.findViewById(R.id.datacheck_cross);
        down_load = llStartDay.findViewById(R.id.down_load);
        up_load = llStartDay.findViewById(R.id.up_load);
        syn_tick = llStartDay.findViewById(R.id.syn_tick);
        syn_cross = llStartDay.findViewById(R.id.syn_cross);

        TextView sync1 = llStartDay.findViewById(R.id.sync1);
        sync1.setTypeface(AppConstants.SanFranciscoDisplay_Regular);
        TextView data1 = llStartDay.findViewById(R.id.data1);
        data1.setTypeface(AppConstants.SanFranciscoDisplay_Regular);
        TextView netspeed1 = llStartDay.findViewById(R.id.netspeed1);
        netspeed1.setTypeface(AppConstants.SanFranciscoDisplay_Regular);
        TextView battery1 = llStartDay.findViewById(R.id.battery1);
        battery1.setTypeface(AppConstants.SanFranciscoDisplay_Regular);
        TextView mark_attendance = llStartDay.findViewById(R.id.mark_attendance);
        mark_attendance.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
        TextView start_day_pr = llStartDay.findViewById(R.id.start_day_pr);
        start_day_pr.setTypeface(AppConstants.SanFranciscoDisplay_Bold);

        TextView battery_percentage = llStartDay.findViewById(R.id.battery_percentage);
        battery_percentage.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
        TextView down_load = llStartDay.findViewById(R.id.down_load);
        down_load.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
        TextView up_load = llStartDay.findViewById(R.id.up_load);
        up_load.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
        TextView netspeed2 = llStartDay.findViewById(R.id.netspeed2);
        netspeed2.setTypeface(AppConstants.SanFranciscoDisplay_Bold);

//        TextView sync = llStartDay.findViewById(R.id.sync);sync.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        TextView data = llStartDay.findViewById(R.id.data);data.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        TextView netspeed = llStartDay.findViewById(R.id.netspeed);netspeed.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        TextView battery = llStartDay.findViewById(R.id.battery);battery.setTypeface(AppConstants.SanFranciscoDisplay_Bold);

        startday_PreCheck_attendance.setOnClickListener(view -> {
            startday_PreCheck.setVisibility(View.GONE);
            startday_TakeSelfie.setVisibility(View.VISIBLE);

            selfie_taken.setVisibility(View.GONE);
            startday_TakeSelfie.setVisibility(View.VISIBLE);
            startday_capture_selfie.setVisibility(View.VISIBLE);
        });

        startday_capture_selfie.setOnClickListener(v -> {
            if (vecDamageImageDOS != null)
                new Handler().postDelayed(() -> {
                }, 2000);

            if (isChecked) {
//                captureSelfiImage();
//                checkPermissionsAndOpenCamera(selfie_img);
                openCamera();
            } else {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.please_mark_tick), Toast.LENGTH_SHORT).show();
            }
        });

        startday_proceed.setOnClickListener(v -> {
            if (isChecked) {
                if (bitmapProcessed != null) {
//                    isStartDayDone = true;
                    preference.saveBooleanInPreference(Preference.START_DAY, true);
                    preference.commitPreference();
                    finish();
                } else
                    showCustomDialog(StartDayPreRequisiteActivityNew.this, getResources().getString(R.string.alert), getResources().getString(R.string.please_capture_the_selfie), getResources().getString(R.string.OK), null, "");
            } else
                showCustomDialog(StartDayPreRequisiteActivityNew.this, getResources().getString(R.string.alert), getResources().getString(R.string.please_mark_attendance_yes_no), getResources().getString(R.string.OK), null, "");
        });


        attendance_types.add("Present");
        attendance_types.add("Absent");
//        attendance_types.add(getString(R.string.present));
//        attendance_types.add(getString(R.string.absent));

        final String[] options = attendance_types.toArray(new String[0]);
        String attendance = tvattendance_type.getText().toString();
        if (attendance == null || attendance.isEmpty()) {
            tvattendance_type.setText(options[0]);
        }

        llattendance_DropDown.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(StartDayPreRequisiteActivityNew.this.getApplicationContext(), view);
            for (int i = 0; i < options.length; i++) {
                if (!options[i].equals(tvattendance_type.getText().toString()))
                    popupMenu.getMenu().add(options[i]);
            }
            popupMenu.setOnMenuItemClickListener(item -> {
                String selectedOption = item.getTitle().toString();
                tvattendance_type.setText(selectedOption);
                loadAttendanceType(selectedOption);
                return true;
            });
            popupMenu.show();
        });
        ivRetake_selfie.setOnClickListener(view -> startday_capture_selfie.performClick());
    }


    public void checkSignals() {
        boolean isCellularSignalAvailable = isCellularSignalAvailableCheck();
        boolean isWifiSignalAvailable = isWifiSignalAvailableCheck();

        isdatacheckWorking = isCellularSignalAvailable || isWifiSignalAvailable;
    }

    public boolean isCellularSignalAvailableCheck() {
        int pid = android.os.Process.myPid(); // Get the process ID
        int uid = android.os.Process.myUid(); // Get the user ID
        if (checkPermission(Manifest.permission.READ_PHONE_STATE, pid, uid) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, handle accordingly
            return false; // Or throw an exception, show a message, etc.
        }

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            return telephonyManager.getNetworkType() != TelephonyManager.NETWORK_TYPE_UNKNOWN;
        }

        return false; // TelephonyManager is null, cannot determine network type
    }

    public boolean isWifiSignalAvailableCheck() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            return wifiNetworkInfo != null && wifiNetworkInfo.isConnected();
        }
        return false;
    }

    void getBattery_percentage() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float) scale;
        float p = batteryPct * 100;

        battery_percentage.setText(Math.round(p) + "%");
        batteryLevel = Math.round(p);
    }

    public void internetSpeedCheck() {
        mStartRX = TrafficStats.getTotalRxBytes();
        mStartTX = TrafficStats.getTotalTxBytes();
        if (mStartRX == TrafficStats.UNSUPPORTED || mStartTX == TrafficStats.UNSUPPORTED) {
            showCustomDialog(StartDayPreRequisiteActivityNew.this, getResources().getString(R.string.alert), getResources().getString(R.string.your_device_does_not_support_traffice), getResources().getString(R.string.OK), "", "");
        } else {
            mHandler.postDelayed(mRunnable, 1000);
        }
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            long Rx = TrafficStats.getTotalRxBytes();
            long Tx = TrafficStats.getTotalTxBytes();
            long rxBytes = Rx - mStartRX;
            down_load.setText(rxBytes + "Bps");
//            down_load.setText(formatSpeed(rxBytes));

            mStartRX = Rx;
            long txBytes = Tx - mStartTX;
            up_load.setText(txBytes + "Bps");
//            up_load.setText(formatSpeed(txBytes));

            mStartTX = Tx;
            mHandler.postDelayed(mRunnable, 1000);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getLocation();
                getLocationNew();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void loadAttendanceType(String selectedOption) {
        switch (selectedOption) {
            case "Present":
                isChecked = true;
                isCheckedAbsent = false;
                startday_capture_selfie.setVisibility(View.VISIBLE);
                selfie_taken.setVisibility(View.GONE);
                if (selfie_img.getDrawable() != null) selfie_img.setImageBitmap(null);

                /*if (selfie_img.getDrawable() != null){
                    startday_capture_selfie.setVisibility(View.GONE);
                    selfie_taken.setVisibility(View.VISIBLE);
                }else{
                    startday_capture_selfie.setVisibility(View.VISIBLE);
                    selfie_taken.setVisibility(View.GONE);
                }*/
                break;
            case "Absent":
                isChecked = false;
                isCheckedAbsent = true;
                camera_imagepath = "";
                startday_capture_selfie.setVisibility(View.GONE);
                selfie_taken.setVisibility(View.GONE);
                if (selfie_img.getDrawable() != null) selfie_img.setImageBitmap(null);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (startday_TakeSelfie.getVisibility() == View.VISIBLE) {
            startday_PreCheck.setVisibility(View.VISIBLE);
            startday_TakeSelfie.setVisibility(View.GONE);
            if (selfie_img.getDrawable() != null) selfie_img.setImageBitmap(null);
        } else {
            selectedBar = 1;
            super.onBackPressed();
//            finish();
        }
    }


    /// /////////////////////////////////////////////////////////////////////////////////
     /*Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    //                photoFile = createImageFile();
                    photoFile = createImageFileNew();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //            photoFile = getOutputMediaFile(); //for saving try this.. kush
                camera_imagepath = photoFile.getAbsolutePath();

                if (photoFile != null) {
                    photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }*/
    @SuppressLint("MissingPermission")
    private void getLocationNew() {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Request location permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return;
        }
        if (selectedLanguage.equalsIgnoreCase(AppConstants.langEnglish)) {
            dateFormat = new SimpleDateFormat("h:mm a, dd-MMM-yyyy", Locale.US);
        } else {
            dateFormat = new SimpleDateFormat("h:mm a, dd-MMM-yyyy", Locale.getDefault());
        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(this, task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                Location lastKnownLocation = task.getResult();
                double latitude = lastKnownLocation.getLatitude();
                double longitude = lastKnownLocation.getLongitude();
                AppConstants.currentLat = latitude + "";
                AppConstants.currentLng = longitude + "";

                // Now you have the latitude and longitude, you can use them to get the address
                String address = LocationUtils.getAddress(this, latitude, longitude);
                // Do something with the address
                tv_locationDetails.setText(address);
            } else {
                Log.d("getLocation", "No address found");
                tv_locationDetails.setText("No address found");
            }
        });
    }

    private void openCameraWorking() {
        if (isDeviceSupportCamera()) {
            File file = FileUtils.getOutputImageFile("Kush PP");
            if (file != null) {
                camera_imagepath = file.getAbsolutePath();
                Uri fileUri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    fileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, file);
                } else {
                    fileUri = Uri.fromFile(file);
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                intent.putExtra("fileName", file.getName());
                intent.putExtra("filePath", file.getAbsolutePath());
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        } else {
            Toast.makeText(this, getString(R.string.sorry_Device_not_supported_to_camera), Toast.LENGTH_SHORT).show();
        }
    }

    private void openCamera() {
        if (!isDeviceSupportCamera()) {
            Toast.makeText(this, R.string.sorry_Device_not_supported_to_camera, Toast.LENGTH_SHORT).show();
            return;
        }

        File imageFile = FileUtils.getOutputImageFile("Kush PP");
        if (imageFile == null) return;

        camera_imagepath = imageFile.getAbsolutePath();

        Uri imageUri = FileProvider.getUriForFile(
                this,
                "com.example.personalproject.fileprovider",  // Must match manifest
                imageFile
        );

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            try { bitmapProcessed = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);}
//            catch (IOException e) { e.printStackTrace();}

            File f = new File(camera_imagepath);
            bitmapProcessed = BitmapUtilsLatLang.decodeSampledBitmapFromResource(f, 720, 1280);

            bitmapProcessed = getBitMap(bitmapProcessed, camera_imagepath);

            if (bitmapProcessed != null) {
                if (selfie_taken.getVisibility() == View.GONE) {
                    startday_capture_selfie.setVisibility(View.GONE);
                    selfie_taken.setVisibility(View.VISIBLE);
                }
                getLocationNew();
                tv_timeDetails.setText(dateFormat.format(new Date()));
                selfie_img.setImageBitmap(bitmapProcessed);

//                camera_imagepath = BitmapUtilsLatLang.saveVerifySignature(bitmapProcessed, camera_imagepath);
//                setBitmapImage(selfie_img, camera_imagepath);

            }
        }
    }

    Bitmap getBitMap(Bitmap bmp, String camera_imagepath) {
        Bitmap mBtBitmap = null;
        if (bmp != null) {
            if (currentLatLng != null)
                bitmapProcessed = BitmapUtilsLatLang.processBitmap2(bmp, currentLatLng.latitude + "", currentLatLng.longitude + "", "");
            else
                bitmapProcessed = BitmapUtilsLatLang.processBitmap2(bmp, "0", "0", "");
            if (bmp != null && !bmp.isRecycled())
                bmp.recycle();

            mBtBitmap = bitmapProcessed;
            return mBtBitmap;
        }
        return mBtBitmap;
    }
    /*public String site_id = "";
    private boolean isLocationFound;
    @Override
    public void gotGpsValidationResponse(Object response, GPSErrorCode code)
    {
        String userId = preference.getStringFromPreference(Preference.EMP_NO, "");
        if (code == GPSErrorCode.EC_GPS_PROVIDER_NOT_ENABLED) {
            gpsUtills.createdLogDataForApp("GpsProviderNotEnabled", userId, site_id, "");
            showSettingsAlert();

        } else if (code == GPSErrorCode.EC_GPS_PROVIDER_ENABLED) {
            gpsUtills.createdLogDataForApp("GpsProviderEnabled", userId,
                    site_id, "");
        } else if (code == GPSErrorCode.EC_UNABLE_TO_FIND_LOCATION) {
            isLocationFound = false;
            currentLatLng = (LatLng) response;
            gpsUtills.createdLogDataForApp("UnableFindLocation", userId,site_id,
                    "Current Lattitude: "+ currentLatLng.latitude+ ", Current Longitude: "+ currentLatLng.longitude);

            showToast("unable to find location");

        } else if (code == GPSErrorCode.EC_LOCATION_FOUND) {
            if (isGPSEnable(this)) {
                currentLatLng = (LatLng) response;
                isLocationFound = true;
                gpsUtills.createdLogDataForApp("LocationFound", userId, site_id, "Currnet Lattitude: " + currentLatLng.latitude +
                        ", Currnet Longitude: " + currentLatLng.longitude);
            }
            else {
                isLocationFound = false;
                showSettingsAlert();
            }

        } else if (code == GPSErrorCode.EC_CUSTOMER_LOCATION_IS_VALID) {
            gpsUtills.createdLogDataForApp("CustomerLocationIsVaild", userId,site_id, "OfficeInOut");
            //            startJouney();
            showCustomDialog(this,getString(R.string.alert),getString(R.string.Do_you_wanttosubmit_EOT), getString(R.string.Yes), getString(R.string.No), "SubmitEOD", false);
        } else if (code == GPSErrorCode.EC_CUSTOMER_lOCATION_IS_INVAILD) {
            gpsUtills.createdLogDataForApp("CustomerLocationIsNotVaild",preference.getStringFromPreference(Preference.EMP_NO, ""), site_id, "OfficeInOut Not Allowed");
            hideLoader();
            showCustomDialog(this,getString(R.string.alert),getString(R.string.you_are_not_at_depot_location), getString(R.string.OK), null, "", false);
        }
    }*/

    /// /////////////////////////////////////////////////////////////////////////////////
    // by kushwant
    private void getLocationNew1() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
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
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (lastKnownLocation != null) {
                double latitude = lastKnownLocation.getLatitude();
                double longitude = lastKnownLocation.getLongitude();

                // Now you have the latitude and longitude, you can use them to get the address
                String address = LocationUtils.getAddress(this, latitude, longitude);
                // Do something with the address
                tv_locationDetails.setText(address);
            } else {
//                Toast.makeText(this, "No address found for the provided coordinates", Toast.LENGTH_SHORT).show();
                Log.d("getLocation", "addressDetails: No address found");
                tv_locationDetails.setText("No address found");
            }
        } else {
//                Toast.makeText(this, "No address found for the provided coordinates", Toast.LENGTH_SHORT).show();
            Log.d("getLocation", "addressDetails: No address found");
            tv_locationDetails.setText("No address found 1");
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        return image;
    }

    private File createImageFileNew() throws IOException {
        //        File captureImagesStorageDir = new File(Environment.getExternalStorageDirectory()+"/KR/outlet");
        File captureImagesStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/KR/ImagesPP");

        if (!captureImagesStorageDir.exists()) {
            if (!captureImagesStorageDir.mkdirs()) {
                Log.d("Unilever", "Oops! Failed create ");
                return null;
            }
        }

        String timestamp = System.currentTimeMillis() + "";
        File imageFile = new File(captureImagesStorageDir.getPath() + File.separator + "CAPTURE_" + timestamp + ".jpg");
        return imageFile;
    }

    private void setBitmapImage(final ImageView imageView, String capturedImageFilePath) {

        Bitmap stampBitmap = decodeFile(new File(capturedImageFilePath), (int) (1280 * px), (int) (720 * px));
        //        Bitmap stampBitmap = decodeFile(new File(capturedImageFilePath), (int) (200 * px), (int) (200 * px));
        if (stampBitmap != null) {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stampBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

            final WeakReference<Bitmap> reference = new WeakReference<Bitmap>(stampBitmap);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(reference.get());
                    hideLoader();
                }
            });
        }
    }

    public static Bitmap decodeFile(File f, int WIDTH, int HIGHT) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_WIDTH = WIDTH;
            final int REQUIRED_HIGHT = HIGHT;
            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_WIDTH && o.outHeight / scale / 2 >= REQUIRED_HIGHT)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }


    /// /////////////////////////////////////////////////////////////////////////////////
    // Method to add a timestamp to the bitmap
    private Bitmap addTimestampToImage(Bitmap originalBitmap) {
        String timestamp = new SimpleDateFormat("dd MMMM, yyyy HH:mm", Locale.getDefault()).format(new Date());

        Bitmap mutableBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(60);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        Rect textBounds = new Rect();
        paint.getTextBounds(timestamp, 0, timestamp.length(), textBounds);

        int x = mutableBitmap.getWidth() - textBounds.width() - 20; // 20px margin from right
        int y = mutableBitmap.getHeight() - 20; // 20px margin from bottom

        canvas.drawText(timestamp, x, y, paint);

        return mutableBitmap;
    }

    // Method to save the modified bitmap to a file
    private void saveBitmapToFile(Bitmap bitmap, File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
