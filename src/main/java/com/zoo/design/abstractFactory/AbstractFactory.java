package com.zoo.design.abstractFactory;

public class AbstractFactory {
	
	//普通工厂难以产生产品系列:导致工厂泛滥，抽象工厂难以产生新"种类"产品:导致接口及实现类需要调整
	
	public static void main(String[] args) {
		Factory factory=new ManFactory();
//		factory=new MagicFactory();
		Vehicle v=factory.createVehicle();
		Weapon w=factory.createWeapon();
		Food f=factory.createFood();
		v.run();
		w.use();
		f.eat();
	}
}
