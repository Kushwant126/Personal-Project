package com.example.personalproject.CustomCommonDesign;

import com.github.mikephil.charting.charts.BarChart;

public class BarChartConfig {
    public BarChart chart;
    public String label1;
    public String label2;
    public int color1;
    public int color2;
    public boolean isValueSelected = true;
    public boolean aboveImage = false;
    public int labelRotation = 0;

    public BarChartConfig(BarChart chart, String label1, int color1, String label2, int color2) {
        this.chart = chart;
        this.label1 = label1;
        this.color1 = color1;
        this.label2 = label2;
        this.color2 = color2;
    }

    public BarChartConfig withAboveImage(boolean enable) {
        this.aboveImage = enable;
        return this;
    }

    public BarChartConfig withLabelRotation(int angle) {
        this.labelRotation = angle;
        return this;
    }

    public BarChartConfig withValueSelected(boolean value) {
        this.isValueSelected = value;
        return this;
    }
}

