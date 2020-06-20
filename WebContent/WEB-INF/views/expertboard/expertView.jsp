<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/layout/header.jsp"></jsp:include>


<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/expertboard/list");
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/expertboard/update?postno=${viewBoard.postno }");
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
		var message = confirm ("삭제하시겠습니까?");	
		if(message){
			$(location).attr("href", "<%=request.getContextPath() %>/expertboard/delete?postno=${viewBoard.postno }");
		} else {
			return false;
		}
	});
	
	//답글버튼 동작
	$("#btnReply").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/expertboard/reply?postno=${viewBoard.postno }");
	});
	
});
</script>



<script type="text/javascript">
$(document).ready(function() {
	// 댓글 입력
	$("#btnCommInsert").click(function() {
		// 게시글 번호.... ${viewBoard.boardno }
	//		console.log($("#commentWriter").val());
	//		console.log($("#commentContent").val());
		
		$form = $("<form>").attr({
			action: "<%=request.getContextPath() %>/expertcomment/insert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"postno",
				value:"${viewBoard.postno }"
			})
		).append(
			$("<input>").attr({
				type:"hidden",
				name:"userid",
				value:"${loginid }"
			})
		).append(
			$("<textarea>")
				.attr("name", "content")
				.css("display", "none")
				.text($("#commentContent").val())
		);
		$(document.body).append($form);
		$form.submit();
		
	});
	
});

//댓글 삭제
function deleteComment(commentNo) {
	$.ajax({
		type: "post"
		, url: "<%=request.getContextPath() %>/expertcomment/delete"
		, dataType: "json"
		, data: {
			commentNo: commentNo
		}
		, success: function(data){
			var message = confirm ("삭제하시겠습니까?");
			if(message) {				
				if(data.success) {
					
					$("[data-commentno='"+commentNo+"']").remove();
					
				} else {
					alert("댓글 삭제 실패");
				}
			} else {
				return false;
			}
		}
		, error: function() {
			console.log("error");
		}
	});
}
</script>

<style type="text/css">
.container {
margin-top: 70px;
}

</style>

<div class="container" style="width: 900px;">
<br><br><br><br>
<h1>전문가 QnA</h1>
<hr>

<input type="hidden" name="postno" value="${viewBoard.postno }" />
<input type="hidden" name="userno" value="<%=session.getAttribute("userno") %>" />

<table class="table table-bordered" >
<tr>
<td class="danger">글번호</td><td>${viewBoard.postno }</td>
<td class="danger">공개범위</td><td>${viewBoard.range }</td>
</tr>

<tr>
<td class="danger">제목</td><td colspan="3">${viewBoard.title }</td>
</tr>

<tr>
<td class="danger">아이디</td><td>${viewBoard.userid }</td>
<td class="danger">닉네임</td><td>${viewBoard.usernick }</td>
</tr>

<tr>
<td class="danger">조회수</td><td>${viewBoard.hit }</td>
<td class="danger">작성일</td><td>${viewBoard.writedate }</td>
</tr>


<tr><td class="danger"  colspan="4">본문</td></tr>
<tr></tr>
<tr><td colspan="4">${viewBoard.content }</td></tr>

</table>



 <div>
<a href="<%=request.getContextPath() %>/upload/${boardFile.storedname }" download="${boardFile.originname }">${boardFile.originname } </a>
</div> 

<div class="text-center">	
	<button onclick="location.href='<%=request.getContextPath() %>/expertboard/list'" id="btnList" class="btn btn-default btn-sm">목록</button>
	
 	<c:if test="${userno eq viewBoard.userno }"> 
 		<button id="btnUpdate" class="btn btn-info btn-sm">수정</button> 
 		<button id="btnDelete" class="btn btn-danger btn-sm">삭제</button> 
	</c:if> 
	
	<c:if test="${isexpert eq 'Y' }">
		<button id="btnReply" class="btn btn-default btn-sm">답변달기</button>
	</c:if>
</div>
<br>

<!-- 댓글 처리 -->
<div>

<hr>
<br><br>
<!-- 비로그인상태 -->
<c:if test="${not login }">
<strong>로그인이 필요합니다</strong><br>
<button onclick='location.href="<%=request.getContextPath() %>/user/login";'>로그인</button>
<button onclick='location.href="<%=request.getContextPath() %>/user/join";'>회원가입</button>
</c:if>

<!-- 로그인상태 -->
<c:if test="${login }">
<!-- 댓글 입력 -->
<div class="form-inline text-center">
	<input type="text" size="10" class="form-control" id="commentWriter" value="${loginid }" readonly="readonly"/>
	<textarea rows="2" cols="60" class="form-control" id="commentContent" style="height: 34px;"></textarea>
	<button id="btnCommInsert" class="btn">입력</button>
</div>	<!-- 댓글 입력 end -->
</c:if>
<br><br>
<!-- 댓글 리스트 -->
<table class="table table-striped table-hover table-condensed">
<thead>
<tr>
<!-- 	<th style="width: 5%;">번호</th> -->
	<th style="width: 10%;">작성자</th>
	<th style="width: 55%; text-align: center;'">댓글</th>
	<th style="width: 20%;">작성일</th>
	<th style="width: 5%;"></th>
</tr>
</thead>
<tbody id="commentBody">
<c:forEach items="${commentList }" var="comment">
<tr data-commentno="${comment.commentno }">
<%-- 	<td>${comment.rnum }</td> --%>
	<td>${comment.userid }</td><!-- 닉네임으로 해도 좋음 -->
	<td>${comment.content }</td>
	<td><fmt:formatDate value="${comment.writedate }" pattern="yy-MM-dd" /></td> 
	<td>
		<c:if test="${userno eq comment.userno }">
		<button class="btn btn-default btn-xs"
			onclick="deleteComment(${comment.commentno });">삭제</button>
		</c:if>
	</td>
</tr>
</c:forEach>
</tbody>
</table>	<!-- 댓글 리스트 end -->

</div>	<!-- 댓글 처리 end -->


</div>

<jsp:include page="/layout/footer.jsp"></jsp:include>

