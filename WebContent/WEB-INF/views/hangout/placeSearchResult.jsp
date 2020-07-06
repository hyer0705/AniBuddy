<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty msg }">
	<script>
	alert('${msg }')
	</script>
</c:if>

<!-- <script type="text/javascript"> -->
<!-- $(document).ready(function() { -->
	
<!-- 	$(".thumb-image").each(function() { -->
<!-- 		if($(this).find("img").length == 0) { -->
<%-- 			$(this).prepend($("<img src='<%=request.getContextPath() %>/img/no-image.png' width='289.8' height='215'/>")) --%>
<!-- 		} -->
<!-- 	}) -->
<!-- </script> -->

    
<c:forEach items="${list }" var="i">

	<div class="row" id="row">
	  <div class="col-sm-6 col-md-4">
	    <div class="thumbnail">
	    	<div class="thumb-image">
	      	<c:forEach items="${fileList }" var="j">
			  <c:if test="${j.hNo eq i.hNo }">
	      		 <img src="<%=request.getContextPath() %>/upload/${j.storedName }" alt="장소 이미지" width="289.8" height="215">
	      	  </c:if>
			</c:forEach>
			</div>
	      <div class="caption" onclick="lcoation.href='<%=request.getContextPath()%>/hangout/view?hNo=${i.hNo}'">
	        <h3>${i.hName }</h3>
	        <p>분류 : ${i.hFilter }</p>
			<p>주소 : ${i.hAddress }</p>
			<p>운영시간 : ${i.hTime }</p>
			<p>연락처 : ${i.hTel }</p>
			<p>기타사항 : ${i.hContent }</p>
	      </div>
			<div class="btnP">	
				<button type="button" class="btn" id="btnView" 
				onclick="location.href='<%=request.getContextPath()%>/hangout/view?hNo=${i.hNo}'">상세보기</button>
				<c:if test="${i.userNo eq userno  || adminLogin}">
			        <button type="button" class="btn" id="btnDelete"
			        onclick="if(confirm('정말 삭제하시겠습니까?')==true){location.href='<%=request.getContextPath() %>/hangout/delete?hNo=${i.hNo }'} else return;">삭제</button>
				</c:if>
			</div>
			<div class="like" id="${i.hNo }" >
				<c:if test="${not empty login }">
				<div class="like1">
					<img src='<%=request.getContextPath() %>/img/like.png' width='70' />
				</div>
				</c:if>
				<div class="like2">
					<img src="<%=request.getContextPath() %>/img/like2.png" width="70"/>
				</div>
				<c:forEach items="${bmList }" var="b">
					<c:if test="${(b.hNo eq i.hNo) and (b.userNo eq userno)}">
						<script>$("#${i.hNo} .like2").css('display', 'inline-block');</script>
					</c:if>
				</c:forEach>
			</div>
	    </div>
	  </div>
	</div>
	
	</c:forEach>
	
	
	<c:forEach items="${placeList }" var="i">

	<div class="row" id="row">
	  <div class="col-sm-6 col-md-4">
	    <div class="thumbnail">
	    	<div class="thumb-image">
	      	<c:forEach items="${fileList }" var="j">
			  <c:if test="${j.hNo eq i.hNo }">
	      		  <img src="<%=request.getContextPath() %>/upload/${j.storedName }" alt="장소 이미지" width="289.8" height="215">
	      	  </c:if>
			</c:forEach>
			</div>
	      <div class="caption" onclick="lcoation.href='<%=request.getContextPath()%>/hangout/view?hNo=${i.hNo}'">
	        <h3>${i.hName }</h3>
	        <p>분류 : ${i.hFilter }</p>
			<p>주소 : ${i.hAddress }</p>
			<p>운영시간 : ${i.hTime }</p>
			<p>연락처 : ${i.hTel }</p>
			<p>기타사항 : ${i.hContent }</p>
	      </div>
			<div class="btnP">	
				<button type="button" class="btn" id="btnView" 
				onclick="location.href='<%=request.getContextPath()%>/hangout/view?hNo=${i.hNo}'">상세보기</button>
				<c:if test="${i.userNo eq userno  || adminLogin}">
			        <button type="button" class="btn" id="btnDelete"
			        onclick="if(confirm('정말 삭제하시겠습니까?')==true){location.href='<%=request.getContextPath() %>/hangout/delete?hNo=${i.hNo }'} else return;">삭제</button>
				</c:if>
			</div>
			<div class="like" id="${i.hNo }" >
				<c:if test="${not empty login }">
				<div class="like1">
					<img src='<%=request.getContextPath() %>/img/like.png' width='70' />
				</div>
				</c:if>
				<div class="like2">
					<img src="<%=request.getContextPath() %>/img/like2.png" width="70"/>
				</div>
				<c:forEach items="${bmList }" var="b">
					<c:if test="${(b.hNo eq i.hNo) and (b.userNo eq userno)}">
						<script>$("#${i.hNo} .like2").css('display', 'inline-block');</script>
					</c:if>
				</c:forEach>
			</div>
	    </div>
	  </div>
	</div>
	
	</c:forEach>