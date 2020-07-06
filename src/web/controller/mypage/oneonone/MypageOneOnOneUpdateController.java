package web.controller.mypage.oneonone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.OneOnOne;
import web.service.face.UserOneOnOneService;
import web.service.impl.UserOneOnOneServiceImpl;

/**
 * Servlet implementation class MypageOneOnOneUpdateController
 */
@WebServlet("/mypage/oneononeUpdate")
public class MypageOneOnOneUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserOneOnOneService userO3Service = new UserOneOnOneServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인이 안됐을 때 메인페이지로 리다이렉트
		if(req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
//		System.out.println("/anibuddy/mypage/oneononeUpdate - [GET]");
		
		// 게시글 번호 얻어오기
		OneOnOne detail = userO3Service.getParam(req);
//		System.out.println("MypageO3UpdateController detail: " + detail);
		
		// 게시글 정보 얻어오기
		detail = userO3Service.detail(detail);
//		System.out.println("MypageO3UpdateController detail: " + detail);
		
		// req로 1:1문의글 정보 넘겨주기
		req.setAttribute("detail", detail);
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/oneonone_update.jsp")
			.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인이 안됐을 때 메인페이지로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
//		System.out.println("/anibuddy/mypage/oneononeUpdate - [POST]");
		
		userO3Service.update(req);
		
		// 디테일 페이지로 리다이렉트
		resp.sendRedirect("/anibuddy/mypage/oneononedetail?oneonone_no=" + req.getParameter("oneonone_no"));
		
	}
	
}
