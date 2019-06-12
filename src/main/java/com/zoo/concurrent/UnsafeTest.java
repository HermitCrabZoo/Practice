package com.zoo.concurrent;

import java.lang.reflect.Field;

@SuppressWarnings("restriction")
public class UnsafeTest {
	private Object match;
	private Object next;
	
	public static void main(String[] args) {
		Object one = new Object();
		Object two = new Object();
		UnsafeTest unsafeTest = new UnsafeTest();
		unsafeTest.match = one;
		unsafeTest.next = two;
		System.out.println(unsafeTest.match);
		System.out.println(unsafeTest.next);
		UNSAFE.compareAndSwapObject(unsafeTest, matchOffset, one, null);
		UNSAFE.compareAndSwapObject(unsafeTest, nextOffset, two, null);
		System.out.println(unsafeTest.match);
		System.out.println(unsafeTest.next);
		System.out.println(one);
		System.out.println(two);
		
	}
	
	
	private static final sun.misc.Unsafe UNSAFE;
    private static final long matchOffset;
    private static final long nextOffset;

    static {
        try {
            Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            sun.misc.Unsafe unsafe = (sun.misc.Unsafe) f.get(null);
            UNSAFE = unsafe;
            Class<?> k = UnsafeTest.class;
            matchOffset = UNSAFE.objectFieldOffset
                (k.getDeclaredField("match"));
            nextOffset = UNSAFE.objectFieldOffset
                (k.getDeclaredField("next"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
