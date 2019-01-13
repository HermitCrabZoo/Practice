package com.zoo.jvm;


/**
 * 空间分配担保
 * 
 * @author Hermi
 * 
 * -Xms20M -Xmx20M -Xmn10M 
 * -XX:SurvivorRatio=8 
 * -XX:+PrintGCDetails
 * -XX:+UseSerialGC
 * -XX:-HandlePromotionFailure
 */
public class HandlePrpmotion {

	private static final int _1MB = 1024 * 1024;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*
		 * 发生了两Minor次GC，第一次发生在给allocation4分配内存空间时，由于老年代的连续可用空间大于存活的对象总和，
		 * 所以allocation2、allocation3将会进入老年代，allocation1的空间将被回收，allocation4分配在新生代；
		 * 第二次发生在给allocation7分配内存空间时，此次GC将allocation4、allocation5、allocation6所占的内存全部回收。
		 * 最后，allocation2、allocation3在老年代，allocation7在新生代。
		 */
		byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation1 = null;
		allocation4 = new byte[2 * _1MB];
		allocation5 = new byte[2 * _1MB];
		allocation6 = new byte[2 * _1MB];
		allocation4 = null;
		allocation5 = null;
		allocation6 = null;
		allocation7 = new byte[2 * _1MB];
	}
}
