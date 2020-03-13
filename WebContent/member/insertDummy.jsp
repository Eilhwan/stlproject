<%@page import="java.sql.Date"%>
<%@page import="com.tj.stl.dto.MemberDto"%>
<%@page import="com.tj.stl.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		MemberDao dao = MemberDao.getInstance();
		
		for(int i=0; i< 100; i++){
			MemberDto member = new MemberDto();
			member.setMemberId("ID" + i);
			member.setMemberPw("ID" + i);
			member.setMemberName("네임" + i);
			member.setMemberTel("010" + i);
			member.setMemberEmail("email" + i);
			member.setMemberAddress("addr" + i);
			member.setMemberPost("110" + i);
			member.setMemberBirth(Date.valueOf("2010-01-01"));
			dao.memberSignup(member);
		}
	%>
</body>
</html>