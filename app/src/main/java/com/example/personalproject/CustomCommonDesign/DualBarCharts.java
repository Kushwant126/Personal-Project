package com.example.personalproject.CustomCommonDesign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.ScrollView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DualBarCharts {
    public BarChart barChart1,barChart2,barChart3;
    public ArrayList<BarEntry> barEntries1,barEntries2,barEntries3;
    public String label1,label2,label3,label4;
    public int growthColor,degrowthColor,barColor1,barColor2,barColor4;
    private float setAxisMaximumBC1=11;
    private List<String> xlabels;
    Context context;
    public ArrayList<Integer> barPercentage;
    HashMap<Float, Integer> valueCounts;
    boolean AQW=true;
    int valuecount=0;
    public ScrollView scrollView1,scrollView2;
    public DualBarCharts(Context context, BarChart singleBarchart, BarChart doubleBarChart, List<String> xlabels){
        this.context = context;
        this.barChart1 = singleBarchart;
        this.barChart2 = doubleBarChart;
        this.xlabels = xlabels;
    }
    public void setBarChart1(ArrayList<BarEntry> growthDegrowthList,String growth,String degrowth,int growthColor,int degrowthColor){
        this.barEntries1 = growthDegrowthList;
        this.label1 = growth;
        this.label2 = degrowth;
        this.growthColor = growthColor;
        this.degrowthColor = degrowthColor;
    }
    public void setBarChart2(ArrayList<BarEntry> BarEntries1,ArrayList<BarEntry> BarEntries2,String label3,String label4,int color1,int color2,
                             ArrayList<Integer> barPercentage){
        this.barEntries2 = BarEntries1;
        this.barEntries3 = BarEntries2;
        this.label3 = label3;
        this.label4 = label4;
        this.barColor1 = color1;
        this.barColor2 = color2;
        this.barPercentage = barPercentage;
    }
    public void ScrollView1(ScrollView scrollView1){
        this.scrollView1=scrollView1;
    }
    float growth=0,Doublebar=0,leftXvisibility=3.5f,rightXvisibility=0;
    float adjustleft=growth;
    public void newBarID(BarChart barChart){ this.barChart3=barChart;}
    float extraOffAdjustment=0;
    int i=1,positivehigh=0,negativehigh=0;
    boolean hasPositive = false;
    boolean hasNegative = false,hasPositiveNegative=false;
    float mostCommonValue = Float.MIN_VALUE;
    float highestValue = Float.MIN_VALUE;
    int maxCount = 0;
    int barEntrySize = 0; // Counter to track consecutive occurrences of the same mostCommonValue
    float maxVisibleRange =0f;
    float startY = 0;
    @SuppressLint("ClickableViewAccessibility")
    //createDualBarChartsPractice
    //createDualBarCharts
    public void createDualBarCharts(){
        barChart1.clear();barChart2.clear();
//        barChart3.clear();
        valueCounts = new HashMap<>();
        barChart1(barChart1);
        barChart2(barChart2);
    }

    public void barChart1(BarChart barChart) {
//        barChart.setTouchEnabled(false);
        barChart.clear();
        barChart.setExtraOffsets(10,0,0,15);

        CustomHorizontalBarChartRenderer customRenderer = new CustomHorizontalBarChartRenderer(barChart,barChart.getAnimator(), barChart.getViewPortHandler());
        customRenderer.setRadius(20);
        barChart.setRenderer(customRenderer);


        List<Integer> colors = new ArrayList<>();
        ArrayList<String> xLabels=new ArrayList<>();
        xLabels.add("");
        xLabels.addAll(xlabels);
        for (BarEntry entry : barEntries1) {
            int value=(int) entry.getY();
            if (value >= 0) colors.add(growthColor);
            else colors.add(degrowthColor);

            if(positivehigh <= value) positivehigh=value;
            if(negativehigh > value) negativehigh=value;
            if (value >= 0) hasPositive = true;
            else if (value < 0) hasNegative = true;
        }



        String[] labelsArray =xLabels.toArray(new String[0]);// Convert ArrayList to array

        BarDataSet barDataSet = new BarDataSet(barEntries1, "label");
        barDataSet.setColors(colors);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(8f);
        barDataSet.setDrawValues(true);
        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int) value + "%";
            }
        });

        int actualEntryCount=barEntries1.size();
        float dummyValue=0f;
        if (hasPositive && hasNegative) {
            if(positivehigh < 4) dummyValue=4f;
        } else if (hasPositive) dummyValue=-0f;
        else if (hasNegative) {
            if (negativehigh<-50) dummyValue=17f;
            else dummyValue=4f;
        }
        BarEntry dummyEntry = new BarEntry(actualEntryCount +1, dummyValue);
        barDataSet.addEntry(dummyEntry);

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        Legend legend = barChart.getLegend();
        legend.setYOffset(7f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        LegendEntry[] customEntries = new LegendEntry[]{
                new LegendEntry(label2, Legend.LegendForm.SQUARE,10f,2f, null,degrowthColor),
                new LegendEntry(label1, Legend.LegendForm.SQUARE,10f,2f, null,growthColor),
        };
        legend.setCustom(customEntries);
        legend.setTextSize(8f);

        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setEnabled(true);
        rightYAxis.setDrawLabels(false); // Hide labels on the right axis
        rightYAxis.setDrawAxisLine(true);
        rightYAxis.setAxisMinimum(0f);
        rightYAxis.setDrawGridLines(false);

        YAxis leftYAxis = barChart.getAxisLeft();
        leftYAxis.setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsArray));
        xAxis.setGranularity(1f);
        xAxis.setEnabled(true);// Hide the X-axis
        xAxis.setAxisMinimum(0.5f);
//        xAxis.setAxisMinimum(0f);
        xAxis.setGranularityEnabled(true);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setGridColor(Color.WHITE);
        xAxis.setAxisMaximum(barEntries2.size()+.5f);
//        xAxis.setLabelCount(barData.getEntryCount(), true); // Set the number of labels to be displayed
//        xAxis.setAxisMaximum(barEntries2.size());
//        barChart.setVisibleXRange(0, barEntries2.size()); // Show only the first 77 entries on the x-axis

        barChart.setVisibleXRangeMinimum(4);
        barChart.setVisibleXRangeMaximum(4);

        barChart.animate();
        barChart.animateXY(1500, 1500);
        barChart.setDragEnabled(true);
        barChart.getDescription().setEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setHighlightPerTapEnabled(false);
        barChart.setScaleEnabled(false); // Disable scaling
        barChart.setHighlightPerDragEnabled(false);
        barChart.setDrawValueAboveBar(false);
        barChart.invalidate();
    }
    public void barChart2(BarChart barChart) {
//        barChart.setTouchEnabled(false);
        barChart2.clear();
        barChart2.animateXY(1500,1500);
        barChart.setExtraOffsets(0,0,20,16);

        CustomHorizonatalOneSideBCRenderer customRenderer = new CustomHorizonatalOneSideBCRenderer(barChart,barChart.getAnimator(),barChart.getViewPortHandler());
        customRenderer.setRadius(20);
        customRenderer.setGroupPercentage(barPercentage);
        customRenderer.getContextF(context);
        customRenderer.textNeededBarPosition(1);
        barChart.setRenderer(customRenderer);

        float barSpace = 0.1f;
        float groupSpace = 0.1f;

        String[] xLabels1 = xlabels.toArray(new String[0]);// Convert ArrayList to array

        BarDataSet barDataSet1 = new BarDataSet(barEntries3, label3);
        barDataSet1.setColor(barColor2); // Set color for the second dataset
        BarDataSet barDataSet2 = new BarDataSet(barEntries2, label4);
        barDataSet2.setColor(barColor1); // Set color for the first dataset

        BarData data = new BarData(barDataSet1, barDataSet2);
        data.setValueTextSize(10f);
        data.setBarWidth(0.35f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);


        /*int actualEntryCount = barDataSet1.getEntryCount();
        int desiredTotalEntries = 5;// Define the desired number of total entries (including dummy entries)
        int numDummyEntries = Math.max(0, desiredTotalEntries - actualEntryCount);// Calculate the number of dummy entries needed
        for (int i = 0; i < numDummyEntries; i++) {// Add dummy entries if needed
            float dummyValue = 0f;
            BarEntry dummyEntry = new BarEntry(actualEntryCount +1+ i, dummyValue);
            barDataSet1.addEntry(dummyEntry);
            barDataSet2.addEntry(dummyEntry);
            barChart.setTouchEnabled(false);
        }*/

        barDataSet1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if(value>0) return (int) value+"";
                else return "";
            }
        });
        barDataSet2.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if(value>0) return (int) value+"";
                else return "";
            }
        });


        Legend legend = barChart.getLegend();
//        legend.setWordWrapEnabled(true);
        legend.setYOffset(7f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        LegendEntry[] customEntries = new LegendEntry[]{
                new LegendEntry(label3, Legend.LegendForm.SQUARE, 10f, 2f, null, barColor1),
                new LegendEntry(label4, Legend.LegendForm.SQUARE, 10f, 2f, null, barColor2),
        };
        legend.setCustom(customEntries);
        legend.setTextSize(8f);
//        barChart.getLegend().setEnabled(false);// to disable the legend

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabels1));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(barEntries2.size()); // Set the maximum based on the total number of entries

        YAxis leftYAxis = barChart.getAxisLeft();
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftYAxis.setTextColor(Color.BLACK);
        leftYAxis.setDrawGridLines(false);
        leftYAxis.setDrawLabels(false);
        leftYAxis.setDrawAxisLine(false);

        // Customize Y-axis (right)
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawAxisLine(true);
        rightAxis.setAxisMinimum(0f); // Set minimum value to 0
        rightAxis.setDrawGridLines(false); // Turn off grid lines

//        barChart.setExtraBottomOffset(10f);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
        barChart.animate();
        barChart.setDragEnabled(true);

        barChart.setVisibleXRangeMinimum(4);
        barChart.setVisibleXRangeMaximum(4);
        barChart.groupBars(0, groupSpace, barSpace);
        barChart.setDrawValueAboveBar(false);
        barChart.setDoubleTapToZoomEnabled(false);//to stop zoom on double tap
        barChart.setHighlightPerTapEnabled(false);// to disable the highlight on selection of the bar
        barChart.setHighlightFullBarEnabled(false);
        barChart.setHighlightPerDragEnabled(false);
        barChart.invalidate();
    }
}
