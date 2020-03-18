package com.tj.stl.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.NoticeDao;
import com.tj.stl.dao.QnADao;
import com.tj.stl.dto.NoticeDto;
import com.tj.stl.dto.QnA;

public class QnaListViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int BLOCKSIZE = 10, PAGESIZE = 10;
		int startRow = (currentPage - 1) * PAGESIZE + 1;
		int endRow = startRow + PAGESIZE - 1;
		System.out.println(startRow);
		System.out.println(endRow);
		QnADao dao = QnADao.getInstance();
		
		ArrayList<QnA> list = dao.getQnAlist(startRow, endRow);
		
		int pageCnt = (int) Math.ceil(((double)dao.getNoticeCnt()/PAGESIZE));
		int startPage = ((currentPage - 1) / BLOCKSIZE) * BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE - 1;
		if (endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("pageNum", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("list", list);

	}

}
