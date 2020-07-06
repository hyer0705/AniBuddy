<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/layout/headermanage.jsp" />

<script type="text/javascript">
	$(document).ready(function() {

		$("#btnSearch").click(function() {
			location.href = "/manage/user?search=" + $("#search").val();
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
						.attr("action", "/user/delete")
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
<link rel="stylesheet" href="/css/oneonone.css" />



<div class="Manager">
	<h3>사용자 관리</h3><br>
	<hr>
	<table class="oneonone">
		<tr>
			<th class="check"><input type="checkbox" id="checkAll"
				onclick="checkAll();" /></th>
			<th>유저 번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>닉네임</th>
			<th>성별</th>
			<th>반려동물</th>
		</tr>
		<c:forEach items="${userinfo }" var="share">
			<tr>
				<td><input type="checkbox" name="checkRow" value="${share.user_no }" /></td>
				<td>${share.user_no }</td>
				<td>${share.user_id }</td>
				<td>${share.user_name }</td>
				<td>
						<fmt:formatDate value="${share.birth }" pattern="yyyy.MM.dd" />
				</td>
				<td>${share.nick }</td>
				<td>${share.gender }</td>
				<td>${share.animal }</td>
			</tr>
		</c:forEach>
	</table>
	<button id="btnDelete" class="btn pull-right">삭제</button>
	<div class="clearfix"></div>
	<c:import url="/layout/paginguser.jsp" />

	<div class="form-inline text-center">
		<input class="form-control" type="text" id="search" placeholder="아이디 검색" />
		<button id="btnSearch" class="btn">검색</button>
	</div>



</div>









<c:import url="/layout/footer.jsp" />