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

import com.tj.stl.dto.MemberDto;

public class MemberDao {
	private static MemberDao instance;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int EXSIST_ON = 1;
	public static final int EXSIST_OFF = 0;
	
	
	private MemberDao() {}
	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
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
	//중복체크 메섣
	public int memberIdcheck(String memberId) {
		int result = EXSIST_ON;
		String sql = "SELECT MEMBERID FROM MEMBER WHERE MEMBERID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return result;
			}
			result = EXSIST_OFF;
		} catch (SQLException e) {
			System.out.println("아이디 중복체크 에러:" + e.getMessage());
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
	public int memberEmailcheck(String memberEmail) {
		int result = EXSIST_ON;
		String sql = "SELECT MEMBERID FROM MEMBER WHERE MEMBEREMAIL = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return result;
			}
			result = EXSIST_OFF;
		} catch (SQLException e) {
			System.out.println("이메일 중복체크 에러:" + e.getMessage());
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
	
	//회원가입 메서드
	public int memberSignup(MemberDto member) {
		int result = FAIL;
		String sql = "INSERT INTO MEMBER ( MEMBERID, MEMBERPW, MEMBERNAME, MEMBERTEL, MEMBEREMAIL, MEMBERADDRESS," + 
				"                     MEMBERPOST, MEMBERBIRTH, GRADELEVEL)" + 
				"            VALUES(  ?, ?, ?, ?, ?, ?," + 
				"                     ?, TO_DATE(?, 'YY-MM-DD'), 1)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberTel());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setString(6, member.getMemberAddress());
			pstmt.setString(7, member.getMemberPost());
			pstmt.setDate(8, member.getMemberBirth());
			result = pstmt.executeUpdate();
			System.out.println(member + "의 정보로 회원가입을 시도하였습니다.");
			System.out.println(result == SUCCESS ? "회원가입이 성공적으로 완료되었습니다." : "회원가입 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("회원가입 정보:" + member.toString());
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
	//로그인 메서드
	public MemberDto memberSignin(String memberId, String memberPw) {
		MemberDto member = null;
		String sql = "SELECT * FROM MEMBER M, MEMBERGRADE MG WHERE TOTALSPENT BETWEEN LOWSPENT AND HISPENT AND MEMBERID = ? AND MEMBERPW= ? AND MEMBERSTATUS = 1";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println(memberId + "비밀번호" + memberPw);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String memberName = rs.getString("memberName");
				String memberTel = rs.getString("memberTel");
				String memberEmail = rs.getString("memberEmail");
				String memberAddress = rs.getString("memberAddress");
				String memberPost = rs.getString("memberPost");
				String gradeName = rs.getString("gradeName");
				int totalSpent = rs.getInt("totalSpent");
				int gradeLevel = rs.getInt("gradeLevel");
				int memberPoint = rs.getInt("memberPoint");
				Date memberRdate = rs.getDate("memberRdate");
				Date memberBirth = rs.getDate("memberBirth");
				member = new MemberDto(memberId, memberPw, memberName, memberTel, memberEmail, memberAddress, memberPost, gradeName, totalSpent, gradeLevel, memberRdate, memberBirth, memberPoint);
				member.setGradeName(gradeName);
				System.out.println(member+"세션 받았음");
			}
			System.out.println(member + "의 정보로 로그인을 시도하였습니다.");
			System.out.println(member != null ? "로그인이 수행되었습니다." : "로그인 실패");
		} catch (SQLException e) {
			System.out.println("회원가입 정보:" + member);
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
		return member;
	}
	public int memberModify(MemberDto member) {
		int result = FAIL;
		String sql = "UPDATE MEMBER SET MEMBERNAME = ?, MEMBERTEL= ?, MEMBEREMAIL = ?, MEMBERPW= ? WHERE MEMBERID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getMemberTel());
			pstmt.setString(3, member.getMemberEmail());
			pstmt.setString(4, member.getMemberPw());
			pstmt.setString(5, member.getMemberId());
			result = pstmt.executeUpdate();
			System.out.println(member + "의 정보 수정 요청이 들어왔습니다.");
			System.out.println(result == SUCCESS ? "정보수정이 성공적으로 완료되었습니다." : "정보수정 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("정보수정 정보:" + member.toString());
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
	public int memberDelete(MemberDto member) {
		int result = FAIL;
		String sql = "UPDATE MEMBER SET MEMBERSTATUS = 0 WHERE MEMBERID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			result = pstmt.executeUpdate();
			System.out.println(member + "의 정보 수정 요청이 들어왔습니다.");
			System.out.println(result == SUCCESS ? "정보수정이 성공적으로 완료되었습니다." : "정보수정 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("정보수정 정보:" + member.toString());
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
	public int memberTotUp(int price, int point, String memberId) {
		int result = FAIL;
		String sql = "UPDATE MEMBER SET TOTALSPENT = TOTALSPENT + ?, MEMBERPOINT = MEMBERPOINT + ? WHERE MEMBERID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, price);
			pstmt.setInt(2, point);
			pstmt.setString(3, memberId);
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
	public int memberModifyAddress(MemberDto member) {
		int result = FAIL;
		String sql = "UPDATE MEMBER SET MEMBERADDRESS= ? WHERE MEMBERID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberAddress());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
			System.out.println(member + "의 정보 수정 요청이 들어왔습니다.");
			System.out.println(result == SUCCESS ? "정보수정이 성공적으로 완료되었습니다." : "정보수정 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("정보수정 정보:" + member.toString());
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
	public ArrayList<MemberDto> getMemberlist(int startRow, int endRow){
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER) A)" + 
				"    WHERE RN BETWEEN ? AND ? AND MEMBERSTATUS > 0";
		
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
					String memberId = rs.getString("memberId");
					String memberPw = rs.getString("memberPw");
					String memberName = rs.getString("memberName");
					String memberTel = rs.getString("memberTel");
					String memberEmail = rs.getString("memberEmail");
					String memberAddress = rs.getString("memberAddress");
					String memberPost = rs.getString("memberPost");
					int totalSpent = rs.getInt("totalSpent");
					int gradeLevel = rs.getInt("gradeLevel");
					int memberPoint = rs.getInt("memberPoint");
					Date memberBirth = rs.getDate("memberBirth");
					Date memberRdate = rs.getDate("memberRdate");
					
					MemberDto member = new MemberDto(memberId, memberPw, memberName, memberTel, memberEmail, memberAddress, memberPost, null, totalSpent, gradeLevel, memberRdate, memberBirth, memberPoint);
					list.add(member);
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
	public int getMemberCnt() {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE MEMBERSTATUS > 0";
		
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
}
