package com.zoo.design.observer;

/**
 * 具体的观察者实现
 * @author ZOO
 *
 */
public class ConcreateObserver implements Observer {

	private int status;
	
	@Override
	public void update(Subject subject) {
		this.status=subject.getStatus();
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public void setStatus(int status) {
		this.status=status;
	}

}
