package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.ProductDao;

public class InsertProductViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ProductDao dao = ProductDao.getInstance();
		
		request.setAttribute("brands", dao.getProductBrandlist());
		request.setAttribute("types", dao.getProductTypelist());

	}

}
