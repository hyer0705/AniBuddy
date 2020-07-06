package web.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.UserTB;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserEmailCheckController
 */
@WebServlet("/user/chkEmail")
public class UserEmailCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/user/join.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/user/chkEmail - [POST]");
		
		resp.setContentType("application/json;charset=utf-8");
		
		// 응답 출력 스트림
		PrintWriter out = resp.getWriter();
		
		// email = userEmail + "@" + userEDomain
		String email = "";
		
		// userEmail 값 가져오기
		String userEmail = req.getParameter("userEmail");
		// userEDomain 값 가져오기
		String userEDomain = req.getParameter("userEDomain");
		
		email = userEmail + "@" + userEDomain;
//		System.out.println("UserEmailCheck email: " + email);
		
		boolean isEmail = userService.chkEmail(email);
//		System.out.println("UserEmailController isEmail: " + isEmail);
		
		String result = "";
		if(isEmail) {
//			System.out.println("이미 사용중인 아이디입니다");
			result = "{\"msg\":\"이미 사용중인 이메일입니다.\", \"data\": true}";
		} else {
//			System.out.println("아이디 없음");
			result = "{\"msg\":\"없는 이메일 입니다. 사용 가능한 이메일입니다.\", \"data\": false}";
		}
		
		out.println(result);
		
	}
}
