package com.zoo.design.abstractFactory;

public class ManFactory extends Factory {

	@Override
	public Vehicle createVehicle() {
		return new Truck();
	}

	@Override
	public Weapon createWeapon() {
		return new M4A1();
	}

	@Override
	public Food createFood() {
		return new Apple();
	}

}
