package com.zoo.design.state;

/**
 * 酒店房间对象
 * @author ZOO
 *
 */
public class HotelRoom {
	private Status status;
	
	public void setStatus(Status status) {
		System.out.println("切换状态");
		this.status=status;
		this.status.handle();
	}
}
