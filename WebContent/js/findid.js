/**
 * 아이디 찾기 view 에서 이름, 이메일 폼 비어있는지 확인
 * 아이디 찾기 버튼 누르면 폼 submit
 */

$(document).ready(function(){
	
	$("#name").focus()
	
	/* 로고 클릭시 */
	$("#logo").on("click", function(){
//		console.log("logo clicked!!!")
		$(location).attr("href", "/anibuddy/")
	})
	
	// 아이디 찾기 버튼 누르면 form submit 발생
	$("#btnFindId").on("click", function(){
		
// 		console.log($(this).parent())
		$(this).parent().submit() // form submit
		
	})
	
	// 비밀번호 찾기 버튼 누르면 form submit 발생
	$("#btnFindPw").on("click", function(){
//		console.log($(this).parent())
		$(this).parent().submit() // form submit
	})
	
	// form submit
	//	이름 입력 폼 비어있을 때, 이메일 입력 폼 비어있을 때 입력하도록 유도
	$("#find-form").on("submit", function(){
// 		console.log("form submit event!")

		if( $("#name").val() == '' ){ // 이름 입력 폼 비어있을 때
			var errNode = $("#err-name")
			printErr(errNode)
			$("#name").focus()
			return false
		} else {
			$("#err-name").html("")
		}
		
		if( $("#email").val() == ''){ // 이메일 입력 폼 비어있을 때
			var errNode = $("#err-email")
			printErr(errNode)
			$("#email").focus()
			
			return false
		} else {
			$("#err-email").html("")
		}
		
		if( $("#userid") &&  $("#userid").val() == ''){ // 아이디 입력 폼이 존재하고 아이디 입력 폼 비어있을 때
			var errNode = $("#err-id")
			printErr(errNode)
			$("#userid").focus()
			
			return false
			
		} else if( $("#userid") && $("#userid").val() != ''){
			$("#err-id").html("")
		}

		return true
	})
	
	// 비밀번호 찾으러 가기 눌렀을 때
	$("#btnToFindPw").on("click", function(){
//		console.log("비밀번호 찾으러 가기 버튼 clicked")
		$(location).attr("href", "../user/findpw" )
	})
	
	// 로그인 화면으로 버튼 눌렀을 때
	$("#btnToLogin").on("click", function(){
//		console.log("로그인 화면으로 버튼 clicked")
		$(location).attr("href", "../user/login")
	})
	
	// 회원가입 화면으로 버튼 눌렀을 때
	$("#btnToJoin").on("click", function(){
		$(location).attr("href", "../user/join")
	})
	
	// 메인 화면으로 버튼 눌렀을 때
	$("#btnToMain").on("click", function(){
		$(location).attr("href", "../")
	})
	
})

function printErr(errNode) {
	$(errNode)
		.html("<h4>필수 입력 사항입니다.</h4>")
		.css("color", "red")
		.css("font-size", "10px")
}