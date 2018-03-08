package com.gpg.erhai.service;

import com.gpg.erhai.entity.User;

public interface IUserService {
	User queryUser(User user);
	int register(User user);
	boolean checkUser(String userName);
}
