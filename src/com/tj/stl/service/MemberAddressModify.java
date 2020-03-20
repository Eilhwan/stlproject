package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.MemberDao;
import com.tj.stl.dto.MemberDto;

public class MemberAddressModify implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = MemberDao.getInstance();
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		String memberPost = request.getParameter("memberPost");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		String address4 = request.getParameter("address4");
		if (address3 == null) {
			address3 = "";
					
		}
		String address = address1 + address3 + "(" + address2 + "," + address4 + ")";
		member.setMemberAddress(address);
		dao.memberModifyAddress(member);
		session.setAttribute("member", member);
		
	}

}
