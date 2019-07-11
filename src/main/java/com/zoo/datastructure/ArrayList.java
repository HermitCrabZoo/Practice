package com.zoo.datastructure;

public class ArrayList<T> implements List<T> {

	private Object[] data = null;

	private int size;

	public ArrayList() {
		// 默认初始化为空数组
		this.data = new Object[0];
	}

	@SuppressWarnings("unchecked")
	public ArrayList(T... array) {
		if (array == null) {
			throw new NullPointerException("array can't be empty!");
		}
		// 创建对应容量的数组
		this.data = new Object[array.length];
		// 复制元素
		for (int i = 0; i < array.length; i++) {
			this.data[i] = array[i];
		}

		this.size = array.length;
	}


	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		if (index >= 0 && index < this.size)
			return (T) this.data[index];
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T set(int index, T e) {
		if (index >= 0 && index < this.size && e != null) {
			T old = (T) this.data[index];
			this.data[index] = e;
			return old;
		}
		return null;
	}

	@Override
	public boolean add(int index, T e) {
		if (e == null)
			return false;

		// 插入下标的容错判断,插入在最前面
		if (index < 0)
			index = 0;

		// 插入下标的容错判断,插入在最后面
		if (index > this.size)
			index = this.size;

		// 判断内部数组是否已满
		if (this.size == data.length) {
			// 把原数组赋值给临时数组
			Object[] temp = this.data;

			// 对原来的数组进行成倍拓容,并把原数组的元素复制到新数组
			this.data = new Object[temp.length * 2];

			// 先把原数组下标从0到index-1(即插入位置的前一个位置)复制到新数组
			for (int i = 0; i < index; i++) {
				this.data[i] = temp[i];
			}
		}

		// 从原数组的最后一个元素开始直到index位置,都往后一个位置
		// 最终腾出来的位置就是新插入元素的位置了
		for (int j = this.size - 1; j >= index; j--) {
			this.data[j + 1] = this.data[j];
		}
		// 插入新值
		this.data[index] = e;
		// 长度加一
		this.size++;
		// 插入成功
		return true;
	}

	@Override
	public boolean add(T e) {
		//添加到最后
		return this.add(this.size, e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T remove(int index) {
		if (this.size != 0 && index >= 0 && index < this.size) {
			// 记录删除元素的值并返回
			T old = (T) this.data[index];

			// 从被删除的元素位置开,其后的元素都依次往前移动
			for (int j = index; j < this.size - 1; j++) {
				this.data[j] = this.data[j + 1];
			}
			// 设置数组元素对象为空
			this.data[this.size - 1] = null;
			// 顺序表长度减1
			this.size--;
			return old;
		}
		return null;
	}

	@Override
	public boolean remove(T e) {
		//先找到索引再删除
		int index = indexOf(e);
		if(index >= 0) {
			this.remove(index);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeAll(T e) {
		boolean done = false;
		if (this.size != 0 && e != null) {
			int i = 0;
			while (i < this.size)
				// 找出数据相同的选项
				if (e.equals(this.data[i])) {
					this.remove(i);// 根据下标删除
					done = true;
				} else
					i++;// 继续查找
		}
		return done;
	}

	@Override
	public void clear() {
		//将每个元素置为null，size置为0
        for (int i = 0; i < size; i++)
            data[i] = null;
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
		if (e != null)
			for (int i = 0; i < this.size; i++) {
				// 相当则返回下标
				if (this.data[i].equals(e))
					return i;
			}
		return -1;
	}

	@Override
	public int lastIndexOf(T e) {
		if (e != null)
			for (int i = this.size - 1; i >= 0; i--)
				if (e.equals(this.data[i]))
					return i;
		return -1;
	}

}
