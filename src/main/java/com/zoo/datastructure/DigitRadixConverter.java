package com.zoo.datastructure;

import java.util.ArrayDeque;

public class DigitRadixConverter {
    public static void main(String[] args) {
        System.out.println(decimalToRadix(3553, 2));
    }

    public static String decimalToRadix(int decimal, int radix) {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        int remainder = decimal % radix;
        decimal = decimal / radix;
        arrayDeque.push(Integer.toString(remainder));
        while (decimal != 0) {
            remainder = decimal % radix;
            decimal = decimal / radix;
            arrayDeque.push(Integer.toString(remainder));
        }
        return String.join("", arrayDeque.toArray(String[]::new));
    }
}
