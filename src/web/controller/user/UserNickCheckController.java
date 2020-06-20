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
 * Servlet implementation class UserNickCheckController
 */
@WebServlet("/user/chkNick")
public class UserNickCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/user/join.jsp")
			.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/user/chkNick - [POST] ");
		
		// 보내는 파일 한글 인코딩
		resp.setContentType("application/json;charset=utf-8");
		
		// 응답 출력 스트림
		PrintWriter out = resp.getWriter();
		
		// 닉네임 가져오기
		String userNick = req.getParameter("userNick");
//		System.out.println("UserNickCheckController userNick: " + userNick);
		UserTB user = new UserTB();
		user.setNick(userNick);
		
		// 닉네임 조회
		boolean isNick = userService.chkNick(user);
//		System.out.println("UserNickCheckController isNick: " + isNick);
		
		String result = "";
		if(isNick) {
			result = "{\"msg\":\"이미 사용중인 닉네임입니다.\", \"data\": true}";
		} else {
			result = "{\"msg\":\"없는 닉네임입니다. 사용 가능한 닉네임입니다.\", \"data\": false}";
		}
		
		out.println(result);
		
	}
	
}
