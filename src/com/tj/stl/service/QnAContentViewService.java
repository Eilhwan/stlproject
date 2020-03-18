package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.QnADao;

public class QnAContentViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qnano = Integer.parseInt(request.getParameter("qnano"));
		QnADao dao = QnADao.getInstance();
		request.setAttribute("qna", dao.getQnaContent(qnano));

	}

}
