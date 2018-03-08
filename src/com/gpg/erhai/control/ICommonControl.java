package com.gpg.erhai.control;

import com.gpg.erhai.server.ServerService;

public interface ICommonControl {
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
	public void operation(ServerService ss, String requestOption, String msg);

	/**
	 * 传递消息方法
	 * 
	 * @param ss
	 *            服务器消息对象
	 * @param msg
	 *            发送到服务端的消息
	 */
	public void opData(ServerService ss, String msg);
}
