package web.controller.mypage.postmessage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.PostMessageService;
import web.service.impl.PostMessageServiceImpl;

/**
 * Servlet implementation class PostMessageSendToAnonymousController
 */
@WebServlet("/mypage/sendtoanonymous")
public class PostMessageSendToAnonymousController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PostMessageService pmService = new PostMessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안할 시 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
//		System.out.println("/anibuddy/mypage/sendtoanonymous - [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/postmessage_write_anonymous.jsp")
			.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그인 안할 시 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
//		System.out.println("/anibuddy/mypage/sendtoanonymous - [POST]");
		
		pmService.write(req);
		
		// 쪽지 리스트로 리다이렉트
		resp.sendRedirect("/anibuddy/mypage/pmlist");
		
		
	}
	
}
