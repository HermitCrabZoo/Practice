package com.zoo.design.proxyStatic;

public class StaticProxy {

	public static void main(String[] args) {
		Star trueStar=new TrueStar();
		Star proxyStar=new ProxyStar(trueStar);
		proxyStar.interview();
		proxyStar.sign();
		proxyStar.performance();
		proxyStar.gather();
	}

}
