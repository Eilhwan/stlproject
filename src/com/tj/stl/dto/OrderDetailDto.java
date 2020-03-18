package com.tj.stl.dto;

public class OrderDetailDto {
	private int odno;
	private int productCode;
	private int orderNo;
	private int odPrice;
	private int odCnt;
	private String productName;
	private String memberId;
	private String memberName;
	public OrderDetailDto(int odno, int productCode, int orderNo, int odPrice, int odCnt, String productName,
			String memberId, String memberName) {
		super();
		this.odno = odno;
		this.productCode = productCode;
		this.orderNo = orderNo;
		this.odPrice = odPrice;
		this.odCnt = odCnt;
		this.productName = productName;
		this.memberId = memberId;
		this.memberName = memberName;
	}
	public int getOdno() {
		return odno;
	}
	public void setOdno(int odno) {
		this.odno = odno;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getOdPrice() {
		return odPrice;
	}
	public void setOdPrice(int odPrice) {
		this.odPrice = odPrice;
	}
	public int getOdCnt() {
		return odCnt;
	}
	public void setOdCnt(int odCnt) {
		this.odCnt = odCnt;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Override
	public String toString() {
		return "OrderDetailDto [odno=" + odno + ", productCode=" + productCode + ", orderNo=" + orderNo + ", odPrice="
				+ odPrice + ", odCnt=" + odCnt + ", productName=" + productName + ", memberId=" + memberId
				+ ", memberName=" + memberName + "]";
	}
	
	
	
	
}