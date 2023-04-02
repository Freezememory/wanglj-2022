package com.wanglj.wanglj2022.study.algorithm;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            //boolean flag = true;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    //flag = false;
                }
            }
            /*if (flag) {
                break;
            }*/
        }
    }
}
