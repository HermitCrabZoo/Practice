package com.zoo.zookeeper;

import java.rmi.RemoteException;

public class Client {

	public static void main(String[] args) throws RemoteException, InterruptedException {
		ServiceConsumer consumer = new ServiceConsumer();
        while (true) {
            Service service = consumer.lookup();
            if (service!=null) {
            	String result = service.something("Jack");
            	System.out.println(result);
			}
            Thread.sleep(3000);
        }
	}

}
