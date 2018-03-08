package com.gpg.erhai.entity;

public class User {
	private int id;
	private String userName;
	private String userPwd;
	private int userType;

	public User() {
		super();
	}

	public User(String userName, String userPwd) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
	}

	public User(int id, String userName, String userPwd, int userType) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPwd=" + userPwd + ", userType=" + userType + "]";
	}

}
