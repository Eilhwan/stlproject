<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script type='text/javascript' src='//code.jquery.com/jquery-1.8.3.js'></script>
<script>
	$(function() {
		$('#submit').click(function() {
			var memberPw = $('#memberPw').val();
			var pwChk = $('#pwChk').val();
			if (memberPw == "") {
				alert('비밀번호를 입력하세요');
				$('#memberPw').val("");
				$('#memberPw').focus();
				return false;
			}else if (pwChk == "") {
				alert('비밀번호 확인을 입력하세요');
				$('#memberPw').val("");
				$('#memberPw').focus();
				return false;
			}else if (memberPw != pwChk) {
				alert('비밀번호가 일치하지 않습니다.');
				$('#memberPw').val("");
				$('#pwChk').val("");
				$('#memberPw').focus();
				return false;
			}
			});
		});
		$('#memberPw').keydown(function(key) {
			
			if (key.keyCode == 13) {			
				var memberPw = $('#memberPw').val();
				var pwChk = $('#pwChk').val();
				var memberPw = $('#memberPw').val();
				var pwChk = $('#pwChk').val();
				if (memberPw == "") {
					alert('비밀번호를 입력하세요');
					$('#memberPw').val("");
					$('#memberPw').focus();
					return false;
				}else if (pwChk == "") {
					alert('비밀번호 확인을 입력하세요');
					$('#memberPw').val("");
					$('#memberPw').focus();
					return false;
				}else if (memberPw != pwChk) {
					alert('비밀번호가 일치하지 않습니다.');
					$('#memberPw').val("");
					$('#pwChk').val("");
					$('#memberPw').focus();
					return false;
				}	
		};
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<form action="${conPath }/delete_member.do" method="post">
	<div class="container my-5" id="main">
		<h2>회원탈퇴확인</h2>
		<div>
			<p>
				<b>${member.memberName }</b>님의 본인확인을 위해 비밀번호를 다시 한번 확인 합니다.
			</p>
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td>${member.memberId }</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="memberPw" id="memberPw"></td>
				</tr>
				<tr>
					<td>비밀번호확인</td>
					<td><input type="password" name="pwChk" id="pwChk"></td>
				</tr>
				<tr>
					<td colspan="2" class="td text-center">
						<button id="submit" class="btn-dark">확인</button>
						<input type="button" onclick='history.back()' value="취소">

					</td>
				</tr>
			</table>
		</div>
	</div>
	</form>
	<jsp:include page="../main/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>