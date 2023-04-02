package com.wanglj.wanglj2022.algorithm;

public class ShellSort {

    public static void shellSort(int[] arr) {
        int len = arr.length, tmp, j;
        for (int gap = len / 2; gap >= 1; gap = gap / 2) {
            for (int i = gap; i < len; i++) {
                tmp = arr[i];
                j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
        }
    }
}
