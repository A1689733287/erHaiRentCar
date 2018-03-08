package com.gpg.erhai.util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

	public static <T> T singleQuery(Connection conn, String sql, RowCallBackHandler<T> handler) throws SQLException {
		return singleQuery(conn, sql, null, handler);
	}

	public static <T> T singleQuery(Connection conn, String sql, PreparedStatementSetter setter,
			RowCallBackHandler<T> handler) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (setter != null) {
			setter.setValues(pstmt);
		}
		ResultSet rs = pstmt.executeQuery();
		if (handler != null && rs.next()) {
			return handler.processRow(rs);
		}
		DBUtil.release(conn, rs);
		return null;
	}

	public static <T> List<T> query(Connection conn, String sql, RowCallBackHandler<T> handler) throws SQLException {
		return query(conn, sql, null, handler);
	}

	public static <T> List<T> query(Connection conn, String sql, PreparedStatementSetter setter,
			RowCallBackHandler<T> handler) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		List<T> list = null;
		if (setter != null) {
			setter.setValues(pstmt);
		}
		ResultSet rs = pstmt.executeQuery();
		if (handler != null)
			list = new ArrayList<>();
		while (rs.next()) {
			list.add(handler.processRow(rs));
		}
		DBUtil.release(conn, rs);
		return list;
	}

	public static int update(Connection conn, String sql, PreparedStatementSetter setter) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (setter != null) {
			setter.setValues(pstmt);
		}
		return pstmt.executeUpdate();
	}

}
