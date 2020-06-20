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
 * Servlet implementation class UserIdCheckController
 */
@WebServlet("/user/chkId")
public class UserIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/user/join.jsp")
			.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/user/chkId [POST]");
		
		resp.setContentType("application/json;charset=utf-8");
		// 응답 출력 스트림
		PrintWriter out = resp.getWriter();
		
		// userId 값 가져오기
		String userId = req.getParameter("userId");
		UserTB user = new UserTB();
		user.setUserId(userId);
		
		boolean isId = userService.chkId(user);
		
		String result = "";
		if(isId) {
//			System.out.println("이미 사용중인 아이디입니다");
			result = "{\"msg\":\"이미 사용중인 아이디입니다.\", \"data\": true}";
		} else {
//			System.out.println("아이디 없음");
			result = "{\"msg\":\"없는 아이디 입니다. 사용 가능한 아이디입니다.\", \"data\": false}";
		}
		
		out.println(result);
	}

}
