package com.zoo.zookeeper;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 继承该接口的，所有方法必须抛出RemoteException异常
 * @author ZOO
 *
 */
public interface Service extends Remote{
	public String something(String name)throws RemoteException;
}
