package com.test.concurrent.vm;

import org.openjdk.jol.info.ClassLayout;

public class ClassStructTest {

	public static void main(String[] args) {
		//klass pointer : compress 4 bytes, without compress 8 bytes
		//header: {markword(8bytes), klass pointer(4bytes)}; instance data, padding
		Object object = new Object();
		System.out.println(ClassLayout.parseInstance(object).toPrintable());
		
		//header: {markword(8bytes), klass pointer(4bytes), length(4bytes)}; instance data, padding
		int[] arr = new int[1];
		System.out.println(ClassLayout.parseInstance(arr).toPrintable());
	}

}
