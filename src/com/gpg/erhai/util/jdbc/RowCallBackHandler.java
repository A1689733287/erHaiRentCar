package com.gpg.erhai.util.jdbc;

import java.sql.ResultSet;

public interface RowCallBackHandler<T> {
	T processRow(ResultSet rs);
}
