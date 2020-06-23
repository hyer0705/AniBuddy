package web.controller.mypage.oneonone;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.OneOnOne;
import web.dto.UserTB;
import web.service.face.UserOneOnOneService;
import web.service.face.UserService;
import web.service.impl.UserOneOnOneServiceImpl;
import web.service.impl.UserServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class MypageOneOnOneListController
 */
@WebServlet("/mypage/oneononelist")
public class MypageOneOnOneListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserOneOnOneService usero3Service = new UserOneOnOneServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안된 상태면 메인화면으로 리다이렉트
		if( req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/anibuddy/");
			return;
		}
		
		System.out.println("/anibuddy/mypage/oneononelist - [GET]");
		
		// 페이징 정보 설정
		Paging paging = usero3Service.getPaging(req);
//		System.out.println("MypageO3Controller paging: " + paging);
		
		// 유저정보 얻기
		UserTB currUser = new UserTB();
		int userno = (int)req.getSession().getAttribute("userno");
		currUser.setUserNo(userno);
//		System.out.println("MypageO3Controller currUser: " + currUser);
		
		List<OneOnOne> o3 = usero3Service.getOneOnOneList(paging, currUser);
		
		// 요청 페이지에 paging 정보와 o3 정보 반환
		req.setAttribute("paging", paging);
		req.setAttribute("o3", o3);
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/oneononelist.jsp")
			.forward(req, resp);
	}
	
}
