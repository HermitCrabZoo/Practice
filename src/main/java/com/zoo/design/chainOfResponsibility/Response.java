package com.zoo.design.chainOfResponsibility;

public class Response {
	
	private String responseStr;

	public Response(String responseStr) {
		super();
		this.responseStr = responseStr;
	}

	public String getResponseStr() {
		return responseStr;
	}

	public void setResponseStr(String responseStr) {
		this.responseStr = responseStr;
	}

	@Override
	public String toString() {
		return "Response [responseStr=" + responseStr + "]";
	}
	
}
