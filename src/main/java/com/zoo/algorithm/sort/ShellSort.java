package com.zoo.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序<br>
 * 一般的折半分组的方式时间复杂度是小于O(n^2)，极端情况下会比插入排序更慢。<br>
 * 于是，人们相继提出了很多种增量方式，其中最具代表性的是Hibbard增量和Sedgewick增量。<br>
 * Hibbard的增量序列如下：<pre>
 *     1，3，7，15......<br>
 *     通项公式 2^k-1<br>
 *     利用此种增量方式的希尔排序，最坏时间复杂度是O(n^(3/2))<br></pre>
 * Sedgewick的增量序列如下：<pre>
 *     1, 5, 19, 41, 109......<br>
 *     通项公式 9*4^k - 9*2^k + 1 或者 4^k - 3*2^k + 1<br>
 *     利用此种增量方式的希尔排序，最坏时间复杂度是O(n^(4/3))</pre>
 */
public class ShellSort {
    public static void sort(int[] array) {
        //希尔排序的增量
        int d = array.length;
        while (d > 1) {
            //使用希尔增量的方式，即每次折半
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < array.length; i = i + d) {
                    int temp = array[i];
                    int j;
                    for (j = i - d; j >= 0 && array[j] > temp; j = j - d) {
                        array[j + d] = array[j];
                    }
                    array[j + d] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
