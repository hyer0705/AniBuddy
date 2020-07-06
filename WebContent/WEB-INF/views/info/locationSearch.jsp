<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="/layout/header.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info.css" />


<script type="text/javascript">
$(document).ready(function() {
	
	$("#search").keydown(function(e) {
		if(e.keyCode == 13 && $(this).val() != ""){
			$("#btnSearch").click();
		}
	})

	$("#btnSearch").click(function() {
	
		if(!$.trim($("#search").val())) {
			alert("검색어를 입력하세요")
		} else {
			
		   $.ajax({
				type: "post"
				, url: "<%=request.getContextPath() %>/info/locationSearch"
				, dataType: "html"
				, data: {
					city1: $("#location-city1").val()
					, name: $("#search").val()
				}
				, error: function() {
					alert("[ERROR]검색 에러")
					
				}
				, success: function(h) {
					$(".searchList").html(h)
					
				}
			})
		
		}
		
	}) //btnSearch.onclick end
	
	
	$("#btnPlace").click(function() {
		location.href = "<%=request.getContextPath()%>/info/place";
	})
	
	$("#btnAdd").click(function() {
		location.href = "<%=request.getContextPath()%>/info/insert";
	})
	
	
	$("#location-city1").change(function() {
		
		if($("#location-city1").val()) {
		   $.ajax({
				type: "post"
				, url: "<%=request.getContextPath() %>/info/citySearch"
				, dataType: "html"
				, data: {
					city1: $("#location-city1").val()
				}
				, error: function() {
					alert("[ERROR]검색 에러")
					
				}
				, success: function(h) {
					$(".searchList").html(h)
					
				}
			})
		}
		
	})
	
	
	$(document).on("click", ".l-like1", function() {
		var iNo = $(this).closest(".l-like").attr("id");
		   $.ajax({
				type: "post"
				, url: "<%=request.getContextPath() %>/info/bmInsertLocation"
				, dataType: "html"
				, data: {
					iNo: iNo
				}
				, error: function() {
					alert("[ERROR]즐겨찾기 추가 오류")
				}
			})
			
			$(this).next().css('display', 'inline-block');
	});
	
	
	$(document).on("click", ".l-like2", function() {
		var iNo = $(this).closest(".l-like").attr("id");
		   $.ajax({
				type: "post"
				, url: "<%=request.getContextPath() %>/info/bmDeleteLocation"
				, dataType: "html"
				, data: {
					iNo: iNo
				}
				, error: function() {
					alert("[ERROR]즐겨찾기 삭제 오류")
				}
			})
			$(this).css('display', 'none');
	});
	
	

	
});
</script>



<div class="top">
	<h2>알거리 <span class="h2-inner"> 위치 검색</span></h2>
		
	<div class="top-menu">
		<button class="btn b-plc" id="btnPlace">장소검색</button>
		<button class="btn b-add" id="btnAdd">장소추가</button>
	</div>
</div>

<div class="loc-content">

<div class="search-form" id="search-form">
	<input type="text" name="search" id="search">
	<button type="button" id="btnSearch">
		<img src="<%=request.getContextPath()%>/img/search.png" width="30" height="30">
	</button>

	<div class="city1">
		<label for="location-city1" class="city-icon">
			<span class="glyphicon glyphicon-map-marker"></span>
		</label>
		<select id="location-city1" name="city1" class="select-city1">
			<option value="" selected>지역 선택</option>
			<option value='서울특별시'>서울특별시</option>
			<option value='인천광역시'>인천광역시</option>
			<option value='대전광역시'>대전광역시</option>
			<option value='광주광역시'>광주광역시</option>
			<option value='대구광역시'>대구광역시</option>
			<option value='울산광역시'>울산광역시</option>
			<option value='부산광역시'>부산광역시</option>
			<option value='경기도'>경기도</option>
			<option value='강원도'>강원도</option>
			<option value='충청북도'>충청북도</option>
			<option value='충청남도'>충청남도</option>
			<option value='전라북도'>전라북도</option>
			<option value='전라남도'>전라남도</option>
			<option value='경상북도'>경상북도</option>
			<option value='경상남도'>경상남도</option>
			<option value='제주도'>제주도</option>
		</select>
	</div>
	
	<div id="searchMap" style="width:525px;height:605px;"></div>
</div>




<div class="searchList">
	<c:forEach items="${list }" var="i">
	<div class="placeContent">
		<div class="place" id="place" onclick="searchMap('${i.iAddress}', '${i.iName }')">
			<p class="place-title">${i.iName }</p>
			<p>분류 : ${i.iFilter }</p>
			<p>주소 : ${i.iAddress }</p>
			<p>운영시간 : ${i.iTime }</p>
			<p>연락처 : ${i.iTel }</p>
			<p>기타사항 : ${i.iContent }</p>
		</div>
		<div class="btnL">
		<c:if test="${(i.userNo eq userno)  || adminLogin }">
			<div class="delete-place" id="delete">
				<button type="button" class="btn b-delete" id="btnDelete" 
				onclick="if(confirm('정말 삭제하시겠습니까?')==true){location.href='<%=request.getContextPath() %>/info/deleteLocation?iNo=${i.iNo }'} else return;">삭제</button>
			</div>
		</c:if>
		</div>
		
		<div class="l-like" id="${i.iNo }" >
			<c:if test="${not empty login }">
			<div class="l-like1">
				<img src='<%=request.getContextPath() %>/img/like.png' width='60' />
			</div>
			</c:if>
			<div class="l-like2">
				<img src="<%=request.getContextPath() %>/img/like2.png" width="60"/>
			</div>
			<c:forEach items="${bmList }" var="b">
				<c:if test="${(b.iNo eq i.iNo) and (b.userNo eq userno)}">
					<script>$("#${i.iNo} .l-like2").css('display', 'inline-block');</script>
				</c:if>
			</c:forEach>
		</div>
	</div>
	</c:forEach>
</div>


</div>



<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6af615d6be453fdf0be75fb615861887&libraries=services"></script>
<script>
var mapContainer = document.getElementById('searchMap'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(37.499059, 127.032912), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};  

//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

function searchMap(ad, iname) {
	
	//주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	//주소로 좌표를 검색합니다
	geocoder.addressSearch(ad, function(result, status) {
	
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
	        content: '<div class="map-content">' + iname +'</div>'
	    });
	    infowindow.open(map, marker);
	
	    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	    map.setCenter(coords);
	}
	});    
}
</script>



<jsp:include page="/layout/footer.jsp"></jsp:include>