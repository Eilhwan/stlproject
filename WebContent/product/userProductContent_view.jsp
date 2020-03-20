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
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script>
	$(function(){
		$('#insertCart').click(function(){
			let productEA = $('#productEA').val();
			if (${product.productRemain } == 0) {
				alert('해당 상품은 현재 품절입니다.');
				return false;				
			}
			if ($('#productEA').val() > ${product.productRemain }) {
				alert('선택하신 수량이 재고량 보다 많습니다.');
				return false;
			}else if ($('#productEA').val() < 1) {
				alert('0보다 낮은 수량은 선택하실 수 없습니다.');
				return false;
			}else if ($('#productEA').val() == "") {
				alert('구입할 상품의 수량을 선택해주세요.');
				return false;
			}else {
				alert('장바구니에 ' + productEA + "개의 상품을 담았습니다.");
			}
		});
	});
			
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container overflow-hidden my-5">
		<div class="row">
			<h2>상품 상세보기</h2>
		</div>
		<img src="${conPath }/pePhotoUp/${pe.peImg1 }" width="500" class="float-left">
		<div>
			<form action="${conPath }/insertCart.do">
			<input type="hidden" value="${param.peCode }" name="peCode" id="peCode">
			<div class="float-left my-5">
				<h3>${product.pbrandName }</h3>
				<h2>${pe.peName }</h2>
				<p>구매${pe.sellCnt }(남은 수량 ${product.productRemain }개)</p>
				<h2>${pe.peDiscount }% <del>${pe.pePrice }</del></h2>
				<h1>${pe.pePrice * (1 - pe.peDiscount/100 ) }원</h1>
				<c:if test="${pe.pePoint != 0 }"><h4>포인트 적립 ${pe.pePoint }원 적립</h4></c:if>
				<input type="number" name="productEA" id="productEA"><input type="submit" id="insertCart" class="btn btn-dark" value='<c:if test="${product.productRemain <= 0 }">재고없음</c:if><c:if test="${product.productRemain > 0 }">장바구니 담기</c:if>'>
				
				<p>문의 사항이 존재하다면? <a href="${conPath }/qnaListView.do">문의사항 바로가기</a></p>
			</div>
			</form>
		</div>
	</div>
	<div class="container">
		<div>
			<h2>상품상세</h2>
			<div class="jumbotron">
				${pe.peContent }
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>