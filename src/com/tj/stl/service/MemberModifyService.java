package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.MemberDao;
import com.tj.stl.dto.MemberDto;

public class MemberModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao mdao = MemberDao.getInstance();
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		String memberId = member.getMemberId();
		String memberName = request.getParameter("memberName");
		String memberTel = request.getParameter("memberTel");
		String memberEmail = request.getParameter("memberEmail");
		String nowPw = request.getParameter("nowPw");
		String memberPw = request.getParameter("memberPw");
		
		if (memberPw == null) {
			memberPw = nowPw;
		}
		
		System.out.println(memberName);
		System.out.println(memberTel);
		System.out.println(memberEmail);
		System.out.println(memberPw);
		member.setMemberName(memberName);
		member.setMemberTel(memberTel);
		member.setMemberEmail(memberEmail);
		member.setMemberPw(memberPw);
		
		int result = mdao.memberModify(member);
		if (result == MemberDao.SUCCESS) {
			MemberDto dto = mdao.memberSignin(member.getMemberId(), member.getMemberPw());
			session.setAttribute("member", dto);
		}
		

	}

}
