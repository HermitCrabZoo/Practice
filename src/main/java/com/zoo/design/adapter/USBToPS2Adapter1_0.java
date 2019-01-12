package com.zoo.design.adapter;

/**
 * 适配器adapter,以类继承的形式实现的适配器
 * @author ZOO
 *
 */
public class USBToPS2Adapter1_0 extends Keyboard implements USB {
	
	@Override
	public void connect() {
		super.join();
	}

}
