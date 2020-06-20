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

<script type="text/javascript" src="../js/updatePw.js"></script>

</head>
<body>

<main class="updatepw-cont">

	<!-- 로고&입력 폼 감싸는 div -->
	<div class="updatepw-cont__wrap">
	
		<!-- 로고 -->
		<div class="updatepw-cont__logo" id="logo"></div>
	
		<!-- 아이디찾기, 비밀번호 찾기 link -->
		<div class="updatepw-cont__link">
		<a href="/anibuddy/user/findid"><span class="id-cont unselect">아이디 찾기</span></a><span class="pw-cont select">비밀번호 찾기</span>
		</div>
		
		<!-- 입력 폼 -->
		<div class="updatepw-cont__update">
			<form action="/anibuddy/user/updatepw" method="post"
			class="updatepw-cont__update-form" id="update-form">
			
			<input type="hidden" name="userno" value="${userno }"/>
			<span class="updatepw_result__title">회원 인증  성공! 비밀번호를 재설정하세요.</span>
			<div class="update-form__group">
			<div id="err-pw"></div>
			<label for="userpw">비밀번호</label>
			<input type="password" name="userpw" id="userpw" />
			</div>
			
			<div class="update-form__group">
			<div id="err-chkpw"></div>
			<label for="chkpw">비밀번호 재확인</label>
			<input type="password" name="chkpw" id="chkpw" />
			</div>
			<button type="button" id="btnUpdatePw" class="btnUpdate">비밀번호 재설정</button>
			
			</form>
		</div>
		
	</div>

</main>


<%-- footer --%>
<jsp:include page="/layout/footer.jsp"></jsp:include>
























