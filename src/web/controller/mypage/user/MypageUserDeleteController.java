package web.controller.mypage.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.UserTB;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class MypageUserDeleteController
 */
@WebServlet("/mypage/userdelete")
public class MypageUserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/anibuddy/mypage/userdelete - [GET]");
		
		// VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/mypage/userdelete.jsp")
			.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/anibuddy/mypage/usertdelete - [POST]");
		
		// 삭제할 사용자 객체 정보 알아오기
		UserTB deleteUser = userService.getFindDeleteUser(req);
//		System.out.println("MypageUserDeleteController deleteUser: " + deleteUser);
		
		// 입력한 비밀번호가 현재 비밀번호와 일치하는지 확인
		boolean chkPw = userService.chkPw(deleteUser);
//		System.out.println("MypageUserDelete chkPw: " + chkPw);
		
		if(chkPw) { // 비밀번호 일치 회원 탈퇴 가능!
			userService.deleteUser(deleteUser);
			req.setAttribute("chkPw", chkPw);
			// 로그아웃도!
			// 세션 객체 얻기
			HttpSession session = req.getSession();
			// 세션 정보 삭제
			session.invalidate();
		}
		
		// VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/mypage/userdelete_result.jsp")
			.forward(req, resp);
		
	}
	
}
