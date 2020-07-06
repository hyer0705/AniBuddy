<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="/layout/header.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info.css" />


<script type="text/javascript">
$(document).ready(function() {

	   $("#btnSearch").click(function() {

		   $.ajax({
				type: "post"
				, url: "<%=request.getContextPath() %>/info/placeSearch"
				, dataType: "html"
				, data: {
					city1: $("#city1").val()
					, city2: $("#city2").val()
					, i_filters: $("#i_filters").val()
				}
				, error: function(error) {
					console.log(error)
					alert("[ERROR]검색 에러")
					
				}
				, success: function(h) {
					$(".p-searchList").html(h)

					$("#load").css('display', 'inline-block');
					var total_li = $(".p-searchList .row").length;
					var view_list = 6;
					
					for (var i=0; i<view_list;i++ )	{
						$(".p-searchList .row").eq(i).css('display', 'inline-block');
					}
					
					if($(".row:visible").size()==total_li) {
						$("#load").css('display', 'none');
					}				
					
					$("#load").click(function() {
				
							view_list += 6;
				
							for (var i=0; i<view_list;i++ )	{
								$(".p-searchList .row").eq(i).fadeIn();
								$(".p-searchList .row").eq(i).css('display', 'inline-block');
							}
							
							if($(".row:visible").size()==total_li) {
								$("#load").css('display', 'none');
							}
				
					})

					
				}
			
				
			})


	   });

	   
	   
	   $("#btnAdd").click(function() {
			location.href="<%=request.getContextPath() %>/info/insert";
		});
		

	   $("#btnLocation").click(function() {
		   location.href="<%=request.getContextPath() %>/info/location";
	   });
	   
	   
});
</script>


<div class="top">
	<h2>알거리 <span class="h2-inner"> 장소 검색</span></h2>
		
	<div class="top-menu">
		<button class="btn b-plc" id="btnLocation">위치검색</button>
		<button class="btn b-add" id="btnAdd">장소추가</button>
	</div>
</div>


<div class="p-content">


<div class="frm">

<form class="form" id="form">

	<div class="c1">
		<div class="contentTitle">위치</div> 
			<select id="city1" name="city1" onchange="categoryChange(this)" class="form-control select-city">
				<option value="" selected>시/도</option>
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
				<option value="" selected>시/군/구</option>
			</select>
	</div>
	
	<div class="c2">
		<div class="contentTitle">장소</div>	
			<input type="button" id="animal-hospital" name="i_filter" value="동물병원" class="btn btnF">
			<input type="button" id="petShop" name="i_filter" value="펫샵" class="btn btnF">
			<input type="button" id="center" name="i_filter" value="유기동물보호소" class="btn btnF">
			<input type="button" id="etc" name="i_filter" value="기타" class="btn btnF">
	</div>
	
	<div class="c3">
		<div class="contentTitle">필터</div>
		<div class="filter">
<!-- 			<input type="button" value="전체" class="btn btnAll"> -->
			<div class="filter-select"></div>
			<input type="hidden" id="i_filters" name="i_filters" />
							
			<button type="button" class="btn" id="btnSearch"><img alt="검색" src="<%=request.getContextPath() %>/img/search.png" width="25px" height="25px"></button>
		</div>
	</div>
	
</form>

</div>

<div class="m-image"></div>

<div class="p-searchList">
	<c:forEach items="${list }" var="i">

	<div class="row" id="row">
	  <div class="col-sm-6 col-md-4">
	    <div class="thumbnail">
	      <div class="caption">
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
				<c:if test="${(i.userNo eq userno)  || adminLogin }">
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
</div>

	<div id="load">더보기</div>


</div>


<style>
.row {
	display: none;
}
</style>


<script type="text/javascript">
	
$(document).ready(function() {
	
	var total_li = $(".p-searchList .row").length;

	var view_list = 6;
	
	for (var i=0; i<view_list;i++ )	{
		$(".p-searchList .row").eq(i).css('display', 'inline-block');
	}

	
	$("#load").click(function() {

			view_list += 6;

			for (var i=0; i<view_list;i++ )	{
				$(".p-searchList .row").eq(i).fadeIn();
				$(".p-searchList .row").eq(i).css('display', 'inline-block');
			}
			
			if($(".row:visible").size()==total_li) {
				$("#load").css('display', 'none');
			}

	})

	
	
	
	$('.btnF').on('click', function() {
		
		if($(this).hasClass("pink")) {
			
			$(this).removeClass("pink");
			$(this).addClass("btnF");
			
			var child = $(this).val();
			$(".filter-select").children('.' + child).remove();

		} else {
			
			$(this).addClass("pink");
			$(this).removeClass("btnF");
			
			var h = $(".filter-select").html();
			
			h += "<span class='text " + $(this).val() + "'>" + $(this).val() + "</span> ";
			$(".filter-select").html(h);
			
		}
		
		$("#i_filters").val($(".filter-select").text())
		
	});
	
	
	$(document).on("click", ".like1", function() {
		var iNo = $(this).closest(".like").attr("id");
		   $.ajax({
				type: "post"
				, url: "<%=request.getContextPath() %>/info/bmInsert"
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
	
	
	$(document).on("click", ".like2", function() {
		var iNo = $(this).closest(".like").attr("id");
		   $.ajax({
				type: "post"
				, url: "<%=request.getContextPath() %>/info/bmDelete"
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