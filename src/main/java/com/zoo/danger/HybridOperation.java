package com.zoo.danger;

public class HybridOperation {
	public void halfCatty() {
		short x=0;
		int i=123456;
		
		//将x+i以short类型来运算，产生溢出后的结果再 赋值给x;潜在的类型转换可能得不到想要的结果。
		x+=i;
		
		//x=x+i;无法编译，不能将int转换为short类型
		
		System.out.println(x);
	}
	public void eightOunce() {
		Object x="Eat";
		String target="Shit!";
		
		//x与target兼容(String是 Object的子类)，所以x连上target得到的字符串可以付给Object类型的对象x.
//		x=x+target;
		x+=target;
		System.out.println(x);
		
	}
	public static void main(String[] args) {
		HybridOperation hOperation=new HybridOperation();
		hOperation.halfCatty();
		hOperation.eightOunce();
	}
	
}
