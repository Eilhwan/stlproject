package com.tj.stl.service;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.CartDao;
import com.tj.stl.dao.OrderDetailDao;
import com.tj.stl.dao.OrdersDao;
import com.tj.stl.dto.CartDto;
import com.tj.stl.dto.MemberDto;
import com.tj.stl.dto.OrdersDto;

public class OrderProductService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		OrdersDao odao = OrdersDao.getInstance();
		OrderDetailDao oddao = OrderDetailDao.getInstance();
		CartDao cdao = CartDao.getInstance();
		
		int orderTotal = Integer.parseInt(request.getParameter("orderTotal"));
		
		MemberDto member = (MemberDto) session.getAttribute("member");
		odao.orderProduct(member.getMemberAddress(), member.getMemberTel(), orderTotal, member.getMemberId());
		
		OrdersDto order = odao.getOrder(member.getMemberId());
		int orderNo = order.getOrderNo();
		ArrayList<CartDto> carts = cdao.getCart(member.getMemberId());
		for (int i = 0; i < carts.size(); i++) {
			int productCode = carts.get(i).getProductCode();
			int odPrice = carts.get(i).getCartPrice();
			int odCnt = carts.get(i).getProductEA();
			oddao.orderProduct(productCode, orderNo, odPrice, odCnt);
		}
		cdao.deleteCart(member.getMemberId());
	}

}
