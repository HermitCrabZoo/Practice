package com.zoo.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 自定义栈使用ArrayDeque双向队列
 * @author ZOO
 *
 * @param <E>
 */
public class IStack<E> {
	
	private Deque<E> deque;

	private int capacity;
	
	public IStack(int capacity) {
		this.capacity=Math.max(0, capacity);
		deque = new ArrayDeque<E>();
	}
	
	public boolean push(E e) {
		if (deque.size()>=capacity) {
			return false;
		}
		return deque.offerLast(e);
	}
	
	public E pop() {
		return deque.pollLast();
	}
	
	public E peek() {
		return deque.peekLast();
	}
	
	public int size() {
		return deque.size();
	}
	
}
