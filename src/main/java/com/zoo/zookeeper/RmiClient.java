package com.zoo.zookeeper;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RmiClient {

	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		String url="rmi://localhost:1099/com.zoo.zookeeper.HelloService";
		Remote remote=Naming.lookup(url);
		Service service=(Service) remote;
		System.out.println(service.something("Wangfei"));
	}

}
