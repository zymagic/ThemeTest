package com.hola.themetest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zy on 17-6-22.
 */

public class ThemeGenerator {

    public static Bitmap generatePreview() {
        int w = Config.dp2px(120);
        int h = w * 16 / 9;
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmp);
        canvas.drawColor(0xffffffff);
        Paint paint = new Paint();
        paint.setTextSize(w);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(0xff000000);
        Paint.FontMetrics fm = paint.getFontMetrics();
        float baseline = h / 2f - (fm.bottom + fm.top) / 2;
        canvas.drawText("T", w / 2f, baseline, paint);
        return bmp;
    }

    public static String generateThemeName() {
        return "zip_com.hola.launcher.theme.themetest_" + new SimpleDateFormat("yyyyMMddhhmmss").format(Calendar.getInstance().getTime());
    }

}
