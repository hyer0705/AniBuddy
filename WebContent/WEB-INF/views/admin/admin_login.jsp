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
</style>


</head>
<body>

<main class="login-cont">

	<!-- 로고 -->
	<div class="login-cont__logo" id="logo"></div>
	
	<!-- 로그인 폼 -->
	<div class="login-cont__login">
		<form action="<%=request.getContextPath() %>/admin/login" method="post"
			class="login-cont__login-form" id="login-form">
			<!-- 아이디를 입력하지 않으면 입력하라는 메시지 출력 -->
			<div id="error-id"></div>
			<input type="text" name="adminid" id="userid" class="login-id"
			placeholder="관리자 아이디"/>
			<!-- 비밀번호를 입력하지 않으면 입력하라는 메시지 출력 -->
			<div id="error-pw"></div>
			<input type="password" name="adminpw" id="userpw" class="login-pw"
			placeholder="비밀번호"/>
			
			<button type="button" id="btnLogin" class="login-btn">관리자 로그인</button>
		</form>		
	</div>

</main>



<%-- footer --%>
<jsp:include page="/layout/footer.jsp"></jsp:include>