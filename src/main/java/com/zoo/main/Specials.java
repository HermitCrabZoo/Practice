package com.zoo.main;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Specials {
    public static void main(String[] args) {
        one();
        two();
        three();
        four();
        five();
        six();
        seven();
        eight();
        ten();
    }

    static void one() {
        float a = 0.125f;
        double b = 0.125d;
        System.out.println((a - b) == 0.0);
    }

    static void two() {
        double c = 0.8;
        double d = 0.7;
        double e = 0.6;
        System.out.println((c - d) == (d - e));
    }

    static void three() {
        System.out.println(1.0 / 0);
    }

    static void four() {
        System.out.println(0.0 / 0.0);
    }

    static void five() {
        System.out.println(0.0 / 0.0);
    }

    static void six() {
//        f(null);//编译错误
    }

    static void seven() {
        g(1);//调用g(double d)
    }

    static void eight() {
        String a = null;
        try {
            switch (a) {
            }
            System.out.println("normal invoke");
        } catch (Exception e) {
            System.out.println("thrown a exception:" + e);
        }
    }

    static void nine() {
        get("string", 1);//normal invoke
    }


    static void ten() {
        //2^13=8192,2^13=16384
        //‭0.75×6384=‬12288‬>10000
        //so the times of resize is 0(exclude initialize resize)
        Map<Integer, Integer> map = new HashMap<>(10000);
        IntStream.rangeClosed(1, 10000).forEach(i -> map.put(i, i));
    }

    static void f(String s) {
    }

    static void f(Integer i) {
    }

    static void g(double d) {
    }

    static void g(Integer i) {
    }

    static <String, T, Alibaba> String get(String string, T t) {
        return string;
    }
}
