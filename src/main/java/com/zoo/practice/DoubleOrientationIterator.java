package com.zoo.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DoubleOrientationIterator {
	public static void main(String args[]){
		
		List<String> list=new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		ListIterator<String> iterator=list.listIterator();
		//顺序遍历
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		//反序遍历
		while(iterator.hasPrevious()){
			System.out.println(iterator.previous());
		}
		
	}
}
