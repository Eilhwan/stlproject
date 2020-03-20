<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<c:if test="${empty member }">
	<script>
		alert('로그인 후에 이용해주세요.');
		location.href = '${conPath }/main.do';
	</script>
</c:if> 
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function(){
		$('form').submit(function(){
			var checkedcnt = $(".chkbox:checked").length
			if (checkedcnt == 0) {
				alert('한 개 이상의 상품을 선택하세요.');
				return false;
			}
		});
		$('#checkall').click(function(){
			if ($('#checkall').prop('checked')) {
				$('.chkbox').prop('checked', true);
				$('.chkbox').attr('name', 'checked');
			}else{
				$('.chkbox').prop('checked', false);
				$('.chkbox').removeAttr('name');
			}
				var checkedcnt = $(".chkbox:checked").length
				$('#checkedcnt').html(checkedcnt);
		});
		$('.chkbox').click(function(){
			if ($(this).prop('checked')) {
				var checkedcnt = $(".chkbox:checked").length
				$(this).attr('name', 'checked');
				$('#checkedcnt').html(checkedcnt);
			}else{
				var checkedcnt = $(".chkbox:checked").length
				$('.chkbox').removeAttr('name');
				$('#checkedcnt').html(checkedcnt);
			}
		});
		$('#checkedDelete').click(function(){
			
			
		});
		$('#main').click(function(){
			location.href="${conPath }/main.do" 
		});
		$('.productEA').click(function(){
			$(this).attr('name', 'productEA');
			$(this).attr('id', 'productEA');
			$(this).next().attr('name', 'cartNo');
			$(this).next().attr('id', 'cartNo');
		});
		$('.productEA').blur(function(){

			$(this).removeAttr('name');
			$(this).removeAttr('id');
			$(this).next().removeAttr('name');
			$(this).next().removeAttr('id');
		 	
		});
		$('#deleteAll').click(function(){
			location.href="${conPath }/deleteCartAll.do?memberId=${member.memberId }";
		});
		$(".productEA").on("propertychange change keyup paste input", function() {
			let productEA = $('#productEA').val();
			let cartNo = $('#cartNo').val();
		 	if (productEA == "") {
				$('#productEA').val('1');
			}
			location.href= '${conPath }/updateCart.do?productEA='+productEA+'&cartNo='+cartNo;
		    
		});
		
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<form action="">
	<div class="container my-5">
		<div class="row">
			<h2>장바구니</h2>
		</div>
		<div class="row">
			<h4>전체 ${cnt }개</h4>
		</div>
		<div class="row bg-dark text-white">
				<div class="col-md p-3">
					<h3><input type="checkbox" width="50" id="checkall">전체선택</h3>				
				</div>
				<div class="col-md p-3">
					<h3>상품정보</h3> 				
				</div>
				<div class=" col-md p-3">
					<h3>상품금액</h3> 			
				</div>
				<div class=" col-md p-3">
					<h3>수량</h3> 			
				</div>
				<div class="col-md p-3">
					<h3>배송비</h3> 				
				</div>
		</div>
		<c:if test="${empty list }">
			<div class="container row">
				<h1>장바구니에 포함된 상품이 없습니다.</h1>
			</div>
		</c:if>

		<c:if test="${not empty list }">
			<c:forEach items="${list }" var="cart">
				<div class="row">
						<div class="col-md p-3">
							<h4><input type="checkbox" width="50" value="${cart.cartNo }" class="chkbox">CAR-${cart.cartNo }</h4>				
						</div>
						<div class="col-md p-3">
							<h4>${cart.productName }<input type="hidden" name="productCode" value="${cart.productCode }"></h4> 				
						</div>
						<div class=" col-md p-3">
							<h4>${cart.cartPrice }</h4>
						</div>
						<div class=" col-md p-3">
							<input type="number" class="productEA" value="${cart.productEA }"><input type="hidden" class="cartNo" value="${cart.cartNo }" name="cartNo">
						</div>
						<div class="col-md p-3">
							<h4>2500원</h4> 				
						</div>
				</div>
			</c:forEach>
		</c:if>
		<div class="row">
			전체선택(<p id="checkedcnt">0</p> /  ${cnt }) <input type="submit" value="선택삭제" formaction="${conPath }/deleteCart.do" id="checkedDelete"> <input type="button" id="deleteAll"value="전체삭제"> 
		</div>
	</div>

	<div class="container overflow-hidden">
		<div class="row">
			<h1>총 주문금액</h1>
		</div>
		<div class="row">
			<ul class="float-right">
				<c:set var="total" value="0"></c:set>
				<c:forEach items="${list }" var="each">
					<c:set var="total" value="${total = total + (each.cartPrice * (1 - each.peDiscount/100)) }"></c:set> 
				</c:forEach>
				<li>총 상품금액<h4>${total } 원</h4></li>
				<li>배송비<h4>2500원</h4></li>
				<li>결제 예상금액<h4>${total + 2500 }원</h4></li>
			</ul>
		</div>
		<div class="row text-center">
			<input type="button" value="계속 쇼핑하기" class="btn btn-dark" id="main"><input type="submit" value="구매하기" formaction="${conPath }/checkout.do" class="btn btn-dark">
		</div>
	</div>
	</form>

	<jsp:include page="../main/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	
</body>
</html>