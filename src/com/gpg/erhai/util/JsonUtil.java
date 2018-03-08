package com.gpg.erhai.util;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	public static String objToString(Object obj) {
		return JSON.toJSONString(obj);
	}

	public static <T> List<T> jsonToList(String str, Class<?> clazz) {
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) JSON.parseArray(str, clazz);
		return list;
	}

	@SuppressWarnings("unchecked")
	public static <T> T jsonToObj(String jsonStr, Class<?> clazz) {
		return (T) JSON.parseObject(jsonStr, clazz);
	}
}
