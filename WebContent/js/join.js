/**
 * 회원가입 입력 폼 검증 및 아이디, 이메일, 닉네임 중복체크
 */
var cnt = 0

$(document).ready(function(){
	/* 로그인 화면으로 이동시 아이디 입력하는 인풋에 포커스 */
	$("#user_id").focus()
	
	/* 로고 클릭 시 메인화면으로 이동 */
	$("#logo").click(function(){
		$(location).attr("href", "../" )
	})
	
	/* 회원가입 버튼 클릭시 form submit */
	$("#btnJoin").on("click", function(){
		$("#join-form").submit()
	})
	
	/* 취소 버튼 클릭시 main 화면으로 이동 */
	$("#btnCancel").on("click", function(){
		$(location).attr("href", "../" )
	})
	
	/* 필수 입력폼에 입력을 하지않고 벗어날 시에 빨간 글씨로 "필수입력 사항입니다" 알려주기 */
	// 1. 아이디
	$("#user_id").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-id")
		
		var reg // 정규식
		var msg // 메시지

		/* 에러메시지 띄워주는 함수 */
		if( $(this).val() == ''){
			emptyMsg(currNode, errNode)
		} else {
			// #user_id : 아이디는 6자 이상 20자 이하의 영어 대소문자와 숫자 특수기호 '_'만 사용 가능합니다.
			reg = /^[A-Za-z0-9_]{6,20}$/
			msg = "<h4>아이디는 6자 이상 20자 이하의 영어 대소문자와 숫자 특수기호 '_' 사용 가능합니다.</h4>"

			
			if( !reg.test( $(currNode).val() )){
				invalidMsg( currNode, reg, msg)
			} else {
				// 아이디 중복 확인
				$.ajax({
					type:"post"	// 요청 method
					, url: "../user/chkId"
					, data: { // 전달 파라미터
						userId: $("#user_id").val()
					}
					, dataType: "json"
					, success: function( res ){
						console.log($("#isId").val())
// 						console.log(res.msg)
						if(res.data){
							// 아이디 이미 존재할 때
							$("#err-id")
								.html(res.msg)
								.css("color", "red")
								.css("font-size", "10px")
							$("#isId").val("N")
						} else {
							// 아이디 존재하지 않을 때
							$("#err-id")
								.html(res.msg)
								.css("color", "green")
								.css("font-size", "10px")
							$("#isId").val("Y")
						}
					}
					, error: function(){
						console.log("ajax 실패")
					}
				}) // $.ajax({ : end
			} // else end
			
		}
		
	}) // $("#user_id").on("blur", function(){ end
	
	// 2. 비밀번호
	$("#user_pw").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-pw")
		
		var reg // 정규식
		var msg // 메시지
		
		/* 에러메시지 띄워주는 함수 */
		if( $(this).val() == ''){
			emptyMsg(currNode, errNode)
		} else {
// 			#user_pw : 비밀번호는 8글자 이상 20자 이하의 영어 대소문자와 숫자만 사용 가능합니다.
			reg = /^[A-Za-z0-9]{8,20}$/
			msg = "<h4>비밀번호는 8글자 이상 20자 이하의 영어 대소문자와 숫자만 사용 가능합니다.</h4>"

			if( !reg.test( $(currNode).val() )){
				invalidMsg( currNode, reg, msg)
			}
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
					.html("<h4>비밀번호가 일치하지 않습니다.</h4>")
					.css("color", "red")
					.css("font-size", "10px")
					
				/* 작성된 내용을 지우고 #user_pw로 포커스 이동시키기 */
//				$("#user_pw").val("") // 내용 지우기
				$(this).val("")
//				$("#user_pw").focus()
				$(this).focus()
				
					
			} else{
				$(this).next().html("")
			}
		}
		
	})
	
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
			msg = "<h4>이름은 2자 이상의 한글만 사용 가능합니다.</h4>"

			if( !reg.test( $(currNode).val() )){
				invalidMsg( currNode, reg, msg)
			}
		}
		
	})
	
	// 5. 닉네임
	$("#nick").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-nick")
		
		var reg // 정규식
		var msg // 메시지
		
		/* 에러메시지 띄워주는 함수 */
		if( $(this).val() == ''){
			emptyMsg(currNode, errNode)
		} else {
			// #nick : 닉네임은 2자 이상의 한글과 영어대소문자, 숫자만 사용가능합니다.
			reg = /^[A-Za-z0-9가-힣]{2,20}$/
			msg = "<h4>닉네임은 2자 이상, 20자 이하의 한글과 영어대소문자, 숫자만 사용가능합니다.</h4>"

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
							// 아이디 존재
							$("#err-nick")
								.html(res.msg)
								.css("color", "red")
								.css("font-size", "10px")
							$("#isNick").val("N")
						} else {
							// 아이디 존재하지 않음
							$("#err-nick")
								.html(res.msg)
								.css("color", "green")
								.css("font-size", "10px")
							$("#isNick").val("Y")
						}
					}
					, error: function(){
						console.log("ajax 실패")
					}
				}) // $.ajax({ : end
			} // else end

		} // 바깥쪽 else end
		
	}) // $("#nick").on("blur", function(){
	
	// 6. 성별
	$("#gender").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-gender")
		
		/* 에러메시지 띄워주는 함수 */
		emptyMsg(currNode, errNode)
		
	})
	
	
	// 7. 생년월일 이건 좀 생각해보기
	//	7-1. 년도(4자리수)
	$("#yy").on("blur", function(){
		
		if($(this).val() == '' || $(this).val().length < 4){
			$("#err-birth")
			.html("<h4>태어난 년도 4자리를 정확하게 입력해주세요!</h4>")
			.css("color", "red")
			.css("font-size", "10px")
		} else{
			$("#err-birth").html("")
		}
	})
	//	7-2. 월
	$("#mm").on("blur", function(){
		if( $("#yy").val() != '' && $(this).val() == ''){
			$("#err-birth")
			.html("<h4>태어난 월을 선택해주세요!</h4>")
			.css("color", "red")
			.css("font-size", "10px")
		} else{
			$("#err-birth").html("")
		}
	})
	//	7-3. 일
	$("#dd").on("blur", function(){
		if( $("#yy").val() != '' 
				&& $("#mm").val() != ''
				&& $(this).val() == '' ){
			$("#err-birth")
			.html("<h4>태어난 일(날짜) 2자리를 정확하게 입력하세요!</h4>")
			.css("color", "red")
			.css("font-size", "10px")
		} else{
			$("#err-birth").html("")
		}
	})
	
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
				}
				, error: function(){
					console.log("ajax 실패")
				}
			}) // $.ajax({ : end
		} 
//		else {
//			$(errNode).html("")
//		} // else end
		
	})
	
	// 8-2. 이메일 도메인
	$("#e-domain").on("blur", function(){
		var currNode = $(this)
		var errNode = $("#err-email")
		
		/* 에러메시지 띄워주는 함수 */
		if( $("email").val() != '' && $(this).val() == ''){
			emptyMsg(currNode, errNode)
		} else if ($("#email").val() != ''){
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
				}
				, error: function(){
					console.log("ajax 실패")
				}
			}) // $.ajax({ : end
		}
	})
	
	// 9. 주소
	$("#sample6_address").on("blur", function(){
		
		var currNode = $(this)
		var errNode = $("#err-addr")
		
		/* 에러메시지 띄워주는 함수 */
		emptyMsg(currNode, errNode)
		
	})
	
	// 9.2 상세주소
	$("#sample6_detailAddress").on("blur", function(){
		var currNode = $(this)
		var errNode = $("#err-addr")
		
		/* 에러메시지 띄워주는 함수 */
		emptyMsg(currNode, errNode)
	})
	
	
	/* form submit 이벤트시 */
	$("#join-form").on("submit", function(){
		
		// 아이디 검증
		if( $("#user_id").val() == '' ){ // 아이디 입력 폼이 비어있을 때!
			
			// 아이디 입력 폼으로 포커스
			$("#user_id").focus()
			// submit 중단
			return false
		} else if( $("#user_id").val() != '') { // 아이디 입력 폼이 비어있지 않을 때!
			var idReg = /^[A-Za-z0-9_]{6,20}$/
			if( !idReg.test( $("#user_id").val() ) ){
				
				// 아이디 입력 폼 비우고 포커스
				$("#user_id").val("")
							.focus()
				return false
			} else if( $("#isId").val() == 'N'){
				$("#user_id").focus().select()
				return false
			}

		}
		
		// 비밀번호 검증
		if( $("#user_pw").val() == ''){ // 비밀번호 폼이 비어있을 때!
			
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
			}
		}
		
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
		
		// 닉네임 비어있는지 검증
		if( $("#nick").val() == ''){ // 닉네임 폼이 비어있을 때
			
			// 닉네임 폼으로 포커스
			$("#nick").focus()
			// submit 중단
			return false
		} else if( $("#nick").val() != ''){ // 닉네임 폼이 비어있지 않을 때
			var nickReg = /^[A-Za-z0-9가-힣]{2,20}$/
			if( !nickReg.test( $("#nick").val() ) ){
				// 닉네임 폼 비우고 포커스
				$("#nick").val("")
							.focus()
							
				// submit 중단
				return false
			} else if( $("#isNick").val() == 'N'){
				$("#nick").focus().select()
				return false
			}
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
			$("#yy").val().focus()
			
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
		} else if( $("#isEmail").val() == 'N'){ // 이메일 중복확인
			$("#email").focus().select()
			return false
		}
		
		// 주소 비어있는지 검증
		if($("#sample6_address").val() == ''){
			
			// 주소 폼으로 focus
			$("#sample6_address").focus()
			return false
		}
		
		return true // submit 수행
		
	})
	
})

function emptyMsg(currNode, errNode) {
	/* 입력 폼이 비어있을 때 경고 메시지 */
	if($(currNode).val() == ''){
		$(errNode)
			.html("<h4>필수입력 사항입니다.</h4>")
			.css("color", "red")
			.css("font-size", "10px")

	} else {
		$(errNode).html("")
	}
}

function invalidMsg( currNode, reg, msg) {
	/* 정규식.test("문자열") 비교 */
	if( !reg.test( $(currNode).val() )){
		$(currNode).next().html(msg)
			.css("color", "red")
			.css("font-size", "10px")
	} else{
		$(currNode).next().html("")
	}
}
