package com.gpg.erhai.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gpg.erhai.dao.ICarDao;
import com.gpg.erhai.entity.Car;
import com.gpg.erhai.util.Container;
import com.gpg.erhai.util.jdbc.DBUtil;
import com.gpg.erhai.util.jdbc.JdbcTemplate;
import com.gpg.erhai.util.jdbc.PreparedStatementSetter;
import com.gpg.erhai.util.jdbc.RowCallBackHandler;

public class CarDaoImpl implements ICarDao {
	private Connection conn = DBUtil.getConnection();

	@Override
	public List<Car> queryAllCar(String option) {
		String sql = null;
		// 用户查询
		if (option.equals("u")) {
			sql = "SELECT C.CID,C.CARMODEL,C.CARNUMBER,C.CARDESC,C.COLOR,C.PRICE,B.BRANDNAME||'('||C.CARBRAND||')',T.TYPENAME||'('||C.CARTYPE||')',C.RENTPRICE,C.ISRENT,C.ISONLINE FROM T_CAR C, T_CARTYPE T, T_CARBRAND B WHERE C.CARTYPE = T.ID AND C.CARBRAND = B.ID AND ISRENT = 0 AND ISONLINE = 0";
		}
		// 管理员查询
		if (option.equals("a")) {
			sql = "SELECT C.CID,C.CARMODEL,C.CARNUMBER,C.CARDESC,C.COLOR,C.PRICE,B.BRANDNAME||'('||C.CARBRAND||')',T.TYPENAME||'('||C.CARTYPE||')',C.RENTPRICE,C.ISRENT,C.ISONLINE FROM T_CAR C, T_CARTYPE T, T_CARBRAND B WHERE C.CARTYPE = T.ID AND C.CARBRAND = B.ID";
		}
		// 用户价格降序
		if (option.equals(Container.USER_ORDER_BY_RENTPRICE_DESC)) {
			sql = "SELECT C.CID,C.CARMODEL,C.CARNUMBER,C.CARDESC,C.COLOR,C.PRICE,B.BRANDNAME||'('||C.CARBRAND||')',T.TYPENAME||'('||C.CARTYPE||')',C.RENTPRICE,C.ISRENT,C.ISONLINE FROM T_CAR C, T_CARTYPE T, T_CARBRAND B WHERE C.CARTYPE = T.ID AND C.CARBRAND = B.ID AND ISRENT = 0 AND ISONLINE = 0 ORDER BY RENTPRICE DESC";
		}
		// 用户价格升序
		if (option.equals(Container.USER_ORDER_BY_RENTPRICE_ASC)) {
			sql = "SELECT C.CID,C.CARMODEL,C.CARNUMBER,C.CARDESC,C.COLOR,C.PRICE,B.BRANDNAME||'('||C.CARBRAND||')',T.TYPENAME||'('||C.CARTYPE||')',C.RENTPRICE,C.ISRENT,C.ISONLINE FROM T_CAR C, T_CARTYPE T, T_CARBRAND B WHERE C.CARTYPE = T.ID AND C.CARBRAND = B.ID AND ISONLINE = 0 ORDER BY RENTPRICE ASC";
		}
		// 管理员价格降序
		if (option.equals(Container.ADMIN_ORDER_BY_RENTPRICE_DESC)) {
			sql = "SELECT C.CID,C.CARMODEL,C.CARNUMBER,C.CARDESC,C.COLOR,C.PRICE,B.BRANDNAME||'('||C.CARBRAND||')',T.TYPENAME||'('||C.CARTYPE||')',C.RENTPRICE,C.ISRENT,C.ISONLINE FROM T_CAR C, T_CARTYPE T, T_CARBRAND B WHERE C.CARTYPE = T.ID AND C.CARBRAND = B.ID ORDER BY RENTPRICE DESC";
		}
		// 管理员价格升序
		if (option.equals(Container.ADMIN_ORDER_BY_RENTPRICE_ASC)) {
			sql = "SELECT C.CID,C.CARMODEL,C.CARNUMBER,C.CARDESC,C.COLOR,C.PRICE,B.BRANDNAME||'('||C.CARBRAND||')',T.TYPENAME||'('||C.CARTYPE||')',C.RENTPRICE,C.ISRENT,C.ISONLINE FROM T_CAR C, T_CARTYPE T, T_CARBRAND B WHERE C.CARTYPE = T.ID AND C.CARBRAND = B.ID ORDER BY RENTPRICE ASC";
		}
		// 用户通过车类型排序
		if (option.startsWith(Container.USER_SEARCH_BY_CARTYPE + "&")) {
			String[] split = option.split("&");
			sql = "SELECT C.CID,C.CARMODEL,C.CARNUMBER,C.CARDESC,C.COLOR,C.PRICE,B.BRANDNAME||'('||C.CARBRAND||')',T.TYPENAME||'('||C.CARTYPE||')',C.RENTPRICE,C.ISRENT,C.ISONLINE FROM T_CAR C, T_CARTYPE T, T_CARBRAND B WHERE C.CARTYPE = T.ID AND C.CARBRAND = B.ID AND ISRENT = 0 AND ISONLINE = 0 AND C.CARTYPE = "
					+ split[1];
		}
		// 管理员通过车类型排序
		if (option.startsWith(Container.ADMIN_SEARCH_BY_CARTYPE + "&")) {
			String[] split = option.split("&");
			sql = "SELECT C.CID,C.CARMODEL,C.CARNUMBER,C.CARDESC,C.COLOR,C.PRICE,B.BRANDNAME||'('||C.CARBRAND||')',T.TYPENAME||'('||C.CARTYPE||')',C.RENTPRICE,C.ISRENT,C.ISONLINE FROM T_CAR C, T_CARTYPE T, T_CARBRAND B WHERE C.CARTYPE = T.ID AND C.CARBRAND = B.ID AND C.CARTYPE = "
					+ split[1];
		}
		// 用户通过车品牌排序
		if (option.startsWith(Container.USER_SEARCH_BY_CARBRADN + "&")) {
			String[] split = option.split("&");
			sql = "SELECT C.CID,C.CARMODEL,C.CARNUMBER,C.CARDESC,C.COLOR,C.PRICE,B.BRANDNAME||'('||C.CARBRAND||')',T.TYPENAME||'('||C.CARTYPE||')',C.RENTPRICE,C.ISRENT,C.ISONLINE FROM T_CAR C, T_CARTYPE T, T_CARBRAND B WHERE C.CARTYPE = T.ID AND C.CARBRAND = B.ID AND ISRENT = 0 AND ISONLINE = 0 AND C.CARBRAND = "
					+ split[1];
		}
		// 管理员通过车品牌排序
		if (option.startsWith(Container.ADMIN_SEARCH_BY_CARBRADN + "&")) {
			String[] split = option.split("&");
			sql = "SELECT C.CID,C.CARMODEL,C.CARNUMBER,C.CARDESC,C.COLOR,C.PRICE,B.BRANDNAME||'('||C.CARBRAND||')',T.TYPENAME||'('||C.CARTYPE||')',C.RENTPRICE,C.ISRENT,C.ISONLINE FROM T_CAR C, T_CARTYPE T, T_CARBRAND B WHERE C.CARTYPE = T.ID AND C.CARBRAND = B.ID AND C.CARBRAND = "
					+ split[1];
		}
		System.out.println("请求的数据库语句--------->" + option + "》》》》" + sql);
		try {
			return JdbcTemplate.query(conn, sql, createHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car queryCar(int id) {
		String sql = "SELECT C.CID,C.CARMODEL,C.CARNUMBER,C.CARDESC,C.COLOR,C.PRICE,B.BRANDNAME||'('||C.CARBRAND||')',T.TYPENAME||'('||C.CARTYPE||')',C.RENTPRICE,C.ISRENT,C.ISONLINE FROM T_CAR C, T_CARTYPE T, T_CARBRAND B WHERE C.CARTYPE = T.ID AND C.CARBRAND = B.ID AND C.CID = ?";
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

	public RowCallBackHandler<Car> createHandler() {
		return new RowCallBackHandler<Car>() {

			@Override
			public Car processRow(ResultSet rs) {
				try {
					return new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getFloat(9), rs.getInt(10),
							rs.getInt(11));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}

	@Override
	public int insertCar(Car car) {
		String sql = "INSERT INTO T_CAR(CID,CARMODEL,CARNUMBER,CARDESC,COLOR,PRICE,CARBRAND,CARTYPE,RENTPRICE,ISRENT,ISONLINE) VALUES(T_CAR_ID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
		try {
			return JdbcTemplate.update(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setString(1, car.getCarModel());
						pstmt.setString(2, car.getCarNumber());
						pstmt.setString(3, car.getCarDesc());
						pstmt.setString(4, car.getColor());
						pstmt.setDouble(5, car.getPrice());
						pstmt.setInt(6, Integer.parseInt(car.getCarBrand()));
						pstmt.setInt(7, Integer.parseInt(car.getCarType()));
						pstmt.setDouble(8, car.getRentPrice());
						pstmt.setInt(9, car.getIsRent());
						pstmt.setInt(10, car.getIsOnline());
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
	public int updateRentState(Connection conn, int status, int cid) {
		String sql = "UPDATE T_CAR SET ISRENT = ? WHERE CID = ?";
		try {
			return JdbcTemplate.update(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setInt(1, status);
						pstmt.setInt(2, cid);
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
	public int updateOnlineState(int status , int cid) {
		String sql = "UPDATE T_CAR SET ISONLINE = ? WHERE CID = ?";
		try {
			return JdbcTemplate.update(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setInt(1, status);
						pstmt.setInt(2, cid);
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
	public int updateRentPrice(String rentPrice, int cid) {
		String sql = "UPDATE T_CAR SET RENTPRICE = ? WHERE CID = ?";
		try {
			return JdbcTemplate.update(conn, sql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setFloat(1, Float.parseFloat(rentPrice));
						pstmt.setInt(2, cid);
					} catch (Exception e) {
					}
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Car queryCarToRent(int id) {
		String sql = "SELECT CID,CARMODEL,CARNUMBER,CARDESC,COLOR,PRICE,CARBRAND,CARTYPE,RENTPRICE,ISRENT,ISONLINE FROM T_CAR WHERE CID = ?";
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

}
