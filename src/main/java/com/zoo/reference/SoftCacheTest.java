package com.zoo.reference;

public class SoftCacheTest {
	private static int num = 0;

    public static void main(String[] args){
        SoftCache<OOMClass> softCache = new SoftCache<>();
        for (int i = 0; i < 40; i++) {
            softCache.add(new OOMClass("OOM Obj-" + ++num));
        }
        System.out.println("缓存数："+softCache.size());
        for (int i = 0; i < softCache.size(); i++) {
            OOMClass obj = softCache.get(i);
            System.out.println(obj == null ? "null" : obj.name);
        }
        System.out.println("缓存数："+softCache.size());
    }

    static class OOMClass{
        private String name;
        int[] oom = new int[1024 * 100];// 100KB

        public OOMClass(String name) {
            this.name = name;
        }
    }
}
