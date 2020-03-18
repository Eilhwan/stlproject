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
import com.tj.stl.dto.NoticeDto;

public class NoticeDao {
	private static NoticeDao instance;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int EXSIST_ON = 1;
	public static final int EXSIST_OFF = 0;
	
	private NoticeDao() {}
	public static NoticeDao getInstance() {
		if (instance == null) {
			instance = new NoticeDao();
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
	public int writeNotice(NoticeDto notice) {
		int result = FAIL;
		String sql = "INSERT INTO NOTICE (NOTICENO, NOTICENAME, NOTICECONTENT, NOTICEEVENT, ADMINID)" + 
				"            VALUES (NOTICENO_SEQ.NEXTVAL, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getNoticeName());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setInt(3, notice.getNoticeEvent());
			pstmt.setString(4, notice.getAdminId());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "공지사항 쓰기 완료" : "공지쓰기 실패");
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
	//글보기
	public ArrayList<NoticeDto> getNoticelist(int startRow, int endRow){
		ArrayList<NoticeDto> list = new ArrayList<NoticeDto>();
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM NOTICE ORDER BY NOTICEEVENT DESC, NOTICENO DESC) A)" + 
				"    WHERE RN BETWEEN ? AND ? AND NOTICEEVENT > -1";
		
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
					int noticeNo = rs.getInt("noticeNo");
					String noticeName = rs.getString("noticeName");
					String noticeContent = rs.getString("noticeContent");
					Date noticeRdate = rs.getDate("noticeRdate");
					byte noticevent = rs.getByte("noticeevent");
					String adminId = rs.getString("adminId");
					
					NoticeDto notice = new NoticeDto(noticeNo, noticeName, noticeContent, noticeRdate, noticevent, adminId);
				
					list.add(notice);
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
	public ArrayList<NoticeDto> getEventlist(int startRow, int endRow){
		ArrayList<NoticeDto> list = new ArrayList<NoticeDto>();
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM NOTICE ORDER BY NOTICEEVENT DESC, NOTICENO DESC) A)" + 
				"    WHERE RN BETWEEN ? AND ? AND NOTICEEVENT > 0";
		
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
					int noticeNo = rs.getInt("noticeNo");
					String noticeName = rs.getString("noticeName");
					String noticeContent = rs.getString("noticeContent");
					Date noticeRdate = rs.getDate("noticeRdate");
					byte noticevent = rs.getByte("noticeevent");
					String adminId = rs.getString("adminId");
					
					NoticeDto notice = new NoticeDto(noticeNo, noticeName, noticeContent, noticeRdate, noticevent, adminId);
					
					list.add(notice);
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
	public int getNoticeCnt() {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM NOTICE";
		
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
	public NoticeDto getNoticeContent(int noticeNo) {
		NoticeDto notice = null;
		String sql = "SELECT * FROM NOTICE WHERE NOTICENO = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				String noticeName = rs.getString("noticeName");
				String noticeContent = rs.getString("noticeContent");
				Date noticeRdate = rs.getDate("noticeRdate");
				byte noticeEvent = rs.getByte("noticeEvent");
				String adminId = rs.getString("adminId");
				notice = new NoticeDto(noticeNo, noticeName, noticeContent, noticeRdate, noticeEvent, adminId);
				
			}
					
			
			
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
		return notice;
	}
	public NoticeDto getMainNotice(int row) {
		NoticeDto notice = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, N.* FROM (SELECT * FROM NOTICE where noticeevent != 1 ORDER BY NOTICENO DESC) N )  WHERE rn = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, row);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int noticeNo = rs.getInt("noticeNo");
				String noticeName = rs.getString("noticeName");
				String noticeContent = rs.getString("noticeContent");
				Date noticeRdate = rs.getDate("noticeRdate");
				byte noticeEvent = rs.getByte("noticeEvent");
				String adminId = rs.getString("adminId");
				notice = new NoticeDto(noticeNo, noticeName, noticeContent, noticeRdate, noticeEvent, adminId);
				
			}
					
			
			
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
		return notice;
	}
	public NoticeDto getMainEvent(int row) {
		NoticeDto notice = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, N.* FROM (SELECT * FROM NOTICE WHERE NOTICEEVENT = 1 ORDER BY NOTICENO DESC) N )  WHERE rn = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, row);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int noticeNo = rs.getInt("noticeNo");
				String noticeName = rs.getString("noticeName");
				String noticeContent = rs.getString("noticeContent");
				Date noticeRdate = rs.getDate("noticeRdate");
				byte noticeEvent = rs.getByte("noticeEvent");
				String adminId = rs.getString("adminId");
				notice = new NoticeDto(noticeNo, noticeName, noticeContent, noticeRdate, noticeEvent, adminId);
				
			}
			
			
			
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
		return notice;
	}
}
