/**
 * 비밀번호 수정할 때
 */
$(document).ready(function(){
	
	// 비밀번호 입력 폼 focus
	$("#userpw").focus()
	
	/* 로고 클릭시 메인화면으로 이동 */
	$("#logo").on("click", function(){
// 		console.log("logo clicked")

		$(location).attr("href", "/anibuddy/")
	})
	
	// 비밀번호 재설정 버튼 누르면 form submit 발생
	$("#btnUpdatePw").on("click", function(){
// 		console.log("비밀번호 재설정 버튼 clicked!!!")
// 		console.log($(this).parent())
		$(this).parent().submit() // form submit 이벤트 발생
	})
	
	// 비밀번호 blur 이벤트
	$("#userpw").on("blur", function(){
		
// 		console.log("비밀번호 입력 폼 blur~")
		var currNode = $(this)
		var errNode = $("#err-pw")
		var msg = ""
		var reg
		
		if( $(this).val() == ''){ // 비밀번호 입력 폼이 비어있을 때
			msg = "<h4>필수 입력사항입니다.</h4>"
			printErr(errNode, msg)
		} else {
// 			#user_pw : 비밀번호는 8글자 이상 20자 이하의 영어 대소문자와 숫자만 사용 가능합니다.
			reg = /^[A-Za-z0-9]{8,20}$/
			msg = "<h4>비밀번호는 8글자 이상 20자 이하의 영어 대소문자와 숫자만 사용 가능합니다.</h4>"

			invalidMsg( currNode, errNode, reg, msg)
		}// 바깥쪽 else end
		
	})
	
	// 비밀번호 재확인 입력 폼 blur 이벤트
	$("#chkpw").on("blur", function(){
// 		console.log("비밀번호 재확인 입력 폼 blur~")

		var currNode = $(this)
		var errNode = $("#err-chkpw")
		var msg = ""
		var reg
		
		if( $(this).val() == ''){ // 비밀번호 재확인 입력 폼이 비어있을 때
			msg = "<h4>필수 입력사항입니다.</h4>"
			printErr(errNode, msg)
		} else {
			if( $(currNode).val() != $("#userpw").val() ) { // 비밀번호 입력 폼 값 != 비밀번호 재확인 입력 폼 값
				msg = "<h4>비밀번호가 일치하지 않습니다.</h4>"
				printErr(errNode, msg)
				
				// 비밀번호 재확인 입력 폼의 입력한 값을 지우고 focus
				$(currNode).val("").focus()
				
			} else {
				$(errNode).html("") // 에러 메시지 비우기
			} // 안쪽 else end
			
		} // 바깥쪽 else end
		

	})
	
	// form submit
	// 비밀번호, 비밀번호 확인 
	$("#update-form").on("submit", function(){
		
// 		console.log("update-form submit event 발생!")
		
		// 비밀번호 검증
		if( $("#userpw").val() == ''){ // 비밀번호 폼이 비어있을 때!
			
			// 비밀번호 입력 폼으로 포커스
			$("#userpw").focus()
			// submit 중단
			return false
		} else if( $("#userpw").val() != '') { // 비밀번호 폼이 비어있지 않을 때!
			var pwReg = /^[A-Za-z0-9]{8,20}$/
			if( !pwReg.test( $("#userpw").val() ) ){
				
				// 비밀번호 입력 폼 비우고 포커스
				$("#userpw").val("")
							.focus()
				// submit 중단
				return false
			}
		}
		
		// 비밀번호 일치하지 않을 때
		if($("#userpw").val() != $("#chkpw").val()){
			// 비밀번호 입력 폼 비우기
			// 비밀번호 재확인 입력 폼 비우기
			// 비밀번호 입력 폼 focus
			
			$("#chkpw").val("")
			$("#userpw").val("").focus()
			return false
		}
		
		return true
	})
	
	/* 엔터키 누를 시 submit event 동작 */
	$("#chkpw").on("keydown", function(key){
		if(key.keyCode == 13){
			$(this).parents("form").submit()
		}
	})
	
})

function printErr(errNode, msg) {
// 	console.log("printErr~")

	$(errNode)
		.html(msg)
		.css("color", "red")
		.css("font-size", "10px")
	
}

function invalidMsg( currNode, errNode, reg, msg) {
	/* 정규식.test("문자열") 비교 */
	if( !reg.test( $(currNode).val() )){
		$(errNode).html(msg)
			.css("color", "red")
			.css("font-size", "10px")
	} else{
		$(errNode).html("")
	}
}