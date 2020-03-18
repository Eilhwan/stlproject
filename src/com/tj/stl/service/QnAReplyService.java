package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.QnADao;
import com.tj.stl.dto.AdminDto;
import com.tj.stl.dto.QnA;

public class QnAReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qnano = Integer.parseInt(request.getParameter("qnano"));
		QnADao dao = QnADao.getInstance();
		HttpSession session = request.getSession();
		AdminDto admin = (AdminDto) session.getAttribute("admin");
		String memberId = admin.getAdminId();
		
		
		QnA qna = dao.getQnaContent(qnano);
		String qnaName = request.getParameter("qnaName");
		String qnaContent = request.getParameter("qnaContent");
		
		
		qna.setQnaName(qnaName);
		qna.setQnaContent(qnaContent);
		qna.setMemberId(memberId);
		dao.replyQna(qna);
	}

}
