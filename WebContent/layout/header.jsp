<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AniBuddy</title>

<!-- taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- <script src="../js/jquery-3.4.1.js"></script> -->

<!-- 부트스트랩 3.3.2 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- css -->
<link rel="stylesheet" href="../css/layout.css" />
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../css/mypage_main.css"/>


</head>
<body>
	<div class="header" style="width:1000px;">
		<div id="icon">
			<a href="../main.jsp"><img src="../img/logo.png"></a>
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
		<nav>
			<ul class="nav nav-pills nav-justified">
				<li><a href="/anibuddy/">홈</a>
					<ul class="dropdown-content">
						<li><a href="#">소개</a></li>
					</ul></li>

				<!-- 기본적으로 놀거리 게시판으로 가게 했습니다. -->
				<li><a href="<%=request.getContextPath() %>/hangout/place">양육정보</a>
					<ul class="dropdown-content">
						<li><a href="<%=request.getContextPath() %>/hangout/place">놀거리</a></li>
						<li><a href="#">알거리</a></li>
					</ul></li>

				<li><a href="#">커뮤니티</a>
					<ul class="dropdown-content">
						<li><a href="#">나눔</a></li>
						<li><a href="#">중고거래</a></li>
						<li><a href="#">봉사</a></li>
						<li><a href="#">전문가QnA</a></li>
						<li><a href="#">자유 게시판</a></li>
					</ul></li>

				<c:choose>
					<c:when test="${not adminLogin }">
						<li><a href="/anibuddy/mypage/main">마이페이지</a>
							<ul class="dropdown-content">
								<li><a href="#">회원정보</a></li>
								<li><a href="#">활동 내역</a></li>
								<li><a href="#">쪽지함</a></li>
								<li><a href="#">1:1 문의</a></li>
							</ul></li>
					</c:when>
					<c:when test="${adminLogin }">
						<li><a href="#">관리자페이지</a>
							<ul class="dropdown-content">
							</ul></li>
					</c:when>
				</c:choose>
			</ul>
		</nav>
	</div>
	
	<section id="contentsArea">