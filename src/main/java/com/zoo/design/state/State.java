package com.zoo.design.state;

/**
 * 状态模式
 * @author ZOO
 *
 */
public class State {
	public static void main(String[] args) {
		HotelRoom hr=new HotelRoom();
		//切换状态
		hr.setStatus(new FreeStatus());
		hr.setStatus(new BookedStatus());
	}
}
