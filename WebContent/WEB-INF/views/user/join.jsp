<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AniBuddy</title>

<!-- jQuery 라이브러리 2.2.4.min.js -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- css -->
<link rel="stylesheet" href="../css/layout.css" />
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/join.css">

<!-- js -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/join.js"></script>

<style type="text/css">
html, body {
	box-sizing: border-box;
}
.input-empty{
	display: none;
}

</style>
</head>
<body>

<main class="join-cont">
	<!-- 로고 -->
	<div class="join-cont__logo" id="logo"></div>
	
	<!-- 회원가입 폼 -->
	<div class="join-cont__join">
		<form action="<%=request.getContextPath() %>/user/join" method="post"
		class="join-cont__join-form" id="join-form" >

		<!-- 아이디 -->		
		<div class="join-form__group">
			<label for="user_id" class="label-margin full-size">아이디</label>
			<input type="text" name="user_id" id="user_id" class="full-size height-size45 focus" />
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
			<input type="text" name="user_name" id="user_name" class="full-size height-size45 focus"/>
			<div id="err-name"></div>
		</div>

		<!-- 닉네임 -->		
		<div class="join-form__group">
			<label for="nick" class="label-margin full-size">닉네임</label>
			<input type="text" name="nick" id="nick" class="full-size height-size45 focus"/>
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
				class="full-size height-size45 focus"/>
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
				class="full-size height-size45 focus">
				</div>
			</div>
			
			<div id="err-birth"></div>
			
		</div>
				
		<!-- 이메일 -->
		<div class="join-form__group">
			<label for="email" class="label-margin full-size">이메일</label>
			
			<div class="email-group">
			<input type="email" name="email" id="email" class="full-size height-size45 focus"
			required="required"/>
			<span>@</span>
			
			<input type="text" name="e-domain" id="e-domain" list="e-domains"
			placeholder="이메일 직접입력" class="full-size height-size45 focus">
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
			<input type="text" name="tel" id="tel" required="required"
			class="full-size height-size45 focus"
			placeholder="예) 01000000000, '-' 빼고 입력해주세요" />
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
		class="full-size height-size30 margin-top20 focus" name="first_addr">
		<input type="text" id="sample6_detailAddress" placeholder="상세주소" 
		class="full-size height-size30 margin-top20 focus" name="second_addr">
		<input type="text" id="sample6_extraAddress" placeholder="참고항목" 
		class="full-size height-size30 margin-top20 focus">
		<div id="err-addr"></div>
		</div>
		
		<!-- 반려동물 선택 -->
		<div class="join-form__group">
		<label for="animal" class="label-margin full-size">반려동물 선택(선택사항)</label>
		<select name="animal" id="animal" class="full-size height-size45 focus" >
			<option value>반려동물 선택</option>
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
		<input type="radio" id="non-expert" name="is_expert" value="N" checked="checked" />
		<label for="non-expert">비전문가</label>
		</div>
		
		<div class="radio-wrap__expert">
		<input type="radio" id="expert" name="is_expert" value="Y" />
		<label for="expert">전문가</label>
		</div>
		</div>
		
		</div>
		
		</form>
	</div>
	
	<div class="btn-wrap">
		<button type="button" id="btnJoin" class="btn_type btn_primary btn-border" >회원가입</button>
		<button type="button" id="btnCancel" class="btn_type btn_primary btn-border">취소</button>
	</div>
	
</main> 

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

<%-- footer --%>
<jsp:include page="/layout/footer.jsp"></jsp:include>