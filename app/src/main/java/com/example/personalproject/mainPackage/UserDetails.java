package com.example.personalproject.mainPackage;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.personalproject.R;
import com.example.personalproject.mainPackage.common.AppConstants;
import com.example.personalproject.mainPackage.common.Preference;

public class UserDetails extends BaseActivity {

    LinearLayout lluserDetails;
    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public void initialize() {

        lluserDetails = (LinearLayout) inflater.inflate(R.layout.user_details, null);
        llBody.addView(lluserDetails, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView tvuserName= lluserDetails.findViewById(R.id.tvuserName);
        TextView tvUserType= lluserDetails.findViewById(R.id.tvUserType);
        TextView tvUserId= lluserDetails.findViewById(R.id.tvUserId);
        TextView tvHeader= lluserDetails.findViewById(R.id.tvHeader);

        tvuserName.setText("" + preference.getStringFromPreference(Preference.USER_NAME, ""));


//        setTypeFaceRobotoOriginalSemibold(lluserDetails);
//        tvuserName.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvUserType.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvUserId.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        tvHeader.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
    }
}
