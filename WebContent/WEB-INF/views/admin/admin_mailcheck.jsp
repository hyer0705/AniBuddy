<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<c:import url="/layout/headermanage.jsp" />
<link rel="stylesheet" href="/anibuddy/css/oneonone.css" />
<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnoneMail").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/admin/onemail");
	});
	
	$("#btnallMail").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/admin/mail");
	});
	
});
</script>
<div class="container" style="width: 900px;">
<h3>메일 확인</h3><br>
<hr>

	<table class="oneonone">
		<tr>
			<th style="width:10%">번호</th>
			<th>제목</th>
			<th style="width:20%">메일 유형</th>
			<th style="width:20%">보낸 날짜</th>
		</tr>
		<c:forEach items="${email }" var="email">
			<tr>
				<td><a href="<%=request.getContextPath() %>/admin/mailview?emailno=${email.emailno}">${email.emailno }</a></td>
				<td><a href="<%=request.getContextPath() %>/admin/mailview?emailno=${email.emailno}">${email.title }</a></td>
				<td><c:if test="${email.isall eq '0'}">개별메일</c:if>
				<c:if test="${email.isall eq '1'}">전체메일</c:if> </td>
				<td>${email.writedate }</td>
			</tr>
		</c:forEach>
	</table>
	<c:import url="/layout/pagingmail.jsp" />
	
	<div class="text-center">
	<button id="btnoneMail" class="btn btn-default btn-sm">개별 메일</button>
	<button id="btnallMail" class="btn btn-default btn-sm">전체 메일</button>
	</div>

</div>
<jsp:include page="/layout/footer.jsp"></jsp:include>