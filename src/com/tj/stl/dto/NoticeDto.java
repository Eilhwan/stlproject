package com.tj.stl.dto;

import java.sql.Date;

public class NoticeDto {
	private int noticeNo;
	private String noticeName;
	private String noticeContent;
	private Date noticeRdate;
	private byte noticeEvent;
	private String adminId;
	
	public NoticeDto(int noticeNo, String noticeName, String noticeContent, Date noticeRdate, byte noticeEvent,
			String adminId) {
		super();
		this.noticeNo = noticeNo;
		this.noticeName = noticeName;
		this.noticeContent = noticeContent;
		this.noticeRdate = noticeRdate;
		this.noticeEvent = noticeEvent;
		this.adminId = adminId;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public Date getNoticeRdate() {
		return noticeRdate;
	}
	public void setNoticeRdate(Date noticeRdate) {
		this.noticeRdate = noticeRdate;
	}
	public byte getNoticeEvent() {
		return noticeEvent;
	}
	public void setNoticeEvent(byte noticeEvent) {
		this.noticeEvent = noticeEvent;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	@Override
	public String toString() {
		return "NoticeDto [noticeNo=" + noticeNo + ", noticeName=" + noticeName + ", noticeContent=" + noticeContent
				+ ", noticeRdate=" + noticeRdate + ", noticeEvent=" + noticeEvent + ", adminId=" + adminId + "]";
	}
	
	
}