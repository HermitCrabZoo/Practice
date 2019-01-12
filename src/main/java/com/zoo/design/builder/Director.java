package com.zoo.design.builder;

public class Director {

	public static void main(String[] args) {
		TrainBuilder trainBuilder=new BulletTrainBuilder();
		TrainAssembler trainAssembler=new BulletTrainAssembler(trainBuilder);
		Train bulletTrain=trainAssembler.assemble();
		System.out.println(bulletTrain);
		bulletTrain.run();
	}

}
