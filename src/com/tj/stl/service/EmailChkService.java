package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.MemberDao;

public class EmailChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String memberEmail = request.getParameter("memberEmail");
		MemberDao dao = MemberDao.getInstance();
		if (dao.memberEmailcheck(memberEmail) == MemberDao.EXSIST_ON) {
			request.setAttribute("resultmsg", "해당 이메일은 이미 가입되었습니다.");
		}else {
			request.setAttribute("resultmsg", "회원가입 가능한 이메일입니다.");
		}

	}

}
