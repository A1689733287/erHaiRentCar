package com.gpg.erhai.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread{
	private Socket socket = null;
	private static BufferedReader br = null;
	private static PrintStream ps = null;

	public Client() {
	}

	@Override
	public void run() {
		try {
			socket = new Socket("127.0.0.1", 8848);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			ps = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("服务器尚未开启！");
		}
	}

	public String readLine() {
		if (br != null) {
			try {
				
				return br.readLine();
			} catch (IOException e) {
				System.out.println("服务器已经断开，重新连接！");
			}
		}
		return null;
	}

	public void send(String msg) {
		if (ps != null) {
			ps.println(msg);
		}
	}
}
