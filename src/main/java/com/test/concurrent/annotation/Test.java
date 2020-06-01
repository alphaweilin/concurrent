package com.test.concurrent.annotation;

import java.lang.reflect.Method;

public class Test {
	@Hello("hello")
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Class clz = Test.class;
		Method method = clz.getMethod("name", String[].class);
		Hello hello = method.getAnnotation(Hello.class);
	}

}
