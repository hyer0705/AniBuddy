<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/layout/header.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnSendPm").click(function() {
		var form = document.fm;
		
		if(!form.title.value) {
			alert("제목을 적어주세요!")
			form.title.focus();
			return;
		}
		
		if(!form.content.value){
			alert("내용을 적어주세요!")
			form.content.focus()
			return;
		}
		
        
		var message = confirm ("작성하시겠습니까?");	
		if(message) {		
			
			//<form>태그 submit
			$("form").submit();
		
		} else {
			return false;
		}
		
	});
	
	//취소버튼 동작
	$("#btnPmCancel").click(function() {
		history.go(-1)
	});
	

	
});
</script>

<style type="text/css">
#content {
    width: 98%;
    height: 300px;
}

.width98 {
	width: 98%;
}
</style>

<div class="container" style="width: 900px;">

<h3>쪽지 작성하기</h3>
<hr>

<div>
<form action="/anibuddy/mypage/sendtoanonymous" method="post" enctype="application/x-www-form-urlencoded" id="fm" name="fm">

<table class="">
<tr>
	<td class="bgBlue">받는사람</td>
	<td><input class="width98" type="text" name="recipient_id" placeholder="받는 사람 아이디를 입력해주세요" /></td>
</tr>
<tr><td class="bgBlue">쪽지 제목</td><td><input class="width98" type="text" id="title" name="title"/></td></tr>
<tr><td class="" colspan="2">쪽지 본문</td></tr>
<tr><td colspan="2"><textarea id="content" name="content"></textarea></td></tr>
<!-- <tr><td colspan="2"><span id="cntChar">0 / 500자</span></td></tr> -->
</table>

<input type="hidden" name="recipient_no" value="${recipient.userNo }" />
</form>
</div>

<div class="text-center margin-top20">	
	<button type="button" id="btnSendPm" class="btn-common btn-write" >쪽지 보내기</button>
	<button type="button" id="btnPmCancel" class="btn-common btn-cancel">취소</button>
</div>
</div>

<jsp:include page="/layout/footer.jsp"></jsp:include>