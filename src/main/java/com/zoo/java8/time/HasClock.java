package com.zoo.java8.time;

import java.time.Clock;

public class HasClock {
	public static void main(String[] args) {
		Clock clock = Clock.systemUTC();
		System.out.println("millis:"+clock.millis());//当前毫秒数
		System.out.println("Clock : " + clock);
		// Returns time based on system clock zone
		//当前时区
		Clock defaultClock = Clock.systemDefaultZone();
		System.out.println("Clock : " + defaultClock);
	}
}
