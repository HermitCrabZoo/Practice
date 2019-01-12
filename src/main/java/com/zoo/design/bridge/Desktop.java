package com.zoo.design.bridge;

public class Desktop extends Computer {

	public Desktop(Brand brand) {
		super(brand);
	}
	
	@Override
	public void boot() {
		super.boot();
		System.out.println("台式电脑开机");
	}
}
