package com.test.concurrent.bridgetest;

public class Cat implements Animal<String> {
	
	@Override
	public String getObject(String name) {
		return "tomcat";
	}


}
