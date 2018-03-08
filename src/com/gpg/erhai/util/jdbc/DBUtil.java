package com.gpg.erhai.util.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	private static final ThreadLocal<Connection> LOCAL = new ThreadLocal<>();
	private static String driver;
	private static String url;
	private static String userName;
	private static String userPwd;

	/**
	 * 静态代码块初始化资源
	 */
	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("src/jdbcinfo.properties"));
		} catch (FileNotFoundException e1) {
			System.out.println("文件未找到");
		} catch (IOException e1) {
			System.out.println("文件读取失败");
		}
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		userName = prop.getProperty("username");
		userPwd = prop.getProperty("password");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动!");
		}
	}

	/**
	 * 获取连接对象
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = LOCAL.get();
		try {
			if (null == conn || conn.isClosed()) {
				conn = DriverManager.getConnection(url, userName, userPwd);
			}
			LOCAL.set(conn);
		} catch (SQLException e) {
			System.out.println("获取连接对象失败!");
		}
		return conn;
	}

	/**
	 * 释放资源
	 * 
	 * @param conn
	 * @param rs
	 */
	public static void release(Connection conn, ResultSet rs) {
		try {
			if (rs != null) {
				release(rs.getStatement());
				rs.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 释放资源
	 * 
	 * @param stmt
	 */
	public static void release(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param conn
	 *            数据库连接对象
	 * @param sql
	 *            sql语句
	 * @param setter
	 *            通配符处理，如不需要设置通配符则设置为null,否则需要创建对象并实现里面的方法来设置统配符
	 *            <code>
	 *            new PreparedStatementSetter(){<br/>
	 *            			@Override
							public void setValues(PreparedStatement pstmt) throws SQLException {<br/>
								pstmt.setInt(1, id);
							}<br/>
	 *            }
	 *            </code>
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement getPreparedStatement(Connection conn, String sql, PreparedStatementSetter setter)
			throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (setter != null) {
			setter.setValues(pstmt);
		}
		return pstmt;
	}

	public static void remove() {
		if (LOCAL != null) {
			LOCAL.remove();
		}
	}

}
