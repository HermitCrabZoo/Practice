package com.zoo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 设置信号量同时执行的线程数是5
        Semaphore semp = new Semaphore(5);
        // 模拟20个线程并发访问
        for (int i = 0; i < 20; i++) {
            final int idx = i;
            Runnable run = () -> {
                try {
                    // 使用acquire()获取锁
                    semp.acquire();
                    System.out.println("accessed: " + idx);
                    // 睡眠1秒
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                } finally {
                    // 使用完成释放锁
                    semp.release();
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }

}
