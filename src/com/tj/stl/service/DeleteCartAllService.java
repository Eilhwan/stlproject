package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.CartDao;

public class DeleteCartAllService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String memberId = request.getParameter("memberId");
		
		CartDao cdao = CartDao.getInstance();
		cdao.deleteCart(memberId);
	}

}
