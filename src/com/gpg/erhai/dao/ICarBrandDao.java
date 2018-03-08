package com.gpg.erhai.dao;

import java.util.List;

import com.gpg.erhai.entity.CarBrand;

public interface ICarBrandDao {
	/**
	 * 查询所有汽车品牌
	 * @return
	 */
	List<CarBrand> queryAllCarBrand();
	/**
	 * 通过汽车id查询汽车品牌
	 * @param id 汽车id
	 * @return 返回汽车品牌
	 */
	CarBrand queryCarBrandById(int id);
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
	/**
	 * 通过id删除汽车品牌
	 * @param id
	 * @return
	 */
	int deleteCarBrand(int id);
}
