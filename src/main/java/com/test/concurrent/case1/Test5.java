package com.test.concurrent.case1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class Test5 {

	public static void main(String[] args) {
		AtomicInteger i = new AtomicInteger(5);
//		i.updateAndGet(operand -> operand * 5);
		updateAndGet(i, operand -> operand / 2);
		System.out.println(i);
	}
	
	public static void updateAndGet(AtomicInteger i, IntUnaryOperator operator) {
		while(true) {
			int prev = i.get();
			int next = operator.applyAsInt(prev);
			if(i.compareAndSet(prev, next)) {
				break;
			}
		}

	}

}
