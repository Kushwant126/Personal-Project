package com.example.personalproject.mainPackage;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.personalproject.CustomCommonDesign.AboveAndCurveCustomBarChart;
import com.example.personalproject.CustomCommonDesign.BarChartConfig;
import com.example.personalproject.CustomCommonDesign.DoubleBarChart;
import com.example.personalproject.CustomCommonDesign.DualBarCharts;
import com.example.personalproject.CustomCommonDesign.OneBehindAnotherBarchart;
import com.example.personalproject.CustomCommonDesign.SingleBarChart;
import com.example.personalproject.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MainActivity extends BaseActivity {
//    https://www.figma.com/design/kiwPyxZfIsLBFionrvu2KI/President-with-Alert-haier-HIMS?node-id=0-1&t=aGMfkA6rPcgF7LJN-0
    String[] xLabels = new String[]{"MTD","LM","YTD","LY","QW","PS","SK"};
    String[] xLabels1 = new String[]{"","MTD","LM","YTD","LY","QW","PS","SK"};
    ArrayList<String> xlabels123 = new ArrayList<>();
//    ArrayList<Integer> growPercentage1 = new ArrayList<>();
//    ArrayList<Integer> myColors = new ArrayList<>();
    ArrayList<Integer> growPercentage1 = new ArrayList<>(Arrays.asList(1, -2, 3, 3, -4, 5, 6, 7));
    ArrayList<Integer> myColors = new ArrayList<>(Arrays.asList(Color.BLACK, Color.YELLOW, Color.BLUE, Color.DKGRAY, Color.GREEN, Color.GRAY));


    boolean iscategoryWSPvalue=true,issegTAvalue=true,iscatTAvalue=true;
    FrameLayout frame_layout_container;
    ScrollView scrollView;
    PieChart zone_wise_sales_piechart;
    BarChart category_over_ATQ,region_over_ATQ,isd_barChart1,isd_barChart2,segment_sellio_barchart,category_sellio_barchart,sellio_barchart,pdp_barChart1,
            pdp_barChart2, ar_od_performance,Region_wise_performance,zeroSales_isdCount, state_wise_sales_barchart,barChart1,barChart2,
            zone_AR_TA,isdTA_cm,isdTA_l1m,isdTA_l2m, category_wise_sales_barchart, category_targetandach_barchart,segment_targetandach_barchart,
            commitmentCW_barchart,pp_barChart,categoryCW_barchart, isd_selloutP_barchart,isd_category_selloutP_barchart,isd_segment_selloutP_barchart,
            zone_DSP,category_DSP,segment_DSP;

    LinearLayout llTruck_List,switch_to_YTD,switch_to_YTD_fs,category_targetandach,category_targetandach_fs,state_targetandach_fs,state_targetandach,
            zonewsp_fs,zone_salesperformance,llFullView,tvfullview,zone_wise_totalSales,categorywisesales_fs,category_salesperformance,
            state_salesperformance,segmentwisesales_fs;
    CardView cvswitch_to_YTD,cvcategory_targetandach,cvstate_targetandach,cvzone_salesperformance,cvparent,cvcategory_salesperformance,cvstate_salesperformance;
    DoubleBarChart doubleBarChart;
    OneBehindAnotherBarchart obarchart;

    @SuppressLint("InflateParams")
    public void initialize() {
        llTruck_List = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
        llBody.addView(llTruck_List, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        llHeader.setVisibility(View.VISIBLE);
        xlabels123 = new ArrayList<>(Arrays.asList(xLabels));
//        growPercentage1.add(1);growPercentage1.add(-2);growPercentage1.add(3);growPercentage1.add(3);growPercentage1.add(-4);growPercentage1.add(5);growPercentage1.add(6);growPercentage1.add(7);
//        myColors.add(Color.BLACK);myColors.add(Color.YELLOW);myColors.add(Color.BLUE);myColors.add(Color.DKGRAY);myColors.add(Color.GREEN);myColors.add(Color.GRAY);

        initializeVariables();

        zoneWiseSalesPieChart(zone_wise_sales_piechart);
        findViewById(R.id.zonewisesales_switchtohiend).setOnClickListener(view -> zoneWiseSalesPieChart(zone_wise_sales_piechart));
        findViewById(R.id.zonewisesales_switchtovolume).setOnClickListener(view -> zoneWiseSalesPieChart(zone_wise_sales_piechart));

        singleBarChart(category_wise_sales_barchart,getBarEntriesOne(),new ArrayList<>(Arrays.asList(xLabels1)),Color.parseColor("#005BAA"),0,iscategoryWSPvalue);
        findViewById(R.id.categorywisesales_switchtohiend).setOnClickListener(view -> singleBarChart(category_wise_sales_barchart,getBarEntriesOne(),new ArrayList<>(Arrays.asList(xLabels1)),Color.parseColor("#005BAA"),0,iscategoryWSPvalue));
        findViewById(R.id.categorywisesales_switchtovolume).setOnClickListener(view -> singleBarChart(category_wise_sales_barchart,getBarEntriesOne(),new ArrayList<>(Arrays.asList(xLabels1)),Color.parseColor("#005BAA"),0,iscategoryWSPvalue));

        singleBarChart(state_wise_sales_barchart,getBarEntriesOne(),new ArrayList<>(Arrays.asList(xLabels1)),Color.parseColor("#077ADF"),315,iscategoryWSPvalue);
        findViewById(R.id.segmentwisesales_switchtohiend).setOnClickListener(view -> singleBarChart(state_wise_sales_barchart,getBarEntriesOne(),new ArrayList<>(Arrays.asList(xLabels1)),Color.parseColor("#077ADF"),315,iscategoryWSPvalue));
        findViewById(R.id.segmentwisesales_switchtovolume).setOnClickListener(view -> singleBarChart(state_wise_sales_barchart,getBarEntriesOne(),new ArrayList<>(Arrays.asList(xLabels1)),Color.parseColor("#077ADF"),315,iscategoryWSPvalue));

        dualBar(barChart1,barChart2);
        findViewById(R.id.hiEnd_state_target_And_Ach_Reload).setOnClickListener(view -> dualBar(barChart1,barChart2));
        findViewById(R.id.volume_state_target_And_Ach_Reload).setOnClickListener(view -> dualBar(barChart1,barChart2));


        DualBarChart("category_targetandach_barchart",findViewById(R.id.categorytargetandach_switchtohiend),findViewById(R.id.category_targetandach_Reload));
        DualBarChart("segment_targetandach_barchart",findViewById(R.id.segmenttargetandach_switchtohiend),findViewById(R.id.segment_targetandach_Reload));

        DualBarChart("commitmentCW_barchart",findViewById(R.id.commitmentCW_switchtohiend),findViewById(R.id.commitmentCW_switchtovolume));
        DualBarChart("categoryCW_barchart",findViewById(R.id.categoryCW_barchart_switchtohiend),findViewById(R.id.categoryCW_barchart_switchtovolume));
        DualBarChart("pp_barChart",null,findViewById(R.id.pp_switch_to_YTD));

        DualBarChart("isd_selloutP_barchart",findViewById(R.id.isd_selloutP_barchart_switchtohiend),findViewById(R.id.isd_selloutP_switchtovolume));
        DualBarChart("isd_category_selloutP_barchart",findViewById(R.id.isd_category_selloutP_barchart_switchtohiend),findViewById(R.id.isd_category_selloutP_barchart_switchtovolume));
        DualBarChart("isd_segment_selloutP_barchart",findViewById(R.id.isd_segment_selloutP_barchart_switchtohiend),findViewById(R.id.isd_segment_selloutP_barchart_switchtovolume));

        DualBarChart("isdTA_cm",findViewById(R.id.isdTA_cm_switchtohiend),findViewById(R.id.isdTA_cm_switchtovolume));
        DualBarChart("isdTA_l1m",findViewById(R.id.isdTA_l1m_switchtohiend),findViewById(R.id.isdTA_l1m_switchtovolume));
        DualBarChart("isdTA_l2m",findViewById(R.id.isdTA_l2m_switchtohiend),findViewById(R.id.isdTA_l2m_switchtovolume));

        DualBarChart("zone_DSP",null,null);
        DualBarChart("category_DSP",null,null);
        DualBarChart("segment_DSP",null,null);
        DualBarChart("zone_AR_TA",null,null);

        OneBehindAnotherBarChart("zeroSales_isdCount");
        OneBehindAnotherBarChart("Region_wise_performance");
        OneBehindAnotherBarChart("ar_od_performance");

        dualBar(pdp_barChart1,pdp_barChart2);
        findViewById(R.id.pdp_MTD).setOnClickListener(view -> dualBar(pdp_barChart1,pdp_barChart2));


        TripleBarChart(sellio_barchart, "Sell In", Color.parseColor("#BE9502"), getBarEntriesOne(),
                "Sell Out", Color.parseColor("#0055A0"), getBarEntriesThree(),
                "Sell Through", Color.parseColor("#498BC5"), getBarEntriesTwo());

        TripleBarChart(category_sellio_barchart, "Sell In", Color.parseColor("#FAB901"), getBarEntriesOne(),
                "Sell Out", Color.parseColor("#55D920"), getBarEntriesThree(),
                "Sell Through", Color.parseColor("#2D9F07"), getBarEntriesTwo());

        TripleBarChart(segment_sellio_barchart, "Sell In", Color.parseColor("#BE9502"), getBarEntriesOne(),
                "Sell Out", Color.parseColor("#56A1E3"), getBarEntriesThree(),
                "Sell Through", Color.parseColor("#0055A0"), getBarEntriesTwo());

        findViewById(R.id.sellio_switchtohiend).setOnClickListener(view -> TripleBarChart(sellio_barchart, "Sell In", Color.parseColor("#BE9502"), getBarEntriesOne(),
                "Sell Out", Color.parseColor("#0055A0"), getBarEntriesThree(),
                "Sell Through", Color.parseColor("#498BC5"), getBarEntriesTwo()));
        findViewById(R.id.sellio_switchtovolume).setOnClickListener(view -> TripleBarChart(sellio_barchart, "Sell In", Color.parseColor("#BE9502"), getBarEntriesOne(),
                "Sell Out", Color.parseColor("#0055A0"), getBarEntriesThree(),
                "Sell Through", Color.parseColor("#498BC5"), getBarEntriesTwo()));
        findViewById(R.id.category_sellio_switchtohiend).setOnClickListener(view -> TripleBarChart(category_sellio_barchart, "Sell In", Color.parseColor("#FAB901"), getBarEntriesOne(),
                "Sell Out", Color.parseColor("#55D920"), getBarEntriesThree(),
                "Sell Through", Color.parseColor("#2D9F07"), getBarEntriesTwo()));
        findViewById(R.id.category_sellio_switchtovolume).setOnClickListener(view -> TripleBarChart(category_sellio_barchart, "Sell In", Color.parseColor("#FAB901"), getBarEntriesOne(),
                "Sell Out", Color.parseColor("#55D920"), getBarEntriesThree(),
                "Sell Through", Color.parseColor("#2D9F07"), getBarEntriesTwo()));
        findViewById(R.id.segment_sellio_switchtohiend).setOnClickListener(view -> TripleBarChart(segment_sellio_barchart, "Sell In", Color.parseColor("#BE9502"), getBarEntriesOne(),
                "Sell Out", Color.parseColor("#56A1E3"), getBarEntriesThree(),
                "Sell Through", Color.parseColor("#0055A0"), getBarEntriesTwo()));
        findViewById(R.id.segment_sellio_switchtovolume).setOnClickListener(view -> TripleBarChart(segment_sellio_barchart, "Sell In", Color.parseColor("#BE9502"), getBarEntriesOne(),
                "Sell Out", Color.parseColor("#56A1E3"), getBarEntriesThree(),
                "Sell Through", Color.parseColor("#0055A0"), getBarEntriesTwo()));

        isdSellOutPieChart();

        dualBar(isd_barChart1,isd_barChart2);
        findViewById(R.id.isd_switchtohiend).setOnClickListener(view -> dualBar(isd_barChart1,isd_barChart2));
        findViewById(R.id.isd_switchtovolume).setOnClickListener(view -> dualBar(isd_barChart1,isd_barChart2));

        competitorDSP();
        marketCoverage();
        overATQ();

        OneBehindAnotherBarChart("region_over_ATQ");
        OneBehindAnotherBarChart("category_over_ATQ");

        valueByFGM();qtyByFGM();
        OverDues();

        new Thread(() -> runOnUiThread(this::hideLoader)).start();
        findViewById(R.id.segment_dropdown).setOnClickListener(view -> setPopPUP(view, findViewById(R.id.segment_selected_type), Arrays.asList(options)));
        findViewById(R.id.pp_dropdown).setOnClickListener(view -> setPopPUP(view, findViewById(R.id.pp_selected_type), Arrays.asList(options1)));
        findViewById(R.id.commitmentCW_dropdown).setOnClickListener(view -> setPopPUP(view, findViewById(R.id.commitmentCW_selected_type), Arrays.asList(weeks)));
        findViewById(R.id.categoryCW_dropdown).setOnClickListener(view -> setPopPUP(view, findViewById(R.id.categoryCW_selectedtype), Arrays.asList(weeks)));
    }


    final String[] options = {"CE","ST","AQ"};
    final String[] options1 = {"All","CE","ST","AQ"};
    final String[] weeks = {"week 1","week 2","week 3","week 4","week 5"};
    public void setPopPUP(View anchorView, TextView targetTextView, List<String> options) {
        PopupMenu popupMenu = new PopupMenu(anchorView.getContext(), anchorView);
        for (String option : options)
            if (!option.equals(targetTextView.getText().toString()))
                popupMenu.getMenu().add(option);

        popupMenu.setOnMenuItemClickListener(item -> {
            targetTextView.setText(item.getTitle());
            return true;
        });
        popupMenu.show();
    }


    public void initializeVariables(){
        frame_layout_container=findViewById(R.id.frame_layout_container);
        scrollView=findViewById(R.id.scrollView);

        zone_wise_sales_piechart=findViewById(R.id.zone_wise_sales_piechart);
        zone_wise_totalSales=findViewById(R.id.zone_wise_totalSales);
        zonewsp_fs=findViewById(R.id.zonewsp_fs);
        zone_salesperformance=findViewById(R.id.zone_salesperformance);
        cvzone_salesperformance=findViewById(R.id.cvzone_salesperformance);

        state_targetandach_fs=findViewById(R.id.state_targetandach_fs);
        state_targetandach=findViewById(R.id.state_targetandach);
        cvstate_targetandach=findViewById(R.id.cvstate_targetandach);
        barChart1=findViewById(R.id.barChart1);
        barChart2=findViewById(R.id.barChart2);
        state_targetandach_fs.setOnClickListener(v -> {addRequiredLayout(cvstate_targetandach,state_targetandach,state_targetandach_fs);});

        category_targetandach_barchart=findViewById(R.id.category_targetandach_barchart);
        category_targetandach_fs=findViewById(R.id.category_targetandach_fs);
        cvcategory_targetandach=findViewById(R.id.cvcategory_targetandach);
        category_targetandach=findViewById(R.id.category_targetandach);
        category_targetandach_fs.setOnClickListener(v -> {addRequiredLayout(cvcategory_targetandach,category_targetandach,category_targetandach_fs);});
        segment_targetandach_barchart=findViewById(R.id.segment_targetandach_barchart);

        category_wise_sales_barchart=findViewById(R.id.category_wise_sales_barchart);
        categorywisesales_fs=findViewById(R.id.categorywisesales_fs);
        category_salesperformance=findViewById(R.id.category_salesperformance);
        cvcategory_salesperformance=findViewById(R.id.cvcategory_salesperformance);

        state_wise_sales_barchart=findViewById(R.id.state_wise_sales_barchart);
        cvstate_salesperformance=findViewById(R.id.cvstate_salesperformance);
        state_salesperformance=findViewById(R.id.state_salesperformance);
        segmentwisesales_fs=findViewById(R.id.segmentwisesales_fs);

        LinearLayout sellio_fs=findViewById(R.id.sellio_fs);
        sellio_fs.setOnClickListener(v -> {addRequiredLayout(findViewById(R.id.cv_sellio),findViewById(R.id.ll_sellio),sellio_fs);});

        zonewsp_fs.setOnClickListener(v -> {addRequiredLayout(cvzone_salesperformance,zone_salesperformance,zonewsp_fs);});
        zone_wise_totalSales.setVisibility(View.VISIBLE);
        categorywisesales_fs.setOnClickListener(v -> {addRequiredLayout(cvcategory_salesperformance,category_salesperformance,categorywisesales_fs);});
        segmentwisesales_fs.setOnClickListener(v -> {addRequiredLayout(cvstate_salesperformance,state_salesperformance,segmentwisesales_fs);});


        isdTA_cm=findViewById(R.id.isdTA_cm);
        isdTA_l1m=findViewById(R.id.isdTA_l1m);
        isdTA_l2m=findViewById(R.id.isdTA_l2m);
        zone_AR_TA=findViewById(R.id.zone_AR_TA);

        isd_selloutP_barchart = findViewById(R.id.isd_selloutP_barchart);
        isd_category_selloutP_barchart = findViewById(R.id.isd_category_selloutP_barchart);
        isd_segment_selloutP_barchart = findViewById(R.id.isd_segment_selloutP_barchart);

        commitmentCW_barchart = findViewById(R.id.commitmentCW_barchart);
        categoryCW_barchart = findViewById(R.id.categoryCW_barchart);
        pp_barChart = findViewById(R.id.pp_barChart);

        zone_DSP=findViewById(R.id.zone_DSP);
        category_DSP=findViewById(R.id.category_DSP);
        segment_DSP=findViewById(R.id.segment_DSP);

        switch_to_YTD_fs = findViewById(R.id.switch_to_YTD_fs);
        cvswitch_to_YTD = findViewById(R.id.cvswitch_to_YTD);
        switch_to_YTD = findViewById(R.id.switch_to_YTD);
        switch_to_YTD_fs.setOnClickListener(v -> {addRequiredLayout(cvswitch_to_YTD,switch_to_YTD,switch_to_YTD_fs);});

        zeroSales_isdCount = findViewById(R.id.zeroSales_isdCount);
        Region_wise_performance = findViewById(R.id.Region_wise_performance);
        ar_od_performance = findViewById(R.id.ar_od_performance);
        pdp_barChart1=findViewById(R.id.pdp_barChart1);
        pdp_barChart2=findViewById(R.id.pdp_barChart2);

        sellio_barchart=findViewById(R.id.sellio_barchart);
        category_sellio_barchart=findViewById(R.id.category_sellio_barchart);
        segment_sellio_barchart=findViewById(R.id.segment_sellio_barchart);

        isd_barChart1=findViewById(R.id.isd_barChart1);
        isd_barChart2=findViewById(R.id.isd_barChart2);
        region_over_ATQ = findViewById(R.id.region_over_ATQ);
        category_over_ATQ = findViewById(R.id.category_over_ATQ);

        llstart_day.setOnClickListener(v -> showCustomDialog(MainActivity.this, getString(R.string.alert), getResources().getString(R.string.do_you_want_to)
                + " " + (false ? getResources().getString(R.string.end) : getResources().getString(R.string.start)) + " " + getResources().getString(R.string.day),
                getString(R.string.Yes), getString(R.string.No), "DepotInOut"));
    }


    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void zoneWiseSalesPieChart(PieChart pieChartView) {

        pieChartView.invalidate();
        pieChartView.animate();
        pieChartView.animateY(2000, Easing.EaseInOutQuad); // Animation duration: 1500 milliseconds with EaseInOutQuad interpolation
        pieChartView.setTransparentCircleAlpha(0);// to remove the shadow if the inner circle
        pieChartView.getCenter();
        pieChartView.setRotationEnabled(true);// Enable rotation of the PieChart
        pieChartView.setHoleColor(Color.TRANSPARENT);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(53, "South"));
        entries.add(new PieEntry(47, "Central"));
        entries.add(new PieEntry(42, "North 2"));
        entries.add(new PieEntry(62, "West"));
        entries.add(new PieEntry(29, "North 1"));
        entries.add(new PieEntry(47, "East"));

        int customColor1 = Color.parseColor("#227FD0");
        int customColor2 = Color.parseColor("#F47575");
        int customColor3 = Color.parseColor("#34B0FD");
        int customColor4 = Color.parseColor("#E04AA4");
        int customColor5 = Color.parseColor("#9D47E0");
        int customColor6 = Color.parseColor("#E0C546");
        int[] colors1 = {customColor1,customColor2,customColor3,customColor4, customColor5,customColor6};// Custom colors for each portion

        int[] colors = {Color.parseColor("#227FD0"), Color.parseColor("#F47575"),
                Color.parseColor("#34B0FD"), Color.parseColor("#E04AA4"), Color.parseColor("#9D47E0"),
                Color.parseColor("#E0C546") };

        String[] labels = {"South", "Central", "North 2", "West", "North 1", "East"};
        ArrayList<PieEntry> entriesNew = new ArrayList<>();
        Random random = new Random();
        int total = 0;
        for (String label : labels) {
            int randomValue = random.nextInt(31) + 20; // Range: 20 to 50
            total = total + randomValue;
            entriesNew.add(new PieEntry(randomValue, label));
        }

        PieDataSet pieDataSet = new PieDataSet(entriesNew, "");
//        PieDataSet pieDataSet = new PieDataSet(entries, "");
//        pieDataSet.setColors(generateRandomColors(entries.size()));// Generate random and unique colors
        pieDataSet.setColors(colors);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int) value+"L";
            }
        });

//        pieChartView.setDrawEntryLabels(false); // Disable labels below the chart or to show the labels outside of the chart
        pieChartView.setData(pieData);
        pieChartView.getDescription().setEnabled(false);
        pieChartView.getLegend().setEnabled(false);// Disable the legend
        pieChartView.setDrawEntryLabels(true);
        pieChartView.setEntryLabelColor(Color.BLACK); // Show labels outside the chart with lines pointing to the corresponding portions
        pieChartView.setEntryLabelTextSize(12f);
        pieChartView.setExtraOffsets(25,0,25,0);
        pieChartView.animate();
        ((TextView) findViewById(R.id.zone_wise_sales_total_sales)).setText(total / 100.0f + "Cr");
    }
    private void singleBarChart(BarChart barChart, ArrayList<BarEntry> entries, ArrayList<String> xlabels,int color,int angle, boolean isvalue)
    {
        SingleBarChart customBarChart = new SingleBarChart(barChart,entries,xlabels);
        customBarChart.setbarColor(color);
        customBarChart.setLabelRotationAngle(angle);
        customBarChart.isValueSelected(isvalue);
        customBarChart.createBarChart();
    }
    private void dualBar(BarChart barChart1, BarChart barChart2) {
        barChart1.clear();barChart2.clear();
        DualBarCharts dualBarCharts1= new DualBarCharts(getApplicationContext(),barChart1,barChart2,xlabels123);
        dualBarCharts1.setBarChart1(getBarEntriesSingleValues(),"growth","Degrwoth",Color.parseColor("#50C327"),
                Color.parseColor("#CB2832"));
        dualBarCharts1.setBarChart2(getBarEntriesTwo(),getBarEntriesThree(),"Target","Ach",Color.parseColor("#026FCD"),
                Color.parseColor("#329F0B"),growPercentage1);
        dualBarCharts1.createDualBarCharts();
    }
    private void TripleBarChart(BarChart idBarChart4, String title1, int parseColor1, ArrayList<BarEntry> barEntries1, String title2, int parseColor2,
                                ArrayList<BarEntry> barEntries2, String title3, int parseColor3, ArrayList<BarEntry> barEntries3)
    {
//        CustomBarChartRender customBarChartRender = new CustomBarChartRender(idBarChart4, idBarChart4.getAnimator(), idBarChart4.getViewPortHandler());
//        customBarChartRender.setRadius(20);
//        idBarChart4.setRenderer(customBarChartRender);

        AboveAndCurveCustomBarChart aboveAndCurveCustomBarChart= new AboveAndCurveCustomBarChart(
                getApplicationContext(),idBarChart4,idBarChart4.getAnimator(),idBarChart4.getViewPortHandler(), myColors,growPercentage1);
        aboveAndCurveCustomBarChart.setRadius(20);
        aboveAndCurveCustomBarChart.aboveValueTextColor(Color.BLACK);
        aboveAndCurveCustomBarChart.disaplayImage(false);
        aboveAndCurveCustomBarChart.textNeededBarPosition(3);
        idBarChart4.setRenderer(aboveAndCurveCustomBarChart);

        idBarChart4.animateXY(1500, 1500);
        idBarChart4.setHighlightFullBarEnabled(false);
        idBarChart4.setDoubleTapToZoomEnabled(false);
        idBarChart4.setDragEnabled(false);

        BarDataSet barDataSet1 = new BarDataSet(barEntries1, title1);
        barDataSet1.setColor(parseColor1);
        BarDataSet barDataSet2 = new BarDataSet(barEntries2, title2);
        barDataSet2.setColor(parseColor2);
        BarDataSet barDataSet3 = new BarDataSet(barEntries3, title3);
        barDataSet3.setColor(parseColor3);

        barDataSet1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if(value>0)
                    return (int) value+"L";
                else
                    return "";
            }
        });
        barDataSet2.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if(value>0)
                    return (int) value+"L";
                else
                    return "";
            }
        });
        barDataSet3.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if(value>0)
                    return (int) value+"L";
                else
                    return "";
            }
        });

        BarData data = new BarData(barDataSet1, barDataSet2,barDataSet3);
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);

        idBarChart4.setExtraTopOffset(10f);
        idBarChart4.setDrawValueAboveBar(false);//to show value above bar

        Legend legend = idBarChart4.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setXOffset(5f);
        legend.setYOffset(5f);

        XAxis xAxis = idBarChart4.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabels));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
//        xAxis.setAxisMinimum(0f);//by kush axis label
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);

        YAxis rightYAxis = idBarChart4.getAxisRight();
        rightYAxis.setEnabled(false);

        YAxis leftYAxis = idBarChart4.getAxisLeft();

        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftYAxis.setDrawGridLines(false);

        idBarChart4.setDoubleTapToZoomEnabled(false);
        idBarChart4.setData(data);
        idBarChart4.getDescription().setEnabled(false);
        idBarChart4.getXAxis().setAxisMinimum(0);
        idBarChart4.animate();
        idBarChart4.getDescription().setEnabled(false);
//        idBarChart1.setTouchEnabled(false);//to remove zoom in option
        idBarChart4.setDragEnabled(true);
        idBarChart4.setVisibleXRangeMaximum(3);
//        float barSpace = 0.1f;
//        float groupSpace = 0.1f;

        //main don't remove or change
//        float groupSpace = 0.4f; // Space between groups
//        float barSpace = 0.06f; // Space between individual bars within a group
//        float barWidth = 0.14f; // Width of each bar

//        okok for 3 and 4 setVisibleXRangeMaximum
//        float groupSpace = 0.3f;
//        float barSpace = 0.03f;
//        float barWidth = 0.20f;
//        idBarChart2.getXAxis().setAxisMinimum(0.5f);


        float groupSpace = 0.1f;
        float barSpace = 0.03f;
        float barWidth = 0.27f;
//        idBarChart4.getXAxis().setAxisMinimum(1.5f);
        data.setBarWidth(barWidth);
        idBarChart4.groupBars(0, groupSpace, barSpace);
        idBarChart4.setExtraOffsets(0,0,0,10);
        idBarChart4.invalidate();
    }

//    private void DualBarChart(String barChartType){
    private void DualBarChartOldWorking(String barChartType, TextView viewById, TextView viewById1) {
        switch (barChartType){
            case "commitmentCW_barchart":
                doubleBarChart=new DoubleBarChart(commitmentCW_barchart,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Target",Color.parseColor("#227FD0"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Achievement",Color.parseColor("#BE9502"));
                doubleBarChart.isValueSelected(true);
                doubleBarChart.createBarChart();
                viewById.setOnClickListener(v-> commitmentCW_barchart.invalidate());
                viewById1.setOnClickListener(v-> commitmentCW_barchart.invalidate());
                break;
            case "isdTA_cm":
                doubleBarChart=new DoubleBarChart(isdTA_cm,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Oct-2023",Color.parseColor("#3ACA06"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Oct-2022",Color.parseColor("#1F7102"));
                doubleBarChart.isValueSelected(true);
                doubleBarChart.setAboveImageEnable(false);
                doubleBarChart.setAboveTextColor(Color.BLACK);
                doubleBarChart.createBarChart();
                viewById.setOnClickListener(v-> isdTA_cm.invalidate());
                viewById1.setOnClickListener(v-> isdTA_cm.invalidate());
                break;
            case "isdTA_l1m":
                doubleBarChart=new DoubleBarChart(isdTA_l1m,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Oct-2023",Color.parseColor("#BE9502"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Oct-2022",Color.parseColor("#227FD0"));
                doubleBarChart.isValueSelected(true);
                doubleBarChart.setAboveImageEnable(false);
                doubleBarChart.setAboveTextColor(Color.BLACK);
                doubleBarChart.createBarChart();
                break;
            case "isdTA_l2m":
                doubleBarChart=new DoubleBarChart(isdTA_l2m,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Oct-2023",Color.parseColor("#02B220"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Oct-2022",Color.parseColor("#F2A201"));
                doubleBarChart.isValueSelected(true);
                doubleBarChart.setAboveImageEnable(false);
                doubleBarChart.setAboveTextColor(Color.BLACK);
                doubleBarChart.createBarChart();
                break;
            case "isd_selloutP_barchart":
                doubleBarChart=new DoubleBarChart(isd_selloutP_barchart,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Oct-2023",Color.parseColor("#005BAA"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Oct-2022",Color.parseColor("#98BDDC"));
                doubleBarChart.isValueSelected(true);
                doubleBarChart.createBarChart();
                break;
            case "isd_category_selloutP_barchart":
                doubleBarChart=new DoubleBarChart(isd_category_selloutP_barchart,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Oct-2023",Color.parseColor("#005BAA"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Oct-2022",Color.parseColor("#98BDDC"));
                doubleBarChart.isValueSelected(true);
                doubleBarChart.createBarChart();
                break;
            case "isd_segment_selloutP_barchart":
                doubleBarChart=new DoubleBarChart(isd_segment_selloutP_barchart,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Oct-2023",Color.parseColor("#005BAA"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Oct-2022",Color.parseColor("#98BDDC"));
                doubleBarChart.isValueSelected(true);
                doubleBarChart.createBarChart();
                break;
            case "categoryCW_barchart":
                doubleBarChart=new DoubleBarChart(categoryCW_barchart,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Target",Color.parseColor("#227FD0"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Achievement",Color.parseColor("#BE9502"));
                doubleBarChart.isValueSelected(true);
                doubleBarChart.createBarChart();
                break;
            case "pp_barChart":
                doubleBarChart=new DoubleBarChart(pp_barChart,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"MTD",Color.parseColor("#227FD0"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"LYTD",Color.parseColor("#20C6B2"));
                doubleBarChart.isValueSelected(true);
                doubleBarChart.setLabelRotationAngle(315);
//                if(ppTargetEntries.size()<5) doubleBarChart.setAxisMaximum(11);
//                else doubleBarChart.setAxisMaximum(0);
                doubleBarChart.createBarChart();
                break;
            case "zone_AR_TA":
                doubleBarChart=new DoubleBarChart(zone_AR_TA,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Target",Color.parseColor("#FAB901"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Ach",Color.parseColor("#1F7102"));
                doubleBarChart.isValueSelected(true);
                doubleBarChart.setAboveImageEnable(false);
                doubleBarChart.setAboveTextColor(Color.BLACK);
                doubleBarChart.createBarChart();
                break;
            case "category_targetandach_barchart":
                doubleBarChart=new DoubleBarChart(category_targetandach_barchart,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Target",Color.parseColor("#227FD0"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Achievement",Color.parseColor("#20C6B2"));
                doubleBarChart.isValueSelected(iscatTAvalue);
                doubleBarChart.createBarChart();
                break;
            case "segment_targetandach_barchart":
                doubleBarChart=new DoubleBarChart(segment_targetandach_barchart,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Target",Color.parseColor("#227FD0"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Achievement",Color.parseColor("#20C6B2"));
                doubleBarChart.isValueSelected(issegTAvalue);
                doubleBarChart.createBarChart();
                break;

            case "zone_DSP":
                doubleBarChart=new DoubleBarChart(zone_DSP,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Oct-2023",Color.parseColor("#227FD0"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Sep-2023",Color.parseColor("#50C327"));
                doubleBarChart.isValueSelected(issegTAvalue);
                doubleBarChart.createBarChart();
                break;
            case "category_DSP":
                doubleBarChart=new DoubleBarChart(category_DSP,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Oct-2023",Color.parseColor("#227FD0"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Sep-2023",Color.parseColor("#50C327"));
                doubleBarChart.isValueSelected(issegTAvalue);
                doubleBarChart.createBarChart();
                break;

            case "segment_DSP":
                doubleBarChart=new DoubleBarChart(segment_DSP,xlabels123,growPercentage1);
                doubleBarChart.setDetails1(getBarEntriesOne(),"Oct-2023",Color.parseColor("#227FD0"));
                doubleBarChart.setDetails2(getBarEntriesTwo(),"Sep-2023",Color.parseColor("#50C327"));
                doubleBarChart.isValueSelected(issegTAvalue);
                doubleBarChart.createBarChart();
                break;


            default:
                Toast.makeText(this, "Correct Bar Chart is Called!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void DualBarChart(String barChartType, TextView view1, TextView view2) {
        Map<String, BarChartConfig> chartMap = new HashMap<>();

        chartMap.put("isdTA_cm", new BarChartConfig(isdTA_cm, "Oct-2023", Color.parseColor("#3ACA06"), "Oct-2022", Color.parseColor("#1F7102")).withAboveImage(true));
        chartMap.put("isdTA_l1m", new BarChartConfig(isdTA_l1m, "Oct-2023", Color.parseColor("#BE9502"), "Oct-2022", Color.parseColor("#227FD0")).withAboveImage(true));
        chartMap.put("isdTA_l2m", new BarChartConfig(isdTA_l2m, "Oct-2023", Color.parseColor("#02B220"), "Oct-2022", Color.parseColor("#F2A201")).withAboveImage(true));
        chartMap.put("isd_selloutP_barchart", new BarChartConfig(isd_selloutP_barchart, "Oct-2023", Color.parseColor("#005BAA"), "Oct-2022", Color.parseColor("#98BDDC")));
        chartMap.put("isd_category_selloutP_barchart", new BarChartConfig(isd_category_selloutP_barchart, "Oct-2023", Color.parseColor("#005BAA"), "Oct-2022", Color.parseColor("#98BDDC")));
        chartMap.put("isd_segment_selloutP_barchart", new BarChartConfig(isd_segment_selloutP_barchart, "Oct-2023", Color.parseColor("#005BAA"), "Oct-2022", Color.parseColor("#98BDDC")));
        chartMap.put("commitmentCW_barchart", new BarChartConfig(commitmentCW_barchart, "Target", Color.parseColor("#227FD0"), "Achievement", Color.parseColor("#BE9502")));
        chartMap.put("categoryCW_barchart", new BarChartConfig(categoryCW_barchart, "Target", Color.parseColor("#227FD0"), "Achievement", Color.parseColor("#BE9502")));
        chartMap.put("pp_barChart", new BarChartConfig(pp_barChart, "MTD", Color.parseColor("#227FD0"), "LYTD", Color.parseColor("#20C6B2")).withLabelRotation(315));
        chartMap.put("zone_AR_TA", new BarChartConfig(zone_AR_TA, "Target", Color.parseColor("#FAB901"), "Ach", Color.parseColor("#1F7102")).withAboveImage(true));
        chartMap.put("category_targetandach_barchart", new BarChartConfig(category_targetandach_barchart, "Target", Color.parseColor("#227FD0"), "Achievement", Color.parseColor("#20C6B2")).withValueSelected(iscatTAvalue));
        chartMap.put("segment_targetandach_barchart", new BarChartConfig(segment_targetandach_barchart, "Target", Color.parseColor("#227FD0"), "Achievement", Color.parseColor("#20C6B2")).withValueSelected(issegTAvalue));
        chartMap.put("zone_DSP", new BarChartConfig(zone_DSP, "Oct-2023", Color.parseColor("#227FD0"), "Sep-2023", Color.parseColor("#50C327")).withValueSelected(issegTAvalue));
        chartMap.put("category_DSP", new BarChartConfig(category_DSP, "Oct-2023", Color.parseColor("#227FD0"), "Sep-2023", Color.parseColor("#50C327")).withValueSelected(issegTAvalue));
        chartMap.put("segment_DSP", new BarChartConfig(segment_DSP, "Oct-2023", Color.parseColor("#227FD0"), "Sep-2023", Color.parseColor("#50C327")).withValueSelected(issegTAvalue));

        if (!chartMap.containsKey(barChartType)) {
            Toast.makeText(this, "Correct Bar Chart is Called!!", Toast.LENGTH_SHORT).show();
            return;
        }

        BarChartConfig config = chartMap.get(barChartType);
        DoubleBarChart doubleBarChart = new DoubleBarChart(config.chart, xlabels123, growPercentage1);
        doubleBarChart.setDetails1(getBarEntriesOne(), config.label1, config.color1);
        doubleBarChart.setDetails2(getBarEntriesTwo(), config.label2, config.color2);
        doubleBarChart.isValueSelected(config.isValueSelected);

        if (config.aboveImage) {
            doubleBarChart.setAboveImageEnable(false);
            doubleBarChart.setAboveTextColor(Color.BLACK);
        }

        if (config.labelRotation != 0) doubleBarChart.setLabelRotationAngle(config.labelRotation);

        doubleBarChart.createBarChart();
        if (view1 != null) {
            view1.setOnClickListener(v -> {
                doubleBarChart.setDetails1(getBarEntriesOne(), config.label1, config.color1);
                doubleBarChart.setDetails2(getBarEntriesTwo(), config.label2, config.color2);
                doubleBarChart.createBarChart(); // redraw with updated data
            });
        }
        if (view2 != null) {
            view2.setOnClickListener(v -> {
                doubleBarChart.setDetails1(getBarEntriesOne(), config.label1, config.color1);
                doubleBarChart.setDetails2(getBarEntriesTwo(), config.label2, config.color2);
                doubleBarChart.createBarChart(); // redraw with updated data
            });
        }
    }



    private void OneBehindAnotherBarChart(String barChartType) {
        switch (barChartType) {
            case"zeroSales_isdCount":
                obarchart=new OneBehindAnotherBarchart(zeroSales_isdCount,xlabels123,growPercentage1);
                obarchart.setDetails1(getBarEntriesOne(),"Sell In",Color.parseColor("#FFAA00"));
                obarchart.setDetails2(getBarEntriesFour(),"Sell Out",Color.parseColor("#005BAA"));
                obarchart.setDetails3(getBarEntriesTwo(),"Sell Through",Color.parseColor("#65B948"));
                obarchart.textNeededBarPosition(1);
                obarchart.createBarChart();
                break;
            case"Region_wise_performance":
                obarchart=new OneBehindAnotherBarchart(Region_wise_performance,xlabels123,growPercentage1);
                obarchart.setDetails1(getBarEntriesOne(),"Sell In",Color.parseColor("#FFAA00"));
                obarchart.setDetails2(getBarEntriesFour(),"Sell Out",Color.parseColor("#005BAA"));
                obarchart.setDetails3(getBarEntriesTwo(),"Sell Through",Color.parseColor("#65B948"));
                obarchart.textNeededBarPosition(1);
                obarchart.createBarChart();
                break;
            case"ar_od_performance":
                obarchart=new OneBehindAnotherBarchart(ar_od_performance,xlabels123,growPercentage1);
                obarchart.setDetails1(getBarEntriesOne(),"Sell In",Color.parseColor("#005BAA"));
                obarchart.setDetails2(getBarEntriesFour(),"Sell Out",Color.parseColor("#29B0FF"));
//                obarchart.setDetails3(getBarEntriesTwo(),"Sell Through",Color.parseColor("#65B948"));
                obarchart.textNeededBarPosition(2);
                obarchart.createBarChart();
                break;
            case"region_over_ATQ":
                obarchart=new OneBehindAnotherBarchart(region_over_ATQ,xlabels123,growPercentage1);
                obarchart.setDetails1(getBarEntriesOne(),"AR",Color.parseColor("#FFAA00"));
                obarchart.setDetails2(getBarEntriesFour(),"OD",Color.parseColor("#005BAA"));
                obarchart.setDetails3(getBarEntriesTwo(),"60 Days OD",Color.parseColor("#65B948"));
                obarchart.textNeededBarPosition(1);
                obarchart.createBarChart();
                break;
            case"category_over_ATQ":
                obarchart=new OneBehindAnotherBarchart(category_over_ATQ,xlabels123,growPercentage1);
                obarchart.setDetails1(getBarEntriesOne(),"AR",Color.parseColor("#FFAA00"));
                obarchart.setDetails2(getBarEntriesFour(),"OD",Color.parseColor("#005BAA"));
                obarchart.setDetails3(getBarEntriesTwo(),"60 Days OD",Color.parseColor("#65B948"));
                obarchart.textNeededBarPosition(1);
                obarchart.createBarChart();
                break;

        }
    }
    private void isdSellOutPieChart() {
        PieChart pieChartView = findViewById(R.id.isd_sellout_piechart);
        pieChartView.getCenter();
        pieChartView.setExtraOffsets(25,5,25,0);//to set the padding or margin of the pie chart

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(18, "18 ISD"));
        entries.add(new PieEntry(16, "16 ISD"));
        entries.add(new PieEntry(32, "32 ISD"));
        entries.add(new PieEntry(22, "22 ISD"));
        entries.add(new PieEntry(12, "12 ISD"));

        int customColor1 = Color.parseColor("#01A1FF");
        int customColor2 = Color.parseColor("#BE9502");
        int customColor3 = Color.parseColor("#FFAA00");
        int customColor4 = Color.parseColor("#498CC5");
        int customColor5 = Color.parseColor("#4CD964");
        int[] colors = {customColor1,customColor2,customColor3,customColor4,customColor5};

        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setColors(colors);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(14f);

        //to display the X and Y axis out side the piechart
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData pieData = new PieData(pieDataSet);
        pieChartView.setData(pieData);
        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ((int) value)+"%";
            }
        });

        // Increase the inner circle size (hole size) as a percentage of the chart's radius
        pieChartView.setHoleRadius(55f); // 65% of the chart's radius

        // Additional settings
        pieChartView.getDescription().setEnabled(false);
//        pieChartView.setCenterText("Years");

//        pieChartView.setDrawEntryLabels(false); // Disable labels below the chart
        pieChartView.setDrawEntryLabels(true);

        // Show labels outside the chart with lines pointing to the corresponding portions
//        pieChartView.setEntryLabelColor(Color.BLACK);
        pieChartView.setEntryLabelColor(Color.parseColor("#BE9502"));

        Legend legend = pieChartView.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setYOffset(5);

        // Create custom entries for the legend
        LegendEntry[] customEntries = new LegendEntry[]{
                new LegendEntry("Zero", Legend.LegendForm.SQUARE, 10f, 2f, null, Color.parseColor("#4CD964")),
//                createGapEntry(),createGapEntry(),createGapEntry(),createGapEntry(),
                new LegendEntry("0-1L", Legend.LegendForm.SQUARE, 10f, 2f, null, Color.parseColor("#01A1FF")),
//                createGapEntry(),createGapEntry(),createGapEntry(),createGapEntry(),
                new LegendEntry("1-5L", Legend.LegendForm.SQUARE, 10f, 2f, null, Color.parseColor("#BE9502")),
//                createGapEntry(),createGapEntry(),createGapEntry(),createGapEntry(),
                new LegendEntry("5-10L", Legend.LegendForm.SQUARE, 10f, 2f, null, Color.parseColor("#FFAA00")),
//                createGapEntry(),createGapEntry(),createGapEntry(),createGapEntry(),
                new LegendEntry(">10L", Legend.LegendForm.SQUARE, 10f, 2f, null, Color.parseColor("#498CC5")),
        };

        // Set custom entries for the legend
        legend.setCustom(customEntries);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false); // Set to true if you want the legend to be drawn inside the chart

        pieChartView.animate();
    }
    private void competitorDSP() {
        //go to the link for the label above the value or please enter the text in search in google  in pie chart i want text label above the value
//        https://stackoverflow.com/questions/69540724/mpandroidchart-piechart-label-on-top-of-the-value

        PieChart pieChartView = findViewById(R.id.competitor_dsp_pieChart);
        pieChartView.getCenter();
//        pieChartView.setExtraOffsets(10,25,10,5);//to set the paddinf or margin of the pie chart

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(14, "Samsung"));
        entries.add(new PieEntry(20, "LG"));
        entries.add(new PieEntry(24, "Sony"));
        entries.add(new PieEntry(10, "Others"));
        entries.add(new PieEntry(32, "Haier"));

        int customColor1 = Color.parseColor("#E5B610");
        int customColor2 = Color.parseColor("#44BD1B");
        int customColor3 = Color.parseColor("#1362A7");
        int customColor4 = Color.parseColor("#E89313");
        int customColor5 = Color.parseColor("#2588DE");
        int[] colors = {customColor1,customColor2,customColor3,customColor4,customColor5};

        PieDataSet pieDataSet = new PieDataSet(entries, "");
//        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setColors(colors);
//        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieChartView.setHoleRadius(35f); // 65% of the chart's radius


        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTypeface(Typeface.DEFAULT_BOLD);
        pieData.setValueTextSize(18f);
        pieData.setValueTextColor(Color.WHITE);

        pieChartView.setData(pieData);
        pieChartView.setTransparentCircleAlpha(0);// to remove the shadow if the inner circle
        pieChartView.setRotationEnabled(true);// to rotate the pie chart
        pieChartView.setRotationAngle(250f); // Set the starting angle to 45 degrees

        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value)+"%";
            }
        });

        // Additional settings
        pieChartView.getDescription().setEnabled(false);
//        pieChartView.setDrawEntryLabels(false); // Disable labels outside the chart
        pieChartView.setDrawEntryLabels(true);
        pieChartView.setEntryLabelTextSize(16f);
        pieChartView.setEntryLabelColor(Color.WHITE);// Show labels outside the chart with lines pointing to the corresponding portions
        pieChartView.getLegend().setEnabled(false);// Disable the legend
//        pieChartView.getLegend().setFormLineColor(colors);
//        pieChartView.getLegend().setDrawInside(false);

        pieChartView.animate();
    }
    private void marketCoverage() {
        PieChart pieChartView = findViewById(R.id.marketCoverage_piechart);
        pieChartView.getCenter();
//        pieChartView.setExtraOffsets(20,0,20,5);//to set the padding or margin of the pie chart

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(5000, "Target "));
        entries.add(new PieEntry(3250, "Ach   "));
        int customColor1 = Color.parseColor("#498CC5");
        int customColor2 = Color.parseColor("#FFAA00");
        int[] colors = {customColor1,customColor2};

        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setColors(colors);
//        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextColor(Color.parseColor("#4C515B"));
        pieDataSet.setValueTextSize(14f);

        //to display the X and Y axis out side the piechart
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        pieChartView.setRotationAngle(250f); // Set the starting angle to 45 degrees

        PieData pieData = new PieData(pieDataSet);
        pieChartView.setData(pieData);
        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ((int) value)+"";
            }
        });

        // Increase the inner circle size (hole size) as a percentage of the chart's radius
        pieChartView.setHoleRadius(60f); // 65% of the chart's radius
        pieChartView.getDescription().setEnabled(false);
//        pieChartView.setDrawEntryLabels(false); // Disable labels below the chart
        pieChartView.setDrawEntryLabels(true);

        // Show labels outside the chart with lines pointing to the corresponding portions
//        pieChartView.setEntryLabelColor(Color.BLACK);
        pieChartView.setEntryLabelColor(Color.parseColor("#4C515B"));

        Legend legend = pieChartView.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setYOffset(2);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // Create custom entries for the legend
        LegendEntry[] customEntries = new LegendEntry[]{
                new LegendEntry("Target", Legend.LegendForm.SQUARE, 10f, 2f, null, Color.parseColor("#FFAA00")),
                createGapEntry(),createGapEntry(),createGapEntry(),createGapEntry(),
                new LegendEntry("Ach", Legend.LegendForm.SQUARE, 10f, 2f, null, Color.parseColor("#498CC5")),
        };
        legend.setCustom(customEntries);// Set custom entries for the legend

        pieChartView.setExtraOffsets(25,0,25,0);
        pieChartView.animate();
    }
    private void overATQ() {
        LineChart lineChart = findViewById(R.id.over_ATQ);
        lineChart.setExtraOffsets(10,0,10,13);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setClickable(false);
        lineChart.getDescription().setEnabled(false);// Remove description label on X-axis

        String[] days50 = generateDates(50);
        ArrayList<Entry> values = generateRandomData(50);
        ArrayList<Entry> values2 = generateRandomData(50);
        ArrayList<Entry> values3 = generateRandomData(50);
        Log.d("values", values.toString());Log.d("values1", values2.toString());Log.d("values3", values3.toString());

        int customColor1 = Color.parseColor("#3EA81A");
        int customColor2 = Color.parseColor("#005BAA");
        int customColor3 = Color.parseColor("#FFAA00");

        LineDataSet dataSet1 = new LineDataSet(values, "Invt.(Inc.Transit)");
        dataSet1.setColor(customColor1);
        dataSet1.setCircleColor(customColor1);
        dataSet1.setLineWidth(2);
        dataSet1.setCircleRadius(6f); // Adjust the radius as needed
        dataSet1.setCircleHoleRadius(3f); // Adjust the hole radius as needed
        dataSet1.setCircleHoleColor(Color.WHITE);
//        dataSet1.setCircleHoleColor(Color.parseColor("#98BDDC"));
        dataSet1.setDrawValues(false); // Disable drawing values on points


        LineDataSet dataSet2 = new LineDataSet(values2, "Over - Ageing");
//        dataSet2.setColor(Color.RED);
        dataSet2.setColor(customColor2);
        dataSet2.setCircleColor(customColor2);
        dataSet2.setDrawHighlightIndicators(true);
        dataSet2.setHighLightColor(Color.RED);
        dataSet2.setLineWidth(2);
        dataSet2.setCircleRadius(6f); // Adjust the radius as needed
        dataSet2.setCircleHoleRadius(3f); // Adjust the hole radius as needed
        dataSet2.setCircleHoleColor(Color.WHITE);
        dataSet2.setValueTextColor(customColor1);
        dataSet2.setValueTextSize(12f);
        dataSet2.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int)value+"%";
            }
        });
        LineDataSet dataSet3 = new LineDataSet(values3, "FWD Ageing");
        dataSet3.setColor(customColor3);
        dataSet3.setCircleColor(customColor3);
        dataSet3.setLineWidth(2);
        dataSet3.setCircleRadius(6f); // Adjust the radius as needed
        dataSet3.setCircleHoleRadius(3f); // Adjust the hole radius as needed
        dataSet3.setCircleHoleColor(Color.WHITE);
        dataSet3.setDrawValues(false); // Disable drawing values on points


        // Combine datasets into LineData
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);
        dataSets.add(dataSet3);

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);

        // Customize X-axis
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days50));
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(LabelsForSingleBarCharts));
//        xAxis.setLabelCount(20, true);
//        xAxis.setAxisLineWidth(1f);
        lineChart.setVisibleXRangeMaximum(7);

        xAxis.setYOffset(10f);
        // Customize left Y-axis
        YAxis leftYAxis = lineChart.getAxisLeft();
        leftYAxis.setGranularity(0f);
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setDrawGridLines(true);

        // Customize right Y-axis
        YAxis rightYAxis = lineChart.getAxisRight();
        rightYAxis.setEnabled(false); // Disable right Y-axis
        rightYAxis.setDrawGridLines(false); // Disable grid lines on the right

        // Customize chart appearance
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        lineChart.getAxisRight().setEnabled(false);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // Set extra offset at the top to create a gap
//        float extraTopOffset = getResources().getDimension(R.dimen.legend_top_offset);
//        lineChart.setExtraTopOffset(extraTopOffset);

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) lineChart.getLayoutParams();
//        layoutParams.topMargin = (int) getResources().getDimensionPixelSize(R.dimen.chart_top_margin);
//        layoutParams.bottomMargin = (int) getResources().getDimensionPixelSize(R.dimen.chart_bottom_margin);
//        layoutParams.leftMargin = (int) getResources().getDimensionPixelSize(R.dimen.legend_left_offset);
        lineChart.setLayoutParams(layoutParams);
        lineChart.invalidate();
    }
    private void valueByFGM() {
        PieChart pieChartView = findViewById(R.id.fgm_value);
        pieChartView.getCenter();
//        pieChartView.setExtraOffsets(10,25,10,5);//to set the paddinf or margin of the pie chart

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(110, "model1"));
        entries.add(new PieEntry(120, "model2"));
        entries.add(new PieEntry(112, "model3"));
        entries.add(new PieEntry( 70, "model4"));
        entries.add(new PieEntry(250, "model5"));


        int customColor1 = Color.parseColor("#E5B610");
        int customColor2 = Color.parseColor("#44BD1B");
        int customColor3 = Color.parseColor("#1362A7");
        int customColor4 = Color.parseColor("#E89313");
        int customColor5 = Color.parseColor("#2588DE");
        int[] colors = {customColor1,customColor2,customColor3,customColor4,customColor5};

        PieDataSet pieDataSet = new PieDataSet(entries, "");
//        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setColors(colors);
//        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieChartView.setHoleRadius(35f); // 65% of the chart's radius


        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTypeface(Typeface.DEFAULT_BOLD);
        pieData.setValueTextSize(18f);
        pieData.setValueTextColor(Color.WHITE);

        pieChartView.setData(pieData);
        pieChartView.setTransparentCircleAlpha(0);// to remove the shadow if the inner circle
        pieChartView.setRotationEnabled(true);// to rotate the pie chart
        pieChartView.setRotationAngle(250f); // Set the starting angle to 45 degrees

        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int) value+"(99%)";
            }
        });

        // Additional settings
        pieChartView.getDescription().setEnabled(false);
//        pieChartView.setDrawEntryLabels(false); // Disable labels outside the chart
        pieChartView.setDrawEntryLabels(true);
        pieChartView.setEntryLabelTextSize(16f);
        pieChartView.setEntryLabelColor(Color.WHITE);// Show labels outside the chart with lines pointing to the corresponding portions
        pieChartView.getLegend().setEnabled(false);// Disable the legend
//        pieChartView.getLegend().setFormLineColor(colors);
//        pieChartView.getLegend().setDrawInside(false);

        pieChartView.animate();
    }
    private void qtyByFGM() {
        PieChart pieChartView = findViewById(R.id.fgm_qty);
        pieChartView.getCenter();
//        pieChartView.setExtraOffsets(10,25,10,5);//to set the paddinf or margin of the pie chart

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(110, "model1"));
        entries.add(new PieEntry(120, "model2"));
        entries.add(new PieEntry(112, "model3"));
        entries.add(new PieEntry( 70, "model4"));
        entries.add(new PieEntry(250, "model5"));

        int customColor1 = Color.parseColor("#E5B610");
        int customColor2 = Color.parseColor("#44BD1B");
        int customColor3 = Color.parseColor("#1362A7");
        int customColor4 = Color.parseColor("#E89313");
        int customColor5 = Color.parseColor("#2588DE");
        int[] colors = {customColor1,customColor2,customColor3,customColor4,customColor5};

        PieDataSet pieDataSet = new PieDataSet(entries, "");
//        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setColors(colors);
//        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieChartView.setHoleRadius(35f); // 65% of the chart's radius


        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTypeface(Typeface.DEFAULT_BOLD);
        pieData.setValueTextSize(18f);
        pieData.setValueTextColor(Color.WHITE);

        pieChartView.setData(pieData);
        pieChartView.setTransparentCircleAlpha(0);// to remove the shadow if the inner circle
        pieChartView.setRotationEnabled(true);// to rotate the pie chart
        pieChartView.setRotationAngle(250f); // Set the starting angle to 45 degrees

        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value)+"%";
            }
        });

        // Additional settings
        pieChartView.getDescription().setEnabled(false);
//        pieChartView.setDrawEntryLabels(false); // Disable labels outside the chart
        pieChartView.setDrawEntryLabels(true);
        pieChartView.setEntryLabelTextSize(16f);
        pieChartView.setEntryLabelColor(Color.WHITE);// Show labels outside the chart with lines pointing to the corresponding portions
        pieChartView.getLegend().setEnabled(false);// Disable the legend
//        pieChartView.getLegend().setFormLineColor(colors);
//        pieChartView.getLegend().setDrawInside(false);

        pieChartView.animate();
    }
    private void OverDues() {
        PieChart pieChartView = findViewById(R.id.high_overdues);
        pieChartView.getCenter();
//        pieChartView.setExtraOffsets(10,25,10,5);//to set the paddinf or margin of the pie chart

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(110, "model1"));
        entries.add(new PieEntry(120, "model2"));
        entries.add(new PieEntry(112, "model3"));
        entries.add(new PieEntry( 70, "model4"));
        entries.add(new PieEntry(250, "model5"));


        int customColor1 = Color.parseColor("#E5B610");
        int customColor2 = Color.parseColor("#44BD1B");
        int customColor3 = Color.parseColor("#1362A7");
        int customColor4 = Color.parseColor("#E89313");
        int customColor5 = Color.parseColor("#2588DE");
        int[] colors = {customColor1,customColor2,customColor3,customColor4,customColor5};

        PieDataSet pieDataSet = new PieDataSet(entries, "");
//        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setColors(colors);
//        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieChartView.setHoleRadius(35f); // 65% of the chart's radius


        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTypeface(Typeface.DEFAULT_BOLD);
        pieData.setValueTextSize(18f);
        pieData.setValueTextColor(Color.WHITE);

        pieChartView.setData(pieData);
        pieChartView.setTransparentCircleAlpha(0);// to remove the shadow if the inner circle
        pieChartView.setRotationEnabled(true);// to rotate the pie chart
        pieChartView.setRotationAngle(250f); // Set the starting angle to 45 degrees

        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int) value+"(99%)";
            }
        });

        // Additional settings
        pieChartView.getDescription().setEnabled(false);
//        pieChartView.setDrawEntryLabels(false); // Disable labels outside the chart
        pieChartView.setDrawEntryLabels(true);
        pieChartView.setEntryLabelTextSize(16f);
        pieChartView.setEntryLabelColor(Color.WHITE);// Show labels outside the chart with lines pointing to the corresponding portions
        pieChartView.getLegend().setEnabled(false);// Disable the legend
//        pieChartView.getLegend().setFormLineColor(colors);
//        pieChartView.getLegend().setDrawInside(false);

        pieChartView.animate();
    }

    private ArrayList<BarEntry> getBarEntriesOne() {
        ArrayList barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1f, 220));
        barEntries.add(new BarEntry(2f, 200));
        barEntries.add(new BarEntry(3f, 180));
        barEntries.add(new BarEntry(4f, 162));
        barEntries.add(new BarEntry(5f, 142));
        barEntries.add(new BarEntry(6f, 120));
        barEntries.add(new BarEntry(7f, 100));
        return barEntries;
    }
    private ArrayList<BarEntry> getBarEntriesTwo() {
        ArrayList barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1f, 200));
        barEntries.add(new BarEntry(2f, 180));
        barEntries.add(new BarEntry(3f, 160));
        barEntries.add(new BarEntry(4f, 140));
        barEntries.add(new BarEntry(5f, 80));
        barEntries.add(new BarEntry(6f, 100));
        barEntries.add(new BarEntry(7f, 80));
        return barEntries;
    }
    private ArrayList<BarEntry> getBarEntriesThree() {
        ArrayList barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1f, 180));
        barEntries.add(new BarEntry(2f, 160));
        barEntries.add(new BarEntry(3f, 140));
        barEntries.add(new BarEntry(4f, 120));
        barEntries.add(new BarEntry(5f, 100));
        barEntries.add(new BarEntry(6f, 80));
        barEntries.add(new BarEntry(7f, 60));
        return barEntries;
    }
    private ArrayList<BarEntry> getBarEntriesFour() {
        ArrayList barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1f, 180));
        barEntries.add(new BarEntry(2f, 150));
        barEntries.add(new BarEntry(3f, 80));
        barEntries.add(new BarEntry(4f, 120));
        barEntries.add(new BarEntry(5f, 105));
        barEntries.add(new BarEntry(6f, 80));
        barEntries.add(new BarEntry(7f, 60));
        return barEntries;
    }
    private String[] generateDates(int length) {
        String[] dates = new String[length];
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMM", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        dates[0] = "";
        for (int i = 1; i < length; i++) {
            dates[i] = dateFormat.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1); // Adjust the interval as needed
        }
        return dates;
    }
    private ArrayList<Entry> generateRandomData(int length) {
        ArrayList<Entry> randomData = new ArrayList<>();
        Random random = new Random();
        randomData.add(new Entry(0,0));
        for (int i = 1; i < length; i++) {
            randomData.add(new Entry(i, random.nextInt(10))); // Adjust the range as needed
        }
        return randomData;
    }
    private ArrayList<BarEntry> getBarEntriesSingleValues() {
        ArrayList barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1f, 2));
        barEntries.add(new BarEntry(2f,-5 ));
        barEntries.add(new BarEntry(3f, 4));
        barEntries.add(new BarEntry(4f, -2));
        barEntries.add(new BarEntry(5f, 1));
        barEntries.add(new BarEntry(6f,3));
        barEntries.add(new BarEntry(7f,-3));
        return barEntries;
    }
    private LegendEntry createGapEntry() {
        //is used to set the space between the legends
        return new LegendEntry("", Legend.LegendForm.NONE, 0f, 0f, null, Color.TRANSPARENT);
    }
    private void addRequiredLayout(CardView cardViewP, LinearLayout linearLayoutP, LinearLayout textViewP) {
        cvparent = cardViewP;
        llFullView = linearLayoutP;
        tvfullview = textViewP;

        tvfullview.setVisibility(View.GONE);
        tvfullview.setClickable(false);
        scrollView.setVisibility(View.GONE);
        frame_layout_container.setVisibility(View.VISIBLE);
        LayoutInflater inflater = LayoutInflater.from(this);

        if (llFullView.getParent() != null) ((ViewGroup) llFullView.getParent()).removeView(llFullView);

        View requiredLayout = inflater.inflate(R.layout.fragment_custom, null);
        CardView cardView = requiredLayout.findViewById(R.id.cv_fs);
        LinearLayout ll_fs = requiredLayout.findViewById(R.id.ll_fs);
        ll_fs.setVisibility(View.VISIBLE);
        cardView.addView(llFullView);
        frame_layout_container.addView(requiredLayout);
        HeaderAndBottomNavigationGone();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    @Override
    public void onBackPressed() {
        if(false) super.onBackPressed();

        if (frame_layout_container.getChildCount() > 0) {
            if (llFullView.getParent() != null)  ((ViewGroup) llFullView.getParent()).removeView(llFullView); // <- fix
            HeaderAndBottomNavigationVisible();
            tvfullview.setVisibility(View.VISIBLE);
            tvfullview.setClickable(true);
            cvparent.addView(llFullView);
            scrollView.setVisibility(View.VISIBLE);
            frame_layout_container.setVisibility(View.GONE);
            frame_layout_container.removeAllViews();

//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else clickLogOut();
    }


}