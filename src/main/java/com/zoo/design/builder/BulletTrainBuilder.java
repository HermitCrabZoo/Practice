package com.zoo.design.builder;

public class BulletTrainBuilder implements TrainBuilder {

	@Override
	public Coach buildCoach() {
		return new Coach("CRH动车车厢");
	}

	@Override
	public WheelSet buildWheelSet() {
		return new WheelSet("CRH动车轮组");
	}

	@Override
	public Engine buildEngine() {
		return new Engine("CRH动车引擎");
	}

}
