<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/layout/headermanage.jsp" />
<script type="text/javascript">
$(document).ready(function(){
	
	$("#btnMail").click(function(){
		var message = confirm("메일을 보내시겠습니까?");
		if(message){
		$("form").submit()
		} else{
			return false;
		}
	})
	
	$("#btnCancel").click(function() {
		var message = confirm("작성을 취소하시겠습니까?");
		
		if(message) {			
			history.go(-1);
		} else {
			return false;
		}
	});
})
</script>
<div class="container" style="width: 900px;">
<h3>개별 메일 보내기</h3><br>
<hr>
<div>
<form action="<%=request.getContextPath() %>/admin/onemail" method="post">
<table class="table table-bordered">
<tr><td class="info">사용자 메일</td><td><input type="email" name="email" id="email"  style="width:100%"/></td></tr>
<tr><td class="info">메일 제목</td><td><input type="text" name="title" id="title"  style="width:100%"/></td></tr>
<tr><td class="info">메일 내용</td><td><textarea id="content" name="content" style="width:100%"></textarea></td></tr>
</table>
</form>
</div>

<div class="text-center">
	<button type="button" id="btnMail" class="btn btn-default">보내기</button>
	<button type="button" id="btnCancel" class="btn btn-default">취소</button>
</div>
</div>

<jsp:include page="/layout/footer.jsp"></jsp:include>