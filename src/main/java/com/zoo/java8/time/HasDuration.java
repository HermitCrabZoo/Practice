package com.zoo.java8.time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
* 作为人，我们习惯与以星期几，几号，几点，几分这样的方式理解日期和时间。
 * 对于计算机来说，建模时间最自然的格式是表示一个持续时间段上某个点的单一大整型数。
 * 这也是新的java.time.Instant类对时间建模的方式，
 * 基本上它是以Unix元年时间（传统的设定为UTC时区1970年1月1日午夜时分）开始经历的秒数进行计算。
 **/
public class HasDuration {
	public static void main(String[] args) {
		HasDuration hasDuration = new HasDuration();
		hasDuration.testDuration();
	}

	public void testDuration() {
		LocalTime time1 = LocalTime.of(18,23,45);
        LocalTime time2 = LocalTime.of(23,34,56);

        Duration d1 = Duration.between(time1,time2);
        System.out.println(String.format("%s - %s 间隔%d时 %d分 %d秒", time1, time2, d1.toHours(), d1.toMinutes(), d1.getSeconds())); //18671


        LocalDateTime localDateTime1 = LocalDateTime.of(2017,Month.MAY,12,11,13,11);
        LocalDateTime localDateTime2 = LocalDateTime.of(2018, Month.AUGUST,18,23,45,20);

        Duration d2 = Duration.between(localDateTime1,localDateTime2);
        System.out.println(String.format("%s - %s 间隔%d天 %d时 %d分 %d秒", localDateTime1, localDateTime2, d2.toDays(), d2.toHours(), d2.toMinutes(), d2.getSeconds())); //40048329


        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(6);

        Duration d3 = Duration.between(instant1,instant2);
        System.out.println(String.format("%s - %s 间隔%d时 %d分 %d秒", instant1, instant2, d3.toHours(), d3.toMinutes(), d3.getSeconds())); //3

	}
}
