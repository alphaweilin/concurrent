package com.test.concurrent.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class JdkIOTest {
	public static void main(String[] args) {
		test1();
		test2();
	}

	private static void test1() {
		byte[] buf = new byte[512];
		System.out.println("hey, may I have your name, please? ");
		int n = 0;
		try {
			n = System.in.read(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("hello, ");
		System.out.write(buf, 0, n);
	}

	private static void test2() {
		char[] cbuf = new char[256];
		System.out.println("hey, may I have your name, please? ");
		int n = 0;
		Reader r = new InputStreamReader(System.in);
		try {
			n = r.read(cbuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("hello, Mr. " + cbuf[0]);
	}

}
