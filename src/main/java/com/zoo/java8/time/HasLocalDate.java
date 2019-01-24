package com.zoo.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class HasLocalDate {

	public static void main(String[] args) {
		HasLocalDate hasLocalDate = new HasLocalDate();
		hasLocalDate.testLocalDate();
	}

	public void testLocalDate() {
		// 关注与年月日
		LocalDate localDateNow = LocalDate.now();
		System.out.println("当前日期");
		System.out.println(localDateNow); // 2019-01-23

		System.out.println(localDateNow.getYear()); // 2019，年
		System.out.println(localDateNow.getMonthValue()); // 1，月
		System.out.println(localDateNow.getDayOfMonth()); // 23，日
		System.out.println(localDateNow.getDayOfWeek()); // MONDAY,星期几
		System.out.println(localDateNow.lengthOfMonth()); // 31,返回当前月份的长度
		System.out.println(localDateNow.isLeapYear());// false,是否是闰年

		System.out.println("------------------");

		LocalDate localDateOf = LocalDate.of(2019, 1, 22);
		System.out.println(localDateOf); // 2019-01-22

		System.out.println("------------------");

		// MonthDay关注月份和日
		MonthDay monthDay = MonthDay.of(localDateOf.getMonth(), localDateOf.getDayOfMonth());
		System.out.println(monthDay); // --01-22
		MonthDay monthDay2 = MonthDay.from(localDateOf);
		System.out.println(monthDay2); // --01-22

		if (monthDay.equals(monthDay2)) {
			System.out.println("equal");
		} else {
			System.out.println("not equal");
		}

		// LocalDate的parse只能转换2007-12-03这样的格式的,不能解析的也会抛出一个RuntimeException
		// 或者DateTimeParseException
		LocalDate date = LocalDate.parse("2017-03-18");
		LocalDate date2 = LocalDate.parse("2018-03-18");
		System.out.println(date);
		System.out.println(date2);

		// 当前日期的二周后
		LocalDate localDate2 = localDateNow.plus(2, ChronoUnit.WEEKS);
		System.out.println("当前日期2周后");
		System.out.println(localDate2); // 2019-02-06

		// 当前时间的二个月之前
		LocalDate localDate3 = localDateNow.minus(2, ChronoUnit.MONTHS);
		System.out.println("当前日期2月前");
		System.out.println(localDate3);// 2018-11-23

		LocalDate localDate6 = LocalDate.of(2018, 12, 12);
		System.out.println(localDateNow.isBefore(localDate6)); // 判断时间在什么时间之前
		System.out.println(localDateNow.isAfter(localDate6)); // 判断时间在什么时间之后
		System.out.println(localDateNow.isEqual(localDate6)); // 判断时间和什么时间相等
		System.out.println("..............");

		// 输出当前时间的本地值（本时区）
		LocalDate localDate1 = LocalDate.now();
		System.out.println("localDate1: " + localDate1);
		LocalDate localDate1PlusDay = localDate1.plusDays(1);// 加一天
		System.out.println("localDate1PlusDay:" + localDate1PlusDay);
		LocalDate localDate1MinuYear = localDate1.minus(1, ChronoUnit.YEARS);// 加一年
		System.out.println("localDate1MinuYear:" + localDate1MinuYear);
		LocalDate nextMonday = localDate1.with(TemporalAdjusters.next(DayOfWeek.MONDAY));// 下一个周一的日期
		System.out.println("nextMonday: " + nextMonday);
		// 下两个周日
		LocalDate secondSunday = localDate1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
				.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println("secondSunday:" + secondSunday);
		// 判断闰年
		if (localDate1.isLeapYear()) {
			System.out.println("This year is leap year:" + localDate1);
		} else {
			System.out.println("This year is not leap year:" + localDate1);
		}

		LocalDate dateOfBirth = LocalDate.of(2016, 9, 7);// 生日
		MonthDay birthDay = MonthDay.from(dateOfBirth);// 出生月日
		MonthDay nowMonthDay = MonthDay.from(localDate1);// 今天的月日
		if (nowMonthDay.equals(birthDay)) {
			System.out.println("Your birthday is:" + birthDay + " Many Many happy!! today is your birthday.");
		} else {
			System.out.println("Sorry, your birthday is:" + birthDay + " today is not your birthday.");
		}

		// 获取月的天数
		YearMonth currentYearMonth = YearMonth.now();
		System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
		YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY);
		System.out.printf("Your credit card expires on %s %n", creditCardExpiry);

		// 输出2016年圣诞节的日期
		LocalDate localDateChristmas = LocalDate.of(2016, Month.DECEMBER, 25);
		System.out.println("localDate2: " + localDateChristmas);

		// 两个日期之间的间隔
		Period period = Period.between(localDate1, localDateChristmas);
		System.out.println("localDate1 ~ lacalDate2:" + period);

		// 判断一个日期是否早于另一个日期
		System.out.println("localDate1 is before localDate2:" + localDate1.isBefore(localDateChristmas));
	}
}
