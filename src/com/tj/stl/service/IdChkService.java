package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.MemberDao;

public class IdChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String memberId = request.getParameter("memberId");
		MemberDao dao = MemberDao.getInstance();
		if (dao.memberIdcheck(memberId) == MemberDao.EXSIST_ON) {
			request.setAttribute("resultmsg", "해당 아이디가 존재합니다.");
		}else {
			request.setAttribute("resultmsg", "회원가입 가능한 아이디입니다.");
		}

	}

}
