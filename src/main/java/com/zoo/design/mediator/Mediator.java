package com.zoo.design.mediator;

/**
 * 中介者模式:解耦多个对象间的关系，每个对象都持有中介者的引用，都与中介者打交道
 * @author ZOO
 *
 */
public class Mediator {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Manager manager=new GeneralManager();
		
		Development development=new Development(manager);
		Market market=new Market(manager);
		Financial financial=new Financial(manager);
		
		development.selfAction();
		development.outAction();
	}

}
