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
		alert('페이지 접근 권한이 없습니다.');
		location.href='${conPath }/main.do';
	</script>
</c:if>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script>
	$(function(){
		$('form').submit(function(){
			if ($('#productName') == null) {
				alert('등록하실 물품을 선택해주세요.');
				return false;
			}else if ($('#peDiscount, #pePoint, #peName, #peContent').val() == '') {
				alert('물품의 세부정보를 입력해주세요.');
				return false;
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div class="container my-5">
		<h2>상품 게시</h2>
		<form action="${conPath }/productenroll.do" method="post" enctype="multipart/form-data">
			<table class="table table-bordered">
				<tr>
					<td colspan="6">
						<input type="button" value="상품선택" onclick='window.open("${conPath }/productSelect.do", "PopupWin", "width=400,height=400")' class="btn btn-dark"><p id="productchosen"></p>
						<input type="hidden" value="${product.productCode }" id="productCode" name="productCode">
					</td>
				</tr>
				<tr>
					<th>물품 이름</th>
					<td><input type="text" name="productName" readonly="readonly" value="${product.productName }"></td>
					<th>물품 종류</th>
					<td>
						<input type="text" name="productType" readonly="readonly" value="${product.ptypeName }">
						<input type="hidden" name="pTypeCode" value="${product.ptypeCode }">
					</td>
					<th>브랜드</th>
					<td><input type="text" name="productBrand" readonly="readonly" value="${product.pbrandName }"></td>
				</tr>
				<tr>
					<th>상품 가격</th>
					<td><input type="text" name="pePrice" readonly="readonly" value="${product.productPrice }"></td>
					<th>상품 할인율</th>
					<td><input type="number" name="peDiscount" id="peDiscount"></td>
					<th>적립포인트</th>
					<td><input type="text" name="pePoint" id="pePoint"></td>
				</tr>
				<tr>
					<th>등록명</th>
					<td><input type="text" name="peName" id="peName"></td>
					<th>상품 재고</th>
					<td><input type="text" name="productRemain" readonly="readonly" value="${product.productRemain }"></td>
				</tr>
				<tr>
					<th>상세 설명</th>
					<td colspan="5"><textarea rows="20" cols="5" class="form-control" name="peContent" id="peContent">${product.productContent }</textarea> </td>
				</tr>
				<tr>
					<th>이미지1</th>
					<td colspan="5"><input type="file" name="peImg1" accept=".jpg,.png,.jpeg"></td>
				</tr>
				<tr>
					<th>이미지2</th>
					<td colspan="5"><input type="file" name="peImg2" accept=".jpg,.png,.jpeg"></td>
				</tr>
				<tr>
					<td colspan="6" class="text-center"><input type="submit" value="등록" class="btn-dark"></td>
				</tr>
				
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>