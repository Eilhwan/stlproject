package com.tj.stl.dto;

public class ProductType {
	private int pTypeCode;
	private String pTypeName;
	public ProductType(int pTypeCode, String pTypeName) {
		super();
		this.pTypeCode = pTypeCode;
		this.pTypeName = pTypeName;
	}
	public int getpTypeCode() {
		return pTypeCode;
	}
	public void setpTypeCode(int pTypeCode) {
		this.pTypeCode = pTypeCode;
	}
	public String getpTypeName() {
		return pTypeName;
	}
	public void setpTypeName(String pTypeName) {
		this.pTypeName = pTypeName;
	}
	@Override
	public String toString() {
		return "ProductType [pTypeCode=" + pTypeCode + ", pTypeName=" + pTypeName + "]";
	}
	
}
