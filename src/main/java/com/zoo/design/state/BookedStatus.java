package com.zoo.design.state;

public class BookedStatus implements Status {

	@Override
	public void handle() {
		System.out.println("已预定");
	}

}
