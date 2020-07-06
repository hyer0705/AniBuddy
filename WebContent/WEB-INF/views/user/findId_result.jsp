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
	<span class="id-cont select">아이디 찾기</span><a href="/anibuddy/user/findpw"><span class="pw-cont unselect">비밀번호 찾기</span></a>
	</div>
	
	<div class="find-cont__link">
		<c:if test="${isSuccess }">
		<div class="find_result">
			<span class="find_result__title">아이디 찾기 성공~!~~!</span>
			<span class="find_result__content">회원님의 아이디는 ${userid }입니다.</span>
			<button type="button" id="btnToFindPw" class="btn">비밀번호 찾으러 가기</button>
			<button type="button" id="btnToLogin" class="btn">로그인 화면으로</button>
		</div>
		</c:if>
		
		<c:if test="${not isSuccess }">
		<div class="find_result">
			<span class="find_result__title">입력하신 정보로 조회된 회원이 없습니다...</span>
			<button type="button" id="btnToJoin" class="btn">회원가입 화면으로</button>
			<button type="button" id="btnToMain" class="btn">메인 화면으로</button>
		</div>
		</c:if>
	</div>
	</div>

</main>

<%-- footer --%>
<jsp:include page="/layout/footer.jsp"></jsp:include>