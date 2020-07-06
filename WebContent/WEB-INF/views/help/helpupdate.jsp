<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/layout/header.jsp" />

<!-- 스마트 에디터 라이브러리 추가 -->
<script type="text/javascript"
 src="<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnUpdate").click(function() {
		
		var message = confirm ("수정하시겠습니까?");
		if(message) {			
			//스마트에디터의 내용으로 <textarea>에 적용시키기
			submitContents($("#btnUpdate"));
			
			//form submit 수행
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

<h3>게시글 수정</h3>
<hr>

<div>
<form action="/anibuddy/help/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="postno" value="${viewBoard.postNo }" />

<table class="table table-bordered">
<tr><td class="info">아이디</td><td>${loginid }</td></tr>
<tr><td class="info">이름</td><td>${loginnick }</td></tr>
<tr><td class="info">제목</td><td><input type="text" name="title" style="width:100%" value="${viewBoard.title }"/></td></tr>
<tr><td class="info">판매 현황</td>
	<td><input type="radio" name="dealProgress" value="구인중" checked>&nbsp;구인중 &nbsp;&nbsp;
		<input type="radio" name="dealProgress" value="구인 완료" >&nbsp;구인 완료&nbsp;&nbsp;</td></tr>
<tr><td class="info" colspan="2">본문</td></tr>
<tr><td colspan="2">
	<textarea id="content" name="content" rows="10" cols="100">${viewBoard.content }</textarea>
</td></tr>
</table>

<div>
기존 첨부파일 : 
<a href="/anibuddy/upload/${boardFile.stored_name }"
 download="${boardFile.origin_name }">${boardFile.origin_name }</a>
</div>
<label>첨부파일 : <input type="file" name="file" /></label>

</form>
</div>

<div class="text-center">	
	<button type="button" id="btnUpdate" class="btn btn-info">수정 적용</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>
</div>

<jsp:include page="/layout/footer.jsp" />

<!-- 스마트 에디터를 생성하는 코드 -->
<!-- 스마트 에디터의 스킨을 입히는 코드 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",	//<textarea>의 id 를 입력
	sSkinURI: "/anibuddy/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2",
	htParams: {
		bUseToolbar: true, //툴바 사용여부
		bUseVerticalResizer: false, //입력창 크기 조절 바
		bUseModeChanger: true //글쓰기 모드 탭
	}
});

//<form>의 submit이 수행되면 스마트에디터의 내용이 <textarea>에 적용됨
function submitContents(elClickedObj) {
	// 에디터의 내용이 textarea에 적용된다.
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

	try {
		elClickedObj.form.submit();
	} catch (e) { }
}
</script>
