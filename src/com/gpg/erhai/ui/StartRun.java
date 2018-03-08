package com.gpg.erhai.ui;

import com.gpg.erhai.client.ClientManager;

public class StartRun {
	public static void main(String[] args) {
		ClientManager.getCm().startNet();
		MainScreen.getMainScreen().initScreen();
	}
}
