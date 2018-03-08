package com.gpg.erhai.service;

import java.util.List;

import com.gpg.erhai.entity.RentRecord;

public interface IRentRecordService {
	List<RentRecord> queryAllRendRecord();
	List<RentRecord> queryRendRecordByUid(int id);
	List<RentRecord> queryRendRecordByCid(int id);
	RentRecord queryRendRecordById(int id);
	int insertRentRecord(RentRecord rentRecord);
	int updateRentRecord(RentRecord rentRecord);
}
