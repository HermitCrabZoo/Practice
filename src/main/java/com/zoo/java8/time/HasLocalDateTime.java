package com.zoo.java8.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class HasLocalDateTime {
	public static void main(String[] args) {
		HasLocalDateTime hasLocalDateTime = new HasLocalDateTime();
		hasLocalDateTime.testLocalDateTime();
	}

	public void testLocalDateTime() {
		// 获得当前的日期和时间
		LocalDateTime localDateTimeNow = LocalDateTime.now();
		System.out.println("local date and time in now: " + localDateTimeNow);
		//获取年月日
		int year=localDateTimeNow.getYear();
		Month month = localDateTimeNow.getMonth();
		int day = localDateTimeNow.getDayOfMonth();
		int seconds = localDateTimeNow.getSecond();
		// 由当前时间对象获得各个字段，输出结果
		System.out.println("local date and time in now --> year:"+year+" month: " + month + " day: " + day + " seconds: " + seconds);

		
		// 由当前时间附带月份和年再输出
		LocalDateTime localDateTime2 = localDateTimeNow.withDayOfMonth(10).withYear(2012);
		System.out.println("localDateTime2: " + localDateTime2);
		
		//获取时间戳
		Instant timestamp = Instant.now();
		System.out.println("What is value of this instant timestamp:" + timestamp);
		
		
		LocalDateTime dt1 = LocalDateTime.of(2017, Month.APRIL,18,13,45,20);
        System.out.println(dt1);
        LocalDate date1 = dt1.toLocalDate(); //通过LocalDateTime获得LocalDate
        LocalTime time1 = dt1.toLocalTime(); //通过LocalDateTime获得LocalTime
        System.out.println("date1======="+date1+"time1===="+time1);
        
        LocalDate date = LocalDate.of(2014,02,26);
        LocalTime time = LocalTime.of(12,23,20);
        LocalDateTime dt2 = LocalDateTime.of(date,time);
        System.out.println(dt2);

        //LocalDate结合LocalTime成一个LocalDateTime
        LocalDateTime dt3 = date.atTime(13,45,20);
        System.out.println(dt3); //2014-02-26T13:45:20

        //LocalDate结合LocalTime成一个LocalDateTime
        LocalDateTime dt4 = date.atTime(time);
        System.out.println(dt4); //2014-02-26T12:23:20

        //LocalTime结合LocalDate成LocalDateTime
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt5); //2014-02-26T12:23:20
		

	}
}
