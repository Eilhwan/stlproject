<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .form-control {
            width: 80%;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        $(function () {
            $('form').submit(function () {
                let memberPw = $('#memberPw').val();
                let pwChk = $('#pwChk').val();
                if (pwChk != memberPw) {
                    alert('비밀번호를 확인해주세요.');
                    return false;
                }
            });
        });

    </script>
</head>

<body>
	<jsp:include page="../main/header.jsp" />
	
    <form action="${conPath }/addressModify.do" method="post">
        <div class="container my-5">
            <div class="row">
                <div class="col-sm-2 my-5">
                    <ul class="list-group">
                        <li class="list-group-item p-3"><a href="${conPath }/modify_view.do">고객 정보</a></li>
                        <li class="list-group-item p-3"><a href="${conPath }/addressView.do"> 배송지 관리 </a></li>
                        <li class="list-group-item p-3"><a href="${conPath }/levelpointView.do"> 등급확인 </a></li>
                        <li class="list-group-item p-3"><a href="${conPath }/deleteMemberView.do">회원탈퇴</a></li>
                    </ul>
                </div>
                <div class="col-sm">
                    <div class="form-group justify-content-center">
                        <h2>배송지 수정</h2>
                    </div>
                    <table class="table table-bordered">
                        <tr>
                            <td>아이디</td>
                            <td><input type="text" class="form-control" value="${member.memberId }" id="memberId"
                                    readonly="readonly" name="memberId"></td>
                        </tr>
                        <tr>
                            <td>이름</td>
                            <td><input type="text" class="form-control" value="${member.memberName }" id="memberName"
                                    name="memberName"></td>
                        </tr>
                        <tr>
                            <td>현 배송지</td>
                            <td><input type="text" class="form-control" value="${member.memberAddress }" id="memberTel"
                                     readonly="readonly"></td>
                        </tr>
                        <tr>
                            <td>신규 배송지</td>
                            <td>
								<div class="form-group addr">
									
										<label for="post"><b>주소</b></label><br>
									
								        <input type="text" id="sample4_postcode" readonly="readonly" placeholder="우편번호" name="memberPost">
										<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
										<input type="text" onclick="sample4_execDaumPostcode()" id="sample4_roadAddress" placeholder="도로명주소" readonly="readonly" class="form-control" name="address1">
										<input type="text" onclick="sample4_execDaumPostcode()" id="sample4_jibunAddress" placeholder="지번주소" readonly="readonly" class="form-control" name="address2">
										<span id="guide" style="color:#999;display:none"></span>
										<input type="text" id="sample4_detailAddress" placeholder="상세주소" class="form-control" name="address3">
										<input type="text" id="sample4_extraAddress" placeholder="참고항목" readonly="readonly" class="form-control" name="address4">
								</div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="td text-center">
                                <input type="submit" value="확인" class="btn-dark">
                                <input type="button" value="취소" onclick='history.back()'>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </form>
    <jsp:include page="../main/footer.jsp" />
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
</body>

</html>