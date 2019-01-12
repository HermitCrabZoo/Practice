package com.zoo.design.observer;

/**
 * 观察者
 * @author ZOO
 *
 */
public interface Observer {
	void update(Subject subject);
	int getStatus();
	void setStatus(int status);
	
}
