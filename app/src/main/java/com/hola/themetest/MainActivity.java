package com.hola.themetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hola.themetest.sort.SortTestActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[] {
                "Sort", "spacing", "icon"
        }));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(MainActivity.this, SortTestActivity.class));
                    return;
                }
                position--;
                String theme = getSharedPreferences("theme", Context.MODE_PRIVATE).getString("theme", "");
                if (TextUtils.isEmpty(theme)) {
                    theme = ThemeGenerator.generateThemeName();
                    getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putString("theme", theme).apply();
                }
                Intent intent = new Intent(MainActivity.this, ThemeEditActivity.class);
                String fragment = null;
                switch (position) {
                    case 0:
                        fragment = SpacingEditor.class.getName();
                        break;
                    case 1:
                        fragment = IconEditor.class.getName();
                        break;
                }
                intent.putExtra("fragment", fragment);
                intent.putExtra("theme", theme);
                startActivity(intent);
            }
        });
    }
}
