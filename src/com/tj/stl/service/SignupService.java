package com.tj.stl.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.MemberDao;
import com.tj.stl.dto.MemberDto;

public class SignupService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao mdao = MemberDao.getInstance();
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String memberTel = request.getParameter("memberTel");
		String memberEmail = request.getParameter("memberEmail");
		String memberPost = request.getParameter("memberPost");
		String memberAddress1 = request.getParameter("memberAddress1");
		String memberAddress2 = request.getParameter("memberAddress2");
		String memberAddress3 = request.getParameter("memberAddress3");
		String memberAddress = memberAddress1 + memberAddress3; 
		Date memberBirth = Date.valueOf(request.getParameter("memberBirth"));
		
		MemberDto member = new MemberDto(memberId, memberPw, memberName, memberTel, memberEmail, memberAddress, memberPost, 0, 0, null, memberBirth);
		
		mdao.memberSignup(member);

	}

}
