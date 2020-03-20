<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<c:if test="${not empty resultmsg }">
	<script>
		alert('resultmsg');
	</script>
</c:if>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div class="container my-3">
	<div class="row">
		<h2>검색 결과</h2>
	</div>
	<div class="row">	
		<h3>${keyWord }의 검색결과로 총 ${resultCnt }개의 상품을 찾았습니다.</h3>
	</div>
		<ul class="overflow-hidden">
			<c:forEach items="${list }" var="item">
			<li class="col-sm-3 float-left">
				<a href="${conPath }/userProductContentView.do?pageNum=${pageNum}&peCode=${item.peCode }">
					<dl class="list-group" style="list-style: none">
						<dt class="list-group-item"><img src="${conPath }/pePhotoUp/${item.peImg1}" width="200"></dt>
						<dd>
							<h4>${item.peName }</h4>
							<p><c:if test="${item.peDiscount != 0 }"><del>${item.pePrice }</del></c:if>
							<strong>${item.pePrice -item.peDiscount }원</strong></p>
							<p>${item.pePoint }점 적립</p>
						</dd>
					</dl>
				</a>
			</li>
			</c:forEach>
		</ul>
		<div class="row text-center">
		<c:if test="${startPage > 10 }">
			<a href="${conPath }/reviewBoardView.do?pageNum=${startPage - 1 }">[이전]</a>
		</c:if> 
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${pageNum eq i }">
				<b>${i }</b>
			</c:if>
			<c:if test="${pageNum != i }">
				<a href="${conPath }/reviewBoardView.do?pageNum=${i }">[${i }]</a>
			</c:if>
		</c:forEach> 
		<c:if test="${endPage < pageCnt}">
			<a href="${conPath }/reviewBoardView.do?pageNum=${endPage + 1 }">[다음]</a>
		</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>