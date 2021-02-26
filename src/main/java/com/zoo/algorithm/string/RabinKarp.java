package com.zoo.algorithm.string;

/**
 * 由Rabin 与 Karp发明的算法
 * 逐个比较主串中与子串等长的子串的hashcode与子串的hashcode，
 * hashcode相同再逐个判断两个子串中是否每个字符都相同，若不是，则表示hash冲突，
 * 此时继续向后寻找子串并比较，直至找到hashcode相同且每个字符相同的子串或找完主串。
 */
public class RabinKarp {

    public static void main(String[] args) {
        String str = "aacdesadsdfer";
        String pattern = "adsd";
        System.out.println("第一次出现的位置:" + rabinKarp(str, pattern));
    }

    /**
     * ①按位相加法(每个字符的hashcode相加等于字符串的hashcode)
     * ②将 a当做1，b当做2，c当做3...以此类推
     * ③生成模式串(子串的hashcode)
     * ④生成主串中第一个等长子串的hashcode与模式串的hashcode比较，相同则比较是否每个字符都相同，不同则将下标后移一位取主串中与模式串等长的子串hashcode来比较，如此循环往复。
     *
     * @param str     主串
     * @param pattern 子串
     * @return 子串在主串中第一次出现的索引
     */
    public static int rabinKarp(String str, String pattern) {
        //主串长度
        int m = str.length();
        //模式串的长度
        int n = pattern.length();
        //计算模式串的hash值
        int patternCode = hash(pattern, n);
        //计算主串当中第一个和模式串等长的子串hash值
        int strCode = hash(str, n);
        //用模式串的hash值和主串的局部hash值比较。
        //如果匹配，则进行精确比较；如果不匹配，计算主串中相邻子串的hash值。
        for (int i = 0; i < m - n + 1; i++) {
            if (strCode == patternCode && compareString(str, i, pattern)) {
                return i;
            }
            //如果不是最后一轮，更新主串从i到i+n的hash值
            if (i < m - n) {
                strCode = nextHash(str, strCode, i, n);
            }
        }
        return -1;
    }

    private static int hash(String str, int endIndex) {
        int hashcode = 0;
        //这里采用最简单的hashcode计算方式：
        //把a当做1，把b当中2，把c当中3.....然后按位相加
        for (int i = 0; i < endIndex; i++) {
            hashcode += str.charAt(i) - 'a';
        }
        return hashcode;
    }

    private static int nextHash(String str, int hash, int index, int n) {
        hash -= str.charAt(index) - 'a';
        hash += str.charAt(index + n) - 'a';
        return hash;
    }

    private static boolean compareString(String str, int startIndex, String pattern) {
        for (int i = 0, j = startIndex; i < pattern.length(); i++, j++) {
            if (pattern.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return str.length() - startIndex >= pattern.length();
    }
}
