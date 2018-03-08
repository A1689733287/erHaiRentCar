package com.gpg.erhai.ui;

import java.util.List;

import com.gpg.erhai.client.ClientManager;
import com.gpg.erhai.entity.Car;
import com.gpg.erhai.entity.CarBrand;
import com.gpg.erhai.entity.CarType;
import com.gpg.erhai.entity.RentRecord;
import com.gpg.erhai.util.Container;
import com.gpg.erhai.util.JsonUtil;
import com.gpg.erhai.util.ScannerUtil;

public class AdminManagerUI {

	public AdminManagerUI() {
	}

	/**
	 * 管理员管理
	 */
	public void adminMainUI() {
		ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_ALL_CAR + "#a");
		String readMsg = ClientManager.getCm().readMsg();
		if (readMsg != null) {
			List<Car> list = JsonUtil.jsonToList(readMsg, Car.class);
			allCarUI(list);
			adminManager();
		} else {
			System.out.println("没有汽车数据!");
		}
	}

	/**
	 * 管理员主页面操作指令UI
	 */
	public void adminManager() {
		while (true) {
			System.out.println("输入0退出");
			System.out.println("输入1+汽车编号 查看指定汽车");
			System.out.println("输入2+1 按价格降序排序     2+2 按价格升序排列");
			System.out.println("输入3+类型编号 按类型搜索");
			System.out.println("输入4 添加类型和品牌");
			System.out.println("输入4+品牌编号 按品牌搜索");
			System.out.println("输入5 查看全部汽车");
			System.out.println("输入6 添加汽车");
			System.out.println("输入7+汽车编号 修改汽车信息");
			System.out.println("输入8查看租车记录");
			String adminOp = ScannerUtil.getString();
			adminLoginAfterOption(adminOp);
		}
	}

	/**
	 * 管理员登录之前的操作
	 * 
	 * @param adminOp
	 *            操作指令
	 */
	public void adminLoginBeforeOption(String adminOp) {
		switch (adminOp) {
		case "1":
			MainScreen.getMainScreen().login(1);
			break;
		case "2":
			System.exit(0);
			break;
		default:
			System.out.println(Container.OPTION_ERROR);
			break;
		}
	}

	/**
	 * 管理员管理
	 * 
	 * @param adminOp
	 *            管理员操作指令
	 */
	public void adminLoginAfterOption(String adminOp) {
		if (adminOp.matches("[1]\\+\\d+")) {
			String[] split = adminOp.split("\\+");
			ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_CAR_BY_ID + "#" + split[1]);
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				Car car = JsonUtil.jsonToObj(readMsg, Car.class);
				singleCarUI(car);
			}
		} else if (adminOp.matches("[2]\\+\\d+")) {
			String[] split = adminOp.split("\\+");
			List<Car> list = null;
			if (split[1].equals("1")) {
				ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_ALL_CAR + "#"
						+ Container.ADMIN_ORDER_BY_RENTPRICE_DESC);
			} else if (split[1].equals("2")) {
				ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_ALL_CAR + "#"
						+ Container.ADMIN_ORDER_BY_RENTPRICE_ASC);
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
		} else if (adminOp.matches("[3]\\+\\d+")) {
			String[] split = adminOp.split("\\+");
			List<Car> list;
			ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_ALL_CAR + "#"
					+ Container.ADMIN_SEARCH_BY_CARTYPE + "&" + split[1]);
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				list = JsonUtil.jsonToList(readMsg, Car.class);
				allCarUI(list);
			} else {
				System.out.println("汽车信息为空!");
			}
		} else if (adminOp.matches("[4]\\+\\d+")) {
			String[] split = adminOp.split("\\+");
			List<Car> list;
			ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_ALL_CAR + "#"
					+ Container.ADMIN_SEARCH_BY_CARBRADN + "&" + split[1]);
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				list = JsonUtil.jsonToList(readMsg, Car.class);
				allCarUI(list);
			} else {
				System.out.println("汽车信息为空!");
			}
		} else if (adminOp.matches("[6]\\+\\d+")) {
			String[] split = adminOp.split("\\+");
			List<Car> list;
			ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_ALL_CAR + "#"
					+ Container.ADMIN_SEARCH_BY_CARBRADN + "&" + split[1]);
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				list = JsonUtil.jsonToList(readMsg, Car.class);
				allCarUI(list);
			} else {
				System.out.println("汽车信息为空!");
			}
		} else if (adminOp.matches("[7]\\+\\d+")) {
			String[] split = adminOp.split("\\+");
			ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.QUERY_CAR_BY_ID + "#" + split[1]);
			String readLine = ClientManager.getCm().readMsg();
			Car car = JsonUtil.jsonToObj(readLine, Car.class);
			singleCarUI(car);
			System.out.println("请输入你要修改的内容编号：");
			System.out.println("1.租赁价格\t2.上架下架");
			String string = ScannerUtil.getString();
			switch (string) {
			case "1":
				System.out.println("请输入新的价格:");
				String price = ScannerUtil.getString();
				ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.UPDATE_CAR_BY_ID + "#"
						+ Container.UPDATE_CAR_RENT_PRICE_STATE + "&" + price + "&" + split[1]);
				String[] split1 = ClientManager.getCm().readMsg().split("&");
				System.out.println(split1[0]);
				Car car1 = JsonUtil.jsonToObj(split1[1], Car.class);
				singleCarUI(car1);
				break;

			case "2":
				System.out.println("是否上架：0.上架\t1.下架");
				String isOnline = ScannerUtil.getString();
				ClientManager.getCm().send(Container.CAR_CONTROL + "#" + Container.UPDATE_CAR_BY_ID + "#"
						+ Container.UPDATE_CAR_ONLINE_STATE + "&" + isOnline + "&" + split[1]);
				String[] split2 = ClientManager.getCm().readMsg().split("&");
				System.out.println(split2[0]);
				Car car2 = JsonUtil.jsonToObj(split2[1], Car.class);
				singleCarUI(car2);
				break;
			}
		} else if (adminOp.equals("0")) {
			System.out.println(Container.EXIT);
			System.exit(0);
		} else if (adminOp.equals("4")) {
			System.out.println("输入 ：1.添加类型\t2.添加品牌");
			String string = ScannerUtil.getString();
			switch (string) {
			case "1":
				System.out.println("----------------------------");
				System.out.println("当前已有的类型:");
				ClientManager.getCm().send(Container.CARTYPE_CONTROL + "#" + Container.QUERY_ALL_CARTYPE + "#1");
				System.out.println("类型编号 \t 类型名");
				String read = ClientManager.getCm().readMsg();
				List<CarType> carTypes = JsonUtil.jsonToList(read, CarType.class);
				for (CarType carType : carTypes) {
					System.out.println(carType.getId() + "\t" + carType.getTypeName());
				}
				System.out.print("请输入车类型:");
				String carType = ScannerUtil.getString();
				ClientManager.getCm().send(Container.CARTYPE_CONTROL + "#" + Container.INSERT_CARTYPE + "#" + carType);
				String readMsg = ClientManager.getCm().readMsg();
				if (readMsg.equals(Container.INSERT_SUCCESS)) {
					System.out.println("添加成功!");
				} else {
					System.out.println("添加失败!");
				}
				break;
			case "2":
				System.out.println("----------------------------");
				System.out.println("当前已有的品牌:");
				ClientManager.getCm().send(Container.CARBRAND_CONTROL + "#" + Container.QUERY_ALL_CARBRAND + "#1");
				System.out.println("品牌编号 \t 品牌名");
				List<CarBrand> carBrands = JsonUtil.jsonToList(ClientManager.getCm().readMsg(), CarBrand.class);
				for (CarBrand carBrand : carBrands) {
					System.out.println(carBrand.getId() + "\t" + carBrand.getBrandName());
				}
				System.out.print("请输入车品牌:");
				String carBrand = ScannerUtil.getString();
				ClientManager.getCm()
						.send(Container.CARBRAND_CONTROL + "#" + Container.INSERT_CARBRAND + "#" + carBrand);
				String readM = ClientManager.getCm().readMsg();
				if (readM.equals(Container.INSERT_SUCCESS)) {
					System.out.println("添加成功!");
				} else {
					System.out.println("添加失败!");
				}
				break;
			default:
				System.out.println("指令错误!");
				break;
			}
		} else if (adminOp.equals("5")) {
			adminMainUI();
		} else if (adminOp.equals("6")) {
			System.out.println("===========================");
			System.out.println("请分别输入一下信息：");
			System.out.println("----------------------------");
			System.out.println("类型如下:");
			ClientManager.getCm().send(Container.CARTYPE_CONTROL + "#" + Container.QUERY_ALL_CARTYPE + "#1");
			System.out.println("类型编号 \t 类型名");
			String readMsg = ClientManager.getCm().readMsg();
			List<CarType> carTypes = JsonUtil.jsonToList(readMsg, CarType.class);
			for (CarType carType : carTypes) {
				System.out.println(carType.getId() + "\t" + carType.getTypeName());
			}
			System.out.print("请选择类型编号:");
			String type = ScannerUtil.getString();
			System.out.println("-----------------------------");
			System.out.println("品牌如下:");
			ClientManager.getCm().send(Container.CARBRAND_CONTROL + "#" + Container.QUERY_ALL_CARBRAND + "#1");
			System.out.println("品牌编号 \t 品牌名");
			List<CarBrand> carBrands = JsonUtil.jsonToList(ClientManager.getCm().readMsg(), CarBrand.class);
			for (CarBrand carBrand : carBrands) {
				System.out.println(carBrand.getId() + "\t" + carBrand.getBrandName());
			}
			System.out.print("请选择品牌编号:");
			String brand = ScannerUtil.getString();
			System.out.println("------------------------------");
			System.out.print("型号：");
			String model = ScannerUtil.getString();
			System.out.println("------------------------------");
			System.out.print("车牌号：");
			String carNumber = ScannerUtil.getString();
			System.out.println("------------------------------");
			System.out.print("概要：");
			String desc = ScannerUtil.getString();
			System.out.println("------------------------------");
			System.out.print("颜色：");
			String color = ScannerUtil.getString();
			System.out.println("------------------------------");
			System.out.print("汽车价格：");
			String price = ScannerUtil.getString();
			System.out.println("------------------------------");
			System.out.print("每日租金：");
			String rentPrice = ScannerUtil.getString();
			System.out.println("------------------------------");
			System.out.print("是否可借(0:可借 1:不可借)：");
			String isRent = ScannerUtil.getString();
			System.out.println("------------------------------");
			System.out.print("是否上架(0:上架 1:下架)：");
			String isOnline = ScannerUtil.getString();
			Car car = new Car(0, model, carNumber, desc, color, Float.parseFloat(price), brand, type,
					Float.parseFloat(rentPrice), Integer.parseInt(isRent), Integer.parseInt(isOnline));
			ClientManager.getCm()
					.send(Container.CAR_CONTROL + "#" + Container.INSERT_CAR + "#" + JsonUtil.objToString(car));
			System.out.println(ClientManager.getCm().readMsg());
		} else if (adminOp.equals("8")) {
			ClientManager.getCm().send(Container.RENT_CONTROL + "#" + Container.QUERY_ALL_RENTRECORD + "#1");
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				List<RentRecord> jsonToList = JsonUtil.jsonToList(readMsg, RentRecord.class);
				allrentRecordUI(jsonToList);
				rentManager();
			} else {
				System.out.println("没有租车记录!");
			}
		} else {
			System.out.println(Container.OPTION_ERROR);
		}
	}

	/**
	 * 查询单个汽车的信息UI
	 * 
	 * @param car
	 */
	private void singleCarUI(Car car) {
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("编号\t汽车名称\t 备注\t  品牌\t 类型\t价格\t\t 是否可租\t 是否上架");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		System.out.println(car.getCid() + "\t" + car.getCarModel() + "\t " + car.getCarDesc() + " " + car.getCarBrand()
				+ "\t " + car.getCarType() + "\t " + car.getRentPrice() + "/天\t " + (car.getIsRent() == 0 ? "是" : "否")
				+ "\t" + (car.getIsOnline() == 0 ? "上架" : "已下架"));
	}

	/**
	 * 查询汽车集合的UI界面
	 * 
	 * @param list
	 *            包含汽车的集合
	 */
	private void allCarUI(List<Car> list) {
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("编号\t汽车名称\t备注\t品牌\t 类型\t价格\t\t 是否可租\t 是否上架");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		for (Car car : list) {
			System.out.println(car.getCid() + "\t" + car.getCarModel() + "\t" + car.getCarDesc() + " "
					+ car.getCarBrand() + "\t " + car.getCarType() + "\t" + car.getRentPrice() + "/天\t\t "
					+ (car.getIsRent() == 0 ? "是" : "否") + "\t" + (car.getIsOnline() == 0 ? "上架" : "已下架"));
		}
	}

	/**
	 * 租车记录管理界面
	 */
	public void rentManager() {
		while (true) {
			System.out.println("输入0退出");
			System.out.println("输入5 查看全部汽车");
			System.out.println("输入6 查看全部租赁记录");
			System.out.println("输入7+用户编号  查看 指定用户的租赁信息");
			System.out.println("输入8+汽车编号  查看 指定汽车的租赁信息");
			String option = ScannerUtil.getString();
			rentOptionUI(option);
		}
	}

	/**
	 * 查看全部租赁记录的UI操作
	 * 
	 * @param option
	 *            操作的指令
	 */
	public void rentOptionUI(String option) {
		if (option.startsWith("7+")) {
			String[] split = option.split("\\+");
			ClientManager.getCm()
					.send(Container.RENT_CONTROL + "#" + Container.QUERY_RENTRECORD_BY_UID + "#" + split[1] + "&1");
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				List<RentRecord> jsonToList = JsonUtil.jsonToList(readMsg, RentRecord.class);
				allrentRecordUI(jsonToList);
			} else {
				System.out.println("租赁信息为空");
			}
		} else if (option.startsWith("8+")) {
			String[] split = option.split("\\+");
			ClientManager.getCm()
					.send(Container.RENT_CONTROL + "#" + Container.QUERY_RENTRECORD_BY_CID + "#" + split[1]);
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				List<RentRecord> jsonToList = JsonUtil.jsonToList(readMsg, RentRecord.class);
				allrentRecordUI(jsonToList);
			} else {
				System.out.println("租赁信息为空");
			}
		} else if (option.equals("5")) {
			adminLoginAfterOption(option);
		} else if (option.equals("6")) {
			ClientManager.getCm().send(Container.RENT_CONTROL + "#" + Container.QUERY_ALL_RENTRECORD + "#1");
			String readMsg = ClientManager.getCm().readMsg();
			if (!readMsg.equals("[]")) {
				List<RentRecord> jsonToList = JsonUtil.jsonToList(readMsg, RentRecord.class);
				allrentRecordUI(jsonToList);
			} else {
				System.out.println("租赁信息为空");
			}
		} else if (option.equals("0")) {
			adminLoginAfterOption(option);
		}
	}

	/**
	 * 租车记录UI
	 * 
	 * @param jsonToList
	 */
	private void allrentRecordUI(List<RentRecord> jsonToList) {
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("编号\t汽车编号\t汽车名称\t用户编号\t用户名\t每日租金\t\t总租金\t备注\t品牌\t类型\t借车时间\t\t\t还车时间\t\t\t车辆状态");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		for (RentRecord rentRecord : jsonToList) {
			System.out.println(rentRecord.getId() + "\t" + rentRecord.getCid() + "\t" + rentRecord.getCarName() + "\t"
					+ rentRecord.getUid() + "\t" + rentRecord.getUname() + "\t" + rentRecord.getRentPrice() + "/天\t\t"
					+ rentRecord.getAllPrice() + "元\t" + rentRecord.getCarDesc() + "\t" + rentRecord.getCarBrand()
					+ "\t" + rentRecord.getCarType() + "\t" + rentRecord.getRentTime() + "\t"
					+ rentRecord.getReturnCarTime() + "\t" + (rentRecord.getStatus() == 0 ? "已借未还" : "已还"));
		}
	}

}
