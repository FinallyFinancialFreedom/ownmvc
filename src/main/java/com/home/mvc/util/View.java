package com.home.mvc.util;

public class View {

	private String filename = "";
	public View(String filename){
		this.filename = filename;
	}
	public String toString(){
		return filename;
	}
}
