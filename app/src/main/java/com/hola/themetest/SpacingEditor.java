package com.hola.themetest;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * Created by zy on 17-6-22.
 */

public class SpacingEditor extends ThemeEditor {

    private EditText hPadding, hGap, vStartPadding, vEndPadding, textSpacing, dockPadding, dockGap, folderGap, indicatorSpacing;

    @Override
    protected int getLayoutId() {
        return R.layout.spacing_edit_activity;
    }

    @Override
    protected void onSubmit() {
        if (!TextUtils.isEmpty(hPadding.getText().toString())) {
            mTheme.hPadding = Integer.parseInt(hPadding.getText().toString());
        }
        if (!TextUtils.isEmpty(hGap.getText().toString())) {
            mTheme.hGap = Integer.parseInt(hGap.getText().toString());
        }
        if (!TextUtils.isEmpty(vStartPadding.getText().toString())) {
            mTheme.vStartPadding = Integer.parseInt(vStartPadding.getText().toString());
        }
        if (!TextUtils.isEmpty(vEndPadding.getText().toString())) {
            mTheme.vEndPadding = Integer.parseInt(vEndPadding.getText().toString());
        }
        if (!TextUtils.isEmpty(textSpacing.getText().toString())) {
            mTheme.textSpacing = Integer.parseInt(textSpacing.getText().toString());
        }
        if (!TextUtils.isEmpty(dockPadding.getText().toString())) {
            mTheme.dockPadding = Integer.parseInt(dockPadding.getText().toString());
        }
        if (!TextUtils.isEmpty(dockGap.getText().toString())) {
            mTheme.dockGap = Integer.parseInt(dockGap.getText().toString());
        }
        if (!TextUtils.isEmpty(folderGap.getText().toString())) {
            mTheme.folderGap = Integer.parseInt(folderGap.getText().toString());
        }
        if (!TextUtils.isEmpty(indicatorSpacing.getText().toString())) {
            mTheme.indicatorSpacing = Integer.parseInt(indicatorSpacing.getText().toString());
        }
    }

    @Override
    protected void onLoaded() {
        if (mTheme.hPadding > 0) {
            hPadding.setText(Integer.toString(mTheme.hPadding));
        }
        if (mTheme.hGap > 0) {
            hGap.setText(Integer.toString(mTheme.hGap));
        }
        if (mTheme.vStartPadding > 0) {
            vStartPadding.setText(Integer.toString(mTheme.vStartPadding));
        }
        if (mTheme.vEndPadding > 0) {
            vEndPadding.setText(Integer.toString(mTheme.vEndPadding));
        }
        if (mTheme.textSpacing > 0) {
            textSpacing.setText(Integer.toString(mTheme.textSpacing));
        }
        if (mTheme.dockPadding > 0) {
            dockPadding.setText(Integer.toString(mTheme.dockPadding));
        }
        if (mTheme.dockGap > 0) {
            dockGap.setText(Integer.toString(mTheme.dockGap));
        }
        if (mTheme.folderGap > 0) {
            folderGap.setText(Integer.toString(mTheme.folderGap));
        }
        if (mTheme.indicatorSpacing > 0) {
            indicatorSpacing.setText(Integer.toString(mTheme.indicatorSpacing));
        }
    }

    @Override
    protected void onInflateView(View ret) {
        hPadding = $(ret, R.id.h_padding);
        hGap = $(ret, R.id.h_gap);
        vStartPadding = $(ret, R.id.v_start_padding);
        vEndPadding = $(ret, R.id.v_end_padding);
        textSpacing = $(ret, R.id.textSpacing);
        dockPadding = $(ret, R.id.dock_padding);
        dockGap = $(ret, R.id.dock_gap);
        folderGap = $(ret, R.id.folder_gap);
        indicatorSpacing = $(ret, R.id.indicator_spacing);
    }
}
