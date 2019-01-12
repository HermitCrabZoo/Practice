package com.zoo.design.observer;

/**
 * 目标
 * @author ZOO
 *
 */
public interface Subject {
	void register(Observer observer);
	void remove(Observer observer);
	void noticeAll();
	
	int getStatus();
	void setStatus(int status);
	
}
