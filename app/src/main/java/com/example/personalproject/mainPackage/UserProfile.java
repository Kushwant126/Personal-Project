package com.example.personalproject.mainPackage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.example.personalproject.R;
import com.example.personalproject.mainPackage.common.AppConstants;
import com.example.personalproject.mainPackage.common.Preference;

public class UserProfile extends BaseActivity{
    LinearLayout llLogin;
    @SuppressLint("SetTextI18n")
    @Override
    public void initialize() {
        llLogin = (LinearLayout) inflater.inflate(R.layout.profile_view, null);
        llBody.addView(llLogin, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView tvuserName= llLogin.findViewById(R.id.tvuserName);
        TextView tvUserType= llLogin.findViewById(R.id.tvUserType);
        TextView tvUserId= llLogin.findViewById(R.id.tvUserId);
        TextView tvUserCompany= llLogin.findViewById(R.id.tvUserCompany);
        TextView tvUserProfile= llLogin.findViewById(R.id.tvUserProfile);
        TextView tvChangePassword= llLogin.findViewById(R.id.tvChangePassword);
        TextView tvUserModel= llLogin.findViewById(R.id.tvUserModel);
        TextView tvUserOS= llLogin.findViewById(R.id.tvUserOS);
        TextView tvUserOSVersion= llLogin.findViewById(R.id.tvUserOSVersion);
        TextView tvlogout= llLogin.findViewById(R.id.tvlogout);

        tvChangePassword.setOnClickListener(v -> {
            Intent profile=new Intent(UserProfile.this,ChangePassword.class);
            startActivity(profile);
        });
        tvUserProfile.setOnClickListener(v -> {
            Intent profile=new Intent(UserProfile.this,UserDetails.class);
            startActivity(profile);
        });

        tvuserName.setText("" + preference.getStringFromPreference(Preference.USER_NAME, ""));
        tvUserModel.setText("" + preference.getStringFromPreference(Preference.DEVICE_MODEL, ""));
        tvUserOS.setText("" + preference.getStringFromPreference(Preference.DEVICE_MODEL_MANUFACTURER, ""));

        String os= preference.getStringFromPreference(Preference.DEVICE_OS, "")
                +" ("+preference.getStringFromPreference(Preference.DEVICE_OS_VERSION, "")+")";
        tvUserOSVersion.setText(""+os);
        tvlogout.setOnClickListener(v -> clickLogOut());



//        setTypeFaceRobotoOriginalSemibold(llLogin);
//        tvUserId.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvUserCompany.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvUserProfile.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvChangePassword.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvUserModel.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvuserName.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvUserType.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvlogout.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvUserOSVersion.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvUserOS.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
    }
}
