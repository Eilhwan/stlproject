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
		<table class="table">
			<tr>
				<th><b>물품코드: </b>${product.productCode }</th>
				<td><b>물품명: </b>${product.productName }</td>
				<td rowspan="3"><img src='${conPath }/productPhotoUp/${product.productImg }'></td>
			</tr>
			<tr>
				<td><b>브랜드:</b> ${product.pbrandName }(${product.pbrandCode })</td>
				<td><b>종류:</b> ${product.ptypeName }(${product.ptypeCode })</td>
			</tr>
			<tr>
				<td><b>가격: </b> ${product.productPrice }</td>
				<td><b>재고: </b> ${product.productRemain }</td>
			</tr>
			<tr>
				<th><b>내용</b></th>
				<td colspan="2">${product.productContent }</td>
			</tr>
			<tr>
				<td colspan="3" class="text-right"><button class="btn-dark" onclick="location.href='${conPath }/productListView.do?pageNum=${param.pageNum }'">목록</button></td>
			</tr>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>