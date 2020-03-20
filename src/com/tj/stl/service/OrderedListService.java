package com.tj.stl.service;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.OrderDetailDao;
import com.tj.stl.dao.OrdersDao;
import com.tj.stl.dto.MemberDto;
import com.tj.stl.dto.OrderDetailDto;
import com.tj.stl.dto.OrdersDto;

public class OrderedListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		OrderDetailDao oddao = OrderDetailDao.getInstance();
		OrdersDao odao = OrdersDao.getInstance();
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		
		// 주문 건수 출력
		int OrderCnt = odao.getOrderCnt(member.getMemberId());
		// 오더 넘버 출력
		ArrayList<OrdersDto> orders = odao.getOrderList(member.getMemberId());
		ArrayList<OrderDetailDto> od = oddao.getOrderDtails(member.getMemberId());
		
		
		request.setAttribute("oc", OrderCnt);
		request.setAttribute("orders", orders);
		request.setAttribute("od", od);
	}

}
