package com.zoo.design.proxyDynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StarHandler implements InvocationHandler {

	private Star trueStar;
	
	public StarHandler(Star trueStar) {
		super();
		this.trueStar = trueStar;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("JDK动态代理方法前");
		System.out.println("代理-面谈->代理-签约");
		
		Object val=method.invoke(trueStar, args);
		
		System.out.println("代理-收款");
		System.out.println("JDK动态代理方法后");
		
		return val;
	}

}
