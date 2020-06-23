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
 * Servlet implementation class MypageOneOnOneDelete
 */
@WebServlet("/mypage/oneononeDelete")
public class MypageOneOnOneDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserOneOnOneService userO3Service = new UserOneOnOneServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안됐을 시 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/mypage/oneononeDelete - [GET]");
		
		// oneonone_no 정보 얻기
		OneOnOne o3 = userO3Service.getParam(req);
		
		// 삭제~
		userO3Service.delete(o3);
		
		// oneonone 게시판으로 리다이렉트
		resp.sendRedirect("/anibuddy/mypage/oneononelist");
		
		
	}

}
