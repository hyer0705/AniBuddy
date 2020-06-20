/**
 * 로그인 페이지 자바스크립트 파일
 */
$(document).ready(function(){
	
	/* 로그인 화면으로 이동시 아이디 입력하는 인풋에 포커스 */
	$("#userid").focus()
	
	/* 로고 클릭 시 메인화면으로 이동 */
	$("#logo").click(function(){
		$(location).attr("href", "../" )
	})
	
	/* input[type=text] 또는 아이디가 uid 인 인풋에 blur 이벤트(포커스를 잃음) 적용 */
	$("#userid").on("blur", function(){

		if($(this).val() == ''){
			/* css 생각하기~ */
			$("#error-id").html("<p>아이디를 입력하세요!</p>")
				.css("color", "red")
				.css("font-size", "10px")
			
		} else {
			$("#error-id").html("")
		}

	})
	
	/* 비밀번호 입력하는 input에 blur 이벤트 적용  */
	$("#userpw").on("blur", function(){

		if($(this).val() == ''){
			$("#error-pw").html("<p>비밀번호를 입력하세요!</p>")
				.css("color", "red")
				.css("font-size", "10px")

		} else {
			$("#error-pw").html("")
		}

	})
	
	/* 비밀번호 입력하는 input에 keydown 이벤트 적용 */
	//패스워드 입력 창에서 엔터 입력 시 form submit
	$("#userpw").on("keydown", function(key){
		if(key.keyCode == 13){
			/* 아이디를 입력하지 않았을 때 -> 입력하라고 아이디 입력창으로 focus */
			if( $("#userid").val() == ''){
				$("#userid").focus()
				
			} else if ( $("#userpw").val() == '' ){ /* 비밀번호를 입력하지 않았을 때 -> 입력하라고 비밀번호 입력창으로 focus */
				$("#userpw").focus()
			}
			
			/* 아이디와 비밀번호 모두 입력했을 때 submit 이벤트 발생 */
			if( $("#userid").val() != '' 
					&& $("#userpw").val() != ''){
				
				/* 비밀번호 입력창의 부모인 form 태그를 찾아서 submit */
				$(this).parents("form").submit()
				
			}
		}
	})

	/* 로그인 버튼 클릭 시 submit() */
	$("#btnLogin").on("click", function(){
		
		/* 아이디를 입력하지 않았을 때 -> 입력하라고 아이디 입력창으로 focus */
		if( $("#userid").val() == ''){
			$("#userid").focus()
			
		} else if ( $("#userpw").val() == '' ){ /* 비밀번호를 입력하지 않았을 때 -> 입력하라고 비밀번호 입력창으로 focus */
			$("#userpw").focus()
		}
		
		/* 아이디와 비밀번호 모두 입력했을 때 submit 이벤트 발생 */
		if( $("#userid").val() != '' 
				&& $("#userpw").val() != ''){
			
			$("#login-form").submit()
			
		}
		
	})
	
	/* 회원가입 버튼 클릭시 회원가입 페이지로 이동 */
	$("#btnJoin").on("click", function(){
		$(location).attr("href", "../user/join" )
	})
	
})