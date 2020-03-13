package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.AdminDao;
import com.tj.stl.dao.MemberDao;

public class AdminSigninService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String adminId = request.getParameter("adminId");
		String adminPw = request.getParameter("adminPw");
		
		AdminDao dao = AdminDao.getInstance();
		if (dao.adminSignin(adminId, adminPw) == null) {
			request.setAttribute("signinerror", "아이디와 비밀번호를 확인하세요.");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("admin", dao.adminSignin(adminId, adminPw));
		}
	}


}
