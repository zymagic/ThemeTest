package com.hola.themetest;

import android.graphics.RectF;
import android.util.DisplayMetrics;

import java.util.List;

/**
 * Created by zy on 17-6-22.
 */

public class Theme {
    public String packageName;
    public String versionName;
    public Integer versionCode;
    public String specification;
    public int defaultDensity = DisplayMetrics.DENSITY_XHIGH;
    public List<String> features;
    public List<String> requiredFeatures;
    public int encrypt = 0;
    public boolean requireValidation = true;

    public int fontColor;
    public int fontSize;
    public String fontFile;
    public String iconStyle;
    public int strokeWidth;
    public int strokeColor;
    public RectF iconBounds;
    public int iconSize;
    public int hPadding;
    public int hGap;
    public int textSpacing;
    public int vStartPadding;
    public int vEndPadding;
    public int dockPadding;
    public int dockGap;
    public int folderGap;
    public int indicatorSpacing;
}
