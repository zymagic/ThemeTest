package com.hola.themetest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zy on 17-6-22.
 */

public class ThemeEditActivity extends Activity {

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class clz;
        try {
            clz = Class.forName(getIntent().getStringExtra("fragment"));
        } catch (Exception e) {
            e.printStackTrace();
            finish();
            return;
        }
        setContentView(R.layout.theme_edit_frame);
        String mTheme = getIntent().getStringExtra("theme");
        ((TextView)findViewById(R.id.title)).setText(mTheme);

        findViewById(R.id.title_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment instanceof ThemeEditor) {
                    ((ThemeEditor) fragment).submit();
                }
            }
        });

        try {
            fragment = (Fragment) clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
    }
}
