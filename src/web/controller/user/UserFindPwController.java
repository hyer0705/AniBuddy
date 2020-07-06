package web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.UserTB;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserFindPwController
 */
@WebServlet("/user/findpw")
public class UserFindPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/anibuddy/user/findpw - [GET]");
		req.getRequestDispatcher("/WEB-INF/views/user/findPw.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/anibuddy/user/findpw - [POST]");
		
		// 조회할 회원 정보 얻기
		UserTB user = userService.getFindUser(req);
//		System.out.println("UserFindPwController - user: " + user);
		
		// 회원 존재 여부 조회
		UserTB isUser = userService.findPw(user);
//		System.out.println("UserFindPwController - isUser: " + isUser);
		
		// 회원 존재 여부
		if( isUser == null) { // 조회한 회원이 없을 때
//			System.out.println("회원 없음~");
			req.setAttribute("isSuccess", false);
			// VIEW 지정
			req.getRequestDispatcher("/WEB-INF/views/user/findPw_result.jsp")
			.forward(req, resp);
		} else { // 조회한 회원이 있을 때
			req.setAttribute("isSuccess", true);
//			System.out.println("회원 있음~");
			req.setAttribute("userno", isUser.getUserNo());
			req.getRequestDispatcher("/WEB-INF/views/user/updatePw.jsp")
			.forward(req, resp);
//			resp.sendRedirect("/anibuddy/user/updatepw");
		}
		
		
	}
	
}
