package com.gpg.erhai.server.dispatcher;

import com.gpg.erhai.control.CarBrandControl;
import com.gpg.erhai.control.CarControl;
import com.gpg.erhai.control.CarTypeControl;
import com.gpg.erhai.control.ICommonControl;
import com.gpg.erhai.control.RentControl;
import com.gpg.erhai.control.UserControl;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.server.ServerService;
import com.gpg.erhai.util.Container;

public class CoreDispatcher {
	private static final CoreDispatcher CDP = new CoreDispatcher();
	private ServerService ss = null;

	private CoreDispatcher() {
	}

	public static CoreDispatcher getCoreDispatcherInstance() {
		return CDP;
	}

	/**
	 * 处理传过来的消息
	 * 
	 * @param line
	 *            传入的消息
	 */
	public void handler(String line) {
		/**
		 * 通过#进行字符串的分隔
		 */
		if (line.contains("#")) {
			String[] split = line.split("#");
			String op = split[0];
			String requestOption = split[1];
			String msg = split[2];
			msgOption(op, requestOption, msg);
		}
	}

	/**
	 * 信息操作处理
	 * 
	 * @param op
	 *            信息的操作请求
	 * @param requestOption
	 *            请求的操作指令
	 * @param msg
	 *            传入数据
	 */
	public void msgOption(String op, String requestOption, String msg) {
		if (op.equals(Container.USER_CONTROL)) {
			// 用户控制器
			ICommonControl userControl = Factory.getInstance("userControl", UserControl.class);
			userControl.operation(ss, requestOption, msg);
		}
		if (op.equals(Container.CAR_CONTROL)) {
			//汽车控制器
			ICommonControl carControl = Factory.getInstance("carControl", CarControl.class);
			carControl.operation(ss, requestOption, msg);
		}
		if (op.equals(Container.RENT_CONTROL)) {
			//租赁信息控制器
			ICommonControl rentControl = Factory.getInstance("rentControl", RentControl.class);
			rentControl.operation(ss, requestOption, msg);
		}
		if (op.equals(Container.CARBRAND_CONTROL)) {
			//车品牌控制器
			ICommonControl carBrandControl = Factory.getInstance("carBrandControl", CarBrandControl.class);
			carBrandControl.operation(ss, requestOption, msg);
		}
		if (op.equals(Container.CARTYPE_CONTROL)) {
			//车类型控制器
			ICommonControl carTypeControl = Factory.getInstance("carTypeControl", CarTypeControl.class);
			carTypeControl.operation(ss, requestOption, msg);
		}
	}

	public ServerService getSs() {
		return ss;
	}

	public void setSs(ServerService ss) {
		this.ss = ss;
	}
}
