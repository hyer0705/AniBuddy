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
 * Servlet implementation class UserFindIdController
 */
@WebServlet("/user/findid")
public class UserFindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/user/findid - [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/user/findId.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/user/findid - [POST]");
		
		// 조회할 회원 정보 얻기
		UserTB user = userService.getFindUser(req);
//		System.out.println("UserFindIdController user: " + user);
		
		// 회원 존재 여부 조회
		UserTB isUser = userService.findId(user);
//		System.out.println("UserFindIdController isUser: " + isUser);
		
		// 회원 존재 여부\
		if( isUser == null ) { // 조회한 회원이 없을 때
			req.setAttribute("isSuccess", false);
		} else { // 조회한 회원이 있을 때
			req.setAttribute("userid", isUser.getUserId());
			req.setAttribute("isSuccess", true);
		}
		
		req.getRequestDispatcher("/WEB-INF/views/user/findId_result.jsp")
		.forward(req, resp);
	}

}
