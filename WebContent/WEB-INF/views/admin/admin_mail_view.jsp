<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/layout/headermanage.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/admin/mailcheck");
	});
});
</script>

<div class="container" style="width: 900px;">
<h3>메일 확인</h3><br>
<hr>

	<table class="table table-bordered ">
		<tr>
			<td class="danger" width="20%">메일 번호</td><td width="30%">${mail.emailno }</td>
			<td class="danger" width="20%">메일 형태(이메일)</td>
				<td><c:if test="${mail.isall eq '0'}">개별메일(${mail.useremail })</c:if>
				<c:if test="${mail.isall eq '1'}">전체메일</c:if></td>
		</tr>
		<tr>
			<td class="danger">제목</td><td colspan="3">${mail.title }</td>
		</tr> 
		<tr> 
			<td class="danger" colspan="4">내용</td>
		</tr>
		<tr height="200px">
			<td colspan="4" width="200">${mail.content }</td>
		</tr>

	</table>

<div class="text-center">
<button onclick="location,href='<%=request.getContextPath() %>/admin/mailcheck'" class="btn btn-default btn-sm" id="btnList">목록</button>
</div>
</div>
<jsp:include page="/layout/footer.jsp"></jsp:include>