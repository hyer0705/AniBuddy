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
<link rel="stylesheet" href="../css/find.css">

<!-- external javascirpt file -->
<script type="text/javascript" src="../js/findid.js"></script>
<style type="text/css">
#footer{
	line-height: 25px;
}
</style>
</head>
<body>

<main class="find-cont">

	<!-- 로고&입력 폼 감싸는 div -->
	<div class="find-cont__wrap">
	<!-- 로고 -->
	<div class="find-cont__logo" id="logo"></div>

	<div class="find-cont__link">
	<a href="/anibuddy/user/findid"><span class="id-cont unselect">아이디 찾기</span></a><span class="pw-cont select">비밀번호 찾기</span>
	</div>
	
	<div class="find-cont__link">
		<c:if test="${not empty isSuccess and not isSuccess }">
		<div class="find_result">
			<span class="find_result__title">입력하신 정보로 조회된 회원이 없습니다...</span>
			<button type="button" id="btnToJoin" class="btn">회원가입 화면으로</button>
			<button type="button" id="btnToMain" class="btn">메인 화면으로</button>
		</div>
		</c:if>
		
		<c:if test="${not empty isUpdate and not isUpdate }">
		<div class="find_result">
			<span class="find_result__title">홈페이지 서버의 문제로 인해 비밀번호 재설정을 할 수 없습니다.</span>
<!-- 			<button type="button" id="btnToJoin" class="btn">회원가입 화면으로</button> -->
			<button type="button" id="btnToMain" class="btn">메인 화면으로</button>
		</div>
		</c:if>
	</div>
	</div>

</main>

<%-- footer --%>
<jsp:include page="/layout/footer.jsp"></jsp:include>