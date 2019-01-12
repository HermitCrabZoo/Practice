package com.zoo.algorithm.sort;

import java.util.stream.IntStream;

import com.zoo.base.Arrs;

public class BubbleSort {

	public static void main(String[] args) {
		int[] iarr = IntStream.iterate(1, i->Double.valueOf(Math.random()*1000).intValue()).limit(10).toArray();
		bubbleSort(iarr);
		System.out.println(Arrs.join(",", iarr));
	}
	
	/**
	 * 冒泡排序
	 * @param array
	 */
	public static void bubbleSort(int[] array) {
		int temp = 0;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}
}
