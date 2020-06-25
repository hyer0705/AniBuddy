<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/layout/header.jsp"></jsp:include>


<!-- 네이버 스마트 에디터2 라이브러리 -->
<script type="text/javascript" src="/anibuddy/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<!-- <form>태그의 submit을 수행하면 editor에 작성한 내용을 <textarea>에 반영 -->
<script type="text/javascript">
function submitContents(elClickedObj) {
	
	var elClickedObj = $("#fm")
	
	//에디터의 내용을 #content에 반영
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	var con = $("#content").val();
	
// 	var con = document.getElementById("content").value;
	if(con == "" || con == null || con == '&nbsp;' || con == '<p>&nbsp;</p>' || con == '<br>' || con == '<br/>') {
		alert("내용을 입력해주세요~")
		oEditors.getById["content"].exec("FOCUS");
		return false;
	}
	
	// 일단 나중으로 미루기
// 	if( con.length > 500 ){
// 		alert("쪽지는 500자 제한입니다. - " + con.length)
// 		oEditors.getById["content"].exec("FOCUS");
// 		return false;
// 	}

	try {
		// <form>태그의 submit 수행
		elClickedObj.form.submit();
	} catch (e) { }
}


</script>
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
		
        
		var message = confirm ("작성하시겠습니까?");	
		if(message) {		
			
// 		스마트에디터의 내용을 <textarea>에 적용하는 함수 호출
// 		submitContents( $("#btnWrite") );
		submitContents();
		
		//<form>태그 submit
		$("form").submit();
		
		} else {
			return false;
		}
		
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1)
	});
	

	
});
</script>

<style type="text/css">
#content {
	width: 98%;
}
</style>

<div class="container" style="width: 900px;">

<h3>쪽지 작성하기</h3>
<hr>

<div>
<form action="/anibuddy/mypage/sendtoanonymous" method="post" enctype="application/x-www-form-urlencoded" id="fm" name="fm">

<table class="table table-bordered">
<tr>
	<td class="info">받는사람</td>
	<td><input type="text" name="recipient_id" placeholder="받는 사람 아이디를 입력해주세요" style="width:100%" /></td>
</tr>
<tr><td class="info">쪽지 제목</td><td><input type="text" id="title" name="title" style="width:100%"/></td></tr>
<tr><td class="info" colspan="2">쪽지 본문</td></tr>
<tr><td colspan="2"><textarea id="content" name="content"></textarea></td></tr>
<!-- <tr><td colspan="2"><span id="cntChar">0 / 500자</span></td></tr> -->
</table>

<input type="hidden" name="recipient_no" value="${recipient.userNo }" />
</form>
</div>

<div class="text-center">	
	<button type="button" id="btnSendPm" class="btn btn-info" style="margin-top: 0;" >쪽지 보내기</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>
</div>

<!-- 스마트 에디터 적용하는 코드 -->
<!-- <textarea>에 스마트 에디터의 스킨을 입히는 코드 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", // 에디터가 적용되는 <textarea>의 id를 입력
	sSkinURI: "/anibuddy/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})
</script>

<jsp:include page="/layout/footer.jsp"></jsp:include>