package com.gpg.erhai.dao;

import java.util.List;

import com.gpg.erhai.entity.CarType;

public interface ICarTypeDao {
	/**
	 * 查询所有汽车类型
	 * 
	 * @return 返回汽车类型的集合
	 */
	List<CarType> queryAllCarType();

	/**
	 * 通过id查询汽车类型
	 * 
	 * @param id
	 *            汽车id
	 * @return 返回汽车类型
	 */
	CarType queryCarTypeById(int id);
	
	int insertCarType(CarType carType);
}
