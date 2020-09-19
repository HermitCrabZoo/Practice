package com.zoo.concurrent;

/**
 * 用来测试CLHLocke生不生效
 * <p>
 * 定义一个静态成员变量cnt，然后开10个线程跑起来，看能是否会有线程安全问题
 */
public class CLHLockTest {
    private static int cnt = 0;

    public static void main(String[] args) throws Exception {
        final CLHLock lock = new CLHLock();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                lock.lock();
                cnt++;
                lock.unLock();
            }).start();
        }
        // 让main线程休眠10秒，确保其他线程全部执行完
        Thread.sleep(10000);
        System.out.println();
        System.out.println("cnt----------->>>" + cnt);

    }
}
