package com.gpg.erhai.entity;

public class CarBrand {
	private int id;
	private String brandName;

	public CarBrand() {
		super();
	}

	public CarBrand(int id, String brandName) {
		super();
		this.id = id;
		this.brandName = brandName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
