package com.zoo.design.observer;

/**
 * 观察者模式：将观察者注册到目标对象中，目标状态变化时通知观察者对象，jdk提供了默认的类Observable(目标/被观察者)、接口Observer(观察者)，用以便捷地实现观察者模式
 * @author ZOO
 *
 */
public class Client {

	public static void main(String[] args) {
		Subject subject=new ConcreateSubject();
		
		Observer observer1=new ConcreateObserver();
		Observer observer2=new ConcreateObserver();
		Observer observer3=new ConcreateObserver();
		
		subject.register(observer1);
		subject.register(observer2);
		subject.register(observer3);
		
		subject.setStatus(200);
		
		System.out.println(observer1.getStatus());
		System.out.println(observer2.getStatus());
		System.out.println(observer3.getStatus());
	}

}
