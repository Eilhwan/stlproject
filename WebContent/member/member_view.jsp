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
<script type='text/javascript' src='//code.jquery.com/jquery-1.8.3.js'></script>
<script>
	$(function(){
		$('#submit').click(function(){
			let memberPw = $('#memberPw').val();
			if (memberPw != '${member.memberPw}') {
				alert('비밀번호를 확인해주세요.');
				$('#memberPw').val("");
				return false;
			}
			$.ajax({
				url : "${conPath }/modify_view.do",
		    	type : "post",
		    	dataType : "html",
		    	data : "memberPw="+ memberPw,
		    	success : function(data){
		    		$('#main').html(data);
		    	}
			});			
		});
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container my-5" id="main">
		<form action="#" method="post">
				<h2>회원정보확인</h2>
				<div>
		
					<p><b>${member.memberName }</b>님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인 합니다.</p>
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
							<td colspan="2" class="td text-center">
								<input type="button" value="확인" id="submit" class="btn-dark">
								<input type="button" value="취소" onclick='history.back()'> 
								
							</td>
						</tr>
					</table>
				</div>
		</form>
				</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>