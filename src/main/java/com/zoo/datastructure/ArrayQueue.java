package com.zoo.datastructure;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * 数组实现的FIFO队列
 * @author Hermi
 *
 * @param <T>
 */
public class ArrayQueue<T> implements Queue<T>, Serializable{

	private static final long serialVersionUID = 8046490361295549601L;

	/**
	 * 默认初始化容量
	 */
	private static final int DEFAULT_CAPACITY = 10;
	/**
	 * 保存队列元素
	 */
	private T elementData[];
	
	/**
	 * 队头指针
	 */
	private int front;
	
	/**
	 * 队尾指针
	 */
	private int rear;
	
	/**
	 * 队列大小
	 */
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		elementData = (T[]) new Object[DEFAULT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
		if (capacity < 1) {
			throw new ArrayIndexOutOfBoundsException("容量不能小于1");
		}
		elementData = (T[]) new Object[capacity];
	}

	/**
	 * data 入队,添加成功返回true,否则返回false,可扩容
	 */
	@Override
	public boolean add(T data) {
		if (data == null)
			throw new NullPointerException("The data can\'t be null");
		// 判断是否满队
		if (isFilled()) {
			ensureCapacity(elementData.length * 2 + 1);
		}
		// 添加data
		elementData[this.rear] = data;
		// 更新rear指向下一个空元素的位置
		this.rear = (this.rear + 1) % elementData.length;
		size++;
		return true;
	}

	/**
	 * offer 方法可插入一个元素,这与add 方法不同， 该方法只能通过抛出未经检查的异常使添加元素失败。
	 * 而不是出现异常的情况，例如在容量固定（有界）的队列中 NullPointerException:data==null时抛出
	 * IllegalArgumentException:队满,使用该方法可以使Queue的容量固定
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean offer(T data) {
		if (data == null)
			throw new NullPointerException("The data can\'t be null");
		// 队满抛出异常
		if (isFilled()) {
			throw new IllegalArgumentException("The capacity of ArrayQueue has reached its maximum");
		}

		// 添加data
		elementData[this.rear] = data;
		// 更新rear指向下一个空元素的位置
		this.rear = (this.rear + 1) % elementData.length;
		size++;
		return true;
	}

	/**
	 * 返回队头元素,不执行删除操作,若队列为空,返回null
	 * 
	 * @return
	 */
	@Override
	public T peek() {
		if(isEmpty()) {
			return null;
		}
		return elementData[front];
	}

	/**
	 * 返回队头元素,不执行删除操作,若队列为空,抛出异常:NoSuchElementException
	 * 
	 * @return
	 */
	@Override
	public T element() {
		if (isEmpty()) {
			throw new NoSuchElementException("The ArrayQueue is empty");
		}
		return peek();
	}

	/**
	 * 出队,执行删除操作,返回队头元素,若队列为空,返回null
	 * 
	 * @return
	 */
	@Override
	public T poll() {
		if(isEmpty()) {
			return null;
		}
		T temp = elementData[this.front];
		elementData[front] = null;
		this.front = (this.front + 1) % elementData.length;
		size--;
		return temp;
	}

	/**
	 * 出队,执行删除操作,若队列为空,抛出异常:NoSuchElementException
	 * 
	 * @return
	 */
	@Override
	public T remove() {
		if (isEmpty()) {
			throw new NoSuchElementException("The ArrayQueue is empty");
		}
		return poll();
	}

	/**
	 * 当尺寸大于0且头尾索引相等时，队列满了。
	 * 
	 * @return
	 */
	private boolean isFilled() {
		return !isEmpty() && front == rear;
	}

	/**
	 * 扩容的方法
	 * 
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int capacity) {
		T[] old = elementData;
		elementData = (T[]) new Object[capacity];
		// 复制元素
		int s = size;
		for (int i = this.front, j = 0, len = old.length; j < s; i = (i + 1) % len, j++) {
			elementData[j] = old[i];
		}
		// 恢复front,rear指向
		this.front = 0;
		this.rear = s;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		//逐个元素置null
		int s = size;
		for (int i = this.front, j=0, len=elementData.length; j<s; i = (i + 1) % len, j++) {
			elementData[i] = null;
		}
		// 复位
		this.front = this.rear = this.size = 0;
	}

}
