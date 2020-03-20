package com.tj.stl.service;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.CartDao;
import com.tj.stl.dao.ProductEnrollDao;
import com.tj.stl.dto.CartDto;
import com.tj.stl.dto.MemberDto;
import com.tj.stl.dto.ProductEnroll;

public class CheckoutService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		CartDao cdao = CartDao.getInstance();			
			Enumeration<String> paramNames = request.getParameterNames();
			
			ArrayList<CartDto> list = new ArrayList<CartDto>();
			
			while (paramNames.hasMoreElements()) {
				String name = (String) paramNames.nextElement();
				if (name.equals("checked")) {
					String values[] = request.getParameterValues(name);
					
					for (int i = 0; i < values.length; i++) {
						list.add(cdao.getCart(Integer.parseInt(values[i])));
					}
				}
			}
			
			request.setAttribute("list", list);
		}
			
	}

