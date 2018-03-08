package com.gpg.erhai.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 获取现在时间
	 *
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		return dateString;
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 计算时间差 按小时
	 * @param oldDate
	 * @param newDate
	 * @return
	 */
	public static long timeMinus(String oldDate, String newDate) {
		Date date = strToDateLong(oldDate);

		Date date1 = strToDateLong(newDate);
		long difference = date1.getTime() - date.getTime();
		long hours =  (difference) / (1000 * 3600);
		return hours + 1;
	}
}
