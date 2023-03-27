package com.wanglj.wanglj2022.algorithm;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {1,2,6,9,10,1,4,3,2};
        int[] ints = mergeSort(arr);
        System.out.println(Arrays.toString(ints));
    }
    public static int[] mergeSort(int[] arr) {
        int len = arr.length;
        if (len < 2) {
            return arr;
        }

        int mdlIdx = len / 2;
        int[] arrLeft = Arrays.copyOfRange(arr, 0, mdlIdx);
        int[] arrRight = Arrays.copyOfRange(arr, mdlIdx, len);
        return merge(mergeSort(arrLeft), mergeSort(arrRight));
    }

    private static int[] merge(int[] arrLeft, int[] arrRight) {
        int leftLen = arrLeft.length, rightLen = arrRight.length, leftIdx = 0, rightIdx = 0, idx = 0;
        int[] result = new int[leftLen + rightLen];
        while (leftIdx < leftLen && rightIdx < rightLen) {
            if (arrLeft[leftIdx] < arrRight[rightIdx]) {
                result[idx++] = arrLeft[leftIdx++];
            } else {
                result[idx++] = arrRight[rightIdx++];
            }
        }
        while (leftIdx < leftLen) {
            result[idx++] = arrLeft[leftIdx++];
        }
        while (rightIdx < rightLen) {
            result[idx++] = arrRight[rightIdx++];
        }
        return result;
    }
}
