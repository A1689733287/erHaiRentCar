package com.gpg.erhai.control;

import com.gpg.erhai.entity.User;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.server.ServerManager;
import com.gpg.erhai.server.ServerService;
import com.gpg.erhai.service.IUserService;
import com.gpg.erhai.service.impl.UserServiceImpl;
import com.gpg.erhai.util.Container;
import com.gpg.erhai.util.JsonUtil;

public class UserControl implements ICommonControl {
	private IUserService userServiceImpl = Factory.getInstance("userService", UserServiceImpl.class);

	/**
	 * 执行服务端接受指令要求
	 * 
	 * @param ss
	 *            服务对象
	 * @param op
	 *            操作指令
	 * @param msg
	 *            传递消息
	 */
	public void operation(ServerService ss, String requestOption, String msg) {
		User user = null;
		switch (requestOption) {
		case Container.REGISTER_USER:
			user = JsonUtil.jsonToObj(msg, User.class);
			int i = userServiceImpl.register(user);
			if (i > 0) {
				opData(ss, Container.REGISTER_SUCCESS);
			} else {
				opData(ss, Container.REGISTER_SUCCESS);
			}
			break;

		case Container.QUERY_USER:
			user = JsonUtil.jsonToObj(msg, User.class);
			if (!ServerManager.getServerManager().checkKey(user)) {
				User u = userServiceImpl.queryUser(user);
				if (u != null && u.getUserName().equals(user.getUserName())
						&& u.getUserPwd().equals(user.getUserPwd())) {
					ServerManager.getServerManager().add(u, ss);
					opData(ss, JsonUtil.objToString(u));
				} else {
					opData(ss, JsonUtil.objToString(u));
				}
			} else {
				opData(ss, Container.U_EXIST);
			}
			break;
		case Container.U_CHECK:
			if (userServiceImpl.checkUser(msg)) {
				opData(ss, Container.U_EXIST);
			} else {
				opData(ss, Container.U_CHECK);
			}
			break;
		}
	}

	/**
	 * 传递消息方法
	 * 
	 * @param ss
	 *            服务器消息对象
	 * @param msg
	 *            发送到服务端的消息
	 */
	public void opData(ServerService ss, String msg) {
		ServerManager.getServerManager().out(ss, msg);
	}
}
