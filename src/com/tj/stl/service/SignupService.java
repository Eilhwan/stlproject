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
		String memberAddress1 = request.getParameter("address1");
		String memberAddress2 = request.getParameter("address2");
		String memberAddress3 = request.getParameter("address3");
		String memberAddress4 = request.getParameter("address4");
		String memberAddress = memberAddress1 + memberAddress3 + "(" + memberAddress2 + "," + memberAddress4 + ")"; 
		Date memberBirth = Date.valueOf(request.getParameter("memberBirth"));
		
		MemberDto member = new MemberDto(memberId, memberPw, memberName, memberTel, memberEmail, memberAddress2, memberPost, null, 0, 0, null, memberBirth, 0);
		
		if (mdao.memberSignup(member) == MemberDao.SUCCESS) {
			request.setAttribute("resultmsg", "회원가입이 성공적으로 완료되었습니다.");
		}else {
			request.setAttribute("errormsg", "회원가입을 실패했습니다");			
		}

	}

}
