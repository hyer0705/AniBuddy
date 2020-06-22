<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AniBuddy</title>

<link rel="stylesheet" href="./css/style.css" />
<link rel="stylesheet" href="./css/style_main.css" />
<link rel="stylesheet" href="./css/jquery.bxslider.css" />
<!-- 메인화면 슬라이더 -->
<script src="./js/jquery-3.4.1.js"></script>
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script> -->
<script src="./js/jquery.bxslider.js"></script>


<!-- taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 부트스트랩 3.3.2 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- layout.css -->
<link rel="stylesheet" href="./css/layout.css" />

</head>
<body>
	<div class="header" style="width:1000px;">
		<div id="icon">
			<a href="/anibuddy/"><img src="./img/logo.png"></a>
		</div>
		<div id="login">
		<c:choose>
			<c:when test="${adminLogin }">
				<span>${adminid } 님</span> | <span><a href="<%=request.getContextPath()%>/admin/logout">로그아웃</a></span>
			</c:when>
			<c:when test="${not login }">
				<span><a href="<%=request.getContextPath() %>/user/login">로그인</a></span> | <span><a href="<%=request.getContextPath()%>/user/join">회원가입</a></span>
			</c:when>
			<c:when test="${login }">
				<span>${loginnick } 님</span> | <span><a href="<%=request.getContextPath() %>/user/logout">로그아웃</a></span>
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
						<li><a href="<%=request.getContextPath() %>/expertboard/list">전문가QnA</a></li>
						<li><a href="<%=request.getContextPath() %>/freeboard/list">자유 게시판</a></li>
					</ul></li>
				<c:choose>
					<c:when test="${not adminLogin }">
						<li><a href="<%=request.getContextPath() %>/mypage/main">마이페이지</a>
							<ul class="dropdown-content">
								<li><a href="/anibuddy/mypage/userupdate">회원정보</a></li>
								<li><a href="#">활동 내역</a></li>
								<li><a href="#">쪽지함</a></li>
								<li><a href="#">1:1 문의</a></li>
							</ul></li>
					</c:when>
					<c:when test="${adminLogin }">
						<li><a href="<%=request.getContextPath() %>/admin/main">관리자페이지</a>
							<ul class="dropdown-content">
								<li><a href="#">회원 관리</a></li>
								<li><a href="#">게시판 관리</a></li>
								<li><a href="/anibuddy/oneonone/reply">QnA 관리</a></li>
								<li><a href="#">메일 관리</a></li>
							</ul></li>
					</c:when>
				</c:choose>
			</ul>
		</nav>
	</div>

<div id="wrapper">
	<section id="contentsArea">
		<!-- 메인 이미지 -->
		<div class="main-image-group">
			<ul class="bxslider">
				<li>
					<div class="main-image">
						<div class="main-text-box">
							<p class="main-text text-large">분양/홍보 이미지1</p>
							<p class="main-text">이미지 설명 텍스트</p>
						</div>
					</div>
				</li>
				<li>
					<div class="main-image main-image02">
						<div class="main-text-box">
							<p class="main-text text-large">분양/홍보 이미지2</p>
							<p class="main-text">이미지 설명 텍스트</p>
						</div>
					</div>
				</li>
				<li>
					<div class="main-image main-image03">
						<div class="main-text-box">
							<p class="main-text text-large">분양/홍보 이미지3</p>
							<p class="main-text">이미지 설명 텍스트</p>
						</div>
					</div>
				</li>
			</ul>
		</div>
			
		<!-- 메인 이미지 -->
		<div class="contentsAreaInner">
			<article class="box">
			
				<div class="boxItem intro1">
					<p class="boxText">
						<span class="fwBold">양육 정보</span> 
						반려동물과 함께 즐길 수 있는 장소를 찾고 계신가요? 
						이름과, 없이 오는 했던 라이너 하나에 버리었습니다. 
						아직 가을로 시인의 어머님, 속의 밤이 쓸쓸함과 나는 이제 있습니다. 
						않은 쉬이 벌써 때 애기 쓸쓸함과 위에도 봅니다. 것은 이런 나는 밤을 별들을 때 라이너 묻힌 가난한 봅니다.
						<br>오른쪽 하단에 <b>More</b> 버튼을 눌러보세요!				
					</p>
					<div class="btnMore"><a href="#">More</a></div>
				</div>
				<div class="boxItem intro1Img"></div>
				
				<div class="boxItem intro2Img"></div>
				<div class="boxItem intro2">
					<p class="boxText">
						<span class="fwBold">커뮤니티</span> 
						커뮤니티 게시판 TOP 5 영역
					</p>
					<div class="btnMore"><a href="#">More</a></div>
				</div>
				
				<div class="boxItem intro3">
					<p class="boxText">
						<span class="fwBold">게시판</span> 
						게시판 TOP 5 영역
					</p>
					<div class="btnMore"><a href="#">More</a></div>
				</div>
				<div class="boxItem intro3Img"></div>
				
			</article>
		</div>
		
		<div>
			<article class="menu">
					<div id="menu1">
						<img src="./img/call.png" alt="전화문의" class="img-circle img-call">
						<p>전화문의 : 070-0000-0000<br>
						상담가능시간 : 09:00 ~ 18:00</p>
					</div>
					<div id="menu2">
						<img src="./img/mail.jpg" alt="메일문의" class="img-circle img-mail">
						<p>메일문의 : anibuddy@ani.co.kr<br>
						상담가능시간 : 00:00 ~ 23:59</p>
					</div>
					<div id="menu3">
						<a href=""><img src="./img/heart.png" alt="유기동물 보호소" class="img-circle"></a>
						<p>당신의 연락을 기다립니다<br>
						<b>유기동물 보호소</b></p>
					</div>
					<div id="menu4">
						<a href=""><img src="./img/hospital.png" alt="24시간 동물병원" class="img-circle"></a>
						<p>24시간 동물병원<br>
						<b>바로가기</b></p>
					</div>
			
			</article>
		
		</div>
			
	</section>

<div id="banner">
	<img class="banner-image" src="./img/banner.jpg" />
	<img class="top-button" id="top-button" src="./img/top.png" />
</div>


</div>



<script type="text/javascript">
	$(function() {
		$('.bxslider').bxSlider({
			auto : true, // 자동으로 애니메이션 시작 여부 (true면 자동 슬라이드, false는 자동 슬라이드 안함)
			speed : 200, // 애니메이션 속도
			pause : 4000, // 애니메이션 유지 시간 (1000은 1초)
			autoControls : false, // 시작 및 중지버튼 여부 (true면 보여짐, false는 안보임)
			pager : true, // 페이지 표시 여부 (true면 보여짐, false는 안보임)
			mode : 'fade', // 슬라이드 모드 ('fade', 'horizontal', 'vertical' 이 있음)
			captions : false
			// img 태그에 사용되는 title 속성을 이용해서 이미지 위에 텍스트를 넣을 수 있음 (true면 보여짐, false는 안보임)
		});
		
		$("#top-button").click(function(){
			$('html,body').animate({
				scrollTop : 0
			}, 400)
			return false;
		})
	});

	$(window).scroll(function() {
		  
		if($(this).scrollTop() > 150) {
			$("#banner").css('position','fixed');
			$("#banner").css('background','#99d4d9');
		}
		else {
			$("#banner").css('position','relative');
			$("#banner").css('background','#99d4d9');
		}
	});
	
	
	
	
</script>



<c:import url="./layout/footer.jsp" />

