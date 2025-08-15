package com.example.personalproject.mainPackage;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.personalproject.R;
import com.example.personalproject.mainPackage.common.AppConstants;
import com.example.personalproject.mainPackage.dataobject.LeaderBoardDO;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class LeaderBoard extends BaseActivity {
    TextView userCategory, userSubCategory;
    TextView tvBranchTab, tvRegionTab, tvCountryTab;
    CircleImageView ivSecondRankImage, ivFirstRankImage, ivThirdRankImage, ivUserImage;
    TextView tvSecondrankName, tvSecondrankId, tvSecondrankVisitedCount;
    TextView tvFirstrankName, tvFirstrankId, tvFirstrankVisitedCount;
    TextView tvThirdrankName, tvThirdrankId, tvThirdrankVisitedCount;
    TextView userRankid, tvUserName, tvUserId, userRank, Sample;
    RecyclerView rvUserList;
    ArrayList<String> categoryList = new ArrayList<>();
    ImageView leaderboard_alert;
    LinearLayout linearlayout;


    LinkedHashMap<String, LeaderBoardDO> hashMapWinner = new LinkedHashMap<>();
    int totCountFromLeaderBoard = 0;


    public void initialize() {
        linearlayout = (LinearLayout) getLayoutInflater().inflate(R.layout.leaderboard_main, null);
        llBody.addView(linearlayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

//        linearlayout= (LinearLayout) inflater.inflate(R.layout.leaderboard_main, llBody);
        initControls();
        setTypeFaceRobotoOriginalSemibold(linearlayout);

//        tvUserName.setText("" + preference.getStringFromPreference(Preference.USER_NAME, ""));
        tvUserName.setText("Kushwant");
        tvUserId.setText("50223689");
//        bindImageNew(profileImage, ivUserImage);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                hashMapWinner = new UserAgencyTargetsDA().getLeaderboardData(preference.getStringFromPreference(Preference.EMP_NO, ""), true);
                totCountFromLeaderBoard = new UserAgencyTargetsDA().getTotalLeaderboardDataMAX();
            }
        }).start();*/

        tvCountryTab.setOnClickListener(view -> {
            isCountrySelected = true;
            loadData();
            tvCountryTab.setBackground(getResources().getDrawable(R.drawable.rounded_textview_bg));
            tvCountryTab.setTextColor(getResources().getColor(R.color.new_app_color));
            tvRegionTab.setBackground(null);
            tvRegionTab.setTextColor(getResources().getColor(R.color.background_tab_pressed));
        });
        tvRegionTab.setOnClickListener(view -> {
            isCountrySelected = false;
            loadData();
            tvRegionTab.setBackground(getResources().getDrawable(R.drawable.rounded_textview_bg));
            tvRegionTab.setTextColor(getResources().getColor(R.color.new_app_color));
            tvCountryTab.setBackground(null);
            tvCountryTab.setTextColor(getResources().getColor(R.color.background_tab_pressed));
        });
        loadData();
    }

    boolean isCountrySelected = true;

    private void loadData() {
        if (hashMapWinner != null && hashMapWinner.size() > 0) {
            if (isCountrySelected) {
                if (hashMapWinner.get("Country_1") != null) {
                    LeaderBoardDO leaderBoardDO = hashMapWinner.get("Country_1");
                    bindImageNew(leaderBoardDO.UserimagePath, ivFirstRankImage);
//										bindImage(leaderBoardDO.UserimagePath,ivLeader1stW);
                    tvFirstrankName.setText(leaderBoardDO.UserName);
                    tvFirstrankVisitedCount.setText(leaderBoardDO.Points);
                }
                if (hashMapWinner.get("Country_2") != null) {
                    LeaderBoardDO leaderBoardDO = hashMapWinner.get("Country_2");
                    bindImageNew(leaderBoardDO.UserimagePath, ivSecondRankImage);
//										bindImage(leaderBoardDO.UserimagePath,ivLeader2ndW);
                    tvSecondrankName.setText(leaderBoardDO.UserName);
                    tvSecondrankVisitedCount.setText(leaderBoardDO.Points);
                }
                if (hashMapWinner.get("Country_3") != null) {
                    LeaderBoardDO leaderBoardDO = hashMapWinner.get("Country_3");
                    bindImageNew(leaderBoardDO.UserimagePath, ivThirdRankImage);
//										bindImage(leaderBoardDO.UserimagePath,ivLeader3rdW);
                    tvThirdrankName.setText(leaderBoardDO.UserName);
                    tvThirdrankVisitedCount.setText(leaderBoardDO.Points);
                }

                if (hashMapWinner.get("Country_USER") != null) {
                    LeaderBoardDO leaderBoardDO = hashMapWinner.get("Country_USER");
//										bindImage(leaderBoardDO.UserimagePath,ivLeader3rdAreaW);
                    userRank.setText(leaderBoardDO.Rank);
//                    tvCountrywise_w.setText(getResources().getString(R.string.country_wise)+"(" + leaderBoardDO.Area + ")");
                }
            } else {
                if (hashMapWinner.get("Region_1") != null) {
                    LeaderBoardDO leaderBoardDO = hashMapWinner.get("Region_1");
                    bindImageNew(leaderBoardDO.UserimagePath, ivFirstRankImage);
//										bindImage(leaderBoardDO.UserimagePath,ivLeader1stW);
                    tvFirstrankName.setText(leaderBoardDO.UserName);
                    tvFirstrankVisitedCount.setText(leaderBoardDO.Points);
                }
                if (hashMapWinner.get("Region_2") != null) {
                    LeaderBoardDO leaderBoardDO = hashMapWinner.get("Region_2");
                    bindImageNew(leaderBoardDO.UserimagePath, ivSecondRankImage);
//										bindImage(leaderBoardDO.UserimagePath,ivLeader2ndW);
                    tvSecondrankName.setText(leaderBoardDO.UserName);
                    tvSecondrankVisitedCount.setText(leaderBoardDO.Points);
                }
                if (hashMapWinner.get("Region_3") != null) {
                    LeaderBoardDO leaderBoardDO = hashMapWinner.get("Region_3");
                    bindImageNew(leaderBoardDO.UserimagePath, ivThirdRankImage);
//										bindImage(leaderBoardDO.UserimagePath,ivLeader3rdW);
                    tvThirdrankName.setText(leaderBoardDO.UserName);
                    tvThirdrankVisitedCount.setText(leaderBoardDO.Points);
                }

                if (hashMapWinner.get("Region_USER") != null) {
                    LeaderBoardDO leaderBoardDO = hashMapWinner.get("Region_USER");
//										bindImage(leaderBoardDO.UserimagePath,ivLeader3rdAreaW);
                    userRank.setText(leaderBoardDO.Rank);
//                    tvregionwise_W.setText(getString(R.string.region_wise)+"(" + leaderBoardDO.Area + ")");
                }
            }
        }
    }


    private void bindImageNew(String imagePath, ImageView iv_brandImage1) {

        String imageURL = "";
        if (!TextUtils.isEmpty(imagePath)) {
            if (imagePath.contains("../")) {
//                imageURL = imagePath.replace("../", ServiceURLs.IMAGE_GLOBAL_URL);
            } else {
//                imageURL = imagePath.replace("~/", ServiceURLs.IMAGE_GLOBAL_URL);
            }
        }
        imageURL = imageURL.replace(" ", "%20");
        if (imageURL.contains("\\"))
            imageURL = imageURL.replace("\\", "/");
//		final Uri uri = Uri.parse(imageURL);
//		if (uri != null) {
//			Bitmap bitmapnew = ((VehicleList.this)).getHttpImageManager()
//					.loadImage(new HttpImageManager.LoadRequest(uri, iv_brandImage1, imageURL));
//			if (bitmapnew != null) {
//				iv_brandImage1.setImageBitmap(bitmapnew);
//			} else {
//				iv_brandImage1.setImageResource(R.drawable.no_image);
//			}
//		}
//        setImageUrl(iv_brandImage1, imageURL);
    }


    private void initControls() {
        TextView your_rank = linearlayout.findViewById(R.id.your_rank);
        your_rank.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
        TextView tvTitle = linearlayout.findViewById(R.id.tvTitle);
        tvTitle.setTypeface(AppConstants.SanFranciscoDisplay_Bold);
        userCategory = linearlayout.findViewById(R.id.userCategory);
        userSubCategory = linearlayout.findViewById(R.id.userSubCategory);
        tvBranchTab = linearlayout.findViewById(R.id.tvBranchTab);
        tvRegionTab = linearlayout.findViewById(R.id.tvRegionTab);
        tvCountryTab = linearlayout.findViewById(R.id.tvCountryTab);

        ivSecondRankImage = linearlayout.findViewById(R.id.ivSecondRankImage);
        ivFirstRankImage = linearlayout.findViewById(R.id.ivFirstRankImage);
        ivThirdRankImage = linearlayout.findViewById(R.id.ivThirdRankImage);
        ivUserImage = linearlayout.findViewById(R.id.ivUserImage);


        tvSecondrankName = linearlayout.findViewById(R.id.tvSecondrankName);
        tvSecondrankId = linearlayout.findViewById(R.id.tvSecondrankId);
        tvSecondrankVisitedCount = linearlayout.findViewById(R.id.tvSecondrankVisitedCount);

        tvFirstrankName = linearlayout.findViewById(R.id.tvFirstrankName);
        tvFirstrankId = linearlayout.findViewById(R.id.tvFirstrankId);
        tvFirstrankVisitedCount = linearlayout.findViewById(R.id.tvFirstrankVisitedCount);

        tvThirdrankName = linearlayout.findViewById(R.id.tvThirdrankName);
        tvThirdrankId = linearlayout.findViewById(R.id.tvThirdrankId);
        tvThirdrankVisitedCount = linearlayout.findViewById(R.id.tvThirdrankVisitedCount);

        userRankid = linearlayout.findViewById(R.id.userRankid);
        tvUserName = linearlayout.findViewById(R.id.tvUserName);
        tvUserId = linearlayout.findViewById(R.id.tvUserId);
        userRank = linearlayout.findViewById(R.id.userRank);

        rvUserList = linearlayout.findViewById(R.id.rvUserList);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        clickLogOut();
    }
}
