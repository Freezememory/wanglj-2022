package com.wanglj.wanglj2022.study.algorithm;

import java.util.Arrays;


/**
 * @author Wanglj
 * @date 2023/4/2 14:34
 * @desc AlgorithmTest
 */
public class AlgorithmTest {

    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 9, 10, 1, 4, 3, 2};
        //List<Integer> integerList = Arrays.asList(1, 2, 6, 9, 10, 1, 4, 3, 2);
        //BucketSort.bucketSort(integerList, integerList.size());
        BubbleSort.bubbleSort(arr);
        //CountingSort.countingSort(arr);
        //HeapSort.heapSort(arr);
        //InsertionSort.insertionSort(arr);
        //MergeSort.mergeSort(arr);
        //RadixSort.radixSort(arr);
        //SelectionSort.selectionSort(arr);
        //ShellSort.shellSort(arr);
        //System.out.println(Arrays.toString(arr));


        int index = BinarySearch.binarySearch(arr, 1);
        System.out.println(index);
    }

}
