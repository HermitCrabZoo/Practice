package com.zoo.design.mediator;

import java.util.HashMap;
import java.util.Map;

public class GeneralManager implements Manager {

	private Map<String, Department> deptMap=new HashMap<String, Department>();
	
	@Override
	public void command(String departmentName) {
		deptMap.get(departmentName).selfAction();
	}

	@Override
	public void register(String departmentName, Department department) {
		deptMap.put(departmentName, department);
	}

}
