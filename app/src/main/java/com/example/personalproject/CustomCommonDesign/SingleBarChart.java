package com.example.personalproject.CustomCommonDesign;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;

public class SingleBarChart {

    private final BarChart barChart;
    private final List<BarEntry> barEntries;
    private final List<String> xlabels;
    private int angle = 0, barColor = 0;
    private String label = "";
    private boolean isValueSelected = true;

    public SingleBarChart(BarChart chart, List<BarEntry> entries, List<String> xlabels) {
        this.barChart = chart;
        this.barEntries = entries;
        this.xlabels = xlabels;
    }

    public void setLabelRotationAngle(int angle) {
        this.angle = angle;
    }

    public void setbarlabel(String label) {
        this.label = label;
    }

    public void setbarColor(int label) {
        this.barColor = label;
    }

    public void clearBarChart() {
        barChart.clear();
    }

    public void isValueSelected(Boolean angle) {
        this.isValueSelected = angle;
    }

    public void createBarChart() {
        barChart.clear();

        CustomBarChartRender customBarChartRender = new CustomBarChartRender(barChart, barChart.getAnimator(), barChart.getViewPortHandler());
        customBarChartRender.setRadius(30);
        barChart.setRenderer(customBarChartRender);

        String[] labelsArray = xlabels.toArray(new String[0]);
        BarDataSet barDataSet = new BarDataSet(barEntries, label);

        barChart.setExtraOffsets(10, 0, 0, 15);
//        barChart.setExtraBottomOffset(5f);


        /*int actualEntryCount = barDataSet.getEntryCount();
        int desiredTotalEntries = 7;
        int numDummyEntries = Math.max(0, desiredTotalEntries - actualEntryCount);
        for (int i = 0; i < numDummyEntries; i++) {
            float dummyValue = 0f;
            BarEntry dummyEntry = new BarEntry(actualEntryCount + 1 + i, dummyValue);
            barDataSet.addEntry(dummyEntry);
//            barChart.setTouchEnabled(false);
        }*/

        BarData barData = new BarData(barDataSet);
//        barData.setBarWidth(.6f);

//        barDataSet.setColor(Color.parseColor("#077ADF"));
        barDataSet.setColor(barColor);
        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(10f);
        barDataSet.setDrawValues(true);

        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                /*if(value>0) return (int) value + "L";
                else return "";*/
                int qwe = (int) value;
                if (qwe > 0) {
                    if (isValueSelected) {
                        return qwe + "L";
                    } else {
                        return qwe + "";
                    }
                } else return "";
            }
        });

        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setXOffset(10f);
        legend.setYOffset(15f);
        legend.setEnabled(false);

        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setEnabled(false);

        YAxis leftYAxis = barChart.getAxisLeft();
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftYAxis.setDrawGridLines(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsArray));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setAxisMinimum(0.5f);
//        xAxis.setAxisMinimum(-1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);
//        xAxis.setLabelRotationAngle(315);
        xAxis.setLabelRotationAngle(angle);

        barChart.animateXY(1500, 1500);
        barChart.setData(barData);
        barChart.setVisibleXRangeMinimum(5);
        barChart.setVisibleXRangeMaximum(5);
        barChart.setDragEnabled(true);

        barChart.animate();
        barChart.getDescription().setEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setHighlightPerTapEnabled(false);
        barChart.setScaleEnabled(false); // Disable scaling
        barChart.setHighlightPerDragEnabled(false);
        barChart.setDrawValueAboveBar(false);
        barChart.invalidate();
    }
}

