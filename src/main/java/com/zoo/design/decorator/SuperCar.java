package com.zoo.design.decorator;

/**
 * Decorator装饰器
 * @author ZOO
 *
 */
public class SuperCar implements ICar {

	protected ICar car;
	
	public SuperCar(ICar car) {
		super();
		this.car = car;
	}

	@Override
	public void run() {
		car.run();
	}

}
