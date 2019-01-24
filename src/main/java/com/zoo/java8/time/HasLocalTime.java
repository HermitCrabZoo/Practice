package com.zoo.java8.time;

import java.time.Duration;
import java.time.LocalTime;

public class HasLocalTime {

	public static void main(String[] args) {
		HasLocalTime hasLocalTime = new HasLocalTime();
		hasLocalTime.testLocalTime();
	}

	public void testLocalTime() {
		// 关注与时分秒
		LocalTime time = LocalTime.now();
		System.out.println("当前时间：");
		System.out.println(time); // 22:30:01.512
		System.out.println(time.getHour()); // 22,时
		System.out.println(time.getMinute()); // 30，分
		System.out.println(time.getSecond()); // 01，秒

		LocalTime time2 = time.plusHours(3).plusMinutes(40);
		System.out.println(time2); // 02:10:01.512

		// 输出新闻联播的开始时间
		LocalTime localTime1 = LocalTime.of(19, 00);
		System.out.println("localTime1: " + localTime1);
		LocalTime localTime1PlusHour = localTime1.plusHours(2);// 加2小时
		System.out.println("localTime1PlusHour: " + localTime1PlusHour);
		LocalTime localTime1PlusMinute = localTime1.plusMinutes(2);// 加2分钟
		System.out.println("localTime1PlusMinute: " + localTime1PlusMinute);
		LocalTime localTime1PlusSecond = localTime1.plusSeconds(2);// 加2秒
		System.out.println("localTime1PlusSecond: " + localTime1PlusSecond);
		LocalTime localTime1PlusNano = localTime1.plusNanos(20);// 加20纳秒
		System.out.println("localTime1PlusNano: " + localTime1PlusNano);

		// 转化为字符串，再输出
		LocalTime localTime2 = LocalTime.parse("20:15:30");
		System.out.println("localTime2: " + localTime2);

		// 两个时间之间的间隔
		Duration duration = Duration.between(localTime1, localTime2);
		System.out.println("localTime1 ~ localTime2:" + duration);

		// 判断一个时间是否早与另一个时间
		System.out.println("localTime1 is before localTime2:" + localTime1.isBefore(localTime2));

	}
}
