package com.zoo.design.proxyDynamic;

import java.lang.reflect.Proxy;

public class DynamicProxy {
	
	public static void main(String[] args) {
		Star trueStar=new TrueStar();//实际的对象
		StarHandler starHandler=new StarHandler(trueStar);//代理行为处理器
		Star proxyStar=(Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Star.class}, starHandler);//生成代理对象
		proxyStar.performance();//执行代理对象的方法
		proxyStar.eat();
		proxyStar.getClass().getClassLoader();
	}
}
