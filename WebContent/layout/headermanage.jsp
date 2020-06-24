<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AniBuddy</title>

<link rel="stylesheet" href="/anibuddy/css/style.css" />
<link rel="stylesheet" href="/anibuddy/css/style_main.css" />
<link rel="stylesheet" href="/anibuddy/css/jquery.bxslider.css" />
<!-- 메인화면 슬라이더 -->
<script src="/anibuddy/js/jquery-3.4.1.js"></script>
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script> -->
<script src="/anibuddy/js/jquery.bxslider.js"></script>


<!-- taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- jQuery 2.2.4 -->

<!-- 부트스트랩 3.3.2 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- layout.css -->
<link rel="stylesheet" href="/anibuddy/css/layout.css" />



</head>
<body>
	<div class="header" style="width: 1000px;">
		<div id="icon">
			<a href="/anibuddy/"><img src="/anibuddy/img/logo.png"></a>
		</div>



		<div id="login">
		<c:choose>
			<c:when test="${adminLogin }">
				<span>${adminid } 님</span> | <span><a href="<%=request.getContextPath()%>/admin/logout">로그아웃</a></span>
			</c:when>
			<c:when test="${not login }">
				<span><a href="<%=request.getContextPath()%>/user/login">로그인</a></span> | <span><a href="<%=request.getContextPath()%>/user/join">회원가입</a></span>
			</c:when>
			<c:when test="${login }">
				<span>${loginnick } 님</span> | <span><a href="<%=request.getContextPath()%>/user/logout">로그아웃</a></span>
			</c:when>
		</c:choose>
		</div>
<!-- 		<div id="login"> -->
<!-- 			<span><a href="/user/login">로그인 |</a></span> <span><a href="#">회원가입</a></span> -->
<!-- 		</div> -->
		<nav>
			<ul class="nav nav-pills nav-justified">
				<li><a href="/anibuddy/">홈</a>

				<li><a href="<%=request.getContextPath() %>/manage/user">회원관리</a>

				<li><a href="<%=request.getContextPath() %>/board/main">게시판 관리</a>

				<li><a href="<%=request.getContextPath() %>/oneonone/reply">QnA관리</a>
			</ul>
		</nav>
	</div>