package com.tj.stl.dto;

public class ProductBrand {
	private int pBrandCode;
	private String pBrandName;
	private String pBrandCountry;
	public ProductBrand(int pBrandCode, String pBrandName, String pBrandCountry) {
		super();
		this.pBrandCode = pBrandCode;
		this.pBrandName = pBrandName;
		this.pBrandCountry = pBrandCountry;
	}
	public int getpBrandCode() {
		return pBrandCode;
	}
	public void setpBrandCode(int pBrandCode) {
		this.pBrandCode = pBrandCode;
	}
	public String getpBrandName() {
		return pBrandName;
	}
	public void setpBrandName(String pBrandName) {
		this.pBrandName = pBrandName;
	}
	public String getpBrandCountry() {
		return pBrandCountry;
	}
	public void setpBrandCountry(String pBrandCountry) {
		this.pBrandCountry = pBrandCountry;
	}
	@Override
	public String toString() {
		return "ProductBrand [pBrandCode=" + pBrandCode + ", pBrandName=" + pBrandName + ", pBrandCountry="
				+ pBrandCountry + "]";
	}
	
}
