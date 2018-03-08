package com.gpg.erhai.entity;

public class Car {
	private int cid; // 车id
	private String carModel;// 车型号
	private String carNumber; // 车牌号
	private String carDesc; // 描述
	private String color; // 颜色
	private float price; // 总价格
	private String carBrand;// 品牌
	private String carType; // 车类型
	private float rentPrice; // 租用价格
	private int isRent; // 是否租
	private int isOnline; // 是否上架

	public Car() {
		super();
	}

	public Car(int cid, String carModel, String carNumber, String carDesc, String color, float price, String carBrand,
			String carType, float rentPrice, int isRent, int isOnline) {
		super();
		this.cid = cid;
		this.carModel = carModel;
		this.carNumber = carNumber;
		this.carDesc = carDesc;
		this.color = color;
		this.price = price;
		this.carBrand = carBrand;
		this.carType = carType;
		this.rentPrice = rentPrice;
		this.isRent = isRent;
		this.isOnline = isOnline;
	}

	public Car(String carModel, String carNumber, String carDesc, String color, float price, String carBrand,
			String carType, float rentPrice, int isRent, int isOnline) {
		super();
		this.carModel = carModel;
		this.carNumber = carNumber;
		this.carDesc = carDesc;
		this.color = color;
		this.price = price;
		this.carBrand = carBrand;
		this.carType = carType;
		this.rentPrice = rentPrice;
		this.isRent = isRent;
		this.isOnline = isOnline;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarDesc() {
		return carDesc;
	}

	public void setCarDesc(String carDesc) {
		this.carDesc = carDesc;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public float getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(float rentPrice) {
		this.rentPrice = rentPrice;
	}

	public int getIsRent() {
		return isRent;
	}

	public void setIsRent(int isRent) {
		this.isRent = isRent;
	}

	public int getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}

	@Override
	public String toString() {
		return "Car [cid=" + cid + ", carModel=" + carModel + ", carNumber=" + carNumber + ", carDesc=" + carDesc
				+ ", color=" + color + ", price=" + price + ", carBrand=" + carBrand + ", carType=" + carType
				+ ", rentPrice=" + rentPrice + ", isRent=" + isRent + ", isOnline=" + isOnline + "]";
	}

}
