package com.gpg.erhai.control;

import java.util.List;

import com.gpg.erhai.entity.CarType;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.server.ServerManager;
import com.gpg.erhai.server.ServerService;
import com.gpg.erhai.service.ICarTypeService;
import com.gpg.erhai.service.impl.CarTypeServiceImpl;
import com.gpg.erhai.util.Container;
import com.gpg.erhai.util.JsonUtil;

public class CarTypeControl implements ICommonControl {
	private ICarTypeService carTypeService = Factory.getInstance("carTypeService", CarTypeServiceImpl.class);

	@Override
	public void operation(ServerService ss, String requestOption, String msg) {
		switch (requestOption) {
		case Container.QUERY_ALL_CARTYPE:
			List<CarType> carTypes = carTypeService.queryAllCarType();
			opData(ss, JsonUtil.objToString(carTypes));
			break;
		case Container.QUERY_CARTYPE_BY_ID:
			CarType carType = carTypeService.queryCarTypeById(Integer.parseInt(msg));
			opData(ss, JsonUtil.objToString(carType));
			break;
		case Container.INSERT_CARTYPE:
			carType = new CarType(0, msg);
			int insertCarType = carTypeService.insertCarType(carType);
			if (insertCarType > 0) {
				opData(ss, Container.INSERT_SUCCESS);
			} else {
				opData(ss, Container.INSERT_FAIL);
			}

			break;
		case Container.DELETE_CARTYPE_BY_ID:

			break;
		case Container.UPDATE_CARTYPE_BY_ID:

			break;
		}
	}

	@Override
	public void opData(ServerService ss, String msg) {
		ServerManager.getServerManager().out(ss, msg);
	}

}
