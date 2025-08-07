package com.example.personalproject.CustomCommonDesign;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.HorizontalBarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class CustomHorizontalBarChartRenderer extends HorizontalBarChartRenderer {
    private RectF mBarShadowRectBuffer = new RectF();
    private int mRadius;
    float x;

    public CustomHorizontalBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    public void setRadius(int radius) {  this.mRadius = radius; }
    boolean isPositive;
    float barWidth;
    @Override
    protected void drawDataSet(Canvas c, IBarDataSet dataSet, int index) {
        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());
        mBarBorderPaint.setColor(dataSet.getBarBorderColor());
        mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getBarBorderWidth()));
        mShadowPaint.setColor(dataSet.getBarShadowColor());
        boolean drawBorder = dataSet.getBarBorderWidth() > 0f;

        float phaseX = mAnimator.getPhaseX();
        float phaseY = mAnimator.getPhaseY();

        if (mChart.isDrawBarShadowEnabled()) {
            mShadowPaint.setColor(dataSet.getBarShadowColor());

            BarData barData = mChart.getBarData();

            barWidth = barData.getBarWidth();
            float barWidthHalf = barWidth / 2.0f;
            int i = 0;
            double count = Math.min(Math.ceil((int) (double) ((float) dataSet.getEntryCount() * phaseX)), dataSet.getEntryCount());
            while (i < count) {
                BarEntry e = dataSet.getEntryForIndex(i);

                x = e.getX() - barWidthHalf; // Adjust the x position based on the half of the bar width

                mBarShadowRectBuffer.left = x;
                mBarShadowRectBuffer.right = x + barWidth;

                trans.rectValueToPixel(mBarShadowRectBuffer);

                if (!mViewPortHandler.isInBoundsLeft(mBarShadowRectBuffer.right)) {
                    i++;
                    continue;
                }

                if (!mViewPortHandler.isInBoundsRight(mBarShadowRectBuffer.left))
                    break;

                mBarShadowRectBuffer.top = mViewPortHandler.contentTop();
                mBarShadowRectBuffer.bottom = mViewPortHandler.contentBottom();

                c.drawRoundRect(mBarRect, mRadius, mRadius, mShadowPaint);
                i++;
            }
        }

        BarBuffer buffer = mBarBuffers[index];
        buffer.setPhases(phaseX, phaseY);
        buffer.setDataSet(index);
        buffer.setInverted(mChart.isInverted(dataSet.getAxisDependency()));
        buffer.setBarWidth(mChart.getBarData().getBarWidth());
        buffer.feed(dataSet);
        trans.pointValuesToPixel(buffer.buffer);

        int j = 0;
        while (j < buffer.size()) {
            BarEntry e = dataSet.getEntryForIndex(j / 4);  // Add this line to get the BarEntry

            if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) {
                j += 4;
                continue;
            }

            if (!mViewPortHandler.isInBoundsRight(buffer.buffer[j])) {
                break;
            }

            mRenderPaint.setShader(null);
            isPositive = e.getY() >= 0;

            Path path;
            if (isPositive) {
                path = roundRectRight1(
                        new RectF(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3]),
                        mRadius, mRadius,
                        false, true, true, false
                );
//                drawTextAboveBar(c, dataSet, e, buffer.buffer[j], buffer.buffer[j + 2], buffer.buffer[j + 1], buffer.buffer[j + 3], true);
            } else {
                path = roundRectRight1(
                        new RectF(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3]),
                        mRadius, mRadius,
                        true, false, false, true
                );
//                drawTextAboveBar(c, dataSet, e, buffer.buffer[j], buffer.buffer[j + 2], buffer.buffer[j + 1], buffer.buffer[j + 3], false);
            }

            if (j / 4 < dataSet.getColors().size()) {
                int barColor = dataSet.getColors().get(j / 4);
                mRenderPaint.setColor(barColor);
            }

            c.drawPath(path, mRenderPaint);


            // Check if the label is visible, and set the visibility of the value accordingly
            /*if (isLabelVisible(buffer.buffer[j + 1], buffer.buffer[j + 3])) {
                drawValue(c, dataSet.getValueFormatter(), e.getY(), e, index, buffer.buffer[j], buffer.buffer[j + 2], buffer.buffer[j + 1], buffer.buffer[j + 3], dataSet.getValueTextSize());
            }*/
            j += 4;
        }
    }
    private Path roundRectRight1(RectF rect, float rx, float ry, boolean tl, boolean tr, boolean br, boolean bl) {
        float top = rect.top;
        float left = rect.left;
        float right = rect.right;
        float bottom = rect.bottom;
        Path path = new Path();
        if (rx < 0) rx = 0;
        if (ry < 0) ry = 0;
        float width = right - left;
        float height = bottom - top;
        if (rx > width / 2) rx = width / 2;
        if (ry > height / 2) ry = height / 2;
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));

        path.moveTo(left, top + ry);
        if (tl)
            path.rQuadTo(0, -ry, rx, -ry); // top-left corner
        else {
            path.rLineTo(0, -ry);
            path.rLineTo(rx, 0);
        }
        path.rLineTo(widthMinusCorners, 0);
        if (tr)
            path.rQuadTo(rx, 0, rx, ry); // top-right corner
        else {
            path.rLineTo(rx, 0);
            path.rLineTo(0, ry);
        }
        path.rLineTo(0, heightMinusCorners);

        // edited code
        if (br)
            path.rQuadTo(0, ry, -rx, ry); // bottom-right corner
        else {
            path.rLineTo(0, ry);
            path.rLineTo(-rx, 0);
        }

        path.rLineTo(-widthMinusCorners, 0);
        if (bl)
            path.rQuadTo(-rx, 0, -rx, -ry); // bottom-left corner
        else {
            path.rLineTo(-rx, 0);
            path.rLineTo(0, -ry);
        }

        path.rLineTo(0, -heightMinusCorners);

        path.close(); // Given close, last lineto can be removed.

        return path;
    }

}

