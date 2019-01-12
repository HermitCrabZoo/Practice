package com.zoo.design.memento;

import java.util.Stack;

public class Notepad {
	private Stack<Snapshot> increases=new Stack<>();
	private Stack<Snapshot> decreases=new Stack<>();
	
	public void save(Snapshot snapshot) {
		increases.push(snapshot);
	}
	
	/**
	 * 相当于ctrl+z
	 * @return
	 */
	public Snapshot back() {
		if (!increases.isEmpty()) {
			Snapshot sp=increases.pop();
			decreases.push(sp);
			return sp;
		}
		
		return null;
	}
	
	/**
	 * 相当于ctrl+y
	 * @return
	 */
	public Snapshot forward() {
		if (!decreases.isEmpty()) {
			Snapshot sp=decreases.pop();
			increases.push(sp);
			return sp;
		}
		return null;
	}
	
}
