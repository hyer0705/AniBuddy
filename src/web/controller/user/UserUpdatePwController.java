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
 * Servlet implementation class UserUpdatePwController
 */
@WebServlet("/user/updatepw")
public class UserUpdatePwController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userSerivce = new UserServiceImpl();
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		System.out.println("/anibuddy/user/updatepw - [GET]");
//		
//		// VIEW 지정 - froward
//		req.getRequestDispatcher("/WEB-INF/views/user/updatePw.jsp")
//			.forward(req, resp);
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/anibuddy/user/updatepw - [POST]");

		// 조회할 회원 정보 얻기 - userpw, userno
		UserTB user = userSerivce.getFindUserByUserno(req);
//		System.out.println("UserUpdatePwController - user: " + user);
		
		// 비밀번호 재설정
		boolean isUpdate = userSerivce.updatePw(user);
//		System.out.println("UserUpdatePwController - isUpdate: " + isUpdate);
		
		if(isUpdate) { // 비밀번호 업데이트 성공시
			
			resp.sendRedirect("/anibuddy/user/login");
			
			return;
		} else { // 비밀번호 업데이트 실패시, 존재하는 회원이 아닙니다  보여주기
			System.out.println("업데이트 실패~~~");
//			req.setAttribute("isUpdate", false);
//			req.getRequestDispatcher("/WEB-INF/views/user/findPw_result.jsp")
//				.forward(req, resp);
		}
		
		
	}
	
}
