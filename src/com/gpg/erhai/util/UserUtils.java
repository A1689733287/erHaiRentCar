package com.gpg.erhai.util;

import com.gpg.erhai.client.ClientManager;

public class UserUtils {
	public static String getUserName() {
		while (true) {
			System.out.println("请输入用户名：");
			String userName = ScannerUtil.getString().trim();
			if (userName.isEmpty()) {
				System.out.println("用户名不能为空!");
				continue;
			}
			ClientManager.getCm().send(Container.USER_CONTROL + "#" + Container.U_CHECK + "#" + userName);
			if (Container.U_EXIST.equals(ClientManager.getCm().readMsg())) {
				System.out.println(Container.U_EXIST);
				continue;
			} else {
				return userName;
			}
		}

	}

	public static String getUserPwd() {
		while (true) {
			System.out.println("请输入密码：");
			String userPwd = ScannerUtil.getString().trim();
			System.out.println("请再次输入密码：");
			String userPwd_1 = ScannerUtil.getString().trim();
			if (userPwd.equals(userPwd_1)) {
				return userPwd;
			} else {
				System.out.println("两次密码不相同!");
				continue;
			}
		}
	}

	public static String getTel() {
		while (true) {
			boolean flag = true;
			System.out.println("请输入手机号码:");
			String tel = ScannerUtil.getString().trim();
			if (tel.length() != 11) {
				System.out.println("手机号有误，请重新输入:");
				flag = false;
				continue;
			}
			if (!(tel.startsWith("13") || tel.startsWith("14") || tel.startsWith("17") || tel.startsWith("15")
					|| tel.startsWith("18"))) {
				System.out.println("手机号码格式不正确,请重新输入:");
				flag = false;
				continue;
			}
			char[] c = tel.toCharArray();
			for (char d : c) {
				if (!(d >= '0' && d <= '9')) {
					System.out.println("手机号格式有误,请重新输入:");
					flag = false;
					break;
				}
			}
			if (flag) {
				return tel;
			}
		}
	}

}
