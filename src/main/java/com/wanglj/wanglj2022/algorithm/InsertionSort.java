package com.wanglj.wanglj2022.algorithm;

import java.util.Arrays;

public class InsertionSort {


    public static void main(String[] args) {
        int[] arr = {1,2,6,9,10,1,4,3,2};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

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
