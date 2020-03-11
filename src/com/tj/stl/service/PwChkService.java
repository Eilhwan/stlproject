package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PwChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String memberPw = request.getParameter("memberPw");
		String pwChk = request.getParameter("pwChk");
		if (memberPw.equals(pwChk)) {
			request.setAttribute("resultmsg", "비밀번호가 일치합니다.");
		}else {
			request.setAttribute("resultmsg", "비밀번호가 일치하지 않습니다.");
		}

	}

}
