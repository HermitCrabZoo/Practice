package com.zoo.design.chainOfResponsibility;

public class GunsFilter implements Filter {

	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		request.setRequestStr(request.getRequestStr()+"-GunsFilter");
		chain.doFilter(request, response, chain);
		response.setResponseStr(response.getResponseStr()+"-GunsFilter");
	}
}
