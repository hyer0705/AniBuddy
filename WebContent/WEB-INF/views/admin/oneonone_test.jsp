<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/layout/headermanage.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	
	$("#btnSearch").click(function(){
		location.href="<%=request.getContextPath() %>/oneonone/reply?search="+$("#search").val();
	})
	// 검색어 입력 폼에서 enter 키를 눌렀을 때
	$("#search").on("keydown", function( ev ){
		if( ev.keyCode == 13){
			$(location).attr("href", "/anibuddy/oneonone/reply?search=" + $("#search").val())
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
		
	})
	
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
<link rel="stylesheet" href="/anibuddy/css/oneonone.css" />

<div class="Manager">
<h3>QnA 관리</h3><br>
<hr>
	<table class="oneonone">
		<tr>
			<th class="check"><input type="checkbox" id="checkAll" onclick="checkAll();"/></th>
			<th>번호</th>
			<th>제목</th>
			<th>등록자</th>
			<th>등록일</th>
			<th>상태</th>
		</tr>
		<c:forEach items="${testList }" var="i">
			<c:forEach items="${i }" var="j">
	<%-- 		${j.key } = ${j.value } --%>
			
			<tr>
				<td><input type="checkbox" name="checkRow" value="${j.value.oneononeNo }" /></td>
				<td><a href="/anibuddy/oneonone/answer?oneonone_no=${j.value.oneononeNo }">${j.value.oneononeNo }</a></td>
				<td><a href="/anibuddy/oneonone/answer?oneonone_no=${j.value.oneononeNo }">${j.value.title }</a></td>
				<c:choose>
					<c:when test="${j.value.userNo eq 0 }">
						<td><a href="/anibuddy/oneonone/answer?oneonone_no=${j.value.oneononeNo }">관리자번호 : ${j.value.adminNo }</a></td>
					</c:when>
					<c:when test="${j.value.userNo ne 0 }">
						<td><a href="/anibuddy/oneonone/answer?oneonone_no=${j.value.oneononeNo }">${j.key.userId }</a></td>
					</c:when>
				</c:choose>
				<td><a href="/anibuddy/oneonone/answer?oneonone_no=${j.value.oneononeNo }"><fmt:formatDate value="${j.value.writeDate }"
						pattern="yyyy.MM.dd" /></a></td>
				<td><a href="/anibuddy/oneonone/answer?oneonone_no=${j.value.oneononeNo }">${j.value.condition }</a></td>
			</tr>
			</c:forEach>
		</c:forEach>
	</table>
	<button id="btnDelete" class="btn pull-right">삭제</button>
<div class="clearfix"></div>
	<c:import url="/layout/paging.jsp" />

<div class="form-inline text-center clearfix">
	<input class="form-control" type="text" id="search" placeholder="제목검색" />
	<button id="btnSearch" class="btn">검색</button>
</div>



</div>









<c:import url="/layout/footer.jsp" />