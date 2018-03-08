package com.gpg.erhai.ui;

import java.util.List;

import com.gpg.erhai.client.ClientManager;
import com.gpg.erhai.entity.Car;
import com.gpg.erhai.entity.RentRecord;
import com.gpg.erhai.util.Container;
import com.gpg.erhai.util.JsonUtil;
import com.gpg.erhai.util.ScannerUtil;

public class UserManagerUI {
	public UserManagerUI() {
	}

	/**
	 * 用户管理
	 */
	public void userMainUI() {
		List<Car> list;
		ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_ALL_CAR + "#u");
		String readMsg = ClientManager.getCm().readMsg();
		if (readMsg != null) {
			list = JsonUtil.jsonToList(readMsg, Car.class);
			allCarUI(list);
		} else {
			System.out.println("没有汽车信息");
		}
		userManager();
	}

	/**
	 * 用户操作指令
	 */
	public void userManager() {
		while (true) {
			System.out.println("输入0退出");
			System.out.println("输入1+汽车编号 进入租车订单");
			System.out.println("输入2+1 按价格降序排序     2+2 按价格升序排列");
			System.out.println("输入3+类型编号 按类型搜索");
			System.out.println("输入4+品牌编号 按品牌搜索");
			System.out.println("输入5 查看全部汽车");
			System.out.println("输入6 查看我的租车记录");
			System.out.println("输入7+汽车编号 还车");
			String userOp = ScannerUtil.getString();
			userLoginAfterOption(userOp);
		}
	}

	/**
	 * 用户登录之前操作管理
	 * 
	 * @param userOp
	 */
	public void userLoginBeforeOption(String userOp) {
		switch (userOp) {
		case "1":
			MainScreen.getMainScreen().login(2);
			break;
		case "2":
			MainScreen.getMainScreen().register();
			break;
		case "3":
			System.exit(0);
			break;
		default:
			System.out.println(Container.OPTION_ERROR);
			break;
		}
	}

	/**
	 * 用户登录之后操作管理
	 * 
	 * @param adminOp
	 *            用户操作指令
	 */
	public void userLoginAfterOption(String userOp) {
		if (userOp.matches("[1]\\+\\d+")) {
			String[] split = userOp.split("\\+");
			ClientManager.getCm()
					.send(Container.CAR_CONTROL + "#" + Container.QUERY_CAR_BY_ID_TO_RENT + "#" + split[1]);
			String msg = ClientManager.getCm().readMsg();
			if (!msg.equals("")) {
				if (msg.contains("#")) {
					String[] split2 = msg.split("#");
					RentRecord rentRecored = JsonUtil.jsonToObj(split2[1], RentRecord.class);
					System.out.println("借车成功!租车信息如下:");
					System.out.println("编号\t汽车名称\t每日租金\t\t备注\t品牌\t类型\t借车时间");
					System.out.println(
							rentRecored.getId() + "\t" + rentRecored.getCarName() + "\t" + rentRecored.getRentPrice()
									+ "元/天\t" + rentRecored.getCarDesc() + "\t" + rentRecored.getCarBrand() + "\t"
									+ rentRecored.getCarType() + "\t" + rentRecored.getRentTime());
				} else {
					System.out.println(msg);
				}
			} else {
				System.out.println("租借信息为空!");
			}
		} else if (userOp.matches("[2]\\+\\d+")) {
			String[] split = userOp.split("\\+");
			List<Car> list;
			if (split[1].equals("1")) {
				ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_ALL_CAR + "#"
						+ Container.USER_ORDER_BY_RENTPRICE_DESC);
			} else if (split[1].equals("2")) {
				ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_ALL_CAR + "#"
						+ Container.USER_ORDER_BY_RENTPRICE_ASC);
			} else {
				System.out.println(Container.OPTION_ERROR);
				return;
			}
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				list = JsonUtil.jsonToList(readMsg, Car.class);
				allCarUI(list);
			} else {
				System.out.println("汽车信息为空!");
			}
			userManager();
		} else if (userOp.matches("[3]\\+\\d+")) {
			String[] split = userOp.split("\\+");
			List<Car> list;
			ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_ALL_CAR + "#"
					+ Container.USER_SEARCH_BY_CARTYPE + "&" + split[1]);
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				list = JsonUtil.jsonToList(readMsg, Car.class);
				allCarUI(list);
			} else {
				System.out.println("汽车信息为空!");
			}
		} else if (userOp.matches("[4]\\+\\d+")) {
			String[] split = userOp.split("\\+");
			List<Car> list;
			ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_ALL_CAR + "#"
					+ Container.USER_SEARCH_BY_CARBRADN + "&" + split[1]);
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				list = JsonUtil.jsonToList(readMsg, Car.class);
				allCarUI(list);
			} else {
				System.out.println("汽车信息为空!");
			}
		} else if (userOp.matches("[7]\\+\\d+")) {
			String[] split = userOp.split("\\+");
			ClientManager.getCm().send(Container.RENT_CONTROL + "#" + Container.UPDATE_RENTCAR_BY_ID + "#" + split[1]);
			String readLine = ClientManager.getCm().readMsg();
			if (!readLine.equals("[]")) {
				if (readLine.contains("#")) {
					String[] rentUpdate = readLine.split("#");
					System.out.println(rentUpdate[0]);
					RentRecord rentRecord = JsonUtil.jsonToObj(rentUpdate[1], RentRecord.class);
					System.out.println("编号\t汽车名称\t每日租金\t\t总租金\t备注\t品牌\t类型\t借车时间\t\t\t还车时间");
					System.out.println(rentRecord.getId() + "\t" + rentRecord.getCarName() + "\t"
							+ rentRecord.getRentPrice() + "元/天\t" + rentRecord.getAllPrice() + "元\t"
							+ rentRecord.getCarDesc() + "\t" + rentRecord.getCarBrand() + "\t" + rentRecord.getCarType()
							+ "\t" + rentRecord.getRentTime() + "\t" + rentRecord.getReturnCarTime());
				}else {
					System.out.println("没有所选订单记录!");
				}
			} else {
				System.out.println("没有租赁记录!");
			}
		} else if (userOp.equals("0")) {
			System.out.println(Container.EXIT);
			System.exit(0);
		} else if (userOp.equals("5")) {
			userMainUI();
		} else if (userOp.equals("6")) {
			ClientManager.getCm().send(Container.RENT_CONTROL + "#" + Container.QUERY_RENTRECORD_BY_UID + "#1");
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				List<RentRecord> jsonToList = JsonUtil.jsonToList(readMsg, RentRecord.class);
				userRentRecordUI(jsonToList);
			} else {
				System.out.println("租赁信息为空!");
			}
		} else {
			System.out.println(Container.OPTION_ERROR);
		}
	}

	private void userRentRecordUI(List<RentRecord> jsonToList) {
		System.out.println("编号\t汽车名称\t每日租金\t\t总租金\t备注\t品牌\t类型\t借车时间\t\t\t还车时间\t\t\t车辆状态");
		for (RentRecord rentRecord : jsonToList) {
			System.out.println(rentRecord.getId() + "\t" + rentRecord.getCarName() + "\t" + rentRecord.getRentPrice()
					+ "元/天\t" + rentRecord.getAllPrice() + "元\t" + rentRecord.getCarDesc() + "\t"
					+ rentRecord.getCarBrand() + "\t" + rentRecord.getCarType() + "\t" + rentRecord.getRentTime() + "\t"
					+ rentRecord.getReturnCarTime() + "\t" + (rentRecord.getStatus() == 0 ? "已借未还" : "已还"));
		}
	}

	/**
	 * 车查询用户界面
	 * 
	 * @param list
	 */
	private void allCarUI(List<Car> list) {
		System.out.println("==============================================================");
		System.out.println("编号\t汽车名称\t 备注\t 品牌\t 类型\t价格\t\t 是否可租");
		for (Car car : list) {
			System.out.println(car.getCid() + "\t" + car.getCarModel() + "\t" + car.getCarDesc() + " "
					+ car.getCarBrand() + "\t " + car.getCarType() + "\t" + car.getRentPrice() + "元/天\t "
					+ (car.getIsRent() == 0 ? "是" : "否"));
		}
	}

}
