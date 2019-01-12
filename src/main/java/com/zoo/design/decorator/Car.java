package com.zoo.design.decorator;

/**
 * ConcreateComponent具体构建角色(被装饰)
 * @author ZOO
 *
 */
public class Car implements ICar {

	@Override
	public void run() {
		System.out.println("地上跑");
	}

}
