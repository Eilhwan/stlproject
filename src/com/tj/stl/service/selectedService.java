package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.ProductDao;

public class selectedService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String productName = request.getParameter("productName");
		ProductDao dao = ProductDao.getInstance();
		request.setAttribute("product", dao.getUserProduct(productName));
		
	}

}
