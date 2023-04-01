package com.wanglj.wanglj2022.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {

    public static void main(String[] args) {
        //int[] arr = {1,2,6,9,10,1,4,3,2};
        List<Integer> integerList = Arrays.asList(1, 2, 6, 9, 10, 1, 4, 3, 2);
        List<Integer> bucketSort = bucketSort(integerList, 8);
        System.out.println(bucketSort);
    }
    private static List<Integer> bucketSort(List<Integer> arr, int bucketSize) {
        int len = arr.size();
        if (len < 2 || bucketSize == 0) {
            return arr;
        }
        int minVal = arr.get(0), maxVal = arr.get(0);
        for (int i = 1; i < len; i++) {
            if (arr.get(i) < minVal) {
                minVal = arr.get(i);
            } else if (arr.get(i) > maxVal) {
                maxVal = arr.get(i);
            }
        }
        int bucketNum = (maxVal - minVal) / bucketSize + 1;

        List<List<Integer>> bucket = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            bucket.add(new ArrayList<>());
        }
        for (int val : arr) {
            int idx = (val - minVal) / bucketSize;
            bucket.get(idx).add(val);
        }
        for (int i = 0; i < bucketNum; i++) {
            if (bucket.get(i).size() > 1) {
                bucket.set(i, bucketSort(bucket.get(i), bucketSize / 2));
            }
        }

        List<Integer> result = new ArrayList<>();
        for (List<Integer> val : bucket) {
            result.addAll(val);
        }
        return result;
    }
}
