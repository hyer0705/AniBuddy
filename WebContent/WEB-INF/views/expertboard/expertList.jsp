<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/layout/header.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function() {
	
	var val = location.href.substr(
		location.href.lastIndexOf()		
	)
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="<%=request.getContextPath() %>/expertboard/write";
	});	
	
	
	//검색 버틀 클릭
	$("#btnSearch").click(function() {
		location.href="<%=request.getContextPath() %>/expertboard/list?search="+$("#search").val();
	});
	
});
</script>

<style type="text/css">
.container {
margin-top: 70px;
}

#exboard thead tr {
background-color: #ccc;
text-align: center;
}

#btnBox #btnWrite {
/* background-color : #ccc; */
}

</style>

<script type="text/javascript">
// 	function loginch(){
<%-- 		if('<%=session.getAttribute("login")%>' == null) { --%>
// 			location.href="/Anibuddy/expertboard/write"
// 		} else{
// 			alert("로그인 후 사용해주세요")
// 			location.href="/Anibuddy/expertboard/list"
// 		}
// 	}
</script>


<div class="container" style="width: 900px; ">
<br><br><br>
<h1>  전문가 QnA 목록  </h1>
<hr>

<table id="exboard" class="table table-hover table-condensed">
<thead>
<tr>
	<th style="width: 8%;">글번호</th>
	<th style="width: 12%;">공개범위</th>
	<th style="width: 45%;">제목</th>
	<th style="width: 14%;">아이디</th>
	<th style="width: 8%;">조회수</th>
	<th style="width: 13%;">작성일</th>
</tr>
</thead>
<c:forEach items="${list }" var="expertboard">
<tr>
	<td>${expertboard.postno }</td>
	<td>${expertboard.range }</td>
	<td>
	<c:if test="${expertboard.depth >0 }">&nbsp;ㄴ&nbsp;</c:if>
	
	<c:choose>
		<c:when test="${login }">
			<c:choose>
			<c:when test="${expertboard.range eq '전문가 공개' }">
				<c:choose>
				<c:when test="${isexpert eq 'Y'}">
				<a href="<%=request.getContextPath() %>/expertboard/view?postno=${expertboard.postno }" >${expertboard.title }</a></c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${expertboard.userid eq loginid }">
					<a href="<%=request.getContextPath() %>/expertboard/view?postno=${expertboard.postno }" >${expertboard.title }</a></c:when>
					<c:otherwise>
					<a href="#" onclick="alert('전문가가 아닙니다!')">${expertboard.title }</a></c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath() %>/expertboard/view?postno=${expertboard.postno }" >${expertboard.title }</a>
			</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:choose>
			<c:when test="${expertboard.range eq '전체 공개' }" >
				<a href="<%=request.getContextPath() %>/expertboard/view?postno=${expertboard.postno }" >${expertboard.title }</a></c:when>
			<c:otherwise>
			<a href="#" onclick="alert('로그인 후 이용해주세요')">${expertboard.title }</a>
			</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	<c:if test="${expertboard.hit > 10 }"><span style="color: coral;" >&nbsp;hit!</span></c:if>
	</td>
	<td>${expertboard.userid }</td>
	<td>${expertboard.hit }</td>
	<td><fmt:formatDate value="${expertboard.writedate }" pattern="yyyy-MM-dd"/></td>
</tr>
</c:forEach>

</table>

<c:if test="${login }">
<div id="btnBox" class="text-right">
	<button id="btnWrite" class="btn btn-default">글쓰기</button>
</div>
</c:if> 
<br><br>
<div class="form-inline text-center">
	<input class="form-control" type="text" id="search" />
	<button id="btnSearch" class="btn">검색</button>
</div>

</div>
<c:import url="expertpaging.jsp" />
<jsp:include page="/layout/footer.jsp"></jsp:include>

