package com.zoo.concurrent;

public class RemoveLock implements Runnable{

	public static void main(String[] args) {
		new Thread(new RemoveLock()).start();
		new Thread(new RemoveLock()).start();
	}

	@Override
	public void run() {
		for(int i = 0 ; i<100;i++) {
			combine(i+"", ""+i);
		}
	}
	//对StringBuffer的操作不涉及线程安全问题，所以锁会在编译时被移除
	public String combine(String one,String two) {
		StringBuffer sb =new StringBuffer();
		sb.append(one).append(two);
		return sb.toString();
	}

}
