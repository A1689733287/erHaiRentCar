package com.gpg.erhai.dao;

import java.sql.Connection;
import java.util.List;

import com.gpg.erhai.entity.Car;

public interface ICarDao {
	/**
	 * 查询所有汽车
	 * @param option 查询数据库的指令
	 * @return
	 */
	List<Car> queryAllCar(String option);

	/**
	 * 通过id查询汽车
	 * @param id 汽车id
	 * @return 返回汽车
	 */
	Car queryCar(int id);

	/**
	 * 通过id查询汽车进行租赁
	 * @param id 汽车id
	 * @return 返回汽车
	 */
	Car queryCarToRent(int id);
	
	/**
	 * 插入汽车信息
	 * @param car 需要插入的汽车
	 * @return 返回插入的行数
	 */
	int insertCar(Car car);

	/**
	 * 通过汽车id修改汽车可租状态
	 * @param conn 
	 * @param status 汽车的状态
	 * @param cid 汽车的id
	 * @return 返回修改的行数
	 */
	int updateRentState(Connection conn, int status, int cid);

	/**
	 * 通过汽车id 修改汽车上线状态
	 * @param conn
	 * @param status 上架状态
	 * @return
	 */
	int updateOnlineState(int status, int cid);
	/**
	 * 修改汽车价格
	 * @param rentPrice
	 * @param cid
	 * @return
	 */
	int updateRentPrice(String rentPrice,int cid);
}
