package com.zoo.datastructure;

import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Queue<T> {

	/**
	 * 队头节点
	 */
	private Node<T> front;

	/**
	 * 队尾节点
	 */
	private Node<T> rear;

	/**
	 * 队列大小
	 */
	private int size;

	/**
	 * 队列容量
	 */
	private int capacity;

	private static class Node<T> {
		public T element;// 数据域
		public Node<T> next;// 地址域

		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
	}

	public LinkedQueue() {
		this.capacity = Integer.MAX_VALUE;
	}

	public LinkedQueue(int capacity) {
		if (capacity < 1) {
			throw new ArrayIndexOutOfBoundsException("容量不能小于1");
		}
		// 初始化队列
		this.capacity = capacity;
	}

	/**
	 * data 入队,添加成功返回true,否则返回false,可扩容
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean add(T data) {
		if (data == null)
			throw new NullPointerException("The data can\'t be null");
		Node<T> q = new Node<>(data, null);
		if (isEmpty()) {// 空队列插入
			this.front = this.rear = q;
		} else {// 非空队列,尾部插入
			this.rear.next = this.rear = q;
		}
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
		if (size >= capacity)
			throw new IllegalArgumentException("The capacity of LinkedQueue has reached its:" + capacity);

		Node<T> q = new Node<>(data, null);
		if (isEmpty()) {// 空队列插入
			this.front = this.rear = q;
		} else {// 非空队列,尾部插入
			this.rear.next = q;
		}
		size++;
		return false;
	}

	/**
	 * 返回队头元素,不执行删除操作,若队列为空,返回null
	 * 
	 * @return
	 */
	@Override
	public T peek() {
		return isEmpty() ? null : front.element;
	}

	/**
	 * 返回队头元素,不执行删除操作,若队列为空,抛出异常:NoSuchElementException
	 * 
	 * @return
	 */
	@Override
	public T element() {
		if (isEmpty()) {
			throw new NoSuchElementException("The LinkedQueue is empty");
		}
		return front.element;
	}

	/**
	 * 出队,执行删除操作,返回队头元素,若队列为空,返回null
	 * 
	 * @return
	 */
	@Override
	public T poll() {
		if (this.isEmpty())
			return null;
		Node<T> p = this.front;
		T x = p.element;
		this.front = p.next;
		if (this.front == null)
			this.rear = null;
		p.element = null;
		p.next = null;
		size--;
		return x;
	}

	/**
	 * 出队,执行删除操作,若队列为空,抛出异常:NoSuchElementException
	 * 
	 * @return
	 */
	@Override
	public T remove() {
		if (isEmpty()) {
			throw new NoSuchElementException("The LinkedQueue is empty");
		}
		T x = this.front.element;
		this.front = this.front.next;
		if (this.front == null)
			this.rear = null;
		size--;
		return x;
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
		Node<T> next = front;
		while (next != null) {
			Node<T> n = next.next;
			next.element = null;
			next.next = null;
			next = n;
		}
	}

}
