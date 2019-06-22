package com.zoo.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ReferenceQueueTest {
    private static ReferenceQueue<byte[]> rq = new ReferenceQueue<>();
    private static int _1M = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        Object value = new Object();
        Map<WeakReference<byte[]>, Object> map = new HashMap<>();
        Thread thread = new Thread(ReferenceQueueTest::run);
        thread.setDaemon(true);
        thread.start();

        for(int i = 0;i < 5;i++) {
            byte[] bytes = new byte[_1M];
            WeakReference<byte[]> weakReference = new WeakReference<>(bytes, rq);
            map.put(weakReference, value);
        }
        System.out.println("map.size->" + map.size());
        
        int aliveNum = 0;
        for (Map.Entry<WeakReference<byte[]>, Object> entry : map.entrySet()){
            if (entry != null){
                if (entry.getKey().get() != null){
                    aliveNum++;
                }
            }
        }
        System.out.println("5个对象中存活的对象数量：" + aliveNum);
        Thread.currentThread().join(1000*20);
    }

    @SuppressWarnings("unchecked")
	private static void run() {
        try {
            int n = 0;
            WeakReference<byte[]> k;
            while ((k = (WeakReference<byte[]>) rq.remove()) != null) {
                System.out.println((++n) + "回收了:" + k);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
