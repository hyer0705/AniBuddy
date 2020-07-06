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
<link rel="stylesheet" href="/anibuddy/css/layout.css" />

</head>
<body>
	<div class="header" style="width: 1000px;">
		<div id="icon">
			<a href="/anibuddy/"><img src="./img/logo.png"></a>
		</div>
		<div id="login">
			<c:choose>
				<c:when test="${adminLogin }">
					<span>${adminid } 님</span> | <span><a
						href="<%=request.getContextPath()%>/admin/logout">로그아웃</a></span>
				</c:when>
				<c:when test="${not login }">
					<span><a href="<%=request.getContextPath()%>/user/login">로그인</a></span> | <span><a
						href="<%=request.getContextPath()%>/user/join">회원가입</a></span>
				</c:when>
				<c:when test="${login }">
					<span>${loginnick } 님</span> | <span><a
						href="<%=request.getContextPath()%>/user/logout">로그아웃</a></span>
				</c:when>
			</c:choose>
		</div>
		<nav>
			<ul class="nav nav-pills nav-justified">
				<li><a href="/anibuddy/">홈</a>
					<ul class="dropdown-content">
						<li class="dummy">.</li>
						<li class="dummy">.</li>
						<li class="dummy">.</li>
						<li class="dummy">.</li>
					</ul></li>

				<!-- 기본적으로 놀거리 게시판으로 가게 했습니다. -->
				<li><a href="<%=request.getContextPath()%>/hangout/place">양육정보</a>
					<ul class="dropdown-content">
						<li><a href="<%=request.getContextPath()%>/hangout/place">놀거리</a></li>
						<li><a href="<%=request.getContextPath()%>/info/place">알거리</a></li>
						<li class="dummy">.</li>
						<li class="dummy">.</li>
					</ul></li>

				<li><a href="<%=request.getContextPath()%>/share/list">커뮤니티</a>
					<ul class="dropdown-content">
						<li><a href="<%=request.getContextPath()%>/share/list">나눔&중고거래</a></li>
						<li><a href="<%=request.getContextPath()%>/help/list">봉사</a></li>
						<li><a href="<%=request.getContextPath()%>/expertboard/list">전문가QnA</a></li>
						<li><a href="<%=request.getContextPath()%>/freeboard/list">자유
								게시판</a></li>
					</ul></li>
				<c:choose>
					<c:when test="${not adminLogin }">
						<li><a href="<%=request.getContextPath() %>/mypage/main">마이페이지</a>
							<ul class="dropdown-content">
								<li><a href="/anibuddy/mypage/userupdate">회원정보</a></li>
								<li><a href="/anibuddy/activity/postlist">활동 내역</a></li>
								<li><a href="<%=request.getContextPath() %>/mypage/pmlist">쪽지함</a></li>
								<li><a href="<%=request.getContextPath() %>/mypage/oneononelist">1:1 문의</a></li>
							</ul></li>
					</c:when>
					<c:when test="${adminLogin }">
						<li><a href="<%=request.getContextPath()%>/manage/user">관리자페이지</a>
							<ul class="dropdown-content">
								<li><a href="<%=request.getContextPath()%>/manage/user">회원
										관리</a></li>
								<li><a href="<%=request.getContextPath()%>/board/main">게시판
										관리</a></li>
								<li><a href="/anibuddy/oneonone/reply">QnA 관리</a></li>
								<li><a href="<%=request.getContextPath()%>/admin/mailcheck">메일
										관리</a></li>
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
								<p class="main-text text-large">Anibuddy</p>
								<p class="main-text">반려동물과 함께할 수 있는 장소를 찾으시나요?</p>
							</div>
						</div>
					</li>
					<li>
						<div class="main-image main-image02">
							<div class="main-text-box">
								<p class="main-text text-large">Anibuddy</p>
								<p class="main-text">반려동물에 대한 정보를 나누고 싶나요?</p>
							</div>
						</div>
					</li>
					<li>
						<div class="main-image main-image03">
							<div class="main-text-box">
								<p class="main-text text-large">Anibuddy</p>
								<p class="main-text">반려인들간의 소통을 원하시나요?</p>
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
						반려동물과 함께 즐길 수 있는 장소를 찾고 계신가요? 카페, 식당, 축제 등의 장소를 반려동물과 함께 이용할 수 있는지 확인해보세요. 병원, 펫샵 등 꼭 알아야 하는 장소와 유기동물 보호소와 같은 공간도 쉽게 검색하고 위치를 찾아볼 수 있습니다. 자주 가는 장소를 즐겨찾기에 추가하여 간편하게 이용해보세요. 다른 사람들에게도 추천하고싶은 장소가 있으신가요? 함께 나누고싶은 장소를 직접 추가하실 수 있습니다.
						반려동물과 함께 건강하고 다양한 문화생활을 즐겨보세요! 
					</p>
						<div class="btnMore">
							<a href="<%=request.getContextPath()%>/hangout/place">More</a>
						</div>
					</div>
					<div class="boxItem intro1Img"></div>

					<div class="boxItem intro2Img"></div>
					<div class="boxItem intro2"id="boardchart">
						<!-- 					</p> -->
						<div class="container" style="width: 500px;">
							<br>
							<h4>
								<strong>나눔 게시물 TOP3</strong>
							</h4>

							<table id="exboard" class="table table-hover table-condensed">
								<thead>
									<tr>
										<th style="width: 18%;">거래 현황</th>
										<th style="width: 40%;">제목</th>
										<th style="width: 14%;">아이디</th>
										<th style="width: 13%;">조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list3 }" var="share">
										<tr>
											<td>${share.dealProgress }</td>
											<td><c:if test="${share.depth >0 }">&nbsp;<span><img
														src="./img/RE.png" width="25px">&nbsp;</span>
												</c:if> <c:choose>
													<c:when test="${login }">
															<a href="/anibuddy/share/view?postno=${share.postno }">${share.title }</a>
													</c:when>
													<c:otherwise>
															<a href="#" onclick="alert('로그인 후 이용해주세요')">${share.title }</a>
														</c:otherwise>
												</c:choose></td>
											<td>${share.userid }</td>
											<td>${share.hit }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							
							<br>
							<h4>
								<strong>도움 게시물 TOP3</strong>
							</h4>
							<table id="frboard" class="table table-hover table-condensed">
								<thead>
									<tr>
										<th style="width: 18%;">모집 현황</th>
										<th style="width: 40%;">제목</th>
										<th style="width: 14%;">아이디</th>
										<th style="width: 13%;">조회수</th>
									</tr>
								</thead>
								<c:forEach items="${list4 }" var="help">
									<tr>
										<td>${help.dealProgress }</td>
										<td><c:choose>
												<c:when test="${login }">
													<a
														href="/anibuddy/help/view?postno=${help.postno }">${help.title }</a>
												</c:when>
														<c:otherwise>
															<a href="#" onclick="alert('로그인 후 이용해주세요')">${help.title }</a>
														</c:otherwise>
											</c:choose></td>
										<%-- 	<td><a href="/anibuddy/freeboard/view?postno=${freeboard.postno }" >${freeboard.title }</a></td> --%>
										<td>${help.userid }</td>
										<td>${help.hit }</td>
									</tr>
								</c:forEach>

							</table>
						
						<div class="btnMore">
							<a href="<%=request.getContextPath()%>/share/list">More</a>
						</div>
					</div>
				</div>
					<div class="boxItem intro3" id="boardchart">
						<!-- 					<p class="boxText"> -->
						<!-- 						<span class="fwBold">게시판</span>  -->
						<!-- 					</p> -->
						<div class="container" style="width: 500px;">
							<br>
							<h4>
								<strong>전문가 게시판 TOP3</strong>
							</h4>

							<table id="exboard" class="table table-hover table-condensed">
								<thead>
									<tr>
										<th style="width: 18%;">공개</th>
										<th style="width: 40%;">제목</th>
										<th style="width: 14%;">아이디</th>
										<th style="width: 13%;">조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list }" var="expertboard">
										<tr>
											<td>${expertboard.range }</td>
											<td><c:if test="${expertboard.depth >0 }">&nbsp;<span><img
														src="./img/RE.png" width="25px">&nbsp;</span>
												</c:if> <c:choose>
													<c:when test="${login }">
														<c:choose>
															<c:when test="${expertboard.range eq '전문가 공개' }">
																<c:choose>
																	<c:when test="${isexpert eq 'Y'}">
																		<a
																			href="/anibuddy/expertboard/view?postno=${expertboard.postno }">${expertboard.title }</a>
																	</c:when>
																	<c:otherwise>
																		<c:choose>
																			<c:when test="${expertboard.userid eq loginid }">
																				<a
																					href="/anibuddy/expertboard/view?postno=${expertboard.postno }">${expertboard.title }</a>
																			</c:when>
																			<c:otherwise>
																				<a href="#" onclick="alert('전문가가 아닙니다!')">${expertboard.title }</a>
																			</c:otherwise>
																		</c:choose>
																	</c:otherwise>
																</c:choose>
															</c:when>
															<c:otherwise>
																<a
																	href="/anibuddy/expertboard/view?postno=${expertboard.postno }">${expertboard.title }</a>
															</c:otherwise>
														</c:choose>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${expertboard.range eq '전체 공개' }">
																<a
																	href="/anibuddy/expertboard/view?postno=${expertboard.postno }">${expertboard.title }</a>
															</c:when>
															<c:otherwise>
																<a href="#" onclick="alert('로그인 후 이용해주세요')">${expertboard.title }</a>
															</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose></td>
											<td>${expertboard.userid }</td>
											<td>${expertboard.hit }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<br>
							<h4>
								<strong>자유 게시판 TOP3</strong>
							</h4>
							<table id="frboard" class="table table-hover table-condensed">
								<thead>
									<tr>
										<th style="width: 18%;">공개범위</th>
										<th style="width: 40%;">제목</th>
										<th style="width: 14%;">아이디</th>
										<th style="width: 13%;">조회수</th>
									</tr>
								</thead>
								<c:forEach items="${list2 }" var="freeboard">
									<tr>
										<td>${freeboard.range }</td>
										<td><c:choose>
												<c:when test="${login }">
													<a
														href="/anibuddy/freeboard/view?postno=${freeboard.postno }">${freeboard.title }</a>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${freeboard.range eq '전체 공개' }">
															<a
																href="/anibuddy/freeboard/view?postno=${freeboard.postno }">${freeboard.title }</a>
														</c:when>
														<c:otherwise>
															<a href="#" onclick="alert('로그인 후 이용해주세요')">${freeboard.title }</a>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose></td>
										<%-- 	<td><a href="/anibuddy/freeboard/view?postno=${freeboard.postno }" >${freeboard.title }</a></td> --%>
										<td>${freeboard.userid }</td>
										<td>${freeboard.hit }</td>
									</tr>
								</c:forEach>

							</table>

						</div>
						<div class="btnMore">
							<a href="<%=request.getContextPath()%>/expertboard/list">More</a>
						</div>
					</div>
					<div class="boxItem intro3Img"></div>

				</article>
			</div>

			<div>
				<article class="menu">
					<div id="menu1">
						<img src="./img/call.png" alt="전화문의" class="img-circle img-call">
						<p>
							전화문의 : 070-0000-0000<br> 상담가능시간 : 09:00 ~ 18:00
						</p>
					</div>
					<div id="menu2">
						<img src="./img/mail.jpg" alt="메일문의" class="img-circle img-mail">
						<p>
							메일문의 : anibuddy@ani.co.kr<br> 상담가능시간 : 00:00 ~ 23:59
						</p>
					</div>
					<div id="menu3" onclick="window.open('http://www.zooseyo.or.kr')">
						<a href=""><img src="./img/heart.png" alt="유기동물 보호소"
							class="img-circle"></a>
						<p>
							당신의 연락을 기다립니다<br> <b>유기동물 보호소</b>
						</p>
					</div>
					<div id="menu4"onclick="window.open('./img/hospitalinfo.jpg', '병원정보','width=500, height=960, scrollbars=yes, location=no, status=no')">
						<a href=""><img src="./img/hospital.png" alt="24시간 동물병원"
							class="img-circle"></a>
						<p>
							24시간 동물병원<br> <b>바로가기</b>
						</p>
					</div>

				</article>

			</div>

		</section>

		<div id="banner">
			<div id="hospital"
				onclick="window.open('./img/hospitalinfo.jpg', '병원정보','width=500, height=960, scrollbars=yes, location=no, status=no')"></div>
			<div id="safety" onclick="window.open('http://www.zooseyo.or.kr')"></div>
			<div>
				<img class="banner-image" src="./img/banner.jpg" width="105" />
			</div>
			<div id="topbutton">
				<img class="top-button" id="top-button" src="./img/top.png"
					width="50" />
			</div>
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