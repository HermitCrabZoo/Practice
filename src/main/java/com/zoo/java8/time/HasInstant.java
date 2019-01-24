package com.zoo.java8.time;

import java.time.Instant;

/**
* 作为人，我们习惯与以星期几，几号，几点，几分这样的方式理解日期和时间。
 * 对于计算机来说，建模时间最自然的格式是表示一个持续时间段上某个点的单一大整型数。
 * 这也是新的java.time.Instant类对时间建模的方式，
 * 基本上它是以Unix元年时间（传统的设定为UTC时区1970年1月1日午夜时分）开始经历的秒数进行计算。
 **/
public class HasInstant {
	public static void main(String[] args) {
		HasInstant hasInstant = new HasInstant();
		hasInstant.testInstant();
	}

	public void testInstant() {
		//获取时间戳
		Instant instantNow = Instant.now();
		System.out.println("What is value of this instant timestamp in now:" + instantNow);

		Instant instant1 = Instant.ofEpochSecond(3);
        System.out.println(instant1);//1970-01-01T00:00:03Z

        //第一个参数是秒，第二个是纳秒参数，纳秒的存储范围是0至999,999,999
        Instant instant2 = Instant.ofEpochSecond(3,0);
        System.out.println(instant2);//1970-01-01T00:00:03Z

        //2s之后的在加上100万纳秒（1s）
        Instant instant3 = Instant.ofEpochSecond(2,1000000000);
        System.out.println(instant3); //1970-01-01T00:00:03Z

        Instant instant4 = Instant.ofEpochSecond(4,-1000000000);
        System.out.println(instant4); //1970-01-01T00:00:03Z

	}
}
