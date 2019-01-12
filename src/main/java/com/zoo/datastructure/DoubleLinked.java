package com.zoo.datastructure;

import java.util.Arrays;
import java.util.LinkedList;

public class DoubleLinked<T> {
	private Node<T> first;
	private Node<T> last;
	private int size;

	public void add(T value) {
		Node<T> node = new Node<T>();
		node.setValue(value);
		if (first == null) {
			first = node;
			last = node;
		} else {
			node.setPrevious(last);
			last.setNext(node);
			last = node;
		}
		size++;
	}

	public T get(int index) {
		indexCheck(index);
		return node(index).getValue();
	}

	public T remove(int index) {
		indexCheck(index);
		Node<T> n = node(index);
		Node<T> previous = n.getPrevious();
		Node<T> next = n.getNext();
		if(previous!=null){
			previous.setNext(next);
		}else {
			first=next;
		}
		if(next!=null){
			next.setPrevious(previous);
		}else{
			last=previous;
		}
		n.setPrevious(null);
		n.setNext(null);
		size--;
		return n.getValue();
	}

	public void add(int index, T value) {
		positionCheck(index);
		Node<T> n = node(index);
		Node<T> node = new Node<T>(null, value, n);
		if (n == null) {
			if(size>0){
				node.setPrevious(last);
				last.setNext(node);
				last=node;
			}else {
				first=node;
				last=node;
			}
		} else {
			if(index==0){
				first=node;
			}
			n.getPrevious().setNext(node);
			n.setPrevious(node);
		}
		size++;
	}

	public int size() {
		return size;
	}

	// 获取指定索引的节点
	private Node<T> node(int index) {
		Node<T> n = first;
		for (int i = 0; i < index; i++) {
			n = n.getNext();
		}
		return n;
	}

	// 检查缩影是否越界，若越界则抛出IndexOutOfBoundsException异常
	private void positionCheck(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}

	// 检查缩影是否越界，若越界则抛出IndexOutOfBoundsException异常
	private void indexCheck(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}

	@Override
	public String toString() {
		StringBuffer str=new StringBuffer();
		Node<T> n = first;
		while(n!=null){
			str.append(n.getValue()).append(",");
			n=n.getNext();
		}
		
		return "DoubleLinked ["+(str.length()>0?str.substring(0,str.length()-1):str)+"]";
	}

	public static void main(String[] args) {
		DoubleLinked<String> dLinked = new DoubleLinked<>();
		dLinked.add("aa");
		dLinked.add("bb");
		dLinked.remove(0);
		dLinked.add(0, "cc");
		System.out.println(dLinked.size);
		System.out.println(dLinked);

		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.add("a");
		linkedList.add("b");
		linkedList.add("b");
		linkedList.add(3, "mm");
		// System.out.println(linkedList.remove());
		System.out.println(linkedList);
		String[] aa = { "a", "b" };

		System.out.println(Arrays.toString(aa));
	}
}
