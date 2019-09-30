package com.zoo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {
    public static void main(String[] args) {
        testAtomicLongVSLongAdder(100, 5000000);
    }

    private static void testAtomicLongVSLongAdder(final int threadCount, final int times) {
        try {
            long start = System.currentTimeMillis();
            testLongAdder(threadCount, times);
            long end = System.currentTimeMillis() - start;
            System.out.println("条件>>>>>>线程数：" + threadCount + ", 每线程操作次数：" + times);
            System.out.println("结果>>>>>>LongAdder方式增加计数" + (threadCount * times) + "万次,共计耗时:" + end);

            long start2 = System.currentTimeMillis();
            testAtomicLong(threadCount, times);
            long end2 = System.currentTimeMillis() - start2;
            System.out.println("条件>>>>>>线程数：" + threadCount + ", 每线程操作次数：" + times);
            System.out.println("结果>>>>>>AtomicLong方式增加计数" + (threadCount * times) + "万次,共计耗时:" + end2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testAtomicLong(final int threadCount, final int times) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        AtomicLong atomicLong = new AtomicLong();
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    atomicLong.incrementAndGet();
                }
                countDownLatch.countDown();
            }, "my-thread" + i).start();
        }
        countDownLatch.await();
    }

    private static void testLongAdder(final int threadCount, final int times) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        LongAdder longAdder = new LongAdder();
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    longAdder.add(1);
                }
                countDownLatch.countDown();
            }, "my-thread" + i).start();
        }
        countDownLatch.await();
    }
}
