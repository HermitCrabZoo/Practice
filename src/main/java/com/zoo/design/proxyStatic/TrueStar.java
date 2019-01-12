package com.zoo.design.proxyStatic;

public class TrueStar implements Star {

	@Override
	public void interview() {
		System.out.println("明星-面谈");
	}

	@Override
	public void sign() {
		System.out.println("明星-签约");
	}

	@Override
	public void performance() {
		System.out.println("明星-表演");
	}

	@Override
	public void gather() {
		System.out.println("明星-收钱");
	}

}
