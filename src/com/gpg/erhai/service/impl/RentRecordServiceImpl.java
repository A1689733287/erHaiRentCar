package com.gpg.erhai.service.impl;

import java.util.List;

import com.gpg.erhai.dao.IRentRecordDao;
import com.gpg.erhai.dao.impl.RentRecordDaoImpl;
import com.gpg.erhai.entity.RentRecord;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.service.IRentRecordService;

public class RentRecordServiceImpl implements IRentRecordService {
	IRentRecordDao rentRecordDao = Factory.getInstance("rentRecordDao", RentRecordDaoImpl.class);

	@Override
	public List<RentRecord> queryAllRendRecord() {
		return rentRecordDao.queryAllRendRecord();
	}

	@Override
	public List<RentRecord> queryRendRecordByUid(int id) {
		return rentRecordDao.queryRendRecordByUid(id);
	}

	@Override
	public int insertRentRecord(RentRecord rentRecord) {
		return rentRecordDao.insertRentRecord(rentRecord);
	}

	@Override
	public RentRecord queryRendRecordById(int id) {
		return rentRecordDao.queryRendRecordById(id);
	}

	@Override
	public int updateRentRecord(RentRecord rentRecord) {
		return rentRecordDao.updateRentRecord(rentRecord);
	}

	@Override
	public List<RentRecord> queryRendRecordByCid(int id) {
		return rentRecordDao.queryRendRecordByCid(id);
	}

}
