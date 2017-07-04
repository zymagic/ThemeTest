package com.hola.themetest.sort;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by zy on 17-7-4.
 */

public class SorterDrawable extends Drawable {

    private Sorter sorter;
    private Paint mPaint;
    private float mStroke;
    private float mHeight;
    private boolean started = false;

    public SorterDrawable(Sorter s) {
        this.sorter = s;
        mPaint = new Paint();
        mPaint.setColor(0xff0000ff);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        int size = sorter.getData().length;
        mStroke = bounds.width() * 1.0f / size;
        mPaint.setStrokeWidth(mStroke);
        mHeight = bounds.height();
    }

    @Override
    public void draw(Canvas canvas) {
        int[] data = sorter.getData();
        Rect bounds = getBounds();
        for (int i = 0; i < data.length; i++) {
            canvas.drawLine((i + 0.5f) * mStroke, bounds.bottom, (i + 0.5f) * mStroke, bounds.bottom - mHeight * data[i] / data.length, mPaint);
        }
        if (!started) {
            started = true;
            startSort();
        }
        if (!sorter.ended()) {
            invalidateSelf();
        }
    }

    private void startSort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sorter.sort();
            }
        }).start();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
