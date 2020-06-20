<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/layout/header.jsp"></jsp:include>

<main class="wrapper">
<div class="mypage-content">

	<div class="card-wrapper">
	<div class="menu-card">
		<div class="menu-card__icon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>
		<div class="menu-card__title">
			<h4>회원정보</h4>
			<span>회원정보 수정하기</span>
		</div>
	</div>
	<div class="menu-card">
		<div class="menu-card__icon"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span></div>
		<div class="menu-card__title">
			<h4>활동 내역</h4>
			<span>내 활동내역 확인하기</span>
		</div>
	</div>
	</div>
	
	<div class="card-wrapper">
	<div class="menu-card">
		<div class="menu-card__icon"><span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span></div>
		<div class="menu-card__title">
			<h4>1:1 문의</h4>
			<span>관리자에게 문의하기</span>
		</div>
	</div>
	<div class="menu-card">
		<div class="menu-card__icon"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></div>
		<div class="menu-card__title">
			<h4>쪽지함</h4>
			<span>쪽지 송수신 확인하기</span>
		</div>
	</div>
	</div>
</div>

<div class="mypage-sidebar">
<ul>
	<li><a href="/anibuddy/mypage/main">마이페이지</a></li>
	<li><a href="#">회원정보</a></li>
	<li><a href="#">활동내역</a></li>
	<li><a href="#">쪽지함</a></li>
	<li><a href="#">1:1문의</a></li>
	<li><a href="#">회원탈퇴</a></li>
</ul>
</div>
</main>
<!-- footer float 속성에 대한 피해 막기 -->
<div class="clear"></div>


<jsp:include page="/layout/footer.jsp"></jsp:include>
