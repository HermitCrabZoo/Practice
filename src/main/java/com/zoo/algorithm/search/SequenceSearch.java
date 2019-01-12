package com.zoo.algorithm.search;

import java.util.stream.IntStream;

public class SequenceSearch {

	public static void main(String[] args) {
		int[] iarr = IntStream.iterate(1, i->i+1).limit(10).toArray();
		System.out.println(sequentialSearch(iarr, 8));
	}
	
	/**
	 * 顺序查找
	 * @param arr
	 * @param key
	 * @return
	 */
	public static int sequentialSearch(int[] arr, int key) {
		int index = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == key) {
				index = i;
				break;
			}
		}
		return index;
	}

}
