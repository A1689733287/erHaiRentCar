package com.gpg.erhai.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private Socket socket;
	private ServerSocket serverSocket;

	public void start() {
		new Thread(){
			public void run() {
				try {
					serverSocket = new ServerSocket(8848);
					System.out.println("服务已开启");
					while (true) {
						socket = serverSocket.accept();
						ServerService serverService = new ServerService(socket);
						serverService.start();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
}
