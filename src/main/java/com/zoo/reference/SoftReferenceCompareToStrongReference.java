package com.zoo.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftReferenceCompareToStrongReference {
	static class OOMClass{
        int[] oom = new int[1024];
    }

    public static void main(String[] args) {
//        testStrongReference();
        testSoftReference();
    }

    public static void testStrongReference(){
        List<OOMClass> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new OOMClass());
        }
    }

    public static void testSoftReference(){
        ReferenceQueue<OOMClass> referenceQueue = new ReferenceQueue<>();
        List<SoftReference<OOMClass>> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            OOMClass oomClass = new OOMClass();
            list.add(new SoftReference<OOMClass>(oomClass, referenceQueue));
            oomClass = null;
        }
    }
}
