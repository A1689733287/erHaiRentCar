package com.gpg.erhai.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gpg.erhai.dao.ICarDao;
import com.gpg.erhai.dao.IRentRecordDao;
import com.gpg.erhai.entity.RentRecord;
import com.gpg.erhai.factory.Factory;
import com.gpg.erhai.util.jdbc.DBUtil;
import com.gpg.erhai.util.jdbc.JdbcTemplate;
import com.gpg.erhai.util.jdbc.JdbcTransaction;
import com.gpg.erhai.util.jdbc.PreparedStatementSetter;
import com.gpg.erhai.util.jdbc.RowCallBackHandler;

public class RentRecordDaoImpl implements IRentRecordDao {
	Connection conn = DBUtil.getConnection();
	ICarDao carDao = Factory.getInstance("carDao", CarDaoImpl.class);

	@Override
	public List<RentRecord> queryAllRendRecord() {
		String sql = "SELECT R.RID,C.CID,C.CARMODEL,U.ID,U.USERNAME,R.RENTPRICE,B.BRANDNAME||'('||R.CARBRAND||')',R.RENTTIME,R.ALLPRICE,C.CARDESC,T.TYPENAME||'('||R.CARTYPE||')',R.RETURNCARTIME,R.RENTSTATUS FROM T_RENTRECORD R, T_USER U,T_CARBRAND B,T_CARTYPE T ,T_CAR C WHERE R.CID = C.CID AND R.CARBRAND = B.ID AND R.CARTYPE = T.ID AND R.U_ID = U.ID";
		try {
			return JdbcTemplate.query(conn, sql, createHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RentRecord> queryRendRecordByUid(int uid) {
		String sql = "SELECT R.RID,C.CID,C.CARMODEL,U.ID,U.USERNAME,R.RENTPRICE,B.BRANDNAME||'('||R.CARBRAND||')',R.RENTTIME,R.ALLPRICE,C.CARDESC,T.TYPENAME||'('||R.CARTYPE||')',R.RETURNCARTIME,R.RENTSTATUS FROM T_RENTRECORD R, T_USER U,T_CARBRAND B,T_CARTYPE T ,T_CAR C WHERE R.CID = C.CID AND R.CARBRAND = B.ID AND R.CARTYPE = T.ID AND R.U_ID = U.ID AND U.ID=?";
		try {
			return JdbcTemplate.query(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setInt(1, uid);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}, createHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public RowCallBackHandler<RentRecord> createHandler() {
		return new RowCallBackHandler<RentRecord>() {

			@Override
			public RentRecord processRow(ResultSet rs) {
				try {
					return new RentRecord(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
							rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getFloat(9), rs.getString(10),
							rs.getString(11), rs.getString(12), rs.getInt(13));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}

	@Override
	public int insertRentRecord(RentRecord rentRecord) {

		JdbcTransaction trans = new JdbcTransaction();
		trans.beginTransaction(conn);
		ResultSet rs = null;
		String sql = "INSERT INTO T_RENTRECORD(RID,CID,U_ID,RENTPRICE,CARBRAND,RENTTIME,ALLPRICE,CARDESC,CARTYPE,RETURNCARTIME,RENTSTATUS) VALUES(T_RENTRECORD_ID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
		try {
			int row = JdbcTemplate.update(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setInt(1, rentRecord.getCid());
						pstmt.setInt(2, rentRecord.getUid());
						pstmt.setDouble(3, rentRecord.getRentPrice());
						pstmt.setInt(4, Integer.parseInt(rentRecord.getCarBrand()));
						pstmt.setString(5, rentRecord.getRentTime());
						pstmt.setDouble(6, rentRecord.getAllPrice());
						pstmt.setString(7, rentRecord.getCarDesc());
						pstmt.setInt(8, Integer.parseInt(rentRecord.getCarType()));
						pstmt.setString(9, rentRecord.getReturnCarTime());
						pstmt.setInt(10, rentRecord.getStatus());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			int isRent = carDao.updateRentState(conn, 1, rentRecord.getCid());
			if (row > 0 && isRent > 0) {
				trans.commit(conn);
				rs = conn.prepareStatement("SELECT T_RENTRECORD_ID_SEQ.CURRVAL FROM DUAL").executeQuery();
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			trans.rollBack(conn);
		}

		return 0;
	}

	@Override
	public RentRecord queryRendRecordById(int id) {
		String sql = "SELECT R.RID,C.CID,C.CARMODEL,U.ID,U.USERNAME,R.RENTPRICE,B.BRANDNAME||'('||R.CARBRAND||')',R.RENTTIME,R.ALLPRICE,C.CARDESC,T.TYPENAME||'('||R.CARTYPE||')',R.RETURNCARTIME,R.RENTSTATUS FROM T_RENTRECORD R, T_USER U,T_CARBRAND B,T_CARTYPE T ,T_CAR C WHERE R.CID = C.CID AND R.CARBRAND = B.ID AND R.CARTYPE = T.ID AND R.U_ID = U.ID AND R.RID=?";
		try {
			return JdbcTemplate.singleQuery(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setInt(1, id);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}, createHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateRentRecord(RentRecord rentRecord) {
		JdbcTransaction tran = new JdbcTransaction();
		tran.beginTransaction(conn);
		String sql = "UPDATE T_RENTRECORD SET RENTSTATUS=1, ALLPRICE = ?, RETURNCARTIME = ? WHERE RID = ?";
		try {
			int updateRentRecord = JdbcTemplate.update(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setDouble(1, rentRecord.getAllPrice());
						pstmt.setString(2, rentRecord.getReturnCarTime());
						pstmt.setInt(3, rentRecord.getId());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				};
			});
			int rentState = carDao.updateRentState(conn, 0, rentRecord.getCid());
			if (updateRentRecord > 0 && rentState > 0) {
				tran.commit(conn);
				return updateRentRecord;
			}
		} catch (SQLException e) {
			tran.rollBack(conn);
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, null);
		}
		return 0;
	}

	@Override
	public List<RentRecord> queryRendRecordByCid(int id) {
		String sql = "SELECT R.RID,C.CID,C.CARMODEL,U.ID,U.USERNAME,R.RENTPRICE,B.BRANDNAME||'('||R.CARBRAND||')',R.RENTTIME,R.ALLPRICE,C.CARDESC,T.TYPENAME||'('||R.CARTYPE||')',R.RETURNCARTIME,R.RENTSTATUS FROM T_RENTRECORD R, T_USER U,T_CARBRAND B,T_CARTYPE T ,T_CAR C WHERE R.CID = C.CID AND R.CARBRAND = B.ID AND R.CARTYPE = T.ID AND R.U_ID = U.ID AND R.CID=?";
		try {
			return JdbcTemplate.query(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setInt(1, id);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}, createHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
