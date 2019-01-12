package com.zoo.practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapIterator {
	public static void main(String[] args) {
		//遍历map
		Map<String,String> map=new HashMap<String,String>();
		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");
		map.put("4", "four");
		Set<Map.Entry<String, String>> entrySet=map.entrySet();
		Iterator<Map.Entry<String, String>> iterator=entrySet.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey()+"--"+entry.getValue());
			
		}
	}
}
