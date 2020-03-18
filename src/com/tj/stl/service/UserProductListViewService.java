package com.tj.stl.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.ProductEnrollDao;
import com.tj.stl.dto.ProductEnroll;

public class UserProductListViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ProductEnrollDao pedao = ProductEnrollDao.getInstance();
		
		String pageNum = request.getParameter("pageNum");
		String ptypecode = request.getParameter("pTypeCode");
		int pTypeCode = 0;
		if (ptypecode != null) {
			pTypeCode = Integer.parseInt(ptypecode);			
		}
		
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int BLOCKSIZE = 5, PAGESIZE = 9;
		int startRow = (currentPage - 1) * PAGESIZE + 1;
		int endRow = startRow + PAGESIZE - 1;
		System.out.println(startRow);
		System.out.println(endRow);
		ArrayList<ProductEnroll> list = new ArrayList<ProductEnroll>();
		if (request.getParameter("pTypeCode") == null) {			
			list = pedao.getUserProductlist(startRow, endRow);
		}else {			
			list = pedao.getUserProductlist(startRow, endRow, pTypeCode);
		}

		int pageCnt = (int) Math.ceil(((double)pedao.getUserProductCnt()/PAGESIZE));
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
