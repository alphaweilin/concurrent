package com.test.concurrent.funcinterface;

import java.util.function.Function;

public class FunctionTest {
	public static void main(String[] args) {
		System.out.println(upperCase().apply("tomcat"));
	}

	public static Function<String, String> upperCase() {
		return v -> v.toUpperCase();
	}
}
