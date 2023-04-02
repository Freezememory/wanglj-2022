package com.wanglj.wanglj2022.algorithm;

import java.util.ArrayList;
import java.util.List;

//基数排序
public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr.length < 2) return;
        int maxVal = arr[0];//求出最大值
        for (int a : arr) {
            if (maxVal < a) {
                maxVal = a;
            }
        }
        int n = 1;
        while (maxVal / 10 != 0) {//求出最大值位数
            maxVal /= 10;
            n++;
        }

        for (int i = 0; i < n; i++) {
            List<List<Integer>> radix = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                radix.add(new ArrayList<>());
            }
            int index;
            for (int a : arr) {
                index = (a / (int) Math.pow(10, i)) % 10;
                radix.get(index).add(a);
            }
            index = 0;
            for (List<Integer> list : radix) {
                for (int a : list) {
                    arr[index++] = a;
                }
            }
        }
    }
}
