package com.wanglj.wanglj2022.algorithm;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {1,2,6,9,10,1,4,3,2};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int minVal = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[minVal] > arr[j]) {
                    minVal = j;
                }
            }
            if (minVal != i) {
                int tmp = arr[i];
                arr[i] = arr[minVal];
                arr[minVal] = tmp;
            }
        }
    }
}
