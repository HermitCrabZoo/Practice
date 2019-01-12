package com.zoo.design.chainOfResponsibility;

public class ChainOfResponsibility {

	//责任链模式:同时能同时处理请求与响应的方法,处理请求的过滤器是正序,处理响应的过滤器是倒序
	
	public static void main(String[] args) {
		
		Request request=new Request("请求字符串");
		Response response=new Response("响应字符串");
		
		FilterChain filterChain=new FilterChain();
		filterChain.add(new HTMLFilter()).add(new GunsFilter());
		filterChain.doFilter(request, response, filterChain);
		
		System.out.println(request);
		System.out.println(response);
	}

}
