package com.gpg.erhai.service.impl;

import com.gpg.erhai.dao.IUserDao;
import com.gpg.erhai.dao.impl.UserDaoImpl;
import com.gpg.erhai.entity.User;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.service.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDao userDao = Factory.getInstance("userDao", UserDaoImpl.class);

	@Override
	public User queryUser(User user) {
		return userDao.queryUser(user);
	}

	@Override
	public int register(User user) {
		return userDao.register(user);
	}

	@Override
	public boolean checkUser(String userName) {
		return userDao.checkUser(userName);
	}

}
