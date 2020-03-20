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
	<c:if test="${not empty msg }">
		<script>
			alert('${msg}');
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div class="container my-5">
		<h2>주문완료</h2>
		<p>고객님이 주문하신  상품이 주문완료되었습니다.</p>
		<h3>주문시각:${order.orderDate }</h3>
		<table class="table table-borered">
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>상품가격</th>
				<th>수량</th>
				<th>합계</th>
			</tr>
			<c:forEach items="${od }" var="item">			
			<tr>
				<th>${item.odno }</th>
				<th>${item.productName }</th>
				<th><fmt:parseNumber integerOnly="true">${item.odPrice/item.odCnt }</fmt:parseNumber> </th>
				<th>${item.odCnt }</th>
				<th>${item.odPrice }</th>
			</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>