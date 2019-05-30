package com.zoo.enumeration;


interface bread{
    void eat();
}

interface sport{
    void run();
}

public enum EnumImplement implements bread ,sport{
	FOOD,
	SPORT
	;//分号分隔

	@Override
	public void run() {
		System.out.println("running");
	}

	@Override
	public void eat() {
		System.out.println("eating");
	}

}
