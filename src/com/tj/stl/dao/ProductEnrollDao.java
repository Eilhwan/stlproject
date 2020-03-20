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

import com.tj.stl.dto.ProductDto;
import com.tj.stl.dto.ProductEnroll;

public class ProductEnrollDao {
	private static ProductEnrollDao instance;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int EXSIST_ON = 1;
	public static final int EXSIST_OFF = 0;
	
	private ProductEnrollDao() {}
	public static ProductEnrollDao getInstance() {
		if (instance == null) {
			instance = new ProductEnrollDao();
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
	public int getUserProductCnt() {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM PRODUCTENROLL WHERE PESTATUS > 0";
		
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
	public int getUserProductCnt(String keyWord) {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM PRODUCTENROLL WHERE PESTATUS > 0 AND PENAME LIKE '%'||?||'%'";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyWord);
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
	public int productEnroll(ProductEnroll product) {
		int result = FAIL;
		String sql = "INSERT INTO PRODUCTENROLL (PECODE, PRODUCTCODE, PENAME, PECONTENT, PEIMG1, PEIMG2, PEPRICE, PEPOINT, PEDISCOUNT, PESTARS)" + 
				"                VALUES    (PECODE_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getProductCode());
			pstmt.setString(2, product.getPeName());
			pstmt.setString(3, product.getPeContent());
			pstmt.setString(4, product.getPeImg1());
			pstmt.setString(5, product.getPeImg2());
			pstmt.setInt(6, product.getPePrice());
			pstmt.setInt(7, product.getPePoint());
			pstmt.setInt(8, product.getPeDiscount());
			pstmt.setInt(9, product.getPeStars());
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
	public int upSellCnt(int sellCnt, int productCode) {
		int result = 0;
		String sql = "UPDATE PRODUCTENROLL SET sellCnt = sellCnt + ? WHERE PRODUCTCODE = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sellCnt);
			pstmt.setInt(2, productCode);
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
	//물품리스트
		public ArrayList<ProductEnroll> getUserProductlist(int startRow, int endRow, int pTypeCode){
			ArrayList<ProductEnroll> list = new ArrayList<ProductEnroll>();
			String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCTENROLL ORDER BY PERDATE) A) E, PRODUCT P "
					+ "WHERE RN BETWEEN ? AND ? AND E.PRODUCTCODE = P.PRODUCTCODE AND P.PTYPECODE LIKE '%'|| ? ||'%'";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
			
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				pstmt.setInt(3, pTypeCode);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						int peCode = rs.getInt("pecode");
						int productCode = rs.getInt("productCode");
						String peName = rs.getString("peName");
						String peContent = rs.getString("peContent");
						String peImg1 = rs.getString("peImg1");
						String peImg2 = rs.getString("peImg2");
						Date peRdate = rs.getDate("peRdate");
						int pePrice = rs.getInt("pePrice");
						int sellCnt = rs.getInt("sellCnt");
						int pePoint = rs.getInt("pePoint");
						int peStars = rs.getInt("peStars");
						int peDiscount = rs.getInt("peDiscount");
						int peStatus = rs.getInt("peStatus");
						ProductEnroll product = new ProductEnroll(peCode, productCode, pTypeCode, peName, peContent, peImg1, peImg2, peRdate, pePrice, sellCnt, pePoint, peStars, peDiscount, peStatus);
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
		public ArrayList<ProductEnroll> searchPeList(int startRow, int endRow, String peName){
			ArrayList<ProductEnroll> list = new ArrayList<ProductEnroll>();
			String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCTENROLL ORDER BY PERDATE) A) E, PRODUCT P "
					+ "WHERE RN BETWEEN ? AND ? AND E.PRODUCTCODE = P.PRODUCTCODE AND E.PENAME LIKE '%'|| ? ||'%'";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				pstmt.setString(3, peName);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
					
						int peCode = rs.getInt("pecode");
						int productCode = rs.getInt("productCode");
						int pTypeCode = rs.getInt("pTypeCode");
						String peContent = rs.getString("peContent");
						peName = rs.getString("peName");
						String peImg1 = rs.getString("peImg1");
						String peImg2 = rs.getString("peImg2");
						Date peRdate = rs.getDate("peRdate");
						int pePrice = rs.getInt("pePrice");
						int sellCnt = rs.getInt("sellCnt");
						int pePoint = rs.getInt("pePoint");
						int peStars = rs.getInt("peStars");
						int peDiscount = rs.getInt("peDiscount");
						int peStatus = rs.getInt("peStatus");
						ProductEnroll product = new ProductEnroll(peCode, productCode, pTypeCode, peName, peContent, peImg1, peImg2, peRdate, pePrice, sellCnt, pePoint, peStars, peDiscount, peStatus);
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
	public ArrayList<ProductEnroll> getUserProductlist(int startRow, int endRow){
			ArrayList<ProductEnroll> list = new ArrayList<ProductEnroll>();
			String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCTENROLL ORDER BY PERDATE) A) E, PRODUCT P "
					+ "WHERE RN BETWEEN ? AND ? AND E.PRODUCTCODE = P.PRODUCTCODE";
			
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
					
						int peCode = rs.getInt("peCode");
						int productCode = rs.getInt("productCode");
						String peName = rs.getString("peName");
						String peContent = rs.getString("peContent");
						String peImg1 = rs.getString("peImg1");
						String peImg2 = rs.getString("peImg2");
						Date peRdate = rs.getDate("peRdate");
						int pePrice = rs.getInt("pePrice");
						int pTypeCode = rs.getInt("pTypeCode");
						int sellCnt = rs.getInt("sellCnt");
						int pePoint = rs.getInt("pePoint");
						int peStars = rs.getInt("peStars");
						int peDiscount = rs.getInt("peDiscount");
						int peStatus = rs.getInt("peStatus");
						ProductEnroll product = new ProductEnroll(peCode, productCode, pTypeCode, peName, peContent, peImg1, peImg2, peRdate, pePrice, sellCnt, pePoint, peStars, peDiscount, peStatus);
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
	public ProductEnroll getUserProduct(int peCode){
		ProductEnroll product = null;
		String sql = "SELECT * FROM PRODUCTENROLL E, PRODUCT P WHERE PECODE = ?  AND P.PRODUCTCODE = E.PRODUCTCODE";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				peCode = rs.getInt("peCode");
				String peName = rs.getString("peName");
				String peContent = rs.getString("peContent");
				String peImg1 = rs.getString("peImg1");
				String peImg2 = rs.getString("peImg2");
				Date peRdate = rs.getDate("peRdate");
				int productCode = rs.getInt("productCode");
				int pTypeCode = rs.getInt("pTypeCode");
				int pePrice = rs.getInt("pePrice");
				int sellCnt = rs.getInt("sellCnt");
				int pePoint = rs.getInt("pePoint");
				int peStars = rs.getInt("peStars");
				int peDiscount = rs.getInt("peDiscount");
				int peStatus = rs.getInt("peStatus");
				product = new ProductEnroll(peCode, productCode, pTypeCode, peName, peContent, peImg1, peImg2, peRdate, pePrice, sellCnt, pePoint, peStars, peDiscount, peStatus);
				
					
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
	public int getProductPoint(int productCode){
		int pePoint = 0;
		String sql = "SELECT * FROM PRODUCTENROLL E, PRODUCT P WHERE E.PRODUCTCODE = ?  AND P.PRODUCTCODE = E.PRODUCTCODE";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				 pePoint = rs.getInt("pePoint");
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
		
		return pePoint;
	}
	public int getPeCode(String productName){
		int peCode = 0;
		String sql = "SELECT * FROM PRODUCTENROLL E, PRODUCT P WHERE E.productName = ?  AND P.PRODUCTCODE = E.PRODUCTCODE";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				peCode = rs.getInt("peCode");
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
		
		return peCode;
	}
		
}
