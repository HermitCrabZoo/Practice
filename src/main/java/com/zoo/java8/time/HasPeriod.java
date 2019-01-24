package com.zoo.java8.time;

import java.time.LocalDate;
import java.time.Period;

public class HasPeriod {
	public static void main(String[] args) {
		HasPeriod hasPeriod = new HasPeriod();
		hasPeriod.testPeriod();
	}

	public void testPeriod() {
		/*
		 * 以年，月，日方式建模，可以使用Period类。
		 */
        LocalDate localDate1 = LocalDate.of(2018,4,12);
        LocalDate localDate2 = LocalDate.of(2019,1,16);

        Period period = Period.between(localDate1,localDate2);

        System.out.println(String.format("%s - %s 间隔%d年 %d月 %d日", localDate1, localDate2, period.getYears(), period.getMonths(), period.getDays()));
	}
}
