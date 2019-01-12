package com.zoo.design.decorator;

public class AICar extends SuperCar {

	public AICar(ICar car) {
		super(car);
	}
	
	public void autoDrive() {
		System.out.println("自动驾驶");
	}
	
	@Override
	public void run() {
		super.run();
		autoDrive();
	}

}
