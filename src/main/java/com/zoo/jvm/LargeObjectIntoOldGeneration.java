package com.zoo.jvm;

/**
 * 大对象直接进入老年代
 * 
 * @author Hermi
 * 
 * -Xms20M -Xmx20M -Xmn10M
 * -XX:+UseSerialGC（client模式运行，收集器为Serail+SerialOld） 
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=3145728(3M，此参数只能以字节为单位,大对象直接进入老年代，参数只对Serial和ParNew两种收集器有效)
 */
public class LargeObjectIntoOldGeneration {
	private static final int _1MB = 1024 * 1024;
	
	/*
	 * 需要大量连续内存空间的Java对象称为大对象，大对象的出现会导致提前触发垃圾收集以获取更大的连续的空间来进行大对象的分配。
	 * 虚拟机提供了-XX:PretenureSizeThreadshold（仅对Serial和ParNew收集器有效）参数来设置大对象的阈值，
	 * 超过阈值的对象直接分配到老年代。这样做的目的是避免在Eden区和两个 Survivor区之间发生大量的内存拷贝（新生代采用复制算法收集内存）。
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// 4MB对象超过PretenureSizeThreshold阈值（3MB），直接分配在了老年代。
		byte[] allocation1 = new byte[4 * _1MB];
	}

}
