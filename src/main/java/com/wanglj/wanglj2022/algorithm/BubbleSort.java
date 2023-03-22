package com.wanglj.wanglj2022.algorithm;

import java.util.Arrays;

public class BubbleSort {


    public static void main(String[] args) {
        int[] arr = {1,2,6,9,10,1,4,3,2};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }




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
