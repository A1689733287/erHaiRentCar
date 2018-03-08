package com.gpg.erhai.control;

import java.util.List;

import com.gpg.erhai.entity.Car;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.server.ServerManager;
import com.gpg.erhai.server.ServerService;
import com.gpg.erhai.server.dispatcher.CoreDispatcher;
import com.gpg.erhai.service.ICarService;
import com.gpg.erhai.service.impl.CarServiceImpl;
import com.gpg.erhai.util.Container;
import com.gpg.erhai.util.JsonUtil;

public class CarControl implements ICommonControl {
	private ICarService carService = Factory.getInstance("carService", CarServiceImpl.class);

	public void operation(ServerService ss, String requestOption, String msg) {
		List<Car> list = null;
		switch (requestOption) {
		case Container.QUERY_ALL_CAR:
			list = carService.queryAllCar(msg);
			String cListStr = JsonUtil.objToString(list);
			opData(ss, cListStr);
			break;
		case Container.UPDATE_CAR_BY_ID:
			String[] split = msg.split("&");
			if (split[0].equals(Container.UPDATE_CAR_ONLINE_STATE)) {
				int rentState = carService.updateOnlineState(Integer.parseInt(split[1]),
						Integer.parseInt(split[2]));
				if (rentState > 0) {
					opData(ss, Container.UPDATE_SUCCESS + "&"
							+ JsonUtil.objToString(carService.queryCar(Integer.parseInt(split[2]))));
				} else {
					opData(ss, Container.UPDATE_FAIL + "&"
							+ JsonUtil.objToString(carService.queryCar(Integer.parseInt(split[2]))));
				}
			}
			if (split[0].equals(Container.UPDATE_CAR_RENT_PRICE_STATE)) {
				int updatePrice = carService.updateRentPrice(split[1], Integer.parseInt(split[2]));
				if (updatePrice > 0) {
					opData(ss, Container.UPDATE_SUCCESS + "&"
							+ JsonUtil.objToString(carService.queryCar(Integer.parseInt(split[2]))));
				} else {
					opData(ss, Container.UPDATE_FAIL + "&"
							+ JsonUtil.objToString(carService.queryCar(Integer.parseInt(split[2]))));
				}
			}
			break;
		case Container.QUERY_CAR_BY_ID_TO_RENT:
			Car car = carService.queryCarToRent(Integer.parseInt(msg));
			if (car != null) {
			CoreDispatcher.getCoreDispatcherInstance().msgOption(Container.RENT_CONTROL, Container.RENT_CAR,
					JsonUtil.objToString(car));
			} else {
				opData(ss, Container.NO_CAR_TO_RENT);
			}
			break;
		case Container.INSERT_CAR:
			Car carInsert = JsonUtil.jsonToObj(msg, Car.class);
			int rowCar = carService.insertCar(carInsert);
			if (rowCar > 0) {
				opData(ss, Container.INSERT_SUCCESS);
			} else {
				opData(ss, Container.INSERT_FAIL);
			}
			break;
		case Container.QUERY_CAR_BY_ID:
			Car car1 = carService.queryCar(Integer.parseInt(msg));
			System.out.println(car1);
			opData(ss, JsonUtil.objToString(car1));
			break;
		}
	}

	public void opData(ServerService ss, String msg) {
		ServerManager.getServerManager().out(ss, msg);
	}
}
