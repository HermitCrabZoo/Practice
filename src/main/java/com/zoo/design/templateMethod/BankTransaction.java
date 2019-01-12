package com.zoo.design.templateMethod;

public abstract class BankTransaction {
	public void takeNumber() {
		System.out.println("取号");
	}
	
	public abstract void transact();
	
	public void evaluate() {
		System.out.println("评价");
	}
	
	public final void process() {
		takeNumber();//先取号排队
		
		transact();//再办理业务
		
		evaluate();//最后评价
	}
	
}
