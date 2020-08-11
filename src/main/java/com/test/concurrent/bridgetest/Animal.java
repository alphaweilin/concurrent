package com.test.concurrent.bridgetest;

public interface Animal<T> {
	T getObject(T name);
}
