<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/layout/header1.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function(){
	// 목록 버튼 클릭시
	$("#btnToList").on("click", function(){
		$(location).attr("href", "/anibuddy/mypage/oneononelist")
	})
	
	// 1:1문의 수정 버튼 클릭시
	$("#btnO3Update").on("click", function(){
		$(location).attr("href", "/anibuddy/mypage/oneononeUpdate?oneonone_no=" + $("#o3No").val() )
	})
	
	// 1:1문의글 삭제 버튼 클릭시
	$("#btnO3Delte").on("click", function(){
		if(confirm("정말 저를... 지우실... 건가요...?")) {
			$(location).attr("href", "/anibuddy/mypage/oneononeDelete?oneonone_no=" + $("#o3No").val() )
		}
	})
})
</script>

<main class="wrapper">
<div class="o3-content">
	<div class="o3-cont">
		<div class="o3-cont__title">1:1문의 상세보기</div>
	</div>
	<div class="o3-cont">
		<table class="table table-bordered" >
		<tr>
		<td class="danger">제목</td><td colspan="3">${detailO3.title }</td>
		</tr>
	
		<tr>		
		<td class="danger">작성일</td><td>${detailO3.writeDate }</td>
		</tr>
		
		
		<tr><td class="danger"  colspan="4">본문</td></tr>
		<tr><td colspan="4">${detailO3.content }</td></tr>
		
		<c:if test="${not empty q }">
		<tr><td class="danger"  colspan="4">답변</td></tr>

		<tr>
		<td class="danger">답변 제목</td><td colspan="3">${q.title }</td>
		</tr>

		<tr>		
		<td class="danger">답변 작성일</td><td>${q.writeDate }</td>
		</tr>

		<tr><td class="danger"  colspan="4">답변 내용</td></tr>
		<tr><td colspan="4">${q.content }</td></tr>
		</c:if>
		
		</table>
	</div>
	<div class="text-center">	
		<button type="button" id="btnO3Update" class="btn btn-info" style="margin-top: 0;" >1:1문의수정</button>
		<button type="button" id="btnO3Delte" class="btn btn-info" style="margin-top: 0;" >1:1문의삭제</button>
		<button type="button" id="btnToList" class="btn btn-danger">목록</button>
	</div>
	<input type="hidden" id="o3No" value="${detailO3.oneononeNo }" />
</div>


<jsp:include page="/layout/mypage_sidebar.jsp"></jsp:include>
</main>

<!-- footer float 속성에 대한 피해 막기 -->
<div class="clear"></div>
<jsp:include page="/layout/footer.jsp"></jsp:include>