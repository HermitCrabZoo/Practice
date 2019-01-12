package com.zoo.design.command;

/**
 * 命令发起者
 * @author ZOO
 *
 */
public class Invoker {

	private Order order;

	public Invoker(Order order) {
		super();
		this.order = order;
	}
	
	public void call() {
		order.execute();
	}
	
}
