package com.zoo.design.state;

public class FreeStatus implements Status {

	@Override
	public void handle() {
		System.out.println("房间空闲");
	}

}
