package com.home.mvc.util;

public enum Result {
	SUCCESS(0,"test some thing");
	private final int code;
	private final String msg;
	Result(int code,String msg){
		this.code =code;
		this.msg = msg;
	}
	public int getCode(){
		return this.code;
	}
	public String jsonResult(){
		return "{\"code\":\""+code+"\",\"msg\":\""+msg+"\"}";
	}
	@Override
	public String toString(){
		return "{\"code\":\""+code+"\",\"msg\":\""+msg+"\"}";
	}
}
