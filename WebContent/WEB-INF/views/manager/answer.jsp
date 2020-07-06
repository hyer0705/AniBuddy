<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/layout/headermanage.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$(".btnDelete").click(function() {
		$(location).attr("href", "/oneonone/reply");
	});
	
	
});
</script>
<style type="text/css">
#answer {
	width: 900px;
	height: 600px;
	margin-bottom: 210px;
}

.sub_content {
	background-color : #ffaf9a;
	height : 70px;
}
table{
	width :100%;
	height : 100%;
}
td{
	text-align: right;
	padding-right: 30px;
}
.title{
	text-align: left;
	font-size: 30px;
	padding-left: 30px;
	width : 600px;
}
.btnDelete{
	background-color : #ffaf9a;
	margin-top: 5px;
	padding : 5px;
	border : 1px solid #ffaf9a;
	border-radius: 3px;
	width : 60px;
	height : 40px;
}
.btnDelete:hover{
	font-weight: bold;

}


</style>

<div id="answer">
	<h3>문의글</h3>
	<hr>
	<div class="sub_content">
	<table>
	<tr>
		<td class="title">
		${detail.title }
		</td>
		<td>
		작성자 번호 : ${detail.user_no }
		</td>
		<td>
		|  작성 일자${detail.write_date }
		</td>
	</tr>
	</table>
	</div>

<div>
<a href="/file/download?fileno=${boardFile.fileno }">${boardFile.originName }</a>
</div>

<div class="content">
	${detail.content }
</div>
<hr>
<button class="btnDelete pull-left">목록</button>

</div>

<c:import url="/layout/footer.jsp" />