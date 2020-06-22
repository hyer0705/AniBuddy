<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AniBuddy</title>

<!-- jQuery 라이브러리 2.2.4.min.js -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- css -->
<link rel="stylesheet" href="../css/layout.css" />
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../css/userdelete.css" />

<!-- 외부 javascript file -->
<script type="text/javascript" src="../js/userdelete.js"></script>

<style type="text/css">
html, body{
	width: 100%;
}

.margin-top-bottom {
    margin-bottom: 120px;
    margin-top: 100px;
}

.text_center {
	text-align: center;
}
.btn-wrap {
	text-align: center;
}

.btn-wrap .btn-wrap__btn {
	width: 400px;
    height: 35px;
    margin-top: 20px;
    background: white;
    outline: none;
    border: none;
    border: 1px solid #99d4d9;
}

.btn-wrap .btn-wrap__btn:focus
, .btn-wrap .btn-wrap__btn:hover {
	border: 1px solid #ffbf00;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#btnToMain").on("click", function(){
		$(location).attr("href", "/anibuddy/")
	})
})
</script>

</head>
<body>
 
<main class="delete-cont margin-top-bottom">
	
	<!-- 로고 -->
	<div class="delete-cont__logo" id="logo"></div>
	
	<!-- 결과 문장 -->
	<c:choose>
		<c:when test="${chkPw }">
			<div class="text_center">비밀번호와 일치하네요! 회원 탈퇴가 진행됐습니다. 안녕히 가세요...ㅜㅜ</div>
		</c:when>
		<c:when test="${empty chkPw }">
			<div class="text_center">비밀번호와 일치하지 않습니다! 안타깝게도 회원탈퇴는 실패했습니다.</div>
		</c:when>
	</c:choose>
	<div class="btn-wrap"><button type="button" id="btnToMain" class="btn-wrap__btn" >메인화면으로 가기</button></div>
	
</main>

<%-- footer --%>
<jsp:include page="/layout/footer.jsp"></jsp:include>