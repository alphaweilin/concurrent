package com.test.concurrent.another;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

public class Base64Test {
	public static void main(String[] args) {
		System.out.println(fileToBase64("C:\\Users\\WeiLin\\Downloads\\exportedData\\ResultSet1-1.csv"));
	}
	
	public static String fileToBase64(String path) {
		String base64 = null;
		InputStream in = null;
		try {
			File file = new File(path);
			in = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			in.read(bytes);
			base64 = Base64.encodeBase64String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return base64;
	}

	public static void base64ToFile(String destPath, String base64, String fileName) {
		File file = null;
		String filePath = destPath;
		File dir = new File(filePath);
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs();
		}
		BufferedOutputStream bos = null;
		java.io.FileOutputStream fos = null;
		try {
			byte[] bytes = Base64.decodeBase64(base64);
			file = new File(filePath + "/" + fileName);
			fos = new java.io.FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
