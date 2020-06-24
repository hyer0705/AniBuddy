<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/layout/headermanage.jsp" />

<script type="text/javascript">
	$(document).ready(function() {

		$("#btnSearch").click(function() {
			location.href = "/anibuddy/board/help?search=" + $("#search").val();
		})
		
		$("#search").on("keydown", function( ev ){
			if( ev.keyCode == 13 ){
				$(location).attr("href", "/anibuddy/board/help?search=" + $("#search").val())
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
						.attr("action", "/anibuddy/helpboard/delete")
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
	<h2>봉사</h2>
	<hr>
	<div class="btn-group pull-right">
		<button type="button" class="btn dropdown-toggle"
			data-toggle="dropdown" aria-expanded="false">
			봉사 <span class="caret"></span>
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
		<c:forEach items="${help }" var="help">
			<tr>
				<td><input type="checkbox" name="checkRow"value="${help.postNo }" /></td>
				<td><a href="/anibuddy/help/view?postno=${help.postNo }">${help.postNo }</a></td>
				<td><a href="/anibuddy/help/view?postno=${help.postNo }">${help.title }</a></td>
				<td><a href="/anibuddy/help/view?postno=${help.postNo }">${help.userNo }</a></td>
				<td><a href="/anibuddy/help/view?postno=${help.postNo }">
						<fmt:formatDate value="${help.writeDate }" pattern="yyyy.MM.dd" />
				</a></td>
				<td><a href="/anibuddy/help/view?postno=${help.postNo }">${help.dealProgress }</a></td>
			</tr>
		</c:forEach>
	</table>
	<button id="btnDelete" class="btn pull-right">삭제</button>
	<div class="clearfix"></div>
	<c:import url="/layout/paginghelp.jsp" />

	<div class="form-inline text-center">
		<input class="form-control" type="text" id="search" placeholder="제목검색"/>
		<button id="btnSearch" class="btn">검색</button>
	</div>



</div>









<c:import url="/layout/footer.jsp" />