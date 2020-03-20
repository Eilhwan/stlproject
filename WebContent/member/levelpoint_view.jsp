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
<style>
.form-control {
	width: 80%;
}
</style>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(function() {
		$('form').submit(function() {
			let memberPw = $('#memberPw').val();
			let pwChk = $('#pwChk').val();
			if (pwChk != memberPw) {
				alert('비밀번호를 확인해주세요.');
				return false;
			}
		});
	});
</script>
</head>

<body>
	<jsp:include page="../main/header.jsp" />

	<form action="${conPath }/addressModify.do" method="post">
		<div class="container my-5">
			<div class="row">
				<div class="col-sm-2 my-5">
					<ul class="list-group">
                        <li class="list-group-item p-3"><a href="${conPath }/modify_view.do">고객 정보</a></li>
                        <li class="list-group-item p-3"><a href="${conPath }/addressView.do"> 배송지 관리 </a></li>
                        <li class="list-group-item p-3"><a href="${conPath }/levelpointView.do"> 등급확인 </a></li>
                        <li class="list-group-item p-3"><a href="${conPath }/deleteMemberView.do">회원탈퇴</a></li>
                    </ul>
				</div>
				<div class="col-sm">
					<div class="container">
						<h2>회원등급확인</h2>
						<div>
							<p>
								<b>${member.memberName }</b>님의 포인트 관련 정보입니다.
							</p>
							<table class="table table-bordered">
								<tr>
									<td colspan="2"><b>${member.memberName}님</b>의 현재까지 구매금액은
										<b>${member.totalSpent }원</b> 이며 등급은 <b>${member.gradeName }등급</b> 입니다.</td>
								</tr>
							</table>
							<table class="table table-bordered">
								<tr>
									<th>MemberShip 등급</th>
								</tr>
								<tr>
									<th>Normal 가입하신 모든 회원님</th>
								</tr>
								<tr>
									<th>Gold 구매금액이 5만원 이상 회원님</th>
								</tr>
								<tr>
									<th>VIP 구매금액이 10만원 이상 회원님</th>
								</tr>
								<tr>
									<th>VVIP 구매금액이 20만원 이상 회원님</th>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<jsp:include page="../main/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>

</html>