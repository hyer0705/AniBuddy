<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/layout/header1.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function(){
	
	// 삭제버튼 클릭시
	$("#btnPMDelete").click(function(){
		
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
						.attr("action", "/anibuddy/mypage/deletesendpm")
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

<script type="text/javascript">
$(document).ready(function(){
	$("#btnSendPM").on("click",function(){
		$(location).attr("href", "/anibuddy/mypage/sendtoanonymous")
	})

	// 보낸쪽지함 div 영역 클릭시
	$("#toPmList").on("click", function(){
		$(location).attr("href", "/anibuddy/mypage/pmlist")	
	})
	
})
</script>

<main class="wrapper">
<div class="pm_content o3-content">
	<div class="pm_cont o3-cont">
		<div class="pm_cont__title o3-cont__title">보낸쪽지함</div>
		<div class="pm_cont__send-title" id="toPmList" >받은쪽지함 가기</div>
	</div>
	
	<table class="pm oneonone">
		<tr>
			<th class="check"><input type="checkbox" id="checkAll" onclick="checkAll();"/></th>
			<th>번호</th>
			<th>제목</th>
			<th>등록일</th>
			<th>상태</th>
		</tr>
		<c:choose>
			<c:when test="${not empty pmSendList }">
				<c:forEach items="${pmSendList }" var="pm">
					<tr>
						<td><input type="checkbox" name="checkRow" value="${pm.pmNo }" /></td>
						<td><a href="/anibuddy/mypage/sendpmdetail?pm_no=${pm.pmNo }">${pm.pmNo }</a></td>
						<td><a href="/anibuddy/mypage/sendpmdetail?pm_no=${pm.pmNo }">${pm.title }</a></td>
						<td><a href="/anibuddy/mypage/sendpmdetail?pm_no=${pm.pmNo }"><fmt:formatDate value="${pm.sendDate }"
 		 						pattern="yyyy.MM.dd" /></a></td>
 						<td><a href="/anibuddy/mypage/sendpmdetail?pm_no=${pm.pmNo }">${pm.isCheck }</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${empty pmSendList }">
				<tr><td colspan="5">쪽지함 비어있음</td></tr>
			</c:when>
		</c:choose>
	</table>
	<div class="pm-cont__wrap o3-cont__wrap">
		<button type="button" id="btnSendPM" class="btn">쪽지 보내기</button>
		<button id="btnPMDelete" class="btn pull-right">삭제</button>
	</div>
	<div class="clearfix"></div>
	<c:if test="${paging.totalCount ne 0 }">
	<c:import url="./postmessage_send_list_paging.jsp" />
	</c:if>
</div>


<jsp:include page="/layout/mypage_sidebar.jsp"></jsp:include>
</main>

<!-- footer float 속성에 대한 피해 막기 -->
<div class="clear"></div>
<jsp:include page="/layout/footer.jsp"></jsp:include>