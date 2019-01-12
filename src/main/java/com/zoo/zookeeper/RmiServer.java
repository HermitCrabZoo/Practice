package com.zoo.zookeeper;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		int port=1099;
		String url="rmi://localhost:1099/com.zoo.zookeeper.HelloService";
		LocateRegistry.createRegistry(port);
		Naming.rebind(url, new HelloService());
	}

}
