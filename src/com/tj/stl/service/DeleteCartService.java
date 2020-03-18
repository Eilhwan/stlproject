package com.tj.stl.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.CartDao;

public class DeleteCartService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Enumeration<String> e = request.getParameterNames();
		CartDao cdao = CartDao.getInstance();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			if (name.equals("checked")) {
				String[] values = request.getParameterValues(name);				
				for (String value : values) {
					cdao.deleteCart(Integer.parseInt(value));
				}   
				
			}
		}
			
		//cdao.deleteCart(cartNo);
	}

}
