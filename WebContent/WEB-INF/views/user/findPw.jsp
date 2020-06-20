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
<link rel="stylesheet" href="../css/find.css">

<!-- external javascript file -->
<script type="text/javascript" src="../js/findid.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#userid").on("keydown", function(key){
		/* 엔터 키 눌렀을 때 submit */
		if(key.keyCode == 13){
			$(this).parents("form").submit()
		}
	})
})
</script>
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
	<!-- 입력 폼 -->
	<div class="find-cont__find">
		<form action="/anibuddy/user/findpw" method="post"
		class="find-cont__find-form" id="find-form">
			<div id="err-name"></div>
			<input type="text" name="name" id="name" placeholder="이름"/>
			<div id="err-email"></div>
			<input type="email" name="email" id="email" placeholder="이메일"/>
			<div id="err-id"></div>
			<input type="text" name="userid" id="userid" placeholder="아이디"/>
			
			<button type="button" id="btnFindPw" class="btnFind">비밀번호 찾기</button>
		</form>
	</div>
	</div>

</main>

<%-- footer --%>
<jsp:include page="/layout/footer.jsp"></jsp:include>