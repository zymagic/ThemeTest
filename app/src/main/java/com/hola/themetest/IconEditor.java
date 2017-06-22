package com.hola.themetest;

import android.view.View;
import android.widget.EditText;

/**
 * Created by zy on 17-6-22.
 */

public class IconEditor extends ThemeEditor {
    EditText iconSize;

    @Override
    protected int getLayoutId() {
        return R.layout.icon_size_edit_activity;
    }

    @Override
    protected void onSubmit() {
        mTheme.iconSize = Integer.parseInt(iconSize.getText().toString());
    }

    @Override
    protected void onLoaded() {
        if (mTheme.iconSize > 0) {
            iconSize.setText(Integer.toString(mTheme.iconSize));
        }
    }

    @Override
    protected void onInflateView(View v) {
        iconSize = $(v, R.id.icon_size);
    }
}
