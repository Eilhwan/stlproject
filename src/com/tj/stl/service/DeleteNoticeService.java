package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.NoticeDao;

public class DeleteNoticeService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		NoticeDao ndao = NoticeDao.getInstance();
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		if (ndao.deleteNotice(noticeNo) == NoticeDao.SUCCESS) {
			request.setAttribute("resultmsg", "성공적으로 삭제되었습니다");			
		}else {
			request.setAttribute("errormsg", "삭제 실패하였습니다");			
		}
	}

}
