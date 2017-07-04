package com.hola.themetest.sort;

/**
 * Created by zy on 17-7-4.
 */

public class Sorter {

    protected int[] a;
    private Sort sort;
    private boolean end = false;

    public Sorter(int[] src, Sort sort) {
        this.a = src;
        this.sort = sort;
    }

    public void sort() {
        sort.sort(a);
        end = true;
    }

    public boolean ended() {
        return end;
    }

    public int[] getData() {
        return a;
    }

    public interface Sort {
        void sort(int[] a);
    }
}
