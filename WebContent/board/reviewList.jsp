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
	<div class="container my-5">
		<div class="form-group justify-content-center">
			<h2>리뷰 게시판</h2>
		</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th class="p-1">번호</th>
					<th class="p-1">제목</th>
					<th class="p-1">글쓴이</th>
					<th class="p-1">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list }">
					<tr>
						<th class="th text-center" colspan="4">작성된 글이 존재하지 않습니다.</th>
					</tr>
				</c:if>
				<c:forEach items="${list }" var="review">
						<tr>
							<td>${review.reviewNo }</td>
							<td><a href="${conPath }/reviewContentView.do?reviewNo=${review.reviewNo }">${review.reviewName }</a></td>
							<td>${review.memberName }</td>
							<td>${review.reviewRdate }</td>
						</tr>
				</c:forEach>
			</tbody>
			<tfoot>
					<tr>
						<td colspan="4" class="td text-right"><button
								onclick="location.href='${conPath}/writereviewView.do'"
								class="btn-dark">글쓰기</button></td>
					</tr>
				<tr>
					<td colspan="4" class="text-center"><c:if
							test="${startPage > 10 }">
							<a href="${conPath }/reviewBoardView.do?pageNum=${startPage - 1 }">[이전]</a>
						</c:if> <c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${pageNum eq i }">
								<b>${i }</b>
							</c:if>
							<c:if test="${pageNum != i }">
								<a href="${conPath }/reviewBoardView.do?pageNum=${i }">[${i }]</a>
							</c:if>

						</c:forEach> <c:if test="${endPage < pageCnt}">
							<a href="${conPath }/reviewBoardView.do?pageNum=${endPage + 1 }">[다음]</a>
						</c:if></td>
				</tr>
			</tfoot>
		</table>
	</div>

	<jsp:include page="../main/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>