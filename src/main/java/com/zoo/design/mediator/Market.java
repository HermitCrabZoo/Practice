package com.zoo.design.mediator;

public class Market implements Department {

	private Manager manager;
	
	
	public Market(Manager manager) {
		super();
		this.manager = manager;
		this.manager.register("market", this);
	}

	
	@Override
	public void selfAction() {
		System.out.println("市场调研");
	}

	@Override
	public void outAction() {
		System.out.println("汇报工作");
	}

}
