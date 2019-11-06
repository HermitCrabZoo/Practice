package com.zoo.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序<br>
 * 稳定<br>
 * 元素个数：N，进行N-1轮排序，最坏情况下比较复制的次数分别是1、2、3、4...一直到N-1次，最坏时间复杂度：O(n^2),空间复杂度：O(1)
 */
public class InsertSort {
    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertValue = array[i];
            int j = i - 1;

            //从右向左比较元素的同时，进行元素复制
            for (; j >= 0 && insertValue < array[j]; j--) {
                array[j + 1] = array[j];
            }

            //insertValue的值插入适当位置
            array[j + 1] = insertValue;
        }
    }

    public static void main(String[] args) {
        int[] array = {12, 1, 3, 46, 5, 0, -3, 12, 35, 16};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
