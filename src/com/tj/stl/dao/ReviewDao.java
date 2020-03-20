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
import com.tj.stl.dto.ReviewDto;

public class ReviewDao {
	private static ReviewDao instance;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int EXSIST_ON = 1;
	public static final int EXSIST_OFF = 0;
	
	
	private ReviewDao() {}
	public static ReviewDao getInstance() {
		if (instance == null) {
			instance = new ReviewDao();
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
	public int getReviewCnt() {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM REVIEWBOARD ";
		
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
	public ReviewDto getReviewContent(int reno) {
		ReviewDto review = null;
		String sql = "SELECT * FROM REVIEWBOARD R, PRODUCTENROLL PE, MEMBER M "
				+ "	WHERE ReviewNo = ? AND PE.PECODE = R.PECODE AND M.MEMBERID = R.MEMBERID";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int reviewNo = rs.getInt("reviewNo");
				int peCode = rs.getInt("peCode");
				int reviewStatus = rs.getInt("reviewStatus");
				String peName = rs.getString("peName");
				String reviewName = rs.getString("reviewName");
				String reviewImg1 = rs.getString("reviewImg1");
				String reviewImg2 = rs.getString("reviewImg2");
				String reviewContent = rs.getString("reviewContent");
				String memberId = rs.getString("memberId");
				String memberName = rs.getString("memberName");
				String userIp = rs.getString("userIp");
				Date reviewRdate = rs.getDate("reviewRdate");

				review = new ReviewDto(reviewNo, peCode, reviewStatus, peName, reviewName, reviewImg1, reviewImg2, reviewContent, memberId, memberName, userIp, reviewRdate);
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
		
		return review;
	}
	public ArrayList<ReviewDto> getReviewlist(int startRow, int endRow){
		ArrayList<ReviewDto> list = new ArrayList<ReviewDto>();
		String sql = "SELECT * " + 
				"    FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM REVIEWBOARD ORDER BY REVIEWNO)A) R, MEMBER M, PRODUCTENROLL PE " + 
				"    WHERE M.MEMBERID = R.MEMBERID AND R.PECODE = PE.PECODE AND RN BETWEEN ? AND ?";
		
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
					int reviewNo = rs.getInt("reviewNo");
					int peCode = rs.getInt("peCode");
					int reviewStatus = rs.getInt("reviewStatus");
					String peName = rs.getString("peName");
					String reviewName = rs.getString("reviewName");
					String reviewImg1 = rs.getString("reviewImg1");
					String reviewImg2 = rs.getString("reviewImg2");
					String reviewContent = rs.getString("reviewContent");
					String memberId = rs.getString("memberId");
					String memberName = rs.getString("memberName");
					String userIp = rs.getString("userIp");
					Date reviewRdate = rs.getDate("reviewRdate");
					ReviewDto review = new ReviewDto(reviewNo, peCode, reviewStatus, peName, reviewName, reviewImg1, reviewImg2, reviewContent, memberId, memberName, userIp, reviewRdate);
					list.add(review);
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
	public int writeReview(ReviewDto review) {
		int result = FAIL;
		String sql = "INSERT INTO REVIEWBOARD (REVIEWNO, REVIEWNAME, REVIEWIMG1, REVIEWIMG2, REVIEWCONTENT, MEMBERID, USERIP, PECODE)" + 
				"     VALUES (RENO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
		
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getReviewName());
			pstmt.setString(2, review.getReviewImg1());
			pstmt.setString(3, review.getReviewImg2());
			pstmt.setString(4, review.getReviewContent());
			pstmt.setString(5, review.getMemberId());
			pstmt.setString(6, review.getUserIp());
			pstmt.setInt(7, review.getPeCode());
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
