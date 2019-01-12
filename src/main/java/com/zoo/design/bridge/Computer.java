package com.zoo.design.bridge;

public abstract class Computer {
	protected Brand brand;
	
	public Computer(Brand brand) {
		this.brand=brand;
	}
	
	public void boot() {
		brand.boot();
	}
}
