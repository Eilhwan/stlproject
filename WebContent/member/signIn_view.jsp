<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<c:if test="${not empty resultmsg }">
	<script>
		alert('${resultmsg }');
	</script>
</c:if>
<c:if test="${not empty errormsg }">
	<script>
		alert('${errormsg }');
		history.back();
	</script>
</c:if>
<c:if test="${not empty member || not empty admin}">
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
			if ($('#memberId').val() <= 4) {
				alert('유효한 아이디를 입력하세요.');
				return false;
			}else if ($('#memberPw').val <= 4) {
				alert('유효한 비밀번호를 입력해주세요.');
				return false;
			}
		});
	});
</script>
</head>
<body class="text-center">
	<form class="form-signin" action="${conPath }/signIn.do" method="post">
		<div class="form-group justify-content-center">		 	
			<a href="${conPath }/main.do"><img alt="사진" src="${conPath }/img/Title.png" width="100px"></a>
		</div>
		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		<label for="memberId" class="sr-only">ID</label> 
			<input type="text" id="memberId" class="form-control" name="memberId" placeholder="ID" required autofocus> 
		<label for="memberPw" class="sr-only">Password</label> 
			<input type="password" id="memberPw" class="form-control" name="memberPw" placeholder="Password" required>
		<div class="checkbox mb-3 text-right">
			<label> <input type="checkbox" value="remember-me">
				Remember me
			</label>
		</div>
		<button class="btn btn-lg btn-dark btn-block" type="submit">Sign in</button>
		<button class="btn btn-lg btn-dark btn-block" type="button" onclick="location.href='${conPath}/signupView.do'">Sign up</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2017-2019</p>
	</form>
	
</body>
</html>