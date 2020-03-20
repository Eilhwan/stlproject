package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.MemberDao;
import com.tj.stl.dto.MemberDto;

public class DeleteMemberService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		MemberDao mdao = MemberDao.getInstance();
		
		String memberPw = request.getParameter("memberPw");
		if (memberPw.equals(member.getMemberPw())) {
			mdao.memberDelete(member);
			request.setAttribute("resultmsg", "회원탈퇴가 완료되었습니다. 사용해주셔서 감사합니다.");
			session.invalidate();
		}else {
			request.setAttribute("resulterror", "비밀번호가 일치하지 않습니다.");
		}
		

	}

}
