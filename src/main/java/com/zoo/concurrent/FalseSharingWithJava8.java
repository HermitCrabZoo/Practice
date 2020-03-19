package com.zoo.concurrent;

import jdk.internal.vm.annotation.Contended;

public class FalseSharingWithJava8 implements Runnable {

    public final static int NUM_THREADS = 4; // change
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharingWithJava8(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharingWithJava8(i));
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
            longs[arrayIndex].value = i;
        }
    }

    // jdk8新特性，Contended注解避免false sharing
    // Restricted on user classpath
    // Unlock: -XX:-RestrictContended
    //@sun.misc.Contended jdk8
    //@jdk.internal.vm.annotation.Contended after jdk8
    @Contended
    public final static class VolatileLong {
        public volatile long value = 0L;
    }
}
