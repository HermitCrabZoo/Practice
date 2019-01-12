package com.zoo.design.command;

/**
 * 命令模式:将请求封装为对象，请求执行前后可以做些额外操作
 * @author ZOO
 *
 */
public class Command {

	public static void main(String[] args) {
		Receiver receiver=new Receiver();//命令执行者
		Order order=new ConcreateOrder(receiver);//命令对象
		Invoker invoker=new Invoker(order);//命令发起者
		
		invoker.call();
	}

}
