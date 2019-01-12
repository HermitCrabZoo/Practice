package com.zoo.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		
		//测试自定义栈LIFO
		IStack<String> stack=new IStack<>(5);
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
		stack.push("F");//放不下：丢弃
		
		System.out.println("自定义栈的大小:"+stack.size());
		System.out.println("peek:"+stack.peek());//瞥一眼栈顶的元素，不弹出栈顶的元素
		
		String ele=null;
		while ((ele=stack.pop())!=null) {//从栈顶到栈底依次弹出元素
			System.out.println(ele);
		}
		
		
		//测试队列FIFO
		Queue<String> queue=new ArrayDeque<>();
		queue.offer("1");
		queue.offer("2");
		queue.offer("3");
		
		System.out.println("ArrayDeque大小："+queue.size());
		System.out.println("peek："+queue.peek());//瞥一眼队列头的元素，不拿出队头元素
		String e=null;
		while ((e=queue.poll())!=null) {//从队头到队尾依次拿出元素
			System.out.println(e);
		}
		
	}

}
