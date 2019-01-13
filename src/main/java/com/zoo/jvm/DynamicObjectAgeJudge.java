package com.zoo.jvm;

/**
 * 对象年龄判断
 * 
 * @author Hermi
 *
 * -Xms20M -Xmx20M -Xmn10M
 * -XX:SurvivorRatio=8
 * -XX:+PrintGCDetails
 * -XX:+UseSerialGC
 * -XX:MaxTenuringThreshold=15
 * -XX:+PrintTenuringDistribution
 */
public class DynamicObjectAgeJudge {
    private static final int _1MB = 1024 * 1024;

    /*
     * 对象的年龄到达了MaxTenuringThreshold可以进入老年代，
     * 同时，如果在survivor区中相同年龄所有对象大小的总和大于survivor区的一半，
     * 年龄大于等于该年龄的对象就可以直接进入老年代。无需等到MaxTenuringThreshold中要求的年龄。
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        /*
         * 发生了两次Minor GC，第一次发生在给allocation4分配内存时，
         * 此时allocation1、allocation2将会进入survivor区，而allocation3通过担保机制将会进入老年代。
         * 第二次发生在第二次给allocation4分配内存时，此时，
         * survivor区的allocation1、allocation2达到了survivor区容量的一半，将会进入老年代，
         * 此次GC可以清理出allocation4原来的4MB空间，并将allocation4分配在Eden区。
         * 最终，allocation1、allocation2、allocation3在老年代，allocation4在Eden区。
         */
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }
}
