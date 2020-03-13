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

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"/>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type='text/javascript' src='//code.jquery.com/jquery-1.8.3.js'></script>
<link rel="stylesheet" href="${conPath }/css/signUp.css" >

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker3.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
$(function(){
	
    
});
function openWindow(){
	window.open('${conPath }/insertBrandView.do', 'width=500, height=600');
};

</script>
	
</head>
<body>
<jsp:include page="../main/header.jsp" />
	<form action="${conPath }/insertProduct.do" method="post" enctype="multipart/form-data">
	<div class="container my-5">
		<h2>물품 등록</h2>
		<table class="table">
			<thead>
				<tr>
					<th>물품명<input type="text" class="form-control" placeholder="물품명" id="productName" name="productName"></th>
					<th>단가<input type="number" class="form-control" placeholder="0" id="productPrice" name="productPrice"></th>
					<th>초기재고<input type="number" class="form-control" placeholder="0" id="productRemain" name="productRemain"></th>
				</tr>
				<tr>
					<th>상품타입<select name="ptypeCode">
								<c:forEach items="${types }" var="type">
									<option value="${type.pTypeCode }">${type.pTypeName}</option>
								</c:forEach>
							</select>
					</th>
					<th>브랜드<select name="pbrandCode">
								<c:forEach items="${brands }" var="brand">
									<option value="${brand.pBrandCode }">${brand.pBrandName}</option>
								</c:forEach>
							</select>
					</th>
					<th>
						<input type="button" class="btn-dark" onclick='openWindow()' value="브랜드 추가">
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>물품설명</th>
					<th colspan="2"><textarea rows="10" cols="20" class="form-control" name="productContent"></textarea></th>
				</tr>
				<tr>
					<th>사진</th>
					<th colspan="2"><input type="file" class="form-control" accept=".jpg, .png, .bmp, .gif" name="productImg"></th>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="3"><input type="submit" value="등록" class="btn-dark"></th>
				</tr>
			</tfoot>
		</table>
	</div>
	</form>
<jsp:include page="../main/footer.jsp" />
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>