package com.tj.stl.dto;

import java.sql.Date;

public class MemberDto {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberTel;
	private String memberEmail;
	private String memberAddress;
	private String memberPost;
	private int totalSpent;
	private int gradeLevel;
	private Date memberRdate;
	private Date memberBirth;
	
	public MemberDto() {}
	public MemberDto(String memberId, String memberPw, String memberName, String memberTel, String memberEmail,
			String memberAddress, String memberPost, int totalSpent, int gradeLevel, Date memberRdate,
			Date memberBirth) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberTel = memberTel;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.memberPost = memberPost;
		this.totalSpent = totalSpent;
		this.gradeLevel = gradeLevel;
		this.memberRdate = memberRdate;
		this.memberBirth = memberBirth;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberPost() {
		return memberPost;
	}
	public void setMemberPost(String memberPost) {
		this.memberPost = memberPost;
	}
	public int gettotalSpent() {
		return totalSpent;
	}
	public void settotalSpent(int totalSpent) {
		this.totalSpent = totalSpent;
	}
	public int getGradeLevel() {
		return gradeLevel;
	}
	public void setGradeLevel(int gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	public Date getMemberRdate() {
		return memberRdate;
	}
	public void setMemberRdate(Date memberRdate) {
		this.memberRdate = memberRdate;
	}
	public Date getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}
	@Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberTel=" + memberTel + ", memberEmail=" + memberEmail + ", memberAddress=" + memberAddress
				+ ", memberPost=" + memberPost + ", totalSpent=" + totalSpent + ", gradeLevel=" + gradeLevel
				+ ", memberRdate=" + memberRdate + ", memberBirth=" + memberBirth + "]";
	}
	
	
	
}