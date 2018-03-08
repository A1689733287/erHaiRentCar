package com.gpg.erhai.service;

import java.util.List;

import com.gpg.erhai.entity.Car;

public interface ICarService {
	List<Car> queryAllCar(String option);

	Car queryCar(int id);

	Car queryCarToRent(int id);
	
	int insertCar(Car car);


	int updateOnlineState(int status, int cid);
	/**
	 * 修改汽车价格
	 * @param rentPrice
	 * @param cid
	 * @return
	 */
	int updateRentPrice(String rentPrice,int cid);
}
