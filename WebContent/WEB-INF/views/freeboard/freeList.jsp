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
		location.href="/anibuddy/freeboard/write";
	});	
	
	
	//검색 버틀 클릭
	$("#btnSearch").click(function() {
		location.href="/anibuddy/freeboard/list?search="+$("#search").val();
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
// 			location.href="/anibuddy/freeboard/write"
// 		} else{
// 			alert("로그인 후 사용해주세요")
// 			location.href="/anibuddy/freeboard/list"
// 		}
// 	}
</script>


<div class="container" style="width: 900px; ">
<br><br><br>
<h1> 자유 게시판 목록  </h1>
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
<c:forEach items="${list }" var="freeboard">
<tr>
	<td>${freeboard.postno }</td>
	<td>${freeboard.range }</td>
	<td><c:choose>
		<c:when test="${login }">
			<a href="/anibuddy/freeboard/view?postno=${freeboard.postno }" >${freeboard.title }</a></c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${freeboard.range eq '전체 공개' }" >
				<a href="/anibuddy/freeboard/view?postno=${freeboard.postno }" >${freeboard.title }</a></c:when>
			<c:otherwise>
			<a href="#" onclick="alert('로그인 후 이용해주세요')">${freeboard.title }</a>
			</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose></td>
<%-- 	<td><a href="/anibuddy/freeboard/view?postno=${freeboard.postno }" >${freeboard.title }</a></td> --%>
	<td>${freeboard.userid }</td>
	<td>${freeboard.hit }</td>
	<td><fmt:formatDate value="${freeboard.writedate }" pattern="yyyy-MM-dd"/></td>
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
<c:import url="freepaging.jsp" />
<jsp:include page="/layout/footer.jsp"></jsp:include>

