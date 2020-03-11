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
<style>
	.form-control{
		width: 80%;
	}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script>
	$(function(){
		$('form').submit(function(){
			if (${nowPw} != ${member.memberPw}) {
				alert('비밀번호를 확인해주세요.');
				return false;
			}
		});
	});
	
</script>
</head>
<body>
	<form action="${conPath }/memberModify.do" method="post">
	<div class="container my-5">
		<div class="form-group justify-content-center">		 	
			<h2>회원정보수정</h2>
		</div>
		<table class="table table-bordered">
			<tr>
				<td>아이디</td>
				<td><input type="text" class="form-control" value="${member.memberId }" id="memberId" readonly="readonly" name="memberId"></td>				
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" class="form-control" value="${member.memberName }" id="memberName" name="memberName"></td>							
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" class="form-control" value="${member.memberTel }" id="memberTel" name="memberTel"></td>							
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" class="form-control" value="${member.memberEmail }" id="memberEmail" name="memberEmail"></td>							
			</tr>
			<tr>
				<td>비밀번호변경</td>
				<td>
					<div><p>현재 비밀번호 <input type="password" class="form-control" id="nowPw" name="nowPw"></p></div>
					<div>신규 비밀번호<input type="password" class="form-control" id="memberPw" name="memberPw"></div>
					<div>비밀번호 다시 입력<input type="password" class="form-control" id="pwChk" name="pwChk"></div>
				</td>							
			</tr>
			<tr>
				<td colspan="2" class="td text-center">
					<input type="submit" value="확인" class="btn-dark">
					<input type="button" value="취소" onclick='history.back()'> 
					
				</td>
			</tr>
		</table>
	  </div>
	</form>
</body>
</html>