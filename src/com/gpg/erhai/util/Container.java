package com.gpg.erhai.util;

public class Container {
	/**
	 * 注册成功
	 */
	public static final String REGISTER_SUCCESS = "注册成功!";
	/***
	 * 注册失败
	 */
	public static final String REGISTER_FAIL = "注册失败!";

	/**
	 * 用户请求
	 */
	public static final String USER_CONTROL = "uer_control";
	/**
	 * 查询用户指令
	 */
	public static final String QUERY_USER = "query_user";
	/**
	 * 注册用户指令
	 */
	public static final String REGISTER_USER = "register_user";

	/**
	 * 二手车请求
	 */
	public static final String CAR_CONTROL = "car_control";
	/**
	 * 查询所有二手车指令
	 */
	public static final String QUERY_ALL_CAR = "query_all_car";
	/**
	 * 修改二手车信息
	 */
	public static final String UPDATE_CAR_BY_ID = "update_car_by_id";
	/**
	 * 通过车id进行租车
	 */
	public static final String QUERY_CAR_BY_ID_TO_RENT = "query_car_by_id_to_rent";
	/**
	 * 添加二手车信息
	 */
	public static final String INSERT_CAR = "insert_car";
	/**
	 * 通过id删除二手车
	 */
	public static final String DELETE_CAR_BY_ID = "delete_car_by_id";
	/**
	 * 修改车的上架状态
	 */
	public static final String UPDATE_CAR_ONLINE_STATE = "update_car_online_state";
	/**
	 * 修改租车价格
	 */
	public static final String UPDATE_CAR_RENT_PRICE_STATE = "update_car_rentprice_state";

	/**
	 * 车品牌请求
	 */
	public static final String CARBRAND_CONTROL = "carBrand_control";
	/**
	 * 查询所有车品牌信息
	 */
	public static final String QUERY_ALL_CARBRAND = "query_all_carBarand";
	/**
	 * 通过id查询车品牌信息
	 */
	public static final String QUERY_CARBRABD_BY_ID = "query_carBrand_by_id";
	/**
	 * 通过id查询车品牌名字
	 */
	public static final String QUERY_CARBRANDNAME_BY_ID = "query_carBrandName_by_id";
	/**
	 * 通过id修改车品牌信息
	 */
	public static final String UPDATE_CARBRAND_BY_ID = "update_carBrand_by_id";
	/**
	 * 通过id删除车品牌
	 */
	public static final String DELETE_CARBRAND_BY_ID = "delete_carBrand_by_id";
	/**
	 * 插入车类型信息
	 */
	public static final String INSERT_CARBRAND = "isnert_carBrand";

	/**
	 * 车类型请求
	 */
	public static final String CARTYPE_CONTROL = "carType_control";
	/**
	 * 查询所有车类型请求
	 */
	public static final String QUERY_ALL_CARTYPE = "query_all_carType";
	/**
	 * 通过id查询车类型信息
	 */
	public static final String QUERY_CARTYPE_BY_ID = "query_carType_by_id";
	/**
	 * 通过id查询车类型信息
	 */
	public static final String QUERY_CARTYPENAME_BY_ID = "query_carTypeName_by_id";
	/**
	 * 通过id修改车类型信息
	 */
	public static final String UPDATE_CARTYPE_BY_ID = "update_carType_by_id";
	/**
	 * 插入车类型信息
	 */
	public static final String INSERT_CARTYPE = "insert_carType";
	/**
	 * 通过id删除车类型
	 */
	public static final String DELETE_CARTYPE_BY_ID = "delete_carType_by_id";

	/**
	 * 租赁信息请求
	 */
	public static final String RENT_CONTROL = "rent_control";
	/**
	 * 查询租车记录
	 */
	public static final String QUERY_RENTRECORD_BY_UID = "query_rentRecord_by_uid";
	/**
	 * 查询所有租车记录
	 */
	public static final String QUERY_ALL_RENTRECORD = "query_all_rentRecord";
	/**
	 * 通过用户id查询租车记录
	 */
	public static final String QUERY_RENTRECORD_BY_CID = "query_rentRecord_by_cid";
	/**
	 * 添加租车记录
	 */
	public static final String RENT_CAR = "rent_car";
	/**
	 * 通过租赁id进行修改
	 */
	public static final String UPDATE_RENTCAR_BY_ID = "update_rentcar_by_id";

	/**
	 * 添加成功
	 */
	public static final String INSERT_SUCCESS = "insert_success";
	/**
	 * 添加失败
	 */
	public static final String INSERT_FAIL = "inser_fail";
	/**
	 * 更新成功
	 */
	public static final String UPDATE_SUCCESS = "update_success";
	/**
	 * 更新失败
	 */
	public static final String UPDATE_FAIL = "update_fail";
	/**
	 * 删除成功
	 */
	public static final String DELECE_SUCCESS = "delete_success";
	/**
	 * 删除失败
	 */
	public static final String DELETE_FAIL = "delete_fail";
	/**
	 * 查询用户名
	 */
	public static final String U_CHECK = "u_check";
	/**
	 * 用户名已存在
	 */
	public static final String U_EXIST = "u_exit";

	/**
	 * 指令错误
	 */
	public static final String OPTION_ERROR = "指令错误";

	public static final String EXIT = "欢迎下次使用!";
	/**
	 * 用户通过车类型搜索
	 */
	public static final String USER_SEARCH_BY_CARTYPE = "user_search_by_cartype";
	/**
	 * 用户通过车品牌搜索
	 */
	public static final String USER_SEARCH_BY_CARBRADN = "user_search_by_carbrand";
	/**
	 * 管理员通过车类型搜索
	 */
	public static final String ADMIN_SEARCH_BY_CARTYPE = "admin_search_by_cartype";
	/**
	 * 管理员通过车品牌搜索
	 */
	public static final String ADMIN_SEARCH_BY_CARBRADN = "admin_search_by_carbrand";
	/**
	 * 用户按价格降序
	 */
	public static final String USER_ORDER_BY_RENTPRICE_DESC = "user_order_by_rentprice_desc";
	/**
	 * 用户按价格升序
	 */
	public static final String USER_ORDER_BY_RENTPRICE_ASC = "user_order_by_rentprice_asc";
	/**
	 * 管理员按价格降序
	 */
	public static final String ADMIN_ORDER_BY_RENTPRICE_DESC = "admin_order_by_rentprice_desc";
	/**
	 * 管理员按价格升序
	 */
	public static final String ADMIN_ORDER_BY_RENTPRICE_ASC = "admin_order_by_rentprice_asc";
	/**
	 * 通过id查询车辆
	 */
	public static final String QUERY_CAR_BY_ID = "query_car_by_id";
	/**
	 * 没有找到你所选的车辆
	 */
	public static final String NO_CAR_TO_RENT = "no_car_to_rent";
}


