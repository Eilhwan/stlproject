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
<script>
	function CloseWindow() {
		let productName = document.getElementById('selectedItem');
		opener.location.href='${conPath }/productEnrollView.do?productName='+productName.value;
	  	window.close();
	}
</script>
</head>
<body>
	<form action="#">
		<select class="form-control" id="selectedItem">
			<c:forEach items="${list }" var="item" >
				<option>${item }</option>
			</c:forEach>
		</select>
		<input type="submit" class="btn btn-dark" onclick="CloseWindow()" value="선택">
	</form>
</body>
</html>