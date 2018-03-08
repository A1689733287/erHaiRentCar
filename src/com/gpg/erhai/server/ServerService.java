package com.gpg.erhai.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import com.gpg.erhai.entity.User;
import com.gpg.erhai.server.dispatcher.CoreDispatcher;

public class ServerService extends Thread {
	private Socket socket;
	private BufferedReader br = null;
	private PrintStream ps = null;

	public ServerService(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			ps = new PrintStream(socket.getOutputStream());
			String line = null;
			while ((line = readLine()) != null) {
				System.out.println("客户端发送的指令---------->" + line);
				CoreDispatcher dispatcher = CoreDispatcher.getCoreDispatcherInstance();
				dispatcher.setSs(this);
				dispatcher.handler(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readLine() {
		if (br != null) {
			try {
				return br.readLine();
			} catch (IOException e) {
				User key = ServerManager.getServerManager().getKey(this);
				ServerManager.getServerManager().remove(key);
				System.out.println("连接已断开！");
			}
		}
		return null;
	}

	public void out(String msg) {
		if (ps != null) {
			ps.println(msg);
		}
	}
}
