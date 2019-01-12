package com.zoo.concurrent;

import java.time.Clock;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;
import org.junit.Test;

public class ForkJoinTest {


	private static Clock clock=Clock.systemUTC();
	
	public static void main(String[] args) {
		
		
	}

	@Test
	public void testForkJoin() {
		long s=clock.millis();
		
		ForkJoinPool pool=new ForkJoinPool();
		ForkJoinCalculate calculate=new ForkJoinCalculate(0, 100000000000L);
		Long sum=pool.invoke(calculate);
		
		long e=clock.millis();
		System.out.println(sum);
		System.out.println("ForkJoin 耗时："+(e-s));
	}
	
	
	@Test
	public void testForLoop() {
		long s=clock.millis();
		
		long sum=0;
		for (long i = 0; i < 100000000000L; i++) {
			sum+=i;
		}
		
		long e=clock.millis();
		System.out.println(sum);
		System.out.println("ForLoop 耗时："+(e-s));
	}
	
	
	@Test
	public void testParallelStream() {
		long s=clock.millis();
		
		long sum=LongStream.rangeClosed(0, 100000000000L).parallel().sum();
		
		long e=clock.millis();
		System.out.println(sum);
		System.out.println("ParallelStream 耗时："+(e-s));
	}
}
