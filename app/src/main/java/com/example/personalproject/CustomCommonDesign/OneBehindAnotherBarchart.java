package com.example.personalproject.CustomCommonDesign;

import android.graphics.Color;
import android.graphics.Typeface;

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
import java.util.List;

public class OneBehindAnotherBarchart {
    final ArrayList<Integer> growPercentage;
    public ArrayList<BarEntry> entryBar1, entryBar2, entryBar3;
    BarChart barChart;
    List<BarEntry> barEntries1, barEntries2, barEntries3;
    List<String> xlabels;
    String label1, label2, label3;
    int barColor1, barColor2, barColor3, barPosition = 1;
    List<Integer> colors1, colors2, colors3;

    public OneBehindAnotherBarchart(BarChart chart, List<String> xlabels, ArrayList<Integer> percantage) {
        this.barChart = chart;
        this.xlabels = xlabels;
        this.growPercentage = percantage;
    }

    public void setDetails1(List<BarEntry> entries, String label, int color) {
        this.barEntries1 = entries;
        this.label1 = label;
        this.barColor1 = color;
    }

    public void setDetails2(List<BarEntry> entries, String label, int color) {
        this.barEntries2 = entries;
        this.label2 = label;
        this.barColor2 = color;
    }

    public void setDetails3(List<BarEntry> entries, String label, int color) {
        this.barEntries3 = entries;
        this.label3 = label;
        this.barColor3 = color;
    }

    public void textNeededBarPosition(int barPosition) {
        this.barPosition = barPosition - 1;
    }


    public void createBarChart() {
        entryBar1 = new ArrayList<>();
        entryBar2 = new ArrayList<>();
        entryBar3 = new ArrayList<>();

        colors1 = new ArrayList<>();
        colors2 = new ArrayList<>();
        colors3 = new ArrayList<>();

        if (growPercentage == null || growPercentage.size() <= 0) {
            for (int i = 0; i < barEntries1.size(); i++) {
                growPercentage.add(0);
            }
        }

        AboveAndCurveCustomBarChart aboveAndCurveCustomBarChart = new AboveAndCurveCustomBarChart(
                barChart.getContext(), barChart, barChart.getAnimator(), barChart.getViewPortHandler(), growPercentage);
        aboveAndCurveCustomBarChart.setRadius(20);
        aboveAndCurveCustomBarChart.textNeededBarPosition(barPosition);
        aboveAndCurveCustomBarChart.disaplayImage(false);
        aboveAndCurveCustomBarChart.aboveValueTextColor(Color.BLACK);
        barChart.setRenderer(aboveAndCurveCustomBarChart);

        if (barEntries3 != null) {
            threeBarCharts();
        } else {
            twoBarCharts();
        }

    }

    private void threeBarCharts() {
        for (int i = 0; i < barEntries1.size(); i++) {
            float e1v = barEntries1.get(i).getY();
            float e2v = barEntries2.get(i).getY();
            float e3v = barEntries3.get(i).getY();

            entryBar1.add(new BarEntry(i + 1, e1v));
            colors1.add(barColor1);
            if (e2v < e3v) {
//            if(false){
                entryBar2.add(new BarEntry(i + 1, e3v));
                entryBar3.add(new BarEntry(i + 1, e2v));
                colors2.add(barColor3);
                colors3.add(barColor2);
            } else {
                entryBar2.add(new BarEntry(i + 1, e2v));
                entryBar3.add(new BarEntry(i + 1, e3v));
                colors2.add(barColor2);
                colors3.add(barColor3);
            }
        }


        barChart.setExtraOffsets(0, 10, 0, 10);
        barChart.animateXY(1500, 1500);
        barChart.setHighlightFullBarEnabled(false);
        barChart.setHighlightPerTapEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setHighlightPerDragEnabled(false);

        BarDataSet barDataSet1 = new BarDataSet(entryBar1, label1);
//        barDataSet1.setColor(barColor1);
        barDataSet1.setColors(colors1);
        BarDataSet barDataSet2 = new BarDataSet(entryBar2, label2);
//        barDataSet2.setColor(barColor2);
        barDataSet2.setColors(colors2);
        BarDataSet barDataSet3 = new BarDataSet(entryBar3, label3);
//        barDataSet3.setColor(barColor3);
        barDataSet3.setColors(colors3);

        barDataSet1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value > 0) return (int) value + "";
                else return "";
            }
        });
        barDataSet2.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value > 0) return (int) value + "";
                else return "";
            }
        });
        barDataSet3.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value > 0) return (int) value + "";
                else return "";
            }
        });

        BarData data = new BarData(barDataSet1, barDataSet2, barDataSet3);
        data.setValueTextSize(12f);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);
        data.setValueTextColor(Color.WHITE);

        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setXOffset(5f);
        legend.setYOffset(5f);
        LegendEntry[] customEntries = new LegendEntry[]{
                new LegendEntry(label1, Legend.LegendForm.SQUARE, 10f, 2f, null, barColor1),
                new LegendEntry(label2, Legend.LegendForm.SQUARE, 10f, 2f, null, barColor2),
                new LegendEntry(label3, Legend.LegendForm.SQUARE, 10f, 2f, null, barColor3),
        };
        legend.setCustom(customEntries);
        legend.setTextSize(8f);

        String[] xLabels1 = xlabels.toArray(new String[0]);// Convert ArrayList to array

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabels1));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setAxisMinimum(0f);//by kush axis label
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(315);
        xAxis.setAxisMaximum(barEntries1.size());

        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setEnabled(false);

        YAxis leftYAxis = barChart.getAxisLeft();
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftYAxis.setDrawGridLines(false);

        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
//        idBarChart1.setTouchEnabled(false);//to remove zoom in option
        barChart.setDrawValueAboveBar(false);//to show value above bar
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMinimum(7);
        barChart.setVisibleXRangeMaximum(7);
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


//        float groupSpace = 1f;
//        float barSpace = -0.5f;
//        float barWidth = 0.7f;


        float groupSpace = 1f;
        float barSpace = -0.7f;
        float barWidth = 0.7f;
        data.setBarWidth(barWidth);
        barChart.groupBars(0, groupSpace, barSpace);
        barChart.invalidate();
    }

    private void twoBarCharts() {
        for (int i = 0; i < barEntries1.size(); i++) {
            float e1v = barEntries1.get(i).getY();
            float e2v = barEntries2.get(i).getY();

            if (e1v < e2v) {
//            if(false){
                entryBar1.add(new BarEntry(i + 1, e2v));
                entryBar2.add(new BarEntry(i + 1, e1v));
                colors1.add(barColor2);
                colors2.add(barColor1);
            } else {
                entryBar1.add(new BarEntry(i + 1, e1v));
                entryBar2.add(new BarEntry(i + 1, e2v));
                colors1.add(barColor1);
                colors2.add(barColor2);
            }
        }


        barChart.setExtraOffsets(0, 10, 0, 10);
        barChart.animateXY(1500, 1500);
        barChart.setHighlightFullBarEnabled(false);
        barChart.setHighlightPerTapEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setHighlightPerDragEnabled(false);

        BarDataSet barDataSet1 = new BarDataSet(entryBar1, label1);
        barDataSet1.setColors(colors1);
        BarDataSet barDataSet2 = new BarDataSet(entryBar2, label2);
        barDataSet2.setColors(colors2);


        barDataSet1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value > 0) return (int) value + "";
                else return "";
            }
        });
        barDataSet2.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value > 0) return (int) value + "";
                else return "";
            }
        });


        BarData data = new BarData(barDataSet1, barDataSet2);
        data.setValueTextSize(12f);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);
        data.setValueTextColor(Color.WHITE);

        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setXOffset(5f);
        legend.setYOffset(5f);
        LegendEntry[] customEntries = new LegendEntry[]{
                new LegendEntry(label1, Legend.LegendForm.SQUARE, 10f, 2f, null, barColor1),
                new LegendEntry(label2, Legend.LegendForm.SQUARE, 10f, 2f, null, barColor2),
        };
        legend.setCustom(customEntries);
        legend.setTextSize(8f);

        String[] xLabels1 = xlabels.toArray(new String[0]);// Convert ArrayList to array

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabels1));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setAxisMinimum(0f);//by kush axis label
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(315);
        xAxis.setAxisMaximum(barEntries1.size());

        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setEnabled(false);

        YAxis leftYAxis = barChart.getAxisLeft();
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftYAxis.setDrawGridLines(false);

        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
//        idBarChart1.setTouchEnabled(false);//to remove zoom in option
        barChart.setDrawValueAboveBar(false);//to show value above bar
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMinimum(7);
        barChart.setVisibleXRangeMaximum(7);

        float groupSpace = 1f;
        float barSpace = -0.7f;
        float barWidth = 0.7f;
        data.setBarWidth(barWidth);
        barChart.groupBars(0, groupSpace, barSpace);
        barChart.invalidate();
    }


}
