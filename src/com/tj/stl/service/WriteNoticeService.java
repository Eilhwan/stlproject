package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.NoticeDao;
import com.tj.stl.dto.AdminDto;
import com.tj.stl.dto.NoticeDto;

public class WriteNoticeService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String noticeName = request.getParameter("noticeName");
		String noticeContent = request.getParameter("noticeContent");
		String tempnoticeEvent = request.getParameter("noticeEvent");
		if (tempnoticeEvent == null) {
			tempnoticeEvent = "0";
		}
		byte noticeEvent = Byte.parseByte(tempnoticeEvent);
		HttpSession session = request.getSession();
		AdminDto admin = (AdminDto) session.getAttribute("admin");
		String adminId = admin.getAdminId();
		NoticeDao dao = NoticeDao.getInstance();
		NoticeDto notice = new NoticeDto(0, noticeName, noticeContent, null, noticeEvent, adminId);
		
		if (dao.writeNotice(notice) == NoticeDao.SUCCESS) {
			request.setAttribute("resultmsg", "공지사항 작성 성공");
		}else {
			request.setAttribute("resultmsg", "공지사항 작성 실패");
		}
	}

}
