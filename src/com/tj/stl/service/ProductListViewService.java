package com.tj.stl.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.dao.NoticeDao;
import com.tj.stl.dao.ProductDao;
import com.tj.stl.dto.NoticeDto;
import com.tj.stl.dto.ProductDto;

public class ProductListViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ProductDao dao = ProductDao.getInstance();
		
		
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
		
		
		ArrayList<ProductDto> list = dao.getProductlist(startRow, endRow);
		
		int pageCnt = (int) Math.ceil(((double)dao.getProductCnt()/PAGESIZE));
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
