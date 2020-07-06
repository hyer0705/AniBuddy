<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/layout/header.jsp"></jsp:include>

<c:if test="${empty login }">
	<script>
		alert("로그인 후 이용 가능합니다")
		location.href="<%=request.getContextPath() %>/info/place";
	</script>
</c:if>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info.css" />


<div class="top">
	<h2>알거리 <span class="h2-inner"> 장소 추가</span></h2>
</div>

<div class="a-content">

<form class="form-horizontal" action="<%=request.getContextPath() %>/info/insert" method="post"> <!-- enctype="multipart/form-data" -->
	
	<div class="form-group">
		<label class="control-label col-sm-2 lab" for="name">장소명</label>
		<div class="col-sm-10"><input type="text" id="name" name="name" class="form-control add" placeholder="장소명을 입력해주세요" required="required"></div>
	</div>
	
	<div class="form-group">
			<label class="control-label col-sm-2 lab">종류</label>
			<div class="col-sm-10">
				<label class="radio-inline"><input type="radio" id="filter" name="filter" value="동물병원">동물병원</label>
				<label class="radio-inline"><input type="radio" id="filter" name="filter" value="펫샵">펫샵</label>
				<label class="radio-inline"><input type="radio" id="filter" name="filter" value="유기동물보호소">유기동물 보호소</label>
				<label class="radio-inline"><input type="radio" id="filter" name="filter" value="기타" checked="checked">기타</label>
			</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-sm-2 lab">영업시간</label>
			<div class="col-sm-10">
			<input type="time" id="startTime" name="startTime" class="form-control time">
			&nbsp; ─ &nbsp; 
			<input type="time" id="endTime" name="endTime" class="form-control time">
			</div>
	</div>
	
	<div class="form-group">
		<label for="address" class="control-label col-sm-2 lab">주소</label>
		<div class="col-sm-10">
			<select id="city1" name="city1" onchange="categoryChange(this)" class="form-control ad1" required="required">
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
			<select id="city2" name="city2" class="form-control ad2" required="required">
				<option value="">시/군/구</option>
			</select>
		
			<input type="text" class="form-control add ad3" id="address" name="address" placeholder="상세주소를 입력해주세요" required="required">
		</div>
	</div>
	
<!-- 	<div class="form-group"> -->
<!-- 		<label class="control-label col-sm-2 lab">사진 첨부</label> -->
<!-- 		<div class="col-sm-10">  -->
<!-- 		<input type="file" id="file" name="file" > -->
<!-- 		<label class="file" for="file">사진 선택</label> -->
<!-- 		<div id="preview"></div> -->
<!-- 		</div> -->
<!-- 	</div> -->
	
	<div class="form-group">
		<label class="control-label col-sm-2 lab">연락처</label>
		<div class="col-sm-10">
			<select name="tel1" class="form-control tel">
				<option value="02" selected="selected">02</option>
				<option value="031">031</option>
				<option value="010">010</option>
				<option value="011">011</option>
				<option value="016">016</option>
				<option value="032">032</option>
			</select>
			 - <input type="text" name="tel2" class="form-control tel">
			 - <input type="text" name="tel3" class="form-control tel">
		</div>
	</div>
	
	<div class="form-group">
		<label for="domail" class="control-label col-sm-2 lab">사이트</label>
		<div class="col-sm-10"><input type="text" class="form-control add" name="domain" id="domain" placeholder="ex)  www.Anibuddy.com"></div>
	</div>
	
	<div class="form-group">
		<label for="content" class="control-label col-sm-2 lab">특이사항</label>
		<div class="col-sm-10"><textarea id="content" name="content" placeholder="내용을 입력해주세요" class="form-control content" rows="4"></textarea></div>
	</div>
	
<div class="btnArea">
<button class="btn btnCancel" type="button" onclick="location.href='javascript:history.go(-1)'">취소</button>
<button class="btn btnAdd">추가하기</button>
</div>

</form>

</div>



<script type="text/javascript">

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