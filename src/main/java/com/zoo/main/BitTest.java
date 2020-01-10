package com.zoo.main;

/**
 * 位操作
 */
public class BitTest {
    public static void main(String[] args) {
        moveLeft();
        moveRight();
        unsignMoveRight();
        moveOverly();
    }


    private static void moveLeft() {
        int n = 6;
        // ..000110 (6)
        System.out.println(n + "左移1位 " + (n << 1));
        // ..001100 (12)
        System.out.println(n + "左移2位 " + (n << 2));
        // ..011000 (24)
        System.out.println(n + "左移3位 " + (n << 3));
        // ..110000 (48)
        // 通过2进制的方式赋值(jdk7+)，0b开头，后面是二进制表示(超过int最大值就要结尾加L成为Long)
        // 等同于int x = 1073741825
        // 01000000000000000000000000000001 第一位0表示正数
        int x = 0b01000000000000000000000000000001;
        System.out.println("x = " + x);
        // 10000000000000000000000000000010
        // 左移后最高位成了1，变成了负数
        System.out.println("x左移1位\t= " + (x << 1));
        // 就像乘2如果超过了int最大值也会变成负数，结果是一样一样的
        System.out.println("x乘2\t= " + (x * 2));
        // 如果左移两位，最高位依然是正数(4)
        // 00000000000000000000000000000100
        System.out.println("x左移2位\t= " + (x << 2));
        // 正数超过极限可能变成负数，负数变成正数也不奇怪吧
        System.out.println("x乘4\t= " + (x * 2 * 2));
        // y = -3 时:
        // 原码: 10000000000000000000000000000011
        // 反码: 11111111111111111111111111111100 (符号位除外，其余取反)
        // 补码: 11111111111111111111111111111101 (反码+1)
        // Java存储补码，移位操作也是对补码操作
        // 也就解释了为啥负数时左移1位也能和乘2结果一样(最高位的1没了，后面跟上来的也是1，符号还是负)
        int y = -3;
        System.out.println(y + " 二进制表示(补码) " + Integer.toBinaryString(y));
        //补码左移一位后: 11111111111111111111111111111010
        //转为反码:      11111111111111111111111111111001  (补码-1)
        //转为原码:      10000000000000000000000000000110
        //十进制: -6
        System.out.println(y + " 左移1位 " + (y << 1));
    }


    private static void moveRight() {
        int n = 6;// ..110 (6)
        System.out.println(n + "右移1位 " + (n >> 1));// ..011 (3)
        System.out.println(n + "右移2位 " + (n >> 2));// ..001 (1)
        System.out.println(n + "右移3位 " + (n >> 3));// ..000 (0)
        System.out.println(n + "右移4位 " + (n >> 4));// ..000 (0)
        int m = -3;
        System.out.println(m + "\t补码 " + Integer.toBinaryString(m));
        System.out.println(m + "\t补码右移1位 " + (m >> 1));
        System.out.println(m + "\t补码右移2位 " + (m >> 2));
        System.out.println(m + "\t补码右移3位 " + (m >> 3));
    }


    private static void unsignMoveRight() {
        int m = -3;
        System.out.println(m + "\t补码 " + Integer.toBinaryString(m));
        System.out.println(m + "\t补码无符号右移1位 " + (m >>> 1));
        System.out.println(m + "\t补码无符号右移2位 " + (m >>> 2));
        System.out.println(m + "\t补码无符号右移3位 " + (m >>> 3));
    }

    private static void moveOverly(){
        int m = 3;
        int t = m;
        for (int i = 1; i <= 36; i++) {
            t = t << 1;
        }
        System.out.println(t);
        System.out.println(m << 36);
        System.out.println(m << (36 % 32));
    }
}
