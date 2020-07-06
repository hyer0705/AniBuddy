<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty msg }">
	<script>
	alert('${msg }')
	</script>
</c:if>
    
<c:forEach items="${list }" var="i">

	<div class="row" id="row">
	  <div class="col-sm-6 col-md-4">
	    <div class="thumbnail">
	      <div class="caption" onclick="lcoation.href='<%=request.getContextPath()%>/info/view?iNo=${i.iNo}'">
	        <h3>${i.iName }</h3>
	        <p>분류 : ${i.iFilter }</p>
			<p>주소 : ${i.iAddress }</p>
			<p>운영시간 : ${i.iTime }</p>
			<p>연락처 : ${i.iTel }</p>
			<p>기타사항 : ${i.iContent }</p>
	      </div>
			<div class="btnP">	
				<button type="button" class="btn" id="btnView" 
				onclick="location.href='<%=request.getContextPath()%>/info/view?iNo=${i.iNo}'">상세보기</button>
				<c:if test="${i.userNo eq userno  || adminLogin}">
			        <button type="button" class="btn" id="btnDelete"
			        onclick="if(confirm('정말 삭제하시겠습니까?')==true){location.href='<%=request.getContextPath() %>/info/delete?iNo=${i.iNo }'} else return;">삭제</button>
				</c:if>
			</div>
			<div class="like" id="${i.iNo }" >
				<c:if test="${not empty login }">
				<div class="like1">
					<img src='<%=request.getContextPath() %>/img/like.png' width='70'/>
				</div>
				</c:if>
				<div class="like2">
					<img src="<%=request.getContextPath() %>/img/like2.png" width="70"/>
				</div>
				<c:forEach items="${bmList }" var="b">
					<c:if test="${(b.iNo eq i.iNo) and (b.userNo eq userno)}">
						<script>$("#${i.iNo} .like2").css('display', 'inline-block');</script>
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
	      <div class="caption" onclick="lcoation.href='<%=request.getContextPath()%>/info/view?iNo=${i.iNo}'">
	        <h3>${i.iName }</h3>
	        <p>분류 : ${i.iFilter }</p>
			<p>주소 : ${i.iAddress }</p>
			<p>운영시간 : ${i.iTime }</p>
			<p>연락처 : ${i.iTel }</p>
			<p>기타사항 : ${i.iContent }</p>
	      </div>
			<div class="btnP">	
				<button type="button" class="btn" id="btnView" 
				onclick="location.href='<%=request.getContextPath()%>/info/view?iNo=${i.iNo}'">상세보기</button>
				<c:if test="${i.userNo eq userno || adminLogin }">
			        <button type="button" class="btn" id="btnDelete"
			        onclick="if(confirm('정말 삭제하시겠습니까?')==true){location.href='<%=request.getContextPath() %>/info/delete?iNo=${i.iNo }'} else return;">삭제</button>
				</c:if>
			</div>
			<div class="like" id="${i.iNo }" >
				<c:if test="${not empty login }">
				<div class="like1">
					<img src='<%=request.getContextPath() %>/img/like.png' width='70'/>
				</div>
				</c:if>
				<div class="like2">
					<img src="<%=request.getContextPath() %>/img/like2.png" width="70"/>
				</div>
				<c:forEach items="${bmList }" var="b">
					<c:if test="${(b.iNo eq i.iNo) and (b.userNo eq userno)}">
						<script>$("#${i.iNo} .like2").css('display', 'inline-block');</script>
					</c:if>
				</c:forEach>
			</div>
	    </div>
	  </div>
	</div>
	
	</c:forEach>