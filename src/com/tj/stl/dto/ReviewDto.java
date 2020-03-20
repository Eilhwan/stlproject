package com.tj.stl.dto;

import java.sql.Date;

public class ReviewDto {
	private int reviewNo;
	private int peCode;
	private int reviewStatus;
	private String peName;
	private String reviewName;
	private String reviewImg1;
	private String reviewImg2;
	private String reviewContent;
	private String MemberId;
	private String MemberName;
	private String userIp;
	private Date reviewRdate;
	public ReviewDto(int reviewNo, int peCode, int reviewStatus, String peName, String reviewName, String reviewImg1,
			String reviewImg2, String reviewContent, String memberId, String memberName, String userIp,
			Date reviewRdate) {
		super();
		this.reviewNo = reviewNo;
		this.peCode = peCode;
		this.reviewStatus = reviewStatus;
		this.peName = peName;
		this.reviewName = reviewName;
		this.reviewImg1 = reviewImg1;
		this.reviewImg2 = reviewImg2;
		this.reviewContent = reviewContent;
		MemberId = memberId;
		MemberName = memberName;
		this.userIp = userIp;
		this.reviewRdate = reviewRdate;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getPeCode() {
		return peCode;
	}
	public void setPeCode(int peCode) {
		this.peCode = peCode;
	}
	public int getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(int reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public String getPeName() {
		return peName;
	}
	public void setPeName(String peName) {
		this.peName = peName;
	}
	public String getReviewName() {
		return reviewName;
	}
	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}
	public String getReviewImg1() {
		return reviewImg1;
	}
	public void setReviewImg1(String reviewImg1) {
		this.reviewImg1 = reviewImg1;
	}
	public String getReviewImg2() {
		return reviewImg2;
	}
	public void setReviewImg2(String reviewImg2) {
		this.reviewImg2 = reviewImg2;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
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
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public Date getReviewRdate() {
		return reviewRdate;
	}
	public void setReviewRdate(Date reviewRdate) {
		this.reviewRdate = reviewRdate;
	}
	@Override
	public String toString() {
		return "ReviewDto [reviewNo=" + reviewNo + ", peCode=" + peCode + ", reviewStatus=" + reviewStatus + ", peName="
				+ peName + ", reviewName=" + reviewName + ", reviewImg1=" + reviewImg1 + ", reviewImg2=" + reviewImg2
				+ ", reviewContent=" + reviewContent + ", MemberId=" + MemberId + ", MemberName=" + MemberName
				+ ", userIp=" + userIp + ", reviewRdate=" + reviewRdate + "]";
	}
	
	
}