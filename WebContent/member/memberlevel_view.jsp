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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	
	<div class="container">
		<h2>회원등급확인</h2>
		<div>

			<p>
				<b>${member.memberName }</b>님의 포인트 관련 정보입니다.
			</p>
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td>${member.memberId }</td>
				</tr>
				<tr>
					<td colspan="2">${member.memberName}님의 총 사용금액은 ${member.totalSpent }원 이며 등급은 ${member.gradeName }등급 입니다.</td>
					
				</tr>
				<tr>
					<td colspan="2" class="td text-center">
						<button id="submit" class="btn-dark">확인</button>
						<button onclick='history.back()'>취소</button>

					</td>
				</tr>
			</table>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>