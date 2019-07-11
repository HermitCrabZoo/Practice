package com.zoo.datastructure;

public class LinkedList<T> implements List<T> {

	private Node<T> head;

	private int size;

	private static class Node<T> {
		public T element;// 数据域
		public Node<T> next;// 地址域

		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
	}

	@Override
	public T get(int index) {
		if (this.head != null && index >= 0) {
			int count = 0;
			Node<T> p = this.head;
			// 找到对应索引的结点
			while (p != null && count < index) {
				p = p.next;
				count++;
			}

			if (p != null) {
				return p.element;
			}
		}
		return null;
	}

	@Override
	public T set(int index, T e) {
		if (index >= 0 && index < size && e != null) {
			// 查找需要替换的结点
			Node<T> x = this.head;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			// 直接替换
			T oldData = x.element;
			x.element = e;
			return oldData;
		}
		return null;
	}

	@Override
	public boolean add(int index, T e) {
		// 插入的情况索引可以是等于size
		if (index < 0 || index > size && e == null) {
			return false;
		}
		// 在头部插入
		if (size == 0 || index == 0) {
			this.head = new Node<T>(e, this.head);
		} else {
			// 在尾部或中间插入
			Node<T> x = this.head;
			// 找到要插入结点位置的前一个结点
			for (int i = 0, len = index - 1; i < len; i++) {
				x = x.next;
			}
			// 尾部添加和中间插入属于同种情况,毕竟当x为尾部结点时x.next=null
			// 当index==size时，x为尾部节点，x.next==null
			x.next = new Node<T>(e, x.next);
		}
		size++;
		return true;
	}

	@Override
	public boolean add(T e) {
		// 添加到最后
		return this.add(this.size, e);
	}

	@Override
	public T remove(int index) {
		T old = null;
		if (index >= 0 && index < size) {
			// 直接删除的是头结点
			if (index == 0) {
				old = this.head.element;
				this.head = this.head.next;
			} else {
				Node<T> prev = this.head;
				// 找到要插入结点位置的前一个结点
				for (int i = 0, len = index - 1; i < len; i++) {
					prev = prev.next;
				}
				// 获取到要删除的结点
				Node<T> curr = prev.next;
				old = curr.element;
				//前一个节点prev指向当前的下一个节点next
				prev.next = curr.next;
				//当前节点置空
				curr.element = null;
				curr.next=null;
			}
			size--;
		}
		return old;
	}

	@Override
	public boolean remove(T e) {
		// 先找到索引再删除
		int index = indexOf(e);
		if (index >= 0) {
			this.remove(index);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeAll(T e) {
		boolean done = false;
		if (size != 0 && e != null) {
			// 找到要插入结点位置的前一个结点
			for (Node<T> x = this.head,prev=null; x!=null;) {
				if(x.element.equals(e)) {
					if(x==head) {
						head = x.next;
					}else {
						prev.next = x.next;
					}
					x.element = null;
					x.next = null;
					size--;
				}else {
					prev = x;
					x=x.next;
				}
			}
		}
		return done;
	}

	@Override
	public void clear() {
		for (Node<T> x = head; x != null;) {
			Node<T> next = x.next;
			x.element = null;
			x.next = null;
			x = next;
		}
		head = null;
		size = 0;
	}

	@Override
	public boolean contains(T e) {
		return indexOf(e) >= 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int indexOf(T e) {
		int index = 0;
		for (Node<T> x = head; x != null; x = x.next) {
			if(x.element.equals(e)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T e) {
		return -1;
	}

}
