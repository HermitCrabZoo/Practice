package com.zoo.java8.functionalInterface;

import java.util.Optional;

public class OptionalExample {
	public static void main(String[] args) {
		OptionalExample tester = new OptionalExample();
	      Integer value1 = null;
//	      Integer value2 = new Integer(5);//java9开始过期
	      Integer value2 = Integer.valueOf(5);

	      // ofNullable 允许传参时给出 null,此方法传参时不会报空指针
	      Optional<Integer> a = Optional.ofNullable(value1);

	      // 如果传递的参数为null，那么 of 将抛出空指针异常（NullPointerException）
	      Optional<Integer> b = Optional.of(value2);
	      System.out.println(tester.sum(a,b));
	   }

	   public Integer sum(Optional<Integer> a, Optional<Integer> b){

	      // isPresent 用于检查值是否存在

	      System.out.println("First parameter is present: " + a.isPresent());
	      System.out.println("Second parameter is present: " + b.isPresent());

	      // 如果当前返回的是传入的默认值，orElse 将返回它
//	      Integer value1 = a.orElse(new Integer(0));
		   Integer value1 = Integer.valueOf(0);

	      // get 用于获得值，条件是这个值必须存在
	      Integer value2 = b.get();
	      return value1 + value2;
	   }
}
