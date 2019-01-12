package com.zoo.design.builder;

public class Train {
	private Coach coach;
	private WheelSet wheelSet;
	private Engine engine;
	
	public void run() {
		System.out.println("列车行进中,请勿跳车!");
	}
	
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public WheelSet getWheelSet() {
		return wheelSet;
	}
	public void setWheelSet(WheelSet wheelSet) {
		this.wheelSet = wheelSet;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	@Override
	public String toString() {
		return "Train [coach=" + coach + ", wheelSet=" + wheelSet + ", engine=" + engine + "]";
	}
}

/**
 * 车厢
 * @author ZOO
 *
 */
class Coach{
	
	private String name;

	public Coach(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Coach [name=" + name + "]";
	}
	
}
/**
 * 轮组
 * @author ZOO
 *
 */
class WheelSet{
	
	private String name;
	
	public WheelSet(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Wheel [name=" + name + "]";
	}
	
}
/**
 * 引擎
 * @author ZOO
 *
 */
class Engine{
	
	private String name;
	
	public Engine(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Engine [name=" + name + "]";
	}
	
}