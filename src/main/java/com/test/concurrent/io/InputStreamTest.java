package com.test.concurrent.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamTest {

	public static void main(String[] args) {

		System.out.println("user.dir: " + System.getProperty("user.dir"));

		File file = new File("src/main/resources/logback.xml");
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(file);
			outputStream = new ByteArrayOutputStream();
			int b;
			while ((b = inputStream.read()) != -1) {
				System.out.print(b + "\t");
				outputStream.write(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		byte[] result = outputStream.toByteArray();
		for (int i = 0; i < result.length; i++) {
			if (i % 10 == 0) {
				System.out.println();
			}
			System.out.print(result[i] + "\t");

		}

	}

}
