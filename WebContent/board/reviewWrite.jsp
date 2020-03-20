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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container my-5">
		<div class="form-group">
			<h3>리뷰 글쓰기</h3>
		</div>
		<form action="${conPath }/writeReview.do">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td><b>상품이름</b></td>
						<td><input type="text" class="form-control" name="peName">&nbsp;
						</td>
					</tr>
					<tr>
						<td><b>제목</b></td>
						<td><input type="text" class="form-control" name="reviewName">&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							내용
						</th>
						<td>
							<textarea rows="20" cols="" class="form-control"></textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th rowspan="2">사진</th>
						<td colspan="2" class="text-center"><input type="file" class="form-control file" name="reviewImg2"></td>
					</tr>
					<tr>
						<td colspan="2" class="text-center"><input type="file" class="form-control file" name="reviewImg1"></td>
					</tr>
					<tr>
						<td colspan="2" class="text-center"><input type="submit"
							value="글작성" class="btn-dark"></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>
