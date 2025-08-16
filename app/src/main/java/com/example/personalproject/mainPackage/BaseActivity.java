package com.example.personalproject.mainPackage;

import static android.view.View.LAYOUT_DIRECTION_LTR;
import static android.view.View.LAYOUT_DIRECTION_RTL;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.personalproject.R;
import com.example.personalproject.mainPackage.common.AppConstants;
import com.example.personalproject.mainPackage.common.CustomDialog;
import com.example.personalproject.mainPackage.common.Flip3dAnimation;
import com.example.personalproject.mainPackage.common.MenuClass;
import com.example.personalproject.mainPackage.common.Preference;
import com.example.personalproject.mainPackage.dataobject.MenuDO;
import com.example.personalproject.mainPackage.gpsutills.GPSUtills;
import com.example.personalproject.mainPackage.httpimage.HttpImageManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

//public abstract class BaseActivity extends FragmentActivity implements AbsListView.OnScrollListener, ViewPager.OnPageChangeListener {
public abstract class BaseActivity extends AppCompatActivity implements AbsListView.OnScrollListener, ViewPager.OnPageChangeListener {
    private static final int PERMISSION_REQUEST_CODE = 1;
    public static float px;
    public static int selectedBar = 1;
    public LatLng currentLatLng = null;
    public PopupWindow customKeyBoardpopup;
    public LayoutInflater inflater;
    public DrawerLayout drawerLayout;
    public Button btnMenu;
    public Preference preference;
    public LinearLayout llHeader, llBody, llMenuOne, llMenuTwo, llMenuThree, llOthers, llNavigationBar, llstart_day, llnon_start_day;
    public ImageView ivMenuOne, ivMenuTwo, ivMenuThree, ivOthers, ivOutsideImage, ImageView01, keyBack, ivprofile;
    public TextView tvMenuOne, tvMenuTwo, tvMenuThree, tvOthers;
    public View v1, v2, v3, v4;
    public CardView cvBottomNavigationBar;
    public PendingIntent pIntent;
    public boolean isCancelableLoader;
    public GPSUtills gpsUtills;
    public FrameLayout flMenu;
    public boolean isStartDayDone = false, isEOTDoneNew, isCanStartDayNew = true, isShortRoute, isPreviousDayEOTDone;
    protected DashBoardOptionsCustomAdapter adapter;
    protected Dialog dialog;
    SimpleDateFormat dateFormat;
    TextView tvEndtrip;
    CustomDialog subMenuPopUP, customDialog;
    MenuAdapter subMenuAdapter;
    Vector<MenuDO> vecMenus = null;
    int textSize_b = 12, textSize_s = 9, count = 0, userType = 100;
    String[] menus;
    Integer[] menuIcons;
    String selectedLanguage = "";
    private Toast toast;
    private int groupPos = 0;
    private View customKeyBoard, globalView;
    private TextView tvDone, tvNext, tvOne, tvTwo, tvThree, tvFour, tvFive, tvSix, tvSeven, tvEight, tvNine, tvZero, tvClear, tvDot, tvUserADID, tvUsername;
    private ExpandableListView lvDashBoard;
    private Animation rotateXaxis;
    private ImageView ivBuildImage, ivSignal;
    private Dialog updateGooglePlayServiceDialog = null;

    public abstract void initialize();

    @SuppressLint({"NewApi", "WrongConstant"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.menu_checkout_icon)
                .setContentTitle("My Notification")
                .setContentText("Hello World!")
                .setContentIntent(pIntent) // Here is where pIntent is used
                .setAutoCancel(true);
        notificationManager.notify(0, builder.build());*/

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.new_app_color));//by kush to change the color at the top i.e notification

        setContentView(R.layout.base_new1);
        initializeControle();

        gpsUtills = GPSUtills.getInstance(BaseActivity.this);
        gpsUtills.setLogEnable(true);
        gpsUtills.setPackegeName(getPackageName());

        ivprofile.setOnClickListener(v -> {
            Intent profile = new Intent(BaseActivity.this, UserProfile.class);
            startActivity(profile);
//            finish();
        });


        initialize();
        if (adapter == null) {
            vecMenus = null;
            if (false) {
                if (userType == AppConstants.USER_PRESELER)
                    vecMenus = AppConstants.loadCheckinMenuforPreseller();
                else if (userType == AppConstants.USER_VAN_SALES)
                    vecMenus = AppConstants.loadCheckinMenuForVanSales();
                else
                    vecMenus = AppConstants.loadCheckinMenuForMerchandiser();
            } else {
                if (userType == AppConstants.USER_PRESELER)
                    vecMenus = AppConstants.loadMenuforPreseller();
                else if (userType == AppConstants.USER_VAN_SALES)
                    vecMenus = AppConstants.loadOutsideCheckinMenuForVanSales();
                else
                    vecMenus = AppConstants.loadMenuForMerchandiser();
            }


            adapter = new DashBoardOptionsCustomAdapter(vecMenus);
            lvDashBoard.setAdapter(adapter);
            lvDashBoard.setCacheColorHint(0);
            lvDashBoard.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            lvDashBoard.setScrollbarFadingEnabled(true);

//            lvDashBoard.setDividerHeight(1);
//            lvDashBoard.setDivider(getResources().getDrawable(R.drawable.saparetor_dash));

        }
        btnMenu.setOnClickListener(v -> {
            hideKeyBoard(v);
            hideCustomKeyBoard();
            TopBarMenuClick();
            if (adapter != null) {
//                    adapter.refreshDashBoardOptionsCustomAdapter(new MenuClass(BaseActivity.this).loadMenu(userType, isCheckin()));
                adapter.refreshDashBoardOptionsCustomAdapter(new MenuClass(BaseActivity.this).loadMenu(userType, false));
//                    adapter.refreshDashBoardOptionsCustomAdapter(new MenuClass(BaseActivity.this).loadMenu(100, true));
            }
        });

        initializeKeyboard();
        bottomNavigationOperation();
        setBottomNavigationDrawables();
        setBottomNavigationVisible();

        setdrawerLayoutVisibile();
        closeDrawer();
        logDeviceInfo();
    }

    public void logDeviceInfo() {
        String deviceModel = Build.MODEL;
        String manufacturer = Build.MANUFACTURER;
        String osVersion = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;

        String osName;
        switch (sdkVersion) {
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
                osName = "Ice Cream Sandwich";
                break;
            case Build.VERSION_CODES.JELLY_BEAN:
                osName = "Jelly Bean";
                break;
            case Build.VERSION_CODES.KITKAT:
                osName = "KitKat";
                break;
            case Build.VERSION_CODES.LOLLIPOP:
                osName = "Lollipop";
                break;
            case Build.VERSION_CODES.M:
                osName = "Marshmallow";
                break;
            case Build.VERSION_CODES.N:
                osName = "Nougat";
                break;
            case Build.VERSION_CODES.O:
                osName = "Oreo";
                break;
            case Build.VERSION_CODES.P:
                osName = "Pie";
                break;
            case Build.VERSION_CODES.Q:
                osName = "Q";
                break;
            case Build.VERSION_CODES.R:
                osName = "R";
                break;
            case Build.VERSION_CODES.S:
                osName = "S";
                break;
            case Build.VERSION_CODES.TIRAMISU:
                osName = "Tiramisu";
                break;
            default:
                osName = "Unknown";
                break;
        }

        Log.d("DeviceInfo", "Model: " + deviceModel);
        Log.d("DeviceInfo", "Manufacturer: " + manufacturer);
        Log.d("DeviceInfo", "OS Version: " + osVersion);
        Log.d("DeviceInfo", "OS Name: " + osName);

        preference.saveStringInPreference(Preference.DEVICE_MODEL, deviceModel);
        preference.saveStringInPreference(Preference.DEVICE_MODEL_MANUFACTURER, manufacturer);
        preference.saveStringInPreference(Preference.DEVICE_OS, osName);
        preference.saveStringInPreference(Preference.DEVICE_OS_VERSION, osVersion);
        preference.commitPreference();
    }

    private void setSignalResource() {
        if (ivSignal != null) {
            if (isNetworkConnectionAvailable(BaseActivity.this))
                ivSignal.setImageResource(R.drawable.signal);
            else
                ivSignal.setImageResource(R.drawable.nosignal);
        }
    }

    public boolean isNetworkConnectionAvailable(Context context) {
        boolean isNetworkConnectionAvailable = false;
        // checking the Internet availability
//        ConnectivityManager connectivityManager1 = (ConnectivityManager) context.getSystemService("connectivity");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null)
            isNetworkConnectionAvailable = activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
        return isNetworkConnectionAvailable;
    }

    public void setLanguage() {
        Locale locale = null;
        try {
            selectedLanguage = preference.getStringFromPreference(Preference.LANGUAGE, "");
            if (selectedLanguage != null && !selectedLanguage.equalsIgnoreCase("")) {
                if (selectedLanguage.equalsIgnoreCase(AppConstants.langEnglish)) {
                    locale = new Locale("en_US");
                } else {
//                	locale = new Locale("ar_EG");
                    locale = new Locale("ar");
                }
                DisplayMetrics dm = getResources().getDisplayMetrics();
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getApplicationContext().getResources()
                        .updateConfiguration(config, dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(flMenu)) {
            drawerLayout.closeDrawer(flMenu);
        } else {
            drawerLayout.openDrawer(flMenu);
        }
    }

    public void closeDrawer() {
        drawerLayout.closeDrawer(flMenu);
    }

    protected void onResume() {
        super.onResume();

        setSignalResource();
        setLanguage();
        isStartDayDone = preference.getbooleanFromPreference(Preference.START_DAY, false);//test
//        ServiceURLs.setImageURL(preference);
        selectedLanguage = preference.getStringFromPreference(Preference.LANGUAGE, "");

        if (llHeader != null) {

            if (selectedLanguage.equalsIgnoreCase(AppConstants.langEnglish)) {
//                llHeader.setLayoutDirection(LayoutDirection.LTR);
                llHeader.setLayoutDirection(LAYOUT_DIRECTION_LTR);
            } else {
//                llHeader.setLayoutDirection(LayoutDirection.RTL);
                llHeader.setLayoutDirection(LAYOUT_DIRECTION_RTL);
            }
        }
        if (selectedLanguage.equalsIgnoreCase(AppConstants.langEnglish)) {
            llBody.setLayoutDirection(LAYOUT_DIRECTION_LTR);

        } else {
            llBody.setLayoutDirection(LAYOUT_DIRECTION_RTL);
        }
        if (selectedLanguage.equalsIgnoreCase(AppConstants.langEnglish)) {
            flMenu.setLayoutDirection(LAYOUT_DIRECTION_LTR);
            drawerLayout.setLayoutDirection(LAYOUT_DIRECTION_LTR);

        } else {
            flMenu.setLayoutDirection(LAYOUT_DIRECTION_RTL);
            drawerLayout.setLayoutDirection(LAYOUT_DIRECTION_RTL);
        }

        showLoader(getString(R.string.please_wait));
        isEOTDoneNew = false;
        isCanStartDayNew = true;

        hideLoader();
//            if(isCanStartDayNew && !isStartDayDone){

//        isStartDayDone = true; //by Kush remove this only for testing purpose..
        if (isStartDayDone) {
            llstart_day.setVisibility(View.GONE);
            llnon_start_day.setVisibility(View.VISIBLE);
        } else {
            llstart_day.setVisibility(View.VISIBLE);
            llnon_start_day.setVisibility(View.GONE);
        }
        btnMenu.setVisibility(View.GONE);
    }

    private void setdrawerLayoutVisibile() {
        if (BaseActivity.this instanceof SplashScreenActivity
                || BaseActivity.this instanceof LoginAcivity) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    private void setBottomNavigationVisible() {
        if (BaseActivity.this instanceof MainActivity
                || BaseActivity.this instanceof LeaderBoard) {
            cvBottomNavigationBar.setVisibility(View.VISIBLE);
            llHeader.setVisibility(View.VISIBLE);
        } else {
            cvBottomNavigationBar.setVisibility(View.GONE);
            llHeader.setVisibility(View.GONE);
        }
    }

    public void showLoader(String str) {
        runOnUiThread(new RunShowLoaderCustom(str));
    }

    public void showLoader(String msg, String title) {
        runOnUiThread(new RunShowLoaderCustom(msg, title));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void applyRotation() {
        BitmapDrawable bd = (BitmapDrawable) BaseActivity.this.getResources().getDrawable(R.drawable.progress_mid_icon);
        float centerY = bd.getBitmap().getHeight() / 2.0f;
        float centerX = bd.getBitmap().getWidth() / 2.0f;

        final Flip3dAnimation rotation = new Flip3dAnimation((float) 0, (float) 360, centerX, centerY);
        rotation.setDuration(1000);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new LinearInterpolator());
        ImageView01.startAnimation(rotation);
    }

    private void initializeControle() {
        llHeader = findViewById(R.id.llHeader);
        llBody = findViewById(R.id.llBody);
        lvDashBoard = findViewById(R.id.lvDashBoard);
        lvDashBoard.setGroupIndicator(null);
        drawerLayout = findViewById(R.id.drawerLayout);
        btnMenu = findViewById(R.id.btnMenu);
        flMenu = findViewById(R.id.flMenu);
        ivprofile = findViewById(R.id.ivprofile);
        ivSignal = findViewById(R.id.ivSignal);
        llstart_day = findViewById(R.id.llstart_day);
        TextView start_day = findViewById(R.id.start_day);
        start_day.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
        dateFormat = new SimpleDateFormat("h:mm a, dd-MMM-yyyy", Locale.getDefault());
        //getting preference for start day
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                TopBarMenuClick();
                if (adapter != null) {
//                    adapter.refreshDashBoardOptionsCustomAdapter(new MenuClass(getBaseActivityContext()).loadMenu(userType,isCheckin()));
                    adapter.refreshDashBoardOptionsCustomAdapter(new MenuClass(BaseActivity.this).loadMenu(userType, false));
                }
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        }); //by kush for loading the on swipe of the menu options

        px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        preference = new Preference(getApplicationContext());
//        inflater = this.getLayoutInflater();// get the inflater object for inflating layouts.
        inflater = LayoutInflater.from(this);

        preference.saveStringInPreference(Preference.USER_NAME, "Kushwant M");
        preference.commitPreference();
        isStartDayDone = preference.getbooleanFromPreference(Preference.START_DAY, false);
    }

    private void bottomNavigationOperation() {

        llMenuOne = findViewById(R.id.llMenuOne);
        llMenuTwo = findViewById(R.id.llMenuTwo);
        llMenuThree = findViewById(R.id.llMenuThree);
        llOthers = findViewById(R.id.llOthers);

        llNavigationBar = findViewById(R.id.llNavigationBar);
        llnon_start_day = findViewById(R.id.llnon_start_day);
        llstart_day = findViewById(R.id.llstart_day);
        ivMenuOne = findViewById(R.id.ivMenuOne);
        ivMenuTwo = findViewById(R.id.ivMenuTwo);
        ivMenuThree = findViewById(R.id.ivMenuThree);
        ivOthers = findViewById(R.id.ivOthers);

        tvMenuOne = findViewById(R.id.tvMenuOne);
        tvMenuTwo = findViewById(R.id.tvMenuTwo);
        tvMenuThree = findViewById(R.id.tvMenuThree);
        tvOthers = findViewById(R.id.tvOthers);

        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);

        cvBottomNavigationBar = findViewById(R.id.cvBottomNavigationBar);
        menus = MenuClass.MENU_DASHBOARD;
        menuIcons = MenuClass.MENU_ICON_DASHBOARD;

        if (menus != null && menus.length > 0) {
            tvMenuOne.setText(getMenuDO1(menus[0]));
            ivMenuOne.setImageDrawable(getResources().getDrawable(menuIcons[0]));
            ivMenuOne.setTag(menuIcons[0]);

            tvMenuTwo.setText(getMenuDO1(menus[1]));
            ivMenuTwo.setImageDrawable(getResources().getDrawable(menuIcons[1]));
            ivMenuTwo.setTag(menuIcons[1]);

            tvMenuThree.setText(getMenuDO1(menus[2]));
            ivMenuThree.setImageDrawable(getResources().getDrawable(menuIcons[2]));
            ivMenuThree.setTag(menuIcons[2]);

            tvOthers.setText(getMenuDO1(menus[3]));
            ivOthers.setImageDrawable(getResources().getDrawable(menuIcons[3]));
            ivOthers.setTag(menuIcons[3]);
        } else {
            tvMenuOne.setText("");
            ivMenuOne.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_1));

            tvMenuTwo.setText("");
            ivMenuTwo.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_1));

            tvMenuThree.setText("");
            ivMenuThree.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_1));

            tvOthers.setText("");
            ivOthers.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_1));
        }
        Log.i("selectedBar", "onCreate: selectedBar ::" + selectedBar);


        llMenuOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedBar != 1) {
//                    callMenuOne();
                    callMenu(1);
                }
                Log.i("selectedBar", "onCreate: selectedBar ::" + selectedBar);
            }
        });
        llMenuTwo.setOnClickListener(view -> {
            if (selectedBar != 2) {
//                    callMenuTwo();
                callMenu(2);
            }
            Log.i("selectedBar", "onCreate: selectedBar ::" + selectedBar);
        });
        llMenuThree.setOnClickListener(view -> {
//                if (isShortRoute && !isPreviousDayEOTDone)
//                    showCustomDialog(BaseActivity.this, getResources().getString(R.string.warning), getString(R.string.Please_submit_the_previous_trip_EOT), getResources().getString(R.string.OK), null, "");
//                else if(selectedBar != 3)
//                    callMenuThree();
            showToast("JP is underDevelopment...!!!");
//            callMenu(3);
            Log.i("selectedBar", "onCreate: selectedBar ::" + selectedBar);
        });
        llOthers.setOnClickListener(view -> {
            if (selectedBar != 4) {
            }
//            callOthers();
            callMenu(4);
            Log.i("selectedBar", "onCreate: selectedBar ::" + selectedBar);
        });
    }

    public void HeaderAndBottomNavigationVisible() {
        llHeader.setVisibility(View.VISIBLE);
        cvBottomNavigationBar.setVisibility(View.VISIBLE);
    }

    public void HeaderAndBottomNavigationGone() {
        llHeader.setVisibility(View.GONE);
        cvBottomNavigationBar.setVisibility(View.GONE);
    }

    public void setBottomNavigationDrawables() {
        switch (selectedBar) {
            case 1:
                setMenuOne();
                break;
            case 2:
                setMenuTwo();
                break;
            case 3:
                setMenuThree();
                break;
            case 4:
                setMenuOthers();
                break;
        }
    }


    private void setMenuOthers() {
        Object tag = ivOthers.getTag();
        if (tag instanceof Integer) {
            int tagValue = (Integer) tag;
            if (tagValue == R.drawable.dashboard_1) {
                ivOthers.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_2));
            } else if (tagValue == R.drawable.leaderboard_1) {
                ivOthers.setImageDrawable(getResources().getDrawable(R.drawable.leaderboard_2));
            } else if (tagValue == R.drawable.jp_1) {
                ivOthers.setImageDrawable(getResources().getDrawable(R.drawable.jp_2));
            } else if (tagValue == R.drawable.menu_1) {
                ivOthers.setImageDrawable(getResources().getDrawable(R.drawable.menu_1));
            }
        }

        if (menuIcons != null && menuIcons.length > 0) {
            ivMenuOne.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_1));
            ivMenuTwo.setImageDrawable(getResources().getDrawable(menuIcons[1]));
            ivMenuThree.setImageDrawable(getResources().getDrawable(menuIcons[2]));
        }

        ivMenuOne.setBackground(null);
        ivMenuThree.setBackground(null);
        ivOthers.setBackground(null);

        v1.setVisibility(View.INVISIBLE);
        v2.setVisibility(View.INVISIBLE);
        v3.setVisibility(View.INVISIBLE);
        v4.setVisibility(View.VISIBLE);

        tvMenuOne.setTextSize(textSize_s);
        tvMenuTwo.setTextSize(textSize_s);
        tvMenuThree.setTextSize(textSize_s);
        tvOthers.setTextSize(textSize_b);

        tvMenuOne.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvMenuTwo.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvMenuThree.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvOthers.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
    }

    private void setMenuThree() {
        Object tag = ivMenuThree.getTag();
        if (tag instanceof Integer) {
            int tagValue = (Integer) tag;
            if (tagValue == R.drawable.dashboard_1) {
                ivMenuThree.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_2));
            } else if (tagValue == R.drawable.leaderboard_1) {
                ivMenuThree.setImageDrawable(getResources().getDrawable(R.drawable.leaderboard_2));
            } else if (tagValue == R.drawable.jp_1) {
                ivMenuThree.setImageDrawable(getResources().getDrawable(R.drawable.jp_2));
                ivMenuThree.setBackground(getResources().getDrawable(R.drawable.circle_white));
            } else if (tagValue == R.drawable.menu_1) {
                ivMenuThree.setImageDrawable(getResources().getDrawable(R.drawable.menu_1));
            }
        }

        if (menuIcons != null && menuIcons.length > 0) {
            ivMenuOne.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_1));
            ivMenuTwo.setImageDrawable(getResources().getDrawable(menuIcons[1]));
            ivOthers.setImageDrawable(getResources().getDrawable(menuIcons[3]));
        }

        ivMenuOne.setBackground(null);
        ivMenuTwo.setBackground(null);
        ivOthers.setBackground(null);

        v1.setVisibility(View.INVISIBLE);
        v2.setVisibility(View.INVISIBLE);
        v4.setVisibility(View.INVISIBLE);
        v3.setVisibility(View.VISIBLE);

        tvMenuOne.setTextSize(textSize_s);
        tvMenuTwo.setTextSize(textSize_s);
        tvOthers.setTextSize(textSize_s);
        tvMenuThree.setTextSize(textSize_b);

        tvMenuOne.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvMenuTwo.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvOthers.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvMenuThree.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
    }

    private void setMenuTwo() {
        Object tag = ivMenuTwo.getTag();
        if (tag instanceof Integer) {
            int tagValue = (Integer) tag;
            if (tagValue == R.drawable.dashboard_1) {
                ivMenuTwo.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_2));
            } else if (tagValue == R.drawable.leaderboard_1) {
                ivMenuTwo.setImageDrawable(getResources().getDrawable(R.drawable.leaderboard_2));
                ivMenuTwo.setBackground(getResources().getDrawable(R.drawable.circle_white));
            } else if (tagValue == R.drawable.jp_1) {
                ivMenuTwo.setImageDrawable(getResources().getDrawable(R.drawable.jp_2));
            } else if (tagValue == R.drawable.menu_1) {
                ivMenuTwo.setImageDrawable(getResources().getDrawable(R.drawable.menu_1));
            }
        }

        if (menuIcons != null && menuIcons.length > 0) {
            ivMenuOne.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_1));
            ivMenuThree.setImageDrawable(getResources().getDrawable(menuIcons[2]));
            ivOthers.setImageDrawable(getResources().getDrawable(menuIcons[3]));
        }

        ivMenuOne.setBackground(null);
        ivMenuThree.setBackground(null);
        ivOthers.setBackground(null);

        v1.setVisibility(View.INVISIBLE);
        v3.setVisibility(View.INVISIBLE);
        v4.setVisibility(View.INVISIBLE);
        v2.setVisibility(View.VISIBLE);

        tvMenuOne.setTextSize(textSize_s);
        tvMenuThree.setTextSize(textSize_s);
        tvOthers.setTextSize(textSize_s);
        tvMenuTwo.setTextSize(textSize_b);

        tvMenuOne.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvMenuThree.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvOthers.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvMenuTwo.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
    }

    private void setMenuOne() {
        Object tag = ivMenuOne.getTag();

        if (tag instanceof Integer) {
            int tagValue = (Integer) tag;

            if (tagValue == R.drawable.dashboard_1) {
                ivMenuOne.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_2));
            } else if (tagValue == R.drawable.leaderboard_1) {
                ivMenuOne.setImageDrawable(getResources().getDrawable(R.drawable.leaderboard_2));
            } else if (tagValue == R.drawable.jp_1) {
                ivMenuOne.setImageDrawable(getResources().getDrawable(R.drawable.jp_2));
            } else if (tagValue == R.drawable.menu_1) {
                ivMenuOne.setImageDrawable(getResources().getDrawable(R.drawable.menu_1));
            }
        }

        if (menuIcons != null && menuIcons.length > 0) {
            ivMenuTwo.setImageDrawable(getResources().getDrawable(menuIcons[1]));
            ivMenuThree.setImageDrawable(getResources().getDrawable(menuIcons[2]));
            ivOthers.setImageDrawable(getResources().getDrawable(menuIcons[3]));
        }

        // Show selected option
        tvMenuOne.setVisibility(View.VISIBLE);
        v1.setVisibility(View.VISIBLE);
        tvMenuOne.setTextSize(textSize_b);
        tvMenuOne.setTypeface(AppConstants.SanFranciscoDisplay_Bold);

        // Hide remaining lines and update other text views
        v2.setVisibility(View.INVISIBLE);
        v3.setVisibility(View.INVISIBLE);
        v4.setVisibility(View.INVISIBLE);

        tvMenuTwo.setTextSize(textSize_s);
        tvMenuThree.setTextSize(textSize_s);
        tvOthers.setTextSize(textSize_s);

        tvMenuTwo.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvMenuThree.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
        tvOthers.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
    }


    private void callMenu(int i) {
        switch (i) {
            case 1:
                if (count == 0 || selectedBar != 1) {
                    count++;
                    selectedBar = 1;
                    callFragments();
                }
                break;
            case 2:
                if (selectedBar != 2) {
                    selectedBar = 2;
                    callFragments();
                }
                break;
            case 3:
                if (selectedBar != 3) {
                    selectedBar = 3;
                    callFragments();
                }
                break;
            case 4:
                if (/*selectedBar != 4*/true) {
                    selectedBar = 4;
                    callFragments();
                }
                break;
            default:
                break;
        }
    }

    private void callFragments() {

        switch (selectedBar) {
            case 1:
//                Intent intent = new Intent(BaseActivity.this, VehicleListPresellerNew.class);
                Intent intent = new Intent(BaseActivity.this, MainActivity.class);
//                Intent intent = new Intent(BaseActivity.this, VehicleListPresellerNew2.class);//by kushwant 1st for graphs

//                intent.putExtra("isFirstTimeLogin", isFirstTimeLogin);
                startActivity(intent);
                finish();
                break;
            case 2:
                Intent intent1 = new Intent(BaseActivity.this, LeaderBoard.class);
                startActivity(intent1);
                finish();
                break;
            case 3:
                MenuDO menuDO = new MenuDO();
                menuDO.menuName = getString(R.string.Journey_Plan);
                menuDO.menuImage = R.drawable.journey_plan_icon;
                menuDO.objMenu = MenuClass.MENUS.MENU_JOURNEY_PLAN;
//                navigateToActivity(menuDO);
//                finish();
                break;
            case 4:
                showMenuPopup();
                break;
        }
    }

    public void showMenuPopup() {  // final Object object
        try {
            if (customDialog != null && customDialog.isShowing())
                customDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }

        View view = inflater.inflate(R.layout.menu_dilog_icons, null);
        customDialog = new CustomDialog(this, view, preference
                .getIntFromPreference(Preference.DEVICE_DISPLAY_WIDTH, 250) - 150, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        customDialog.setCancelable(true);
        setTypeFaceRobotoOriginalSemibold((ViewGroup) view);


        RecyclerView rvmemudilog = view.findViewById(R.id.rv_menu);
        tvEndtrip = view.findViewById(R.id.tvEndtrip);
        tvEndtrip.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
        tvEndtrip.setText(getString(R.string.Logout));
        //            @Override
        tvEndtrip.setOnClickListener(view1 -> clickLogOut());

//        if (menuAdapter == null) {
//        menuAdapter = new MenuAdapter(new MenuClass(BaseActivity.this).loadMenu(userType, false));
        rvmemudilog.setAdapter(new MenuAdapter(new MenuClass(BaseActivity.this).loadMenu(userType, false)));
//        } else {
//            menuAdapter.refreshDashBoardOptionsCustomAdapter(new MenuClass(BaseActivity.this).loadMenu(userType, isCheckin()));
//        }


        int numberoficons = 3;

        GridLayoutManager layoutManager = new GridLayoutManager(this, numberoficons);
        rvmemudilog.setLayoutManager(layoutManager);
//        customDialog.create();
        Window window = customDialog.getWindow();

        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.BOTTOM;
            params.y = getResources().getDimensionPixelOffset(R.dimen.size100);
            window.setAttributes(params);
        }
//        customDialog.show();
        try {
            if (customDialog != null && !customDialog.isShowing())
                customDialog.showCustomDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSubMenuPopUp(Vector<MenuDO> vecMenuDo) {
        if (subMenuPopUP != null && subMenuPopUP.isShowing()) {
            subMenuPopUP.dismiss();
        }

        View view = inflater.inflate(R.layout.menu_dilog_icons, null);
//        subMenuPopUP = new CustomDialog(this, view, preference.getIntFromPreference(Preference.DEVICE_DISPLAY_WIDTH, 250) - 300, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        subMenuPopUP = new CustomDialog(this, view, preference.getIntFromPreference(Preference.DEVICE_DISPLAY_WIDTH, 250) - 300, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        subMenuPopUP.setCancelable(true);


        RecyclerView rvmemudilog = view.findViewById(R.id.rv_menu);
        tvEndtrip = view.findViewById(R.id.tvEndtrip);

        tvEndtrip.setVisibility(View.GONE);

        tvEndtrip.setOnClickListener(new View.OnClickListener() {
            //            @Override
            public void onClick(View view) {

            }
        });

//        if (menuAdapter == null) {
        subMenuAdapter = new MenuAdapter(vecMenuDo);
        rvmemudilog.setAdapter(subMenuAdapter);
//        } else {
//            menuAdapter.refreshDashBoardOptionsCustomAdapter(new MenuClass(BaseActivity.this).loadMenu(userType, isCheckin()));
//        }


        int numberoficons = 3;

        GridLayoutManager layoutManager = new GridLayoutManager(this, numberoficons);
        rvmemudilog.setLayoutManager(layoutManager);
//        customDialog.create();
        Window window = subMenuPopUP.getWindow();

        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.CENTER_VERTICAL;
//            params.y = getResources().getDimensionPixelOffset(preference.getIntFromPreference(Preference.DEVICE_DISPLAY_HEIGHT,800)/2);
            window.setAttributes(params);
        }
        subMenuPopUP.show();
        try {
            if (subMenuPopUP != null && !subMenuPopUP.isShowing())
                subMenuPopUP.showCustomDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMenuDO1(String field) {
        String asd = "";
        switch (field) {
            case "Dashboard":
                asd = getString(R.string.dashboard);
                break;
            case "Leaderboard":
                asd = getString(R.string.leaderboard1);
                break;
            case "JP":
                asd = getString(R.string.jp);
                break;
            case "Menu":
                asd = getString(R.string.others_new);
                break;
            default:
                asd = "";
                break;
        }
        return asd;
    }

    public Context getBaseActivityContext() {
        return this;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        hideCustomKeyBoard();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initializeKeyboard() {
        customKeyBoardpopup = new PopupWindow(BaseActivity.this);
        customKeyBoard = inflater.inflate(R.layout.popup_keyboard, null);
        customKeyBoardpopup.setContentView(customKeyBoard);
        customKeyBoardpopup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        customKeyBoardpopup.setOutsideTouchable(true);
        customKeyBoardpopup.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        customKeyBoardpopup.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        customKeyBoardpopup.setOnDismissListener(() -> llBody.requestFocus());

        tvNext = customKeyBoard.findViewById(R.id.next);
        tvNine = customKeyBoard.findViewById(R.id.keyNine);
        tvEight = customKeyBoard.findViewById(R.id.keyEight);
        tvSeven = customKeyBoard.findViewById(R.id.keySeven);
        tvSix = customKeyBoard.findViewById(R.id.keySix);
        tvFive = customKeyBoard.findViewById(R.id.keyFive);
        tvFour = customKeyBoard.findViewById(R.id.keyFour);
        tvThree = customKeyBoard.findViewById(R.id.keyThree);
        tvTwo = customKeyBoard.findViewById(R.id.keyTwo);
        tvOne = customKeyBoard.findViewById(R.id.keyOne);
        tvZero = customKeyBoard.findViewById(R.id.keyZero);
        tvDone = customKeyBoard.findViewById(R.id.done);
        tvDot = customKeyBoard.findViewById(R.id.keyDot);
        tvClear = customKeyBoard.findViewById(R.id.keyClear);
        keyBack = customKeyBoard.findViewById(R.id.keyBack);

        // Set OnClickListener for all relevant views
        tvDone.setOnClickListener(v -> onClick(v));
        tvNine.setOnClickListener(v -> onClick(v));
        tvEight.setOnClickListener(v -> onClick(v));
        tvSeven.setOnClickListener(v -> onClick(v));
        tvSix.setOnClickListener(v -> onClick(v));
        tvFive.setOnClickListener(v -> onClick(v));
        tvFour.setOnClickListener(v -> onClick(v));
        tvThree.setOnClickListener(v -> onClick(v));
        tvTwo.setOnClickListener(v -> onClick(v));
        tvOne.setOnClickListener(v -> onClick(v));
        tvZero.setOnClickListener(v -> onClick(v));
        tvDot.setOnClickListener(v -> onClick(v));

        tvClear.setOnClickListener(v -> {
            if (globalView != null && globalView instanceof EditText) {
                ((EditText) globalView).setText("");
                globalView.requestFocus();
            }
        });

        keyBack.setOnClickListener(v -> {
            View etFocus = globalView;
            if (etFocus instanceof EditText) {
                EditText focusCurrent = (EditText) etFocus;
                focusCurrent.clearFocus();
                focusCurrent.requestFocus();
                focusCurrent.setCursorVisible(true);
                int start = focusCurrent.getSelectionStart();
                Editable editable = focusCurrent.getText();
                if (editable != null && start > 0)
                    editable.delete(start - 1, start);
            }
        });

        tvNext.setOnClickListener(v -> {
            try {
                boolean noFocusableView = false;
                View etFocus = null;
                etFocus = globalView;
                View tmpView = null;

                while ((tmpView = etFocus.focusSearch(View.FOCUS_DOWN)) != null) {
                    if (tmpView instanceof EditText) {
                        etFocus = tmpView;
                        break;
                    }
                }

                if (tmpView == null)
                    noFocusableView = true;

                ViewGroup parent = (ViewGroup) etFocus.getParent();
                while (!parent.isScrollContainer()) {
                    if (parent.getId() == R.id.llBody) {
                        break;
                    }
                    parent = (ViewGroup) parent.getParent();
                }

                if (parent.isScrollContainer()) {
                    int[] location = new int[2];
                    etFocus.getLocationOnScreen(location);

                    int[] parentLocation = new int[2];
                    parent.getLocationOnScreen(parentLocation);

                    int delta = location[1] - parentLocation[1];
                    if (parent instanceof ExpandableListView || parent instanceof ListView) {
                        ((ListView) parent).smoothScrollBy(delta, 450);
                        parent.invalidate();
                    } else if (parent instanceof ScrollView) {
                        ((ScrollView) parent).smoothScrollBy(delta, 450);
                        parent.invalidate();
                    } else {
                        Log.i("Class Info", parent.getClass().getName());
                    }
                }

                globalView = etFocus;

                if (etFocus != null && etFocus instanceof EditText && !noFocusableView) {
                    EditText nextEditText = (EditText) etFocus;

                    if (nextEditText != null) {
                        Log.e("nextEditText", "nextEditText");
                        nextEditText.clearFocus();
                        nextEditText.requestFocus();
                        nextEditText.setCursorVisible(true);
                        nextEditText.setSelection(nextEditText.getText().length());
                        if (customKeyBoard != null && customKeyBoard.isShown())
                            customKeyBoardpopup.dismiss();
                    }
                } else if (customKeyBoard != null) {
                    Log.e("nextEditText customKeyBoard", "nextEditText customKeyBoard");
                    customKeyBoardpopup.dismiss();
                }

            } catch (Exception e) {
                e.printStackTrace();
                if (customKeyBoard != null) {
                    Log.e("nextEditText customKeyBoard Exception", "nextEditText customKeyBoard Exception");
                    customKeyBoardpopup.dismiss();
                }
            }
        });
    }

    public void hideCustomKeyBoard() {
        if (customKeyBoardpopup != null && customKeyBoardpopup.isShowing())
            customKeyBoardpopup.dismiss();
        if (globalView != null) {
            globalView = null;
        }

    }

    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.done) {
            Log.e("tvDone", "tvDone");
            hideCustomKeyBoard();
        } else if (id == R.id.keyNine) {
            // Handle keyNine click
        } else if (id == R.id.keyEight) {
            // Handle keyEight click
        }
        // Add more else-if as needed
    }

    public void onButtonYesClick(String from, String params) {
//        String serverURL = preference.getStringFromPreference(Preference.SETTINGS_URL, "");
//        if (from.equalsIgnoreCase("server_settings") && !serverURL.equalsIgnoreCase(params)) {
//            preference.saveStringInPreference(Preference.SETTINGS_URL, params);
//            preference.saveStringInPreference(Preference.TEMP_EMP_NO, "");
//            preference.commitPreference();
        showToast("Successfully updated settings!");
//        }

        if (from.equalsIgnoreCase("Settings")) {
            Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    public void onButtonYesClick(String from) {
        if (from.equalsIgnoreCase("logout")) {
            Intent intentBrObj = new Intent();
            intentBrObj.setAction(AppConstants.ACTION_LOGOUT);
            sendBroadcast(intentBrObj);
            Intent intent = new Intent(BaseActivity.this, LoginAcivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_right1, R.anim.slide_left1);
        } else if (from.equalsIgnoreCase("DepotInOut")) {
            Intent intent = new Intent(BaseActivity.this, StartDayPreRequisiteActivityNew.class);
            startActivity(intent);
        } else if (from.equalsIgnoreCase("Settings")) {
            Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    public void onButtonYesClick(String from, Object params) {
    }

    public void onButtonNoClick(String from, Object params) {
    }

    public void onButtonNoClick(String from) {
    }

    public void showToast(String message) {
        if (toast != null) toast.cancel();

        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    public void hideKeyBoard(View v) {
        if (v != null && getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public void TopBarMenuClick() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    //font setting
    public void setTypeFaceRobotoOriginalBold(ViewGroup group) {
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView || v instanceof Button || v instanceof EditText)
                ((TextView) v).setTypeface(AppConstants.SanFranciscoDisplay_Bold);
            else if (v instanceof ViewGroup)
                setTypeFaceRobotoOriginalBold((ViewGroup) v);
        }
    }

    public void setTypeFaceRobotoOriginalSemibold(ViewGroup group) {
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView || v instanceof Button || v instanceof EditText)
                ((TextView) v).setTypeface(AppConstants.SanFranciscoDisplay_Semibold);
            else if (v instanceof ViewGroup)
                setTypeFaceRobotoOriginalSemibold((ViewGroup) v);
        }
    }

    public void setTypeFaceRobotoOriginalRegular(ViewGroup group) {
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView || v instanceof Button || v instanceof EditText)
                ((TextView) v).setTypeface(AppConstants.SanFranciscoDisplay_Regular);
            else if (v instanceof ViewGroup)
                setTypeFaceRobotoOriginalRegular((ViewGroup) v);
        }
    }

    public void setTypeFaceRobotoOriginalMedium(ViewGroup group) {
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView || v instanceof Button || v instanceof EditText)
                ((TextView) v).setTypeface(AppConstants.SanFranciscoDisplay_Medium);
            else if (v instanceof ViewGroup)
                setTypeFaceRobotoOriginalMedium((ViewGroup) v);
        }
    }

    public void setTypeFaceRobotoOriginalLight(ViewGroup group) {
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView || v instanceof Button || v instanceof EditText)
                ((TextView) v).setTypeface(AppConstants.montserrat_light);
            else if (v instanceof ViewGroup)
                setTypeFaceRobotoOriginalLight((ViewGroup) v);
        }
    }

    public void clickLogOut() {
        showCustomDialog(BaseActivity.this, getResources().getString(R.string.warning), getResources().getString(R.string.do_you_want_to_logout), getResources().getString(R.string.Yes), getResources().getString(R.string.No), "logout");
    }

    public void showCustomDialog(Context context, String strTitle, String strMessage, String firstBtnName, String secondBtnName, String from) {
        runOnUiThread(new RunshowCustomDialogs(context, strTitle, strMessage, firstBtnName, secondBtnName, from, true));
    }

    public void showCustomDialog(Context context, String strTitle, String strMessage, String firstBtnName, String secondBtnName, String from, boolean isCancelable) {
        runOnUiThread(new RunshowCustomDialogs(context, strTitle, strMessage, firstBtnName, secondBtnName, from, isCancelable));
    }

    public void hideLoader() {
        runOnUiThread(() -> {
            try {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressLint("HardwareIds")
    public String getUniqueIDOriginal() {
        String myAndroidDeviceId = "";
        TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (!TextUtils.isEmpty(mTelephony.getDeviceId())) {
            myAndroidDeviceId = mTelephony.getDeviceId();
        } else {
            myAndroidDeviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);//777817287dc5bc9
        }
        if (!TextUtils.isEmpty(myAndroidDeviceId))
            myAndroidDeviceId = myAndroidDeviceId.toUpperCase();
        return myAndroidDeviceId;
    }

    @SuppressLint("HardwareIds")
    public String getUniqueID() {
        String myAndroidDeviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        if (!TextUtils.isEmpty(myAndroidDeviceId)) {
            myAndroidDeviceId = myAndroidDeviceId.toUpperCase();
        }
        return myAndroidDeviceId;
    }

    public HttpImageManager getHttpImageManager() {
        return ((MyApplicationNew) BaseActivity.this.getApplication()).getHttpImageManager();
    }

    public File getOutputMediaFile() {
        File fileDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        //        File fileDir = getExternalFilesDir(Environment.getExternalStorageDirectory()+"/HaierEmpower/"+"Images");
        File mediaStorageDir = new File(fileDir, "Kush1");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                showToast("Failed to create directory to take picture.");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.US).format(new Date());
        return new File(mediaStorageDir + File.separator + "HAIER_SLF_" + timeStamp + ".jpg");
    }

    public void showGoogleUpdateServiceAlert() {
        updateGooglePlayServiceDialog = null;

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(BaseActivity.this);
        if (status != ConnectionResult.SUCCESS) {
            updateGooglePlayServiceDialog = GooglePlayServicesUtil.getErrorDialog(status, this, 1);
            if (!isFinishing()) updateGooglePlayServiceDialog.show();
        } else
            Toast.makeText(BaseActivity.this, "You have updated googlePlayservice already", Toast.LENGTH_SHORT).show();
    }

    public void cancelGoogleUpdateServiceAlert() {
        if (updateGooglePlayServiceDialog != null && updateGooglePlayServiceDialog.isShowing()) {
            updateGooglePlayServiceDialog.dismiss();
        }
    }

    public void showSettingsAlert() {
        showCustomDialog(BaseActivity.this, getString(R.string.GPS_Settings), getString(R.string.GPS_not_enabled), "Settings", null, "Settings");
    }

    public boolean isDeviceSupportCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public boolean isGPSEnable(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            boolean gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!gps_enabled && !network_enabled)
                return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    class RunShowLoaderCustom implements Runnable {
        private final String strMsg;
        private String title;

        public RunShowLoaderCustom(String strMsg) {
            this.strMsg = strMsg;
        }

        public RunShowLoaderCustom(String strMsg, String title) {
            this.strMsg = strMsg;
            this.title = title;
        }

        @Override
        public void run() {
            try {
                if (dialog == null)
                    dialog = new Dialog(BaseActivity.this, R.style.Theme_Dialog_Translucent);

                dialog.setContentView(R.layout.loading);
//                if (!isCancelableLoader) dialog.setCancelable(false); else dialog.setCancelable(true);
                dialog.setCancelable(isCancelableLoader);

                try {
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                LinearLayout llPopup = dialog.findViewById(R.id.llPopup);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llPopup.getLayoutParams();
                params.gravity = Gravity.CENTER; // Center both horizontally and vertically
                llPopup.setLayoutParams(params);

                ivOutsideImage = dialog.findViewById(R.id.ivOutsideImage);
                ImageView01 = dialog.findViewById(R.id.ImageView01);

                TextView tvLoading = dialog.findViewById(R.id.tvLoading);
                if (!strMsg.equalsIgnoreCase("")) tvLoading.setText(strMsg);
                else tvLoading.setVisibility(View.GONE);

                rotateXaxis = AnimationUtils.loadAnimation(BaseActivity.this, R.anim.rotate_x_axis);
                rotateXaxis.setInterpolator(new LinearInterpolator());

                ivOutsideImage.setAnimation(rotateXaxis);
                applyRotation();
            } catch (Exception e) {
            }
        }
    }

    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyHolder> {
        Context context;
        int row_index = 0;
        ArrayList<String> menu;
        ArrayList<Integer> menu_icon;
        private Vector<MenuDO> vecMenuDOs;

        public MenuAdapter(Vector<MenuDO> vecMenuDOs) {
            this.vecMenuDOs = vecMenuDOs;
        }

        public void refreshDashBoardOptionsCustomAdapter(Vector<MenuDO> vecMenuDOs) {
            this.vecMenuDOs = vecMenuDOs;
            notifyDataSetChanged();
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_list_item, parent, false);

            return new MyHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, @SuppressLint("RecyclerView") final int position) {
            try {
                final MenuDO menuDO = vecMenuDOs.get(position);

                holder.tv_title.setText(menuDO.menuName);
                holder.tv_title.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);
                Glide.with(holder.itemView.getContext()).load(menuDO.menuImage).into(holder.iv_icon);

                holder.ivArrowIcon.setVisibility(menuDO.vecMenuDOs.isEmpty() ? View.INVISIBLE : View.VISIBLE);
                holder.itemView.setTag(menuDO.menuName);
                holder.itemView.setOnClickListener(v -> {
//                            TopBarMenuClick();
//                            groupPos = -1;

                    if (menuDO.vecMenuDOs.size() == 0) {
                        new Handler().postDelayed(() -> {
                            if (!v.getTag().toString().equalsIgnoreCase("Checkout")) {
                                Intent intent = new Intent();
                                intent.setAction(AppConstants.ACTION_GOTO_HOME);
                                sendBroadcast(intent);
                            }
//                                    navigateToActivity(menuDO);
                            showToast("Under Development...!!!");
                        }, 400);
                    } else showSubMenuPopUp(menuDO.vecMenuDOs);
                });
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            } catch (Exception e) {
                Log.i("menuAdapter", "onBindViewHolder: menu Adapter Exception :" + e);
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            if (vecMenuDOs != null && vecMenuDOs.size() > 0) {
                return vecMenuDOs.size();
            } else return 0;
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView tv_title;
            ImageView iv_icon;
            ImageView ivArrowIcon;

            public MyHolder(View itemView) {
                super(itemView);
                tv_title = itemView.findViewById(R.id.tv_title);
                iv_icon = itemView.findViewById(R.id.iv_icon);
                ivArrowIcon = itemView.findViewById(R.id.ivArrowIcon);


            }
        }
    }

    public class DashBoardOptionsCustomAdapter extends BaseExpandableListAdapter {
        private Vector<MenuDO> vecMenuDOs;

        protected DashBoardOptionsCustomAdapter(Vector<MenuDO> vecMenuDOs) {
            this.vecMenuDOs = vecMenuDOs;
        }

        public void refreshDashBoardOptionsCustomAdapter(Vector<MenuDO> vecMenuDOs) {
            this.vecMenuDOs = vecMenuDOs;
            notifyDataSetInvalidated();
        }

        @Override
        public int getGroupCount() {
            return vecMenuDOs.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return vecMenuDOs.get(groupPosition).vecMenuDOs.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return vecMenuDOs.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return vecMenuDOs.get(groupPosition).vecMenuDOs.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.dashboard_options_cell, null);
            ImageView ivOptionIcon = convertView.findViewById(R.id.ivOptionIcon);
            ImageView ivArrowIcon = convertView.findViewById(R.id.ivArrowIcon);
            TextView tvOptionName = convertView.findViewById(R.id.tvOptionName);
            LinearLayout llMenu = convertView.findViewById(R.id.llMenu);
            LinearLayout rlCalender = convertView.findViewById(R.id.rlCalender);
            ImageView ivFooter = convertView.findViewById(R.id.ivFooter);

            ivOptionIcon.setImageResource(vecMenuDOs.get(groupPosition).menuImage);
            tvOptionName.setText(vecMenuDOs.get(groupPosition).menuName);
//            tvOptionName.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
            tvOptionName.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);

            if (vecMenuDOs.get(groupPosition).vecMenuDOs.size() > 0)
                ivArrowIcon.setVisibility(View.VISIBLE);
            else
                ivArrowIcon.setVisibility(View.GONE);
            if (groupPos == groupPosition) {
                if (isExpanded) {
                    ExpandableListView mExpandableListView = (ExpandableListView) parent;
                    mExpandableListView.expandGroup(groupPosition);
                } else {
                    ExpandableListView mExpandableListView = (ExpandableListView) parent;
                    mExpandableListView.collapseGroup(groupPosition);
                }
            }


            if (vecMenuDOs.get(groupPosition).menuName.equalsIgnoreCase(getString(R.string.Footer))) {
                rlCalender.setVisibility(View.GONE);
                ivFooter.setVisibility(View.VISIBLE);
            } else {
                rlCalender.setVisibility(View.VISIBLE);
                ivFooter.setVisibility(View.GONE);
            }

            if (vecMenuDOs.get(groupPosition).vecMenuDOs.size() == 0) {
                convertView.setTag(vecMenuDOs.get(groupPosition).menuName);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        TopBarMenuClick();
                        groupPos = -1;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (!v.getTag().toString().equalsIgnoreCase("Checkout")) {
                                    Intent intent = new Intent();
                                    intent.setAction(AppConstants.ACTION_GOTO_HOME);
                                    sendBroadcast(intent);
                                }

//                                moveToNextAcivityGT(v.getTag().toString());
//                                navigateToActivity(vecMenuDOs.get(groupPosition));
                            }
                        }, 400);
                    }
                });
            }


            if (isExpanded) {
                ivArrowIcon.setBackgroundResource(R.drawable.menu_arrow_down);
//                ivArrowIcon.setBackgroundResource(R.drawable.arrow_dwn);
//                ivArrowIcon.setBackgroundResource(R.drawable.arrow_down_new_1);
            } else
                ivArrowIcon.setBackgroundResource(R.drawable.menu_arrow_right);
//                ivArrowIcon.setBackgroundResource(R.drawable.arrow_one);

            convertView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (55 * px)));

            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final Vector<MenuDO> vecDos = vecMenuDOs.get(groupPosition).vecMenuDOs;

            convertView = inflater.inflate(R.layout.dashboard_options_cell, null);
            //			convertView.setBackgroundResource(R.color.dark_menu);
            ImageView ivOptionIcon = convertView.findViewById(R.id.ivOptionIcon);
            TextView tvOptionName = convertView.findViewById(R.id.tvOptionName);
            LinearLayout rlCalender = convertView.findViewById(R.id.rlCalender);
            ImageView ivFooter = convertView.findViewById(R.id.ivFooter);
            ImageView ivArrowIcon = convertView.findViewById(R.id.ivArrowIcon);

            ivOptionIcon.setImageResource(vecDos.get(childPosition).menuImage);
            ivArrowIcon.setVisibility(View.GONE);
            tvOptionName.setText(vecDos.get(childPosition).menuName);
//            tvOptionName.setTypeface(AppConstants.SanFranciscoDisplay_Medium);
            tvOptionName.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);

            //rlCalender.setBackgroundColor(getResources().getColor(R.color.gray_light));

            convertView.setTag(vecDos.get(childPosition).menuName);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    groupPos = groupPosition;

                    TopBarMenuClick();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.setAction(AppConstants.ACTION_GOTO_HOME);
                            sendBroadcast(intent);

//                            moveToNextAcivityGT(v.getTag().toString());
//                            navigateToActivity(vecDos.get(childPosition));
                        }
                    }, 400);
                }
            });

            convertView.setPadding(20, 5, 5, 5);
            convertView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (55 * px)));

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    // For showing Dialog message.
    class RunshowCustomDialogs implements Runnable {
        private final String strTitle;// Title of the dialog
        private final String strMessage;// Message to be shown in dialog
        private final String firstBtnName;
        private final String secondBtnName;
        private final String from;
        private String params;
        private Object paramateres;
        private boolean isCancelable = false;
        private View.OnClickListener posClickListener;
        private View.OnClickListener negClickListener;

        public RunshowCustomDialogs(Context context, String strTitle, String strMessage, String firstBtnName, String secondBtnName, String from, boolean isCancelable) {
            this.strTitle = strTitle;
            this.strMessage = strMessage;
            this.firstBtnName = firstBtnName;
            this.secondBtnName = secondBtnName;
            this.isCancelable = isCancelable;
            if (from != null)
                this.from = from;
            else
                this.from = "";
        }

        public RunshowCustomDialogs(Context context, String strTitle, String strMessage, String firstBtnName, String secondBtnName, String from, boolean isCancelable, String params) {
            this.strTitle = strTitle;
            this.strMessage = strMessage;
            this.firstBtnName = firstBtnName;
            this.secondBtnName = secondBtnName;
            this.isCancelable = isCancelable;
            if (from != null)
                this.from = from;
            else
                this.from = "";

            if (params != null)
                this.params = params;
            else
                this.params = "";
        }

        public RunshowCustomDialogs(Context context, String strTitle, String strMessage, String firstBtnName, String secondBtnName, String from, boolean isCancelable, Object params) {
            this.strTitle = strTitle;
            this.strMessage = strMessage;
            this.firstBtnName = firstBtnName;
            this.secondBtnName = secondBtnName;
            this.isCancelable = isCancelable;
            if (from != null)
                this.from = from;
            else
                this.from = "";

            if (params != null)
                this.paramateres = params;
            else
                this.paramateres = "";
        }


        @Override
        public void run() {
            if (customDialog != null && customDialog.isShowing())
                customDialog.dismiss();

            View view;
            if (from.equalsIgnoreCase("Storecheck")) {
                view = inflater.inflate(R.layout.popup_storecheck_confirmation, null);
                customDialog = new CustomDialog(BaseActivity.this, view, preference.getIntFromPreference(Preference.DEVICE_DISPLAY_WIDTH, 250) - 150, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//				customDialog.setCancelable(isCancelable);
                customDialog.setCancelable(false);

                setTypeFaceRobotoOriginalMedium((ViewGroup) view);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (customDialog != null && customDialog.isShowing())
                            customDialog.dismiss();
                        onButtonYesClick(from);
                    }
                }, 3000);
                try {
                    if (!customDialog.isShowing())
                        customDialog.showCustomDialog();
                } catch (Exception e) {
                }
            } else if (from.equalsIgnoreCase("Without_MSL_Items")) {
                view = inflater.inflate(R.layout.custom_common_popup_msl, null);

                customDialog = new CustomDialog(BaseActivity.this, view, preference
                        .getIntFromPreference(Preference.DEVICE_DISPLAY_WIDTH, 320) - 20,
                        ViewGroup.LayoutParams.WRAP_CONTENT, true);
                customDialog.setCancelable(isCancelable);
                TextView tvTitle = view.findViewById(R.id.tvTitlePopup);
                TextView tvMessage = view.findViewById(R.id.tvMessagePopup);
                Button btnYes = view.findViewById(R.id.btnYesPopup);
                Button btnNo = view.findViewById(R.id.btnNoPopup);
                tvTitle.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);
                tvMessage.setTypeface(AppConstants.SanFranciscoDisplay_Regular);
                btnYes.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);
                btnNo.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);

                tvTitle.setText(strTitle);
                tvMessage.setText(strMessage);
                btnYes.setText(firstBtnName);

//                if (strTitle.toLowerCase().contains("success"))
                //tvTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.paymode_checked, 0, 0, 0);

                if (secondBtnName != null && !secondBtnName.equalsIgnoreCase(""))
                    btnNo.setText(secondBtnName);
                else {
                    btnNo.setVisibility(View.GONE);
                    btnYes.setBackground(getResources().getDrawable(R.drawable.roundcorner_new_app_color_one));
                }
                if (posClickListener == null)
                    btnYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customDialog.dismiss();

                            if (from != null && from.equalsIgnoreCase("cancelorder"))
                                onButtonYesClick(from, params);
                            else if (from != null && from.equalsIgnoreCase("StoreCheckObject"))
                                onButtonYesClick(from, paramateres);
                            else
                                onButtonYesClick(from);
                        }
                    });
                else
                    btnYes.setOnClickListener(posClickListener);

                if (negClickListener == null)
                    btnNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customDialog.dismiss();
                            onButtonNoClick(from);
                        }
                    });
                else
                    btnNo.setOnClickListener(negClickListener);
                try {
                    if (!customDialog.isShowing())
                        customDialog.showCustomDialog();
                } catch (Exception e) {
                }
            } else {
                view = inflater.inflate(R.layout.custom_common_popup, null);

                customDialog = new CustomDialog(BaseActivity.this, view, preference
                        .getIntFromPreference(Preference.DEVICE_DISPLAY_WIDTH, 320) - 20,
                        ViewGroup.LayoutParams.WRAP_CONTENT, true);
                customDialog.setCancelable(isCancelable);
                TextView tvTitle = view.findViewById(R.id.tvTitlePopup);
                TextView tvMessage = view.findViewById(R.id.tvMessagePopup);
                Button btnYes = view.findViewById(R.id.btnYesPopup);
                Button btnNo = view.findViewById(R.id.btnNoPopup);

                tvTitle.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);
                tvMessage.setTypeface(AppConstants.SanFranciscoDisplay_Regular);
                btnYes.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);
                btnNo.setTypeface(AppConstants.SanFranciscoDisplay_Semibold);

                tvTitle.setText(strTitle);
                tvMessage.setText(strMessage);
                btnYes.setText(firstBtnName);
                if (secondBtnName != null && !secondBtnName.equalsIgnoreCase("")) {
                    btnNo.setText(secondBtnName);
                } else {
                    btnNo.setVisibility(View.GONE);
                    btnYes.setBackground(getResources().getDrawable(R.drawable.roundcorner_new_app_color_one));

                }
                if (strTitle.toLowerCase().contains("success") || strTitle.toLowerCase().contains("Verified") || strTitle.toLowerCase().contains("Warning !") || strTitle.toLowerCase().contains("Warning"))
                    //   tvTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.paymode_checked, 0, 0, 0);

                    if (secondBtnName != null && !secondBtnName.equalsIgnoreCase("")) {
                        btnNo.setText(secondBtnName);
                    } else {
                        btnNo.setVisibility(View.GONE);
                        btnYes.setBackground(getResources().getDrawable(R.drawable.roundcorner_new_app_color_one));
                    }
                if (posClickListener == null)
                    btnYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customDialog.dismiss();

                            if (from != null && from.equalsIgnoreCase("cancelorder"))
                                onButtonYesClick(from, params);
                            else if (from != null && (from.equalsIgnoreCase("EXCESS_LOAD") || from.equalsIgnoreCase("StoreCheckObject") || from.equalsIgnoreCase("Excess_Stock")))
                                onButtonYesClick(from, paramateres);
                            else if (from != null && from.equalsIgnoreCase("RejectVarience"))
                                onButtonYesClick(from, paramateres);
                            else
                                onButtonYesClick(from);
                        }
                    });
                else
                    btnYes.setOnClickListener(posClickListener);

                if (negClickListener == null)
                    btnNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customDialog.dismiss();
                            if (from.equalsIgnoreCase("EXCESS_LOAD") || from.equalsIgnoreCase("advorderdone_checkout") || from.equalsIgnoreCase("Excess_Stock")) {
                                onButtonNoClick(from, paramateres);
                            } else {
                                onButtonNoClick(from);
                            }
                        }
                    });
                else
                    btnNo.setOnClickListener(negClickListener);
                try {
                    if (!customDialog.isShowing())
                        customDialog.showCustomDialog();
                } catch (Exception e) {
                }
            }
        }
    }

}
