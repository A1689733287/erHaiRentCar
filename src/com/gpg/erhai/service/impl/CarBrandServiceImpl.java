package com.gpg.erhai.service.impl;

import java.util.List;

import com.gpg.erhai.dao.ICarBrandDao;
import com.gpg.erhai.dao.impl.CarBrandDaoImpl;
import com.gpg.erhai.entity.CarBrand;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.service.ICarBrandService;

public class CarBrandServiceImpl implements ICarBrandService {
	ICarBrandDao carBrandDao = Factory.getInstance("carBrandDao", CarBrandDaoImpl.class);

	@Override
	public List<CarBrand> queryAllCarBrand() {
		return carBrandDao.queryAllCarBrand();
	}

	@Override
	public CarBrand queryCarBrandById(int id) {
		return carBrandDao.queryCarBrandById(id);
	}

	@Override
	public int deleteCarBrand(int id) {
		return carBrandDao.deleteCarBrand(id);
	}

	@Override
	public int insertCarBrand(CarBrand carBrand) {
		return carBrandDao.insertCarBrand(carBrand);
	}

	@Override
	public int updateCarBrand(int id, CarBrand carBrand) {
		return 0;
	}

}
