package com.gpg.erhai.util.jdbc;

import java.sql.PreparedStatement;

public interface PreparedStatementSetter {
	// 通过PreparedStatement来设置一些值来替换占位符"?"
	void setValues(PreparedStatement pstmt);
}
