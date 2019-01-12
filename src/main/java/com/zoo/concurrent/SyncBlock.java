package com.zoo.concurrent;
//同步锁
public class SyncBlock {
	public int i;
	
	public synchronized void increase() {
		i++;
	}
}
