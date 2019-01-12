package com.zoo.design.memento;

public class Snapshot {
	private String name;
	private int age;
	private String address;
	
	
	public Snapshot(Man man) {
		this.name=man.getName();
		this.age=man.getAge();
		this.address=man.getAddress();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
