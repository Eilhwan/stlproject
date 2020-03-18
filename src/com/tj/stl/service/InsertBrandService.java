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
		if (dao.chkBrand(pBrandName) == ProductDao.EXSIST_ON) {
			request.setAttribute("resultmsg", "동일한 이름의 브랜드가 이미 존재합니다.");
		}else {
			dao.insertBrand(pBrandName, pBrandCountry);			
			request.setAttribute("resultmsg", "브랜드가 성공적으로 등록되었습니다.");
		}

	}

}
