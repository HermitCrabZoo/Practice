package com.zoo.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class WeakReferenceTest {
    private static final List<Object> TEST_DATA = new LinkedList<>();
    private static final ReferenceQueue<WeakReferenceTestClass> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
    	WeakReferenceTestClass obj = new WeakReferenceTestClass("Test");
        WeakReference<WeakReferenceTestClass> weakRef = new WeakReference<>(obj, QUEUE);
        //可以重新获得OOMClass对象，并用一个强引用指向它
        //oomObj = weakRef.get();

        // 该线程不断读取这个弱引用，并不断往列表里插入数据，以促使系统早点进行GC
        new Thread(() -> {
            while (true) {
                TEST_DATA.add(new byte[1024 * 400]);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(weakRef.get());
            }
        }).start();

        // 这个线程不断读取引用队列，当弱引用指向的对象呗回收时，该引用就会被加入到引用队列中
        new Thread(() -> {
            while (true) {
                Reference<? extends WeakReferenceTestClass> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("--- 弱引用对象被jvm回收了 ---- " + poll);
                    System.out.println("--- 回收对象 ---- " + poll.get());
                }
            }
        }).start();

        //将强引用指向空指针 那么此时只有一个弱引用指向TestClass对象
        obj = null;

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    static class WeakReferenceTestClass {
        private String name;

        public WeakReferenceTestClass(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "TestClass - " + name;
        }
    }
}