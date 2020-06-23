/**
 * 회원 탈퇴 페이지 javascript file
 */
$(document).ready(function(){
	
	// 로고 클릭시 메인 화면으로
	$("#logo").on("click", function(){
		$(location).attr("href", "/anibuddy/")
	})
	
	// 비밀번호를 입력할 수 있도록 focus
	$("#userpw").focus()
	
	// 비밀번호 입력폼이 비어있을 때 에러 문구 출력
	$("#userpw").on("blur", function(){
// 		console.log("비밀번호 blur~")
		if( $(this).val() == ''){ // 비밀번호 입력 폼이 비어있을 때
			$("#error-pw")
				.html("입력하셔야 탈퇴를 할 수 있어요...")
				.css("font-size", "10px")
				.css("color", "red")
		} else {
			$("#error-pw").html("")
		}
	})
	
	// 회원 탈퇴 버튼 누르면 form submit 발생
	$("#btnDelete").on("click", function(){
		$(this).parents("form").submit()
	})
	
	// form submit 발생
	$("#delete-form").on("submit", function(){
		if( $("#userpw").val() == '') { // 비밀번호 입력 폼에 아무것도 입력하지 않았을 때
			$("#userpw").focus()
			
			return false
		}
		return true
	})
	
})