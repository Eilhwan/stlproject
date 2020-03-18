package com.tj.stl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.stl.dto.CartDto;
import com.tj.stl.dto.MemberDto;
import com.tj.stl.dto.ProductDto;
import com.tj.stl.dto.ProductEnroll;

public class CartDao {
	private static CartDao instance;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int EXSIST_ON = 1;
	public static final int EXSIST_OFF = 0;
	
	private CartDao() {}
	public static CartDao getInstance() {
		if (instance == null) {
			instance = new CartDao();
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
	public int getCartCnt() {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM CART";
		
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
	public CartDto getCart(int cartNo) {
		CartDto cart = null;
		String sql = "SELECT * FROM CART C, PRODUCTENROLL E, PRODUCT P WHERE C.PRODUCTCODE = P.PRODUCTCODE AND E.PRODUCTCODE = P.PRODUCTCODE AND CARTNO = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String memberId = rs.getString("memberId");
				String productName = rs.getString("productName");
				int productEA = rs.getInt("productEA");
				int productCode = rs.getInt("productCode");
				int cartPrice = rs.getInt("cartPrice");
				int peDiscount = rs.getInt("peDiscount");
				int pePrice = rs.getInt("pePrice");
				cart = new CartDto(cartNo, memberId, productEA, productCode, cartPrice, productName, peDiscount, pePrice);
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
		
		return cart;
	}
	public int getCartCnt(String memberId) {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM CART WHERE MEMBERID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
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
	public int insertCart(ProductEnroll product, MemberDto member, int ea) {
		int result = FAIL;
		String sql = "INSERT INTO CART (CARTNO, MEMBERID, PRODUCTEA, PRODUCTCODE, CARTPRICE)" + 
				"     VALUES(CARTNO_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setInt(2, ea);
			pstmt.setInt(3, product.getProductCode());
			pstmt.setInt(4, product.getPePrice() * (1 - (product.getPeDiscount() / 100) ) * ea);
			result = pstmt.executeUpdate();
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
	public ArrayList<CartDto> getCart(String memberId){
		ArrayList<CartDto> list = new ArrayList<CartDto>();
		String sql = "SELECT * FROM CART C, PRODUCTENROLL E, PRODUCT P WHERE C.PRODUCTCODE = P.PRODUCTCODE AND E.PRODUCTCODE = P.PRODUCTCODE AND MEMBERID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					int cartNo = rs.getInt("cartNo");
					int productEA = rs.getInt("productEA");
					int productCode = rs.getInt("productCode");
					int cartPrice = rs.getInt("cartPrice");
					int peDiscount = rs.getInt("peDiscount");
					int pePrice = rs.getInt("pePrice");
					String productName = rs.getString("productName");
					CartDto cart = new CartDto(cartNo, memberId, productEA, productCode, cartPrice, productName, peDiscount, pePrice);
					

					list.add(cart);
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
	public int deleteCart(int cartNo) {
		int result = FAIL;
		String sql = "DELETE FROM CART WHERE CARTNO = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNo);
			result = pstmt.executeUpdate();
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
	public int deleteCart(String memberId) {
		int result = FAIL;
		String sql = "DELETE FROM CART WHERE memberId = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
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
	public int updateCart(CartDto cart) {
		int result = FAIL;
		String sql = "UPDATE CART SET PRODUCTEA = ?, CARTPRICE = ? WHERE CARTNO = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getProductEA());
			pstmt.setInt(2, cart.getPePrice() * (1 - (cart.getPeDiscount() / 100) ) * cart.getProductEA());
			pstmt.setInt(3, cart.getCartNo());
			result = pstmt.executeUpdate();
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
}
