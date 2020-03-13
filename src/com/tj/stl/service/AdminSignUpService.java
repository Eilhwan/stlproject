package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.AdminDao;

public class AdminSignUpService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AdminDao dao = AdminDao.getInstance();
		String adminId = request.getParameter("adminId");
		String adminPw = request.getParameter("adminPw");
		int result = dao.signUpAdmin(adminId, adminPw);
		if (result == AdminDao.SUCCESS) {
			request.setAttribute("resultmsg", "관리자 등록 성공");
		}else {
			request.setAttribute("resultmsg", "관리자 등록 실패");
		}

	}

}
