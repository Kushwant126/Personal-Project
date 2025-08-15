package com.example.personalproject.mainPackage;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.personalproject.R;
import com.example.personalproject.mainPackage.common.AppConstants;
import com.example.personalproject.mainPackage.common.Preference;
import com.example.personalproject.mainPackage.gpsutills.GPSCallback;
import com.example.personalproject.mainPackage.gpsutills.common.GPSErrorCode;
import com.google.android.gms.maps.model.LatLng;

public class LoginAcivity extends BaseActivity implements GPSCallback {
    private EditText etUserName, etPassword;
    private Button btnLogin;
    private TextView tvForgotPassword, tvServerSettings, tvRemember;
    private LinearLayout ll_rememberme, llLogin;
    private final boolean isFirstTimeLogin = false;
    private TextView tvIMEINO_NEW;

    @Override
    public void initialize() {
//        llLogin = (LinearLayout) inflater.inflate(R.layout.login, null);
        llLogin = (LinearLayout) inflater.inflate(R.layout.new_login_screen, null);
        llBody.addView(llLogin, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setTypeFaceRobotoOriginalMedium(llLogin);
        btnLogin = findViewById(R.id.btnLogin);
//        isStartDayDone=false;

        gpsUtills.setListner(this);
        gpsUtills.isGpsProviderEnabled();
        gpsUtills.getCurrentLatLng();

        btnLogin.setOnClickListener(v -> {
            showLoader(getString(R.string.Syncing_Data));
            preference.saveBooleanInPreference(Preference.START_DAY, false);
            preference.commitPreference();
            Intent intent = new Intent(LoginAcivity.this, MainActivity.class);//CompetitorsListActivity
            startActivity(intent);
            overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            finish();
        });
        tvIMEINO_NEW = llLogin.findViewById(R.id.tvIMEINO_NEW);
        tvIMEINO_NEW.setText("IMEI : " + getUniqueID());

        TextView tvAppVersion = llLogin.findViewById(R.id.tvAppVersion);
        TextView tvVersion = llLogin.findViewById(R.id.tvVersion);
        etUserName = llLogin.findViewById(R.id.etUserName);
        btnLogin = llLogin.findViewById(R.id.btnLogin);
        tvServerSettings = llLogin.findViewById(R.id.tvServerSettings);
        etPassword = llLogin.findViewById(R.id.etPassword);
        tvRemember = llLogin.findViewById(R.id.tvRemember);
        ll_rememberme = llLogin.findViewById(R.id.ll_rememberme);
        tvForgotPassword = llLogin.findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setVisibility(View.GONE);

        etUserName.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvServerSettings.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);
        etPassword.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvForgotPassword.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);
        tvAppVersion.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);
//        tvRemember.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
        btnLogin.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);

//        btnLogin.performClick(); //by Kush
    }

    @Override
    public void gotGpsValidationResponse(Object response, GPSErrorCode code) {

        if (code == GPSErrorCode.EC_UNABLE_TO_FIND_LOCATION) {
            currentLatLng = (LatLng) response;
            //unable to find location means lat = 0.0 ,lng = 0.0;
        } else if (code == GPSErrorCode.EC_LOCATION_FOUND) {
            currentLatLng = (LatLng) response;

            //Added by Asad
            AppConstants.currentLat = String.valueOf(currentLatLng.latitude);
            AppConstants.currentLng = String.valueOf(currentLatLng.longitude);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        gpsUtills.connectGoogleApiClient();
    }

    @Override
    public void onStop() {
        super.onStop();
        gpsUtills.disConnectGoogleApiClient();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gpsUtills.stopLocationUpdates();
    }

    @Override
    public void onResume() {
        super.onResume();
        gpsUtills.startLocationUpdates();
    }
}
