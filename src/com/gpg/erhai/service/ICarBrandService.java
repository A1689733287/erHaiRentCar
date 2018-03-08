package com.gpg.erhai.service;

import java.util.List;

import com.gpg.erhai.entity.CarBrand;

public interface ICarBrandService {
	List <CarBrand> queryAllCarBrand();
	CarBrand queryCarBrandById(int id);
	int deleteCarBrand(int id);
	/**
	 * 插入汽车品牌
	 * @param carBrand 汽车品牌
	 * @return
	 */
	int insertCarBrand(CarBrand carBrand);

	/**
	 * 通过id更新汽车品牌
	 * @param id 
	 * @param carBrand
	 * @return
	 */
	int updateCarBrand(int id, CarBrand carBrand);

}
