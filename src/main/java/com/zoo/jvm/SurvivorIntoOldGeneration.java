package com.zoo.jvm;


/**
 * 存活区对象在达到MinorGC次数后仍然存活则进入老年代
 * 
 * @author Hermi
 * 
 * -Xms20M -Xmx20M -Xmn10M
 * -XX:+UseSerialGC（client模式运行，收集器为Serail+SerialOld）
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=1
 * -XX:+PrintTenuringDistribution
 */
public class SurvivorIntoOldGeneration {

	private static final int _1MB = 1024 * 1024;
	
	/*
	 * 每个对象有一个对象年龄计数器，与对象头标记字中的GC分代年龄对应。
	 * 对象出生在Eden区、经过一次Minor GC后仍然存活，并能够被Survivor容纳，设置年龄为1，
	 * 对象在Survivor区每次经过一次Minor GC，年龄就加1，当年龄达到一个阈值（默认15），
	 * 下次触发Minor GC时就被移到老年代（即配置的是最多被在Survivor间移动几次，
	 * 达到阈值后下一次GC时不移动而是直接晋升了）。虚拟机提供了-XX:MaxTenuringThreshold来进行设置。
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*
		 * 发生了两次Minor GC，
		 * 第一次是在给allocation3进行分配的时候会出现一次Minor GC，此时survivor区域不能容纳allocation2，
		 * 但是可以容纳allocation1，所以allocation1将会进入survivor区域并且年龄为1，达到了阈值，
		 * 将在下一次GC时晋升到老年代，而allocation2则会通过担保机制进入老年代。
		 * 第二次发生GC是在第二次给allocation3分配空间时，这时，allocation1的晋升到老年代，
		 * 此次GC也可以清理出原来allocation3占据的4MB空间，将allocation3分配在Eden区。
		 * 所以，最后的结果是allocation1、allocation2在老年代，allocation3在Eden区。
		 */
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
	}
}
