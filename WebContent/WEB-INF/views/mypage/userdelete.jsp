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
<link rel="stylesheet" href="../css/userdelete.css" />

<!-- 외부 javascript file -->

<style type="text/css">
html, body{
	width: 100%;
}

</style>
<script type="text/javascript" src="../js/userdelete.js"></script>
</head>
<body>
 
<main class="delete-cont">
	
	<!-- 로고 -->
	<div class="delete-cont__logo" id="logo"></div>
	
	<!-- 슬픈 이미지 -->
	<div class="delete-cont__img">진짜 갈거에요...?:(</div>
	
	<!-- 회원탈퇴 확인용 비밀번호 입력 폼 -->
	<div class="delete-cont__delete">
		<form action="<%=request.getContextPath() %>/mypage/userdelete" method="post"
			class="delete-cont__delete-form" id="delete-form">
			
			<label for="userpw">회원 탈퇴 확인용 비밀번호를 입력해주세요...</label>
			<!-- 비밀번호를 입력하지 않으면 입력하라는 메시지 출력 -->
			<div id="error-pw"></div>
			<input type="password" name="upw" id="userpw" class="login-pw"
			placeholder="비밀번호"/>
			
			<!-- 회원 탈퇴 버튼 -->
			<button type="button" id="btnDelete" class="delete-btn">회원 탈퇴</button>
		</form>
	</div>
	
</main>

<%-- footer --%>
<jsp:include page="/layout/footer.jsp"></jsp:include>