package com.zoo.jvm;

/**
 * 设置JVM参数： 
 * 堆内存固定为4000MB 
 * 新生代内存为1300MB 
 * 新生代使用ParNew垃圾收集器(并行，复制算法)
 * 老年代使用CMS垃圾收集器(并发，标记-清除算法) 
 * 使用手动定义初始化定义开始CMS收集(禁止hostspot自行触发CMS GC)
 * 使用cms作为垃圾回收在老年代内存使用了75%后开始CMS收集 
 * 在发生垃圾回收时打印内存回收日志，并在进程退出时输出当前内存各区域分配情况。
 * -Xmx4000M -Xms4000M -Xmn1300M -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=75
 * -XX:+PrintGCDetails
 *
 */
public class GCRootTest {
    private static final int _1MB = 1024 * 1024;

    private static final int LENGTH = 40000000;

    public static void main(String[] args) {
        Node next = null;
        for (int i = 0; i <= LENGTH; i++) {
            Node node = new Node(i, next);
            next = node;
        }
        // 如果不设置为null这里将会又大批量的遍历，打开这里和不打开这里，gc时间完全不一样，现在你直到为什么要设置为null了吗？
        next = null;
        triggerGC();
    }

    /**
     * 不触发gc看不见效果 new 很多小对象。不然直接到 old区去了。
     */
    private static void triggerGC() {
//        byte[] all = new byte[2000 * _1MB]; //这里为什么没又直接new 一个大对象？它可能直接就到old区去了。
        for (int i = 0; i < 500; i++) {
            byte[] bytes = new byte[2 * _1MB];
        }
    }

    // POJO 不用看这里
    static class Node {

        private int valuel;

        private Node node;

        public Node(int valuel, Node node) {
            this.valuel = valuel;
            this.node = node;
        }

        public int getValuel() {
            return valuel;
        }

        public void setValuel(int valuel) {
            this.valuel = valuel;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }
    }
}
