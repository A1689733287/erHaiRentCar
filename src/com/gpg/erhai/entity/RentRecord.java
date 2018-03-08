package com.gpg.erhai.entity;

public class RentRecord {
	private int id;// 编号
	private int cid;// 汽车编号
	private String carName; // 汽车名称
	private int uid;// 用户编号
	private String uname;// 用户名
	private float rentPrice; // 租金
	private String carBrand;// 品牌
	private String rentTime;// 借车时间
	private float allPrice;// 租金总额
	private String carDesc; // 备注
	private String carType;// 类型
	private String returnCarTime;
	private int status;// 状态 1.租车中 0,已还车

	public RentRecord() {
		super();
	}

	public RentRecord(int cid, String carName, int uid, String uname, float rentPrice, String carBrand, String rentTime,
			float allPrice, String carDesc, String carType, String returnCarTime, int status) {
		super();
		this.cid = cid;
		this.carName = carName;
		this.uid = uid;
		this.uname = uname;
		this.rentPrice = rentPrice;
		this.carBrand = carBrand;
		this.rentTime = rentTime;
		this.allPrice = allPrice;
		this.carDesc = carDesc;
		this.carType = carType;
		this.returnCarTime = returnCarTime;
		this.status = status;
	}

	public RentRecord(int id, int cid, String carName, int uid, String uname, float rentPrice, String carBrand,
			String rentTime, float allPrice, String carDesc, String carType, String returnCarTime, int status) {
		super();
		this.id = id;
		this.cid = cid;
		this.carName = carName;
		this.uid = uid;
		this.uname = uname;
		this.rentPrice = rentPrice;
		this.carBrand = carBrand;
		this.rentTime = rentTime;
		this.allPrice = allPrice;
		this.carDesc = carDesc;
		this.carType = carType;
		this.returnCarTime = returnCarTime;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public float getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(float rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getRentTime() {
		return rentTime;
	}

	public void setRentTime(String rentTime) {
		this.rentTime = rentTime;
	}

	public float getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(float allPrice) {
		this.allPrice = allPrice;
	}

	public String getCarDesc() {
		return carDesc;
	}

	public void setCarDesc(String carDesc) {
		this.carDesc = carDesc;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getReturnCarTime() {
		return returnCarTime;
	}

	public void setReturnCarTime(String returnCarTime) {
		this.returnCarTime = returnCarTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RentRecord [id=" + id + ", cid=" + cid + ", carName=" + carName + ", uid=" + uid + ", uname=" + uname
				+ ", rentPrice=" + rentPrice + ", carBrand=" + carBrand + ", rentTime=" + rentTime + ", allPrice="
				+ allPrice + ", carDesc=" + carDesc + ", carType=" + carType + ", returnCarTime=" + returnCarTime
				+ ", status=" + status + "]";
	}

}
