package com.zoo.design.chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter{
	List<Filter> filters=new ArrayList<>();
	private int index=0;
	
	
	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		if (index>=filters.size()) {
			return;
		}
		Filter filter=filters.get(index);
		index++;
		filter.doFilter(request, response, chain);
	}
	
	
	public FilterChain add(Filter filter) {
		filters.add(filter);
		return this;
	}
	
}
