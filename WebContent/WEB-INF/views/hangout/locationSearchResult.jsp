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
		<div class="place" id="place" onclick="searchMap('${i.hAddress}', '${i.hName }')">
			<p class="place-title">${i.hName }</p>
			<p>분류 : ${i.hFilter }</p>
			<p>주소 : ${i.hAddress }</p>
			<p>운영시간 : ${i.hTime }</p>
			<p>연락처 : ${i.hTel }</p>
			<p>기타사항 : ${i.hContent }</p>
		</div>
		<div class="btnL">
		<c:if test="${i.userNo eq userno  || adminLogin}">
			<div class="delete-place" id="delete">
				<button type="button" class="btn b-delete" id="btnDelete" 
				onclick="if(confirm('정말 삭제하시겠습니까?')==true){location.href='<%=request.getContextPath() %>/hangout/deleteLocation?hNo=${i.hNo }'} else return;">삭제</button>
			</div>
		</c:if>
		</div>
		
		<div class="l-like" id="${i.hNo }" >
			<c:if test="${not empty login }">
			<div class="l-like1">
				<img src='<%=request.getContextPath() %>/img/like.png' width='60' />
			</div>
			</c:if>
			<div class="l-like2">
				<img src="<%=request.getContextPath() %>/img/like2.png" width="60"/>
			</div>
			<c:forEach items="${bmList }" var="b">
				<c:if test="${(b.hNo eq i.hNo) and (b.userNo eq userno)}">
					<script>$("#${i.hNo} .l-like2").css('display', 'inline-block');</script>
				</c:if>
			</c:forEach>
		</div>
	</div>
	</c:forEach>
	
	<c:forEach items="${placeList }" var="i">
	<div class="placeContent">
		<div class="place" id="place" onclick="searchMap('${i.hAddress}', '${i.hName }')">
			<p class="place-title">${i.hName }</p>
			<p>분류 : ${i.hFilter }</p>
			<p>주소 : ${i.hAddress }</p>
			<p>운영시간 : ${i.hTime }</p>
			<p>연락처 : ${i.hTel }</p>
			<p>기타사항 : ${i.hContent }</p>
		</div>
		<div class="btnL">
		<c:if test="${i.userNo eq userno  || adminLogin}">
			<div class="delete-place" id="delete">
				<button type="button" class="btn" id="btnDelete" 
				onclick="if(confirm('정말 삭제하시겠습니까?')==true){location.href='<%=request.getContextPath() %>/hangout/deleteLocation?hNo=${i.hNo }'} else return;">삭제</button>
			</div>
		</c:if>
		</div>
		
		<div class="l-like" id="${i.hNo }" >
			<c:if test="${not empty login }">
			<div class="l-like1">
				<img src='<%=request.getContextPath() %>/img/like.png' width='60' />
			</div>
			</c:if>
			<div class="l-like2">
				<img src="<%=request.getContextPath() %>/img/like2.png" width="60"/>
			</div>
			<c:forEach items="${bmList }" var="b">
				<c:if test="${(b.hNo eq i.hNo) and (b.userNo eq userno)}">
					<script>$("#${i.hNo} .l-like2").css('display', 'inline-block');</script>
				</c:if>
			</c:forEach>
		</div>
	</div>
	</c:forEach>