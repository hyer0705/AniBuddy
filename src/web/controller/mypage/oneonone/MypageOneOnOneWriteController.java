package web.controller.mypage.oneonone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.UserOneOnOneService;
import web.service.impl.UserOneOnOneServiceImpl;

/**
 * Servlet implementation class MypageOneOnOneWriteController
 */
@WebServlet("/mypage/oneononewrite")
public class MypageOneOnOneWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserOneOnOneService userO3Service = new UserOneOnOneServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인을 안했을 경우 메인페이지로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/mypage/oneononewrite - [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/oneonone_write.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그인이 안했을 경우 메인페이지로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/mypage/oneononewrite - [POST]");
		
		// 작성글 삽입
		userO3Service.write(req);
		
		// 목록으로 리다이렉션
		resp.sendRedirect( req.getContextPath() + "/mypage/oneononelist");
		
	}

}
