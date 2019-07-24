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
		if (data == null)
			throw new RuntimeException("data can\'Comparable be null !");
		// 删除结点
		root = remove(data, root);
	}

	/**
	 * 分3种情况 1.删除叶子结点(也就是没有孩子结点) 2.删除拥有一个孩子结点的结点(可能是左孩子也可能是右孩子) 3.删除拥有两个孩子结点的结点
	 * 
	 * @param data
	 * @param p
	 * @return
	 */
	private BinaryNode<T> remove(T data, BinaryNode<T> p) {
		// 没有找到要删除的元素,递归结束
		if (p == null) {
			return p;
		}
		int compareResult = data.compareTo(p.data);
		if (compareResult < 0) {// 左边查找删除结点
			p.left = remove(data, p.left);
		} else if (compareResult > 0) {
			p.right = remove(data, p.right);
		} else if (p.left != null && p.right != null) {// 已找到结点并判断是否有两个子结点(情况3)
			// 中继替换，找到右子树中最小的元素并替换需要删除的元素值
			BinaryNode<T> x = findMin(p.right);
			p.data = x.data;
			// 移除用于替换的结点x(取代者)
			if(x.right==null) {
				//此时，用于替换被删节点的x节点是叶子节点，则需要从x的父节点开始往下查找并删除x节点，
				//而二叉链表实现的树并不持有其父节点的引用，所以需要从当前被删节点的右边开始找到x节点。
				p.right = remove(p.data, p.right);
			}else {
				//此时，用于替换被删节点p的节点x不是叶子节点，则直接将x.right的数据替换到x.data，然后再删除掉x.right即可。
				//能如此干的原因在于：x是p.rigth下的最小节点，所以必然x.left==null，而x.right=?null不可知，所以还需要一个非null判断。
				x.data = x.right != null ? x.right.data : null;
				x.right = null;
			}
		} else {
			// 拥有一个孩子结点的结点和叶子结点的情况
			p = (p.left != null) ? p.left : p.right;
		}

		return p;// 返回该结点
	}

	/**
	 * 非递归删除
	 * 
	 * @param data
	 */
	public T removeUnrecure(T data) {
		if (data == null) {
			throw new RuntimeException("data can\'Comparable be null !");
		}
		// 从根结点开始查找
		BinaryNode<T> current = this.root;
		// 记录父结点
		BinaryNode<T> parent = this.root;
		// 判断左右孩子的flag
		boolean isLeft = true;

		// 找到要删除的结点
		while (data.compareTo(current.data) != 0) {
			// 更新父结点记录
			parent = current;
			int result = data.compareTo(current.data);

			if (result < 0) {// 从左子树查找
				isLeft = true;
				current = current.left;
			} else if (result > 0) {// 从右子树查找
				isLeft = false;
				current = current.right;
			}
			// 如果没有找到,返回null
			if (current == null) {
				return null;
			}
		}

		// ----------到这里说明已找到要删除的结点

		// 删除的是叶子结点
		if (current.left == null && current.right == null) {
			if (current == this.root) {
				this.root = null;
			} else if (isLeft) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
		// 删除带有一个孩子结点的结点,当current的right不为null
		else if (current.left == null) {
			if (current == this.root) {
				this.root = current.right;
			} else if (isLeft) {// current为parent的左孩子
				parent.left = current.right;
			} else {// current为parent的右孩子
				parent.right = current.right;
			}
		}
		// 删除带有一个孩子结点的结点,当current的left不为null
		else if (current.right == null) {
			if (current == this.root) {
				this.root = current.left;
			} else if (isLeft) {// current为parent的左孩子
				parent.left = current.left;
			} else {// current为parent的右孩子
				parent.right = current.left;
			}
		}
		// 删除带有两个孩子结点的结点
		else {
			// 找到当前要删除结点current的右子树中的最小值元素
			BinaryNode<T> successor = findSuccessor(current);

			if (current == root) {
				this.root = successor;
			} else if (isLeft) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			// 把当前要删除的结点的左孩子赋值给successor
			successor.left = current.left;
		}
		return current.data;
	}

	/**
	 * 查找中继结点--右子树最小值结点
	 * 
	 * @param delNode 要删除的结点
	 * @return
	 */
	public BinaryNode<T> findSuccessor(BinaryNode<T> delNode) {
		BinaryNode<T> successor = delNode;
		BinaryNode<T> successorParent = delNode;
		BinaryNode<T> current = delNode.right;

		// 不断查找左结点,直到为空,则successor为最小值结点
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.left;
		}
		// 如果要删除结点的右孩子与successor不相等,则执行如下操作(如果相当,则说明删除结点)
		if (successor != delNode.right) {
			successorParent.left = successor.right;
			// 把中继结点的右孩子指向当前要删除结点的右孩子
			successor.right = delNode.right;
		}
		return successor;
	}

	@Override
	public T findMin() {
		if (isEmpty())
			throw new EmptyTreeException("BinarySearchTree is empty!");

		return findMin(root).data;
	}

	@Override
	public T findMax() {
		if (isEmpty())
			throw new EmptyTreeException("BinarySearchTree is empty!");
		return findMax(root).data;
	}

	/**
	 * 查找最小值结点
	 * 
	 * @param p
	 * @return
	 */
	private BinaryNode<T> findMin(BinaryNode<T> p) {
		if (p == null)// 结束条件
			return null;
		else if (p.left == null)// 如果没有左结点,那么t就是最小的
			return p;
		return findMin(p.left);
	}

	/**
	 * 查找最大值结点
	 * 
	 * @param p
	 * @return
	 */
	private BinaryNode<T> findMax(BinaryNode<T> p) {
		if (p == null)// 结束条件
			return null;
		else if (p.right == null)
			return p;
		return findMax(p.right);
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

	static class EmptyTreeException extends RuntimeException {
		public EmptyTreeException() {
		}

		public EmptyTreeException(String message) {
			super(message);
		}

		private static final long serialVersionUID = -4674912530725819641L;

	}

}
