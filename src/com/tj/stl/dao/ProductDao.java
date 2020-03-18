package com.tj.stl.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.stl.dto.NoticeDto;
import com.tj.stl.dto.ProductBrand;
import com.tj.stl.dto.ProductDto;
import com.tj.stl.dto.ProductEnroll;
import com.tj.stl.dto.ProductType;

public class ProductDao {
	private static ProductDao instance;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int EXSIST_ON = 1;
	public static final int EXSIST_OFF = 0;
	
	private ProductDao() {}
	public static ProductDao getInstance() {
		if (instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return conn;
	}
	//갯수세는 메서드
	public int getNoticeCnt() {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM PRODUCT";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	//물품등록
	public int insertProduct(ProductDto product) {
		int result = FAIL;
		String sql = "INSERT INTO PRODUCT (PRODUCTCODE, PRODUCTNAME, PRODUCTPRICE, PTYPECODE, PRODUCTIMG, PBRANDCODE, PRODUCTREMAIN, PRODUCTCONTENT)" + 
				"                VALUES(PCODE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProductName());
			pstmt.setInt(2, product.getProductPrice());
			pstmt.setInt(3, product.getPtypeCode());
			pstmt.setString(4, product.getProductImg());
			pstmt.setInt(5, product.getPbrandCode());
			pstmt.setInt(6, product.getProductRemain());
			pstmt.setString(7, product.getProductContent());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "물품등록 성공" : "물품등록 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	//물품리스트
	public ArrayList<ProductDto> getProductlist(int startRow, int endRow){
		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		String sql = "SELECT RN, PRODUCTCODE, PRODUCTNAME, PRODUCTPRICE, PTYPENAME, T.PTYPECODE, PRODUCTIMG, PBRANDNAME, PRODUCTREMAIN, PRODUCTCONTENT, P.PBRANDCODE" + 
				"				FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCT ORDER BY PRODUCTCODE) A) P, PRODUCT_BRAND B, PRODUCT_TYPE T " + 
				"                 WHERE RN BETWEEN ? AND ? AND B.PBRANDCODE = P.PBRANDCODE AND P.PTYPECODE = T.PTYPECODE";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					int productCode = rs.getInt("PRODUCTCODE");
					String productName = rs.getString("productName");
					String pbrandName = rs.getString("pbrandName");
					String ptypeName = rs.getString("ptypeName");
					int ptypeCode = rs.getInt("ptypeCode");
					int productPrice = rs.getInt("productPrice");
					int productRemain = rs.getInt("productRemain");
					String productContent = rs.getString("productContent");
					String productImg = rs.getString("productImg");
					int pbrandCode = rs.getInt("pbrandCode");
					ProductDto product = new ProductDto(productCode, productName, pbrandName, ptypeName, ptypeCode, productPrice, productRemain, productContent, productImg, pbrandCode);
					list.add(product);
				} while (rs.next());
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public ArrayList<ProductBrand> getProductBrandlist(){
		ArrayList<ProductBrand> list = new ArrayList<ProductBrand>();
		String sql = "SELECT * FROM PRODUCT_BRAND";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					int pBrandCode = rs.getInt("pBrandCode");
					String pBrandName = rs.getString("pBrandName");
					String pBrandCountry = rs.getString("pBrandCountry");
					ProductBrand brand = new ProductBrand(pBrandCode, pBrandName, pBrandCountry);
					list.add(brand);
				} while (rs.next());
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public ArrayList<ProductType> getProductTypelist(){
		ArrayList<ProductType> list = new ArrayList<ProductType>();
		String sql = "SELECT * FROM PRODUCT_TYPE";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					int pTypeCode = rs.getInt("pTypeCode");
					String pTypeName = rs.getString("pTypeName");
					ProductType ptype = new ProductType(pTypeCode, pTypeName);
					list.add(ptype);
				} while (rs.next());
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	//물품정보
	public ProductDto getProductContent(int productCode) {
		ProductDto product = null;
		String sql = "SELECT * FROM PRODUCT P, PRODUCT_BRAND B, PRODUCT_TYPE T" + 
				"    WHERE P.PTYPECODE = t.ptypecode AND P.PBRANDCODE = B.PBRANDCODE AND PRODUCTCODE = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String productName = rs.getString("productName");
				String pbrandName = rs.getString("pbrandName");
				String ptypeName = rs.getString("ptypeName");
				int ptypeCode = rs.getInt("ptypeCode");
				int productPrice = rs.getInt("productPrice");
				int productRemain = rs.getInt("productRemain");
				String productContent = rs.getString("productContent");
				String productImg = rs.getString("productImg");
				int pbrandCode = rs.getInt("pbrandCode");
				
				product = new ProductDto(productCode, productName, pbrandName, ptypeName, ptypeCode, productPrice, productRemain, productContent, productImg, pbrandCode);
				
			}
					
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return product;
	}
	//브랜드 등록
	public int insertBrand(String pBrandName, String pBrandCountry) {
		int result = FAIL;
		String sql = "INSERT INTO PRODUCT_BRAND VALUES(BRAND_SEQ.NEXTVAL, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pBrandName);
			pstmt.setString(2, pBrandCountry);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "브랜드 성공" : "브랜드 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public int chkBrand(String pBrandName) {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM PRODUCT_BRAND WHERE PBRANDNAME= ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pBrandName);
			result = pstmt.executeUpdate();
			System.out.println(result == EXSIST_ON ? "브랜드 있음" : "브랜드 없음");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public ArrayList<String> selectProductName(){
		ArrayList<String> list = new ArrayList<String>();
		String sql = "SELECT PRODUCTNAME FROM PRODUCT";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					String productName = rs.getString(1);
					System.out.println(productName);
					list.add(productName);
				} while (rs.next());
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public ProductDto getUserProduct(String productName){
		ProductDto product = null;
		String sql = "SELECT * FROM PRODUCT P, product_type T, product_brand B " + 
				"WHERE PRODUCTNAME = ? AND T.PTYPECODE = P.PTYPECODE AND B.PBRANDCODE = P.PBRANDCODE";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();
			if (rs.next()) {	
				int productCode =rs.getInt("productCode"); 
				productName = rs.getString("productName");
				String pbrandName = rs.getString("pbrandName");
				String ptypeName = rs.getString("ptypeName");
				int ptypeCode = rs.getInt("ptypeCode");
				int productPrice = rs.getInt("productPrice");
				int productRemain = rs.getInt("productRemain");
				String productContent = rs.getString("productContent");
				String productImg = rs.getString("productImg");
				int pbrandCode = rs.getInt("pbrandCode");
				product = new ProductDto(productCode, productName, pbrandName, ptypeName, ptypeCode, productPrice, productRemain, productContent, productImg, pbrandCode);
				
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return product;
	}
}
