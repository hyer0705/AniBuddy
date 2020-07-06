package web.controller.mypage.user;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.UserFile;
import web.dto.UserTB;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserUpdateController
 */
@WebServlet("/mypage/userupdate")
public class UserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인이 되어있지 않으면 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect( req.getContextPath() + "/");
			return;
		}
		
//		System.out.println("/anibuddy/mypage/userupdate- - [GET]");
		
		// 사용자 정보 얻어오기
		UserTB user = userService.getLoginUserByUserno(req);
//		System.out.println("UserUpdateController user: " + user);
		
		// 사용자 번호로 검색하기
		user = userService.getFindUserByUserno(user);
//		System.out.println("UserUpdateController user: " + user);
		
		// 사용자 정보 req에 설정
		req.setAttribute("user", user);
//		System.out.println(user.getGender());
		
		// 생년월일
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birth = sdf.format(user.getBirth());
//		System.out.println("UserUpdate birth: " + birth);
		
		String[] tmp = birth.split("-");
		req.setAttribute("byear", tmp[0]);
		req.setAttribute("bmonth", tmp[1]);
		req.setAttribute("bday", tmp[2]);

		// email
		String[] email = user.getEmail().split("@");
		req.setAttribute("email", email);
		
		// 이미지 프로필 사진 전달
		UserFile userProfile = userService.viewProfileFile(user);
//		System.out.println("UserUpdateController userProfile: " + userProfile);
		if(userProfile != null) {
			req.setAttribute("isProfile", true);
			req.setAttribute("userProfile", userProfile);
//			req.setAttribute("imgurl", "../upload/"+userProfile.getStoreName());
		}
		
		// VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/mypage/userupdate.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// post 요청 완료 출력
//		System.out.println("/anibuddy/mypage/userupdate - [POST]");
		
		// 회원정보 수정
		userService.updateUserInfo(req);
		
		
		// 회원정보 수정 페이지로 리다이렉트
		resp.sendRedirect( req.getContextPath() + "/mypage/userupdate");
	}

}
