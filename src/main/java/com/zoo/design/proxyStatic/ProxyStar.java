package com.zoo.design.proxyStatic;

public class ProxyStar implements Star {

	private Star trueStar;
	
	public ProxyStar(Star trueStar) {
		super();
		this.trueStar = trueStar;
	}

	@Override
	public void interview() {
		System.out.println("代理-面谈");
	}

	@Override
	public void sign() {
		System.out.println("代理-签约");
	}

	@Override
	public void performance() {
		//其余杂事自己做,表演让明星来
		trueStar.performance();
	}

	@Override
	public void gather() {
		System.out.println("代理-收款");
	}

}
