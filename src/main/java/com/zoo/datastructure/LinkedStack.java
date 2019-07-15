package com.zoo.datastructure;

import java.io.Serializable;
import java.util.EmptyStackException;


public class LinkedStack<T> implements Stack<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6346231848374473085L;
	
	/**
	 * 栈链表节点
	 *
	 * @param <T>
	 */
	private static class Node<T> {
		public T element;// 数据域
		public Node<T> next;// 地址域

		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
	}

	/**
	 * 栈顶元素
	 */
	private Node<T> top;
	
	/**
	 * 栈元素个数
	 */
    private int size;

    public LinkedStack(){
    }
	
	@Override
	public boolean isEmpty() {
		return size == 0 ;
	}

	@Override
	public void push(T data) {
		top=new Node<>(data,this.top);//更新栈顶
        size++;
	}

	@Override
	public T peek() {
		if(isEmpty()){
            throw new EmptyStackException();
        }
        return top.element;
	}

	@Override
	public T pop() {
		if(isEmpty()){
            throw new EmptyStackException();
        }
        T data=top.element;
        top=top.next;
        size--;
        return data;
	}

	@Override
	public int size() {
		return size;
	}

}
