<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/layout/headermanage.jsp" />

<script type="text/javascript">
	$(document).ready(function() {

		// 검색 버튼 클릭할 때
		$("#btnSearch").click(function() {
			location.href = "/anibuddy/board/main?search=" + $("#search").val();
		})
		
		// 검색 입력 폼에서 엔터 키 눌렀을 때
		$("#search").on("keydown", function( ev ){
			if(ev.keyCode == 13){
// 				console.log("enter key down!!!")
				$(location).attr("href", "/anibuddy/board/main?search=" + $("#search").val() )
			}
		})

	})
</script>

<script type="text/javascript">
$(document).ready(function(){
	
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
						.attr("action", "/anibuddy/board/delete")
						.attr("method", "post")
						.append(
								$("<input>")
									.attr("type", "hidden")
									.attr("name", "names")
									.attr("value", names)
						)
		$(document.body).append($form);

		$form.submit();
		
	})
	
})


	function checkAll() {

		var $checkboxes = $("input:checkbox[name='checkRow']")

		var check_status = $("#checkAll").is(":checked")

		if (check_status) {

			$checkboxes.each(function() {
				this.checked = true;
			})
		} else {
			$checkboxes.each(function() {
				this.checked = false;
			})

		}
	}
</script>
<link rel="stylesheet" href="/anibuddy/css/oneonone.css" />



<div class="Manager">
	<h3>게시판 관리</h3>
	<br>
	<h2>나눔&중고거래</h2>
	<hr>
	<div class="btn-group pull-right">
		<button type="button" class="btn dropdown-toggle"
			data-toggle="dropdown" aria-expanded="false">
			나눔&중고거래 <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu">
			
			<li><a href="/anibuddy/board/main">나눔&중고거래</a></li>
			<li><a href="/anibuddy/board/help">봉사</a></li>
			<li><a href="/anibuddy/board/expert">전문가QnA</a></li>
			<li><a href="/anibuddy/board/free">자유 게시판</a></li>
		</ul>
	</div>
	<table class="oneonone">
		<tr>
			<th class="check"><input type="checkbox" id="checkAll"
				onclick="checkAll();" /></th>
			<th>등록 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>거래  상황</th>
		</tr>
		<c:forEach items="${share }" var="share">
			<tr>
				<td><input type="checkbox" name="checkRow"value="${share.postno }" /></td>
				<td><a href="/anibuddy/share/view?post_no=${share.postno }">${share.postno }</a></td>
				<td><a href="/anibuddy/share/view?post_no=${share.postno }">${share.title }</a></td>
				<td><a href="/anibuddy/share/view?post_no=${share.postno }">${share.userno }</a></td>
				<td><a href="/anibuddy/share/view?post_no=${share.postno }">
						<fmt:formatDate value="${share.writedate }" pattern="yyyy.MM.dd" />
				</a></td>
				<td><a href="/anibuddy/share/view?post_no=${share.postno }">${share.dealProgress }</a></td>
			</tr>
		</c:forEach>
	</table>
	<button id="btnDelete" class="btn pull-right">삭제</button>
	<div class="clearfix"></div>
	<c:import url="/layout/pagingboard.jsp" />

	<div class="form-inline text-center">
		<input class="form-control" type="text" id="search" placeholder="제목검색"/>
		<button id="btnSearch" class="btn">검색</button>
	</div>



</div>









<c:import url="/layout/footer.jsp" />