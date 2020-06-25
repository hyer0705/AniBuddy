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
		$(location).attr("href", "/anibuddy/oneonone/replywrite?oneonone_no=" + $("#oneonone_no").val() )
	})
	
	// 글 수정 버튼 동작
	$("#btnO3Update").on("click", function(){
		$(location).attr("href", "/anibuddy/oneonone/replyupdate?oneonone_no=" + $("#oneonone_no").val() )
	})
	
	// 글 삭제 버튼 동작
	$("#btnO3Delete").on("click", function(){
// 		console.log("delete btn clicked")
		$(location).attr("href", "/anibuddy/oneonone/replydelete?oneonone_no=" + $("#oneonone_no").val() )
	})
	
});
</script>
<style type="text/css">
#answer {
	width: 900px;
/* 	height: 600px; */
 	margin-bottom: 100px;
}
table, tr, th, td{
/* 	border: 1px solid black; */
	border-collapse: collapse;
}

table{
 	width :100%;
}

td {
	padding: 10px;
}

.text-center {
	text-align: center;
}

.border-top-blue {
	border-top: 1px solid #b8d3d2;
}

.border-bottom-blue {
	border-bottom: 1px solid #b8d3d2;
}

.border-right-blue {
	border-right: 1px solid #b8d3d2;
}

.width25 {
	width: 25%
}

.height300 {
	height: 300px
}

.bgBlue {
	background: #b8d3d2;
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
	<table>
		<tr class="border-bottom-blue border-top-blue">
			<td class="bgBlue">문의 제목</td>
			<td colspan="3">${detail.title }</td>
		</tr>
		<c:choose>
 			<c:when test="${detail.userNo eq 0 }">
			<tr class="border-bottom-blue">
				<td class="width25 bgBlue">작성자</td>
				<td class="width25 border-right-blue">${detail.adminNo }</td>
				<td class="width25 bgBlue">작성일자</td>
				<td class="width25">${detail.writeDate }</td>
			</tr>
 			</c:when>
 			<c:when test="${detail.userNo ne 0 }">
			<tr class="border-bottom-blue">
				<td class="width25 bgBlue">작성자</td>
				<td class="width25 border-right-blue">${detail.userNo }</td>
				<td class="width25 bgBlue">작성일자</td>
				<td class="width25">${detail.writeDate }</td>
			</tr>
 			</c:when>
 		</c:choose>
 		<tr class="border-bottom-blue">
 			<td colspan="4" class="text-center">문의 내용</td>
 		</tr>
		<tr class="border-bottom-blue height300">
			<td colspan="4">${detail.content }</td>
		</tr>
	</table>

<c:if test="${not empty q }">
<table>
		<tr class="border-bottom-blue border-top-blue">
			<td class="bgBlue">답변 제목</td>
			<td colspan="3">${q.title }</td>
		</tr>
		<tr class="border-bottom-blue">
			<td class="bgBlue">답변 작성일자</td>
			<td>${q.writeDate }</td>
		</tr>
 		<tr class="border-bottom-blue">
 			<td colspan="4" class="text-center">답변 내용</td>
 		</tr>
		<tr class="border-bottom-blue height300">
			<td colspan="4">${q.content }</td>
		</tr>
	</table>
	
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