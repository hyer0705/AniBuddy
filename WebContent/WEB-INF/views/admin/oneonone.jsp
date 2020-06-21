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
			<c:forEach items="${oneonone }" var="oneonone">
				<tr>
					<td><input type="checkbox" name="checkRow" value="${oneonone.oneononeNo }" /></td>
					<td><a href="<%=request.getContextPath() %>/oneonone/answer?oneonone_no=${oneonone.oneononeNo }">${oneonone.oneononeNo }</a></td>
					<td><a href="<%=request.getContextPath() %>/oneonone/answer?oneonone_no=${oneonone.oneononeNo }">${oneonone.title }</a></td>
					<td><a href="<%=request.getContextPath() %>/oneonone/answer?oneonone_no=${oneonone.oneononeNo }">${oneonone.userNo }</a></td>
					<td><a href="<%=request.getContextPath() %>/oneonone/answer?oneonone_no=${oneonone.oneononeNo }"><fmt:formatDate value="${oneonone.writeDate }"
							pattern="yyyy.MM.dd" /></a></td>
					<td><a href="<%=request.getContextPath() %>/oneonone/answer?oneonone_no=${oneonone.oneononeNo }">${oneonone.condition }</a></td>
				</tr>
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