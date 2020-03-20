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

import com.tj.stl.dto.OrdersDto;

public class OrdersDao {
	private static OrdersDao instance;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int EXSIST_ON = 1;
	public static final int EXSIST_OFF = 0;
	
	private OrdersDao() {}
	public static OrdersDao getInstance() {
		if (instance == null) {
			instance = new OrdersDao();
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
		String sql = "SELECT COUNT(*) FROM ORDERS";
		
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
	public int getOrderCnt(String memberId) {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM ORDERS WHERE MEMBERID = ?";
		
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
	public OrdersDto getOrder(String memberId) {
		OrdersDto order = null;
		String sql = "SELECT * FROM MEMBER M, ORDERS O WHERE O.MEMBERID = M.MEMBERID AND M.MEMBERID = ? AND ORDERSTATUS = 1";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int orderNo = rs.getInt("orderNo");
				String orderDate = rs.getString("orderDate");
				String orderAddress = rs.getString("orderAddress");
				String orderTel = rs.getString("orderTel");
				int orderTotal = rs.getInt("orderTotal");
				String memberName = rs.getString("memberName");
				int orderStatus = rs.getInt("orderStatus");
				order = new OrdersDto(orderNo, orderDate, orderAddress, orderTel, orderTotal, memberId, memberName, orderStatus);
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
		
		return order;
	}
	public ArrayList<OrdersDto> getOrderList(String memberId) {
		ArrayList<OrdersDto> list = new ArrayList<OrdersDto>();
		String sql = "SELECT * FROM MEMBER M, ORDERS O WHERE O.MEMBERID = M.MEMBERID AND M.MEMBERID = ? AND ORDERSTATUS > 1";
		
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
					int orderNo = rs.getInt("orderNo");
					String orderDate = rs.getString("orderDate");
					String orderAddress = rs.getString("orderAddress");
					String orderTel = rs.getString("orderTel");
					int orderTotal = rs.getInt("orderTotal");
					String memberName = rs.getString("memberName");
					int orderStatus = rs.getInt("orderStatus");
					OrdersDto order = new OrdersDto(orderNo, orderDate, orderAddress, orderTel, orderTotal, memberId, memberName, orderStatus);
					list.add(order);					
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
	//주문 생성
	public int orderProduct(String orderAddress, String orderTel, int orderTotal, String memberId) {
		int result = FAIL;
		String sql = "INSERT INTO ORDERS (ORDERNO, ORDERADDRESS, ORDERTEL, ORDERTOTAL, MEMBERID)" + 
				"            VALUES (ORDERNO_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderAddress);
			pstmt.setString(2, orderTel);
			pstmt.setInt(3, orderTotal);
			pstmt.setString(4, memberId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "주문성공" : "주문실패");
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
	public int orderStatusChange(int orderStatus, int orderNo) {
		int result = FAIL;
		String sql = "UPDATE ORDERS SET ORDERSTATUS = ? WHERE ORDERNO = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderStatus);
			pstmt.setInt(2, orderNo);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "주문성공" : "주문실패");
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
