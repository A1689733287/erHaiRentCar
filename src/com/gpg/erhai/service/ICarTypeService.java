package com.gpg.erhai.service;

import java.util.List;

import com.gpg.erhai.entity.CarType;

public interface ICarTypeService {
	List <CarType> queryAllCarType();
	CarType queryCarTypeById(int id);
	int insertCarType(CarType carType);
}
