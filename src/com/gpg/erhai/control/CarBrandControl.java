package com.gpg.erhai.control;

import java.util.List;

import com.gpg.erhai.entity.CarBrand;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.server.ServerManager;
import com.gpg.erhai.server.ServerService;
import com.gpg.erhai.service.ICarBrandService;
import com.gpg.erhai.service.impl.CarBrandServiceImpl;
import com.gpg.erhai.util.Container;
import com.gpg.erhai.util.JsonUtil;

public class CarBrandControl implements ICommonControl {
	ICarBrandService carBrandService = Factory.getInstance("carBrandService", CarBrandServiceImpl.class);

	@Override
	public void operation(ServerService ss, String requestOption, String msg) {
		switch (requestOption) {
		case Container.QUERY_ALL_CARBRAND:
			List<CarBrand> carBrands = carBrandService.queryAllCarBrand();
			opData(ss, JsonUtil.objToString(carBrands));
			break;
		case Container.QUERY_CARBRABD_BY_ID:
			CarBrand carBrand = carBrandService.queryCarBrandById(Integer.parseInt(msg));
			opData(ss, JsonUtil.objToString(carBrand));
			break;
		case Container.INSERT_CARBRAND:
			CarBrand cBrand = new CarBrand(0,msg);
			int insertCarBrand = carBrandService.insertCarBrand(cBrand);
			if (insertCarBrand > 0) {
				opData(ss, Container.INSERT_SUCCESS);
			} else {
				opData(ss, Container.INSERT_FAIL);
			}
			break;
		case Container.DELETE_CARBRAND_BY_ID:
			break;
		case Container.UPDATE_CARBRAND_BY_ID:
		}
	}

	@Override
	public void opData(ServerService ss, String msg) {
		ServerManager.getServerManager().out(ss, msg);
	}

}
