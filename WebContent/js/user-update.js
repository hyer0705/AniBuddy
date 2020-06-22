/**
 * 
 */
$(document).ready(function(){
	
	// profile preview
	// input[type="file"]
	$("#user_profile").on("change", function( e ){
		
		var files = e.target.files // FileList 객체
		
		//FileReader 객체 생성
		var reader = new FileReader()
		//File 객체 정보를 모두 읽어서 메모리에 적재(load)한 이후에 동작
		reader.onload = function( ev ) {
			
			// 읽어들인 파일을 <profile-image> 에 프리뷰로 보여주기
			$(".profile-image")
				.css({
					"background-image": "url("+ ev.target.result +")"
				})
		}
		
		// FileReader 객체를 통해 File 객체 정보 읽기
		reader.readAsDataURL( files[0] ) // Blob 또는 File 형식으로 읽기
		
	})
	
	var fileTarget = $(".profile-filebox .upload-hidden")
	
	fileTarget.on("change", function(){ // 값이 변경되면
		if(window.FileReader){ // modern browser
			var filename = $(this)[0].files[0].name
		} else { // old IE
			// 파일명만 추출
			var filename = $(this).val().split("/").pop().split("\\").pop()
		}
	
		// 추출한 파일명 삽입
		$(this).siblings(".profile-filename").val(filename)
		
	})
	
	// 회원정보수정 버튼 클릭시 form submit 발생
	$("#btnUpdate").on("click", function(){
		$("#join-form").submit()
	})
	
	/* form submit 이벤트시 */
	$("#join-form").on("submit", function(){
		
		// 이름 검증
		if( $("#user_name").val() == ''){ // 이름 폼이 비어있을 때!
			
			// 이름 입력 폼으로 포커스
			$("#user_name").focus()
			// submit 중단
			return false
		} else if( $("#user_name").val() != ''){ // 이름 폼이 비어있지 않을 때!
			var nameReg = /^[가-힣]{2,}$/
			if( !nameReg.test( $("#user_name").val() ) ){
				
				// 이름 폼 비우고 포커스
				$("#user_name").val("")
								.focus()
				// submit 중단
				return false
			}
		}
		
		// 비밀번호 검증
		if( $("#user_pw").val() == '' && $("#check_pw").val() != ''){ // 비밀번호 폼이 비어있을 때!
			
			// 비밀번호 입력 폼으로 포커스
			$("#user_pw").focus()
			// submit 중단
			return false
		} else if( $("#user_pw").val() != '') { // 비밀번호 폼이 비어있지 않을 때!
			var pwReg = /^[A-Za-z0-9]{8,20}$/
			if( !pwReg.test( $("#user_pw").val() ) ){
				
				// 비밀번호 입력 폼 비우고 포커스
				$("#user_pw").val("")
							.focus()
				// submit 중단
				return false
			} else if( $("#check_pw").val() == '' ){
				// 비밀번호 확인 입력하도록 focus
				$("#check_pw").focus()
				
				// submit 중단
				return false
			}
		}
		
		// 닉네임 비어있는지 검증
		if( $("#nick").val() == ''){ // 닉네임 폼이 비어있을 때
			
			// 닉네임 폼으로 포커스
			$("#nick").focus()
			// submit 중단
			return false
		}
		
		// 성별 선택했는지 검증
		if( $("#gender").val() == ''){
			
			// 성별 select로 포커스
			$("#gender").focus()
			//submit 중단
			return false
		}
		
		// 생년월일 선택했는지 검증
		if($("#yy").val() == '' || $("#yy").val().length < 4){
			// 년도 입력 폼 포커스
			$("#yy").focus()
			
			return false
		} else if( $("#mm").val() == ''){
			// 월 select 포커스
			$("#mm").focus()
			
			return false
		} else if( $("#dd").val() == ''){
			// 일(날짜) 폼 focus
			$("#dd").focus()
			
			return false
		}
		
		// 이메일 비어있는지 확인
		if($("#email").val() == ''){
			$("#email").focus()
			
			return false
		}
		
		// 주소 비어있는지 검증
		if($("#sample6_address").val() == ''){
			
			// 주소 폼으로 focus
			$("#sample6_address").focus()
			return false
		}
		
		// 상세 주소 비어있는지 검증
		if( $("#sample6_detailAddress").val() == '' ){
			
			// 주소 폼으로 focus
			$("#sample6_detailAddress").focus()
			return false
		}
		
	}) // $("#join-form").on("submit", function(){ - end
	
	// 휴대폰 11자 맞는지 확인
	$("#tel").on("blur",function(){
		if($(this).val().length != 11){
// 			console.log("휴대폰 번호가 맞는지 확인하세요")	
			$("#err-tel")
				.html("휴대폰 번호가 11자리가 아닙니다!")
				.css("color", "red")
				.css("font-size", "10px")
		}
	}) // $("#tel").on("blur",function(){ - end
	
	// 닉네임, 이메일 중복확인(수정시)
	// 5. 닉네임
	$("#nick").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-nick")
		
		var reg // 정규식
		var msg // 메시지
		
		/* 에러메시지 띄워주는 함수 */
		if( $(this).val() == ''){ // 닉네임 폼이 비었을 경우 에러 메시지 띄우기
			emptyMsg(currNode, errNode)
		} else {
			// #nick : 닉네임은 2자 이상, 20자 이하의 한글과 영어대소문자, 숫자만 사용가능합니다.
			reg = /^[A-Za-z0-9가-힣]{2,20}$/
			msg = "닉네임은 2자 이상, 20자 이하의 한글과 영어대소문자, 숫자만 사용가능합니다."

			if( !reg.test( $(currNode).val() )){
				invalidMsg( currNode, reg, msg)
			} else {
				// 닉네임 중복 체크	
				$.ajax({
					type:"post"	// 요청 method
					, url: "../user/chkNick"
					, data: { // 전달 파라미터
						userNick: $(this).val()
					}
					, dataType: "json"
					, success: function( res ){
						console.log(res.msg)
						if(res.data){
							// 닉네임 존재
							$("#err-nick")
								.html(res.msg + "변경하지 않은셔도 됩니다.")
								.css("color", "red")
								.css("font-size", "10px")
							$("#isNick").val("N")
						} else {
							// 닉네임 존재하지 않음
							$("#err-nick")
								.html(res.msg)
								.css("color", "green")
								.css("font-size", "10px")
							$("#isNick").val("Y")
						} // else - end
					} // success: function( res ){ - end
					, error: function(){
						console.log("ajax 실패")
					}
				}) // $.ajax({ : end
			} // else end

		} // 바깥쪽 else end
	}) // $("#nick").on("blur", function(){ - end
		
	// 8. 이메일
	$("#email").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-email")
		
		/* 에러메시지 띄워주는 함수 */
// 		emptyMsg(currNode, errNode)
		
		/* 에러메시지 띄워주는 함수 */
		if( $(this).val() == ''){
			emptyMsg(currNode, errNode)
		} else if( $("#e-domain").val() != ''){
//			$(errNode).html("")
			$.ajax({
				type:"post"	// 요청 method
				, url: "../user/chkEmail"
				, data: { // 전달 파라미터
					userEmail: $("#email").val(),
					userEDomain: $("#e-domain").val()
				}
				, dataType: "json"
				, success: function( res ){
					console.log(res.msg)
					if(res.data){
						// 이메일 존재
						$("#err-email")
							.html(res.msg + "변경하지 않으셔도 됩니다.")
							.css("color", "red")
							.css("font-size", "10px")
						$("#isEmail").val("N")
					} else {
						// 이메일 존재 하지 않음
						$("#err-email")
							.html(res.msg)
							.css("color", "green")
							.css("font-size", "10px")
						$("#isEmail").val("Y")
					}
				}
				, error: function(){
					console.log("ajax 실패")
				}
			}) // $.ajax({ : end
		} // else if( $("#e-domain").val() != ''){
		
	}) // $("#email").on("blur", function(){ - end
	
	// 8-2. 이메일 도메인
	$("#e-domain").on("blur", function(){
		var currNode = $(this)
		var errNode = $("#err-email")
		
		/* 에러메시지 띄워주는 함수 */
		if( $("email").val() != '' && $(this).val() == ''){
			emptyMsg(currNode, errNode)
		} else if ($("#email").val() != ''){
				$.ajax({
				type:"post"	// 요청 method
				, url: "../user/chkEmail"
				, data: { // 전달 파라미터
					userEmail: $("#email").val(),
					userEDomain: $("#e-domain").val()
				}
				, dataType: "json"
				, success: function( res ){
					console.log(res.msg)
					if(res.data){
						// 이메일 존재
						$("#err-email")
							.html(res.msg)
							.css("color", "red")
							.css("font-size", "10px")
						$("#isEmail").val("N")
					} else {
						// 이메일 존재 하지 않음
						$("#err-email")
							.html(res.msg)
							.css("color", "green")
							.css("font-size", "10px")
						$("#isEmail").val("Y")
					}
				} // , success: function( res ){ - end
				, error: function(){
					console.log("ajax 실패")
				}
			}) // $.ajax({ : end
		} // } else if ($("#email").val() != ''){ - end
	}) // $("#e-domain").on("blur", function(){ - end
	
	// 이름, 닉네임(위에 있음), 성별, 생년월일, 이메일(위에 있음), 집주소 비어있을시
	// 필수 입력사항입니다. 띄워주기
	// 4. 이름
	$("#user_name").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-name")
		
		var reg // 정규식
		var msg // 메시지
		
		/* 에러메시지 띄워주는 함수 */
		if( $(this).val() == ''){
			emptyMsg(currNode, errNode)
		} else {
			// #user_name : 이름은 2자 이상의 한글만 사용 가능합니다.
			reg = /^[가-힣]{2,}$/
			msg = "이름은 2자 이상의 한글만 사용 가능합니다."

			if( !reg.test( $(currNode).val() )){
				invalidMsg( currNode, reg, msg)
			} else {
				$(errNode).html("")
			}
		} // } else { - end
		
	}) // $("#user_name").on("blur", function(){ - end
	
	// 6. 성별
	$("#gender").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-gender")
		
		/* 에러메시지 띄워주는 함수 */
		emptyMsg(currNode, errNode)
		
	}) // $("#gender").on("blur", function(){ - end
	
	// 7. 생년월일 이건 좀 생각해보기
	//	7-1. 년도(4자리수)
	$("#yy").on("blur", function(){
		
		if($(this).val() == '' || $(this).val().length < 4){
			$("#err-birth")
			.html("태어난 년도 4자리를 정확하게 입력해주세요!")
			.css("color", "red")
			.css("font-size", "10px")
		} else{
			$("#err-birth").html("")
		}
	}) // $("#yy").on("blur", function(){ - end
	//	7-2. 월
	$("#mm").on("blur", function(){
		if( $("#yy").val() != '' && $(this).val() == ''){
			$("#err-birth")
			.html("태어난 월을 선택해주세요!")
			.css("color", "red")
			.css("font-size", "10px")
		} else{
			$("#err-birth").html("")
		}
	}) // $("#mm").on("blur", function(){ - end
	//	7-3. 일
	$("#dd").on("blur", function(){
		if( $("#yy").val() != '' 
				&& $("#mm").val() != ''
				&& $(this).val() == '' ){
			$("#err-birth")
			.html("태어난 일(날짜) 2자리를 정확하게 입력하세요!")
			.css("color", "red")
			.css("font-size", "10px")
		} else{
			$("#err-birth").html("")
		}
	}) // $("#dd").on("blur", function(){ - end
	
	// 9. 주소
	$("#sample6_address").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-addr")
		
		/* 에러메시지 띄워주는 함수 */
		emptyMsg(currNode, errNode)
		
	}) // $("#sample6_address").on("blur", function(){ - end
	// 9.2 상세주소
	$("#sample6_detailAddress").on("blur", function(){
		var currNode = $(this)
		var errNode = $("#err-addr")
		
		console.log(currNode.val())
		/* 에러메시지 띄워주는 함수 */
		emptyMsg(currNode, errNode)
	}) // $("#sample6_detailAddress").on("blur", function(){ - end
	
	// 비밀번호 입력 시
	// 2. 비밀번호
	$("#user_pw").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-pw")
		
		var reg // 정규식
		var msg // 메시지
		
		/* 에러메시지 띄워주는 함수 */
		if( $("#check_pw").val() != '' && $(this).val() == ''){
			emptyMsg(currNode, errNode)
		} else {
// 			#user_pw : 비밀번호는 8글자 이상 20자 이하의 영어 대소문자와 숫자만 사용 가능합니다.
			reg = /^[A-Za-z0-9]{8,20}$/
			msg = "비밀번호는 8글자 이상 20자 이하의 영어 대소문자와 숫자만 사용 가능합니다.변경하지 않으실 거면 비워두세요."

			invalidMsg( currNode, reg, msg)
		}// 바깥쪽 else end
		
	}) // $("#user_pw").on("blur", function(){ end
	
	// 3. 비밀번호 확인
	$("#check_pw").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-chkpw")
		
		var reg // 정규식
		var msg // 메시지
		
		/* 에러메시지 띄워주는 함수 */
		if( $(this).val() == '' ){
			emptyMsg(currNode, errNode)
		} else {
			
			if( $("#user_pw").val() != $(this).val() ){
				$(this).next()
					.html("비밀번호가 일치하지 않습니다.변경하지 않으실 거면 비워두세요.(비밀번호 입력 폼도 같이)")
					.css("color", "red")
					.css("font-size", "10px")
					
				/* 작성된 내용을 지우고 #user_pw로 포커스 이동시키기 */
//				$("#user_pw").val("") // 내용 지우기
				$(this).val("")
//				$("#user_pw").focus()
				$(this).focus()
				
					
			} else{
				$(this).next().html("")
			} //  안쪽 } else{ - end
		} // 바깥쪽 } else { - end
		
	}) // $("#check_pw").on("blur", function(){ - end
	
	
}) // $(document).ready(function(){ - end

function emptyMsg(currNode, errNode) {
	/* 입력 폼이 비어있을 때 경고 메시지 */
	if($(currNode).val() == ''){
		$(errNode)
			.html("필수입력 사항입니다.")
			.css({"font-size": "10px", "color": "red"})

	} else {
		$(errNode).html("")
	}
}
function invalidMsg( currNode, reg, msg) {
	/* 정규식.test("문자열") 비교 */
	if( !reg.test( $(currNode).val() )){
		$(currNode).next().html(msg)
			.css({"font-size": "10px", "color": "red"})
	} else{
		$(currNode).next().html("")
	}
}