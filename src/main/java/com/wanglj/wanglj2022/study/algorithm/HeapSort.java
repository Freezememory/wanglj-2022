package com.wanglj.wanglj2022.study.algorithm;

public class HeapSort {
    private static int heapLen;

    public static void heapSort(int[] arr) {
        heapLen = arr.length;
        for (int i = heapLen - 1; i >= 0; i--) {
            heapify(arr, i);
        }

        for (int i = heapLen - 1; i > 0; i--) {
            swap(arr, 0, heapLen - 1);
            heapLen--;
            heapify(arr, 0);
        }
    }

    private static void heapify(int[] arr, int idx) {
        int left = idx * 2 + 1, right = idx * 2 + 2, largest = idx;
        if (left < heapLen && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapLen && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != idx) {
            swap(arr, largest, idx);
            heapify(arr, largest);
        }
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
