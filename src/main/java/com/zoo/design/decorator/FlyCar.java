package com.zoo.design.decorator;

/**
 * ConcreateDecorator具体装饰角色
 * @author ZOO
 *
 */
public class FlyCar extends SuperCar {

	public FlyCar(ICar car) {
		super(car);
	}
	
	public void fly() {
		System.out.println("天上飞");
	}
	
	@Override
	public void run() {
		super.run();
		fly();
	}
}
