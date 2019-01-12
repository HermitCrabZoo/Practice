package com.zoo.design.mediator;

public class Development implements Department {

	private Manager manager;
	
	
	public Development(Manager manager) {
		super();
		this.manager = manager;
		manager.register("development", this);
	}

	@Override
	public void selfAction() {
		System.out.println("开发");
	}

	@Override
	public void outAction() {
		System.out.println("需要资金支持");
		manager.command("financial");
	}

}
