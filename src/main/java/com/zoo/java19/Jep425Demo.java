package com.zoo.java19;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

/**
 * 虚拟线程，也就是轻量级线程。虚拟线程极大地降低了高吞吐量应用的开发和维护成本
 * 		平台线程（原有线程）是在OS线程上做的封装，它的创建和切换成本很高，可用的线程数量也有限制。对于并发较高的应用，想要提高系统的吞吐量，之前一般是做异步化，但这种方式很难定位线上问题
 * 虚拟线程的引入，让thread-per-request风格再次回到开发者的视线，虚拟线程的资源分配和调度由Java平台实现，它不再直接与OS线程强关联，而是直接将平台线程作为载体线程，这使得虚拟线程的可用数量大大增加
 */
public class Jep425Demo {

    private static void firstVirtualThread(){
        // 创建10000个虚拟线程
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }  // try-with-resources，会隐式调用executor.close()
    }

    private static void testPlatformThread() {
        try (var executor = Executors.newThreadPerTaskExecutor(Thread.ofPlatform().factory())) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }
    }

    private static void infoCurrentThread() {
        Thread thread = Thread.currentThread();
        System.out.printf("线程名称: %s，是否虚拟线程: %s\n",
                thread.getName(), thread.isVirtual());
    }

    private static void waysToCreateVirtualThread() {
        // 方式一：直接启动，虚拟线程名称为""
        Thread.startVirtualThread(() -> infoCurrentThread());

        // 方式二：Builder模式构建
        Thread vt = Thread.ofVirtual().allowSetThreadLocals(false)
                .name("VirtualWorker-", 0)
                .inheritInheritableThreadLocals(false)
                .unstarted(() -> infoCurrentThread());
        vt.start();

        // 方式三：Factory模式构建
        ThreadFactory factory = Thread.ofVirtual().allowSetThreadLocals(false)
                .name("VirtualFactoryWorker-", 0)
                .inheritInheritableThreadLocals(false)
                .factory();
        Thread virtualWorker = factory.newThread(() -> infoCurrentThread());
        virtualWorker.start();

        // 方式四：newVirtualThreadPerTaskExecutor
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> infoCurrentThread());
        }

        // 方式五：构建"虚拟线程池"
        ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory);
        executorService.submit(() -> infoCurrentThread());

        infoCurrentThread();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        firstVirtualThread();
        System.out.printf("firstVirtualThread finished, time cost %d ms\n",
                System.currentTimeMillis() - startTime);
    }
}