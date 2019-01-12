package com.zoo.design.decorator;

public class WaterCar extends SuperCar {

	public WaterCar(ICar car) {
		super(car);
	}
	
	public void swim() {
		System.out.println("水中游");
	}
	
	@Override
	public void run() {
		super.run();
		swim();
	}

}
