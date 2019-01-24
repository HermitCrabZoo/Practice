package com.zoo.java8.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

public class HasZoneDateTime {
	public static void main(String[] args) {
		HasZoneDateTime hasZoneDateTime = new HasZoneDateTime();
		hasZoneDateTime.testZoneDateTime();
	}

	public void testZoneDateTime() {
		//所有时区
		Set<String> sets = ZoneId.getAvailableZoneIds();
		sets.stream().forEach(System.out::println);
		
		//新的java.time.ZoneId替代了老版本的java.util.TimeZone.
		// 纽约的时区，时区名称错误则抛异常
		ZoneId america = ZoneId.of("America/New_York");
		LocalDateTime localDateTime1 = LocalDateTime.now();
		ZonedDateTime dateTimeInNewYork = ZonedDateTime.of(localDateTime1, america);
		System.out.println("Current date and time in a particular timezone : " + dateTimeInNewYork);

		//旧的Date对象转新对象
		Date currentDate = new Date();
		System.out.println("Current date: " + currentDate);

		// 获得当前日期的实例（以毫秒的形式）
		Instant now = currentDate.toInstant();
		ZoneId currentZone = ZoneId.systemDefault();

		// 用ofInstant方法获得实例
		LocalDateTime localDateTime2 = LocalDateTime.ofInstant(now, currentZone);
		System.out.println("Local date: " + localDateTime2);

		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, currentZone);
		System.out.println("Zoned date: " + zonedDateTime);
		
		//印度的时间与标准GMT时间相差+05:30
		LocalDateTime localDateTime3 = LocalDateTime.of(2014, Month.JANUARY, 14, 19, 30);
		ZoneOffset zoneOffset = ZoneOffset.of("+05:30");
		OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime3, zoneOffset);
		System.out.println("localDateTime3 with timezone offset in Java : " + offsetDateTime);
	}
}
