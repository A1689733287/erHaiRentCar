package com.gpg.erhai.control;

import java.util.List;

import com.gpg.erhai.entity.Car;
import com.gpg.erhai.entity.RentRecord;
import com.gpg.erhai.entity.User;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.server.ServerManager;
import com.gpg.erhai.server.ServerService;
import com.gpg.erhai.service.IRentRecordService;
import com.gpg.erhai.service.impl.RentRecordServiceImpl;
import com.gpg.erhai.util.Container;
import com.gpg.erhai.util.DateUtil;
import com.gpg.erhai.util.JsonUtil;

public class RentControl implements ICommonControl {
	private IRentRecordService rentRecordService = Factory.getInstance("rentRecordService",
			RentRecordServiceImpl.class);
	User user = null;

	public void operation(ServerService ss, String requestOption, String msg) {
		List<RentRecord> rendRecords = null;
		switch (requestOption) {
		case Container.QUERY_ALL_RENTRECORD:
			List<RentRecord> allRendRecord = rentRecordService.queryAllRendRecord();
			opData(ss, JsonUtil.objToString(allRendRecord));
			break;
		case Container.QUERY_RENTRECORD_BY_CID:
			rendRecords = rentRecordService.queryRendRecordByCid(Integer.parseInt(msg));
			opData(ss, JsonUtil.objToString(rendRecords));
			break;
		case Container.QUERY_RENTRECORD_BY_UID:
			user = ServerManager.getServerManager().getKey(ss);
			if (msg.contains("&")) {
				String[] split = msg.split("&");
				rendRecords = rentRecordService.queryRendRecordByUid(Integer.parseInt(split[0]));
			} else {
				rendRecords = rentRecordService.queryRendRecordByUid(user.getId());
			}
			opData(ss, JsonUtil.objToString(rendRecords));
			break;
		case Container.RENT_CAR:
			Car car = JsonUtil.jsonToObj(msg, Car.class);
			user = ServerManager.getServerManager().getKey(ss);
			RentRecord rentRecord = new RentRecord(0, car.getCid(), car.getCarModel(), user.getId(), user.getUserName(),
					car.getRentPrice(), car.getCarBrand(), DateUtil.getStringDate(), 0, car.getCarDesc(),
					car.getCarType(), null, 0);
			int rentRecordRow = rentRecordService.insertRentRecord(rentRecord);
			RentRecord rentRec = rentRecordService.queryRendRecordById(rentRecordRow);
			if (rentRecordRow > 0) {
				opData(ss, Container.INSERT_SUCCESS + "#" + JsonUtil.objToString(rentRec));
			} else {
				opData(ss, Container.INSERT_FAIL);
			}
			break;
		case Container.UPDATE_RENTCAR_BY_ID:
			
				RentRecord recordById = rentRecordService.queryRendRecordById(Integer.parseInt(msg));
				if (recordById != null) {
				recordById.setReturnCarTime(DateUtil.getStringDate());
				recordById.setAllPrice((recordById.getRentPrice() / 24)
						* DateUtil.timeMinus(recordById.getRentTime(), recordById.getReturnCarTime()));
				int record = rentRecordService.updateRentRecord(recordById);
				if (record > 0) {
					opData(ss, Container.UPDATE_SUCCESS + "#" + JsonUtil.objToString(recordById));
				} else {
					opData(ss, Container.UPDATE_FAIL);
				} 
			}else {
				opData(ss, Container.UPDATE_FAIL);
			}
			break;
		}

	}

	public void opData(ServerService ss, String msg) {
		ServerManager.getServerManager().out(ss, msg);
	}
}
