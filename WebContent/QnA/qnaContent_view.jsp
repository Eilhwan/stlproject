<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<c:if test="${empty admin && empty member }">
	<script>
		alert('로그인 후에 이용해주세요.');
		location.href = '${conPath }/signinView.do';
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
		<table class="table">
			<tr>
				<th>문의사항</th>
				<td>${qna.qnaName }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${qna.memberId }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${qna.qnaContent }</td>
			</tr>
			<tr>
				<td colspan="2" class="text-right">
				<c:if test="${not empty admin }">
					<button class="btn-dark" onclick="location.href='${conPath }/replyqnaView.do?qnano=${param.qnano }'">답변</button>
					<button class="btn-dark" onclick="location.href='${conPath }/qnaContent_delete'">삭제</button>
				</c:if>
					<button class="btn-dark" onclick="location.href='${conPath }/qnaListView.do'">목록</button>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>