package com.zoo.jvm;

/**
 * 新生代MinorGC
 * 
 * @author Hermi
 * 
 * -Xms20M -Xmx20M -Xmn10M
 * -XX:+UseSerialGC（client模式运行，收集器为Serail+SerialOld）
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 */
public class YoungGenerationMinorGC {
	private static final int _1MB = 1024 * 1024;

	// 对象优先分配在Eden区，如果Eden区没有足够的空间时，虚拟机执行一次Minor GC
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*
		 * 新生代可用的空间为9M = 8M（Eden容量） + 1M（一个survivor容量），
		 * 分配完allocation1、allocation2、allocation3之后，无法再分配allocation4，
		 * 会发生分配失败，则需要进行一次Minor GC，survivor to区域的容量为1M，无法容纳总量为6M的三个对象，
		 * 则会通过担保机制将allocation1、allocation2、allocation3转移到老年代，然后再将allocation4分配在Eden区。
		 */
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation4 = new byte[4 * _1MB];// 出现一次MinorGC
	}

}
