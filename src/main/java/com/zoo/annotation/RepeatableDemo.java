package com.zoo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.stream.Stream;

//使用Java8新增@Repeatable原注解
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(FilterPaths.class)
@Inherited
@interface FilterPath {
String  value();
}


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface FilterPaths {
FilterPath[] value();
}

@FilterPath("/web/list")
class RepeatableParent { }

//子类上不使用@FilterPath(@Repeatable)注解,getAnnotationsByType将会从父类找
public class RepeatableDemo extends RepeatableParent{

	public static void main(String[] args) {
		Class<?> clazz = RepeatableDemo.class;
        //通过getAnnotationsByType方法获取所有重复注解
        FilterPath[] annotationsRepeatables = clazz.getAnnotationsByType(FilterPath.class);
        FilterPath[] annotationsByType2 = clazz.getDeclaredAnnotationsByType(FilterPath.class);
        Stream.of(annotationsRepeatables).forEach(f->System.out.println("Repeatable:"+f.value()));
        Stream.of(annotationsByType2).forEach(f->System.out.println("Declared:"+f.value()));
        System.out.println("使用getAnnotation的结果:"+clazz.getAnnotation(FilterPath.class));
//        Repeatable:/web/list
//        使用getAnnotation的结果:@com.zoo.annotation.FilterPath(value=/web/list)
	}

}

