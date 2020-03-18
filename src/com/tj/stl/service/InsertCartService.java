package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.CartDao;
import com.tj.stl.dao.ProductEnrollDao;
import com.tj.stl.dto.MemberDto;
import com.tj.stl.dto.ProductEnroll;

public class InsertCartService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String ea = request.getParameter("productEA");
		String pe = request.getParameter("peCode");

		System.out.println(ea);
		if (ea == null) {
			ea = "1";
		}
		int productEA = Integer.parseInt(ea);
		int peCode = Integer.parseInt(pe);
		
		HttpSession session = request.getSession();
		CartDao cdao = CartDao.getInstance();
		ProductEnrollDao pedao = ProductEnrollDao.getInstance();
		
		MemberDto member = (MemberDto) session.getAttribute("member");
		ProductEnroll product = pedao.getUserProduct(peCode);
		
		cdao.insertCart(product, member, productEA);
		request.setAttribute("resultmsg", "장바구니에 추가하였습니다.");
		
	}

}
