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
import com.tj.stl.dto.QnA;

public class QnADao {
	private static QnADao instance;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int EXSIST_ON = 1;
	public static final int EXSIST_OFF = 0;
	
	private QnADao() {}
	public static QnADao getInstance() {
		if (instance == null) {
			instance = new QnADao();
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
		String sql = "SELECT COUNT(*) FROM QNABOARD";
		
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
	public ArrayList<QnA> getQnAlist(int startRow, int endRow){
		ArrayList<QnA> list = new ArrayList<QnA>();
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* " + 
				"    FROM (SELECT * FROM QNABOARD WHERE QNAANSWER > 0 ORDER BY QNAGROUP DESC, QNAANSWER) A)" + 
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
					int qnano = rs.getInt("qnano");
					String memberId = rs.getString("memberId");
					String qnaName = rs.getString("qnaName");
					String qnaContent = rs.getString("qnaContent");
					Date qnaRdate = rs.getDate("qnaRdate");
					int qnaAnswer = rs.getInt("qnaAnswer");
					int qnaGroup = rs.getInt("qnaGroup");
					QnA qna = new QnA(qnano, memberId, qnaName, qnaContent, qnaRdate, qnaAnswer, qnaGroup);
					list.add(qna);
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
	public int writeQna(QnA qna) {
		int result = FAIL;
		String sql = "INSERT INTO QNABOARD (QNANO, MEMBERID, QNANAME, QNACONTENT, QNAANSWER, QNAGROUP)" + 
				"            VALUES(QNANO_SEQ.NEXTVAL, ?, ?, ?, 1, QNANO_SEQ.NEXTVAL)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qna.getMemberId());
			pstmt.setString(2, qna.getQnaName());
			pstmt.setString(3, qna.getQnaContent());
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "문의 쓰기 완료" : "문의 실패");
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
	public int replyQna(QnA qna) {
		int result = FAIL;
		String sql = "INSERT INTO QNABOARD (QNANO, MEMBERID, QNANAME, QNACONTENT, QNAANSWER, QNAGROUP)" + 
				"            VALUES(QNANO_SEQ.NEXTVAL, ?, ?, ?, 2, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qna.getMemberId());
			pstmt.setString(2, qna.getQnaName());
			pstmt.setString(3, qna.getQnaContent());
			pstmt.setInt(4, qna.getQnaGroup());
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "문의 쓰기 완료" : "문의 실패");
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
	
	public QnA getQnaContent(int qnano) {
		QnA qna = null;
		String sql = "SELECT * FROM QNABOARD WHERE QNANO = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnano);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				String memberId = rs.getString("memberId");
				String qnaName = rs.getString("qnaName");
				Date qnaRdate = rs.getDate("qnaRdate");
				int qnaAnswer = rs.getInt("qnaAnswer");
				int qnaGroup = rs.getInt("qnaGroup");
				String qnaContent = rs.getString("qnaContent");
				qna = new QnA(qnano, memberId, qnaName, qnaContent, qnaRdate, qnaAnswer, qnaGroup);
				
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
		return qna;
	}
	
}
