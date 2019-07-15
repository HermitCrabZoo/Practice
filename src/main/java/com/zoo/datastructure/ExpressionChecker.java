package com.zoo.datastructure;

public class ExpressionChecker {
	
	/**
	 * 检查表达式是否合法
	 * @param exp
	 * @return
	 */
	public static boolean isValid(String exp) {
		// 创建栈
		LinkedStack<String> stack = new LinkedStack<>();
		int i = 0;
		while (i < exp.length()) {
			char ch = exp.charAt(i);
			i++;
			switch (ch) {
			case '(':
				// 左括号直接入栈
				stack.push(ch + "");
				break;
			case ')':
				// 遇见右括号左括号直接出栈
				if (stack.isEmpty() || !stack.pop().equals("("))
					return false;
			}
		}
		// 最后检测是否为空,为空则检测通过
		if (stack.isEmpty())
			return true;
		else
			return false;
	}
}
