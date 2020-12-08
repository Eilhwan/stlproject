package com.tj.stl.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.stl.dao.ProductEnrollDao;
import com.tj.stl.dto.ProductEnroll;

public class ProductEnrollService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String[] fileNames = new String[2];
		int maxSize = 1024 * 1024 * 10;
		String path = request.getRealPath("pePhotoUp");
		
		MultipartRequest mRequest = null;
		
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			int i = 0;
			while (params.hasMoreElements()) {
				String param = (String) params.nextElement();
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
			for (int j = 0; j < fileNames.length; j++) {
				File serverFile = new File(path+"/"+fileNames[j]);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:\\java2\\source\\source\\7_JQuery\\stlProject\\WebContent\\pePhotoUp/"+fileNames[j]);
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
		int productCode = Integer.parseInt(mRequest.getParameter("productCode"));
		int pTypeCode = Integer.parseInt(mRequest.getParameter("pTypeCode"));
		String peName = mRequest.getParameter("peName");
		String peContent = mRequest.getParameter("peName");
		String peImg2 = fileNames[0];
		String peImg1 = fileNames[1];
		int pePrice = Integer.parseInt(mRequest.getParameter("pePrice"));
		int pePoint = Integer.parseInt(mRequest.getParameter("pePoint"));
		int peDiscount = Integer.parseInt(mRequest.getParameter("peDiscount") == "" ? "0" : mRequest.getParameter("peDiscount"));
		
		ProductEnrollDao dao = ProductEnrollDao.getInstance();
		ProductEnroll product = new ProductEnroll(0, productCode, pTypeCode, peName, peContent, peImg1, peImg2, null, pePrice, 0, pePoint, 0, peDiscount, 1);
		dao.productEnroll(product);
	 
	}

}
