package com.zoo.design.mediator;

public class Financial implements Department {

	private Manager manager;
	
	
	public Financial(Manager manager) {
		super();
		this.manager = manager;
		this.manager.register("financial", this);
	}

	
	@Override
	public void selfAction() {
		System.out.println("数钱");
	}

	@Override
	public void outAction() {
		System.out.println("汇报工作：钱多人少。");
	}

}
