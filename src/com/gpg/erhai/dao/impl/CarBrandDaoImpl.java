package com.gpg.erhai.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gpg.erhai.dao.ICarBrandDao;
import com.gpg.erhai.entity.CarBrand;
import com.gpg.erhai.util.jdbc.DBUtil;
import com.gpg.erhai.util.jdbc.JdbcTemplate;
import com.gpg.erhai.util.jdbc.PreparedStatementSetter;
import com.gpg.erhai.util.jdbc.RowCallBackHandler;

public class CarBrandDaoImpl implements ICarBrandDao {
	private Connection conn = DBUtil.getConnection();

	@Override
	public List<CarBrand> queryAllCarBrand() {
		String sql = "SELECT ID,BRANDNAME  FROM T_CARBRAND";
		try {
			return JdbcTemplate.query(conn, sql, createHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CarBrand queryCarBrandById(int id) {
		String sql = "SELECT ID,BRANDNAME  FROM T_CARBRAND WHERE ID = ?";
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
	public int insertCarBrand(CarBrand carBrand) {
		String sql = "INSERT INTO T_CARBRAND(ID,BRANDNAME) VALUES(T_CARBRAND_ID_SEQ.NEXTVAL,?)";
		try {
			return JdbcTemplate.update(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setString(1, carBrand.getBrandName());
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

	@Override
	public int updateCarBrand(int id, CarBrand carBrand) {
		String sql = "UPDATE T_CARBRAND SET BRANDNAME = ? WHERE ID = ?";
		try {
			return JdbcTemplate.update(conn, sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement pstmt)  {
					try {
						pstmt.setString(1, carBrand.getBrandName());
						pstmt.setInt(2, id);
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

	public RowCallBackHandler<CarBrand> createHandler() {
		return new RowCallBackHandler<CarBrand>() {

			@Override
			public CarBrand processRow(ResultSet rs) {
				try {
					return new CarBrand(rs.getInt(1), rs.getString(2));
				} catch (SQLException e) {
					e.printStackTrace();
				} 
				return null;
			}
		};
	}

	@Override
	public int deleteCarBrand(int id) {
		String sql = "DELETE FROM T_CARBRAND WHERE ID = ?";
		try {
			return JdbcTemplate.update(conn, sql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement pstmt){
					try {
						pstmt.setInt(1, id);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}
