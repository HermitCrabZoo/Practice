package com.zoo.algorithm.sort;

import java.util.Arrays;

/**
 * 计数排序<br>
 * 稳定<br>
 * 数组大小：N，数组元素最大最小之间的差值+1：M，时间复杂度：O(N+M)、空间复杂度：O(M)。<br>
 * 线性排序算法。<br>
 */
public class CountSort {

    /**
     * 稳定版
     * @param array 原数组
     * @return 返回新数组
     */
    public static int[] countSort(int[] array) {
        //1.得到数列的最大值和最小值，并算出差值d
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int d = max - min;
        //2.创建统计数组并统计对应元素个数
        int[] countArray = new int[d + 1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }
        //3.统计数组做变形，后面的元素等于前面的元素之和
        int sum = 0;
        for (int i = 0; i < countArray.length; i++) {
            sum += countArray[i];
            countArray[i] = sum;
        }
        //4.倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[countArray[array[i] - min] - 1] = array[i];
            countArray[array[i] - min]--;
        }
        return sortedArray;
    }


    /**
     * 不稳定版本
     * @param array 原数组
     * @return 已排序结果数组
     */
    public static int[] countSortUnstable(int[] array) {
        //1.得到数列的最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        // 2.根据数列最大值确定统计数组的长度
        int[] countArray = new int[max + 1];
        // 3.遍历数列，填充统计数组
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]]++;
        }
        // 4.遍历统计数组，输出结果
        int index = 0;
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                sortedArray[index++] = i;
            }
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10};
        int[] sortedArray = countSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }
}
