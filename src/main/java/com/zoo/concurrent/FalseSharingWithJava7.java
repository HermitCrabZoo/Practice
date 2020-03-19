package com.zoo.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Java7以前加入闲置的long字段来填充缓存行来避免伪共享。<br>
 * 但是看起来Java 7变得更加智慧了，它淘汰或者是重新排列了无用的字段，这样我们之前的办法在Java 7下可能就不奏效了，这导致了伪共享依然会发生。<br>
 * 我在不同的平台上实验了一些列不同的方案，并且最终发现下面的代码是最可靠的。<br>
 * 用以上这种办法获得了和FalseSharing相近的性能，可以把PaddedAtomicLong里面那行填充物注释掉再跑测试看看效果。<br>
 */
public class FalseSharingWithJava7 implements Runnable {
    public final static int NUM_THREADS = 4; // change
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    private static PaddedAtomicLong[] longs = new PaddedAtomicLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new PaddedAtomicLong();
        }
    }

    public FalseSharingWithJava7(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharingWithJava7(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].set(i);
        }
    }

    public static long sumPaddingToPreventOptimisation(final int index) {
        PaddedAtomicLong v = longs[index];
        return v.p1 + v.p2 + v.p3 + v.p4 + v.p5 + v.p6;
    }

    /**
     * 填充方案里加了一些东西，使那些个用于填充的字段很难被JVM优化掉。<br>
     * 一个耍小聪明的JVM还是会把你用于填充的P1-P7的字段优化掉，原理是这样滴：<br>
     * <pre>
     * PaddedAtomicLong类如果只对final的FalseSharing类可见（就是说PaddedAtomicLong不能再被继承了）。
     * 这样一来编译器就会“知道”它正在审视的是所有可以看到这个填充字段的代码，这样就可以证明没有行为依赖于p1到p7这些字段。
     * 那么“聪明”的JVM会把上面这些丝毫不占地方的字段统统优化掉。
     * 那么针对这样的情况，你可以巧妙的让PaddedAtomicLong类在FalseSharingWithJava7类之外可见，比如直接加一个依赖于p1到p7的公开的访问函数，并且这个函数在理论上可以被外界访问到。
     * </pre>
     */
    public static class PaddedAtomicLong extends AtomicLong {
        public volatile long p1, p2, p3, p4, p5, p6 = 7L;
    }
}