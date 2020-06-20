<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty msg }">
<script>
alert('${msg }')
</script>
</c:if>
    	
	<c:forEach items="${list }" var="i">
	<div class="placeContent">
		<div class="placeImg" id="placeImg" onclick="location.href='<%=request.getContextPath() %>/hangout/view?hNo=${i.hNo}'">
<!-- 		<div class="placeImg"> -->
			<c:forEach items="${fileList }" var="j">
				<c:if test="${j.hNo eq i.hNo }">
					<div>
						<img src="<%=request.getContextPath() %>/upload/${j.storedName }" alt="장소 이미지" width="320" height="220">
					</div> 
				</c:if>
			</c:forEach>
		</div> 
		<div class="place"  id="place" onclick="location.href='<%=request.getContextPath() %>/hangout/view?hNo=${i.hNo}'">
			<p class="place-title">${i.hName }</p>
			<p>분류 : ${i.hFilter }</p>
			<p>주소 : ${i.hAddress }</p>
			<p>운영시간 : ${i.hTime }</p>
			<p>연락처 : ${i.hTel }</p>
			<p>기타사항 : ${i.hContent }</p>
			<p>사이트 주소 : ${i.hDomain }</p>
		</div>
		<c:if test="${i.userNo eq userno }">
			<div class="delete-place" id="delete">
				<button type="button" class="btn" id="btnDelete"
				onclick="if(confirm('정말 삭제하시겠습니까?')==true){location.href='<%=request.getContextPath() %>/hangout/delete?hNo=${i.hNo }'} else return;">삭제</button>
			</div>
		</c:if>
<%-- 		<c:if test="${i.userNo eq userno }"> --%>
<!-- 			<div class="delete-place" id="delete"> -->
<!-- 				<button type="button" class="btn"  -->
<%-- 				onclick="location.href='<%=request.getContextPath() %>/hangout/delete?hNo=${i.hNo }' --%>
<%-- 				 confirm('정말 삭제하시겠습니까?')">삭제</button> --%>
<!-- 			</div> -->
<%-- 		</c:if> --%>
	</div>
	</c:forEach>
	
	<c:forEach items="${placeList }" var="i">
	<div class="placeImg" id="placeImg" onclick="location.href='<%=request.getContextPath() %>/hangout/view?hNo=${i.hNo}'">
<!-- 	<div class="placeContent"> -->
		<div class="placeImg">
			<c:forEach items="${fileList }" var="j">
				<c:if test="${j.hNo eq i.hNo }">
					<div>
						<img src="<%=request.getContextPath() %>/upload/${j.storedName }" alt="장소 이미지" width="320" height="220">
					</div> 
				</c:if>
			</c:forEach>
		</div> 
		<div class="place"  id="place" onclick="location.href='<%=request.getContextPath() %>/hangout/view?hNo=${i.hNo}'">
<!-- 		<div class="place"> -->
			<p class="place-title">${i.hName }</p>
			<p>분류 : ${i.hFilter }</p>
			<p>주소 : ${i.hAddress }</p>
			<p>운영시간 : ${i.hTime }</p>
			<p>연락처 : ${i.hTel }</p>
			<p>기타사항 : ${i.hContent }</p>
			<p>사이트 주소 : ${i.hDomain }</p>
		</div>
		<c:if test="${i.userNo eq userno }">
			<div class="delete-place" id="delete">
				<button type="button" class="btn" 
				onclick="location.href='<%=request.getContextPath() %>/hangout/delete?hNo=${i.hNo }'
				 confirm('정말 삭제하시겠습니까?')">삭제</button>
			</div>
		</c:if>
	</div>
	</c:forEach>
    