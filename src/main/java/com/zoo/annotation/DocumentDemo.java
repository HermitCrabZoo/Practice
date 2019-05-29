package com.zoo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

//使用@Inherited注解
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface DocumentA {
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface DocumentB {
}

//使用了被@Inherited修饰的注解
@DocumentA
class A{ }
class B extends A{ }


@DocumentB
class C{ }
class D extends C{ }


public class DocumentDemo {
	public static void main(String[] args) {
		A a = new B();
		C c = new D();
		System.out.println("有@Inherited:"+Arrays.toString(a.getClass().getAnnotations()));
		System.out.println("没@Inherited:"+Arrays.toString(c.getClass().getAnnotations()));
		//结果：
		//有@Inherited:[@com.amcare.controller.DocumentA()]
		//没@Inherited:[]
	}
}

