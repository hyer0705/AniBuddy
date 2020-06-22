/**
 * 
 */
$(document).ready(function(){
	
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
	
})