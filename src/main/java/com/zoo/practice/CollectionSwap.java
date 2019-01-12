package com.zoo.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CollectionSwap {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		Collections.addAll(list, "one","two","three","four");//把后面的元素添加到list中
		Iterator<String> iterator=list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		Collections.swap(list, 0,2);//交换指定两个索引的元素
		iterator=list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			
		}
	}
}
