package com.tj.stl.dto;

public class OrdersDto {
	private int orderNo;
	private String orderDate;
	private String orderAddress;
	private String orderTel;
	private int orderTotal;
	private String MemberId;
	private String MemberName;
	private int orderStatus;
	public OrdersDto(int orderNo, String orderDate, String orderAddress, String orderTel, int orderTotal, String memberId,
			String memberName, int orderStatus) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.orderAddress = orderAddress;
		this.orderTel = orderTel;
		this.orderTotal = orderTotal;
		MemberId = memberId;
		MemberName = memberName;
		this.orderStatus = orderStatus;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getOrderTel() {
		return orderTel;
	}
	public void setOrderTel(String orderTel) {
		this.orderTel = orderTel;
	}
	public int getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
	public String getMemberId() {
		return MemberId;
	}
	public void setMemberId(String memberId) {
		MemberId = memberId;
	}
	public String getMemberName() {
		return MemberName;
	}
	public void setMemberName(String memberName) {
		MemberName = memberName;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "OrdersDto [orderNo=" + orderNo + ", orderDate=" + orderDate + ", orderAddress=" + orderAddress
				+ ", orderTel=" + orderTel + ", orderTotal=" + orderTotal + ", MemberId=" + MemberId + ", MemberName="
				+ MemberName + ", orderStatus=" + orderStatus + "]";
	}
	
	
	
}
