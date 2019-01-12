package com.zoo.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标的实现
 * @author ZOO
 *
 */
public class ConcreateSubject implements Subject {

	private List<Observer> observers=new ArrayList<>();
	private int status;
	
	@Override
	public void register(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void noticeAll() {
		for (Observer obs : observers) {
			obs.update(this);
		}
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public void setStatus(int status) {
		this.status=status;
		noticeAll();
	}

}
