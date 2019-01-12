package com.zoo.design.decorator;

/**
 * 装饰器模式:为了增加新功能的同时不导致类的过于膨胀，使增加新功能变得更灵活,使用组合代替继承。
 * @author ZOO
 *
 */
public class Decorator {

	public static void main(String[] args) {
		System.out.println("原始车辆");
		ICar car=new Car();//被装饰对象
		car.run();
		
		System.out.println("原始车辆添加了飞行能力");
		FlyCar flyCar=new FlyCar(car);
		flyCar.run();
		
		System.out.println("飞行车辆添加了潜水能力");
		WaterCar waterCar=new WaterCar(flyCar);
		waterCar.run();
		
		System.out.println("潜水车辆添加了AI");
		AICar aiCar=new AICar(waterCar);
		aiCar.run();
		
		
		
	}

}
