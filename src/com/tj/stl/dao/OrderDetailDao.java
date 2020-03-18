package com.tj.stl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDetailDao {
	private static OrderDetailDao instance;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int EXSIST_ON = 1;
	public static final int EXSIST_OFF = 0;
	
	private OrderDetailDao() {}
	public static OrderDetailDao getInstance() {
		if (instance == null) {
			instance = new OrderDetailDao();
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
	public int getOrderCnt() {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM ORDERDETAIL";
		
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
	//상세주문
	public int orderProduct(int productCode, int orderNo, int odPrice, int odCnt) {
		int result = FAIL;
		String sql = "INSERT INTO ORDERDETAIL (ODNO, PRODUCTCODE, ORDERNO, ODPRICE, ODCNT)" + 
				"                VALUES  (ODNO_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productCode);
			pstmt.setInt(2, orderNo);
			pstmt.setInt(3, odPrice);
			pstmt.setInt(4, odCnt);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "주문추가" : "주문실패");
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
