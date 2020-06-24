<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/layout/headermanage.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btntoList").click(function() {
		$(location).attr("href", "/anibuddy/oneonone/reply");
	});
	
	
	// 글쓰기 버튼 동작
	$("#btnO3Write").on("click", function(){
		$(location).attr("href", "/anibuddy/oneonone/replyWrite?oneonone_no=" + $("#oneonone_no").val() )
	})
	
	// 글 수정 버튼 동작
	$("#btnO3Update").on("click", function(){
		$(location).attr("href", "/anibuddy/oneonone/replyUpdate?oneonone_no=" + $("#oneonone_no").val() )
	})
	
	// 글 삭제 버튼 동작
	$("#btnO3Delete").on("click", function(){
// 		console.log("delete btn clicked")
		$(location).attr("href", "/anibuddy/oneonone/replyDelete?oneonone_no=" + $("#oneonone_no").val() )
	})
	
});
</script>
<style type="text/css">
#answer {
	width: 900px;
	height: 600px;
	margin-bottom: 210px;
}

.sub_content {
	background-color : #ffaf9a;
	height : 70px;
}
table{
	width :100%;
	height : 100%;
}
td{
	text-align: right;
	padding-right: 30px;
}
.title{
	text-align: left;
	font-size: 30px;
	padding-left: 30px;
	width : 600px;
}
.btnDelete{
	background-color : #ffaf9a;
	margin-top: 5px;
	padding : 5px;
	border : 1px solid #ffaf9a;
	border-radius: 3px;
	width : 60px;
	height : 40px;
}
.btnDelete:hover{
	font-weight: bold;

}

#btnO3Write
, #btnO3Update
, #btnO3Delete {
	margin-left: 10px;
}

#btnO3Update
, #btnO3Write
, #btnO3Delete {
	width: 80px;
}

#btnO3Delete {
    margin-left: 5px;
}


</style>

<div id="answer">
	<h3>문의글</h3>
	<hr>
	<div class="sub_content">
	<table>
	<tr>
		<td class="title">
		${detail.title }
		</td>
		<c:choose>
			<c:when test="${detail.userNo eq 0 }">
				<td>
				작성자 번호 : ${detail.adminNo }
				</td>
			</c:when>
			<c:when test="${detail.userNo ne 0 }">
				<td>
				작성자 번호 : ${detail.userNo }
				</td>
			</c:when>
		</c:choose>

		<td>
		|  작성 일자${detail.writeDate }
		</td>
	</tr>
	</table>
	</div>

<div>
<a href="/anibuddy/file/download?fileno=${boardFile.fileno }">${boardFile.originName }</a>
</div>

<div class="content">
	${detail.content }
</div>
<hr>

<c:if test="${not empty q }">
	<div class="sub_content">
	<table>
	<tr>
		<td class="title">
		${q.title }
		</td>
		<td>
		작성 일자${q.writeDate }
		</td>
	</tr>
	</table>
	</div>

<div class="content">
	${q.content }
</div>
<hr>
</c:if>
<button type="button" class="btnDelete pull-left" id="btntoList">목록</button>
<c:choose>
	<c:when test="${detail.adminNo > 0 }">
		<button type="button" class="btnDelete" id="btnO3Update">답변 수정</button>
		<button type="button" class="btnDelete" id="btnO3Delete">답변 삭제</button>
	</c:when>
	<c:when test="${empty q }">
		<button type="button" class="btnDelete" id="btnO3Write">답변 쓰기</button>
	</c:when>
</c:choose>

</div>
<input type="hidden" id="oneonone_no" value="${detail.oneononeNo }" />

<c:import url="/layout/footer.jsp" />