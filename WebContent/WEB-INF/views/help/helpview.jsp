<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/layout/header.jsp"></jsp:include>


<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		history.go(-1);
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		$(location).attr("href", "/anibuddy/help/update?postno=${viewhelp.postNo }");
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
		var message = confirm ("삭제하시겠습니까?");	
		if(message){
			$(location).attr("href", "/anibuddy/help/delete?postno=${viewhelp.postNo }");
		} else {
			return false;
		}
	});
	
});
</script>

<script type="text/javascript">
$(document).ready(function(){
	if(${isRecommendhelp}){
		$("#btnRecommend")
		.html('<img src="../img/heart2.jpg">')
	}else{
		$("#btnRecommend")
		.html('<img src="../img/heart1.png">')
	}
	
	$("#btnRecommend").click(function(){
		$.ajax({
			type: "get"
			, url: "/anibuddy/help/recommend"
			, data: {"postno" : '${viewhelp.postNo}'}
			, dataType : "json"
			, success : function( data ){
// 				console.log("성공");
// 				console.log(data);
				
				if (data.result ){
					$("#btnRecommend")
					.html('<img src="../img/heart2.jpg">')
			
				} else{
					$("#btnRecommend")
					.html('<img src="../img/heart1.png">')
					
				}
				
			}
			,error:function(){
				console.log("실패")
			}
				
		})
	})
});

</script>


<script type="text/javascript">
$(document).ready(function() {
	// 댓글 입력
	$("#btnCommInsert").click(function() {

	//		console.log($("#commentWriter").val());
	//		console.log($("#commentContent").val());
		
		$form = $("<form>").attr({
			action: "/anibuddy/helpcomment/insert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"postno",
				value:"${viewhelp.postNo }"
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
		, url: "/anibuddy/helpcomment/delete"
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
#btnRecommend{
	height : 60px;
	width : 60px;
	padding :0;
	background-color : white; 
}
</style>

<div class="container" style="width: 900px;">
	<br>
	<br>
	<br>
	<br>
	<h1>봉사</h1>
	<hr>

	<input type="hidden" name="postNo" value="${viewhelp.postNo }" /> <input
		type="hidden" name="userNo"
		value="<%=session.getAttribute("userno")%>" />

	<table class="table table-bordered">
		<tr>
			<td class="danger">글번호</td>
			<td>${viewhelp.postNo }</td>
			<td class="danger">거래 현황</td>
			<td>${viewhelp.dealProgress }</td>
		</tr>

		<tr>
			<td class="danger">제목</td>
			<td colspan="3">${viewhelp.title }</td>
		</tr>

		<tr>
			<td class="danger">아이디</td>
			<td>${viewhelp.userid }</td>
			<td class="danger">닉네임</td>
			<td>${viewhelp.usernick }</td>
		</tr>

		<tr>
			<td class="danger">조회수</td>
			<td>${viewhelp.hit }</td>
			<td class="danger">작성일</td>
			<td>${viewhelp.writeDate }</td>
		</tr>


		
<tr><td class="danger"  colspan="4">본문</td></tr>
<tr></tr>
<tr><td colspan="4">
<div class="text-center">
<c:if test="${helpFile.fileno ne null}"><img src="<%=request.getContextPath() %>/upload/${helpFile.stored_name }" width="500" ></c:if></div>
${viewhelp.content }</td></tr>

	</table>


	<c:if test="${login }">
		<button id="btnRecommend" class="btn pull-right"
			style="margin-top: 30px;"></button>
		<div>
			<a href="/anibuddy/upload/${helpFile.stored_name }"
				download="${helpFile.origin_name }">${helpFile.origin_name } </a>
		</div>
	</c:if>

	<div class="text-center">
		<button onclick="location.href='/anibuddy/help/list'" id="btnList"
			class="btn btn-default btn-sm">목록</button>

		<c:if test="${userno eq viewhelp.userNo }">
			<button id="btnUpdate" class="btn btn-info btn-sm">수정</button>
			<button id="btnDelete" class="btn btn-danger btn-sm">삭제</button>
		</c:if>
		<c:if test="${adminLogin }">
			<button id="btnDelete" class="btn btn-danger btn-sm">삭제</button>
		</c:if>
	</div>
	<br>

	<!-- 댓글 처리 -->
	<div>

		<hr>
		<br>
		<br>
<c:choose>
		<c:when test="${login }">
				<div class="form-inline text-center">
				<input type="text" size="10" class="form-control" id="commentWriter"
					value="${loginid }" readonly="readonly" />
				<textarea rows="2" cols="60" class="form-control"
					id="commentContent" style="height: 34px;"></textarea>
				<button id="btnCommInsert" class="btn">입력</button>
			</div>
			<!-- 댓글 입력 end --></c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${adminLogin }" >
					<div class="form-inline text-center">
				<input type="text" size="10" class="form-control" id="commentWriter"
					value="${adminid }" readonly="readonly" />
				<textarea rows="2" cols="60" class="form-control"
					id="commentContent" style="height: 34px;"></textarea>
				<button id="btnCommInsert" class="btn">입력</button>
			</div>
			<!-- 댓글 입력 end --></c:when>
			<c:otherwise>
			<strong>로그인이 필요합니다</strong>
			<br>
			<button onclick='location.href="/anibuddy/user/login";'>로그인</button>
			<button onclick='location.href="/anibuddy/user/join";'>회원가입</button>
			</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
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
					<tr data-commentno="${comment.comment_no }">
						<%-- 	<td>${comment.rnum }</td> --%>
						<td>${comment.user_no }</td>
						<!-- 닉네임으로 해도 좋음 -->
						<td>${comment.content }</td>
						<td><fmt:formatDate value="${comment.write_date }"
								pattern="yy-MM-dd" /></td>
						<td><c:if test="${userno eq comment.user_no }">
								<button class="btn btn-default btn-xs"
									onclick="deleteComment(${comment.comment_no });">삭제</button>
							</c:if>
							<c:if test="${adminLogin }">
								<button class="btn btn-default btn-xs"
									onclick="deleteComment(${comment.comment_no });">삭제</button>
						</c:if>
							</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 댓글 리스트 end -->

	</div>
	<!-- 댓글 처리 end -->


</div>

<jsp:include page="/layout/footer.jsp"></jsp:include>

