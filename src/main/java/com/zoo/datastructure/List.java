package com.zoo.datastructure;

public interface List<T> {

	/**
	 * 获取元素
	 * 
	 * @param index
	 * @return
	 */
	T get(int index);

	/**
	 * 设置某个元素的值
	 * 
	 * @param index
	 * @param e
	 * @return
	 */
	T set(int index, T e);

	/**
	 * 根据index添加元素
	 * 
	 * @param index
	 * @param e
	 * @return
	 */
	boolean add(int index, T e);

	/**
	 * 添加元素
	 * 
	 * @param e
	 * @return
	 */
	boolean add(T e);

	/**
	 * 根据index移除元素
	 * 
	 * @param index
	 * @return
	 */
	T remove(int index);

	/**
	 * 根据e移除元素
	 * 
	 * @param e
	 * @return
	 */
	boolean remove(T e);

	/**
	 * 根据e移除元素
	 * 
	 * @param e
	 * @return
	 */
	boolean removeAll(T e);

	/**
	 * 清空链表
	 */
	void clear();

	/**
	 * 是否包含e元素
	 * 
	 * @param e
	 * @return
	 */
	boolean contains(T e);
	
	
	/**
	 * 判断链表是否为空
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 链表长度
	 * 
	 * @return
	 */
	int size();

	/**
	 * 根据值查询下标
	 * 
	 * @param e
	 * @return
	 */
	int indexOf(T e);

	/**
	 * 根据data值查询最后一个出现在顺序表中的下标
	 * 
	 * @param e
	 * @return
	 */
	int lastIndexOf(T e);

	/**
	 * 输出格式
	 * 
	 * @return
	 */
	String toString();
}
