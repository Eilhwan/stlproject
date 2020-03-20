package com.tj.stl.service;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.CartDao;
import com.tj.stl.dao.MemberDao;
import com.tj.stl.dao.OrderDetailDao;
import com.tj.stl.dao.OrdersDao;
import com.tj.stl.dao.ProductDao;
import com.tj.stl.dao.ProductEnrollDao;
import com.tj.stl.dto.CartDto;
import com.tj.stl.dto.MemberDto;
import com.tj.stl.dto.OrdersDto;
import com.tj.stl.dto.ProductDto;
import com.tj.stl.dto.ProductEnroll;

public class OrderProductService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		OrdersDao odao = OrdersDao.getInstance();
		OrderDetailDao oddao = OrderDetailDao.getInstance();
		CartDao cdao = CartDao.getInstance();
		ProductDao pdao = ProductDao.getInstance();
		ProductEnrollDao pedao = ProductEnrollDao.getInstance();
		MemberDao mdao = MemberDao.getInstance();
		
		int orderTotal = Integer.parseInt(request.getParameter("orderTotal"));
		String[] productCodes = request.getParameterValues("productCode");
		String[] productEAs = request.getParameterValues("productEA");
		String[] cartPrices = request.getParameterValues("cartPrice");
		
		MemberDto member = (MemberDto) session.getAttribute("member");
		
		odao.orderProduct(member.getMemberAddress(), member.getMemberTel(), orderTotal, member.getMemberId());
		OrdersDto order = odao.getOrder(member.getMemberId());
		int orderNo = order.getOrderNo();
		int point = 0;
		int totalSell = 0;
		ArrayList<CartDto> carts = cdao.getCart(member.getMemberId());
		for (int i = 0; i < productEAs.length; i++) {
			oddao.orderProduct(Integer.parseInt(productCodes[i]), orderNo, Integer.parseInt(cartPrices[i]), Integer.parseInt(productEAs[i])); 			
			pdao.stockMinus(Integer.parseInt(productEAs[i]), Integer.parseInt(productCodes[i]));
			pedao.upSellCnt(Integer.parseInt(productEAs[i]), Integer.parseInt(productCodes[i]));
			point += pedao.getProductPoint(Integer.parseInt(productCodes[i])) * Integer.parseInt(productEAs[i]);
			totalSell += Integer.parseInt(cartPrices[i]);
		}
		System.out.println("totalsell=" + totalSell);
		System.out.println("point=" + point);
		System.out.println("id=" + member.getMemberId());
		mdao.memberTotUp(totalSell, point, member.getMemberId());
		odao.orderStatusChange(2, orderNo);
		request.setAttribute("od", oddao.getOrderDtos(orderNo));
		cdao.deleteCart(member.getMemberId());
		request.setAttribute("order", order);
		request.setAttribute("msg", "주문이 성공적으로 완료되었습니다.");
		session.setAttribute("member", mdao.memberSignin(member.getMemberId(), member.getMemberPw()));
	}

}
