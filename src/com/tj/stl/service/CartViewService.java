package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.CartDao;
import com.tj.stl.dto.MemberDto;

public class CartViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		if (member == null) {
			request.setAttribute("resultmsg", "잘못된 접근입니다");
		}else {
			CartDao cdao = CartDao.getInstance();
			String memberId = member.getMemberId();
			request.setAttribute("cnt", cdao.getCartCnt(memberId));
			request.setAttribute("list", cdao.getCart(memberId));
		}
	}

}
