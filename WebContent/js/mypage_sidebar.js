/**
 * mypage sidebar 메뉴 클릭시 
 */
$(document).ready(function(){
	$("#muserinfo").on("click", function(){
// 		console.log("회원정보 clicked")
		$(location).attr("href", "/anibuddy/mypage/userupdate")
	})
	
	$("#moneonone").on("click", function(){
// 		console.log("1:1문의 clicked")
		$(location).attr("href", "/anibuddy/mypage/oneononelist")
	})
	
	$("#mdeleteuser").on("click", function(){
// 		console.log("회원 탈퇴 clicked")
		$(location).attr("href", "/anibuddy/mypage/userdelete")
	})
	
	$("#mactivity").on("click", function(){
		$(location).attr("href", "/anibuddy/activity/postlist")
	})
	
	$("#mpostmessage").on("click", function(){
		$(location).attr("href", "/anibuddy/mypage/pmlist")
	})
	
})