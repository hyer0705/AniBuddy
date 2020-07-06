<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="../css/login.css">

<!-- 외부 javascript file -->
<script type="text/javascript" src="../js/login.js"></script>

<style type="text/css">
html, body{
	width: 100%;
}
#footer{
	line-height: 25px;
}
</style>


</head>
<body>
 
<main class="login-cont">
	
	<!-- 로고 -->
	<div class="login-cont__logo" id="logo"></div>
	
	<!-- 로그인 폼 -->
	<div class="login-cont__login">
		<form action="<%=request.getContextPath() %>/user/login" method="post"
			class="login-cont__login-form" id="login-form">
			<!-- 아이디를 입력하지 않으면 입력하라는 메시지 출력 -->
			<div id="error-id"></div>
			<input type="text" name="uid" id="userid" class="login-id"
			placeholder="아이디"/>
			<!-- 비밀번호를 입력하지 않으면 입력하라는 메시지 출력 -->
			<div id="error-pw"></div>
			<input type="password" name="upw" id="userpw" class="login-pw"
			placeholder="비밀번호"/>
			
			<button type="button" id="btnLogin" class="login-btn">로그인</button>
		</form>		
	</div>
	
	<!-- 회원가입 버튼 -->
	<div class="login-cont__btn-join">
		<button type="button" id="btnJoin" class="join-btn">회원가입</button>
	</div>
	
	<!-- find-info -->
	<div class="login-cont__find-info">
		<span><a href="/anibuddy/user/findid">아이디 찾기</a></span><span><a href="/anibuddy/user/findpw">비밀번호 찾기</a></span><span><a href="/anibuddy/admin/login">관리자 로그인</a></span>
	</div>
	
</main>

<%-- footer --%>
<jsp:include page="/layout/footer.jsp"></jsp:include>



