package com.zoo.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序<br>
 * 二叉堆的节点下沉调整（downAdjust 方法）是堆排序算法的基础，这个调节操作本身的时间复杂度是多少呢？
 *
 * 假设二叉堆总共有n个元素，那么下沉调整的最坏时间复杂度就等同于二叉堆的高度，也就是O（logn）。
 *
 * 我们再来回顾一下堆排序算法的步骤：
 *
 * 1. 把无序数组构建成二叉堆。
 * 2. 循环删除堆顶元素，移到集合尾部，调节堆产生新的堆顶。
 *
 * 第一步，把无序数组构建成二叉堆，需要进行n/2次循环。每次循环调用一次 downAdjust 方法，所以第一步的计算规模是  n/2 * logn，时间复杂度 O（nlogn）。
 *
 * 第二步，需要进行n-1次循环。每次循环调用一次 downAdjust 方法，所以第二步的计算规模是 （n-1） * logn ，时间复杂度 O（nlogn）。
 *
 * 两个步骤是并列关系，所以整体的时间复杂度同样是 O（nlogn）。
 *
 * 空间复杂度是：O(1)
 */
public class HeapSort {
    /**
     * 下沉调整
     *
     * @param array       待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length      堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        // temp保存父节点值，用于最后的赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            // 如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex + 1 < length && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            // 如果父节点小于任何一个孩子的值，直接跳出
            if (temp >= array[childIndex])
                break;
            //无需真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 堆排序  * @param array     待调整的堆
     */
    public static void heapSort(int[] array) {
        // 1.把无序数组构建成二叉堆。
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
        System.out.println(Arrays.toString(array));
        // 2.循环删除堆顶元素，移到集合尾部，调节堆产生新的堆顶。
        for (int i = array.length - 1; i > 0; i--) {
            // 最后一个元素和第一元素进行交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // 下沉调整最大堆
            downAdjust(array, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
