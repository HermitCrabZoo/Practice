package com.zoo.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序<br>
 * 不稳定<br>
 * 每一轮都选最小值到左侧，时间复杂度是O(n)，一共迭代n-1轮，所以时间复杂度是O(n^2)，空间复杂度是O(1)。
 */
public class SelectSort {
    public static void selectionSort(int[] array) {
        for (int i = 0, len = array.length; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                minIndex = array[minIndex] < array[j] ? minIndex : j;
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8, 30, 50, 1, 33, 24, 5, -4, 7, 0};
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
