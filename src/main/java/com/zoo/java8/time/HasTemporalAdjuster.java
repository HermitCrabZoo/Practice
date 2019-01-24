package com.zoo.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.*;

public class HasTemporalAdjuster {
	public static void main(String[] args) {
		HasTemporalAdjuster hasTemporalAdjuster = new HasTemporalAdjuster();
		hasTemporalAdjuster.testTemporalAdjuster();
	}
	
	public void testTemporalAdjuster() {
		LocalDate localDate = LocalDate.of(2018,6,20);
        System.out.println(localDate); //2018-06-20

        //下一周的星期天
        LocalDate newdata = localDate.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(newdata); //2018-06-24

        LocalDate lastDate = localDate.with(lastDayOfMonth());
        System.out.println(lastDate); //2018-06-30

        //表示当前月的第二周的星期天
        LocalDate date1 = localDate.with(dayOfWeekInMonth(2,DayOfWeek.SUNDAY));
        System.out.println(date1); //2018-06-10

        //当前月的第一天
        LocalDate date2 = localDate.with(firstDayOfMonth());
        System.out.println(date2); //2018-06-01

        //下个月的第一天
        LocalDate date3 = localDate.with(firstDayOfNextMonth());
        System.out.println(date3); //2018-07-01

        //明年的第一天
        LocalDate date4 = localDate.with(firstDayOfNextYear());
        System.out.println(date4); //2019-01-01

        //当前的以一天
        LocalDate date5 = localDate.with(firstDayOfYear());
        System.out.println(date5); //2018-01-01

        //本月第一个满足星期三的日期
        LocalDate date6 =localDate.with(firstInMonth(DayOfWeek.WEDNESDAY));
        System.out.println(date6); //2018-06-06

        //今年的最后一天
        LocalDate date7 = localDate.with(lastDayOfYear());
        System.out.println(date7); //2018-12-31

        //当月最后一个满足星期二的日期
        LocalDate date8 = localDate.with(lastInMonth(DayOfWeek.TUESDAY));
        System.out.println(date8); //2018-06-26

        //下个星期天的日期
        LocalDate date9 = localDate.with(next(DayOfWeek.SUNDAY));
        System.out.println(date9); //2018-06-24
        System.out.println("localDate======="+localDate); //localDate=======2018-06-20
        LocalDate localDate2 = LocalDate.of(2018,6,20);
        LocalDate date13 = localDate2.with(nextOrSame(DayOfWeek.WEDNESDAY));
        System.out.println(date13);
        System.out.println("localDate2==="+localDate2);

        //上个星期天的日期
        LocalDate date10 = localDate.with(previous(DayOfWeek.SUNDAY));
        System.out.println(date10); //2018-06-17

        //当前或上一个星期三
        LocalDate date11 = localDate.with(previousOrSame(DayOfWeek.WEDNESDAY));
        System.out.println(date11); //2018-06-20

        //当前或上一个星期三
        LocalDate localDate1 = LocalDate.of(2018,6,6); //为本月第一个星期三
        LocalDate date12 = localDate1.with(previousOrSame(DayOfWeek.WEDNESDAY));
        System.out.println(date12); //2018-06-06
	}
}
