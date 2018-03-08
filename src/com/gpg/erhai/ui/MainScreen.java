package com.gpg.erhai.ui;

import com.gpg.erhai.client.ClientManager;
import com.gpg.erhai.entity.User;
import com.gpg.erhai.util.Container;
import com.gpg.erhai.util.JsonUtil;
import com.gpg.erhai.util.MD5Util;
import com.gpg.erhai.util.ScannerUtil;
import com.gpg.erhai.util.UserUtils;

public class MainScreen {
	private AdminManagerUI adminManagerUI = new AdminManagerUI();
	private UserManagerUI userManagerUI = new UserManagerUI();
	private static final MainScreen MS = new MainScreen();

	private MainScreen() {

	}

	public static MainScreen getMainScreen() {
		return MS;
	}

	public void initScreen() {
		while (true) {
			System.out.println("================================");
			System.out.println("	欢迎访问二嗨租车");
			System.out.println("================================");
			System.out.println("u.用户\t a.管理员");
			System.out.println("请选择输入:");
			String ch = ScannerUtil.getString();
			main(ch);
		}
	}

	public void userUI() {
		while (true) {
			System.out.println("================================");
			System.out.println("	欢迎访问二嗨租车");
			System.out.println("================================");
			System.out.println("1.登录 \t 2.注册  \t 3.退出");
			String userOp = ScannerUtil.getString();
			userManagerUI.userLoginBeforeOption(userOp);
		}
	}

	public void adminUI() {
		while (true) {
			System.out.println("================================");
			System.out.println("	欢迎访问二嗨租车");
			System.out.println("================================");
			System.out.println("1.登录 \t 2.退出  ");
			String adminOp = ScannerUtil.getString();
			adminManagerUI.adminLoginBeforeOption(adminOp);
		}
	}

	public void main(String ch) {
		switch (ch) {
		case "a":
			adminUI();
			break;
		case "u":
			userUI();
		default:
			System.out.println(Container.OPTION_ERROR);
			break;
		}
	}

	/**
	 * 管理员与用户管理选择
	 * 
	 * @param user
	 */
	public void toUserManager(User user) {
		switch (user.getUserType()) {
		case 1:
			adminManagerUI.adminMainUI();
			break;
		case 2:
			userManagerUI.userMainUI();
			break;
		default:
			System.out.println(Container.OPTION_ERROR);
			break;
		}
	}

	/**
	 * 用户登录
	 */
	public void login(int userType) {
		while (true) {
			System.out.println("==============登录=============>>>>");
			System.out.println("请输入用户名:");
			String userName = ScannerUtil.getString();
			System.out.println("请输入密码:");
			String userPwd = ScannerUtil.getString();
			if (userName.isEmpty() || userPwd.isEmpty()) {
				System.out.println("用户名或密码不能为空!");
				continue;
			} else {
				User user = new User(0, userName, MD5Util.encoder(userPwd), userType);
				String juser = JsonUtil.objToString(user);
				ClientManager.getCm().send(Container.USER_CONTROL + "#" + Container.QUERY_USER + "#" + juser);
				String msg = ClientManager.getCm().readMsg();
				if (msg.equals(Container.U_EXIST)) {
					System.out.println("该用户已登录");
					continue;
				} else {
					User u = JsonUtil.jsonToObj(msg, User.class);
					if (u == null) {
						System.out.println("用户或密码错误!!");
						System.out.println("**********重新登录？********");
						System.out.println("输入1. 继续登录\t 2.返回主界面");
						String string = ScannerUtil.getString();
						switch (string) {
						case "1":
							continue;
						case "2":
							initScreen();
							break;
						default:
							System.out.println("指令错误!");
							break;
						}
					} else {
						toUserManager(u);
					}
				}
			}
		}
	}

	/**
	 * 用户注册
	 */
	public void register() {
		while (true) {
			System.out.println("==============注册=============>>>>");
			String userName = UserUtils.getUserName();
			String userPwd = MD5Util.encoder(UserUtils.getUserPwd());
			User user = new User(0, userName, userPwd, 2);
			ClientManager.getCm()
					.send(Container.USER_CONTROL + "#" + Container.REGISTER_USER + "#" + JsonUtil.objToString(user));
			String msg = ClientManager.getCm().readMsg();
			if (msg.equals(Container.REGISTER_SUCCESS)) {
				initScreen();
			}
			if (msg.equals(Container.REGISTER_FAIL)) {
				System.out.println("注册失败，是否重新注册? 1.注册  \t 2.返回主界面");
				char ch = ScannerUtil.getChar();
				if (ch == '1') {
					continue;
				} else {
					break;
				}
			}
		}
	}

}
