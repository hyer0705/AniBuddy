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

<!-- external javascirpt file -->
<script type="text/javascript" src="../js/findid.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#email").on("keydown", function(key){
		/* 엔터 키 눌렀을 때 submit */
		if(key.keyCode == 13){
			$(this).parents("form").submit()
		}
	})
})
</script>
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
	<!-- 입력 폼 -->
	<div class="find-cont__find">
		<form action="/anibuddy/user/findid" method="post"
		class="find-cont__find-form" id="find-form">
			<div id="err-name"></div>
			<input type="text" name="name" id="name" placeholder="이름"/>
			<div id="err-email"></div>
			<input type="email" name="email" id="email" placeholder="이메일"/>
			<button type="button" id="btnFindId" class="btnFind">아이디 찾기</button>
		</form>
	</div>
	</div>

</main>

<%-- footer --%>
<jsp:include page="/layout/footer.jsp"></jsp:include>