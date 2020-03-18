package com.tj.stl.dto;

public class CartDto {
	private int cartNo;
	private String memberId;
	private int productEA;
	private int productCode;
	private int cartPrice;
	private String productName;
	private int peDiscount;
	private int pePrice;
	public CartDto(int cartNo, String memberId, int productEA, int productCode, int cartPrice, String productName,
			int peDiscount, int pePrice) {
		super();
		this.cartNo = cartNo;
		this.memberId = memberId;
		this.productEA = productEA;
		this.productCode = productCode;
		this.cartPrice = cartPrice;
		this.productName = productName;
		this.peDiscount = peDiscount;
		this.pePrice = pePrice;
	}
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getProductEA() {
		return productEA;
	}
	public void setProductEA(int productEA) {
		this.productEA = productEA;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public int getCartPrice() {
		return cartPrice;
	}
	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPeDiscount() {
		return peDiscount;
	}
	public void setPeDiscount(int peDiscount) {
		this.peDiscount = peDiscount;
	}
	public int getPePrice() {
		return pePrice;
	}
	public void setPePrice(int pePrice) {
		this.pePrice = pePrice;
	}
	@Override
	public String toString() {
		return "CartDto [cartNo=" + cartNo + ", memberId=" + memberId + ", productEA=" + productEA + ", productCode="
				+ productCode + ", cartPrice=" + cartPrice + ", productName=" + productName + ", peDiscount="
				+ peDiscount + ", pePrice=" + pePrice + "]";
	}
	
	
}
