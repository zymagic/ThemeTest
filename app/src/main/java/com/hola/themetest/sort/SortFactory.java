package com.hola.themetest.sort;

import java.util.Random;

/**
 * Created by zy on 17-7-4.
 */

public class SortFactory {

    public static Sorter.Sort getSort(int id) {
        switch (id) {
            case 0: return new BubbleSort();
            case 1: return new BubbleSort2();
            case 2: return new SelectSort();
            case 3: return new InsertSort();
            case 4: return new BInsertSort();
            case 5: return new ShellSort();
            case 6: return new QuickSort();
            case 7: return new QuickSort2();
            case 8: return new MergeSort();
            case 9: return new BucketSort();
            case 10: return new StoogeSort();
        }
        return null;
    }

    public static void randomArray(int[] a) {
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < a.length; i++) {
            a[i] = i + 1;
        }
        for (int i = 0; i < a.length; i++) {
            int ri = r.nextInt(a.length - i) + i;
            int temp = a[i];
            a[i] = a[ri];
            a[ri] = temp;
        }
    }

    public static void sortArray(int[] a) {
        int step = a.length / 10;
        if (step < 5) {
            step = 5;
        }
        for (int i = 0; i < a.length; i++) {
//            a[i] = i + 1;
            a[i] = (i / step) * step +step - (i % step);
        }
//    	for (int i = 0; i < a.length - 1; i+=2) {
//    		int temp = a[i];
//    		a[i] = a[i + 1];
//    		a[i + 1] = temp;
//    	}
    }

    public static void reverseArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = a.length - i;
        }
    }

    private static class BubbleSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 1; j < a.length - i; j++) {
                    if (gt(a[j - 1], a[j])) {
                        swap(a, j - 1, j);
                    }
                }
            }
        }
    }

    private static class BubbleSort2 implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            int t;
            for (int i = 0; i < a.length; i++) {
                for (int j = i; j < a.length; j++) {
                    if (gt(a[i], a[j])) {
                        swap(a, i, j);
                    }
                }
            }
        }
    }

    private static class SelectSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            int min, mi;
            for (int i = 0; i < a.length; i++) {
                min = a[i];
                mi = i;
                for (int j = i; j < a.length; j++) {
                    if (st(a[j], min)) {
                        min = a[j];
                        mi = j;
                    }
                }
                if (eq(mi, i)) {
                    continue;
                }
                swap(a, i, mi);
            }
        }
    }

    private static class MergeSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            mergeSort(a, 0, a.length - 1, null);
        }

        public static void mergeSort2(int[] a, int s, int e, int[] ra) {
            if (ra == null) {
                ra = new int[e - s + 1];
            }
            if (ge(s, e)) {
                return;
            }
            if (eq(e - s, 1)) {
                if (gt(a[s], a[e])) {
                    swap(a, s, e);
                }
                return;
            }
            int ci = (s + e) / 2;
            mergeSort2(a, s, ci, ra);
            mergeSort2(a, ci + 1, e, ra);

            int li = s;
            int ri = ci + 1;
            for (int i = 0; i < e - s + 1; i++) {
                if (gt(ri, e) || se(li, ci) && st(a[li], a[ri])) {
                    ra[i] = a[li];
                    li++;
                } else {
                    ra[i] = a[ri];
                    ri++;
                }
            }
            System.arraycopy(ra, 0, a, s, e - s + 1);
            onSwap();
        }

        private void mergeSort(int[] a, int s, int e, int[] ra) {
            if (ra == null) {
                ra = new int[e - s + 1];
            }
            if (ge(s, e)) {
                return;
            }
            if (eq(e - s, 1)) {
                if (gt(a[s], a[e])) {
                    swap(a, s, e);
                }
                return;
            }
            int ci = (s + e) / 2;
            mergeSort(a, s, ci, ra);
            mergeSort(a, ci + 1, e, ra);

            System.arraycopy(a, s, ra, 0, e - s + 1);
            onSwap();

            int li = 0;
            int ri = ci + 1 - s;
            for (int i = s; i <= e; i++) {
                if (gt(ri, e - s) || se(li, ci - s) && st(ra[li], ra[ri])) {
                    a[i] = ra[li];
                    li++;
                } else {
                    a[i] = ra[ri];
                    ri++;
                }
            }
        }
    }

    private static class ShellSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            int i, j, k, t;
            int n = a.length;
            k = n / 2;
            while (gt(k, 0)) {
                for (i = k; i < n; i++) {
                    t = a[i];
                    j = i - k;
                    while (ge(j, 0) && st(t, a[j])) {
                        a[j + k] = a[j];
                        j = j - k;
                    }
                    a[j + k] = t;
                }
                k /= 2;
            }
        }
    }

    private static class QuickSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            quickSort(a, 0, a.length - 1);
        }

        private void quickSort(int[] a, int s, int e) {
            if (ge(s, e)) {
                return;
            }
            int l = s;
            int r = e;
            int seed = a[s];
            while (st(l, r)) {
                for (;r > l; r--) {
                    if (st(a[r], seed)) {
                        a[l] = a[r];
                        ++l;
                        break;
                    }
                }
                for (;l < r; l++) {
                    if (ge(a[l], seed)) {
                        a[r] = a[l];
                        r--;
                        break;
                    }
                }
            }
            a[l] = seed;
            if (st(s, l - 1)) {
                quickSort(a, s, l - 1);
            }
            if (st(l + 1, e)) {
                quickSort(a, l + 1, e);
            }
        }
    }

    private static class QuickSort2 implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            qsort(a, 0, a.length - 1);
        }

        private void qsort(int[] a, int s, int e) {
            if (ge(s, e)) {
                return;
            }
            int seedIndex = e;
            int seed = a[seedIndex];
            int temp, si;
            for (int i = s; i < seedIndex; ) {
                if (gt(a[i], seed)) {
                    si = seedIndex - 1;
                    temp = a[si];
                    a[seedIndex] = a[i];
                    a[i] = temp;
                    a[si] = seed;
                    --seedIndex;
                } else {
                    i++;
                }
            }
            if (st(s, seedIndex - 1)) {
                qsort(a, s, seedIndex - 1);
            }
            if (st(seedIndex + 1, e)) {
                qsort(a, seedIndex + 1, e);
            }
        }
    }

    private static class InsertSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            for (int i = 1; i < a.length; i++) {
                for (int j = i; j > 0; j--) {
                    if (st(a[j], a[j - 1])) {
                        swap(a, j, j - 1);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private static class BInsertSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            int t, ii;
            for (int i = 1; i < a.length; i++) {
                t = a[i];
                if (ge(t, a[i - 1])) {
                    continue;
                }
                ii = bInsert(a, 0, i - 1, a[i]);
                if (st(ii, i)) {
                    System.arraycopy(a, ii, a, ii + 1, i - ii);
                    onSwap();
                    a[ii] = t;
                }
            }
        }

        private int bInsert(int[] a, int s, int e, int v) {
            if (eq(s, e)) {
                if (st(v, a[e])) {
                    return e;
                }
                return e + 1;
            }
            int ci = (s + e) / 2;
            if (st(v, a[ci])) {
                return bInsert(a, s, ci, v);
            } else {
                return bInsert(a, ci + 1, e, v);
            }
        }
    }

    private static class BucketSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            int max = a[0];
            for (int i = 1; i < a.length; i++) {
                if (st(max, a[i])) {
                    max = a[i];
                }
            }
            int[] bucket = new int[max + 1];
            for (int i = 0; i < a.length; i++) {
                bucket[a[i]] = a[i];
            }
            int j = 0;
            for (int i = 0; i < bucket.length && j < a.length; i++) {
                if (gt(bucket[i], 0)) {
                    a[j] = bucket[i];
                    j++;
                }
            }
        }
    }

    private static class StoogeSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            stoogeSort(a, 0, a.length - 1);
        }

        private void stoogeSort(int[] a, int s, int e) {
            if (ge(s, e)) {
                return;
            }
            if (gt(a[s], a[e])) {
                int temp = a[s];
                a[s] = a[e];
                a[e] = temp;
            }
            if (ge(s + 1, e)) {
                return;
            }
            int k = (e - s + 1) / 3;
            stoogeSort(a, s, e - k);
            stoogeSort(a, s + k, e);
            stoogeSort(a, s, e - k);
        }
    }

    private static void onCompare() {
        try {
            Thread.sleep(0, 50000);
        } catch (Exception e) {
            // ignore
        }
    }

    private static void onSwap() {
        try {
            Thread.sleep(0, 150000);
        } catch (Exception e) {
            // ignore
        }
    }

    private static boolean gt(int a, int b) {
        onCompare();
        return a > b;
    }

    private static boolean ge(int a, int b) {
        onCompare();
        return a >= b;
    }

    private static boolean st(int a, int b) {
        onCompare();
        return a < b;
    }

    private static boolean se(int a, int b) {
        onCompare();
        return a <= b;
    }

    private static boolean eq(int a, int b) {
        onCompare();
        return a == b;
    }

    private static void swap(int[] a, int i, int j) {
        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];
        onSwap();
    }
}
