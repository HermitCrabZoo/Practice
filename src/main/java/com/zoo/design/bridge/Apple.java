package com.zoo.design.bridge;

public class Apple implements Brand {

	@Override
	public void boot() {
		System.out.print("Mac");
	}

}
