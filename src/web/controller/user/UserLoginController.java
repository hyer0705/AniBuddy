package web.controller.user;

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
 * Servlet implementation class UserLoginController
 */
@WebServlet("/user/login")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/anibuddy/user/login - [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/user/login.jsp")
		.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/anibuddy/user/login - [POST]");
		
		// 전달파라미터 얻기 - 로그인 정보
		UserTB user = userService.getLoginUser(req);
		
		
		// 로그인 인증 
		boolean login = userService.login(user);
		
		if(login) {
			// 로그인 사용자의 정보 얻어오기
			user = userService.info(user);
			// 세션 정보 저장하기
			HttpSession session = req.getSession();
			session.setAttribute("login", login);
			session.setAttribute("loginid", user.getUserId());
			session.setAttribute("loginnick", user.getNick());
			session.setAttribute("userno", user.getUserNo()); // 사용자 번호
			
			// 전문가 여부 인데 한 번 확인
			session.setAttribute("username", user.getUserName());
			session.setAttribute("isexpert", user.getIsExpert());
			
			session.setMaxInactiveInterval(43200); // 12시간 지속
			
			resp.sendRedirect(req.getContextPath() + "/");
			
		} else {
			
			// 로그인 실패시 일단! 로그인 페이지로 리다이렉트
			resp.sendRedirect(req.getContextPath() + "/user/login");
		}
		
		
		
	}

}
