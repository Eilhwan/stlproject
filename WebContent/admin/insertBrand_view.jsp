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
	<form action="${conPath }/insertBrand.do">
	<table>
		<tr>
			<td>브랜드이름</td>
			<td><input type="text" name="pBrandName"></td>
		</tr>
		<tr>
			<td>나라</td>
			<td><input type="text" name="pBrandCountry"></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="등록">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>