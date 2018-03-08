package com.gpg.erhai.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.gpg.erhai.entity.User;

public class ServerManager {
	private static final ServerManager SM = new ServerManager();

	private Map<User, ServerService> map = new HashMap<>();

	private ServerManager() {

	}

	public static ServerManager getServerManager() {
		return SM;
	}

	public void add(User user, ServerService ss) {
		getMap().put(user, ss);
	}

	public void remove(User user) {
		map.remove(user);
	}

	public User getKey(ServerService ss) {
		Set<User> set = map.keySet();
		for (User user : set) {
			if (ss.equals(map.get(user))) {
				return user;
			}
		}
		return null;
	}

	public void out(ServerService ss, String msg) {
		ss.out(msg);
	}

	public Map<User, ServerService> getMap() {
		return map;
	}

	public void setMap(Map<User, ServerService> map) {
		this.map = map;
	}

	public boolean checkKey(User user) {
		Set<User> set = map.keySet();
		for (User u : set) {
			if (set != null && user.getUserName().equals(u.getUserName())) {
				return true;
			}
		}
		return false;
	}
}
