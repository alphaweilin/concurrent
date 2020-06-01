package com.test.concurrent.another;

import java.io.IOException;

public class StandardInputOutput {
	public static void main(String args[]) {
		int b;
		try {
			System.out.println("please Input:");
			while ((b = System.in.read()) != -1) {
				System.out.print((char) b);
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
