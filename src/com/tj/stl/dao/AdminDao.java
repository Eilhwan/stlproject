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

import com.tj.stl.dto.AdminDto;
import com.tj.stl.dto.MemberDto;

public class AdminDao {
	private static AdminDao instance;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int EXSIST_ON = 1;
	public static final int EXSIST_OFF = 0;
	
	private AdminDao() {}
	public static AdminDao getInstance() {
		if (instance == null) {
			instance = new AdminDao();
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
	//admin 로그인
	public AdminDto adminSignin(String adminId, String adminPw) {
		AdminDto admin = null;
		String sql = "SELECT * FROM ADMIN WHERE ADMINID = ? AND ADMINPW = ? AND ADMINPW != '-1'";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println(adminId + "비밀번호" + adminPw);
			pstmt.setString(1, adminId);
			pstmt.setString(2, adminPw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				adminId = rs.getString(1);
				adminPw = rs.getString(2);
				admin = new AdminDto(adminId, adminPw);
			}
			System.out.println(admin + "의 정보로 로그인을 시도하였습니다.");
			System.out.println(admin != null ? "로그인이 수행되었습니다." : "로그인 실패");
		} catch (SQLException e) {
			System.out.println("회원가입 정보:" + admin);
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
		return admin;
	}
	public ArrayList<AdminDto> getAdminlist(int startRow, int endRow){
		ArrayList<AdminDto> list = new ArrayList<AdminDto>();
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM ADMIN) A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		
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
					String adminId = rs.getString("adminId");
					String adminPw = rs.getString("adminPw");
					
					AdminDto admin = new AdminDto(adminId, adminPw);
					list.add(admin);
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
	public int getAdminCnt() {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM ADMIN";
		
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
	public int delAdmin(String adminId) {
		int result = FAIL;
		String sql = "Update ADMIN SET ADMINPW = -1 WHERE ADMINID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminId);
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
	public int signUpAdmin(String adminId, String adminPw) {
		int result = FAIL;
		String sql = "INSERT INTO ADMIN VALUES(?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminId);
			pstmt.setString(2, adminPw);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "성공" : "실패");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
