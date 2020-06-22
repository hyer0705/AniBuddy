<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/layout/header.jsp"></jsp:include>
<script type="text/javascript" src="../js/user-update.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	// 프로필 이미지가 존재할 시 프로필 이미지 가져오기
	if(${isProfile }){
		var url = "../upload/${userProfile.storeName }"
		$(".profile-image")
			.css({
				"background-image": "url("+ url +")"
			})
	}
	
	// 성별 선택하기
	$("#gender").val('${user.gender}').prop("selected", true)
	
	// 반려동물 선택하기
	$("#animal").val('${user.animal}').prop("selected", true)
	
	// 전문가/비전문가 선택하기
	$("input:radio[name='is_expert']:radio[value='${user.isExpert}']").prop("checked", true)
	
	// 생년월일 월 선택하기
	$("#mm").val('${bmonth}').prop("selected", true)
	
	
	/* form submit 이벤트시 */
	$("#join-form").on("submit", function(){
		
		// 반려동물이 비어있을 시에		
		if(${not empty user.animal } && $("#animal").val() == '' ){
			$("#animal option:eq(1)").attr("selected", "selected")
		}

		// 휴대폰 번호가 비어있을 시에
		if(${not empty user.tel } && $("#tel").val() == ''){
			$("#tel").val("null")
			
		} else if(${not empty user.tel } && $("#tel").val() != ''){
			$("#tel").focus().select()
			
			return false
		}
	}) // form submit event - end
	
}) // $(document).ready(function(){ - end

</script>

<main class="wrapper">
<div class="mypage-content">
<div class="join-cont marginTop0">
<div class="join-cont__join">
		<form action="<%=request.getContextPath() %>/mypage/userupdate" method="post"
		class="join-cont__join-form" id="join-form" enctype="multipart/form-data" >

		<!-- 프로필 이미지 -->
		<div class="join-form__group">
			<div class="profile__title">
			<label class="label-margin full-size">프로필</label>
			</div>
			<div class="profile__file">
			<div class="profile-image"></div>
			<div class="profile__desc">
				<span>사진은 선택사항 입니다.</span><span>사진 크기는 가로 120px, 세로 120px 고정입니다.</span>
				
				<div class="profile-filebox">
					<input class="profile-filename" value="사진 선택" disabled="disabled" />
				
					<label for="user_profile">사진등록</label>
					<input type="file" name="user_profile" id="user_profile" class="upload-hidden" />
				</div>
			</div>
			</div>
		</div>

		<!-- 아이디 -->		
		<div class="join-form__group">
			<label for="user_id" class="label-margin full-size">아이디</label>
			<input type="text" name="user_id" id="user_id" class="full-size height-size45 focus" readonly="readonly" value="${user.userId }"/>
			<div id="err-id"></div>
			<input type="hidden" id="isId" name="isId" value="N" />
		</div>
		
		<!-- 비밀번호 -->		
		<div class="join-form__group">
			<label for="user_pw" class="label-margin full-size">비밀번호</label>
			<input type="password" name="user_pw" id="user_pw" class="full-size height-size45 focus" />
			<div id="err-pw"></div>
		</div>
		
		<!-- 비밀번호 확인 -->		
		<div class="join-form__group">
			<label for="check_pw" class="label-margin full-size">비밀번호 재확인</label>
			<input type="password" name="check_pw" id="check_pw" class="full-size height-size45 focus" />
			<div id="err-chkpw"></div>
		</div>
		
		<!-- 이름 -->		
		<div class="join-form__group">
			<label for="user_name" class="label-margin full-size">이름</label>
			<input type="text" name="user_name" id="user_name" class="full-size height-size45 focus"
			 value="${user.userName }" />
			<div id="err-name"></div>
		</div>

		<!-- 닉네임 -->		
		<div class="join-form__group">
			<label for="nick" class="label-margin full-size">닉네임</label>
			<input type="text" name="nick" id="nick" class="full-size height-size45 focus" value="${user.nick }"/>
			<div id="err-nick"></div>
			<input type="hidden" id="isNick" name="isNick" value="N" />
		</div>
				
		<!-- 성별 -->
		<div class="join-form__group">
			<label for="gender" class="label-margin full-size">성별</label>
			<select name="gender" id="gender" class="full-size height-size45 focus">
				<option value>성별</option>
				<option value="f" >여자</option>
				<option value="m" >남자</option>
			</select>
			<div id="err-gender"></div>
		</div>
		
		<!-- 생년월일 -->
		<div class="join-form__group">
			<label for="birYY" class="label-margin full-size">생년월일</label>
			
			<div class="birth-wrap display-flex">
				<div class="birth">
				<input type="text" name="bir_yy" id="yy" placeholder="년(4자)" maxlength="4"
				class="full-size height-size45 focus" value="${byear }" />
				</div>
				<div class="birth">
				<select name="bir_mm" id="mm" class="full-size height-size45 focus">
					<option value>월</option>
					<option value="01" >1</option>
					<option value="02" >2</option>
					<option value="03" >3</option>
					<option value="04" >4</option>
					<option value="05" >5</option>
					<option value="06" >6</option>
					<option value="07" >7</option>
					<option value="08" >8</option>
					<option value="09" >9</option>
					<option value="10" >10</option>
					<option value="11" >11</option>
					<option value="12" >12</option>
				</select>
				</div>
				<div class="birth">
				<input type="text" name="bir_dd" id="dd" placeholder="일" maxlength="2"
				class="full-size height-size45 focus" value="${bday }" />
				</div>
			</div>
			
			<div id="err-birth"></div>
			
		</div>
				
		<!-- 이메일 -->
		<div class="join-form__group">
			<label for="email" class="label-margin full-size">이메일</label>
			
			<div class="email-group">
			<input type="email" name="email" id="email" class="full-size height-size45 focus"
			required="required" value="${email[0] }"/>
			<span>@</span>
			
			<input type="text" name="e-domain" id="e-domain" list="e-domains"
			placeholder="이메일 직접입력" class="full-size height-size45 focus" value="${email[1] }">
		    <datalist id="e-domains">
		      <option value="gmail.com">
		      <option value="naver.com">
		      <option value="nate.com">
		      <option value="daum.com">
		    </datalist>
			</div>
			<div id="err-email"></div>
			<input type="hidden" id="isEmail" name="isEmail" value="N" />
		</div>
		
		<!-- 전화번호 -->
		<div class="join-form__group">
			<label for="tel" class="label-margin full-size">휴대폰 번호(선택사항)</label>
			<input type="tel" name="tel" id="tel" required="required"
			class="full-size height-size45 focus" value="${user.tel }"
			placeholder="예) 01000000000, '-' 빼고 입력해주세요" maxlength="11"/>
			<div id="err-tel"></div>
		</div>
		
		<!-- 주소 -->
		<div class="join-form__group">
		
		<label class="label-margin full-size">집 주소</label>
		<div class="addr_wrap1">
		<input type="text" id="sample6_postcode" placeholder="우편번호"
		class="half-size float-left focus">
		<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"
		class="half-size back-pink float-right focus btn-border">
		</div>
		
		<input type="text" id="sample6_address" placeholder="주소" 
		class="full-size height-size30 margin-top20 focus" name="first_addr" value="${user.firstAddr }">
		<input type="text" id="sample6_detailAddress" placeholder="상세주소" 
		class="full-size height-size30 margin-top20 focus" name="second_addr" value="${user.secondAddr }">
		<input type="hidden" id="sample6_extraAddress" placeholder="참고항목" 
		class="full-size height-size30 margin-top20 focus">
		<div id="err-addr"></div>
		</div>
		
		<!-- 반려동물 선택 -->
		<div class="join-form__group">
		<label for="animal" class="label-margin full-size">반려동물 선택(선택사항)</label>
		<select name="animal" id="animal" class="full-size height-size45 focus" >
			<option value="null">반려동물 선택</option>
			<option value="개">개</option>
			<option value="고양이">고양이</option>
			<option value="금붕어/열대어">금붕어/열대어</option>
			<option value="햄스터">햄스터</option>
			<option value="토끼">토끼</option>
			<option value="새">새</option>
			<option value="기타">기타</option>
		</select>			
			
		</div>
		
		<!-- 전문가인지 비전문가인지 선택 -->
		<div class="join-form__group">
		
		<label for="userAnimal" class="label-margin full-size">전문가 여부</label>
		
		<div class="radio-wrap">
		<div class="radio-wrap__nonexpert">
		<input type="radio" id="non-expert" name="is_expert" value="N" checked="checked" disabled="disabled" />
		<label for="non-expert">비전문가</label>
		</div>
		
		<div class="radio-wrap__expert">
		<input type="radio" id="expert" name="is_expert" value="Y" disabled="disabled" />
		<label for="expert">전문가</label>
		</div>
		</div>
		
		</div>
		
		</form>
	</div>
	
<div class="btn-wrap">
	<button type="button" id="btnUpdate" class="btn_type btn_primary btn-border btn-margin-auto" >회원 정보 수정</button>
	<button type="button" id="btnCancel" class="btn_type btn_primary btn-border btn-margin-auto">취소</button>
</div>
</div>

</div>

<div class="mypage-sidebar">
<ul>
	<li><a href="/anibuddy/mypage/main">마이페이지</a></li>
	<li><a href="/anibuddy/mypage/userupdate">회원정보</a></li>
	<li><a href="#">활동내역</a></li>
	<li><a href="#">쪽지함</a></li>
	<li><a href="#">1:1문의</a></li>
	<li><a href="#">회원탈퇴</a></li>
</ul>
</div>
</main>

<!-- footer float 속성에 대한 피해 막기 -->
<div class="clear"></div>

<!-- 주소 api -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>

<jsp:include page="/layout/footer.jsp"></jsp:include>
