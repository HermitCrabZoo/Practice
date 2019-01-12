package com.zoo.design.builder;

public class BulletTrainAssembler implements TrainAssembler {

	private TrainBuilder trainBuilder;
	
	
	public BulletTrainAssembler(TrainBuilder trainBuilder) {
		super();
		this.trainBuilder = trainBuilder;
	}


	@Override
	public Train assemble() {
		Train train=new Train();
		train.setCoach(trainBuilder.buildCoach());
		train.setWheelSet(trainBuilder.buildWheelSet());
		train.setEngine(trainBuilder.buildEngine());
		return train;
	}

}
