package com.zoo.design.command;

/**
 * 具体命令类
 * @author ZOO
 *
 */
public class ConcreateOrder implements Order {

	private Receiver receiver;
	
	
	public ConcreateOrder(Receiver receiver) {
		super();
		this.receiver = receiver;
	}


	@Override
	public void execute() {
		
		//执行命令前后可以做一些操作
		receiver.action();
	}

}
