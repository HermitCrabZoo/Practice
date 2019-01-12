package com.zoo.design.mediator;

public interface Manager {
	void command(String departmentName);
	void register(String departmentName,Department department);
}
