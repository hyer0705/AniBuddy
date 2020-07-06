<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/layout/header1.jsp"></jsp:include>

<style type="text/css">
.positionR {
	position: relative;
}

.positionA {
	position: absolute;
	right: 0;
	top: 0;
    font-size: 19px;
    font-weight: 700;
	cursor: pointer;
}

.bgYellow {
	background: #ffbf00;
}

.fontBold {
	font-weight: 700;
}

.margin-top100 {
	margin-top: 100px;
}

</style>

<script type="text/javascript">
$(document).ready(function(){
	
	// 삭제버튼 클릭시
	$("#btnBMDelete").click(function(){
		
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
						.attr("action", "/anibuddy/activity/hbookmarkdelete")
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
$(document).ready(function() {

$("#toSendPmList").on("click", function(){
	$(location).attr("href", "/anibuddy/activity/postlist")	
})

})
</script>


<main class="wrapper">
<div class="o3-content">
	<div class="pm-cont o3-cont positionR">
		<div class="o3-cont__title"><span class="glyphicon glyphicon-heart"></span> 장소 선호 목록</div>
		<div class="pm_cont__send-title positionA" id="toSendPmList">작성한 게시글 목록 </div>
	</div>
	<div class="o3-cont positionR">
		<div class="btn-group positionA">
			<button type="button" class="btn dropdown-toggle bgYellow fontBold"
				data-toggle="dropdown" aria-expanded="false">
				놀거리 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				
				<li><a href="/anibuddy/activity/hbookmarklist">놀거리</a></li>
				<li><a href="/anibuddy/activity/ibookmarklist">알거리</a></li>
			</ul>
		</div>
	</div>
	<div class="o3-cont margin-top100">
	<table class="oneonone">
		<tr>
			<th class="check"><input type="checkbox" id="checkAll"
				onclick="checkAll();" /></th>
			<th>등록 번호</th>
			<th>장소명</th>
			<th>주소지</th>
		</tr>
		<c:choose>
			<c:when test="${not empty bmList }"> <%int bmNo=1; %>
				<c:forEach items="${bmList }" var="bm">
					<c:forEach items="${list }" var="list">
						<c:if test="${(bm.hNo eq list.hNo) and (bm.userNo eq userno) }">
							<tr>
								<td><input type="checkbox" name="checkRow" value="${bm.bmNo }" /></td>
								<td><a href="/anibuddy/hangout/view?hNo=${bm.hNo }"><%=bmNo++ %></a></td>
								<td><a href="/anibuddy/hangout/view?hNo=${bm.hNo }">${list.hName }</a></td>
								<td>${list.hCity1 } ${list.hCity2 }</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:forEach>
			</c:when>
			<c:when test="${empty bmList }">
				<tr><td colspan="6">선호 목록 비어있음</td></tr>
			</c:when>
		</c:choose>
	</table>
	<div class="pm-cont__wrap o3-cont__wrap">
		<button id="btnBMDelete" class="btn pull-right">삭제</button>
	</div>
	<div class="clearfix"></div>
	<c:if test="${paging.totalCount ne 0 }">
		<c:import url="./activity_hangout_bm_paging.jsp" />
	</c:if>
	
	</div>
</div>

<jsp:include page="/layout/mypage_sidebar.jsp"></jsp:include>
</main>
<!-- footer float 속성에 대한 피해 막기 -->
<div class="clear"></div>


<jsp:include page="/layout/footer.jsp"></jsp:include>
