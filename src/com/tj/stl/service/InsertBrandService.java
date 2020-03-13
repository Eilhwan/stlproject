package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.ProductDao;

public class InsertBrandService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pBrandName = request.getParameter("pBrandName");
		String pBrandCountry = request.getParameter("pBrandCountry");
		ProductDao dao = ProductDao.getInstance();
		
		dao.insertBrand(pBrandName, pBrandCountry);

	}

}
