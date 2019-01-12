package com.zoo.design.facade;

/**
 * 外观模式,客户无需了解系统内部运作,客户只需和门面打交道,让门面去操作系统
 * 迪米特法则(最少知识原则):一个软件实体尽可能少的与其他实体发生作用
 * @author ZOO
 *
 */
public class Facade {

	public static void main(String[] args) {
		new RegisterFacade().register();
	}

}
