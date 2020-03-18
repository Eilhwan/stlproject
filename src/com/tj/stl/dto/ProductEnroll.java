package com.tj.stl.dto;

import java.sql.Date;

public class ProductEnroll {
	private int peCode;
	private int productCode;
	private int pTypeCode;
	private String peName;
	private String peContent;
	private String peImg1;
	private String peImg2;
	private Date peRdate;
	private int pePrice;
	private int sellCnt;
	private int pePoint;
	private int peStars;
	private int peDiscount;
	private int peStatus;

	public ProductEnroll(int peCode, int productCode, int pTypeCode, String peName, String peContent, String peImg1,
			String peImg2, Date peRdate, int pePrice, int sellCnt, int pePoint, int peStars, int peDiscount,
			int peStatus) {
		super();
		this.peCode = peCode;
		this.productCode = productCode;
		this.pTypeCode = pTypeCode;
		this.peName = peName;
		this.peContent = peContent;
		this.peImg1 = peImg1;
		this.peImg2 = peImg2;
		this.peRdate = peRdate;
		this.pePrice = pePrice;
		this.sellCnt = sellCnt;
		this.pePoint = pePoint;
		this.peStars = peStars;
		this.peDiscount = peDiscount;
		this.peStatus = peStatus;
	}

	public int getPeCode() {
		return peCode;
	}

	public void setPeCode(int peCode) {
		this.peCode = peCode;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public int getpTypeCode() {
		return pTypeCode;
	}

	public void setpTypeCode(int pTypeCode) {
		this.pTypeCode = pTypeCode;
	}

	public String getPeName() {
		return peName;
	}

	public void setPeName(String peName) {
		this.peName = peName;
	}

	public String getPeContent() {
		return peContent;
	}

	public void setPeContent(String peContent) {
		this.peContent = peContent;
	}

	public String getPeImg1() {
		return peImg1;
	}

	public void setPeImg1(String peImg1) {
		this.peImg1 = peImg1;
	}

	public String getPeImg2() {
		return peImg2;
	}

	public void setPeImg2(String peImg2) {
		this.peImg2 = peImg2;
	}

	public Date getPeRdate() {
		return peRdate;
	}

	public void setPeRdate(Date peRdate) {
		this.peRdate = peRdate;
	}

	public int getPePrice() {
		return pePrice;
	}

	public void setPePrice(int pePrice) {
		this.pePrice = pePrice;
	}

	public int getSellCnt() {
		return sellCnt;
	}

	public void setSellCnt(int sellCnt) {
		this.sellCnt = sellCnt;
	}

	public int getPePoint() {
		return pePoint;
	}

	public void setPePoint(int pePoint) {
		this.pePoint = pePoint;
	}

	public int getPeStars() {
		return peStars;
	}

	public void setPeStars(int peStars) {
		this.peStars = peStars;
	}

	public int getPeDiscount() {
		return peDiscount;
	}

	public void setPeDiscount(int peDiscount) {
		this.peDiscount = peDiscount;
	}

	public int getPeStatus() {
		return peStatus;
	}

	public void setPeStatus(int peStatus) {
		this.peStatus = peStatus;
	}

	@Override
	public String toString() {
		return "ProductEnroll [peCode=" + peCode + ", productCode=" + productCode + ", pTypeCode=" + pTypeCode
				+ ", peName=" + peName + ", peContent=" + peContent + ", peImg1=" + peImg1 + ", peImg2=" + peImg2
				+ ", peRdate=" + peRdate + ", pePrice=" + pePrice + ", sellCnt=" + sellCnt + ", pePoint=" + pePoint
				+ ", peStars=" + peStars + ", peDiscount=" + peDiscount + ", peStatus=" + peStatus + "]";
	}

}
