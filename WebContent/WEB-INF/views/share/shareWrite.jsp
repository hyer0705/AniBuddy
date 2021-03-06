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
	
	//에디터의 내용을 #content에 반영
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		// <form>태그의 submit 수행
		elClickedObj.form.submit();
	} catch (e) { }
}
</script>
<script type="text/javascript">
$(document).ready(function() {
	
	console.log(document.fm)
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		var form = document.fm;
		
		if(!form.title.value) {
			alert("제목을 적어주세요!")
			form.title.focus();
			return;
		}
		var message = confirm ("작성하시겠습니까?");	
		if(message) {			
		//스마트에디터의 내용을 <textarea>에 적용하는 함수 호출
		submitContents( $("#btnWrite") );
		
		//<form>태그 submit
		$("form").submit();
		} else {
			return false;
		}
		
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		var message = confirm("작성을 취소하시겠습니까?");
		
		if(message) {			
			history.go(-1);
		} else {
			return false;
		}
	});
	
});
</script>

<style type="text/css">
#content {
	width: 98%;
}
</style>

<div class="container" style="width: 900px;">

<h3>게시글 쓰기</h3>
<hr>

<div>
<form action="/anibuddy/share/write" method="post" enctype="multipart/form-data" name="fm">

<table class="table table-bordered">
<tr><td class="info">아이디</td><td>${loginid }</td></tr>
<tr><td class="info">이름</td><td>${loginnick }</td></tr>
<tr><td class="info">제목</td><td><input type="text" name="title" style="width:100%"/></td></tr>
<tr><td class="info">판매 현황</td>
	<td><input type="radio" name="dealProgress" value="판매중" checked>&nbsp;거래중 &nbsp;&nbsp;
		<input type="radio" name="dealProgress" value="판매 완료" >&nbsp;거래 완료&nbsp;&nbsp;</td></tr>
<tr><td class="info" colspan="2">본문</td></tr>
<tr><td colspan="2"><textarea id="content" name="content"></textarea></td></tr>
</table>

첨부파일 <input type="file" name="file" />
</form>
</div>

<div class="text-center">	
	<button type="button" id="btnWrite" class="btn btn-info">작성</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>
</div>

<!-- 스마트 에디터 적용하는 코드 -->
<!-- <textarea>에 스마트에디터의 스킨을 입히는 코드 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //에디터가 적용되는 <textarea>의 id를 입력
	sSkinURI: "/anibuddy/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})
</script>

<jsp:include page="/layout/footer.jsp"></jsp:include>