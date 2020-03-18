<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<c:if test="${admin.adminId != 'ADMIN' }">
	<script>
		alert('접근 권한이 없습니다.');
		location.href='${conPath }/adminView.do'
	</script>
</c:if>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container my-5">
		<div class="row">
			<div class="col-sm-2 my-5">
				<ul class="list-group">
					<li class="list-group-item p-3">회원리스트</li>
					<li class="list-group-item p-3">탈퇴회원목록</li>
					<li class="list-group-item p-3">통계</li>
					<li class="list-group-item p-3">관리자목록</li>
				</ul>
			</div>
			<div class="col-sm">
				<div class="form-group">
					<h2>관리자 페이지</h2>
					<table class="table table-bordered">
						<thead>
							<tr class="tr">
								<th>관리자계정</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty list }">
								<tr>
									<th class="th text-center">관리자가 존재하지 않습니다.</th>
								</tr>
							</c:if>
							<c:if test="${not empty list }">
								<c:forEach items="${list }" var="admin">
									<tr>
										<td>${admin.adminId }</td>
									</tr>								
								</c:forEach>
							</c:if>
						</tbody>
						<tfoot>
							<tr><td class="text-right"><button onclick="location.href='${conPath }/adminSignUpView.do'" class="btn btn-dark">관리자 계정 만들기</button></td></tr>
							<tr><td colspan="8" class="text-center">
								<c:if test="${startPage > 10 }">
									<a href="${conPath }/productListView.do?pageNum=${startPage - 1 }">[이전]</a>
								</c:if>
								<c:forEach var="i" begin="${startPage }" end="${endPage }">
									<c:if test="${pageNum eq i }">
										<b>${i }</b>
									</c:if>
									<c:if test="${pageNum != i }">									
										<a href="${conPath }/productListView.do?pageNum=${i }">[${i }]</a>
									</c:if>
								
								</c:forEach>
								<c:if test="${endPage < pageCnt}">
									<a href="${conPath }/productListView.do?pageNum=${endPage + 1 }">[다음]</a>
								</c:if>
							</td></tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="../main/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>