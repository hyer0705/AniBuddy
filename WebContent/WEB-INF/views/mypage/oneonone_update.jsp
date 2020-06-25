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
		return;
	}

	try {
		// <form>태그의 submit 수행
		elClickedObj.form.submit();
	} catch (e) { }
}


</script>
<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnO3").click(function() {
		var form = document.fm;
		
		if(!form.title.value) {
			alert("제목을 적어주세요!")
			form.title.focus();
			return;
		}
		
        
		var message = confirm ("수정하시겠습니까?");	
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
	$("#btnO3Cancel").click(function() {
		history.go(-1)
	});
	

	
});
</script>

<style type="text/css">
#content
, .width98 {
	width: 98%;
}
</style>

<div class="container" style="width: 900px;">

<h3>1:1문의 수정하기</h3>
<hr>

<div>
<form action="/anibuddy/mypage/oneononeUpdate" method="post" enctype="application/x-www-form-urlencoded" name="fm">
<input type="hidden" name="oneonone_no" value="${detail.oneononeNo }" />
<table class="">
<tr><td class="bgBlue">아이디</td><td>${loginid } 님</td></tr>
<tr><td class="bgBlue">제목</td><td><input class="width98" type="text" id="title" name="title" value="${detail.title }"/></td></tr>
<tr><td class="" colspan="2">본문</td></tr>
<tr><td colspan="2"><textarea id="content" name="content">${detail.content }</textarea></td></tr>
</table>

</form>
</div>

<div class="text-center margin-top20">	
	<button type="button" id="btnO3" class="btn-common btn-write" style="margin-top: 0;" >수정하기</button>
	<button type="button" id="btnO3Cancel" class="btn-common btn-cancel">취소</button>
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