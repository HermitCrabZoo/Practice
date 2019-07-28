package com.zoo.datastructure;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

	private BinaryNode<T> root;

	private int size = 0;

	@Override
	public void insert(T data) {
		if (data == null)
			throw new RuntimeException("data can\'Comparable be null !");
		// 插入操作
		root = insert(data, root);
		size++;
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
		size--;
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
			if (x.right == null) {
				// 此时，用于替换被删节点的x节点是叶子节点，则需要从x的父节点开始往下查找并删除x节点，
				// 而二叉链表实现的树并不持有其父节点的引用，所以需要从当前被删节点的右边开始找到x节点。
				p.right = remove(p.data, p.right);
			} else {
				// 此时，用于替换被删节点p的节点x不是叶子节点，则直接将x.right的数据替换到x.data，然后再删除掉x.right即可。
				// 能如此干的原因在于：x是p.rigth下的最小节点，所以必然x.left==null，而x.right=?null不可知，所以还需要一个非null判断。
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
		int result;
		;
		while ((result = data.compareTo(current.data)) != 0) {
			// 更新父结点记录
			parent = current;

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
		// 如果要删除结点的右孩子与successor不相等,则执行如下操作(如果相等,则说明删除结点)
		if (successor != delNode.right) {
			// 此时successor为右子树中最小的元素，它没有左孩子，可能会有右孩子。
			// 且由于successor是其父节点的左孩子，所以将successor的右孩子赋给父节点的左孩子(successor的右孩子取代自己的位置)
			successorParent.left = successor.right;
			// 把中继结点的右孩子指向当前要删除结点的右孩子
			successor.right = delNode.right;
		}
		return successor;
	}

	@Override
	public String preOrder() {
		String sb = preOrder(root);
		if (sb.length() > 0) {
			// 去掉尾部","号
			sb = sb.substring(0, sb.length() - 1);
		}

		return sb;
	}

	/**
	 * 先根遍历
	 * 
	 * @param subtree
	 * @return
	 */
	private String preOrder(BinaryNode<T> subtree) {
		StringBuilder sb = new StringBuilder();
		if (subtree != null) {// 递归结束条件
			// 先访问根结点
			sb.append(subtree.data + ",");
			// 遍历左子树
			sb.append(preOrder(subtree.left));
			// 遍历右子树
			sb.append(preOrder(subtree.right));
		}
		return sb.toString();
	}

	/**
	 * 非递归的先根遍历
	 * 
	 * @return
	 */
	public String preOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		// 构建用于存放结点的栈
		LinkedStack<BinaryNode<T>> stack = new LinkedStack<>();
		BinaryNode<T> p = this.root;
		while (p != null || !stack.isEmpty()) {
			if (p != null) {
				// 访问该结点
				sb.append(p.data + ",");

				// 将已访问过的结点入栈
				stack.push(p);

				// 继续访问其左孩子，直到p为null
				p = p.left;

			} else { // 若p=null 栈不为空,则说明已沿左子树访问完一条路径, 从栈中弹出栈顶结点,并访问其右孩子
				p = stack.pop();// 获取已访问过的结点记录
				p = p.right;
			}

		}
		// 去掉最后一个逗号
		if (sb.length() > 0) {
			return sb.toString().substring(0, sb.length() - 1);
		} else {
			return sb.toString();
		}
	}

	@Override
	public String inOrder() {
		String sb = inOrder(root);
		if (sb.length() > 0) {
			// 去掉尾部","号
			sb = sb.substring(0, sb.length() - 1);
		}
		return sb;
	}

	/**
	 * 中根遍历
	 * 
	 * @return
	 */
	public String inOrder(BinaryNode<T> subtree) {
		StringBuilder sb = new StringBuilder();
		if (subtree != null) {// 递归结束条件
			// 先遍历左子树
			sb.append(inOrder(subtree.left));
			// 再遍历根结点
			sb.append(subtree.data + ",");
			// 最后遍历右子树
			sb.append(inOrder(subtree.right));
		}
		return sb.toString();
	}

	/**
	 * 非递归的中根遍历
	 * 
	 * @return
	 */
	public String inOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		// 构建用于存放结点的栈
		LinkedStack<BinaryNode<T>> stack = new LinkedStack<>();

		BinaryNode<T> p = this.root;

		while (p != null || !stack.isEmpty()) {
			while (p != null) {// 把左孩子都入栈,至到左孩子为null
				stack.push(p);
				p = p.left;
			}
			// 如果栈不为空,因为前面左孩子已全部入栈
			if (!stack.isEmpty()) {
				p = stack.pop();
				// 访问p结点
				sb.append(p.data + ",");
				// 访问p结点的右孩子
				p = p.right;
			}
		}

		if (sb.length() > 0) {
			return sb.toString().substring(0, sb.length() - 1);
		} else {
			return sb.toString();
		}
	}

	@Override
	public String postOrder() {
		String sb = postOrder(root);
		if (sb.length() > 0) {
			// 去掉尾部","号
			sb = sb.substring(0, sb.length() - 1);
		}

		return sb;
	}

	/**
	 * 后根遍历
	 * 
	 * @param subtree
	 * @return
	 */
	public String postOrder(BinaryNode<T> subtree) {
		StringBuilder sb = new StringBuilder();
		if (subtree != null) {// 递归结束条件
			// 先遍历左子树
			sb.append(postOrder(subtree.left));

			// 再遍历右子树
			sb.append(postOrder(subtree.right));

			// 最后遍历根结点
			sb.append(subtree.data + ",");
		}
		return sb.toString();
	}

	/**
	 * 非递归后根遍历
	 * 
	 * @return
	 */
	public String postOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		// 构建用于存放结点的栈
		LinkedStack<BinaryNode<T>> stack = new LinkedStack<>();

		BinaryNode<T> currentNode = this.root;
		BinaryNode<T> prev = this.root;

		while (currentNode != null || !stack.isEmpty()) {
			// 把左子树加入栈中,直到叶子结点为止
			while (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.left;
			}

			// 开始访问当前结点父结点的右孩子
			if (!stack.isEmpty()) {
				// 获取右孩子，先不弹出
				BinaryNode<T> right = stack.peek().right;
				// 先判断是否有右孩子或者右孩子是否已被访问过
				if (right == null || right == prev) {// 没有右孩子||右孩子已被访问过
					// 如果没有右孩子或者右孩子已被访问,则弹出父结点并访问
					currentNode = stack.pop();
					// 访问
					sb.append(currentNode.data + ",");
					// 记录已访问过的结点
					prev = currentNode;
					// 置空当前结点
					currentNode = null;
				} else {
					// 有右孩子,则开始遍历右子树
					currentNode = right;
				}
			}

		}
		// 去掉最后一个逗号
		if (sb.length() > 0) {
			return sb.toString().substring(0, sb.length() - 1);
		} else {
			return sb.toString();
		}
	}

	@Override
	public String levelOrder() {
		return null;
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

	/**
	 * 计算深度
	 * 
	 * @return
	 */
	@Override
	public int height() {
		return height(root);
	}

	/**
	 * 递归实现
	 * 
	 * @param subtree
	 * @return
	 */
	private int height(BinaryNode<T> subtree) {
		if (subtree == null) {
			return 0;
		} else {
			// 分别递归左右子树，获取最大值。
			int l = height(subtree.left);
			int r = height(subtree.right);
			return (l > r) ? (l + 1) : (r + 1);// 返回并加上当前层
		}
	}

	/**
	 * 计算节点数
	 * 
	 * @return
	 */
	public int count() {
		return count(root);
	}

	/**
	 * 递归实现：定义根节点root后，再用subtree实现递归
	 * 
	 * @param subtree
	 * @return
	 */
	private int count(BinaryNode<T> subtree) {
		if (subtree == null)
			return 0;
		else {
			// 左子树+当前+右子树
			// 对比汉诺塔:H(n)=H(n-1) + 1 + H(n-1)
			return count(subtree.left) + 1 + count(subtree.right);
		}
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
