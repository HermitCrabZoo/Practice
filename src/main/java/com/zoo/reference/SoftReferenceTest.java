package com.zoo.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftReferenceTest {
	
	static class OOMClass{
        int[] oom = new int[1024 * 100];// 100KB
    }

	public static void main(String[] args) throws InterruptedException {
		ReferenceQueue<OOMClass> queue = new ReferenceQueue<>();
        List<SoftReference<OOMClass>> list = new ArrayList<>();
        while(true){
            for (int i = 0; i < 100; i++) {
                list.add(new SoftReference<OOMClass>(new OOMClass(), queue));
            }
            Thread.sleep(500);
        }
	}

}
