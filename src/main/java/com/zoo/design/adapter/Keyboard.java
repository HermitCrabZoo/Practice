package com.zoo.design.adapter;

/**
 * ps/2接口的键盘:被适配的对象adaptee，需要适配器才能连接到笔记本上
 * @author ZOO
 *
 */
public class Keyboard {
	
	public void join() {
		System.out.println("PS/2接口的键盘已连接到USB接口中");
	}
}
