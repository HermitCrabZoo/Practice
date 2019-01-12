package com.zoo.algorithm.search;

import java.util.stream.IntStream;

import com.zoo.base.Arrs;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = IntStream.iterate(1, i -> i + 1).limit(10).toArray();
        // 遍历输出排序好的数组
        System.out.println("排好序的数组：" + Arrs.join(",", arr));
        int num = 7;
        System.out.println("要查找的数：" + num);
        int result = binarySearch(arr, num);
        if (result == -1) {
            System.out.println("你要查找的数不存在……");
        } else {
            System.out.println("你要查找的数存在，在数组中的位置是：" + result);
        }
    }

    // 二分查找算法
    public static int binarySearch(int[] arr, int num) {
        int low = 0;
        int upper = arr.length - 1;
        while (low <= upper) {
            int mid = (upper + low) / 2;
            if (arr[mid] < num) {
                low = mid + 1;
            } else if (arr[mid] > num) {
                upper = mid - 1;
            } else {
            	return mid;
            }
        }
        return -1;
    }
}
