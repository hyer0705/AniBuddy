package web.controller.mypage.postmessage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.UserTB;
import web.service.face.PostMessageService;
import web.service.face.UserService;
import web.service.impl.PostMessageServiceImpl;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class PostMessageSendController
 */
@WebServlet("/mypage/sendpm")
public class PostMessageSendController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PostMessageService pmService = new PostMessageServiceImpl();
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안되어 있을 시 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/mypage/sendpm - [GET]");
		
		// pm_recipient_id 로 사용자 번호 설정
		UserTB recipient = pmService.getUserByRecipientId(req);
		System.out.println("PMSendController recipient 조회 전: " + recipient);
		
		// 사용자 조회
		recipient = userService.getFindUserByUserno(recipient);
		System.out.println("PMSendController recipient 조회 후: " + recipient);
		
		
		// req 받는 사람 설정
		req.setAttribute("recipient", recipient);
		
		// VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/mypage/postmessage_write.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안되어 있을 시 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}

		System.out.println("/anibuddy/mypage/sendpm - [POST]");
		
		pmService.write(req);
		
		// 쪽지 리스트로 리다이렉트
		resp.sendRedirect("/anibuddy/mypage/pmlist");
		
	}
	
}
