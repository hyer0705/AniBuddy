<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<jsp:include page="/layout/header.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/hangout.css" />


<script type="text/javascript">
$(document).ready(function() {
	   
	
	   $("#btnSearch").click(function() {

		   $.ajax({
				type: "post"
				, url: "<%=request.getContextPath() %>/hangout/placeSearch"
				, dataType: "html"
				, data: {
					city1: $("#city1").val()
					, city2: $("#city2").val()
					, h_filters: $("#h_filters").val()
				}
				, error: function(error) {
					console.log(error)
					alert("[ERROR]검색 에러")
					
				}
				, success: function(h) {
					console.log(h)
					$(".searchList").html(h)
					
				}
			
				
			})


	   });
	   
	   
	   $("#btnLocation").click(function() {
		   location.href="<%=request.getContextPath() %>/hangout/location";
	   });
	   
	   $("#btnAdd").click(function() {
			location.href="<%=request.getContextPath() %>/hangout/insert";
	   });
	   
});


</script>


<div class="top">
	<h2>놀거리 <span class="h2-inner"> 장소 검색</span></h2>
		
	<div class="top-menu">
		<button class="btn btn-info" id="btnLocation">위치검색</button>
		<button class="btn btn-info" id="btnAdd">장소추가</button>
	</div>
</div>



<div class="p-content">

<div class="frm">
<form class="form">

	<div class="c1">
		<span class="contentTitle">위치</span> 
			<select id="city1" name="city1" onchange="categoryChange(this)" class="form-control select-city">
				<option value="">시/도</option>
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
			<select id="city2" name="city2" class="form-control select-city">
				<option value="">시/군/구</option>
			</select>
	</div>
	
	<div class="c2">
		<span class="contentTitle">장소</span>	
			<input type="button" id="cafe" name="h_filter" value="카페" class="btn btnF">
			<input type="button" id="restaurant" name="h_filter" value="식당" class="btn btnF">
			<input type="button" id="camp" name="h_filter" value="캠핑" class="btn btnF">
			<input type="button" id="field" name="h_filter" value="운동장" class="btn btnF">
			<input type="button" id="pension" name="h_filter" value="펜션" class="btn btnF">
			<input type="button" id="etc" name="h_filter" value="기타" class="btn btnF">
	</div>
	
	<div>
		<span class="contentTitle">행사</span>	
			<input type="button" id="expo" name="h_filter" value="박람회" class="btn btnF">
			<input type="button" id="festival" name="h_filter" value="축제" class="btn btnF">
			<input type="button" id="contest" name="h_filter" value="대회" class="btn btnF">
	</div>
	
	<div class="c3">
		<span class="contentTitle">필터</span>
		<div class="filter">
			<input type="button" value="전체" class="btn btnAll">
			<div class="filter-select"></div>
			<input type="hidden" id="h_filters" name="h_filters" />
							
			<button type="button" class="btn" id="btnSearch"><img alt="검색" src="../img/search.png" width="25px" height="25px"></button>
		</div>
	</div>
	
</form>

</div>

<div class="searchList">
	
<c:forEach items="${list }" var="i">
	<div class="placeContent">
		<div class="placeImg" id="placeImg" onclick="location.href='<%=request.getContextPath() %>/hangout/view?hNo=${i.hNo}'">
			<c:forEach items="${fileList }" var="j">
				<c:if test="${j.hNo eq i.hNo }">
					<div>
						<img src="<%=request.getContextPath() %>/upload/${j.storedName }" alt="장소 이미지" width="320" height="220">
					</div> 
				</c:if>
			</c:forEach>
		</div>
		<div class="place" id="place" onclick="location.href='<%=request.getContextPath() %>/hangout/view?hNo=${i.hNo}'">
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
	</div>
</c:forEach>
	

</div>


</div>




<script type="text/javascript">
$(document).ready(function() {
	
	
	$('.btnF').on('click', function() {
		
		if($(this).hasClass("pink")) {
			
			$(this).removeClass("pink");
			
			var child = $(this).val();
			$(".filter-select").children('.' + child).remove();

		} else {
			
			$(this).addClass("pink");
			
			var h = $(".filter-select").html();
			
			h += "<span class='text " + $(this).val() + "'>" + $(this).val() + "</span> ";
			$(".filter-select").html(h);
			
		}
		
		$("#h_filters").val($(".filter-select").text())
		
	});
	
	
	$('.btnAll').on('click', function() {
		
		if($(this).hasClass("blue")) {
			
			$(this).removeClass("blue");
			$(".btnF").removeClass("pink");
			
			$(".filter-select").children().remove();
			$("#h_filters").val($(".filter-select").text())

		} else {
			
			$(this).addClass("blue");
			$(".btnF").addClass("pink");
			
			var h = $(".filter-select").html();
			
			for(i in $("input").filter(".btnF")) {
				if($(".filter-select").children().length<9) {
					h += "<span class='text " + $("input").filter(".btnF").eq(i).val() + "'>" + $("input").filter(".btnF").eq(i).val() + "</span> ";
					$(".filter-select").html(h);
				}
			}

			$("#h_filters").val($(".filter-select").text())
		}
		
	});
	
	
});



function categoryChange(e) {
	   
	var target = document.getElementById("city2");

	for(y in e) {
		if(e.value == "서울특별시") { var d = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"] }
		else if (e.value == "인천광역시") { var d = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"] }
		else if (e.value == "대전광역시") { var d = ["대덕구","동구","서구","유성구","중구"] }
		else if (e.value == "광주광역시") { var d = ["광산구","남구","동구","북구","서구"] }
		else if (e.value == "대구광역시") { var d = ["남구","달서구","동구","북구","서구","수성구","중구","달성군"] }
		else if (e.value == "울산광역시") { var d = ["남구","동구","북구","중구","울주군"] }
		else if (e.value == "부산광역시") { var d = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"] }
		else if (e.value == "경기도") { var d = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"] }
		else if (e.value == "강원도") { var d = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"] }
		else if (e.value == "충청북도") { var d = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"] }
		else if (e.value == "충청남도") { var d = ["계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"] }
		else if (e.value == "전라북도") { var d = ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"] }
		else if (e.value == "전라남도") { var d = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"] }
		else if (e.value == "경상북도") { var d = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"] }
		else if (e.value == "경상남도") { var d = ["거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"] }
		else if (e.value == "제주도") { var d = ["서귀포시","제주시","남제주군","북제주군"] }
	}

	target.options.length = 1;

	for (x in d) {
		var opt = document.createElement("option");
		opt.value = d[x];
		opt.innerHTML += d[x];
		target.appendChild(opt);
	}	
}

</script>



<jsp:include page="/layout/footer.jsp"></jsp:include>
