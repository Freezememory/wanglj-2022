package com.wanglj.wanglj2022.study.algorithm;

public class InsertionSort {

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i], j = i;
            while (j > 0 && val < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = val;
        }
    }
}
