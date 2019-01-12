package com.zoo.zookeeper;

import java.rmi.RemoteException;

public class Server {

	public static void main(String[] args) throws RemoteException {
		
		String host = "192.168.1.6";
		int port = 13800;
		 
        ServiceProvider provider = new ServiceProvider();
 
        Service service = new HelloService();
        provider.publish(service, host, port);
	}

}
