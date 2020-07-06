<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/layout/header1.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function() {
	$("#toUpdate").on("click", function(){
// 		console.log("회원정보 clicked")
		$(location).attr("href", "/anibuddy/mypage/userupdate")
	})
	
	$("#toOneOnOne").on("click", function(){
// 		console.log("1:1문의하기 clicked")
		$(location).attr("href", "/anibuddy/mypage/oneononelist")
	})

	// 쪽지함 div 영역 클릭시
	$("#toPostMessage").on("click", function(){
// 		console.log("쪽지함 clicked")
		$(location).attr("href", "/anibuddy/mypage/pmlist")
	})

	$("#toActivity").on("click", function(){
// 		console.log("활동내역 clicked")
		$(location).attr("href", "/anibuddy/activity/postlist")
	})
	
})
</script>

<main class="wrapper">
<div class="mypage-content">

	<div class="card-wrapper">
	<div class="menu-card" id="toUpdate">
		<div class="menu-card__icon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>
		<div class="menu-card__title">
			<h4>회원정보</h4>
			<span>회원정보 수정하기</span>
		</div>
	</div>
	<div class="menu-card" id="toActivity">
		<div class="menu-card__icon"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span></div>
		<div class="menu-card__title">
			<h4>활동 내역</h4>
			<span>내 활동내역 확인하기</span>
		</div>
	</div>
	</div>
	
	<div class="card-wrapper">
	<div class="menu-card" id="toOneOnOne">
		<div class="menu-card__icon"><span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span></div>
		<div class="menu-card__title">
			<h4>1:1 문의</h4>
			<span>관리자에게 문의하기</span>
		</div>
	</div>
	<div class="menu-card" id="toPostMessage">
		<div class="menu-card__icon"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></div>
		<div class="menu-card__title">
			<h4>쪽지함</h4>
			<span>쪽지 송수신 확인하기</span>
		</div>
	</div>
	</div>
</div>

<jsp:include page="/layout/mypage_sidebar.jsp"></jsp:include>
</main>
<!-- footer float 속성에 대한 피해 막기 -->
<div class="clear"></div>


<jsp:include page="/layout/footer.jsp"></jsp:include>
