package com.example.personalproject.CustomCommonDesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.renderer.HorizontalBarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

public class CustomHorizonatalOneSideBCRenderer extends HorizontalBarChartRenderer {
    Context context;
    private final RectF mBarShadowRectBuffer = new RectF();
    private int mRadius;
    private boolean imageDisplay = false;
    private int abovetextColor = 0;
    private int barPosition = 1;
    private final Paint myPaint;
    private ArrayList<Integer> myColors;
    private ArrayList<Integer> growPercentage;
    public CustomHorizonatalOneSideBCRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);

        this.myPaint = new Paint();
        this.myColors = myColors;
        this.context = context;
    }

    public void setRadius(int radius) {
        this.mRadius = radius;
    }

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

            float barWidth = barData.getBarWidth();
            float barWidthHalf = barWidth / 2.0f;
            float y;

            int i = 0;
            double count = Math.min(Math.ceil((int) (double) ((float) dataSet.getEntryCount() * phaseX)), dataSet.getEntryCount());
            while (i < count) {
                BarEntry e = dataSet.getEntryForIndex(i);

                y = e.getY();

                mBarShadowRectBuffer.top = y - barWidthHalf;
                mBarShadowRectBuffer.bottom = y + barWidthHalf;

                trans.rectValueToPixelHorizontal(mBarShadowRectBuffer);

                if (!mViewPortHandler.isInBoundsTop(mBarShadowRectBuffer.bottom)) {
                    i++;
                    continue;
                }

                if (!mViewPortHandler.isInBoundsBottom(mBarShadowRectBuffer.top))
                    break;

                mBarShadowRectBuffer.left = mViewPortHandler.contentLeft();
                mBarShadowRectBuffer.right = mViewPortHandler.contentRight();

                c.drawRoundRect(mBarShadowRectBuffer, mRadius, mRadius, mShadowPaint);
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

        boolean isSingleColor = dataSet.getColors().size() == 1;

        int j = 0;
        while (j < buffer.size()) {
//            int setDiableCustom=25;
            int setDiableCustom = 0;//still working on it.. by kush
            if (!mViewPortHandler.isInBoundsTop(buffer.buffer[j + 1]) ||
                    !mViewPortHandler.isInBoundsBottom(buffer.buffer[j + 3] - setDiableCustom) ||
                    !mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2]) ||
                    !mViewPortHandler.isInBoundsRight(buffer.buffer[j])) {
                j += 4;
                continue;
            }


            if (!mViewPortHandler.isInBoundsTop(buffer.buffer[j + 1])) {
                j += 4;
                continue;
            }

            if (!mViewPortHandler.isInBoundsBottom(buffer.buffer[j + 3]))
                break;

            if (isSingleColor) {
                mRenderPaint.setColor(dataSet.getColor());
            }

            if (dataSet.getGradientColor() != null) {
                GradientColor gradientColor = dataSet.getGradientColor();
                mRenderPaint.setShader(new LinearGradient(
                        buffer.buffer[j],
                        buffer.buffer[j + 3],
                        buffer.buffer[j],
                        buffer.buffer[j + 1],
                        gradientColor.getStartColor(),
                        gradientColor.getEndColor(),
                        Shader.TileMode.MIRROR));
            }

            if (dataSet.getGradientColors() != null) {
                mRenderPaint.setShader(new LinearGradient(
                        buffer.buffer[j],
                        buffer.buffer[j + 3],
                        buffer.buffer[j],
                        buffer.buffer[j + 1],
                        dataSet.getGradientColor(j / 4).getStartColor(),
                        dataSet.getGradientColor(j / 4).getEndColor(),
                        Shader.TileMode.MIRROR));
            }

            Path path = roundRectRight(new RectF(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3]),
                    mRadius, mRadius, false, true, true, false);
            c.drawPath(path, mRenderPaint);

            if (drawBorder) {
                Path borderPath = roundRectRight(new RectF(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3]),
                        mRadius, mRadius, false, true, true, false);
                c.drawPath(borderPath, mBarBorderPaint);
            }

            j += 4;
        }
    }

    private Path roundRectRight(RectF rect, float rx, float ry, boolean tl, boolean tr, boolean br, boolean bl) {
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

    public void getContextF(Context context) {
        this.context = context;
    }

    public void setGroupPercentage(ArrayList<Integer> growPercentage) {
        this.growPercentage = growPercentage;
    }

    public void disaplayImage(boolean imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    public void aboveValueTextColor(int textColor) {
        this.abovetextColor = textColor;
    }

    public void textNeededBarPosition(int barPosition) {
        this.barPosition = barPosition - 1;
    }

    @Override
    public void drawValues(Canvas c) {
        super.drawValues(c);
        int colorIndex = 0;
        int Listindex = 0;

        for (int i = 0; i < mChart.getBarData().getDataSetCount(); i++) {
            BarBuffer buffer = mBarBuffers[i];
            float left, right, top, bottom;

            for (int j = 0; j < buffer.buffer.length * mAnimator.getPhaseX(); j += 4) {
                left = buffer.buffer[j];
                right = buffer.buffer[j + 2];
                top = buffer.buffer[j + 1];
                bottom = buffer.buffer[j + 3];

                int valueAtIndex = 0;
                if (i == barPosition) {
                    int index = (int) buffer.buffer[j] % growPercentage.size();
//                    valueAtIndex = growPercentage.get(Listindex);
                    if (Listindex >= 0 && Listindex < growPercentage.size()) {
                        valueAtIndex = growPercentage.get(Listindex);
                        // Use the valueAtIndex as needed
                    }
                    Listindex++;

                    if (valueAtIndex != 0)
                        drawBitmapAndTextAboveBar(c, left, top, right, bottom, valueAtIndex);
                }
                colorIndex++;
            }
        }
    }

    private void drawBitmapAndTextAboveBar(Canvas c, float left, float top, float right, float bottom,
                                           int valueAtIndex) {

        int setDiableCustom = 0;
        if (mViewPortHandler.isInBoundsTop(top + setDiableCustom) && mViewPortHandler.isInBoundsBottom(bottom)
                && mViewPortHandler.isInBoundsLeft(left) &&
                mViewPortHandler.isInBoundsRight(right + setDiableCustom)) {
            int convertedValue = Math.abs(valueAtIndex);
            String customText = convertedValue + "%";

//            float centerX = ( right) ;
//            float centerY = bottom + 50f; // Adjust the vertical position as needed
            float centerX = right + 30f; // Adjust the horizontal position as needed
            float centerY = (top + bottom) / 2f + 15f;

            Paint textPaint = new Paint();
//            textPaint.setTextSize(30f);
            textPaint.setTextSize(25f);
            textPaint.setFakeBoldText(true);

//        if (abovetextColor != 0)
//            textPaint.setColor(abovetextColor);
//        else if (textColor != 0)
//            textPaint.setColor(textColor);
//        else textPaint.setColor(Color.BLACK);

            float textWidth = textPaint.measureText(customText);
            float textX = centerX - textWidth / 2f - 8f;
            float textY = centerY - 5f; // Adjust the vertical position of the text

            c.drawText(customText, textX, textY, textPaint);
        }
    }


    private void drawBitmapAndTextAboveBar1(Canvas c, float left, float top, float right, float bottom,
                                            int valueAtIndex) {

        int setDiableCustom = 0;
        if (mViewPortHandler.isInBoundsTop(top + setDiableCustom) && mViewPortHandler.isInBoundsBottom(bottom)
                && mViewPortHandler.isInBoundsLeft(left) &&
                mViewPortHandler.isInBoundsRight(right + setDiableCustom)) {

            Bitmap bitmap;
            int textColor = 0;

            int convertedValue = Math.abs(valueAtIndex);
            String customText = convertedValue + "%";

            textColor = Color.BLACK;
            float centerX = (left + right) / 2f;
            float centerY = bottom + 50f; // Adjust the vertical position as needed

            Paint textPaint = new Paint();
            textPaint.setTextSize(30f);
            textPaint.setFakeBoldText(true);

//            if (abovetextColor != 0)
//                textPaint.setColor(abovetextColor);
//            else if (textColor != 0)
//                textPaint.setColor(textColor);
//            else textPaint.setColor(Color.BLACK);

//            if (imageDisplay)
//                c.drawBitmap(bitmap, centerX - bitmap.getWidth() / 2f, centerY - bitmap.getHeight(), null);

            float textWidth = textPaint.measureText(customText);
            float textX = centerX - textWidth / 2f;
            float textY = centerY + 5f; // Adjust the vertical position of the text

            c.drawText(customText, textX, textY, textPaint);
        }
    }

}
