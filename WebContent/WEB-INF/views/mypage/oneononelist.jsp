<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/layout/header.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function(){
	
	// 검색 버튼 클릭시 검색어를 통해 검색
	$("#btnSearch").click(function(){
		location.href="<%=request.getContextPath() %>/mypage/oneononelist?search="+$("#search").val();
	})
	
	// 검색어 입력 폼에서 enter 키를 눌렀을 때
	$("#search").on("keydown", function( ev ){
// 		console.log(ev.keyCode)
		if( ev.keyCode == 13){
// 			console.log("Enter keydown")
			$(location).attr("href", "/anibuddy/mypage/oneononelist?search=" + $("#search").val())
		}
	})
	
	// 문의하기 버튼 클릭시
	$("#btnO3").on("click", function(){
		$(location).attr("href", "/anibuddy/mypage/oneononewrite")
	})
	
	// 삭제버튼 클릭시
	$("#btnDelete").click(function(){
		
		var $checkboxes = $("input:checkbox[name='checkRow']:checked");
		
		var map = $checkboxes.map(function(){
			return $(this).val();
		})
		
		var names= map.get().join(",");
		
		console.log($checkboxes);
		console.log("map : "+ map);
		console.log("map -> array : "+map.get());
		console.log("array tostring : " + map.get().join(","));
		
		var $form = $("<form>")
						.attr("action", "/anibuddy/oneonone/delete")
						.attr("method", "post")
						.append(
								$("<input>")
									.attr("type", "hidden")
									.attr("name", "names")
									.attr("value", names)
						)
		$(document.body).append($form);
		$form.submit();
		
	}) // $("#btnDelete").click(function(){ - end
	
})

function checkAll(){
	
	var $checkboxes=$("input:checkbox[name='checkRow']")
	
	var check_status = $("#checkAll").is(":checked")
	
	if( check_status ){
		
		$checkboxes.each(function(){
			this.checked = true;
		})
	}else{
		$checkboxes.each(function(){
			this.checked = false;
		})
		
	}
}
</script>

<main class="wrapper">
<div class="o3-content">
	<div class="o3-cont">
		<div class="o3-cont__title">1:1문의</div>
	</div>
	
	<table class="oneonone">
		<tr>
			<th class="check"><input type="checkbox" id="checkAll" onclick="checkAll();"/></th>
			<th>번호</th>
			<th>제목</th>
			<th>등록일</th>
			<th>상태</th>
		</tr>
		<c:forEach items="${o3 }" var="oneonone">
			<tr>
				<td><input type="checkbox" name="checkRow" value="${oneonone.oneononeNo }" /></td>
				<td><a href="/anibuddy/mypage/oneononedetail?oneonone_no=${oneonone.oneononeNo }">${oneonone.oneononeNo }</a></td>
				<td><a href="/anibuddy/mypage/oneononedetail?oneonone_no=${oneonone.oneononeNo }">${oneonone.title }</a></td>
				<td><a href="/anibuddy/mypage/oneononedetail?oneonone_no=${oneonone.oneononeNo }"><fmt:formatDate value="${oneonone.writeDate }"
						pattern="yyyy.MM.dd" /></a></td>
				<td><a href="/anibuddy/mypage/oneononedetail?oneonone_no=${oneonone.oneononeNo }">${oneonone.condition }</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<div class="o3-cont__wrap">
		<button type="button" id="btnO3" class="btn">문의하기</button>
		<button id="btnDelete" class="btn pull-right">삭제</button>
	</div>
	<div class="clearfix"></div>
	<c:import url="./oneonone_paging.jsp" />

<div class="form-inline text-center clearfix">
	<input class="form-control" type="text" id="search" placeholder="제목검색" />
	<button id="btnSearch" class="btn">검색</button>
</div>
	
</div>


<jsp:include page="/layout/mypage_sidebar.jsp"></jsp:include>
</main>

<!-- footer float 속성에 대한 피해 막기 -->
<div class="clear"></div>
<jsp:include page="/layout/footer.jsp"></jsp:include>