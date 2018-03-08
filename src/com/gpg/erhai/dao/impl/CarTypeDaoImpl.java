package com.gpg.erhai.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gpg.erhai.dao.ICarTypeDao;
import com.gpg.erhai.entity.CarType;
import com.gpg.erhai.util.jdbc.DBUtil;
import com.gpg.erhai.util.jdbc.JdbcTemplate;
import com.gpg.erhai.util.jdbc.PreparedStatementSetter;
import com.gpg.erhai.util.jdbc.RowCallBackHandler;

public class CarTypeDaoImpl implements ICarTypeDao {
	Connection conn = DBUtil.getConnection();

	@Override
	public List<CarType> queryAllCarType() {
		String sql = "SELECT ID,TYPENAME FROM T_CARTYPE";
		try {
			return JdbcTemplate.query(conn, sql, createHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CarType queryCarTypeById(int id) {
		String sql = "SELECT ID,TYPENAME FROM T_CARTYPE WHERE ID = ?";
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

	private RowCallBackHandler<CarType> createHandler() {
		return new RowCallBackHandler<CarType>() {

			@Override
			public CarType processRow(ResultSet rs) {
				try {
					return new CarType(rs.getInt(1), rs.getString(2));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}

	@Override
	public int insertCarType(CarType carType) {
		String sql = "INSERT INTO T_CARTYPE(ID,TYPENAME) VALUES(T_CARTYPE_ID_SEQ.NEXTVAL,?)";
		try {
			return JdbcTemplate.update(conn, sql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setString(1, carType.getTypeName());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
