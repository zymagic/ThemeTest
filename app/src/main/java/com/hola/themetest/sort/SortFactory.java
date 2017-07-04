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
        for (int i = 0; i < a.length; i++) {
            a[i] = i + 1;
        }
    	for (int i = 0; i < a.length - 1; i+=2) {
    		int temp = a[i];
    		a[i] = a[i + 1];
    		a[i + 1] = temp;
    	}
    }

    public static void reverseArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = a.length - i;
        }
    }

    private static class BubbleSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            int t;
            for (int i = 0; i < a.length; i++) {
                for (int j = 1; j < a.length - i; j++) {
                    if (a[j - 1] > a[j]) {
                        t = a[j - 1];
                        a[j - 1] = a[j];
                        a[j] = t;
                        lag();
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
                    if (a[i] > a[j]) {
                        t = a[i];
                        a[i] = a[j];
                        a[j] = t;
                        lag();
                    }
                }
            }
        }
    }

    private static class SelectSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            int min, mi, t;
            for (int i = 0; i < a.length; i++) {
                min = a[i];
                mi = i;
                for (int j = i; j < a.length; j++) {
                    if (a[j] < min) {
                        min = a[j];
                        mi = j;
                    }
                    lag();
                }
                if (mi == i) {
                    continue;
                }
                t = a[i];
                a[i] = min;
                a[mi] = t;
                lag();
            }
        }
    }

    private static class MergeSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            mergeSort2(a, 0, a.length - 1, null);
        }

        public static void mergeSort2(int[] a, int s, int e, int[] ra) {
            if (ra == null) {
                ra = new int[e - s + 1];
            }
            if (s >= e) {
                return;
            }
            if (e - s == 1) {
                if (a[s] > a[e]) {
                    int t = a[s];
                    a[s] = a[e];
                    a[e] = t;
                    lag();
                }
                return;
            }
            int ci = (s + e) / 2;
            mergeSort2(a, s, ci, ra);
            mergeSort2(a, ci + 1, e, ra);

            int li = s;
            int ri = ci + 1;
            for (int i = 0; i < e - s + 1; i++) {
                if (ri > e || li <= ci && a[li] < a[ri]) {
                    ra[i] = a[li];
                    li++;
                } else {
                    ra[i] = a[ri];
                    ri++;
                }
                lag();
            }
            System.arraycopy(ra, 0, a, s, e - s + 1);
            lag();
        }

        private void mergeSort(int[] a, int s, int e, int[] ra) {
            if (ra == null) {
                ra = new int[e - s + 1];
            }
            if (s >= e) {
                return;
            }
            if (e - s == 1) {
                if (a[s] > a[e]) {
                    int t = a[s];
                    a[s] = a[e];
                    a[e] = t;
                    lag();
                }
                return;
            }
            int ci = (s + e) / 2;
            mergeSort(a, s, ci, ra);
            mergeSort(a, ci + 1, e, ra);

            System.arraycopy(a, s, ra, 0, e - s + 1);

            int li = 0;
            int ri = ci + 1 - s;
            for (int i = s; i <= e; i++) {
                if (ri > e - s + 1 || li <= ci - s && ra[li] < ra[ri]) {
                    a[i] = ra[li];
                    li++;
                } else {
                    a[i] = ra[ri];
                    ri++;
                }
                lag();
            }
        }
    }

    private static class ShellSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            int i, j, k, t;
            int n = a.length;
            k = n / 2;
            while (k > 0) {
                for (i = k; i < n; i++) {
                    t = a[i];
                    j = i - k;
                    while (j >= 0 && t < a[j]) {
                        a[j + k] = a[j];
                        j = j - k;
                        lag();
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
            if (s >= e) {
                return;
            }
            int l = s;
            int r = e;
            int seed = a[s];
            while (l < r) {
                for (;r > l; r--) {
                    if (a[r] < seed) {
                        a[l] = a[r];
                        ++l;
                        lag();
                        break;
                    }
                }
                for (;l < r; l++) {
                    if (a[l] >= seed) {
                        a[r] = a[l];
                        r--;
                        lag();
                        break;
                    }
                }
            }
            a[l] = seed;
            if (s < l - 1) {
                quickSort(a, s, l - 1);
            }
            if (l + 1 < e) {
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
            if (s >= e) {
                return;
            }
            int seedIndex = e;
            int seed = a[seedIndex];
            int temp, si;
            for (int i = s; i < seedIndex; ) {
                if (a[i] > seed) {
                    si = seedIndex - 1;
                    temp = a[si];
                    a[seedIndex] = a[i];
                    a[i] = temp;
                    a[si] = seed;
                    --seedIndex;
                } else {
                    i++;
                }
                lag();
            }
            if (s < seedIndex - 1) {
                qsort(a, s, seedIndex - 1);
            }
            if (seedIndex + 1 < e) {
                qsort(a, seedIndex + 1, e);
            }
        }
    }

    private static class InsertSort implements Sorter.Sort {

        @Override
        public void sort(int[] a) {
            int t;
            for (int i = 1; i < a.length; i++) {
                for (int j = i; j > 0; j--) {
                    if (a[j] < a[j - 1]) {
                        t = a[j];
                        a[j] = a[j - 1];
                        a[j - 1] = t;
                        lag();
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
                if (t >= a[i - 1]) {
                    continue;
                }
                ii = bInsert(a, 0, i - 1, a[i]);
                if (ii < i) {
                    System.arraycopy(a, ii, a, ii + 1, i - ii);
                    a[ii] = t;
                    lag();
                }
            }
        }

        private int bInsert(int[] a, int s, int e, int v) {
            if (s == e) {
                lag();
                if (v < a[e]) {
                    return e;
                }
                return e + 1;
            }
            int ci = (s + e) / 2;
            if (v < a[ci]) {
                return bInsert(a, s, ci, v);
            } else {
                return bInsert(a, ci + 1, e, v);
            }
        }
    }

    private static void lag() {
        try {
//            Thread.sleep(0, 500000);
            Thread.sleep(5);
        } catch (Exception e) {
            // ignore
        }
    }
}
