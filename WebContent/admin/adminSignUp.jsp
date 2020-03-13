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
	<c:if test="${not empty resultmsg }">
		<script>
			alert('${resultmsg}');
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div class="container my-5" id="main">
		<h2><b>관리자등록</b></h2>
		<div>
			<p>
				<b>관리자 등록을 위해 새롭게 등록할 관리자 아이디와 비밀번호를 설정하세요.</b>
			</p>
			<form action="${conPath }/adminSignUp.do">
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="adminId" id="adminId"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="adminPw" id="adminPw"></td>
				</tr>
				<tr>
					<td colspan="2" class="td text-center">
						<input type="submit" id="submit" class="btn-dark" value="확인">
						<button onclick='history.back()'>취소</button>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>