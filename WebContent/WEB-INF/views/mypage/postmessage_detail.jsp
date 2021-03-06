<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/layout/header1.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function(){
	
	// 목록 버튼 클릭시
	$("#btnToList").on("click", function(){
		$(location).attr("href", "/anibuddy/mypage/pmlist")
	})
	
	// 쪽지 보내기 버튼 클릭시
	$("#btnSendPm").on("click", function(){
		$(location).attr("href", "/anibuddy/mypage/sendpm?pm_recipient_id=" + $("#recipient").val())
	})
	
	// 쪽지 삭제 버튼 클릭시
	$("#btnPMDelete").on("click", function(){
		$(location).attr("href", "/anibuddy/mypage/deletepm?pm_no=" + $("#pmNo").val())
	})
	
})
</script>

<main class="wrapper">
<div class="o3-content">
	<div class="o3-cont">
		<div class="o3-cont__title">받은쪽지 상세보기</div>
	</div>
	<div class="o3-cont">
		<table class="" >
		<tr>
		<td class="bgBlue">제목</td><td colspan="3">${pm.title }</td>
		</tr>
	
		<tr>		
		<td class="bgBlue">받은 날짜</td><td colspan="3">${pm.sendDate }</td>
		</tr>
		
		<tr>		
		<td class="bgBlue">보낸 사람</td>
		<td colspan="3">
			${sender }
			<input type="hidden" id="recipient" value="${pm.pmSenderId }"/>
		</td>
		</tr>
		
		<tr><td class="danger"  colspan="4">쪽지 내용</td></tr>
		<tr><td class="height300 text-left" colspan="4">${pm.content }</td></tr>
		
		</table>
	</div>
	<div class="text-center margin-top20">	
		<button type="button" id="btnSendPm" class="btn-common btn-write" style="margin-top: 0;" >쪽지보내기</button>
		<button type="button" id="btnToList" class="btn-common btn-cancel">목록</button>
		<button type="button" id="btnPMDelete" class="btn-common btn-delete" style="margin-top: 0;" >쪽지삭제</button>
	</div>
	<input type="hidden" id="pmNo" value="${pm.pmNo }" />
</div>


<jsp:include page="/layout/mypage_sidebar.jsp"></jsp:include>
</main>

<!-- footer float 속성에 대한 피해 막기 -->
<div class="clear"></div>
<jsp:include page="/layout/footer.jsp"></jsp:include>