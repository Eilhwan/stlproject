<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />


<!DOCTYPE html>
<html lang="en-US">
<head>
 <title></title>
 <meta charset="UTF-8">
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
  <body>
  	<form action="${conPath }/orderProduct.do">
  	
	<div class="container">
			<div class="form-group justify-content-center">		 	
			<a href="${conPath }/main.do" class="justify-content-center"><img alt="사진" src="${conPath }/img/Title.png" width="300px"></a>
		</div>
		<div class="row">
			<h2>주문하기</h2>
		</div>
		<div class="row">
			<h3>구매자정보</h3>
		</div>
			<table class="table text-left">
				<tr>
					<th>이름</th>
					<td>${member.memberName }</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${member.memberEmail }</td>
				</tr>
				<tr>
					<th>휴대폰 번호</th>
					<td>${member.memberTel }</td>
				</tr>
			</table>
		</div>
		
	
	<div class="container">
		<h3>배송정보<input type="button" value="배송지 변경" class="btn btn-dark"></h3>
		<table class="table">
				<tr>
					<th>배송주소</th>
					<td>${member.memberAddress }</td>
				</tr>
				<tr>
					<th>구매상세</th>
							<th>상품명</th>
							<th>단가</th>
							<th>수량</th>
							<th>합계</th>
				</tr>
				<c:forEach items="${list }" var="cart">
				<tr>
					<td>
							${cart.productName }<input type="hidden" value="${cart.productCode }" name="productCode">
					</td>
					<td>
							${cart.pePrice }
					</td>
					<td>
							${cart.productEA }<input type="hidden" value="${cart.productEA }" name="productEA">
					</td>
					<td>
							 ${cart.cartPrice }<input type="hidden" value="${cart.cartPrice }" name="cartPrice">
					</td>
				</tr>
				</c:forEach>
			</table>
		
	</div>
	<div class="container">
		<h3>결제정보</h3>
				<table class="table">
				<tr>
				<c:set var="total" value="0"></c:set>
				<c:forEach items="${list }" var="each">
					<c:set var="total" value="${total = total + each.cartPrice }"></c:set>
					<c:set var="discount" value="${discount = discount + each.cartPrice * (each.peDiscount/100) }"></c:set>
				</c:forEach>
					
					<th>총상품가격</th>
					<td>${total }원</td>
				</tr>
				<tr>
					<th>즉시할인</th>
					<td><fmt:parseNumber value="${discount }" integerOnly="true"></fmt:parseNumber>원</td>
				</tr>
				<tr>
					<th>배송비</th>
					<td>2500원</td>
				</tr>
				<tr>
					<th>포인트</th>
					<td></td>
				</tr>
				<tr>
					<th>총결제금액</th>
					<td><fmt:parseNumber value="${(total - discount) + 2500 }" integerOnly="true"></fmt:parseNumber> 원<input type="hidden" value="<fmt:parseNumber value="${total - discount}" integerOnly="true" type="number"></fmt:parseNumber>" name="orderTotal"></td>
				</tr>
				<tr>
					<th>결제방법</th>
					<td>
					<span data-toggle="collapse" data-target="#demo" class="bg-dark text-white">결제방법</span>
					<div id="demo" class="collapse">
						<input type="radio" name="payment" value="cash">무통장입금<input type="radio" name="payment" value="point">포인트
						<c:if test="${param.payment eq point }">
							가용 포인트${member.memberPoint }원 사용할 포인트 <input type="text" value="0"><input type="button" value= "가용포인트 사용하기">
						</c:if>
					</div>
					</td>				

				</tr>

			</table>
	</div>
	<div class="container">
		<input type="button" value="뒤로가기" onclick="history.back()" class="btn btn-dark">
		<input type="submit" value="결제하기" class="btn btn-dark">
	</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>