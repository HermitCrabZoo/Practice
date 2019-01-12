package com.zoo.design.chainOfResponsibility;

public class Request {
	
	private String requestStr;

	public Request(String requestStr) {
		super();
		this.requestStr = requestStr;
	}

	public String getRequestStr() {
		return requestStr;
	}

	public void setRequestStr(String requestStr) {
		this.requestStr = requestStr;
	}

	@Override
	public String toString() {
		return "Request [requestStr=" + requestStr + "]";
	}
	
}
