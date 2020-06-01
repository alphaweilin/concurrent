package com.test.concurrent.io;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BIOServer {

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(8881)) {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Connection from " + clientSocket.getRemoteSocketAddress());
				try (Scanner input = new Scanner(clientSocket.getInputStream())) {
					while (true) {
						String request = input.nextLine();
						if ("quit".equals(request)) {
							break;
						}
						System.out
								.println(String.format("From %s : %s", clientSocket.getRemoteSocketAddress(), request));
						String response = "From BIOServer Hello " + request +".\n";
						clientSocket.getOutputStream().write(response.getBytes());
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
