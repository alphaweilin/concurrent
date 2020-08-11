package com.test.concurrent.bridgetest;

public class Dog<T> implements Animal<T> {

	@Override
	public T getObject(T name) {
		// TODO Auto-generated method stub
		return name;
	}

}
