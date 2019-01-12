package com.zoo.java8.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class HasLocalDateTime {
	public static void main(String[] args) {
		HasLocalDateTime hasLocalDateTime = new HasLocalDateTime();
		hasLocalDateTime.testLocalDateTime();
	}

	public void testLocalDateTime() {
		// 获得当前的日期和时间
		LocalDateTime localDateTime1 = LocalDateTime.now();
		System.out.println("local date and time: " + localDateTime1);
		//获取年月日
		int year=localDateTime1.getYear();
		Month month = localDateTime1.getMonth();
		int day = localDateTime1.getDayOfMonth();
		int seconds = localDateTime1.getSecond();
		// 由当前时间对象获得各个字段，输出结果
		System.out.println("local date and time --> year:"+year+" month: " + month + " day: " + day + " seconds: " + seconds);

		
		// 由当前时间附带月份和年再输出
		LocalDateTime localDateTime2 = localDateTime1.withDayOfMonth(10).withYear(2012);
		System.out.println("localDateTime2: " + localDateTime2);
		
		// 输出当前时间的本地值（本时区）
		LocalDate localDate1 = LocalDate.now();
		System.out.println("localDate1: " + localDate1);
		LocalDate localDate1PlusDay = localDate1.plusDays(1);//加一天
		System.out.println("localDate1PlusDay:" + localDate1PlusDay);
		LocalDate localDate1MinuYear = localDate1.minus(1, ChronoUnit.YEARS);//加一年
		System.out.println("localDate1MinuYear:" + localDate1MinuYear);
		LocalDate nextMonday = localDate1.with(TemporalAdjusters.next(DayOfWeek.MONDAY));//下一个周一的日期
	    System.out.println("nextMonday: " + nextMonday);
	    //下两个周日
	    LocalDate secondSunday = localDate1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
	    System.out.println("secondSunday:" + secondSunday);
	    //判断闰年
	    if (localDate1.isLeapYear()) {
	    	System.out.println("This year is leap year:"+localDate1);
	    }else{
	    	System.out.println("This year is not leap year:"+localDate1);
	    }
		
	    
		LocalDate dateOfBirth = LocalDate.of(2016, 9, 7);//生日
		MonthDay birthDay = MonthDay.from(dateOfBirth);//出生月日
		MonthDay nowMonthDay = MonthDay.from(localDate1);//今天的月日
		if (nowMonthDay.equals(birthDay)) {
			System.out.println("Your birthday is:"+birthDay+" Many Many happy!! today is your birthday.");
		} else {
			System.out.println("Sorry, your birthday is:"+birthDay+" today is not your birthday.");
		}
		
		//获取月的天数
		YearMonth currentYearMonth = YearMonth.now();
		System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
		YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY);
		System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
		

		
		// 输出2016年圣诞节的日期
		LocalDate localDate2 = LocalDate.of(2016, Month.DECEMBER, 25);
		System.out.println("localDate2: " + localDate2);

		
		//两个日期之间的间隔
		Period period=Period.between(localDate1, localDate2);
		System.out.println("localDate1 ~ lacalDate2:"+period);

		//判断一个日期是否早于另一个日期
		System.out.println("localDate1 is before localDate2:"+localDate1.isBefore(localDate2));
		
		// 输出新闻联播的开始时间
		LocalTime localTime1 = LocalTime.of(19, 00);
		System.out.println("localTime1: " + localTime1);
		LocalTime localTime1PlusHour = localTime1.plusHours(2);//加2小时
		System.out.println("localTime1PlusHour: " + localTime1PlusHour);
		LocalTime localTime1PlusMinute = localTime1.plusMinutes(2);//加2分钟
		System.out.println("localTime1PlusMinute: " + localTime1PlusMinute);
		LocalTime localTime1PlusSecond = localTime1.plusSeconds(2);//加2秒
		System.out.println("localTime1PlusSecond: " + localTime1PlusSecond);
		LocalTime localTime1PlusNano = localTime1.plusNanos(20);//加20纳秒
		System.out.println("localTime1PlusNano: " + localTime1PlusNano);

		
		// 转化为字符串，再输出
		LocalTime localTime2 = LocalTime.parse("20:15:30");
		System.out.println("localTime2: " + localTime2);
		
		//两个时间之间的间隔
		Duration duration=Duration.between(localTime1, localTime2);
		System.out.println("localTime1 ~ localTime2:"+duration);
		
		//判断一个时间是否早与另一个时间
		System.out.println("localTime1 is before localTime2:"+localTime1.isBefore(localTime2));
		
		//获取时间戳
		Instant timestamp = Instant.now();
		System.out.println("What is value of this instant timestamp:" + timestamp);
		

	}
}
