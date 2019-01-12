package com.zoo.design.abstractFactory;

public class MagicFactory extends Factory {

	@Override
	public Vehicle createVehicle() {
		return new Broom();
	}

	@Override
	public Weapon createWeapon() {
		return new MagicStick();
	}

	@Override
	public Food createFood() {
		return new Mushroom();
	}

}
