package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.MemberDao;

public class SigninService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		MemberDao dao = MemberDao.getInstance();
		if (dao.memberSignin(memberId, memberPw) == null) {
			request.setAttribute("signinerror", "아이디와 비밀번호를 확인하세요.");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("member", dao.memberSignin(memberId, memberPw));
		}
	}

}
