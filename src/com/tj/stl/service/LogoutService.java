package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("member") != null || session.getAttribute("admin") != null) {
			session.invalidate();
			request.setAttribute("logoutmsg", "성공적으로 로그아웃 되었습니다.");			
		}else {
			request.setAttribute("logouterror", "비정상적인 접근입니다.");
		}
	}

}
