<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/layout/header.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/hangout.css" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="top">
	<h2>놀거리 <span class="h2-inner"> 장소 상세보기</span></h2>

	<div class="view-top-menu">
		<button type="button" class="btn view-list" onclick="location.href='javascript:history.go(-1)'">목록으로</button>
		<c:if test="${hangout.userNo eq userno || adminLogin}">
			<div class="delete-place" id="delete">
				<button type="button" class="btn view-delete" id="btnDelete"
				onclick="if(confirm('정말 삭제하시겠습니까?')==true){location.href='<%=request.getContextPath() %>/hangout/delete?hNo=${hangout.hNo }'} else return;">
				삭제하기</button>
			</div>
		</c:if>
	</div>

</div>

<div class="view-content">

<div class="info-box">
	<div class="place-name">
		<p class="hName">${hangout.hName }</p>
		<div class="filter-icon">#${hangout.hFilter }</div>
	</div>

	<div class="info">
		<div class="info-title">주소</div>
		<div class="info-content">${hangout.hAddress }</div>
	</div>

	<div class="info">
		<div class="info-title">영업시간</div>
		<div class="info-content">${hangout.hTime }</div>
	</div>

	<div class="info">
		<div class="info-title">연락처</div>
		<div class="info-content">${hangout.hTel }</div>
	</div>

	<div class="info">
		<div class="info-title">이메일</div>
		<div class="info-content">${hangout.hEmail }</div>
	</div>

	<div class="info">
		<div class="info-title">사이트</div>
		<div class="info-content"><a href="http://${hangout.hDomain }" target="_blank">${hangout.hDomain }</a></div>
	</div>

	<div class="info">
		<div class="info-title">특이사항</div>
		<div class="info-content">${hangout.hContent }</div>
	</div>
</div>

	
<div class="info-image">
	<img src="<%=request.getContextPath() %>/upload/${hangoutFile.storedName}" alt="장소 이미지" width="430" height="340"
	 onerror="this.src='<%=request.getContextPath() %>/img/no-image.png'">
</div>



<div id="map" style="width:700px;height:500px;"></div>


</div>



<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6af615d6be453fdf0be75fb615861887&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(37.499059, 127.032912), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};  

//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

//주소로 좌표를 검색합니다
geocoder.addressSearch('${hangout.hAddress}', function(result, status) {

// 정상적으로 검색이 완료됐으면 
 if (status === kakao.maps.services.Status.OK) {

    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: coords
    });

    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div class="map-content">${hangout.hName}</div>'
    });
    infowindow.open(map, marker);

    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);
} 
});    

</script>



<jsp:include page="/layout/footer.jsp"></jsp:include>


