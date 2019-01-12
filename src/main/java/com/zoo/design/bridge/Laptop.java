package com.zoo.design.bridge;

public class Laptop extends Computer {

	public Laptop(Brand brand) {
		super(brand);
	}
	
	@Override
	public void boot() {
		super.boot();
		System.out.println("笔记本电脑开机");
	}
}
