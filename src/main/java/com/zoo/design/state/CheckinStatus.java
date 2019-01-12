package com.zoo.design.state;

public class CheckinStatus implements Status {

	@Override
	public void handle() {
		System.out.println("已入住");
	}

}
