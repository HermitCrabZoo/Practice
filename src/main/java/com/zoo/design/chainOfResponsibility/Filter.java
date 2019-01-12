package com.zoo.design.chainOfResponsibility;

public interface Filter {
	void doFilter(Request request,Response response,FilterChain chain);
}
