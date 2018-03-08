package com.gpg.erhai.service.impl;

import java.util.List;

import com.gpg.erhai.dao.ICarTypeDao;
import com.gpg.erhai.dao.impl.CarTypeDaoImpl;
import com.gpg.erhai.entity.CarType;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.service.ICarTypeService;

public class CarTypeServiceImpl implements ICarTypeService {
	ICarTypeDao carTypeDao = Factory.getInstance("carTypeDao", CarTypeDaoImpl.class);

	@Override
	public List<CarType> queryAllCarType() {
		return carTypeDao.queryAllCarType();
	}

	@Override
	public CarType queryCarTypeById(int id) {
		return carTypeDao.queryCarTypeById(id);
	}

	@Override
	public int insertCarType(CarType carType) {
		return carTypeDao.insertCarType(carType);
	}

}
