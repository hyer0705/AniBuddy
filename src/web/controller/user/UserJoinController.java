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
 * Servlet implementation class UserJoinController
 */
@WebServlet("/user/join")
public class UserJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/user/join - [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/user/join.jsp")
			.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/user/join - [POST]");
		
		// 요청 파라미터 처리
		UserTB param = userService.getJoinUser(req);
//		System.out.println("UserJoinController joinUser: " + param);
		
		// 회원가입
		userService.join(param);
		
		// 로그인 페이지로 리다이렉션
		resp.sendRedirect( req.getContextPath() + "/user/login");
		
	}
	
	
	

}
