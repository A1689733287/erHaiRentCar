package com.gpg.erhai.factory;

import java.util.ResourceBundle;

public class Factory {
	private static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle("instance");
	}

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(String key, Class<T> clazz) {
		String className = bundle.getString(key);
		try {
			return (T) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
