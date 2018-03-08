package com.gpg.erhai.dao;

import java.util.List;

import com.gpg.erhai.entity.RentRecord;

public interface IRentRecordDao {
	/**
	 * 查询所有租车记录
	 * 
	 * @return 返回租车记录集合
	 */
	List<RentRecord> queryAllRendRecord();

	/**
	 * 通过用户id查询汽车租赁记录
	 * 
	 * @param id
	 *            汽车id
	 * @return 租赁记录的id
	 */
	List<RentRecord> queryRendRecordByUid(int id);

	/**
	 * 通过汽车id查询租赁记录
	 * 
	 * @param id
	 * @return
	 */
	List<RentRecord> queryRendRecordByCid(int id);

	/**
	 * 通过id查询租赁记录
	 * 
	 * @param id
	 * @return
	 */
	RentRecord queryRendRecordById(int id);

	/**
	 * 插入租赁记录
	 * 
	 * @param rentRecord
	 * @return 返回插入的行数
	 */
	int insertRentRecord(RentRecord rentRecord);

	/**
	 * 修改租赁信息
	 * 
	 * @param rentRecord
	 * @return 返回修改的汽车信息
	 */
	int updateRentRecord(RentRecord rentRecord);
}
