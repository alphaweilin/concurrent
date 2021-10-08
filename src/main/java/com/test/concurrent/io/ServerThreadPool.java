package com.test.concurrent.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerThreadPool {

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(8882)) {
			ExecutorService executor = Executors.newFixedThreadPool(3);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Connection from " + clientSocket.getRemoteSocketAddress());
				executor.submit(new Handler(clientSocket));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Handler implements Runnable{
	private Socket clientSocket;
	
	public Handler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {

		try (Scanner input = new Scanner(clientSocket.getInputStream())) {
			while (true) {
				String request = input.nextLine();
				if ("quit".equals(request)) {
					break;
				}
				System.out
						.println(String.format("From %s : %s", clientSocket.getRemoteSocketAddress(), request));
				String response = "From BIOServer Hello " + request + ".\n";
				clientSocket.getOutputStream().write(response.getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
