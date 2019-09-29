package com.zoo.concurrent;

import java.util.concurrent.Phaser;

/**
 * 通过Phaser控制多个线程的执行时机：有时候我们希望所有线程到达指定点后再同时开始执行，我们可以利用CyclicBarrier或CountDownLatch来实现，这里给出使用Phaser的版本。
 * 示例中，创建了10个线程，并通过register方法注册Phaser的参与者数量为10。当某个线程调用arriveAndAwaitAdvance方法后，arrive数量会加1，如果数量没有满足总数（参与者数量10），当前线程就是一直等待，当最后一个线程到达后，所有线程都会继续往下执行。
 *
 * 注意：arriveAndAwaitAdvance方法是不响应中断的，也就是说即使当前线程被中断，arriveAndAwaitAdvance方法也不会返回或抛出异常，而是继续等待。如果希望能够响应中断，可以参考awaitAdvanceInterruptibly方法。
 */
public class PhaserTest1 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        for (int i = 0; i < 10; i++) {
            phaser.register();                  // 注册各个参与者线程
            new Thread(new Task(phaser), "Thread-" + i).start();
        }
    }
}

class Task implements Runnable {
    private final Phaser phaser;

    Task(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        int i = phaser.arriveAndAwaitAdvance();     // 等待其它参与者线程到达
        // do something
        System.out.println(Thread.currentThread().getName() + ": 执行完任务，当前phase =" + i + "");
    }
}
