package com.zoo.danger;

public class Odd {
	
	//当i为负数时，返回值都为false
	public boolean isOddError(int i) {
		return i%2==1;
	}
	
	//将isOddError方法修改成反转比较含义即可
	public boolean isOddCorrect(int i) {
		//return (i&1)!=0;//性能更好
		return i%2!=0;
	}
	
	public static void main(String[] args) {
		Odd odd=new Odd();
		System.out.println(odd.isOddError(-1));
		System.out.println(odd.isOddCorrect(-1));
	}
}
