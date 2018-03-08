package com.gpg.erhai.client;

public class ClientManager {
	private final static ClientManager CM = new ClientManager();
	private Client client;

	private ClientManager() {

	}

	public static ClientManager getCm() {
		return CM;
	}

	public void startNet() {
		client = new Client();
		client.start();
	}

	public void send(String msg) {
		client.send(msg);
	}

	public String readMsg() {
		return client.readLine();
	}
}
