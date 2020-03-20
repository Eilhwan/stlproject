<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>


<c:if test="${not empty signinerror}">
	<script>
		alert('${signinerror }');
		history.back();
	</script>
</c:if>
<c:if test="${not empty resultmsg}">
	<script>
		alert('${resultmsg }');
	</script>
</c:if>
<c:if test="${not empty resulterror}">
	<script>
		alert('${resulterror }');
		history.back();
	</script>
</c:if>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container my-5">	
		  <div class="slider container">
		   	<div><img src="${conPath }/img/twg.jpg"></div>
		   	<div><img src="${conPath }/img/potnam.jpg"></div>
		 </div>
		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">공지사항</h2>
				<p>
					<a
						href="${conPath }/noticeContentView.do?noticeNo=${notice1.noticeNo }">${notice1.noticeName }</a>
				</p>
				<hr class="featurette-divider">
				<p>
					<a
						href="${conPath }/noticeContentView.do?noticeNo=${notice2.noticeNo }">${notice2.noticeName }</a>
				</p>
				<hr class="featurette-divider">
				<p>
					<a
						href="${conPath }/noticeContentView.do?noticeNo=${notice3.noticeNo }">${notice3.noticeName }</a>
				</p>
				<hr class="featurette-divider">
				<p>
					<a
						href="${conPath }/noticeContentView.do?noticeNo=${notice4.noticeNo }">${notice4.noticeName }</a>
				</p>
			</div>
		</div>
	
		<hr class="featurette-divider">
	
		<div class="row featurette">
			<div class="col-md-7 order-md-2">
				<h2 class="featurette-heading">진행중인 이벤트</h2>
				<a
					href="${conPath }/noticeContentView.do?noticeNo=${event1.noticeNo }">${event1.noticeName }</a>
				<hr class="featurette-divider">
				<p>
					<a
						href="${conPath }/noticeContentView.do?noticeNo=${event2.noticeNo }">${event2.noticeName }</a>
				</p>
				<hr class="featurette-divider">
				<p>
					<a
						href="${conPath }/noticeContentView.do?noticeNo=${event3.noticeNo }">${event3.noticeName }</a>
				</p>
				<hr class="featurette-divider">
				<p>
					<a
						href="${conPath }/noticeContentView.do?noticeNo=${event4.noticeNo }">${event4.noticeName }</a>
				</p>
	
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
		<script>
			$(document).ready(function(){
			    $('.slider').bxSlider({
			    	auto: true,
			    	mode: 'fade',
			        captions: true,
			        slideWidth: 1000
			    });
			  });
		</script>
</body>
</html>