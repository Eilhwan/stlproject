package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.CartDao;
import com.tj.stl.dao.ProductEnrollDao;
import com.tj.stl.dto.CartDto;
import com.tj.stl.dto.ProductEnroll;

public class UpdateCartService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String ea = request.getParameter("productEA");
		if (ea.equals("")) {
			ea = "1";
		}
		int productEA = Integer.parseInt(ea);
		int cartNo = Integer.parseInt(request.getParameter("cartNo"));
		
		CartDao cdao = CartDao.getInstance();
		ProductEnrollDao pedao = ProductEnrollDao.getInstance();
		CartDto cart = cdao.getCart(cartNo);
		cart.setProductEA(productEA);
		request.setAttribute("result", cdao.updateCart(cart));
	}

}
