package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.NoticeDao;

public class NoticeContentViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		NoticeDao dao = NoticeDao.getInstance();
		
		request.setAttribute("notice", dao.getNoticeContent(noticeNo));
	}

}
