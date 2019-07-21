package com.zoo.datastructure;

import java.io.Serializable;

public interface Tree<T extends Comparable<?>> {

	/**
	 * 判空
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 二叉树的结点个数
	 * 
	 * @return
	 */
	int size();

	/**
	 * 返回二叉树的高度或者深度,即结点的最大层次
	 * 
	 * @return
	 */
	int height();

	/**
	 * 先根次序遍历
	 */
	String preOrder();

	/**
	 * 中根次序遍历
	 */
	String inOrder();

	/**
	 * 后根次序遍历
	 */
	String postOrder();

	/**
	 * 层次遍历
	 */
	String levelOrder();

	/**
	 * 将data 插入
	 * 
	 * @return
	 */
	void insert(T data);

	/**
	 * 删除
	 */
	void remove(T data);

	/**
	 * 查找最大值
	 * 
	 * @return
	 */
	T findMin();

	/**
	 * 查找最小值
	 * 
	 * @return
	 */
	T findMax();

	/**
	 * 根据值找到结点
	 * 
	 * @param data
	 * @return
	 */
	BinaryNode findNode(T data);

	/**
	 * 是否包含某个值
	 * 
	 * @param data
	 * @return
	 */
	boolean contains(T data) throws Exception;

	/**
	 * 清空
	 */
	void clear();

	static class BinaryNode<T extends Comparable<?>> implements Serializable {
		private static final long serialVersionUID = -6477238039299912313L;

		public BinaryNode<T> left;// 左结点

		public BinaryNode<T> right;// 右结点

		public T data;

		public BinaryNode(T data, BinaryNode left, BinaryNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public BinaryNode(T data) {
			this(data, null, null);

		}

		/**
		 * 判断是否为叶子结点
		 * 
		 * @return
		 */
		public boolean isLeaf() {
			return this.left == null && this.right == null;
		}
	}
}
