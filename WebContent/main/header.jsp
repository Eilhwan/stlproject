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
</head>
<body>
	<header>
        <div class="container-expand-md navbar-dark bg-dark p-2">
			<c:if test="${empty member && empty admin}">
	            <ul class="nav justify-content-end">
	                <li class="nav-item">
	                  <a class="nav-link" href="${conPath }/signinView.do">Sign-in</a>
	                </li>
	                <li class="nav-item">
	                  <a class="nav-link bg-dark" href="${conPath }/signupView.do">Sign-up</a>
	                </li>
	              </ul>
			</c:if>
			<c:if test="${not empty member && empty admin}">
		 		<ul class="nav justify-content-end">
					<li class="nav-item">
	                  <a class="nav-link" href="${conPath }/memberView.do">${member.memberName }님</a>
	                </li>
					<li class="nav-item">
	                  <a class="nav-link" href="${conPath }/cartView.do">장바구니</a>
	                </li>
					<li class="nav-item">
	                  <a class="nav-link" href="${conPath }/signinView.do">주문목록</a>
	                </li>
	                <li class="nav-item">
	                  <a class="nav-link bg-dark" href="${conPath }/logout.do">Logout</a>
	                </li>
             	 </ul>
			</c:if>
			<c:if test="${not empty admin && empty member}">
		 		<ul class="nav justify-content-end">
					<li class="nav-item">
	                  <a class="nav-link" href="${conPath }/memberView.do">관리자 ${admin.adminId }님</a>
	                </li>
					<li class="nav-item">
	                  <a class="nav-link" href="${conPath }/adminView.do">관리메뉴</a>
	                </li>
					<li class="nav-item">
	                  <a class="nav-link" href="${conPath }/noticeBoardView.do">공지사항</a>
	                </li>
	                <li class="nav-item">
	                  <a class="nav-link bg-dark" href="${conPath }/logout.do">Logout</a>
	                </li>
             	 </ul>
			</c:if>
        </div> 
        <nav class="navbar navbar-expand navbar-dark bg-dark p-3">
            <a class="navbar-brand" href="#">STL</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${conPath }/main.do">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown hovered">
                        <a class="nav-link" href="#" id="navbardrop" data-toggle="dropdown">
                            		상품보기
                        </a>
                        <div class="dropdown-menu">
                        <a href="${conPath }/userProductListView.do?pTypeCode=1" class="dropdown-item">홍차</a>
                        <a href="${conPath }/userProductListView.do?pTypeCode=2" class="dropdown-item">녹차</a>
                        <a href="${conPath }/userProductListView.do?pTypeCode=7" class="dropdown-item">허브티</a>
                        <a href="${conPath }/userProductListView.do" class="dropdown-item">전체</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            게시판
                        </a>
                        <div class="dropdown-menu">
                        <a href="${conPath }/noticeBoardView.do" class="dropdown-item">공지사항</a>
                        <a href="${conPath }/eventBoardView.do" class="dropdown-item">이벤트</a>
                        <a href="#" class="dropdown-item">리뷰</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            고객센터
                        </a>
                        <div class="dropdown-menu">
                        <a href="${conPath }/qnaListView.do" class="dropdown-item">QnA</a>
                        <a href="#" class="dropdown-item">FAQ</a>
                        <a href="#" class="dropdown-item">About</a>
                        </div>
                    </li>
                </ul>
                <form class="form-inline mt-2 mt-md-0">
                    <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav>
    </header>
</body>
</html>