package com.zoo.design.builder;

public interface TrainBuilder {
	/**
	 * 构建车厢
	 * @return
	 */
	Coach buildCoach();
	
	/**
	 * 构建轮组
	 * @return
	 */
	WheelSet buildWheelSet();
	
	/**
	 * 构建引擎
	 * @return
	 */
	Engine buildEngine();
}
