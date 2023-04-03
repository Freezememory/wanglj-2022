package com.wanglj.wanglj2022.study.algorithm;

/**
 * @author Wanglj
 * @date 2023/4/3 22:08
 * @desc BinarySearch
 */
public class BinarySearch {


    /**
     * 二分查找
     * @param srcArray 源数组
     * @param des 目标元素
     * @return 如果找到则返回索引位置，找不到则返回-1
     */
    public static int binarySearch(int[] srcArray, int des) {
        //定义初始最小、最大索引
        int start = 0;
        int end = srcArray.length - 1;
        //确保不会出现重复查找，越界
        while (start <= end) {
            //计算出中间索引值  >>> 逻辑右移 也就是 int middle = (end + start)/2
            int middle = (end + start)>>>1 ;//防止溢出
            if (des == srcArray[middle]) {
                return middle;
                //判断下限
            } else if (des < srcArray[middle]) {
                end = middle - 1;
                //判断上限
            } else {
                start = middle + 1;
            }
        }
        //若没有，则返回-1
        return -1;
    }
}
