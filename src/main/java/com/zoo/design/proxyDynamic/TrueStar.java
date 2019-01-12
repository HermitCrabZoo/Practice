package com.zoo.design.proxyDynamic;

public class TrueStar implements Star {

	@Override
	public void performance() {
		System.out.println("明星-表演");
	}

	@Override
	public void eat() {
		System.out.println("明星-吃饭");
	}
	
}