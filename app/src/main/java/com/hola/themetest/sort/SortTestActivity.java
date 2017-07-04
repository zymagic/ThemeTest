package com.hola.themetest.sort;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by zy on 17-7-4.
 */

public class SortTestActivity extends Activity {
    static int id = 0;
    static int ai = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        setContentView(v);
        int[] array = new int[360];
        if (ai == 1) {
            SortFactory.sortArray(array);
        } else if (ai == 2) {
            SortFactory.reverseArray(array);
        } else {
            SortFactory.randomArray(array);
        }
        Sorter.Sort sort = SortFactory.getSort(id);
        if (id == 8) {
            id = 0;
            ai++;
            if (ai > 2) {
                ai = 0;
            }
        } else {
            id++;
        }
        SorterDrawable sd = new SorterDrawable(new Sorter(array, sort));
        v.setBackgroundDrawable(sd);
    }
}
