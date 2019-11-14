package com.zoo.main;

/**
 * 减法乘法比较
 */
public class SubMultiplicationPerformanceTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        long startTime = 0L;
        long endTime = 0L;

        int base  = 32;

        //求相反数
        System.out.println("#1 :"+base);
        System.out.println("#2 :"+base * (-1));
        System.out.println("#3 :"+(0 - base));

        //减法
        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            int result = (0 - base);
        }
        endTime = System.nanoTime();
        System.out.println("#5 0- running time is："+(endTime-startTime));

        //乘法
        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            int result = base * (-1);
        }
        endTime = System.nanoTime();
        System.out.println("#4 * (-1) running time is："+(endTime-startTime));
    }
}
