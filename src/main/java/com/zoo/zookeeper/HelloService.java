package com.zoo.zookeeper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloService extends UnicastRemoteObject implements Service {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1000707931110881489L;

	protected HelloService() throws RemoteException {
	}

	@Override
	public String something(String name) throws RemoteException {
		return String.format("Hello %s", name);
	}

}
