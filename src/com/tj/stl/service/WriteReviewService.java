package com.tj.stl.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.stl.dao.ProductDao;
import com.tj.stl.dao.ProductEnrollDao;
import com.tj.stl.dao.ReviewDao;
import com.tj.stl.dto.MemberDto;
import com.tj.stl.dto.ProductDto;
import com.tj.stl.dto.ReviewDto;

public class WriteReviewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		
		
		String[] fileNames = new String[2];
		int maxSize = 1024 * 1024 * 10;
		String path = request.getRealPath("reviewPhotoUp");
		
		MultipartRequest mRequest = null;
		
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			int i = 0;
			while (params.hasMoreElements()) {
				String param = params.nextElement();
				fileNames[i] = mRequest.getFilesystemName(param);
				i++;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream is = null;
		OutputStream os = null;
		try {
			for (int i = 0; i < fileNames.length; i++) {
				File serverFile = new File(path+"/"+fileNames[i]);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:\\java2\\source\\source\\7_JQuery\\stlProject\\WebContent\\productPhotoUp/"+fileNames[i]);
				byte[] bs = new byte[(int)serverFile.length()];
				while (true) {
					int nReadCnt = is.read(bs);
					if (nReadCnt == -1) {
						break;
					}
					os.write(bs, 0, nReadCnt);				
			}
				
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if(os != null) os.close();
				if(is != null) is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		String peName = mRequest.getParameter("peName");
		String reviewName = mRequest.getParameter("reviewName");
		String reviewContent = mRequest.getParameter("reviewContent");
		String productName = mRequest.getParameter("productName");
		ProductEnrollDao pedao = ProductEnrollDao.getInstance();
		int peCode = pedao.getPeCode(productName);
		ReviewDto review = new ReviewDto(0, peCode, 0, peName, reviewName, fileNames[1], fileNames[0], reviewContent, member.getMemberId(), member.getMemberName(), request.getRemoteAddr(), null);
		ReviewDao rdao = ReviewDao.getInstance();
		rdao.writeReview(review);
		
		
	}

}
