package com.wanglj.wanglj2022.algorithm;

import java.util.Arrays;

public class ShellSort {


    public static void main(String[] args) {
        int[] arr = {1,2,6,9,10,1,4,3,2};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

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
