package com.zoo.design.adapter;

/**
 * 适配器adapter,以聚合的形式实现的适配器,比继承的方式更灵活
 * @author ZOO
 *
 */
public class USBToPS2Adapter2_0 implements USB {
	
	private Keyboard kb;
	
	
	public USBToPS2Adapter2_0(Keyboard kb) {
		super();
		this.kb = kb;
	}

	@Override
	public void connect() {
		kb.join();
	}
}
