package com.zoo.datastructure;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

	private BinaryNode<T> root;

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public int height() {
		return 0;
	}

	@Override
	public String preOrder() {
		return null;
	}

	@Override
	public String inOrder() {
		return null;
	}

	@Override
	public String postOrder() {
		return null;
	}

	@Override
	public String levelOrder() {
		return null;
	}

	@Override
	public void insert(T data) {
		if (data == null)
			throw new RuntimeException("data can\'Comparable be null !");
		// 插入操作
		root = insert(data, root);
	}

	/**
	 * 插入操作,递归实现
	 * 
	 * @param data
	 * @param p
	 * @return
	 */
	private BinaryNode<T> insert(T data, BinaryNode<T> p) {
		if (p == null) {
			// 当前节点为null则直接返回新对象做为当前节点
			return new BinaryNode<>(data, null, null);
		}

		// 比较插入结点的值，决定向左子树还是右子树搜索
		int compareResult = data.compareTo(p.data);

		if (compareResult < 0) {// 左
			p.left = insert(data, p.left);
		} else if (compareResult > 0) {// 右
			p.right = insert(data, p.right);
		} else {
			;// 已有元素就没必要重复插入了
		}
		return p;
	}

	@Override
	public void remove(T data) {

	}

	@Override
	public T findMin() {
		return null;
	}

	@Override
	public T findMax() {
		return null;
	}

	@Override
	public BinaryNode<T> findNode(T data) {
		return null;
	}

	@Override
	public boolean contains(T data) throws Exception {
		return false;
	}

	@Override
	public void clear() {

	}

}
