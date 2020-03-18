package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.NoticeDao;
import com.tj.stl.dao.ProductDao;

public class ProductContentViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int productCode = Integer.parseInt(request.getParameter("productCode"));
		ProductDao dao = ProductDao.getInstance();
		
		
		request.setAttribute("product", dao.getProductContent(productCode));

	}

}
