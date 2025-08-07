package com.example.personalproject.mainPackage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.personalproject.R;
import com.example.personalproject.mainPackage.common.AppConstants;

public class ChangePassword extends BaseActivity {

    LinearLayout llchangepassword;
    @Override
    public void initialize() {
        llchangepassword = (LinearLayout) inflater.inflate(R.layout.change_password, null);
        llBody.addView(llchangepassword, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView tvlanguage1= llchangepassword.findViewById(R.id.tvlanguage1);
        TextView schedule_dialog_Cancel= llchangepassword.findViewById(R.id.schedule_dialog_Cancel);
        TextView schedule_dialog_Submit= llchangepassword.findViewById(R.id.schedule_dialog_Submit);
        schedule_dialog_Submit.setOnClickListener(v -> finish());
        schedule_dialog_Cancel.setOnClickListener(v -> finish());


//        setTypeFaceRobotoOriginalSemibold(llchangepassword);
//        tvlanguage1.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        schedule_dialog_Cancel.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
//        schedule_dialog_Submit.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
    }
}
