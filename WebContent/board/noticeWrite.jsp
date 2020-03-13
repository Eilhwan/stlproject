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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container my-5">
		<div class="form-group">
			<h3>공지사항 글쓰기</h3>
		</div>
		<form action="${conPath }/writeNotice.do">
			<table class="table table-bordered">
				<thead>
					<tr>
						<td><b>옵션</b></td>
						<td><input type="checkbox" name="noticeEvent" value="1">&nbsp;이벤트
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><b>제목</b></td>
						<td><input type="text" class="form-control" name="noticeName">&nbsp;
						</td>
					</tr>
					<tr>
						<td><b>내용</b></td>
						<td><textarea class="form-control" rows="20"
								name="noticeContent"></textarea></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2" class="text-center"><input type="submit"
							value="글작성" class="btn-dark"></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
