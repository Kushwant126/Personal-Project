package com.example.personalproject.mainPackage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.personalproject.R;
import com.example.personalproject.mainPackage.common.AppConstants;
import com.example.personalproject.mainPackage.common.Preference;
import com.example.personalproject.mainPackage.gpsutills.GPSCallback;
import com.example.personalproject.mainPackage.gpsutills.common.GPSErrorCode;
import com.example.personalproject.mainPackage.utilities.CalendarUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends BaseActivity implements GPSCallback {
    private LinearLayout llSplash;
    private Vector<String> vecLanguage;
    private String isLanguageSelected = "";
    private static final int PERMISSION_REQUEST_CODE = 200;
    boolean isOld = true, isbackPressed = true;
    ProgressBar progressBar;
    TextView tEnglish, tArabic, tvSubmit, tvlanguage;
    LinearLayout llyenglish, llyarabic;

    /* @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         // Call the initialize method after setting content view
         initialize();
     }*/
    @Override
    public void initialize() {
        llSplash = (LinearLayout) inflater.inflate(R.layout.new_splash_screen, null);
        llBody.addView(llSplash, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        progressBar = findViewById(R.id.progressBar);
        TextView t1 = findViewById(R.id.t1);
        t1.setTypeface(ResourcesCompat.getFont(this, R.font.montserrat_extrabold));
        TextView t2 = findViewById(R.id.t2);
        t2.setTypeface(ResourcesCompat.getFont(this, R.font.montserrat_light));

        vecLanguage = new Vector<String>();
        vecLanguage.add("English");
        vecLanguage.add("Arabic");

        initializeControlls();

//        showLanguagePopup();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission(getRequestPermissionList());
        } else
            showLanguagePopup();
        /*AppConstants.SanFranciscoDisplay_Regular 	= Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Regular.ttf");
        AppConstants.SanFranciscoDisplay_Medium 	= Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Medium.ttf");
        AppConstants.SanFranciscoDisplay_Semibold 	= Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-SemiBold.ttf");
        AppConstants.SanFranciscoDisplay_Bold 		= Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Bold.ttf");

        AppConstants.montserrat_black        = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Black.ttf");
        AppConstants.montserrat_light        = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Light.ttf");
        AppConstants.montserrat_extralight   = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-ExtraLight.ttf");
        AppConstants.montserrat_thin         = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Thin.ttf");
        AppConstants.montserrat_extrabold    = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-ExtraBold.ttf");*/

    }

    public ArrayList<String> getRequestPermissionList() {
        ArrayList<String> permissions = new ArrayList<>();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);

            if (info.requestedPermissions != null) {
                for (String perm : info.requestedPermissions) {
                    // Check Android version to determine which permissions to consider
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 14+
                        if (perm.equals(Manifest.permission.READ_EXTERNAL_STORAGE) ||
                                perm.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            continue; // Skip deprecated permissions
                        }
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) { // Android 12 and 13
                        if (perm.equals(Manifest.permission.READ_MEDIA_IMAGES) ||
                                perm.equals(Manifest.permission.READ_MEDIA_AUDIO) ||
                                perm.equals(Manifest.permission.READ_MEDIA_VIDEO)) {
                            continue; // Skip permissions not applicable to Android 12+
                        }
                    }

                    // Only add permissions not already granted
                    if (!AppConstants.NORMAL_PERMISSIONS.contains(perm) &&
                            ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                        permissions.add(perm);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(); // Log or handle the exception as needed
        }
        return permissions;
    }


    public void initializeControlls() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        if (TextUtils.isEmpty(preference.getStringFromPreference(Preference.BUILD_INSTALLATIONDATE, "")))
            preference.saveStringInPreference(Preference.BUILD_INSTALLATIONDATE, CalendarUtils.getOrderPostDate());
        preference.saveIntInPreference(Preference.DEVICE_DISPLAY_WIDTH, displaymetrics.widthPixels);
        preference.saveIntInPreference(Preference.DEVICE_DISPLAY_HEIGHT, displaymetrics.heightPixels);

        File imageFile = getOutputMediaFile();
        preference.saveStringInPreference(Preference.FILE_PATH, imageFile.toString());


        gpsUtills.setListner(this);
        gpsUtills.getCurrentLatLng();
        if (!preference.getbooleanFromPreference(Preference.IS_APP_FIRSTTIME_LAUNCH, false)) {
            preference.saveBooleanInPreference(Preference.IS_APP_FIRSTTIME_LAUNCH, true);
            preference.commitPreference();
        }
        if (preference.getbooleanFromPreference(Preference.IS_APP_FIRSTTIME_LAUNCH, false)) {
            gpsUtills.isDeviceConfiguredProperly();
        }
        if (preference.getbooleanFromPreference(Preference.IS_APP_CRASHED, false)) {
            preference.saveBooleanInPreference(Preference.IS_APP_CRASHED, false);
            preference.commitPreference();
            Toast.makeText(this, getString(R.string.unexpected_exception_app_restarted), Toast.LENGTH_LONG).show();
        }


        preference.commitPreference();

        AppConstants.DIVICE_WIDTH = displaymetrics.widthPixels;
        AppConstants.DIVICE_HEIGHT = displaymetrics.heightPixels;
//        AppConstants.CategoryIconsPath 	= 	Environment.getExternalStorageDirectory().getAbsolutePath()+"/AlSeer/CategoryIcons/";
//        AppConstants.productCatalogPath = 	Environment.getExternalStorageDirectory().getAbsolutePath()+"/AlSeer/";
//        AppConstants.baskinLogoPath 	= 	AppConstants.productCatalogPath+"AlSeerLogo";
    }

    private void moveToLogin() {
        try {
//            Locale locale = null;
//            if (isLanguageSelected.equalsIgnoreCase(AppConstants.langEnglish)) locale = new Locale("en_US");
//            else  locale = new Locale("ar");

            Locale locale = isLanguageSelected.equalsIgnoreCase(AppConstants.langEnglish) ? new Locale("en_US") : new Locale("ar");

            DisplayMetrics dm = this.getResources().getDisplayMetrics();
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            this.getApplicationContext().getResources().updateConfiguration(config, dm);

            AppConstants.DATABASE_PATH = getApplication().getFilesDir().toString() + "/";
            AppConstants.SanFranciscoDisplay_Regular = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Regular.ttf");
            AppConstants.SanFranciscoDisplay_Medium = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Medium.ttf");
            AppConstants.SanFranciscoDisplay_Semibold = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-SemiBold.ttf");
            AppConstants.SanFranciscoDisplay_Bold = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Bold.ttf");

            AppConstants.montserrat_black = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Black.ttf");
            AppConstants.montserrat_light = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Light.ttf");
            AppConstants.montserrat_extralight = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-ExtraLight.ttf");
            AppConstants.montserrat_thin = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-Thin.ttf");
            AppConstants.montserrat_extrabold = Typeface.createFromAsset(getApplicationContext().getAssets(), "Montserrat-ExtraBold.ttf");
            Intent intent = new Intent(this, LoginAcivity.class);//CompetitorsListActivity
            startActivity(intent);
            overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (isbackPressed) {
            progressBar.setVisibility(View.VISIBLE);
            isbackPressed = false;
            showLanguagePopup();
        } else {
            super.onBackPressed(); // Call the default back button behavior if button B is not enabled
        }
    }

    @SuppressLint("SetTextI18n")
    public void showLanguagePopup() {
        progressBar.setVisibility(View.VISIBLE);

        //		}, 10000); // 10 seconds delay (10000 milliseconds)
//		}, 5000);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            isbackPressed = true;
            setDefaultEnglishLanguage();
            showLanguagePopup1(); // Call your method here after the delay
        }, 1500);
    }

    private void setDefaultEnglishLanguage() {
        preference.saveStringInPreference(Preference.LANGUAGE, vecLanguage.get(0));
        preference.commitPreference();
    }

    private void showLanguagePopup1() {

        if (customDialog == null || !customDialog.isShowing()) {
            Dialog customDialog = new Dialog(this);
            customDialog.setContentView(R.layout.new_login_select_langugage);
            Objects.requireNonNull(customDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);

            llyenglish = customDialog.findViewById(R.id.llyEnglish);
            llyarabic = customDialog.findViewById(R.id.llyArabic);

            tEnglish = customDialog.findViewById(R.id.tEnglish);
            tArabic = customDialog.findViewById(R.id.tArabic);
            tvSubmit = customDialog.findViewById(R.id.tvSubmit);
            tvlanguage = customDialog.findViewById(R.id.tvlanguage1);

            String languageText = getString(R.string.select_language);
            tvlanguage.setText(languageText);
            setEnglishLanguage();

            llyenglish.setOnClickListener(view -> setEnglishLanguage());
            llyarabic.setOnClickListener(view -> {
//                llyarabic.setBackgroundColor(Color.parseColor("#EF9300"));
//                llyenglish.setBackgroundColor(Color.WHITE);
//
//                tEnglish.setTextColor(Color.BLACK);
//                tArabic.setTextColor(Color.WHITE);
//                preference.saveStringInPreference(Preference.LANGUAGE, vecLanguage.get(1));
//                preference.commitPreference();
                showToast("Arabic language is Still Under Development...");
            });

            tvSubmit.setOnClickListener(view -> moveToLogin());
            tvlanguage.setTypeface(ResourcesCompat.getFont(this, R.font.montserrat_bold));
            tvSubmit.setTypeface(ResourcesCompat.getFont(this, R.font.montserrat_bold));
            tEnglish.setTypeface(ResourcesCompat.getFont(this, R.font.montserrat_medium));
            tArabic.setTypeface(ResourcesCompat.getFont(this, R.font.montserrat_medium));

            customDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            customDialog.getWindow().setGravity(Gravity.BOTTOM);
            if (!isFinishing()) customDialog.show();
        }
    }

    private void setEnglishLanguage() {
        llyenglish.setBackgroundColor(Color.parseColor("#EF9300"));
        llyarabic.setBackgroundColor(Color.WHITE);

        tEnglish.setTextColor(Color.WHITE);
        tArabic.setTextColor(Color.BLACK);
        preference.saveStringInPreference(Preference.LANGUAGE, vecLanguage.get(0));
        preference.commitPreference();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gpsUtills.isGpsProviderEnabled();
    }
    @Override
    public void gotGpsValidationResponse(Object response, GPSErrorCode code) {
        if (code == GPSErrorCode.EC_GOOGLEPLAY_SERVICES_UPDATE_REQUIRED) {
            showGoogleUpdateServiceAlert();
        } else if (code == GPSErrorCode.EC_GPS_PROVIDER_NOT_ENABLED) {
            // Prompt the user to enable GPS
            showLanguagePopup(); // Assuming this provides an explanation or UI feedback
            showSettingsAlert(); // Show settings alert to enable GPS
        } else if (code == GPSErrorCode.EC_GPS_PROVIDER_ENABLED) {
            // GPS provider is enabled; check permissions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                requestPermission(getRequestPermissionList()); // Request permissions if needed
            else showLanguagePopup(); // Handle the case for pre-M devices
        }
    }


    private void requestPermission(ArrayList<String> permissionList) {
        if (!permissionList.isEmpty())
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[0]), PERMISSION_REQUEST_CODE);
        else showLanguagePopup();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, final String permissions[], int[] grantResults) {
        if (false) super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                //                int i = 0;
                if (grantResults.length > 0) {

                    for (int i = 0; i < permissions.length; i++) {
                        String permission = permissions[i];
                        int grantResult = grantResults[i];

                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            Log.d("PermissionResult", permission + " is granted.");
                        } else {
                            Log.d("PermissionResult", permission + " is denied.");
                        }
                    }

                    boolean deviceLocation = true;
					/*for (int j = 0; j < grantResults.length; j++) {
						deviceLocation = grantResults[j] == PackageManager.PERMISSION_GRANTED;
						if (!deviceLocation) {
							i = j;
							break;
						}
					}*/
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            deviceLocation = false;
                            break;
                        }
                    }
                    if (deviceLocation) {
                        showLanguagePopup();
//                        Toast.makeText(this, "Permission Granted, Now you can access device data", Toast.LENGTH_LONG).show();
                        showToast("Permission Granted, Now you can access device data");
                    } else {
//                        Toast.makeText(this, "Permission Denied, You cannot access device data", Toast.LENGTH_LONG).show();
                        showToast("Permission Denied, You cannot access device data");
//                        showToast("Some permissions were denied. Please enable them in settings.");
//                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                        Uri uri = Uri.fromParts("package", getPackageName(), null);
//                        intent.setData(uri);
//                        startActivity(intent);

                        for (int i = 0; i < permissions.length; i++) {
                            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                                Log.d("PermissionResultNew", permissions[i] + " is denied.");
                            }
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            //                            requestPermissions(new String[]{READ_PHONE_STATE},PERMISSION_REQUEST_CODE);
                            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
                        }

                    }
                }
                break;
        }
    }

}

