package com.zoo.datastructure;

import java.io.Serializable;
import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3159883471515364912L;
	
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * 默认数组大小为10
	 */
	private int capacity = 10;

	/**
	 * 存放元素的数组
	 */
	private T[] array;

	/**
	 * 栈元素个数
	 */
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		array = (T[]) new Object[capacity];
	}

	@SuppressWarnings("unchecked")
	public ArrayStack() {
		array = (T[]) new Object[this.capacity];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void push(T data) {
		// 判断容量是否充足
		if (size >= array.length)
			ensureCapacity(size * 2 + 1);// 扩容
		// 从栈顶添加元素
		array[++size] = data;
	}

	@Override
	public T peek() {
		if (isEmpty())
			new EmptyStackException();
		return array[size - 1];
	}

	@Override
	public T pop() {
		if(isEmpty())
	         new EmptyStackException();
	     return array[--size];
	}

	public void ensureCapacity(int newCapacity) {
		// 如果需要拓展的容量比现在数组的容量还小,则无需扩容
		if (newCapacity < array.length)
			return;
		if (newCapacity >= MAX_ARRAY_SIZE)
			newCapacity = MAX_ARRAY_SIZE;
		array = Arrays.copyOf(array, newCapacity);
	}

	@Override
	public int size() {
		return size;
	}

}
