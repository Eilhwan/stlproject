package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.ProductDao;
import com.tj.stl.dao.ProductEnrollDao;
import com.tj.stl.dto.ProductEnroll;

public class UserProductContentViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int peCode = Integer.parseInt(request.getParameter("peCode"));
		ProductEnrollDao pedao = ProductEnrollDao.getInstance();
		ProductDao pdao = ProductDao.getInstance();
		request.setAttribute("pe", pedao.getUserProduct(peCode));
		
		request.setAttribute("product", pdao.getProductContent(pedao.getUserProduct(peCode).getProductCode()));
		
	}

}
