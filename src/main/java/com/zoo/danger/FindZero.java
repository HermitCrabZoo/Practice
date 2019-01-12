package com.zoo.danger;

import java.math.BigDecimal;

public class FindZero {
	
	public static void main(String[] args) {
		System.out.println(2.00-1.10);//得不到:0.90只能得到近似值:0.8999999999999999
		
		System.out.println((200-110)/100.0);//可将小数点右移转换成int值再计算
		
		//可用BigDecimal类来计算，但是构造时，不要传入如下double类型值，否则结果将是:0.899999999999999911182158029987476766109466552734375
		System.out.println(new BigDecimal(2.00).subtract(new BigDecimal(1.10)));
		//应该使用string参数狗仔BigDecimal对象，结果才正确:0.90
		//不过使用BigDecimal可能会影响效率
		System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.10")));
		
	}
	
}
