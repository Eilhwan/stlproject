package com.tj.stl.dto;

import java.sql.Date;

public class QnA {
    private int qnano;
    private String memberId;
    private String qnaName;
    private String qnaContent;
    private Date qnaRdate;
    private int qnaAnswer;
    private int qnaGroup;
	public QnA(int qnano, String memberId, String qnaName, String qnaContent, Date qnaRdate, int qnaAnswer,
			int qnaGroup) {
		super();
		this.qnano = qnano;
		this.memberId = memberId;
		this.qnaName = qnaName;
		this.qnaContent = qnaContent;
		this.qnaRdate = qnaRdate;
		this.qnaAnswer = qnaAnswer;
		this.qnaGroup = qnaGroup;
	}
	public int getQnano() {
		return qnano;
	}
	public void setQnano(int qnano) {
		this.qnano = qnano;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getQnaName() {
		return qnaName;
	}
	public void setQnaName(String qnaName) {
		this.qnaName = qnaName;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public Date getQnaRdate() {
		return qnaRdate;
	}
	public void setQnaRdate(Date qnaRdate) {
		this.qnaRdate = qnaRdate;
	}
	public int getQnaAnswer() {
		return qnaAnswer;
	}
	public void setQnaAnswer(int qnaAnswer) {
		this.qnaAnswer = qnaAnswer;
	}
	public int getQnaGroup() {
		return qnaGroup;
	}
	public void setQnaGroup(int qnaGroup) {
		this.qnaGroup = qnaGroup;
	}
	@Override
	public String toString() {
		return "QnA [qnano=" + qnano + ", memberId=" + memberId + ", qnaName=" + qnaName + ", qnaContent=" + qnaContent
				+ ", qnaRdate=" + qnaRdate + ", qnaAnswer=" + qnaAnswer + ", qnaGroup=" + qnaGroup + "]";
	}
    
}
