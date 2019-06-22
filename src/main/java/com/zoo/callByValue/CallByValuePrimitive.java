package com.zoo.callByValue;

/**
 * java中的原始数据类型作为方法参数的按值调用
 * @author Devil
 */
public class CallByValuePrimitive {
	private static int x = 10;

	public static void updateValue(int value) {
		value = 3 * value;
	}

	public static void main(String[] args) {
		System.out.println("调用前x的值：" + x);
		updateValue(x);
		System.out.println("调用后x的值：" + x);
		/**
		 * 结果：
		 * 调用前x的值：10
		 * 调用后x的值：10
		 */
	}
}
