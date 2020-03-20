package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.ReviewDao;

import oracle.net.aso.r;

public class ReviewContentViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReviewDao rdao = ReviewDao.getInstance();
		int reno = Integer.parseInt(request.getParameter("reviewNo"));
		

		request.setAttribute("review", 	rdao.getReviewContent(reno));
	}

}
