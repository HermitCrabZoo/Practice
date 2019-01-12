package com.zoo.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//CopyOnWriteArrayList ：写入并复制
public class CopyOnWriteArrayListTest {


	public static void main(String[] args) {
		Runnable runner=new Cowal();
		
		for (int i = 0; i < 5; i++) {
			new Thread(runner).start();
		}
		
	}
	
}


class Cowal implements Runnable{
	
	//会发生并发修改异常
//	private static List<String> list=Collections.synchronizedList(new ArrayList<>());
	
	//每次添加操作时都会做复制，不适合做频繁添加操作，适合迭代(并发迭代)，比ArrayList迭代的效率高
	private static CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();
	
	static {
		list.add("AA");
		list.add("BB");
		list.add("CC");
	}
	
	@Override
	public void run() {
		Iterator<String> it=list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
			
			list.add("DD");
		}
		
	}
	
} 
