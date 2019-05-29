package com.zoo.annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;

//使用了被@Inherited修饰的注解
@DocumentA
class AA {
}

@DocumentB
public class AnnotatedElementDemo  extends AA{
	public static void main(String[] args) {
		Class<?> clazz = AnnotatedElementDemo.class;
		
		// 根据指定注解类型获取该注解
		DocumentA documentA = clazz.getAnnotation(DocumentA.class);
		System.out.println("@DocumentA from @Inherited：" + documentA);

		// 获取该元素上的所有注解，包含从父类继承
		Annotation[] annotations = clazz.getAnnotations();
		System.out.println("All Annotations：" + Arrays.toString(annotations));
		
		// 获取该元素上的所有注解，但不包含继承！
		Annotation[] no_annotations = clazz.getDeclaredAnnotations();
		System.out.println("All Annotations exclusive @Inherited：" + Arrays.toString(no_annotations));

		// 判断注解DocumentA是否在该元素上
		boolean b = clazz.isAnnotationPresent(DocumentA.class);
		System.out.println("Is presented for @Inherited:" + b);
		
		//结果：
		//@DocumentA from @Inherited：@com.zoo.annotation.DocumentA()
		//All Annotations：[@com.zoo.annotation.DocumentA(), @com.zoo.annotation.DocumentB()]
		//All Annotations exclusive @Inherited：[@com.zoo.annotation.DocumentB()]
		//Is presented for @Inherited:true
	}
}
