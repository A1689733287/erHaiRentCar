package com.gpg.erhai.dao;

import com.gpg.erhai.entity.User;

public interface IUserDao {
	/**
	 * 查询用户
	 * 
	 * @param user
	 * @return
	 */
	User queryUser(User user);

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return
	 */
	int register(User user);

	/**
	 * 查询 用户名
	 * 
	 * @param userName
	 * @return
	 */
	boolean checkUser(String userName);
}
