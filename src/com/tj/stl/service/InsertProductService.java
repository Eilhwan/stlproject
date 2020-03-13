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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.stl.dao.ProductDao;
import com.tj.stl.dto.ProductDto;

public class InsertProductService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String fileName = "";
		int maxSize = 1024 * 1024 * 10;
		String path = request.getRealPath("productPhotoUp");
		
		MultipartRequest mRequest = null;
		
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			
			while (params.hasMoreElements()) {
				String param = params.nextElement();
				fileName = mRequest.getFilesystemName(param);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream is = null;
		OutputStream os = null;
		try {
			File serverFile = new File(path+"/"+fileName);
			is = new FileInputStream(serverFile);
			os = new FileOutputStream("D:\\java2\\source\\source\\7_JQuery\\stlProject\\WebContent\\productPhotoUp/"+fileName);
			byte[] bs = new byte[(int)serverFile.length()];
			while (true) {
				int nReadCnt = is.read(bs);
				if (nReadCnt == -1) {
					break;
				}
				os.write(bs, 0, nReadCnt);
				
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
		String productName = mRequest.getParameter("productName");
		String productContent = mRequest.getParameter("productContent");
		int productPrice = Integer.parseInt(mRequest.getParameter("productPrice"));
		int ptypeCode = Integer.parseInt(mRequest.getParameter("ptypeCode"));
		int pbrandCode = Integer.parseInt(mRequest.getParameter("pbrandCode"));
		int productRemain = Integer.parseInt(mRequest.getParameter("productRemain"));
		
		
		ProductDto product = new ProductDto(0, productName, null, null, ptypeCode, productPrice, productRemain, productContent, fileName, pbrandCode);
		ProductDao dao = ProductDao.getInstance();
		System.out.println(product);
		dao.insertProduct(product);
	}

}
