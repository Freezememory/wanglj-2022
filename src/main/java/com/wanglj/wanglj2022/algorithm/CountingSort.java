package com.wanglj.wanglj2022.algorithm;

import java.util.Arrays;

public class CountingSort {

    public static void main(String[] args) {
        int[] arr = {1,2,6,9,10,1,4,3,2};
        countingSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void countingSort(int[] arr) {
        int len = arr.length;
        if (len < 2) return;
        int minVal = arr[0], maxVal = arr[0];
        for (int i = 1; i < len; i++) {
            if (arr[i] < minVal) {
                minVal = arr[i];
            } else if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }

        int[] countArr = new int[maxVal - minVal + 1];
        for (int val : arr) {
            countArr[val - minVal]++;
        }
        for (int arrIdx = 0, countIdx = 0; countIdx < countArr.length; countIdx++) {
            while (countArr[countIdx]-- > 0) {
                arr[arrIdx++] = minVal + countIdx;
            }
        }
    }
}
