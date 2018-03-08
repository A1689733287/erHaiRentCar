package com.gpg.erhai.service.impl;

import java.util.List;

import com.gpg.erhai.dao.ICarDao;
import com.gpg.erhai.dao.impl.CarDaoImpl;
import com.gpg.erhai.entity.Car;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.service.ICarService;

public class CarServiceImpl implements ICarService {
	ICarDao carDao = Factory.getInstance("carDao", CarDaoImpl.class);

	@Override
	public List<Car> queryAllCar(String option) {
		return carDao.queryAllCar(option);
	}

	@Override
	public Car queryCar(int id) {
		return carDao.queryCar(id);
	}

	@Override
	public int insertCar(Car car) {
		return carDao.insertCar(car);
	}


	@Override
	public int updateOnlineState(int status, int cid) {
		return carDao.updateOnlineState(status, cid);
	}

	@Override
	public int updateRentPrice(String rentPrice, int cid) {
		return carDao.updateRentPrice(rentPrice, cid);
	}

	@Override
	public Car queryCarToRent(int id) {
		return carDao.queryCarToRent(id);
	}



}
