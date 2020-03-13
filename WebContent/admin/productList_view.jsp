<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<c:if test="${empty admin }">
	<script>
		alert('접근 권한이 없습니다.');
		location.href='${conPath }/main.do'
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
					<li class="list-group-item p-3"><a href="${conPath }/adminView.do">회원리스트</a></li>
					<li class="list-group-item p-3"><a href="${conPath }/productListView.do">물품목록</a></li>
					<li class="list-group-item p-3">통계</li>
					<li class="list-group-item p-3"><a href='${conPath }/adminListView.do'>관리자목록</a></li>
				</ul>
			</div>
			<div class="col-sm">
				<div class="form-group">
					<h2>물품 리스트</h2>
					<table class="table table-bordered">
						<thead>
							<tr class="tr text-center">
								<th>물품코드</th>
								<th>브랜드</th>								
								<th>물품종류</th>
								<th>이름</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty list }">
								<tr>
									<th class="th text-center" colspan="4">등록된 상품이 없습니다.</th>
								</tr>
							</c:if>
							<c:if test="${not empty list }">
								<c:forEach items="${list }" var="product">
									<tr>
										<td>${product.productCode }</td>
										<td><a href="${conPath }/productContentView.do?productCode=${product.productCode }&pageNum=${pageNum }">${product.pbrandName }</a></td>
										<td>${product.ptypeName }</td>
										<td>${product.productName }</td>
									</tr>								
								</c:forEach>
							</c:if>
						</tbody>
						<tfoot>
							<tr><td colspan="4" class="text-right"><button class="btn-dark" onclick="location.href='${conPath}/insertProductView.do'">물품등록</button></td></tr>
							<tr><td colspan="8" class="text-center">
								<c:if test="${startPage > 10 }">
									<a href="${conPath }/adminView.do?pageNum=${startPage - 1 }">[이전]</a>
								</c:if>
								<c:forEach var="i" begin="${startPage }" end="${endPage }">
									<c:if test="${pageNum eq i }">
										<b>${i }</b>
									</c:if>
									<c:if test="${pageNum != i }">									
										<a href="${conPath }/adminView.do?pageNum=${i }">[${i }]</a>
									</c:if>
								
								</c:forEach>
								<c:if test="${endPage < pageCnt}">
									<a href="${conPath }/adminView.do?pageNum=${endPage + 1 }">[다음]</a>
								</c:if>
							</td></tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="../main/footer.jsp" />
	
</body>
</html>