package com.gpg.erhai.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gpg.erhai.dao.IUserDao;
import com.gpg.erhai.entity.User;
import com.gpg.erhai.util.jdbc.DBUtil;
import com.gpg.erhai.util.jdbc.JdbcTemplate;
import com.gpg.erhai.util.jdbc.PreparedStatementSetter;
import com.gpg.erhai.util.jdbc.RowCallBackHandler;

public class UserDaoImpl implements IUserDao {
	Connection conn = DBUtil.getConnection();

	@Override
	public User queryUser(User user) {
		String sql = "SELECT ID,USERNAME,USERPWD,USERTYPE FROM T_USER WHERE USERNAME=? AND USERPWD=? AND USERTYPE=?";
		try {
			return JdbcTemplate.singleQuery(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setString(1, user.getUserName());
						pstmt.setString(2, user.getUserPwd());
						pstmt.setInt(3, user.getUserType());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}, cretateHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int register(User user) {
		String sql = "INSERT INTO T_USER(ID,USERNAME,USERPWD,USERTYPE) VALUES(T_USER_ID_SEQ.nextval,?,?,?)";
		try {
			return JdbcTemplate.update(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setString(1, user.getUserName());
						pstmt.setString(2, user.getUserPwd());
						pstmt.setInt(3, user.getUserType());
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
	public boolean checkUser(String userName) {
		String sql = "SELECT ID,USERNAME,USERPWD,USERTYPE FROM T_USER WHERE USERNAME = ?";
		try {
			User user = JdbcTemplate.singleQuery(conn, sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) {
					try {
						pstmt.setString(1, userName);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}, cretateHandler());
			if (user != null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;

	}

	private RowCallBackHandler<User> cretateHandler() {
		return new RowCallBackHandler<User>() {

			@Override
			public User processRow(ResultSet rs) {
				try {
					return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
}
