package com.zoo.java8.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HasDateTimeFormatter {
	public static void main(String[] args) {
		HasDateTimeFormatter hasDateTimeFormatter = new HasDateTimeFormatter();
		hasDateTimeFormatter.testDateTimeFormatter();
	}
	
	public void testDateTimeFormatter() {
		Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatdate = localDateTime.format(formatter);
        System.out.println(formatdate);

        System.out.println("...........");
        LocalDate localDate = LocalDate.of(2018,3,17);
        //BASIC_ISO_DATE格式，20180317
        String str = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(str);

        //DateTimeFormatter.ISO_LOCAL_DATE 格式 2018-03-17
        String str2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(str2);

        //定义
        String str3 = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println(str3);

        LocalDate localDate1 = LocalDate.parse("20111203",DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate1); //2011-12-03

        LocalDate localDate2 = LocalDate.parse("2017-03-17",DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(localDate2); //2017-03-17
	}
}
