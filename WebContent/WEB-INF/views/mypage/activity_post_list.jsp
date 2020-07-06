<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/layout/header1.jsp"></jsp:include>

<style type="text/css">
.positionR {
	position: relative;
}

.positionA {
	position: absolute;
	right: 0;
	top: 0;
	font-size: 19px;
    font-weight: 700;
	cursor: pointer;
}

.bgYellow {
	background: #ffbf00;
}

.fontBold {
	font-weight: 700;
}

.margin-top100 {
	margin-top: 100px;
}
</style>

<script type="text/javascript">
$(document).ready(function() {

$("#toSendBmList").on("click", function(){
	$(location).attr("href", "/anibuddy/activity/hbookmarklist")	
})

})
</script>


<main class="wrapper">
<div class="o3-content">
	<div class="pm-cont o3-cont positionR">
		<div class="o3-cont__title"><span class="glyphicon glyphicon-list"></span> 작성한 게시글 목록</div>
		<div class="pm_cont__send-title positionA" id="toSendBmList" >장소 선호 목록 </div>
	</div>
	<div class="o3-cont positionR">
		<div class="btn-group positionA">
			<button type="button" class="btn dropdown-toggle bgYellow fontBold"
				data-toggle="dropdown" aria-expanded="false">
				나눔&중고거래 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				
				<li><a href="#">나눔&중고거래</a></li>
				<li><a href="#">봉사</a></li>
				<li><a href="#">전문가QnA</a></li>
				<li><a href="#">자유 게시판</a></li>
			</ul>
		</div>
	</div>
	<div class="o3-cont margin-top100">
	<table class="oneonone">
		<tr>
			<th class="check"><input type="checkbox" id="checkAll"
				onclick="checkAll();" /></th>
			<th>등록 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>거래  상황</th>
		</tr>
<%-- 		<c:forEach items="${share }" var="share"> --%>
<!-- 			<tr> -->
<%-- 				<td><input type="checkbox" name="checkRow"value="${share.postNo }" /></td> --%>
<%-- 				<td><a href="/anibuddy/share/answer?post_no=${share.postNo }">${share.postNo }</a></td> --%>
<%-- 				<td><a href="/anibuddy/share/answer?post_no=${share.postNo }">${share.title }</a></td> --%>
<%-- 				<td><a href="/anibuddy/share/answer?post_no=${share.postNo }">${share.userNo }</a></td> --%>
<%-- 				<td><a href="/anibuddy/share/answer?post_no=${share.postNo }"> --%>
<%-- 						<fmt:formatDate value="${share.writeDate }" pattern="yyyy.MM.dd" /> --%>
<!-- 				</a></td> -->
<%-- 				<td><a href="/anibuddy/share/answer?post_no=${share.postNo }">${share.dealProgress }</a></td> --%>
<!-- 			</tr> -->
<%-- 		</c:forEach> --%>
	</table>
	</div>
</div>

<jsp:include page="/layout/mypage_sidebar.jsp"></jsp:include>
</main>
<!-- footer float 속성에 대한 피해 막기 -->
<div class="clear"></div>


<jsp:include page="/layout/footer.jsp"></jsp:include>
