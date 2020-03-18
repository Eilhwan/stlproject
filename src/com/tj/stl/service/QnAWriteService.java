package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.QnADao;
import com.tj.stl.dto.MemberDto;
import com.tj.stl.dto.QnA;

public class QnAWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		QnADao dao = QnADao.getInstance();
		
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		String memberId = member.getMemberId();
		String qnaName = request.getParameter("qnaName");
		String qnaContent = request.getParameter("qnaContent");
		QnA qna = new QnA(0, memberId, qnaName, qnaContent, null, 1, 0);
		dao.writeQna(qna);

	}

}
