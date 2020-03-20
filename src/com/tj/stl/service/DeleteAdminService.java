package com.tj.stl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.stl.dao.AdminDao;
import com.tj.stl.dto.AdminDto;

public class DeleteAdminService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AdminDao adao = AdminDao.getInstance();
		HttpSession session = request.getSession();
		
		AdminDto admin = (AdminDto) session.getAttribute("admin");
		
		if (adao.delAdmin(admin.getAdminId()) == AdminDao.SUCCESS) {
			request.setAttribute("resultmsg", "관리자가 성공적으로 삭제되었습니다.");
		}else {
			request.setAttribute("errormsg", "관리자가 삭제되지 않았습니다.");
			
		}

	}

}
