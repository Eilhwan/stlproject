<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<c:if test="${not empty logoutmsg }">
	<script>
		alert('${logoutmsg }');
	</script>
</c:if>
<c:if test="${not empty logouterror }">
	<script>
		alert('${logouterror }');
		history.back();
	</script>
</c:if>
<c:if test="${not empty admin }">
	<script>
		alert('비정상적인 접근입니다.');
		history.back();
	</script>
</c:if>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<link rel="stylesheet" href="${conPath }/css/signin.css" >
<script type='text/javascript' src='//code.jquery.com/jquery-1.8.3.js'></script>
<script>
	$(function(){
		$('form').submit(function(){
			if ($('#adminId').val() <= 4) {
				alert('유효한 아이디를 입력하세요.');
				return false;
			}else if ($('#adminPw').val < 3) {
				alert('유효한 비밀번호를 입력해주세요.');
				return false;
			}
		});
	});
</script>
</head>
<body class="text-center">
	<form class="form-signin" action="${conPath }/adminSignIn.do" method="post">
		<div class="form-group justify-content-center">		 	
			<h3><a href="${conPath }/main.do"><b>관리자 로그인</b></a></h3>
		</div>
		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		<label for="memberId" class="sr-only">ID</label> 
			<input type="text" id="adminId" class="form-control" name="adminId" placeholder="ID" required autofocus> 
		<label for="memberPw" class="sr-only">Password</label> 
			<input type="password" id="adminPw" class="form-control" name="adminPw" placeholder="Password" required>
		<div class="checkbox mb-3 text-right">
			<label> <input type="checkbox" value="remember-me">
				Remember me
			</label>
		</div>
		<button class="btn btn-lg btn-dark btn-block" type="submit">Sign in</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2017-2019</p>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>