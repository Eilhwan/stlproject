package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.NoticeDao;

public class MainService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		NoticeDao dao = NoticeDao.getInstance();
		
		request.setAttribute("notice1", dao.getMainEvent(1));
		request.setAttribute("notice2", dao.getMainEvent(2));
		request.setAttribute("notice3", dao.getMainEvent(3));
		request.setAttribute("notice4", dao.getMainEvent(4));
		

	}

}
